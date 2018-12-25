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
public class CalculateMean implements Callable<Double> {

    private final double word;
    private final double character;
    private double mean;

    public CalculateMean(int word, int character) {
        this.word = (double)word;
        this.character = (double)character;

    }

    @Override
    public Double call() throws Exception {
        mean = character / word;
        return mean;
    }
}
