/**
 * 
 */
package dms.manger.chart;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.jfree.chart.*;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.PieLabelLinkStyle;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.ui.RectangleInsets;
import dms.manger.data.FormData;
import org.jfree.data.category.DefaultCategoryDataset;


/**
 * @author bird
 *
 */
public class LineChartPanel {
	private ChartPanel frame;
	private String title;
	private String type;
	//CategoryPlot cp;
	JFreeChart chart;
	private DefaultCategoryDataset dataset;
	private String [] months = {"Jan.", "Feb.", "Mar.", "Apr.", "May", 
			"Jun.", "Jul.", "Aug.", "Sept.", "Oct.", "Nov.", "Dec."};
	
	/**
	 * 
	 */
	public LineChartPanel(String title, String type, Dimension size) {
		// TODO Auto-generated constructor stub
		//frame = new ChartPanel(initChart(), true);
		this.title = title;
		this.type = type;
		dataset = new DefaultCategoryDataset();
        chart = createChart();
        frame = new ChartPanel(chart, true);
        //frame.setPreferredSize(size);
        frame.setDisplayToolTips(true);
        frame.setAutoscrolls(true);
	}
	
	public void updateDataSet(ArrayList<FormData> formData, String s) {	
		dataset.clear();
		if (this.type.equals("day")) {
			int m = Integer.parseInt(s);
			chart.setTitle(months[m - 1] + " Month Form");
			for (FormData f : formData) {
				dataset.addValue(f.getyAxisData(), months[m - 1], f.getNormalXDate());
			}
		} else {
			chart.setTitle(s + " Form");
			for (FormData f : formData) {
				dataset.addValue(f.getyAxisData(), s, months[(int)f.getxAxisData() - 1]);
			}
		}
		
	}
	
	private JFreeChart createChart() {
		setChartTheme();
		final JFreeChart jfc = ChartFactory.createLineChart(title, type, "Duration/h", dataset, PlotOrientation.VERTICAL, false, false, false);
		jfc.setBorderVisible(true);
		jfc.getCategoryPlot();
		return jfc;
	}
	private void setChartTheme() {
		// 设置中文主题样式 解决乱码
		StandardChartTheme chartTheme = new StandardChartTheme("EN");
		// 设置标题字体
		Color[] CHART_COLORS = {
				new Color(31,129,188), new Color(92,92,97), new Color(144,237,125), new Color(255,188,117),
				new Color(153,158,255), new Color(255,117,153), new Color(253,236,109), new Color(128,133,232),
				new Color(158,90,102),new Color(255, 204, 102) };
		chartTheme.setTitlePaint(new Color(51, 51, 51));
		chartTheme.setSubtitlePaint(new Color(85, 85, 85));
		
		chartTheme.setLegendBackgroundPaint(Color.WHITE);// 设置标注
		chartTheme.setLegendItemPaint(Color.BLACK);//
		chartTheme.setChartBackgroundPaint(Color.WHITE);
		// 绘制颜色绘制颜色.轮廓供应商
		// paintSequence,outlinePaintSequence,strokeSequence,outlineStrokeSequence,shapeSequence

		Paint[] OUTLINE_PAINT_SEQUENCE = new Paint[] { Color.WHITE };
		// 绘制器颜色源
		DefaultDrawingSupplier drawingSupplier = new DefaultDrawingSupplier(CHART_COLORS, CHART_COLORS, OUTLINE_PAINT_SEQUENCE,
				DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE, DefaultDrawingSupplier.DEFAULT_OUTLINE_STROKE_SEQUENCE,
				DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE);
		chartTheme.setDrawingSupplier(drawingSupplier);

		chartTheme.setPlotBackgroundPaint(Color.WHITE);// 绘制区域
		chartTheme.setPlotOutlinePaint(Color.WHITE);// 绘制区域外边框
		chartTheme.setLabelLinkPaint(new Color(8, 55, 114));// 链接标签颜色
		chartTheme.setLabelLinkStyle(PieLabelLinkStyle.CUBIC_CURVE);

		chartTheme.setAxisOffset(new RectangleInsets(5, 12, 5, 12));
		chartTheme.setDomainGridlinePaint(new Color(192, 208, 224));// X坐标轴垂直网格颜色
		chartTheme.setRangeGridlinePaint(new Color(192, 192, 192));// Y坐标轴水平网格颜色

		chartTheme.setBaselinePaint(Color.WHITE);
		chartTheme.setCrosshairPaint(Color.BLUE);// 不确定含义
		chartTheme.setAxisLabelPaint(new Color(51, 51, 51));// 坐标轴标题文字颜色
		chartTheme.setTickLabelPaint(new Color(67, 67, 72));// 刻度数字
		chartTheme.setBarPainter(new StandardBarPainter());// 设置柱状图渲染
		chartTheme.setXYBarPainter(new StandardXYBarPainter());// XYBar 渲染

		chartTheme.setItemLabelPaint(Color.black);
		chartTheme.setThermometerPaint(Color.white);// 温度计

		ChartFactory.setChartTheme(chartTheme);
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
