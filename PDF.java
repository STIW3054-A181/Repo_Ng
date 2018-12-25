
import java.io.File;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class PDF {
   File dir;
   String name;
   double words, characters, mean, sd, zscore, median;
   
   public String getName(){
       return name;
   }
   
   public void setName(String name){
       this.name = name;
   }
   
   public File getDir(){
       return dir;
   }
   
   public void setDir(File dir){
       this.dir = dir;
   }
   
   public double getwords(){
       return words;
   }
   
   public void setwords(double words){
       this.words = words;
   }
   
   public double getcharacters(){
       return characters;
   }
   
    public void setcharacters(double characters){
       this.characters = characters;
   }
   
   public double getmean(){
       return mean;
   }
   
    public void setmean(double mean){
       this.mean = mean;
   }
   
   public double getsd(){
       return sd;
   }
   
    public void setsd(double sd){
       this.sd = sd;
   }
   
   public double getzscore(){
       return zscore;
   }
   
    public void setzscore(double zscore){
       this.zscore = zscore;
   }
   
   public double getmedian(){
       return median;
   }
   
    public void setmedian(double median){
       this.median = median;
   }
}
