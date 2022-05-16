package application;

import entities.Point;
import entities.equations.EquationManager;
import methods.Euler;
import methods.LagrangeInterpolation;
import utils.Asker;
import utils.IOutil;
import utils.Plotter;

public class Main {
    public static void main(String[] args) {
        IOutil io = new IOutil();
        EquationManager equationManager = new EquationManager();
        Asker asker = new Asker(io, equationManager);
        LagrangeInterpolation lagrange = new LagrangeInterpolation();
        Euler euler = new Euler(equationManager);
        Plotter plt = new Plotter();

        boolean running = true;
        int counter = 1;
        while (running){
            int mode = asker.askMode();
            switch (mode) {
                case (1): {
                    int eqid = asker.askEquation();
                    if(eqid == -1) continue;
                    Point initial = asker.askInitial();
                    double step = asker.askStep();
                    int steps = asker.askNumberOfPoints();

                    Point[] pointsDifferential = euler.solveDifferential(eqid, initial, step, steps);
                    Point[] funcEu = lagrange.getInterpolation(pointsDifferential);

                    Point[] pointsAnalytic = euler.analyticSolution(eqid, initial, step, steps);
                    Point[] funcAnalytic = lagrange.getInterpolation(pointsAnalytic);

                    plt.scatter(counter, pointsDifferential, funcEu, pointsAnalytic, funcAnalytic);
                    counter++;
                    break;
                }
                case (0): {
                    running = false;
                    System.exit(0);
                }
            }
        }
    }
}
