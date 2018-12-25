
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class PdfListGenerator {
    
    public List<PDF> generate (int size) throws InterruptedException, ExecutionException{
        List<PDF> ret = new ArrayList<PDF>();
        for(int i=0; i<size; i++){
            PDF pdf = new PDF();
            pdf.setName("Pdf "+ ++i);
            i--;
            ret.add(pdf);
        }
            
            return ret;
    }
    
}
