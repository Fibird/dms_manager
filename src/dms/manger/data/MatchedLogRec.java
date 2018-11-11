/**
 * 
 */
package dms.manger.data;

/**
 * @author bird
 *
 */
public class MatchedLogRec {
	String logName;
	int pid;
	int loginTime;
	int logoutTime;
	int duration;
	String logIp;
	static int dataPackSize;
	/**
	 * 
	 */
	public MatchedLogRec() {
		// TODO Auto-generated constructor stub
		dataPackSize = 32 + 32 + 32 + 32 + 32 + 32;
	}
	/**
	 * @param logName
	 * @param pid
	 * @param loginTime
	 * @param logoutTime
	 * @param duration
	 * @param logIp
	 */
	public MatchedLogRec(String logName, int pid, int loginTime, int logoutTime, int duration, String logIp) {
		super();
		this.logName = logName;
		this.pid = pid;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
		this.duration = duration;
		this.logIp = logIp;
	}
	@Override
	public String toString() {
		return logName + "\t" + pid + "\t" + loginTime + "\t"
				+ logoutTime + "\t" + duration + "\t" + logIp;
	}
	public String getLogName() {
		return logName;
	}
	public void setLogName(String logName) {
		this.logName = logName;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(int loginTime) {
		this.loginTime = loginTime;
	}
	public int getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(int logoutTime) {
		this.logoutTime = logoutTime;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getLogIp() {
		return logIp;
	}
	public void setLogIp(String logIp) {
		this.logIp = logIp;
	}
	public static int getDataPackSize() {
		return dataPackSize;
	}
}
