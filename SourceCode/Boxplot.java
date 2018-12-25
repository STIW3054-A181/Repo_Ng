/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.BoxAndWhiskerToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

public class Boxplot implements BoxInterface {
    private ArrayList<Double> zScoreArrayList;

    Boxplot(ArrayList<Double> zScoreArrayList) {
        this.zScoreArrayList = zScoreArrayList;
    }

    private static final String ROW_KEY = "Boxplot";

    @Override
    public void boxplotGraph() {
        JFrame f = new JFrame("BoxPlot");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DefaultBoxAndWhiskerCategoryDataset data = new DefaultBoxAndWhiskerCategoryDataset();
        data.add(zScoreArrayList, ROW_KEY, "Z-score");
        final CategoryAxis xAxis = new CategoryAxis("X-Axis");
        final NumberAxis yAxis = new NumberAxis("Y-Axis");
        final BoxAndWhiskerRenderer renderer = new BoxAndWhiskerRenderer();
        renderer.setFillBox(true);
        renderer.setSeriesPaint(0, Color.LIGHT_GRAY);
        renderer.setSeriesOutlinePaint(0, Color.BLUE);
//        renderer.setUseOutlinePaintForWhiskers(true);
//        renderer.setDefaultToolTipGenerator(new BoxAndWhiskerToolTipGenerator());

        Font legendFont = new Font("SansSerif", Font.PLAIN, 16);
        renderer.setLegendTextFont(0, legendFont);
        renderer.setMedianVisible(true);
        renderer.setMeanVisible(false);
        final CategoryPlot categoryPlot = new CategoryPlot(data, xAxis, yAxis, renderer);
        JFreeChart jFreeChart = new JFreeChart("Drawplot", JFreeChart.DEFAULT_TITLE_FONT, categoryPlot, true);
        f.add(new ChartPanel(jFreeChart) {
            public Dimension getPreferredSize() {
                return new Dimension(540,900);
            }
        });
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

}