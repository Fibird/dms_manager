/**
 * 
 */
package dms.manger.data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author bird
 *
 */
public class UserInfo {
	private String userName;
	private long logDate;
	private int pid;
	private String logIp;
	private long duration;
	/**
	 * 
	 */
	public UserInfo() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param userName
	 * @param logDate
	 * @param pid
	 * @param logIp
	 * @param duration
	 */
	public UserInfo(String userName, long logDate, int pid, String logIp, long duration) {
		super();
		this.userName = userName;
		this.logDate = logDate;
		this.pid = pid;
		this.logIp = logIp;
		this.duration = duration;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getLogDate() {
		return logDate;
	}
	
	public String getNormalLogDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sd = sdf.format(logDate);
		return sd;
	}
	
	public void setLogDate(long logDate) {
		this.logDate = logDate;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getLogIp() {
		return logIp;
	}

	public void setLogIp(String logIp) {
		this.logIp = logIp;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "UserInfo [userName=" + userName + ", logDate=" + logDate + ", pid=" + pid + ", logIp=" + logIp
				+ ", duration=" + duration + "]";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
