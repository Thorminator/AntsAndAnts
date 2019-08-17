package main.java.entity.impl;

import main.java.external.entity.Entity;

import java.awt.geom.Point2D;
import java.util.UUID;

public abstract class AbstractEntityBase implements Entity {
    private String id;
    private Point2D.Float position;
    private float health;
    private float damage;
    private float speed;
    private float size;
    // TODO: Insert AIController here

    public AbstractEntityBase(Point2D.Float position, float health, float damage, float speed, float size) {
        id = UUID.randomUUID().toString();
        this.position = position;
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        this.size = size;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Point2D.Float getPosition() {
        return new Point2D.Float(position.x, position.y);
    }

    @Override
    public float getHealth() {
        return health;
    }

    @Override
    public float getDamage() {
        return damage;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public float getSize() {
        return size;
    }
}
