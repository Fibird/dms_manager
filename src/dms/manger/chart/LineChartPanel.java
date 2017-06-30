/**
 * 
 */
package dms.manger.chart;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;

import org.jfree.chart.*;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;

import dms.manger.data.FormData;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.*;

/**
 * @author bird
 *
 */
public class LineChartPanel {
	private ChartPanel frame;
	private String title;
	private String type;
	private DefaultCategoryDataset dataset;
	
	/**
	 * 
	 */
	public LineChartPanel(String title, String type, Dimension size) {
		// TODO Auto-generated constructor stub
		//frame = new ChartPanel(initChart(), true);
		this.title = title;
		this.type = type;	
		dataset = new DefaultCategoryDataset();
		
        final JFreeChart chart = createChart();
        frame = new ChartPanel(chart, true);
        //frame.setPreferredSize(size);
        frame.setDisplayToolTips(true);
        frame.setAutoscrolls(true);
	}
	
	public void updateDataSet(ArrayList<FormData> formData, String s) {	
		dataset.clear();
		for (FormData f : formData) {
			dataset.addValue(f.getyAxisData(), s, f.getNormalXDate());
		}
	}
	
	private JFreeChart createChart() {
		final JFreeChart jfc = ChartFactory.createLineChart(title, type, "Duration/h", dataset, PlotOrientation.VERTICAL, true, true, true);
		return jfc;
	}
	
	public ChartPanel getChartPanel() { 
		return frame;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame jf = new JFrame();
		LineChartPanel jcp = new LineChartPanel("Month", "month", new Dimension(300, 300));
		jf.add(jcp.getChartPanel());
		jf.setVisible(true);
	}

}
