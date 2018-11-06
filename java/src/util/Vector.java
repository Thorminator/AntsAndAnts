package util;

public interface Vector
{
  Vector add(Vector v);
  
  Vector sub(Vector v);
  
  Vector mult(Vector v);
  
  Vector normalize();
  
  double calculateLength();
}
