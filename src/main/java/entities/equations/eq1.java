package entities.equations;

import entities.Point;

public class eq1 implements Equation {
    @Override
    public double getImage(Point v) {
        return Math.pow(v.getX(), 2) - 2 * v.getY();
    }

    @Override
    public double getAnalytic(Point initial, double v) {
        double c = (initial.getY() - (0.5 * Math.pow(initial.getX(), 2) - 0.5 * initial.getX() + 0.25)) / (Math.exp(-2 * initial.getX()));
        return c * Math.exp(-2 * v) + 0.5 * Math.pow(v, 2) - 0.5 * v + 0.25;
    }

    @Override
    public String toString() {
        return "y' = x^2 - 2*y";
    }
}
