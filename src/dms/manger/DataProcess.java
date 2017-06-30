/**
 * 
 */
package dms.manger;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import dms.manger.data.*;

/**
 * @author bird
 *
 */
public class DataProcess {
	DateDataSet dateDataSet;

	/**
	 * 
	 */
	public DataProcess() {
		// TODO Auto-generated constructor stub
		dateDataSet = new DateDataSet();
	}
	
	public DateDataSet getDateDataSet() {
		return dateDataSet;
	}
	public void updateData() {
		File f = new File("cache/logs.txt");
		BufferedReader input = null;
		try {
			// output result to file
			String cl = new String();
			input = new BufferedReader(new FileReader(f));
			while ((cl = input.readLine()) != null) {
				String[] splited = cl.split("\\s+");
				dateDataSet.insert(processData(splited));
			}
			// input.
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public UserInfo processData(String[] s) {
		String usr = s[0];
		long logDate = Long.parseLong(s[3]);
		long dur = Long.parseLong(s[4]);
		String ip = s[5];
		int pid = Integer.parseInt(s[1]);
		return new UserInfo(usr, timeRoundToDay(logDate), pid, ip, dur);
	}
	
	public UserSet getUserSet(long key) {
		return dateDataSet.getUserSet(key);
	}
	private long timeRoundToDay(long t) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sd = sdf.format(t * 1000);
		Date date = null;
		try {		
			date = sdf.parse(sd);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date.getTime();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataProcess dp = new DataProcess();
		dp.updateData();
		System.out.println("update succeeded.");
		System.out.println(dp.dateDataSet.toString());
	}
}
