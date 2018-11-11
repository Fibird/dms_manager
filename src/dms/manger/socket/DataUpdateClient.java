/**
 * 
 */
package dms.manger.socket;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;

import dms.manger.util.Config;
import dms.manger.data.MatchedLogRec;

/**
 * @author chaoy
 *
 */
public class DataUpdateClient {
	private Socket sc;
	private String serverIp;
	private int port;
	private Config cfg;
	public ArrayList<MatchedLogRec> logs = new ArrayList<MatchedLogRec>();
	/**
	 * 
	 */
	public DataUpdateClient() {
		// get config info from config file
		cfg = new Config("config/config.properties");
		// get config info from config file
		serverIp = cfg.getProperty("IP");
		port = Integer.parseInt(cfg.getProperty("PORT"));
	}
	
	private ArrayList<MatchedLogRec> pullServerData()
    {
        DataInputStream netInputStream;
        DataOutputStream netOutputStream;
        byte[] buffer;
 
        try
        {
            sc = new Socket(serverIp, port);
            if (sc.isConnected())
            {
            	System.out.println("Connected to server.");
            }
            
            netInputStream=new DataInputStream(sc.getInputStream());
            netOutputStream=new DataOutputStream(sc.getOutputStream());
            int flag = 2;
            // send flag to tell server who am i
            netOutputStream.write(Integer.toString(flag).getBytes());
            System.out.println("Send flag to tell server who am I.");

            buffer = new byte[MatchedLogRec.getDataPackSize()];
            System.out.println("Getting data from server...");
            // receive data from server
            while((netInputStream.read(buffer))>0)
            {
            	MatchedLogRec ml = new MatchedLogRec();
            	String temp = buffer.toString();
            	ml.setLogName(temp.substring(0, 31));
            	ml.setPid(Integer.parseInt(temp.substring(32, 63)));
            	ml.setLoginTime(Integer.parseInt(temp.substring(64, 95)));
            	ml.setLogoutTime(Integer.parseInt(temp.substring(96, 127)));
            	ml.setDuration(Integer.parseInt(temp.substring(128, 159)));
            	ml.setLogIp(temp.substring(160, 191));
            	System.out.println("Get log Data!");
            	ml.toString();
            	logs.add(ml);
            }
            System.out.println("Data updating ends.");
            netInputStream.close();
            netOutputStream.close();
            sc.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return logs;
    }
	
	/*
	 * update local data from server
	 */
	public void updateCache() {
		ArrayList<MatchedLogRec> logs = pullServerData();
		Iterator<MatchedLogRec> itr = logs.iterator();
		File f = null;
		try {
			f = new File("cache/logs.txt");
			// if not existing file, create it.
			if (!f.exists()) {
				// if not existing parent dir, create it.
				if (!f.getParentFile().exists()) {
					f.getParentFile().mkdirs();
				}
				f.createNewFile();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		try {
			// output result to file
			BufferedWriter output;
			output = new BufferedWriter(new FileWriter(f));
			while (itr.hasNext()) {
				MatchedLogRec tmp = itr.next();
				output.write(tmp.toString()+'\n');
			}
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataUpdateClient duc = new DataUpdateClient();
		duc.updateCache();
	}
}
