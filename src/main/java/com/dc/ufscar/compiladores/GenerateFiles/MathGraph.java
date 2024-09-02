package com.dc.ufscar.compiladores.GenerateFiles;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
//import java.awt.image.BufferedImage;
public class MathGraph {
    public static JFreeChart createGraph(double a, double b) {
        XYSeries series = new XYSeries("Variacao do peso");
        for (int x = -10; x <= 10; x++) {
            double y = a * x + b;
            series.add(x, y);
        }

        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
            "Graph of f(x) = " + a + "x + " + b,
            "tempo",
            "peso",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );
        return chart;
    }
}
