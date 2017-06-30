/**
 * 
 */
package dms.manger.data;

import java.text.SimpleDateFormat;

/**
 * @author bird
 *
 */
public class FormData {
	private long xAxisData;
	private long yAxisData;
	/**
	 * 
	 */
	public FormData() {
		// TODO Auto-generated constructor stub
		this.xAxisData = 0;
		this.yAxisData = 0;
	}

	public long getxAxisData() {
		return xAxisData;
	}

	public void setxAxisData(long xAxisData) {
		this.xAxisData = xAxisData;
	}

	public long getyAxisData() {
		return yAxisData;
	}

	public void setyAxisData(long yAxisData) {
		this.yAxisData = yAxisData;
	}
	
	public String getNormalXDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		return sdf.format(xAxisData);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
