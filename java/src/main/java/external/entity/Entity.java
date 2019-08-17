package main.java.external.entity;

import java.awt.geom.Point2D;

public interface Entity {
    String getId();

    Point2D.Float getPosition();

    float getHealth();

    float getDamage();

    float getSpeed();

    float getSize();
}
