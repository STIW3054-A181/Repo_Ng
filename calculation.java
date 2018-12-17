/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.issues19;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 *
 * @author lenovo
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException{
        int N=211;
        ExecutorService executor=Executors.newCachedThreadPool();
        Test test=new Test(N);
        Future<Integer> result=executor.submit(test);
        System.out.println("Value of N:"+N);
        System.out.println("Total :"+result.get());
    }
}
