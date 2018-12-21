package zscore;

import java.util.concurrent.Callable;

public class Z_score implements Callable<Integer>{
	private final double sd;
	private final double mean;
	private final int totcharacter;
		
	public  Z_score(double sd, double mean,int totcharacter){
        this.sd=sd;
        this.mean=mean;
        this.totcharacter = totcharacter;
    }
    @Override
    public Integer call() throws Exception {
		super.getClass();
        double zscore=totcharacter-mean/sd;

        return (int)zscore;
    }
           
   
}
