/**
 * 
 */
package dms.manger.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author bird
 *
 */
public class DBAccsee {
	private String driver;		// Name of Driver
	private String user;		// Name of user
	private String url;
	private String password;
	private Connection conn;
	private String tableName;
	/**
	 * 
	 */
	public DBAccsee(String usr, String pwd) {
		// TODO Auto-generated constructor stub		
		user = usr;
		password = pwd;
		url = "jdbc:oracle:thin:@172.29.19.164:1521:ora1";
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
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
