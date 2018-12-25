/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author User
 */
public class GetValue implements Callable <double[]> {
    File file ;
    int wordCount, charCount;
    double meanCount, sdCount, zCount,median;
    double[] value = new double[6];
    
    
    GetValue(File file){
        this.file = file;
    }


    @Override
    public double[] call() throws Exception {
        
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        //Create MyCallable instance
        Callable<Integer> word = new CallableCountWords(file);
        Callable<Integer> character = new CountCharacter(file);

        //submit Callable tasks to be executed by thread pool
        Future<Integer> futureWord = executor.submit(word);
        Future<Integer> futureChar = executor.submit(character);
        wordCount = futureWord.get();
        value[0] = (double)wordCount;
        charCount = futureChar.get();
        value[1] = (double)charCount;

        Callable<Double> mean = new CalculateMean(wordCount, charCount);
        Future<Double> futureMean = executor.submit(mean);
        meanCount = futureMean.get();
        value[2] = meanCount;

        Callable<Double> sd = new CalculateSD(wordCount, charCount, meanCount);
        Future<Double> futureSD = executor.submit(sd);
        sdCount = futureSD.get();
        value[3] = sdCount;

        Callable<Double> zScore = new CalculateZscore(charCount, meanCount, sdCount);
        Future<Double> futureZ = executor.submit(zScore);
        zCount = futureZ.get();
        value[4] = zCount;
        
        Callable<Double> medianZ = new CalculateMedianZ(zCount);
        Future<Double> futureMedianZ = executor.submit(medianZ);
        median = futureMedianZ.get();
        value[5] = median;
        
        
        //shut down the executor service now
        executor.shutdown();
        
        return value;
    }

}
