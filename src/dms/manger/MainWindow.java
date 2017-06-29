package dms.manger;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;

import com.alee.extended.date.WebDateField;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.splitpane.WebSplitPane;
import static javax.swing.JSplitPane.HORIZONTAL_SPLIT;

import dms.manger.data.UserInfo;
import dms.manger.data.UserSet;
import dms.manger.db.DBAccess;


public class MainWindow {
	// components in ui
	JFrame frmMainWindow;
	private WebPanel infoPanel;
	private WebPanel formPanel;
	private JButton showFormsBtn;
	private JButton freshBtn;
	private JPanel btnPanel;
	private JButton searchBtn;
	private WebDateField dateField;
	//private JScrollPanel tablePanel;
	private JTable logInfoTable;
	private DefaultTableModel logInfoTableModel;
	private JScrollPane tableScrollPanel;
	// variables for data process
	DataProcess dp;
	DBAccess dba;
	private JTabbedPane tabbedPane;
	private JPanel monthFormPanel;
	private JPanel yearFormPanel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {				
					MainWindow window = new MainWindow();
					window.frmMainWindow.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
		dp = new DataProcess();
		dba = new DBAccess();
//		dba.connDataBase();
		dp.updateData();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		WebLookAndFeel.install();
		WebLookAndFeel.initializeManagers ();
		frmMainWindow = new JFrame();
		frmMainWindow.setIconImage(Toolkit.getDefaultToolkit().getImage("images/mainwindow.ico"));
		frmMainWindow.setTitle("DMS Manager");
		frmMainWindow.setBounds(100, 100, 899, 600);
		frmMainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		infoPanel = new WebPanel();
		formPanel = new WebPanel();
		WebSplitPane splitPane = new WebSplitPane(HORIZONTAL_SPLIT, infoPanel, formPanel);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		formPanel.add(tabbedPane, BorderLayout.CENTER);
		
		monthFormPanel = new JPanel();
		tabbedPane.addTab("Month Form", null, monthFormPanel, null);
		
		yearFormPanel = new JPanel();
		tabbedPane.addTab("Year Form", null, yearFormPanel, null);
		splitPane.setContinuousLayout(true);
		splitPane.setOneTouchExpandable(true);
		frmMainWindow.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		String[] columnNames = {"user", "IP", "duration", "pid", "date"};
		logInfoTableModel = new DefaultTableModel(null, columnNames);
		infoPanel.setLayout(new BorderLayout(0, 0));
		logInfoTable = new JTable(logInfoTableModel);
		
		tableScrollPanel = new JScrollPane(logInfoTable);
		infoPanel.add(tableScrollPanel);

		btnPanel = new JPanel();
		btnPanel.setMinimumSize(new Dimension(100, 100));
		frmMainWindow.getContentPane().add(btnPanel, BorderLayout.NORTH);
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
		
		freshBtn = new JButton("Fresh");
		freshBtn.setActionCommand("");
		freshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dba.updateCache();
				dp.updateData();
			}
		});
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setPreferredSize(new Dimension(0, 40));
		verticalStrut.setMinimumSize(new Dimension(0, 40));
		verticalStrut.setMaximumSize(new Dimension(0, 40));
		btnPanel.add(verticalStrut);
		btnPanel.add(freshBtn);
		
		Component leftHGlue = Box.createHorizontalGlue();
		leftHGlue.setPreferredSize(new Dimension(300, 0));
		leftHGlue.setMinimumSize(new Dimension(300, 0));
		leftHGlue.setMaximumSize(new Dimension(1000, 0));
		btnPanel.add(leftHGlue);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		rigidArea_1.setMinimumSize(new Dimension(20, 40));
		rigidArea_1.setMaximumSize(new Dimension(20, 40));
		rigidArea_1.setPreferredSize(new Dimension(10, 40));
       
        // Simple date field
        dateField = new WebDateField ( new Date () );
        dateField.setMinimumSize(new Dimension(100, 23));
        dateField.setPreferredSize(new Dimension(100, 26));
        dateField.setMaximumSize(new Dimension(100, 23));
        dateField.setHorizontalAlignment ( SwingConstants.CENTER );
        
        btnPanel.add(dateField);
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateField.setDateFormat((SimpleDateFormat) dateFormat);
		searchBtn = new JButton("Search");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserSet ts = dp.getUserSet(dateField.getDate().getTime());
				//System.out.println( dateField.getDate().getTime());
				clearLogTable();
				if (ts != null) {
					for (UserInfo u : ts.getList()) {
						logInfoTableModel.addRow(new Object [] {u.getUserName(), u.getLogIp(), u.getDuration(), u.getPid(), u.getNormalLogDate()});
					}
				} else {
					JOptionPane.showMessageDialog(null, "Oops, No result to display!");
				}
			}
		});
		btnPanel.add(searchBtn);
		
		Component rightHGlue = Box.createHorizontalGlue();
		rightHGlue.setMinimumSize(new Dimension(260, 0));
		rightHGlue.setPreferredSize(new Dimension(260, 0));
		rightHGlue.setMaximumSize(new Dimension(1000, 0));
		btnPanel.add(rightHGlue);
		
		showFormsBtn = new JButton("show Forms");
		btnPanel.add(showFormsBtn);
	}
	boolean getServerLogs() {
		
		return true;
	}
	void clearLogTable() {
		while (logInfoTableModel.getRowCount() != 0) {
			logInfoTableModel.removeRow(0);
		}
	}
}
