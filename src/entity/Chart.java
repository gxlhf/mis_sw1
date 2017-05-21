package entity;

import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.function.Function2D;
import org.jfree.data.function.NormalDistributionFunction2D;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;

public class Chart  extends ApplicationFrame{
	 public Chart(final String title) {

	        super(title);
	 }
	 public JFreeChart paint(double miu,double sigma) throws IOException {
	        Function2D normal = new NormalDistributionFunction2D(miu, sigma);
	        XYDataset dataset = DatasetUtilities.sampleFunction2D(normal, -10.0, 2*miu+10.0, 100, "Normal");
	        final JFreeChart chart = ChartFactory.createXYLineChart(
	            "XY Series Demo",
	            "X", 
	            "Y", 
	            dataset,
	            PlotOrientation.VERTICAL,
	            true,
	            true,
	            false
	        );

	   //     final ChartPanel chartPanel = new ChartPanel(chart);
	   //     chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
	  //      setContentPane(chartPanel);
            return chart;
	 }
	  
}
