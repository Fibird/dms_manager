package dms.manger;

import java.awt.EventQueue;
import javax.swing.JFrame;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.button.WebButton;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.JTextField;
import org.jdatepicker.*;
import org.jdatepicker.impl.*;

import java.awt.Dimension;
import javax.swing.BoxLayout;

public class MainWindow {

	private JFrame frmDmsManager;
	private WebPanel infoPanel;
	private JTextField textField;
	private JButton showFormsBtn;
	private JButton dateBtn;
	private JButton freshBtn;
	private JPanel btnPanel;
	private SpringLayout springLayout;
	private JButton searchBtn;
	JDatePanelImpl datePanel;
	JDatePickerImpl datePicker;
	//private JDatePicker datePicker;
	
	// database variables
	private String driver;		// Name of Driver
	private String user;		// Name of user
	private String url;
	private String password;
	private Connection conn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {				
					MainWindow window = new MainWindow();
					window.frmDmsManager.setVisible(true);
					
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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		WebLookAndFeel.install ();
		WebLookAndFeel.initializeManagers ();
		frmDmsManager = new JFrame();
		frmDmsManager.setIconImage(Toolkit.getDefaultToolkit().getImage("images/mainwindow.ico"));
		frmDmsManager.setTitle("DMS Manager");
		frmDmsManager.setBounds(100, 100, 899, 600);
		frmDmsManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		infoPanel = new WebPanel();
		frmDmsManager.getContentPane().add(infoPanel, BorderLayout.CENTER);
		infoPanel.setLayout(null);
		
		btnPanel = new JPanel();
		btnPanel.setMinimumSize(new Dimension(100, 100));
		frmDmsManager.getContentPane().add(btnPanel, BorderLayout.NORTH);
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
		
		freshBtn = new JButton("Fresh");
		freshBtn.setActionCommand("");
		freshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setPreferredSize(new Dimension(0, 40));
		verticalStrut.setMinimumSize(new Dimension(0, 40));
		verticalStrut.setMaximumSize(new Dimension(0, 40));
		btnPanel.add(verticalStrut);
		btnPanel.add(freshBtn);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		horizontalGlue.setMinimumSize(new Dimension(300, 0));
		horizontalGlue.setMaximumSize(new Dimension(800, 0));
		btnPanel.add(horizontalGlue);
		
		dateBtn = new JButton("Date");
		//btnPanel.add(dateBtn);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		rigidArea_1.setMinimumSize(new Dimension(20, 40));
		rigidArea_1.setMaximumSize(new Dimension(20, 40));
		rigidArea_1.setPreferredSize(new Dimension(10, 40));
		
	
		UtilDateModel model = new UtilDateModel();
        //model.setDate(20,04,2014);
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        
        datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        SpringLayout springLayout_1 = (SpringLayout) datePicker.getLayout();
        datePicker.setMaximumSize(new Dimension(100, 23));
        datePicker.setSize(100, 20);
        btnPanel.add(datePicker);
		
		searchBtn = new JButton("Search");
		btnPanel.add(searchBtn);
		//btnPanel.add(textField);
		//textField.setColumns(10);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		horizontalGlue_1.setMaximumSize(new Dimension(800, 0));
		btnPanel.add(horizontalGlue_1);
		
		showFormsBtn = new JButton("Forms");
		btnPanel.add(showFormsBtn);
		
		//infoScrollPanel.setViewportView(infoTable);
		//frame.getContentPane().add(comp, constraints);
	}
	boolean getServerLogs() {
		
		return true;
	}
}
