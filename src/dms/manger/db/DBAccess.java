/**
 * 
 */
package dms.manger.db;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import dms.manger.data.MatchedLogRec;
import dms.manger.util.Config;

/**
 * @author bird
 *
 */
public class DBAccess {
	// oracle connection info
	private String driver;		// Name of Driver	
	private String url;
	private Connection conn;
	
	// config info
	private String DBName;
	private String serverIp;
	private String tableName;
	private Config cfg;
	
	// user info
	private String user;		// Name of user
	private String password;
	
	
	/**
	 * 
	 */
	public DBAccess(String usr, String pwd) {
		// get user info from user input	
		user = usr;
		password = pwd;
		cfg = new Config("config/config.properties");
		// get config info from config file
		tableName = cfg.getProperty("tableName");
		DBName = cfg.getProperty("DBName");
		serverIp = cfg.getProperty("IP");
		// init oracle connection info
		url = "jdbc:oracle:thin:@" + serverIp + ":1521:" + DBName;
		driver = "oracle.jdbc.driver.OracleDriver";
	}
	
	
	/*
	 * 连接数据库
	 * 参数：无
	 */
	public boolean connDataBase() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);			
			if (!conn.isClosed())
				return true;
				//JOptionPane.showMessageDialog(null, "Connection Success!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
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
	/*
	 * pull data from server
	 */
	private ArrayList<MatchedLogRec> pullServerData() {
		String getAllData = "select * from matched_record";
		ResultSet rs = null;
		ArrayList<MatchedLogRec> logs = new ArrayList<MatchedLogRec>();
		try {
			Statement getDataSet = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
			rs = getDataSet.executeQuery(getAllData);
			rs.last();
			System.out.println(rs.getRow());
			while (rs.next()) {
				String usr = rs.getString(2);
				int pid = rs.getInt(3);
				int logInTime = rs.getInt(4);
				int logOutTime = rs.getInt(5);
				int duration = rs.getInt(6);
				String ip = rs.getString(7);
				MatchedLogRec mgr = new MatchedLogRec(usr, pid, logInTime, logOutTime, duration, ip);
				
				logs.add(mgr);
			}
			DBAccess.freeRsSt(rs, getDataSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return logs;
	}
	
	public static void freeRsSt(ResultSet rs, Statement st) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (st != null) {
					try {
						st.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				}
			}
		}
	}
	
	/*
	 * 与数据库断开连接
	 * 参数：无
	 */
	public Boolean disConn() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DBAccess dba = new DBAccess("system", "open123");
		if (dba.connDataBase()) {
			System.out.println("DataBase Connection Succeeded.");
			dba.updateCache();
		} else {
			System.out.println("DataBase Connection Failed!");
		}
	}

}
