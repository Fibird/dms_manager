package dms.manger;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;

import com.alee.extended.date.WebDateField;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.splitpane.WebSplitPane;
import static javax.swing.JSplitPane.HORIZONTAL_SPLIT;

import dms.manger.chart.LineChartPanel;
import dms.manger.data.FormData;
import dms.manger.data.ReturnValues;
import dms.manger.data.UserInfo;
import dms.manger.data.UserSet;
import dms.manger.db.DBAccess;

import com.alee.extended.button.WebSwitch;
import com.alee.extended.date.DateSelectionListener;

public class MainWindow {
	// components in ui
	JFrame frmMainWindow;
	private WebPanel infoPanel;
	private WebPanel formPanel;
	private JButton freshBtn;
	private JPanel btnPanel;
	private JButton searchBtn;
	private WebDateField dateField;
	WebSwitch modeSwitch;
	// private JScrollPanel tablePanel;
	private JTable logInfoTable;
	private DefaultTableModel logInfoTableModel;
	private JScrollPane tableScrollPanel;
	// variables for data process
	private DataProcess dp;

	public DataProcess getDp() {
		return dp;
	}

	public void setDp(DataProcess dp) {
		this.dp = dp;
	}

	public DBAccess getDba() {
		return dba;
	}

	public void setDba(DBAccess dba) {
		this.dba = dba;
	}

	private DBAccess dba;
	private JTabbedPane tabbedPane;
	private JPanel monthFormPanel;
	private JPanel yearFormPanel;
	private LineChartPanel mlcp;
	private LineChartPanel ylcp;
	private JPanel switchPanel;
	private boolean hasLogined;
	private JMenuBar menuBar;
	private JMenu mnTool;
	private JMenuItem mntmLogin;
	private JMenuItem mntmExit;
	private JMenu mnAbout;
	private JMenuItem mntmAbout;

	public boolean isHasLogined() {
		return hasLogined;
	}

	public void setHasLogined(boolean hasLogined) {
		this.hasLogined = hasLogined;
	}

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
		dp = new DataProcess();
		dba = new DBAccess();
		// dba.connDataBase();
		dp.updateData();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		WebLookAndFeel.install();
		WebLookAndFeel.initializeManagers();
		hasLogined = false;
		frmMainWindow = new JFrame();
		frmMainWindow.setIconImage(Toolkit.getDefaultToolkit().getImage("images/mainwindow.ico"));
		frmMainWindow.setTitle("DMS Manager");
		frmMainWindow.setBounds(100, 100, 924, 621);
		frmMainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		infoPanel = new WebPanel();
		formPanel = new WebPanel();
		WebSplitPane splitPane = new WebSplitPane(HORIZONTAL_SPLIT, infoPanel, formPanel);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setPreferredSize(new Dimension(400, 300));
		tabbedPane.setMinimumSize(new Dimension(400, 300));
		formPanel.add(tabbedPane, BorderLayout.CENTER);

		monthFormPanel = new JPanel();
		monthFormPanel.setMinimumSize(new Dimension(400, 300));
		monthFormPanel.setPreferredSize(new Dimension(400, 300));
		monthFormPanel.setLayout(new BorderLayout(0, 0));
		tabbedPane.addTab("Month Form", null, monthFormPanel, null);

		yearFormPanel = new JPanel();
		yearFormPanel.setPreferredSize(new Dimension(400, 300));
		yearFormPanel.setMinimumSize(new Dimension(400, 300));
		yearFormPanel.setLayout(new BorderLayout(0, 0));
		tabbedPane.addTab("Year Form", null, yearFormPanel, null);
		splitPane.setContinuousLayout(true);
		splitPane.setOneTouchExpandable(true);
		frmMainWindow.getContentPane().add(splitPane, BorderLayout.CENTER);

		String[] columnNames = { "user", "IP", "duration", "pid", "date" };
		logInfoTableModel = new DefaultTableModel(null, columnNames);
		infoPanel.setLayout(new BorderLayout(0, 0));
		logInfoTable = new JTable(logInfoTableModel);

		tableScrollPanel = new JScrollPane(logInfoTable);
		tableScrollPanel.setPreferredSize(new Dimension(452, 248));
		tableScrollPanel.setMinimumSize(new Dimension(248, 248));
		infoPanel.add(tableScrollPanel);

		btnPanel = new JPanel();
		btnPanel.setMinimumSize(new Dimension(100, 100));
		frmMainWindow.getContentPane().add(btnPanel, BorderLayout.NORTH);
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));

		freshBtn = new JButton("Fresh");
		freshBtn.setActionCommand("");
		freshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isHasLogined()) {
					dba.updateCache();
					dp.updateData();
				} else {
					JOptionPane.showMessageDialog(null, "You haven't logined, please login server before freshing!");
				}				
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
		dateField = new WebDateField(new Date());
		dateField.addDateSelectionListener(new DateSelectionListener() {
			public void dateSelected(Date arg0) {
				SimpleDateFormat msdf = new SimpleDateFormat("MM");
				//System.out.println("test" + msdf.format(dateField.getDate()));
				mlcp.updateDataSet(createMonthDataSet(), msdf.format(dateField.getDate()));
				SimpleDateFormat ysdf = new SimpleDateFormat("yyyy");
				ylcp.updateDataSet(createYearDataSet(), "Year " + ysdf.format(dateField.getDate()));
			}
		});

		dateField.setMinimumSize(new Dimension(100, 23));
		dateField.setPreferredSize(new Dimension(100, 26));
		dateField.setMaximumSize(new Dimension(100, 23));
		dateField.setHorizontalAlignment(SwingConstants.CENTER);

		btnPanel.add(dateField);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateField.setDateFormat((SimpleDateFormat) dateFormat);
		searchBtn = new JButton("Search");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserSet ts = dp.getUserSet(dateField.getDate().getTime());
				// System.out.println( dateField.getDate().getTime());
				clearLogTable();
				if (ts != null) {
					for (UserInfo u : ts.getList()) {
						logInfoTableModel.addRow(new Object[] { u.getUserName(), u.getLogIp(), u.getDuration(),
								u.getPid(), u.getNormalLogDate() });
					}
				} else {
					JOptionPane.showMessageDialog(null, "Oops, No result to display!");
				}
			}
		});
		btnPanel.add(searchBtn);

		Component rightHGlue = Box.createHorizontalGlue();
		rightHGlue.setMinimumSize(new Dimension(250, 0));
		rightHGlue.setPreferredSize(new Dimension(260, 0));
		rightHGlue.setMaximumSize(new Dimension(1000, 0));
		btnPanel.add(rightHGlue);

		switchPanel = new JPanel();
		switchPanel.setMaximumSize(new Dimension(100, 30));
		switchPanel.setPreferredSize(new Dimension(100, 23));
		switchPanel.setMinimumSize(new Dimension(100, 23));
		btnPanel.add(switchPanel);
		switchPanel.setLayout(new GridLayout(0, 1, 0, 0));
		modeSwitch = new WebSwitch();
		modeSwitch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				splitPane.setOrientation(1 - splitPane.getOrientation());
			}
		});

		modeSwitch.setPreferredSize(new Dimension(100, 23));
		modeSwitch.setMaximumSize(new Dimension(100, 23));
		modeSwitch.setMinimumSize(new Dimension(100, 20));
		switchPanel.add(modeSwitch);
		modeSwitch.setMaximumWidth(100);
		modeSwitch.setMaximumHeight(20);
		modeSwitch.setPreferredWidth(100);
		modeSwitch.setPreferredHeight(20);
		modeSwitch.setMinimumWidth(100);
		modeSwitch.setMinimumHeight(20);

		WebLabel wblblV = new WebLabel("VERTI");
		wblblV.setBoldFont(true);
		wblblV.setText("  VERTI");
		WebLabel wblblH = new WebLabel("HORIZ");
		wblblH.setText(" HORIZ");
		wblblH.setBoldFont(true);
		modeSwitch.setLeftComponent(wblblH);
		modeSwitch.setRightComponent(wblblV);
		modeSwitch.setRightComponent(wblblV);

		menuBar = new JMenuBar();
		frmMainWindow.setJMenuBar(menuBar);

		mnTool = new JMenu("Tool");
		menuBar.add(mnTool);

		mntmLogin = new JMenuItem("Login");
		mntmLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reLogin();
			}
		});
		mnTool.add(mntmLogin);

		mntmExit = new JMenuItem("Logout");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hasLogined = false;
				dba.disConn();
			}
		});
		mnTool.add(mntmExit);

		mnAbout = new JMenu("About");
		menuBar.add(mnAbout);

		mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Copyright(c)2017 NWAFU\nchaoyanglius@outlook.com\nAll rights reserved.");
			}
		});
		mnAbout.add(mntmAbout);

		mlcp = new LineChartPanel("Month Form", "month", monthFormPanel.getPreferredSize());
		SimpleDateFormat msdf = new SimpleDateFormat("MM");
		mlcp.updateDataSet(createMonthDataSet(), msdf.format(dateField.getDate()));
		monthFormPanel.add(mlcp.getChartPanel(), BorderLayout.CENTER);
		SimpleDateFormat ysdf = new SimpleDateFormat("yyyy");
		ylcp = new LineChartPanel("Year Form", "year", yearFormPanel.getPreferredSize());
		ylcp.updateDataSet(createYearDataSet(), "Year " + ysdf.format(dateField.getDate()));
		yearFormPanel.add(ylcp.getChartPanel(), BorderLayout.CENTER);
	}

	boolean getServerLogs() {

		return true;
	}

	void clearLogTable() {
		while (logInfoTableModel.getRowCount() != 0) {
			logInfoTableModel.removeRow(0);
		}
	}

	public ArrayList<FormData> createMonthDataSet() {
		ArrayList<FormData> monthDataSet = new ArrayList<FormData>();

		Calendar cdr = Calendar.getInstance();
		cdr.setTime(dateField.getDate());
		cdr.set(Calendar.DAY_OF_MONTH, 1);
		long lowLimit = cdr.getTimeInMillis();
		cdr.set(Calendar.DAY_OF_MONTH, cdr.getActualMaximum(Calendar.DAY_OF_MONTH));
		long upLimit = cdr.getTimeInMillis();
		// System.out.println(lowLimit + "-" + upLimit);
		Hashtable<Long, UserSet> ds = dp.getDateDataSet().getDateSet();

		for (long day = lowLimit; day <= upLimit; day = day + 24 * 3600 * 1000) {
			FormData fd = new FormData();
			fd.setxAxisData(day);
			UserSet us = ds.get(day);
			if (us != null) {
				long dur = 0;
				for (UserInfo u : us.getList()) {
					dur += u.getDuration();
				}
				dur /= 3600;
				fd.setyAxisData(dur);
			} else {
				fd.setyAxisData(0);
			}
			monthDataSet.add(fd);
		}
		return monthDataSet;
	}

	public ArrayList<FormData> createYearDataSet() {
		ArrayList<FormData> yearDataSet = new ArrayList<FormData>();
		Hashtable<Long, UserSet> ds = dp.getDateDataSet().getDateSet();
		Calendar cdr = Calendar.getInstance();
		cdr.setTime(dateField.getDate());
		for (int i = 1; i <= 12; i++) {
			FormData fd = new FormData();
			cdr.set(Calendar.MONTH, i - 1);
			cdr.set(Calendar.DAY_OF_MONTH, 1);
			long lowLimit = cdr.getTimeInMillis();
			cdr.set(Calendar.DAY_OF_MONTH, cdr.getActualMaximum(Calendar.DAY_OF_MONTH));
			long upLimit = cdr.getTimeInMillis();
			long dur = 0;
			for (long day = lowLimit; day <= upLimit; day = day + 24 * 3600 * 1000) {
				UserSet us = ds.get(day);
				if (us != null) {
					for (UserInfo u : us.getList()) {
						dur += u.getDuration();
					}
				}
			}
			dur /= 1000;
			fd.setxAxisData(i);
			fd.setyAxisData(dur);
			yearDataSet.add(fd);
		}
		return yearDataSet;
	}

	public boolean connDataBase() {
		return dba.connDataBase();
	}

	public void updateCache() {
		dp.updateData();
	}

	public void reLogin() {
		ReturnValues value = new ReturnValues(null, null, false);
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
			window.getDba().SetLogInfo(value.usr, value.pwd);
			if (window.connDataBase()) {
				window.setHasLogined(true);
			}
		}
	}
}
