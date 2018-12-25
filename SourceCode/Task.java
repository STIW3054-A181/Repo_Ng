
import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveAction;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
public class Task extends RecursiveAction {

    private final List<PDF> pdf;
    private final List<File> file;
    private int first;
    private int last;

    public Task(List<PDF> pdf, int first, int last, List<File> file) {
        this.pdf = pdf;
        this.first = first;
        this.last = last;
        this.file = file;
    }

    @Override
    protected void compute() {
        if (last - first < 10) {
            try {
                updatePDf();

            } catch (Exception ex) {
                Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            int middle = (first + last) / 2;
            //System.out.printf("Task: Pending tasks: %s\n", getQueuedTaskCount());;
            Task t1 = new Task(pdf, first, middle + 1, file);
            Task t2 = new Task(pdf, middle + 1, last, file);
            invokeAll(t1, t2);
        }
    }

    private void updatePDf() throws InterruptedException, ExecutionException {

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        double[] set;
        for (int i = first; i < last; i++) {

            PDF newpdf = pdf.get(i);
            Callable<double[]> value = new GetValue(file.get(i));
            Future<double[]> futureValue = executor.submit(value);
            set = futureValue.get();

            newpdf.setDir(file.get(i));
            newpdf.setwords(set[0]);
            newpdf.setcharacters(set[1]);
            newpdf.setmean(set[2]);
            newpdf.setsd(set[3]);
            newpdf.setzscore(set[4]);
            newpdf.setmedian(set[5]);

            pdf.set(i, newpdf);

        }
        executor.shutdown();

    }

}
