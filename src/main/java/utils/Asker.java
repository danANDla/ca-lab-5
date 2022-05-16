package utils;

import entities.Point;
import entities.equations.EquationManager;

import java.util.Locale;

public class Asker {
    private IOutil io;
    private EquationManager equationManager;

    public Asker(IOutil io, EquationManager equationManager) {
        this.io = io;
        this.equationManager = equationManager;
    }

    private String getNonEmpty() {
        String str;
        io.printArrow();
        str = io.readLine().trim();
        while (str.equals("")) {
            str = io.readLine().trim();
        }
        return str;
    }

    public int askEquation() {
        int sysid = 0;
        boolean valid = false;
        io.printText("Выберите уравнение:");
        for (int i = 0; i < equationManager.getAllEquations().size(); ++i) {
            System.out.printf("(%d) ", i + 1);
            io.printText(equationManager.getEq(i).toString());
        }
        io.printText("(0) отмена");
        while (!valid) {
            try {
                sysid = Integer.parseInt(getNonEmpty()) - 1;
                valid = true;
                if (sysid < -1 || sysid >= equationManager.getAllEquations().size()) {
                    io.printError("Такой опции нет");
                    valid = false;
                }
            } catch (NumberFormatException e) {
                io.printError("Неправильный формат целого числа");
            }
        }
        return sysid;
    }

    public int askMode() {
        int mode = 0;
        boolean valid = false;
        io.printDivider();
        io.printText(
                "(1) решить дифференциальное уравнение\n" +
                "(0) выход из приложения\n"
        );
        while (!valid) {
            try {
                mode = Integer.parseInt(getNonEmpty());
                valid = true;
                if (mode < 0 || mode > 1) {
                    io.printError("Такой опции нет");
                    valid = false;
                }
            } catch (NumberFormatException e) {
                io.printError("Неправильный формат целого числа");
            }
        }
        return mode;
    }

    public int askNumberOfPoints() {
        io.printText("введите количество точек");
        int k = -1;
        boolean valid = false;
        while (!valid) {
            try {
                k = Integer.parseInt(getNonEmpty());
                valid = true;
            } catch (NumberFormatException e) {
                io.printError("Неправильный формат целого числа");
            }
        }
        return k;
    }

    public double askStep() {
        io.printText("введите шаг генерации точек");
        double step = 0;
        boolean valid = false;
        while (!valid) {
            try {
                step = Double.parseDouble(getNonEmpty());
                valid = true;
            } catch (NumberFormatException e) {
                io.printError("Неправильный формат целого числа");
            }
        }
        return step;
    }

    public Point askInitial() {
        io.printText("введите координаты начальной точки");

        double x = 0, y = 0;
        boolean validrow = false;
        while (!validrow) {
            String row = getNonEmpty();
            String[] numbers = row.trim().toLowerCase(Locale.ROOT).split("\\s+");
            if (numbers.length != 2) {
                io.printError("Неверное количество введенных чисел");
                continue;
            }
            boolean badNum = false;
            try {
                x = (Double.parseDouble(numbers[0]));
                y = (Double.parseDouble(numbers[1]));
            } catch (NumberFormatException e) {
                io.printError("Неправильный формат вещественного числа");
                badNum = true;
            }
            validrow = !badNum;
        }
        return new Point(x, y);
    }

}
