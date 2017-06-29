/**
 * 
 */
package dms.manger.data;

/**
 * @author bird
 *
 */
public class UserInfo {
	private String userName;
	private int logDate;
	private int pid;
	private String logIp;
	private int duration;
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
	public UserInfo(String userName, int logDate, int pid, String logIp, int duration) {
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

	public int getLogDate() {
		return logDate;
	}

	public void setLogDate(int logDate) {
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

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
