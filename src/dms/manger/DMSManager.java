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
		
		if (value.btnValue) {			
			MainWindow window = new MainWindow();
			window.dba.SetLogInfo(value.usr, value.pwd);
			if (window.dba.connDataBase()) {
				window.dp.updateData();
				window.frmMainWindow.setVisible(true);				
			} else {
				System.out.println("Database connection failed!");
			}
		}			
	}
}
