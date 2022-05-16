package entities.equations;

import java.util.ArrayList;

public class EquationManager {
    ArrayList<Equation> allEquations;

    public EquationManager() {
        allEquations = new ArrayList<>();
        allEquations.add(new eq1());
        allEquations.add(new eq2());
        allEquations.add(new eq3());
    }

    public ArrayList<Equation> getAllEquations() {
        return allEquations;
    }

    public Equation getEq(int v) {
        return allEquations.get(v);
    }
}
