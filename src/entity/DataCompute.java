package entity;

import java.util.ArrayList;

public class DataCompute {
	public double getFangcha(ArrayList<Double> data){	  
		
	      double ave = 0;
	      for (int i = 0; i < data.size(); i++)
	          ave += data.get(i);
	      ave /= data.size();
	      
	      double sum = 0;
	      for(int i = 0;i<data.size();i++)
	          sum += (data.get(i) - ave)  * (data.get(i) - ave) ;
	      sum /= data.size();
	      
	      return Math.sqrt(sum);
		
	  }
	  public double getAve(ArrayList<Double> data){
		  double ave = 0.0;
		  for (int i = 0; i < data.size(); i++)
	          ave += data.get(i);
	      
		  ave /= data.size();
	      return ave;
		  
	  }
}
