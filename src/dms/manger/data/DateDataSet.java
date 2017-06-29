/**
 * 
 */
package dms.manger.data;

import java.util.Hashtable;

/**
 * @author bird
 *
 */
public class DateDataSet {
	Hashtable<Integer, UserSet> dateDataSet;
	/**
	 * 
	 */
	public DateDataSet() {
		// TODO Auto-generated constructor stub
		dateDataSet = new Hashtable();
	}
	
	public void insert(UserInfo userInfo) {
		UserSet usrSet = getUserSet(userInfo.getLogDate());
		if (usrSet == null) {
			UserSet us = new UserSet();
			us.insert(userInfo);
			dateDataSet.put(userInfo.getLogDate(), us);
		} else {
			usrSet.insert(userInfo);
		}	
	}
	private UserSet getUserSet(int date) {
		return (UserSet)dateDataSet.get(date);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
