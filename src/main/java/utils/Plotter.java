package utils;

import entities.Point;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.lines.SeriesLines;
import org.knowm.xchart.style.markers.SeriesMarkers;

import javax.swing.*;

public class Plotter {

    public Plotter() {
    }

    private void addGraph(XYChart chart, String name, String seriesname, Point[] initial, Point[] interpolated){
        double[] xValues = new double[interpolated.length];
        double[] yValues = new double[interpolated.length];

        for (int i = 0; i < interpolated.length; ++i) {
            xValues[i] = interpolated[i].getX();
            yValues[i] = interpolated[i].getY();
        }
        XYSeries seris = chart.addSeries(name, xValues, yValues);
        seris.setMarker(SeriesMarkers.NONE);

        double[] xInitValues = new double[initial.length];
        double[] yInitValues = new double[initial.length];
        for (int i = 0; i < initial.length; ++i) {
            xInitValues[i] = initial[i].getX();
            yInitValues[i] = initial[i].getY();
        }
        XYSeries initSeris = chart.addSeries(seriesname, xInitValues, yInitValues);
        initSeris.setLineStyle(SeriesLines.NONE);
    }

    public void scatter(int counter, Point[] initialEuler, Point[] interpolatedEuler, Point[] initialAnalytic, Point[] interpolatedAnalytic) {
        int width = 640;
        int height = 480;
        XYChart chart = new XYChartBuilder().width(width).height(height).title("Graph").xAxisTitle("X").yAxisTitle("Y").build();

        addGraph(chart, "Euler", "intermediate points", initialEuler, interpolatedEuler);
        addGraph(chart, "Analytic", "intermediate points (analytic)", initialAnalytic, interpolatedAnalytic);

        show(chart);
    }

    private void show(XYChart chart) {
        JFrame frame = new SwingWrapper(chart).displayChart();
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }
}
