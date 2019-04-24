import java.io.BufferedReader;

import java.io.File;

import java.io.FileReader;

import java.io.IOException;

import java.util.concurrent.Callable;







public class CallableCountWords implements Callable<Integer> {



    private final File input;







    public CallableCountWords(File input) {

        this.input = input;

    }







    public Integer call() throws IOException {



        BufferedReader bf = new BufferedReader(new FileReader(input));

        String line = bf.readLine();

        int wordCounter = 0;



        while (line != null) {

            

            String[] words = line.split(" ");

            wordCounter += words.length;

            

            line = bf.readLine();

        }

        

        bf.close();

        return wordCounter;

    }



}
