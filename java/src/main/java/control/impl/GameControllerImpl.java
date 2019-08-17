package main.java.control.impl;

import main.java.control.GameController;
import main.java.external.entity.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameControllerImpl implements GameController {
    private List<Entity> entities;

    public GameControllerImpl() {
        this.entities = new ArrayList<>();
    }

    @Override
    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    @Override
    public List<Entity> getEntities() {
        return Collections.unmodifiableList(entities);
    }
}
