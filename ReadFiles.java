package com.mycompany.groupassignment;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

class WordCountParallel {

  public Map<String, Integer> getWordCountForFiles(String path) throws InterruptedException, ExecutionException {
    Map<String, Integer> resultMap = new HashMap<>();
    List<File> files = getFilesList(path);
    
    //Create a executor service and submit word count tasks to it.
    //ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
   
    //executor.shutdown();
    return resultMap;
  }



  private List<File> getFilesList(String rootPath) {
      int i=0;
      List<File> results = new ArrayList<>();
    File[] files = new File(rootPath).listFiles();
      for (File file : files != null ? files : new File[0]) {
        if (file.isFile()) {
            System.out.println(files[i++].toString());
            results.add(file);
        }
      }
    return results;
  }



    public static void main(String[] args) throws InterruptedException {
        WordCountParallel word = new WordCountParallel();
      try {
          word.getWordCountForFiles("C:\\Users\\User\\Desktop\\Hubungan etnik");
      } catch (ExecutionException ex) {
          Logger.getLogger(WordCountParallel.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
}




