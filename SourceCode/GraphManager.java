
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class GraphManager implements GraphInterface, BoxInterface {
    
    private GraphInterface graph;
    private BoxInterface box;
    
     public void addGraph(GraphInterface graph){
        this.graph = graph;
    }
     
    public void addBox(BoxInterface box){
        this.box = box;
    } 
     

    @Override
    public double[][] xyValue(double size, double[] value) {
        return this.graph.xyValue(size, value);
    }

    @Override
    public XYChart createChart(double[][] xyvalue) {
        return this.graph.createChart(xyvalue);
    }

    @Override
    public SwingWrapper swing() {
        return this.graph.swing();
    }

    @Override
    public void updateChart(double[] yvalue) {
        this.graph.updateChart(yvalue);
    }

    @Override
    public void boxplotGraph() {
        this.box.boxplotGraph();
    }

    
    
}
