package entities.equations;

import entities.Point;

public interface Equation {
    public double getImage(Point v);
    public double getAnalytic(Point initial, double v);
}
