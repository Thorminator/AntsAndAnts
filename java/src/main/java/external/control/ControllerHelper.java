package main.java.external.control;

import main.java.exceptions.IllegalModificationException;
import main.java.external.entity.Entity;

import java.util.List;
import java.util.Map;

public interface ControllerHelper {
    List<Map.Entry<Entity, Float>> getEntitiesSortedByDistanceToGivenEntity(Entity entity) throws IllegalModificationException;
}
