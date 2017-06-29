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
	}
	
	public void insert(UserInfo ui) {
		UserInfo rs = searchByUsrName(ui.getUserName());
		if (rs == null) {
			usrSet.add(ui);
		} else {
			int t = rs.getDuration() + ui.getDuration();
			rs.setDuration(t);
		}
	}
	
	private UserInfo searchByUsrName(String usr) {
		for (int i = 0; i < usrSet.size(); ++i) {
			if (usrSet.get(i).getUserName().equals(usr)) {
				return usrSet.get(i);
			}
		}
		return null;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
