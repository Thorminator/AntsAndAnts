package main.java.external.control;

import main.java.exceptions.IllegalModificationException;
import main.java.external.entity.AntEntity;
import main.java.external.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public interface ControllerHelper {
    @NotNull
    List<Map.Entry<Entity, Float>> getEntitiesSortedByDistanceToGivenEntity(@NotNull Entity entity) throws IllegalModificationException;

    @Nullable
    Map.Entry<Entity, Float> getClosestEntityOfType(@NotNull Entity entity, @NotNull Class<? extends Entity> entityType) throws IllegalModificationException;
}
