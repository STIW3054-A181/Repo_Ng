import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class App {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Create the list that will contain all the tasks

        File file=new File("C:/Users/Dell/Desktop/example.txt");
        List<FutureTask<Integer>> futuresWord = new ArrayList<>();

        
        // Create a task for the current String
        FutureTask<Integer> futureword = new FutureTask<>(new CallableCountWords(file) {
         });

            // Add the task to the list of tasks
            futuresWord.add(futureword);

            // Provide the task to a new Thread and start it
            new Thread(futureword).start();

        int totalword = 0;

        // Iterate over all the tasks
        for (FutureTask<Integer> task : futuresWord) {
        // Wait as long as it is needed to get the result of the current task
            totalword += task.get();
         }
        // Print the total of vowels found
         System.out.printf("Number of Word: %d%n", totalword);


    }
}
