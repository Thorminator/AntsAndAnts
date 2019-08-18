package controllerhelper.mocks;

import main.java.entity.impl.AntEntityImpl;
import main.java.external.entity.AntEntity;
import main.java.external.entity.Entity;
import org.jetbrains.annotations.NotNull;

import java.awt.geom.Point2D;

public class EntityBuilder {
    private Point2D.Float position;
    private float health;
    private float damage;
    private float speed;
    private float size;
    private Class<? extends Entity> entityClass;

    public EntityBuilder(@NotNull Class<? extends Entity> entityClass) {
        this.entityClass = entityClass;
    }

    public EntityBuilder setPosition(Point2D.Float position) {
        this.position = position;
        return this;
    }

    public EntityBuilder setHealth(float health) {
        this.health = health;
        return this;
    }

    public EntityBuilder setDamage(float damage) {
        this.damage = damage;
        return this;
    }

    public EntityBuilder setSpeed(float speed) {
        this.speed = speed;
        return this;
    }

    public EntityBuilder setSize(float size) {
        this.size = size;
        return this;
    }

    public Entity createEntity() {
        if (entityClass == null || entityClass.equals(MockEntity.class)) {
            return new MockEntity(position, health, damage, speed, size);
        } else if (entityClass.equals(AntEntity.class)) {
            return new AntEntityImpl(position, health, damage, speed, size);
        } else {
            throw new IllegalStateException("Unknown entity type, please implement!");
        }
    }
}