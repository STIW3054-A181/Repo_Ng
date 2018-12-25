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
public class CalculateSD implements Callable<Double> {

    private final int word;
    private final int character;
    private final double mean;
    private double SD;

    public CalculateSD(int word, int character, double mean) {
        this.word = word;
        this.character = character;
        this.mean = mean;
    }

    @Override
    public Double call() throws Exception {
        SD = Math.sqrt(Math.pow((character - mean), 2) / word);
        return SD;
    }
}
