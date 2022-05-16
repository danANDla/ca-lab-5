package methods;

import entities.Point;
import entities.equations.Equation;
import entities.equations.EquationManager;

public class Euler {
    private EquationManager equationManager;

    public Euler(EquationManager equationManager) {
        this.equationManager = equationManager;
    }

    public Point[] solveDifferential(int eqid, Point initial, double h, int steps){
        Equation eq = equationManager.getEq(eqid);
        double prevY = initial.getY();
        double currX = initial.getX();

        Point[] ans = new Point[steps+1];
        ans[0] = new Point(currX, prevY);
        for(int i = 1;  i <= steps; ++i){
            prevY = prevY + h * eq.getImage(ans[i-1]);
            currX += h;
            ans[i] = new Point(currX, prevY);
        }
        return ans;
    }

    public Point[] analyticSolution(int eqid, Point initial, double h, int steps){
        Equation eq = equationManager.getEq(eqid);
        double prevY = initial.getY();
        double currX = initial.getX();

        Point[] ans = new Point[steps+1];
        ans[0] = new Point(currX, prevY);
        for(int i = 1;  i <= steps; ++i){
            currX += h;
            prevY = eq.getAnalytic(currX);
            ans[i] = new Point(currX, prevY);
        }
        return ans;
    }

}
