package main.java.entity.impl;

import main.java.external.entity.AntEntity;

import java.awt.geom.Point2D;

public class AntEntityImpl extends AbstractEntityBase implements AntEntity {
    public AntEntityImpl(Point2D.Float position, float health, float damage, float speed, float size) {
        super(position, health, damage, speed, size);
    }
}
