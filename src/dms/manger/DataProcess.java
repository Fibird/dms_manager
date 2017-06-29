/**
 * 
 */
package dms.manger;

import java.io.*;

import dms.manger.data.DateDataSet;
import dms.manger.data.MatchedLogRec;
import dms.manger.data.UserInfo;

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
	
	public void updateData() {
		File f = new File("cache/logs.txt");
		BufferedReader input = null;
		try {
			// output result to file
			String cl = new String();
			input = new BufferedReader(new FileReader(f));
			while ((cl = input.readLine()) != null) {
				String[] splited = cl.split("\\s+");
				
			}
			//input.
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
	UserInfo processData(String[] s) {
		String usr = s[0];
		int logDate = Integer.parseInt(s[3]);
		int dur = Integer.parseInt(s[4]);
		String ip = s[5];
		int pid = Integer.parseInt(s[1]);
		return new UserInfo(usr, logDate, pid, ip, dur);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
