
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
public interface GraphInterface {
    
    double [][] xyValue(double size, double [] value);
    XYChart createChart(double [][] xyvalue);
    SwingWrapper swing();
    void updateChart(double [] yvalue);
    
    
    
}
