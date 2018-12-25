
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
public class FileManagment implements Callable<List<File>>, FileInterface {

     List<File> results = new ArrayList<>();
     
    /**
     *
     * @return
     */
    @Override
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
        //ret = fileChooser.getSelectedFile().getPath();
        ret = fileChooser.getCurrentDirectory().toString();

        return ret;
    }

    /**
     *
     * @param rootPath
     * @return
     */
    @Override
    public List<File> getFilesList(String rootPath) {
        int i = 0;
       
        File[] files = new File(rootPath).listFiles();
        for (File file : files != null ? files : new File[0]) {
            if (file.isFile()) {
                String name = files[i].toString();
                name = name.substring(name.length() - 3);
               
                if(name.equals("pdf")){
                    //System.out.println(files[i].toString());
                    results.add(files[i]);
                }
                    i++;
            }
        }
        return results;
    }


    @Override
    public List<File> call() throws Exception {
        return getFilesList(choosefile());
    }

}
