package entities.equations;

import entities.Point;

public class eq2 implements Equation {
    @Override
    public double getImage(Point v) {
        return -2 * v.getY() * v.getX();
    }

    @Override
    public double getAnalytic(Point initial, double v) {
        double c = initial.getY() / Math.exp(-Math.pow(initial.getX(), 2));
        return c * Math.exp(-Math.pow(v,2));
    }

    @Override
    public String toString() {
        return "y' = -2xy";
    }
}
