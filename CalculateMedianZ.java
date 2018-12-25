/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
import java.util.concurrent.Callable;

/**
 *
 * @author lenovo
 */
public class CalculateMedianZ implements Callable<Double> {

    private final double zScore;
    
    private double median;

    public CalculateMedianZ(double z) {
        this.zScore = z;
        
    }

    @Override
    public Double call() throws Exception {
        median = zScore / 2;
        return median;
    }
}
