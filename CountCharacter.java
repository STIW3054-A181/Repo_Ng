
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;

public class CountCharacter implements Callable<Integer> {

    private final File input;

    public CountCharacter(File input) {
        this.input = input;
    }

    public Integer call() throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader(input));
        String line = bf.readLine();
        int totalch = 0;
        

        while (line != null) {
            String[] words = line.split(" ");
            for (String word : words) {
                
                //Updating the charCount
                totalch += word.length();

            }
            line = bf.readLine();
        }

        bf.close();
        return totalch;
    }

}
