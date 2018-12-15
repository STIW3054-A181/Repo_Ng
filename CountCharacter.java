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
        char[] ch = line.toCharArray();
        int totalch=0;

        for ( int i=0; i<ch.length;++i){
            int counter=0;
            for (int k=0; k<ch.length; ++k){
                if(k<i && ch[i] ==ch[k]){
                    break;
                }
                if(ch[k]==ch[i]){
                    counter++;
                }
            }
            totalch=counter+totalch;
        }

        bf.close();
        return totalch;
    }

}
