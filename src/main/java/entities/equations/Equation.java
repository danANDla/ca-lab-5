package entities.equations;

import entities.Point;

public interface Equation {
    public double getImage(Point v);
    public double getAnalytic(double v);
    public String toString();
}
