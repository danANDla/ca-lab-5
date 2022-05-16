package methods;

import entities.Point;

public class LagrangeInterpolation {

    private double getPolynomial(double[] xVals, int coeffId, double x) {
        double res = 1;
        for (int i = 0; i < xVals.length; ++i) {
            if (i == coeffId) continue;
            res *= (x - xVals[i]);
            res /= (xVals[coeffId] - xVals[i]);
        }
        return res;
    }

    private double[] getAllPolynomials(double[] xVals, double x) {
        double[] res = new double[xVals.length];
        for (int i = 0; i < xVals.length; ++i) {
            res[i] = getPolynomial(xVals, i, x);
        }
        return res;
    }

    private double getImage(double[] xVals, double[] yVals, double x) {
        double[] polynomials = getAllPolynomials(xVals, x);
        double res = 0;
        for (int i = 0; i < xVals.length; ++i) {
            res += polynomials[i] * yVals[i];
        }
        return res;
    }

    private double[] getBorders(double[] xVals) {
        if (xVals.length == 0) return null;
        double min = xVals[0];
        double max = xVals[0];

        for (double i : xVals) {
            if (i < min) min = i;
            if (i > max) max = i;
        }
        return new double[]{min, max};
    }

    public Point[] getInterpolation(Point[] points) {
        double[] xVals = new double[points.length];
        double[] yVals = new double[points.length];
        for (int i = 0; i < points.length; ++i) {
            xVals[i] = points[i].getX();
            yVals[i] = points[i].getY();
        }

        double[] borders = getBorders(xVals);
        if (borders == null) return null;

        int iterations = 0;
        if (borders[1] - borders[0] > 1) {
            iterations = (int) (100 * Math.round(borders[1] - borders[0]));
        } else {
            iterations = 200;
        }

        double step = (borders[1] - borders[0]) / iterations;
        double point = borders[0];
        Point[] res = new Point[iterations];
        for (int i = 0; i < iterations; ++i) {
            res[i] = new Point();
            res[i].setY(getImage(xVals, yVals, point));
            res[i].setX(point);
            point += step;
        }
        return res;
    }
}
