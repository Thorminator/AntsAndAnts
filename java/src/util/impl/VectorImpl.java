package util.impl;

import util.Vector;

public class VectorImpl implements Vector {

    private double x, y;

    public VectorImpl(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Vector add(Vector v) {
        return new VectorImpl(x + v.getX(), y + v.getY());
    }

    @Override
    public Vector sub(Vector v) {
        return new VectorImpl(x - v.getX(), y - v.getY());
    }

    @Override
    public Vector scalarMult(double scalar) {
        return new VectorImpl(x * scalar, y * scalar);
    }

    @Override
    public Vector normalize() {
        double length = calculateLength();
        return new VectorImpl(x / length, y / length);
    }

    @Override
    public double calculateLength() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }
}
