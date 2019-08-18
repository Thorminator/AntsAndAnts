package main.java.control.impl;

import main.java.exceptions.IllegalModificationException;
import main.java.external.control.ControllerHelper;
import main.java.control.GameController;
import main.java.external.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class ControllerHelperImpl implements ControllerHelper {
    private final GameController gameController;

    public ControllerHelperImpl(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    @NotNull
    public List<Map.Entry<Entity, Float>> getEntitiesSortedByDistanceToGivenEntity(@NotNull Entity entity) throws IllegalModificationException {
        Objects.requireNonNull(entity);

        List<Map.Entry<Entity, Float>> resultingList = getEntitiesByDistanceToGivenEntity(entity);

        resultingList.sort((Map.Entry<Entity, Float> ent1, Map.Entry<Entity, Float> ent2)
                -> Float.compare(ent1.getValue(), ent2.getValue()));

        return resultingList;
    }

    @Override
    @Nullable
    public Map.Entry<Entity, Float> getClosestEntityOfType(@NotNull Entity entity, @NotNull Class<? extends Entity> entityType) throws IllegalModificationException {
        Objects.requireNonNull(entity);
        Objects.requireNonNull(entityType);

        List<Map.Entry<Entity, Float>> entitiesByDistanceToGivenEntity = getEntitiesByDistanceToGivenEntity(entity);
        Map.Entry<Entity, Float> closestEntity = null;

        for (Map.Entry<Entity, Float> entityWithDistance : entitiesByDistanceToGivenEntity) {
            boolean correctEntityType = entityType.isAssignableFrom(entityWithDistance.getKey().getClass());
            if (!correctEntityType) {
                continue;
            }

            if (closestEntity == null) {
                closestEntity = entityWithDistance;
                continue;
            }

            boolean currentEntityIsCloser = entityWithDistance.getValue() < closestEntity.getValue();
            if (currentEntityIsCloser) {
                closestEntity = entityWithDistance;
            }
        }

        return closestEntity;
    }

    @NotNull
    private List<Map.Entry<Entity, Float>> getEntitiesByDistanceToGivenEntity(@NotNull Entity entity) throws IllegalModificationException {
        List<Map.Entry<Entity, Float>> unsortedResultingList = new ArrayList<>();
        boolean entityFound = false;

        for (Entity existingEntity : gameController.getEntities()) {
            boolean sameEntity = entity.getId().equals(existingEntity.getId());
            if (sameEntity) {
                entityFound = true;
                continue;
            }

            Float distanceBetweenEntities = (float) existingEntity.getPosition().distance(entity.getPosition());
            unsortedResultingList.add(new AbstractMap.SimpleEntry<>(existingEntity, distanceBetweenEntities));
        }

        if (!entityFound) {
            throw new IllegalModificationException("Found an entity with an id that does not exist in the current game state.");
        }

        return unsortedResultingList;
    }
}
