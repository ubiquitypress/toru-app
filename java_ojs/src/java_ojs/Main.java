package java_ojs;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Button;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.BorderLayout;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.awt.Label;

public class Main {
	JFrame login, api, issues, articles, settings;
	private JTextField access_key, api_url,username;
	private JTable table;
	private int delay = 500; // milliseconds
	private String source_api = "";
	private String source_access_key = "";
	private JPasswordField passwordField;
	/*
	 * Initial setup test
	 */
	public void login() {
		login = new JFrame();
		login.getContentPane().setForeground(Color.WHITE);
		login.getContentPane().setBackground(new Color(128, 128, 128));

		login.setSize(400, 500);// 400 width and 500 height
		login.getContentPane().setLayout(null);// using no layout managers
		username = new JTextField();
		username.setBounds(80, 195, 239, 26);
		login.getContentPane().add(username);
		username.setColumns(10);
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(new Color(245, 255, 250));
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(80, 179, 239, 16);
		login.getContentPane().add(lblUsername);
		JPanel title_background = new JPanel();
		title_background.setBackground(new Color(0, 0, 0));
		title_background.setBounds(-17, 0, 433, 54);
		login.getContentPane().add(title_background);
		JLabel lblNewLabel = new JLabel("TORU");
		title_background.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(255, 250, 250));
		lblNewLabel.setBackground(new Color(230, 230, 250));
		lblNewLabel.setFont(new Font("Trattatello", Font.BOLD, 24));
		lblNewLabel.setToolTipText("Welcome\n");
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setForeground(new Color(245, 255, 250));
		lblPassword.setBounds(80, 233, 239, 16);
		login.getContentPane().add(lblPassword);
		passwordField = new JPasswordField();
		passwordField.setBounds(80, 248, 239, 26);
		login.getContentPane().add(passwordField);
		JButton btnLogin = new JButton("Login");
		Action actionSubmit = new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				String user = username.getText();
				String pass = String.valueOf(passwordField.getPassword());
				if (pass.compareTo("root") == 0 && user.compareTo("user") == 0) {
					login.setVisible(false);
					login.dispose();
					if (source_api.compareTo("") == 0
							&& source_access_key.compareTo("") == 0) {
						api(false);
					} else {
						issues();
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Wrong username or password");
				}
			}
		};
		username.addActionListener(actionSubmit);
		passwordField.addActionListener(actionSubmit);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = username.getText();
				String pass = String.valueOf(passwordField.getPassword());
				if (pass.compareTo("root") == 0 && user.compareTo("user") == 0) {
					login.setVisible(false);
					if (source_api.compareTo("") == 0
							&& source_access_key.compareTo("") == 0) {
						api(false);
					} else {
						issues();
					}

				} else {
					JOptionPane.showMessageDialog(null,
							"Wrong username or password");
				}

			}
		});

		btnLogin.setBackground(UIManager.getColor("Button.darkShadow"));
		btnLogin.setBounds(139, 300, 117, 29);
		login.getContentPane().add(btnLogin);

		final JButton btnSync1 = new JButton("Sync");
		btnSync1.setBounds(235, 60, 70, 25);
		login.getContentPane().add(btnSync1);

		JLabel lblLogIn = new JLabel("Log in");
		lblLogIn.setForeground(new Color(224, 255, 255));
		lblLogIn.setFont(new Font("URW Gothic L", Font.BOLD, 24));
		lblLogIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogIn.setBounds(160, 128, 80, 30);
		login.getContentPane().add(lblLogIn);
		
		final Label internetCheck = new Label("   ONLINE");
		internetCheck.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		internetCheck.setBackground(Color.GREEN);
		internetCheck.setForeground(new Color(255,255,255));
		internetCheck.setAlignment(1);
		internetCheck.setBounds(305, 62, 65, 20);
		login.getContentPane().add(internetCheck);

		ActionListener taskPerformer1 = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					Socket sock = new Socket();
					InetSocketAddress addr = new InetSocketAddress(
							"www.google.com", 80);
					sock.setSoTimeout(500);
					sock.connect(addr, 3000);
					sock.close();
					internetCheck.setBackground(Color.GREEN);
					internetCheck.setText("ONLINE");
					btnSync1.setEnabled(true);

				} catch (Exception e) {
					internetCheck.setBackground(Color.RED);
					internetCheck.setText("OFFLINE");
					btnSync1.setEnabled(false);
				}
			}
		};
		new Timer(delay, taskPerformer1).start();
		login.setVisible(true);// making the frame visible
	}
	public void settings() {
		settings = new JFrame();
		settings.getContentPane().setBackground(new Color(128, 128, 128));

		settings.setSize(400, 500);// 400 width and 500 height
		settings.getContentPane().setLayout(null);
		JPanel title_background = new JPanel();
		title_background.setBackground(new Color(0, 0, 0));
		title_background.setBounds(-17, 0, 433, 54);
		settings.getContentPane().add(title_background);
		JLabel lblNewLabel = new JLabel("TORU");
		title_background.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(255, 250, 250));
		lblNewLabel.setBackground(new Color(230, 230, 250));
		lblNewLabel.setFont(new Font("Trattatello", Font.BOLD, 24));
		lblNewLabel.setToolTipText("Welcome\n");
	

		final JButton btnSync1 = new JButton("Sync");
		btnSync1.setBounds(235, 60, 70, 25);
		settings.getContentPane().add(btnSync1);

		JLabel lblSettings = new JLabel("Settings");
		lblSettings.setForeground(new Color(224, 255, 255));
		lblSettings.setFont(new Font("URW Gothic L", Font.BOLD, 24));
		lblSettings.setHorizontalAlignment(SwingConstants.CENTER);
		lblSettings.setBounds(110, 128, 160, 30);
		settings.getContentPane().add(lblSettings);
		
		JCheckBox chckbxSampleSetting = new JCheckBox("Sample setting");
		chckbxSampleSetting.setBounds(125, 206, 150, 23);
		settings.getContentPane().add(chckbxSampleSetting);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				settings.setVisible(false);
				settings.dispose();
				if(issues==null){
					issues();
				}else if (!issues.isVisible()){
					issues.setVisible(true);
				}
			}
		});
		btnSave.setBounds(150, 288, 100, 25);
		settings.getContentPane().add(btnSave);
		final Label internetCheck = new Label("  ONLINE");
		internetCheck.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		internetCheck.setBackground(Color.GREEN);
		internetCheck.setAlignment(1);
		internetCheck.setForeground(new Color(255,255,255));
		internetCheck.setBounds(305, 62, 65, 20);
		settings.getContentPane().add(internetCheck);

		ActionListener taskPerformer1 = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					Socket sock = new Socket();
					InetSocketAddress addr = new InetSocketAddress(
							"www.google.com", 80);
					sock.setSoTimeout(500);
					sock.connect(addr, 3000);

					internetCheck.setBackground(Color.GREEN);
					internetCheck.setText("ONLINE");
					btnSync1.setEnabled(true);
					sock.close();

				} catch (Exception e) {
					internetCheck.setBackground(Color.RED);
					internetCheck.setText("OFFLINE");
					btnSync1.setEnabled(false);
				}
			}
		};
	
		new Timer(delay, taskPerformer1).start();
		settings.setVisible(true);// making the frame visible
	}
	public void api(boolean edit) {
		api = new JFrame();
		api.getContentPane().setBackground(new Color(128, 128, 128));

		api.setSize(400, 500);// 400 width and 500 height
		api.getContentPane().setLayout(null);// using no layout managers
		api_url = new JTextField();
		api_url.setBounds(80, 195, 239, 26);
		api_url.setText(source_api);
		api.getContentPane().add(api_url);
		api_url.setColumns(10);

		JLabel lblApi = new JLabel("API URL");
		lblApi.setForeground(new Color(245, 255, 250));
		lblApi.setHorizontalAlignment(SwingConstants.CENTER);
		lblApi.setBounds(80, 179, 239, 16);
		api.getContentPane().add(lblApi);
		JPanel title_background = new JPanel();
		title_background.setBackground(new Color(0, 0, 0));
		title_background.setBounds(-17, 0, 433, 54);
		api.getContentPane().add(title_background);
		JLabel lblNewLabel = new JLabel("TORU");
		title_background.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(255, 250, 250));
		lblNewLabel.setBackground(new Color(230, 230, 250));
		lblNewLabel.setFont(new Font("Trattatello", Font.BOLD, 24));
		lblNewLabel.setToolTipText("Welcome\n");
		access_key = new JTextField();
		access_key.setColumns(10);
		access_key.setText(source_access_key);
		access_key.setBounds(80, 248, 239, 26);
		api.getContentPane().add(access_key);
		JLabel lblAccessKey = new JLabel("Access Key");
		lblAccessKey.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccessKey.setForeground(new Color(245, 255, 250));
		lblAccessKey.setBounds(80, 233, 239, 16);
		api.getContentPane().add(lblAccessKey);

		JButton btnSubmit = new JButton("Submit");
		if (edit){
			btnSubmit.setText("Save");
		}
		Action actionSubmit = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String url = api_url.getText();
				String key = access_key.getText();
				if (key.compareTo("enter") == 0 && url.compareTo("api") == 0) {
					api.setVisible(false);
					source_api = url;
					source_access_key = key;
					if (!edit){
					issues();}
				} else {
					JOptionPane.showMessageDialog(null,
							"Wrong access key or API url");
				}
			}
		};
		access_key.addActionListener(actionSubmit);
		api_url.addActionListener(actionSubmit);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String url = api_url.getText();
				String key = access_key.getText();
				if (key.compareTo("enter") == 0 && url.compareTo("api") == 0) {
					api.setVisible(false);
					issues();
					source_api = url;
					source_access_key = key;
				} else {
					JOptionPane.showMessageDialog(null,
							"Wrong access key or API url");
				}

			}
		});

		btnSubmit.setBackground(new Color(255, 255, 255));
		btnSubmit.setBounds(139, 300, 117, 29);
		api.getContentPane().add(btnSubmit);

	
		final JButton btnSync1 = new JButton("Sync");
		btnSync1.setBounds(235, 60, 70, 25);
		api.getContentPane().add(btnSync1);

		JLabel lblApiInformation = new JLabel("API Information");
		lblApiInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblApiInformation.setForeground(new Color(255, 255, 255));
		lblApiInformation.setFont(new Font("Dialog", Font.BOLD, 20));
		lblApiInformation.setBounds(55, 110, 309, 43);
		api.getContentPane().add(lblApiInformation);
		final Label internetCheck = new Label("  ONLINE");
		internetCheck.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		internetCheck.setBackground(Color.GREEN);
		internetCheck.setBounds(305, 62, 65, 20);
		internetCheck.setForeground(new Color(255,255,255));
		internetCheck.setAlignment(1);
		api.getContentPane().add(internetCheck);

		ActionListener taskPerformer1 = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					Socket sock = new Socket();
					InetSocketAddress addr = new InetSocketAddress(
							"www.google.com", 80);
					sock.setSoTimeout(500);
					sock.connect(addr, 3000);

					internetCheck.setBackground(Color.GREEN);
					internetCheck.setText("ONLINE");
					btnSync1.setEnabled(true);
					sock.close();

				} catch (Exception e) {
					internetCheck.setBackground(Color.RED);
					internetCheck.setText("OFFLINE");
					btnSync1.setEnabled(false);
				}
			}
		};
		new Timer(delay, taskPerformer1).start();
		api.setVisible(true);// making the frame visible
	}

	public void issues() {
		issues = new JFrame();
		issues.setSize(640, 480);
		issues.getContentPane().setBackground(new Color(128, 128, 128));
		issues.setVisible(true);
		Object rowData[][] = {
				{ "Row1-Column1", "Row1-Column2", "Row1-Column3", "View" },
				{ "Row2-Column1", "Row2-Column2", "Row2-Column3", "View" },
				{ "Row3-Column1", "Row3-Column2", "Row3-Column3", "View" },
				{ "Row4-Column1", "Row4-Column2", "Row4-Column3", "View" } };
		Object columnNames[] = { "Column One", "Column Two", "Column Three", "" };
		issues.getContentPane().setLayout(null);


		final JButton btnSync = new JButton("Sync");
		btnSync.setBounds(445, 20, 70, 25);
		issues.getContentPane().add(btnSync);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(120, 120, 400, 280);
		issues.getContentPane().add(scrollPane);
		table = new JTable(rowData, columnNames);
		scrollPane.setViewportView(table);
		table.setColumnSelectionAllowed(true);
		table.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null,
				null));
		table.setCellSelectionEnabled(true);

		final Label internetCheck = new Label("ONLINE");
		internetCheck.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		internetCheck.setBackground(Color.GREEN);
		internetCheck.setBounds(515, 22, 65, 20);
		internetCheck.setForeground(new Color(255,255,255));
		internetCheck.setAlignment(1);
		issues.getContentPane().add(internetCheck);

		ActionListener taskPerformer1 = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					Socket sock = new Socket();
					InetSocketAddress addr = new InetSocketAddress(
							"www.google.com", 80);
					sock.setSoTimeout(500);
					sock.connect(addr, 3000);

					internetCheck.setBackground(Color.GREEN);
					internetCheck.setText("ONLINE");
					btnSync.setEnabled(true);
					sock.close();

				} catch (Exception e) {
					internetCheck.setBackground(Color.RED);
					internetCheck.setText("OFFLINE");
					btnSync.setEnabled(false);
				}
			}
		};
		new Timer(delay, taskPerformer1).start();
		DefaultTableModel model = new DefaultTableModel(rowData, columnNames);
		Action view = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				issues.setVisible(false);
				articles(modelRow);
				// / ((DefaultTableModel)table.getModel()).removeRow(modelRow);
			}
		};

		ButtonColumn buttonColumn = new ButtonColumn(table, view, 3);

		JLabel lblIssues = new JLabel("Issues");
		lblIssues.setFont(new Font("Dialog", Font.BOLD, 28));
		lblIssues.setForeground(new Color(240, 255, 255));
		lblIssues.setBounds(120, 78, 120, 30);
		issues.getContentPane().add(lblIssues);
		
		JButton btnSettings = new JButton("Settings");
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				settings();
			}
		});
		btnSettings.setBackground(UIManager.getColor("Button.disabledToolBarBorderBackground"));
		btnSettings.setBounds(12, 20, 117, 25);
		issues.getContentPane().add(btnSettings);
		
		JButton btnApi = new JButton("API");
		btnApi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				api(true);
			}
		});
		btnApi.setBounds(120, 18, 117, 29);
		issues.getContentPane().add(btnApi);
		buttonColumn.setMnemonic(KeyEvent.VK_D);
	}
	public void articles(int issue_id) {
		articles = new JFrame();
		articles.setSize(640, 480);
		articles.getContentPane().setBackground(new Color(128, 128, 128));
		articles.setVisible(true);
		Object rowData[][] = {
				{ "Row1-Column1", "Row1-Column2", "View","Delete" },
				{ "Row2-Column1", "Row2-Column2", "View","Delete"  },
				{ "Row3-Column1", "Row3-Column2", "View","Delete"  },
				{ "Row4-Column1", "Row4-Column2", "View","Delete"  } };
		Object columnNames[] = { "Column One", "Column Two", "" , "" };
		articles.getContentPane().setLayout(null);

		final JButton btnSync = new JButton("Sync");
		btnSync.setBounds(445, 20, 70, 25);
		articles.getContentPane().add(btnSync);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(120, 120, 400, 280);
		articles.getContentPane().add(scrollPane);
		table = new JTable(rowData, columnNames);
		scrollPane.setViewportView(table);
		table.setColumnSelectionAllowed(true);
		table.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null,
				null));
		table.setCellSelectionEnabled(true);

		final Label internetCheck = new Label("  ONLINE");
		internetCheck.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		internetCheck.setBackground(Color.GREEN);
		internetCheck.setAlignment(1);
		internetCheck.setForeground(new Color(255,255,255));
		internetCheck.setBounds(515, 22, 65, 20);
		articles.getContentPane().add(internetCheck);

		ActionListener taskPerformer1 = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					Socket sock = new Socket();
					InetSocketAddress addr = new InetSocketAddress(
							"www.google.com", 80);
					sock.setSoTimeout(500);
					sock.connect(addr, 3000);

					internetCheck.setBackground(Color.GREEN);
					internetCheck.setText("ONLINE");
					btnSync.setEnabled(true);
					sock.close();

				} catch (Exception e) {
					internetCheck.setBackground(Color.RED);
					internetCheck.setText("OFFLINE");
					btnSync.setEnabled(false);
				}
			}
		};
		new Timer(delay, taskPerformer1).start();
		DefaultTableModel model = new DefaultTableModel(rowData, columnNames);
		Action delete = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				JOptionPane.showMessageDialog(null, "Deleted");
				// / ((DefaultTableModel)table.getModel()).removeRow(modelRow);
			}
		};
		Action view = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				JOptionPane.showMessageDialog(null, modelRow);
				// / ((DefaultTableModel)table.getModel()).removeRow(modelRow);
			}
		};

		ButtonColumn buttonColumn = new ButtonColumn(table, view, 2);
		ButtonColumn buttonColumn2 = new ButtonColumn(table, delete, 3);

		JLabel lblIssues = new JLabel("Articles");
		lblIssues.setFont(new Font("Dialog", Font.BOLD, 28));
		lblIssues.setForeground(new Color(240, 255, 255));
		lblIssues.setBounds(120, 78, 180, 30);
		articles.getContentPane().add(lblIssues);
		
		JLabel lblIssue = new JLabel("Issue:");
		lblIssue.setForeground(new Color(240, 255, 255));
		lblIssue.setFont(new Font("Dialog", Font.BOLD, 28));
		lblIssue.setBounds(29, 20, 94, 30);
		articles.getContentPane().add(lblIssue);
		
		JLabel lblIssueId = new JLabel("");
		lblIssueId.setForeground(new Color(240, 255, 255));
		lblIssueId.setFont(new Font("Dialog", Font.BOLD, 28));
		lblIssueId.setBounds(129, 20, 94, 30);
		lblIssueId.setText(Integer.toString(issue_id));
		articles.getContentPane().add(lblIssueId);
		
		JButton btnGoBack = new JButton("Go back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				articles.setVisible(false);
				if(issues==null){
					articles.dispose();
					issues();
				}else if (!issues.isVisible()){
					articles.dispose();
					issues();
				}else{
					articles.dispose();
					issues.setVisible(true);
				}
			}
		});
		btnGoBack.setBounds(167, 20, 117, 30);
		articles.getContentPane().add(btnGoBack);
		buttonColumn.setMnemonic(KeyEvent.VK_D);
		buttonColumn2.setMnemonic(KeyEvent.VK_D);
	}

	public Main() {
		login();
	}

	public static void main(String[] args) {
		new Main();
	}
}