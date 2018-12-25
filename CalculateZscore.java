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

public class CalculateZscore implements Callable<Double>{
	private final double sd;
	private final double mean;
	private final int totcharacter;
		
	public  CalculateZscore(int totcharacter,double mean, double sd){
        this.sd=sd;
        this.mean=mean;
        this.totcharacter = totcharacter;
    }
    
    
        @Override
    public Double call() throws Exception {
        double zscore=totcharacter-mean/sd;
        return zscore;
    }
           
   
}