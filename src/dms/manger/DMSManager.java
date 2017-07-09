/**
 * 
 */
package dms.manger;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import dms.manger.data.ReturnValues;


/**
 * @author bird
 *
 */
public class DMSManager {
	static ReturnValues value = new ReturnValues(null, null, false);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Login lg = new Login();
		try {
			lg.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			lg.setModal(true);
			value = lg.showDialog();
		} catch (Exception e) {
			e.printStackTrace();
		}
		MainWindow window = new MainWindow();
		if (value.btnValue) {			
			
			window.getDba().SetLogInfo(value.usr, value.pwd);
			if (window.connDataBase()) {
				window.setHasLogined(true);
				window.updateCache();
				window.frmMainWindow.setVisible(true);				
			} else {
				int res = JOptionPane.showConfirmDialog(null, "You can using me offline, do you want to login later?");
				if (res == JOptionPane.YES_OPTION) {
					window.frmMainWindow.setVisible(true);
				}
			}
		} else {
			int res = JOptionPane.showConfirmDialog(null, "You can using me offline, do you want to login later?");
			if (res == JOptionPane.YES_OPTION) {
				window.frmMainWindow.setVisible(true);
			}
		}
	}
}
