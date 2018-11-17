package util;

public interface Vector {
    Vector add(Vector v);

    Vector sub(Vector v);

    Vector scalarMult(double scalar);

    Vector normalize();

    double calculateLength();

    double getX();

    double getY();
}
