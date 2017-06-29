/**
 * 
 */
package dms.manger.data;

import java.util.*;

/**
 * @author bird
 *
 */
public class UserSet {
	private ArrayList<UserInfo> usrSet;
	
	/**
	 * 
	 */
	public UserSet() {
		// TODO Auto-generated constructor stub
		usrSet = new ArrayList<UserInfo>();
	}
	
	public void insert(UserInfo ui) {
		UserInfo rs = searchByUsrName(ui.getUserName());
		if (rs == null) {
			usrSet.add(ui);
		} else {
			long t = rs.getDuration() + ui.getDuration();
			rs.setDuration(t);
		}
	}
	
	private UserInfo searchByUsrName(String usr) {
		for(UserInfo u : usrSet) {
	        if((u.getUserName() != null) && u.getUserName().equals(usr)) {
	           //something here
	        	return u;
	    }
		}
		return null;
	}
	
	public ArrayList<UserInfo> getList() {
		return usrSet;
	}
	public String toString() {
		String s = new String();
		for (UserInfo u : usrSet) {
			s = s + u + "\n";
		}
		return s;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserSet us = new UserSet();
		for (int i = 0; i < 100; i++) {
			String t = "majun" + i;
			us.insert(new UserInfo(t, 2342423, 232, "172.29.21.23", 2423));
		}
		System.out.println(us.toString());
	}
}
