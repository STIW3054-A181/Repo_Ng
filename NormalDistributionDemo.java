/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.normal_distribution_graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.DeviationRenderer;
import org.jfree.data.function.Function2D;
import org.jfree.data.function.NormalDistributionFunction2D;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author Wkeattan
 */
public class NormalDistributionDemo extends ApplicationFrame {

    /**
     * A demonstration application showing an XY series containing a null value.
     *
     * @param title the frame title.
     */
    public NormalDistributionDemo(final String title) {
        super(title);
    }

    private void createChart(double mean, double sd) {
        Function2D normal = new NormalDistributionFunction2D(mean, sd);//mean, standard deviation 

        XYDataset dataset = DatasetUtilities.sampleFunction2D(normal, ((sd * -3) + mean), ((sd * 3) + mean), 100, "");//((sd*-3)+mean)<--mean-->((sd*3)+mean)
        final JFreeChart chart = ChartFactory.createXYLineChart(
                "Standard Normal Distribution Graph",
                "Mean",
                "",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        final ChartPanel chartPanel = new ChartPanel(chart);
        setContentPane(chartPanel);
    }
    /* 
    **Returns the height of the normal distribution at the specified z-score
 
    double getNormalProbabilityAtZ(double z) {
        return Math.exp(-Math.pow(z, 2) / 2) / Math.sqrt(2 * Math.PI);
    }
    
    **Returns the area under the normal curve between the z-scores z1 and z2
  
    double getAreaUnderNormalCurve(double z1, double z2) {
        double area = 0.0;
        final int rectangles = 100000; // more rectangles = more precise, less rectangles = quicker execution
        final double width = (z2 - z1) / rectangles;
        for(int i = 0; i < rectangles; i++)
        area += width * getNormalProbabilityAtZ(width * i + z1);
        return area;
    }*/
    public static void main(final String[] args) {
        double mean = 4.55;
        double sd = 187;
        final NormalDistributionDemo demo = new NormalDistributionDemo("Standard Normal Distribution Graph");
        demo.createChart(mean, sd);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
