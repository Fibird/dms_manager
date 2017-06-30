/**
 * 
 */
package dms.manger.data;

import java.util.*;

/**
 * @author bird
 *
 */
public class DateDataSet {
	private Hashtable<Long, UserSet> dateDataSet;
	/**
	 * 
	 */
	public DateDataSet() {
		// TODO Auto-generated constructor stub
		dateDataSet = new Hashtable<Long, UserSet>();
	}
	
	public Hashtable<Long, UserSet> getDateSet() {
		return dateDataSet;
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
	public UserSet getUserSet(long date) {
		return (UserSet)dateDataSet.get(date);
	}
	
	public String toString() {
		ArrayList<String> s = new ArrayList<String>();

		for (Iterator<Long> it = dateDataSet.keySet().iterator(); it.hasNext();) {
			long tmp = it.next();
			if (dateDataSet.get(tmp) != null) {
				s.add(tmp + "\n");
				s.add(dateDataSet.get(tmp).toString());
				s.add("\n");
			}
			
		}
		return s.toString();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
}
