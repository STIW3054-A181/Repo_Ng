
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        List<File> listFile = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        Callable<List<File>> list = new FileManagment();
        Future<List<File>> futureList = executor.submit(list);
        listFile = futureList.get();
        executor.shutdown();

        //create pdf
        PdfListGenerator generator = new PdfListGenerator();
        List<PDF> pdf = generator.generate(listFile.size());
        //System.out.println(pdf.size());

        Task task = new Task(pdf, 0, pdf.size(), listFile);
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        pool.invoke(task);

        //check if task has completed normally
        if (task.isCompletedNormally()) {
            System.out.printf("Main: The Process has Completed normally.\n");

        }
        pool.shutdown();

        //Create Graph
        GraphManager graph = new GraphManager();
        graph.addGraph(new Normalgraph());

        double sdValueSet[] = new double[pdf.size()];
        double xAxis = 1.0;

        ArrayList<Double> zScoreArrayList = new ArrayList<Double>();;

        //----> Create xChart
        graph.createChart(graph.xyValue(xAxis, sdValueSet));
        graph.swing();

        for (int i = 0; i < pdf.size(); i++) {
            System.out.println(pdf.get(i).getName());
            System.out.println(pdf.get(i).getDir());
            System.out.println(pdf.get(i).getwords());
            System.out.println(pdf.get(i).getcharacters());
            System.out.println(pdf.get(i).getmean());
            System.out.println(pdf.get(i).getsd());
            System.out.println(pdf.get(i).getzscore());
            System.out.println("");
            System.out.println("");

            sdValueSet[i] = (pdf.get(i).getsd()) / 100;
            zScoreArrayList.add(pdf.get(i).getzscore()/10000);
            graph.updateChart(sdValueSet);

            //update graph every one second
            Thread.sleep(1000);
        }
        
        //sort arraylist
        Collections.sort(zScoreArrayList);
        
        //create boxplot
        GraphManager box = new GraphManager();
        box.addBox(new Boxplot(zScoreArrayList));
        box.boxplotGraph();

    }
}
