/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author User
 */
public class RealTime {

    public String choosefile() {
        //Choose File to Read
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("PDF file");
        //only choose excel file format
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF", "pdf");
        fileChooser.setFileFilter(filter);
        fileChooser.showOpenDialog(null);
        //File selectedfile = fileChooser.getCurrentDirectory();
        String ret = new String();
        ret = fileChooser.getSelectedFile().getPath();
        return ret;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        //choose file
        RealTime a = new RealTime();
        File file = new File(a.choosefile());

        //Get ExecutorService from Executors utility class, thread pool size is 10
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        //Create MyCallable instance
        Callable<Integer> word = new CallableCountWords(file);
        Callable<Integer> character = new CountCharacter(file);
        
        //submit Callable tasks to be executed by thread pool
        Future<Integer> futureWord = executor.submit(word);
        Future<Integer> futureChar = executor.submit(character);

        System.out.println(futureWord.get());
        System.out.println(futureChar.get());
        //shut down the executor service now
        executor.shutdown();

    }

}
