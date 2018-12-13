/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.group;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author lenovo
 */
public class calculation extends Thread{
    ArrayList number;
    public calculation(ArrayList number){
        this.number=number;
    }
    Iterator it = number.iterator();
    double num=0;
    int count=0;
    double sum=0;
    double mean=0;
    double Variance=0;
    double i=0;
    double SD=0;

    @Override
    public void run() {
        while(it.hasNext()){
             num=(double) it.next();
             count=number.size();
             sum+=num;
             mean=sum/count;
             i=Math.pow((num-mean), 2);
             Variance+=i;
             SD=Math.sqrt(Variance/count);
        }
             System.out.println("Standard Deviation : " + SD );
    }
}
