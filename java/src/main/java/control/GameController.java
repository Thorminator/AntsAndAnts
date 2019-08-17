package main.java.control;

import main.java.external.entity.Entity;

import java.util.List;

public interface GameController {
    void addEntity(Entity entity);

    List<Entity> getEntities();
}
