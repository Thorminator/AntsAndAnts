package controllerhelper.mocks;

import java.awt.geom.Point2D;

public class MockEntityBuilder {
    private Point2D.Float position;
    private float health;
    private float damage;
    private float speed;
    private float size;

    public MockEntityBuilder setPosition(Point2D.Float position) {
        this.position = position;
        return this;
    }

    public MockEntityBuilder setHealth(float health) {
        this.health = health;
        return this;
    }

    public MockEntityBuilder setDamage(float damage) {
        this.damage = damage;
        return this;
    }

    public MockEntityBuilder setSpeed(float speed) {
        this.speed = speed;
        return this;
    }

    public MockEntityBuilder setSize(float size) {
        this.size = size;
        return this;
    }

    public MockEntity createMockEntity() {
        return new MockEntity(position, health, damage, speed, size);
    }
}