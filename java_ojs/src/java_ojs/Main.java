package java_ojs;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import java.util.Date;
import java.awt.event.ActionListener;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Button;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import models.Issue;

import javax.swing.JOptionPane;
import java.awt.Label;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Panel;
import java.awt.SystemColor;

public class Main {
	JFrame login, api, issues, articles, settings;
	private JTextField access_key, api_url, username;
	private JTable issues_table;
	private int delay = 1000; // milliseconds
	private static String source_api = "";
	private static String source_access_key = "";
	private JPasswordField passwordField;
	private static HashMap<String, Boolean> list_settings;
	private static HashMap<Integer, Integer> list_issues;
	private static HashMap<Integer, JFrame> issue_screens;
	private static HashMap<Integer, HashMap<Integer, JFrame>> article_screens;
	private static ArrayList<String> setting_keys = new ArrayList<String>();
	private static Connection c = null;
	private static Statement stmt = null;
	private String api_insert_or_replace_statement = "INSERT OR REPLACE INTO API(URL,ACCESS_KEY) VALUES (?,?)";
	private String settings_insert_or_replace_statement = "INSERT OR REPLACE INTO SETTING(NAME,VALUE) VALUES (?,?)";
	private int width = 960;
	private int height = 600;
	private Boolean logged_in = true;
	private static int i_id = 1;

	/*
	 * Initial setup test
	 */
	public void database_save() {
		System.out.println("Saving database...");

		try {
			if (c != null && c.isClosed()) {
				c = DriverManager.getConnection("jdbc:sqlite:local_datatabse.db");
				stmt = c.createStatement();
			}
			PreparedStatement prep = c.prepareStatement(api_insert_or_replace_statement);
			prep.setString(1, source_api);
			prep.setString(2, source_access_key);
			prep.executeUpdate();
			for (int i = 0; i < list_settings.size(); i++) {
				PreparedStatement setting_prep = c.prepareStatement(settings_insert_or_replace_statement);
				setting_prep.setString(1, setting_keys.get(i));
				setting_prep.setBoolean(2, list_settings.get(setting_keys.get(i)));
				setting_prep.executeUpdate();
			}
			stmt.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Done.");
	}

	public static void populate_variables() {
		System.out.println("Retrieving data from local database");
		list_settings = new HashMap<String, Boolean>();
		list_issues = new HashMap<Integer, Integer>();
		issue_screens = new HashMap<Integer, JFrame>();
		article_screens = new HashMap<Integer, HashMap<Integer, JFrame>>();
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM API WHERE URL=" + "'api'" + ";");
			while (rs.next()) {
				source_api = rs.getString("url");
				source_access_key = rs.getString("access_key");
				System.out.println("URL: " + source_api);
				System.out.println("ACCESS KEY: " + source_access_key);
			}
			rs.close();
			rs = stmt.executeQuery("SELECT * FROM SETTING ;");
			while (rs.next()) {
				String name = rs.getString("name");
				Boolean value = rs.getBoolean("value");
				list_settings.put(name, value);
				setting_keys.add(name);
				System.out.println("Setting - " + name + " : " + value.toString());
			}
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Done.");
	}

	public static void database_setup() {

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:local_datatabse.db");
			stmt = c.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS SETTING" + "(NAME CHAR(100) PRIMARY KEY NOT NULL,"
					+ " VALUE BOOLEAN DEFAULT FALSE)";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS API" + "(URL CHAR(250) PRIMARY KEY NOT NULL,"
					+ " ACCESS_KEY CHAR(100) NOT NULL)";
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
	}

	public void login(final String returning_view) {
		if (!logged_in) {
			int width_small = 0;
			int height_small = 0;
			if (width >= 640) {
				width_small = (int) (960 - (960 * (37.5 / 100)));
			} else {
				width_small = (int) (640 - (640 * (37.5 / 100)));
			}
			height_small = (int) (480 - (480 * (5 / 100)));

			login = new JFrame();
			login.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					// database_save();
				}
			});
			login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			login.getContentPane().setForeground(Color.WHITE);
			login.getContentPane().setBackground(new Color(128, 128, 128));

			login.setSize(width_small, height_small);// 400 width and 500 height
			login.getContentPane().setLayout(null);// using no layout managers
			JLabel lblNewLabel = new JLabel("TORU");

			lblNewLabel.setForeground(new Color(255, 250, 250));
			lblNewLabel.setBackground(new Color(230, 230, 250));
			lblNewLabel.setFont(new Font("Trattatello", Font.BOLD, 24));
			lblNewLabel.setToolTipText("Welcome\n");
			lblNewLabel.setBounds((width_small / 2) - 34, 15, 70, 25);
			login.getContentPane().add(lblNewLabel);
			username = new JTextField();
			username.setBounds(80, 220, width_small - 161, 26);
			login.getContentPane().add(username);
			username.setColumns(10);
			JLabel lblUsername = new JLabel("Username");
			lblUsername.setForeground(new Color(245, 255, 250));
			lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
			lblUsername.setBounds(80, 200, width_small - 161, 16);
			login.getContentPane().add(lblUsername);
			JPanel title_background = new JPanel();
			title_background.setBackground(new Color(0, 0, 0));
			title_background.setBounds(-17, 0, width - 67, 54);
			login.getContentPane().add(title_background);
			JLabel lblPassword = new JLabel("Password");
			lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
			lblPassword.setForeground(new Color(245, 255, 250));
			lblPassword.setBounds(80, 260, width_small - 161, 16);
			login.getContentPane().add(lblPassword);
			passwordField = new JPasswordField();
			passwordField.setBounds(80, 280, width_small - 161, 26);
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
						logged_in = true;
						login.setVisible(false);
						login.dispose();
						if (source_api.compareTo("") == 0 && source_access_key.compareTo("") == 0) {
							api(false);
						} else {
							System.out.println(returning_view);
							if (returning_view.compareTo("api") == 0) {
								api(true);
							} else if (returning_view.compareTo("settings") == 0) {
								settings();
							} else {
								dashboard();
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "Wrong username or password");
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
						if (source_api.compareTo("") == 0 && source_access_key.compareTo("") == 0) {
							api(false);
						} else {
							System.out.println(returning_view);
							if (returning_view.compareTo("api") == 0) {
								api(true);
							} else if (returning_view.compareTo("settings") == 0) {
								settings();
							} else {
								dashboard();
							}
						}

					} else {
						JOptionPane.showMessageDialog(null, "Wrong username or password");
					}

				}
			});

			btnLogin.setBounds(width_small / 3, 340, width_small / 3, 29);
			login.getContentPane().add(btnLogin);

			final JButton btnSync1 = new JButton("Sync");
			btnSync1.setBounds(width_small - 155, 68, 70, 25);
			login.getContentPane().add(btnSync1);

			JLabel lblLogIn = new JLabel("Log in");
			lblLogIn.setForeground(new Color(224, 255, 255));
			lblLogIn.setFont(new Font("URW Gothic L", Font.BOLD, 24));
			lblLogIn.setHorizontalAlignment(SwingConstants.CENTER);
			lblLogIn.setBounds((width_small / 2) - 40, 150, 80, 30);
			login.getContentPane().add(lblLogIn);

			final Label internetCheck = new Label("   ONLINE");
			internetCheck.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
			internetCheck.setBackground(Color.GREEN);
			internetCheck.setForeground(new Color(255, 255, 255));
			internetCheck.setAlignment(1);
			internetCheck.setBounds(width_small - 85, 70, 65, 22);
			login.getContentPane().add(internetCheck);

			Panel panel = new Panel();
			panel.setBackground(new Color(204, 51, 51));
			panel.setBounds(0, 54, width_small, 5);
			login.getContentPane().add(panel);

			ActionListener taskPerformer1 = new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					try {
						Socket sock = new Socket();
						InetSocketAddress addr = new InetSocketAddress("www.google.com", 80);
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
		} else {
			if (source_api.compareTo("") == 0 && source_access_key.compareTo("") == 0) {
				api(false);
			} else {
				System.out.println(returning_view);
				if (returning_view.compareTo("api") == 0) {
					api(true);
				} else if (returning_view.compareTo("settings") == 0) {
					settings();
				} else {
					dashboard();
				}
			}
		}
	}

	public void settings() {
		if (logged_in) {
			if (settings == null || !settings.isVisible()) {
				int width_small = (int) (width - (width * (37.5 / 100)));
				int height_small = (int) (height - (height * (5 / 100)));
				settings = new JFrame();
				settings.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				settings.getContentPane().setBackground(new Color(128, 128, 128));
				settings.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						// database_save();
					}
				});
				settings.setSize(width_small, height_small);// 400 width and 500
															// height
				settings.getContentPane().setLayout(null);
				JLabel lblNewLabel = new JLabel("TORU");
				lblNewLabel.setForeground(new Color(255, 250, 250));
				lblNewLabel.setBackground(new Color(230, 230, 250));
				lblNewLabel.setFont(new Font("Trattatello", Font.BOLD, 24));
				lblNewLabel.setToolTipText("Welcome\n");

				lblNewLabel.setBounds((width_small / 2) - 34, 15, 70, 25);
				settings.getContentPane().add(lblNewLabel);
				JPanel title_background = new JPanel();
				title_background.setBackground(new Color(0, 0, 0));
				title_background.setBounds(-17, 0, width_small + 33, 54);
				settings.getContentPane().add(title_background);
				final JButton btnSync1 = new JButton("Sync");
				btnSync1.setBounds(width_small - 155, 68, 70, 25);
				settings.getContentPane().add(btnSync1);

				JLabel lblSettings = new JLabel("Settings");
				lblSettings.setBackground(new Color(128, 0, 128));
				lblSettings.setOpaque(true);
				lblSettings.setForeground(new Color(255, 255, 255));
				lblSettings.setFont(new Font("URW Gothic L", Font.BOLD, 24));
				lblSettings.setHorizontalAlignment(SwingConstants.CENTER);
				lblSettings.setBounds((width_small / 2) - 83, 128, 160, 30);
				settings.getContentPane().add(lblSettings);
				JScrollPane scrollSettings = new JScrollPane();
				scrollSettings.setBounds(40, 180, width_small - 80, height_small - 300);

				JPanel panelSettings = new JPanel();

				panelSettings.setLayout(null);
				panelSettings.setAutoscrolls(true);
				int y = 10;
				int settings_height = 210 + 30 * (setting_keys.size() - 8);
				panelSettings.setPreferredSize(new Dimension(width_small - 80, settings_height));
				JScrollPane scrollFrame = new JScrollPane(panelSettings);
				panelSettings.setAutoscrolls(true);
				scrollFrame.setPreferredSize(new Dimension(320, 200));
				scrollFrame.setBounds(40, 180, width_small - 80, height_small - 300);
				// scrollSettings.setViewportView(scrollFrame);
				settings.getContentPane().add(scrollFrame);
				for (int i = 0; i < setting_keys.size(); i++) {
					final JCheckBox chckbxSampleSetting = new JCheckBox(setting_keys.get(i));
					final int s = i;
					chckbxSampleSetting.setName(Integer.toString(i));
					chckbxSampleSetting.setBounds(81, y, 150, 23);
					chckbxSampleSetting.setSelected(list_settings.get(setting_keys.get(i)));
					chckbxSampleSetting.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							list_settings.remove(setting_keys.get(s));
							list_settings.put(setting_keys.get(s), chckbxSampleSetting.isSelected());
						}
					});
					y = y + 25;
					panelSettings.add(chckbxSampleSetting);
				}
				JButton btnSave = new JButton("Save");
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						settings.setVisible(false);
						settings.dispose();
						if (issues == null) {
							dashboard();
						} else if (!issues.isVisible()) {
							issues.setVisible(true);
						}
					}
				});
				y = y + 10;
				btnSave.setBounds((width_small / 2) - 60, height_small - 100, 100, 25);
				settings.getContentPane().add(btnSave);
				final Label internetCheck = new Label("  ONLINE");
				internetCheck.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
				internetCheck.setBackground(Color.GREEN);
				internetCheck.setAlignment(1);
				internetCheck.setForeground(new Color(255, 255, 255));
				internetCheck.setBounds(width_small - 85, 70, 65, 22);
				settings.getContentPane().add(internetCheck);

				Panel panel = new Panel();
				panel.setBackground(new Color(128, 0, 128));
				panel.setBounds(0, 120, width_small, 40);
				settings.getContentPane().add(panel);

				Panel panel_1 = new Panel();
				panel_1.setBackground(new Color(102, 0, 102));
				panel_1.setBounds(0, 160, width_small, 5);
				settings.getContentPane().add(panel_1);

				Panel panel_2 = new Panel();
				panel_2.setBackground(new Color(204, 51, 51));
				panel_2.setBounds(0, 54, width_small, 5);
				settings.getContentPane().add(panel_2);

				ActionListener taskPerformer1 = new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						try {
							Socket sock = new Socket();
							InetSocketAddress addr = new InetSocketAddress("www.google.com", 80);
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
		} else {
			login("settings");
		}
	}

	public void api(final boolean edit) {
		if (logged_in) {
			if (api == null || !api.isVisible()) {
				int width_small = 0;
				int height_small = 0;
				if (height >= 480 && width >= 640) {
					width_small = (int) (900 - (900 * (37.5 / 100)));
				} else {
					width_small = (int) (640 - (640 * (37.5 / 100)));
				}

				height_small = (int) (480 - (480 * (5 / 100)));
				api = new JFrame();
				api.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				api.getContentPane().setBackground(new Color(128, 128, 128));
				api.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						// database_save();
					}
				});
				api.setSize(width_small, height_small);// 400 width and 500
														// height
				api.getContentPane().setLayout(null);// using no layout managers
				JLabel lblNewLabel = new JLabel("TORU");
				lblNewLabel.setForeground(new Color(255, 250, 250));
				lblNewLabel.setBackground(new Color(230, 230, 250));
				lblNewLabel.setFont(new Font("Trattatello", Font.BOLD, 24));
				lblNewLabel.setToolTipText("Welcome\n");

				lblNewLabel.setBounds((width_small / 2) - 34, 15, 70, 25);
				api.getContentPane().add(lblNewLabel);
				api_url = new JTextField();
				api_url.setBounds(100, 218, width_small - 200, 26);
				api_url.setText(source_api);
				api.getContentPane().add(api_url);
				api_url.setColumns(10);

				JLabel lblApi = new JLabel("API URL");
				lblApi.setForeground(new Color(245, 255, 250));
				lblApi.setHorizontalAlignment(SwingConstants.CENTER);
				lblApi.setBounds(74, 200, width_small - 151, 16);
				api.getContentPane().add(lblApi);
				JPanel title_background = new JPanel();
				title_background.setBackground(new Color(0, 0, 0));
				title_background.setBounds(-17, 0, width_small + 33, 54);
				api.getContentPane().add(title_background);

				access_key = new JTextField();
				access_key.setColumns(10);
				access_key.setText(source_access_key);
				access_key.setBounds(100, 270, width_small - 200, 26);
				api.getContentPane().add(access_key);
				JLabel lblAccessKey = new JLabel("Access Key");
				lblAccessKey.setHorizontalAlignment(SwingConstants.CENTER);
				lblAccessKey.setForeground(new Color(245, 255, 250));
				lblAccessKey.setBounds(80, 250, width_small - 161, 16);
				api.getContentPane().add(lblAccessKey);

				JButton btnSubmit = new JButton("Submit");
				if (edit) {
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
							database_save();
							if (!edit) {
								dashboard();
							}
						} else {
							JOptionPane.showMessageDialog(null, "Wrong access key or API url");
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
							dashboard();
							source_api = url;
							source_access_key = key;

						} else {
							JOptionPane.showMessageDialog(null, "Wrong access key or API url");
						}

					}
				});
				if (height_small - 150 > 300) {
					btnSubmit.setBounds(((width_small / 3) * 2) / 2, height_small - 150, width_small / 3, 29);
				} else {
					btnSubmit.setBounds(((width_small / 3) * 2) / 2, 310, width_small / 3, 29);
				}

				api.getContentPane().add(btnSubmit);

				final JButton btnSync1 = new JButton("Sync");
				btnSync1.setBounds(width_small - 150, 68, 70, 24);
				api.getContentPane().add(btnSync1);

				JLabel lblApiInformation = new JLabel("API Information");
				lblApiInformation.setBackground(new Color(51, 102, 204));
				lblApiInformation.setHorizontalAlignment(SwingConstants.CENTER);
				lblApiInformation.setForeground(new Color(255, 255, 255));
				lblApiInformation.setFont(new Font("Dialog", Font.BOLD, 20));
				lblApiInformation.setBounds((width_small / 2) - 145, 108, 309, 40);
				lblApiInformation.setOpaque(true);
				api.getContentPane().add(lblApiInformation);
				final Label internetCheck = new Label("  ONLINE");
				internetCheck.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
				internetCheck.setBackground(Color.GREEN);
				internetCheck.setBounds(width_small - 80, 70, 65, 22);
				internetCheck.setForeground(new Color(255, 255, 255));
				internetCheck.setAlignment(1);
				api.getContentPane().add(internetCheck);

				Panel panel = new Panel();
				panel.setBackground(new Color(204, 51, 51));
				panel.setBounds(0, 54, width_small, 5);
				api.getContentPane().add(panel);

				ActionListener taskPerformer1 = new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						try {
							Socket sock = new Socket();
							InetSocketAddress addr = new InetSocketAddress("www.google.com", 80);
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

				Panel panel_2 = new Panel();
				panel_2.setBackground(new Color(51, 51, 204));
				panel_2.setBounds(0, 150, width_small, 5);
				api.getContentPane().add(panel_2);
				Panel panel_1 = new Panel();
				panel_1.setBackground(new Color(51, 102, 204));
				panel_1.setBounds(0, 105, width_small, 45);
				api.getContentPane().add(panel_1);
			}
		} else {
			login("api");
		}
	}

	public void dashboard() {
		if (logged_in) {

			if (issues == null || !issues.isVisible()) {
				Date date = new Date();
				Issue issue = new Issue(i_id, "title", 1, 1, 2015, "title", 1, 1, 2015, date);
				// Issue Table [title, volume, number, year, show_title,
				// show_volume,
				// show_number, show_year, date_published]
				issues = new JFrame();
				issues.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				list_issues.put(i_id, 1);

				issue_screens.put(1, new JFrame());
				if (height >= 480 && width >= 640) {
					issues.setSize(width, height);
				} else {
					width = 640;
					height = 480;
					issues.setSize(640, 480);
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				issues.getContentPane().setBackground(new Color(105, 105, 105));
				issues.setVisible(true);
				issues.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						// database_save();
					}
				});
				Object rowData[][] = { { 1, "title", 1, 1, 2015, sdf.format(date), "View","Edit", "Delete" } };
				Object columnNames[] = { "ID", "Title", "Volume", "Number", "Year", "Date Published","", "", "" };
				issues.getContentPane().setLayout(null);

				final JButton btnSync = new JButton("Sync");
				btnSync.setBounds(width - 155, 21, 70, 24);
				issues.getContentPane().add(btnSync);

				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				scrollPane.setBounds(20, 140, width - 40, height - 285);
				issues.getContentPane().add(scrollPane);

				DefaultTableModel dtm = new DefaultTableModel(rowData, columnNames);

				issues_table = new JTable(dtm){
					//**** Source: http://stackoverflow.com/questions/9467093/how-to-add-a-tooltip-to-a-cell-in-a-jtable ****//
		            //Implement table cell tool tips.           
		            public String getToolTipText(MouseEvent e) {
		                String tip = null;
		                java.awt.Point p = e.getPoint();
		                int rowIndex = rowAtPoint(p);
		                int colIndex = columnAtPoint(p);

		                try {
		                    tip = getValueAt(rowIndex, colIndex).toString();
		                } catch (RuntimeException e1) {
		                    //catch null pointer exception if mouse is over an empty line
		                }

		                return tip;
		            }
		        };
				scrollPane.setViewportView(issues_table);
				issues_table.setColumnSelectionAllowed(true);
				issues_table.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				issues_table.setCellSelectionEnabled(true);
				issues_table.setRowHeight(23);
				issues_table.setAutoCreateRowSorter(true);

				final Label internetCheck = new Label("ONLINE");
				internetCheck.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
				internetCheck.setBackground(Color.GREEN);
				internetCheck.setBounds(width - 85, 22, 65, 22);
				internetCheck.setForeground(new Color(255, 255, 255));
				internetCheck.setAlignment(1);
				issues.getContentPane().add(internetCheck);

				ActionListener taskPerformer1 = new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						try {
							Socket sock = new Socket();
							InetSocketAddress addr = new InetSocketAddress("www.google.com", 80);
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
						// JOptionPane.showMessageDialog(null, "Deleted");
						int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this issue?",
								"Delete ?", JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {

							((DefaultTableModel) table.getModel()).removeRow(modelRow);
							table.repaint();
						}

						// /
						// ((DefaultTableModel)table.getModel()).removeRow(modelRow);
					}
				};
				Action view = new AbstractAction() {
					public void actionPerformed(ActionEvent e) {
						JTable table = (JTable) e.getSource();
						int modelRow = Integer.valueOf(e.getActionCommand());
						int issue_row = table.getSelectedRow();
						int selectedColumnIndex = 0;
						Object selectedObject = (Object) table.getModel().getValueAt(issue_row, selectedColumnIndex);
						int selected_issue = (int) selectedObject;
						if (!issue_screens.get(selected_issue).isVisible()) {
							issue(selected_issue);
						}
						// /
						// ((DefaultTableModel)table.getModel()).removeRow(modelRow);
					}
				};
				Action edit = new AbstractAction() {
					public void actionPerformed(ActionEvent e) {
						JTable table = (JTable) e.getSource();
						int modelRow = Integer.valueOf(e.getActionCommand());
						int issue_row = table.getSelectedRow();
						int selectedColumnIndex = 0;
						Object selectedObject = (Object) table.getModel().getValueAt(issue_row, selectedColumnIndex);
						int selected_issue = (int) selectedObject;
						if (!issue_screens.get(selected_issue).isVisible()) {
							issue(selected_issue);
						}
						// /
						// ((DefaultTableModel)table.getModel()).removeRow(modelRow);
					}
				};
				ButtonColumn buttonColumn = new ButtonColumn(issues_table, view, 6);
				ButtonColumn buttonColumn2 = new ButtonColumn(issues_table, edit, 7);
				ButtonColumn buttonColumn3 = new ButtonColumn(issues_table, delete, 8);

				JLabel lblIssues = new JLabel("Issues");
				lblIssues.setBackground(new Color(220, 20, 60));
				lblIssues.setOpaque(true);
				lblIssues.setFont(new Font("Dialog", Font.BOLD, 28));
				lblIssues.setForeground(new Color(240, 255, 255));
				lblIssues.setBounds(40, 60, 120, 30);
				issues.getContentPane().add(lblIssues);

				JButton btnSettings = new JButton("Settings");
				btnSettings.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						settings();
					}
				});
				btnSettings.setBounds(15, 20, 90, 29);
				issues.getContentPane().add(btnSettings);

				JButton btnApi = new JButton("API");
				btnApi.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						api(true);
					}
				});
				btnApi.setBounds(103, 20, 90, 29);
				issues.getContentPane().add(btnApi);
				ImageIcon db_icon = new ImageIcon("src/lib/db_xxs.png");
				JButton btnSaveData = new JButton(db_icon);
				btnSaveData.setFont(new Font("Dialog", Font.BOLD, 24));
				btnSaveData.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						database_save();
					}
				});
				btnSaveData.setBounds(26, height - 117, 70, 40);
				issues.getContentPane().add(btnSaveData);

				JLabel lblUpdateDb = new JLabel("Update");
				lblUpdateDb.setForeground(Color.WHITE);
				lblUpdateDb.setHorizontalAlignment(SwingConstants.CENTER);
				lblUpdateDb.setBounds(26, height - 132, 70, 15);
				issues.getContentPane().add(lblUpdateDb);

				Panel footer_border = new Panel();
				footer_border.setBackground(new Color(220, 20, 60));
				footer_border.setBounds(0, height - 74, width, 10);
				issues.getContentPane().add(footer_border);

				Panel panel_1 = new Panel();
				panel_1.setBackground(new Color(128, 0, 0));
				panel_1.setBounds(0, 95, width, 5);
				issues.getContentPane().add(panel_1);

				Panel footer = new Panel();
				footer.setBackground(new Color(178, 34, 34));
				footer.setBounds(0, height - 74, width, 120);
				issues.getContentPane().add(footer);

				Panel panel = new Panel();
				panel.setBackground(new Color(220, 20, 60));
				panel.setBounds(0, 55, width, 40);
				issues.getContentPane().add(panel);

				JButton btnAdd = new JButton("Add");
				btnAdd.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						i_id = i_id + 1;
						// JOptionPane.showMessageDialog(null, "Deleted");
						Date date = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
						Issue issue = new Issue(i_id, "title", 1, 1, 2015, "title", 1, 1, 2015, date);

						list_issues.put(i_id, 1);
						issue_screens.put(i_id, new JFrame());
						Object[] new_row = { i_id, "title", 1, 1, 2015, sdf.format(date), "View","Edit", "Delete" };

						((DefaultTableModel) issues_table.getModel()).addRow(new_row);
						issues_table.repaint();
					}
				});
				btnAdd.setBounds(width - 150, 112, 117, 25);
				issues.getContentPane().add(btnAdd);
				buttonColumn.setMnemonic(KeyEvent.VK_D);
				buttonColumn2.setMnemonic(KeyEvent.VK_D);
				buttonColumn3.setMnemonic(KeyEvent.VK_D);
			}
		} else {
			login("dashboard");
		}

	}

	public void issue(final int issue_id) {
		if (logged_in) {
			if (!issue_screens.get(issue_id).isVisible()) {
				articles = new JFrame();
				articles.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				if (height >= 480 && width >= 640) {
					articles.setSize(width, height);
				} else {
					width = 640;
					height = 480;
					articles.setSize(640, 480);
				}
				HashMap<Integer, JFrame> issue_articles = new HashMap<Integer, JFrame>();
				issue_articles.put(1, new JFrame());
				article_screens.put(1, issue_articles);
				articles.getContentPane().setBackground(new Color(128, 128, 128));
				articles.setVisible(true);
				articles.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						// database_save();
					}
				});
				Date current = new Date();

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				Object rowData[][] = {
						{ 1, issue_id, 1, "title", 1, "abstract", sdf.format(current), "View","Edit", "Delete" }, };

				Object columnNames[] = { "ID", "Issue", "Section", "Title", "Pages", "Abstract", "Date Published", "","",
						"" };

				articles.getContentPane().setLayout(null);

				final JButton btnSync = new JButton("Sync");
				btnSync.setBounds(width - 155, 21, 70, 24);
				articles.getContentPane().add(btnSync);

				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				scrollPane.setBounds(15, height / 16 * 7, width - 30, (height - 130) - (height / 16 * 7) - 10);
				articles.getContentPane().add(scrollPane);

				DefaultTableModel dtm = new DefaultTableModel(rowData, columnNames);

				final JTable article_table = new JTable(dtm){
					//**** Source: http://stackoverflow.com/questions/9467093/how-to-add-a-tooltip-to-a-cell-in-a-jtable ****//
		            //Implement table cell tool tips.           
		            public String getToolTipText(MouseEvent e) {
		                String tip = null;
		                java.awt.Point p = e.getPoint();
		                int rowIndex = rowAtPoint(p);
		                int colIndex = columnAtPoint(p);

		                try {
		                    tip = getValueAt(rowIndex, colIndex).toString();
		                } catch (RuntimeException e1) {
		                    //catch null pointer exception if mouse is over an empty line
		                }

		                return tip;
		            }
		        };
				article_table.setAutoCreateRowSorter(true);
				scrollPane.setViewportView(article_table);
				article_table.setColumnSelectionAllowed(true);
				article_table.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				article_table.setCellSelectionEnabled(true);
				article_table.setRowHeight(23);
				final Label internetCheck = new Label("  ONLINE");
				internetCheck.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
				internetCheck.setBackground(Color.GREEN);
				internetCheck.setAlignment(1);
				internetCheck.setForeground(new Color(255, 255, 255));
				internetCheck.setBounds(width - 85, 22, 65, 22);
				articles.getContentPane().add(internetCheck);

				ActionListener taskPerformer1 = new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						try {
							Socket sock = new Socket();
							InetSocketAddress addr = new InetSocketAddress("www.google.com", 80);
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
						// JOptionPane.showMessageDialog(null, "Deleted");
						int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this article?",
								"Delete ?", JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {

							((DefaultTableModel) table.getModel()).removeRow(modelRow);
							table.repaint();
						}

						// /
						// ((DefaultTableModel)table.getModel()).removeRow(modelRow);
					}
				};
				Action view = new AbstractAction() {
					public void actionPerformed(ActionEvent e) {
						JTable table = (JTable) e.getSource();

						int modelRow = Integer.valueOf(e.getActionCommand());
						int article_row = table.getSelectedRow();
						int selectedColumnIndex = 0;
						Object selectedObject = (Object) table.getModel().getValueAt(article_row, selectedColumnIndex);
						int selected_article = (int) selectedObject;
						article(issue_id, selected_article);
						// /
						// ((DefaultTableModel)table.getModel()).removeRow(modelRow);
					}
				};
				Action edit = new AbstractAction() {
					public void actionPerformed(ActionEvent e) {
						JTable table = (JTable) e.getSource();

						int modelRow = Integer.valueOf(e.getActionCommand());
						int article_row = table.getSelectedRow();
						int selectedColumnIndex = 0;
						Object selectedObject = (Object) table.getModel().getValueAt(article_row, selectedColumnIndex);
						int selected_article = (int) selectedObject;
						article(issue_id, selected_article);
						// /
						// ((DefaultTableModel)table.getModel()).removeRow(modelRow);
					}
				};
				ButtonColumn buttonColumn = new ButtonColumn(article_table, view, 7);
				ButtonColumn buttonColumn2 = new ButtonColumn(article_table, edit, 8);
				ButtonColumn buttonColumn3 = new ButtonColumn(article_table, delete,9);

				JLabel lblArticles = new JLabel("Articles");
				lblArticles.setBackground(new Color(128, 128, 128));
				lblArticles.setFont(new Font("Dialog", Font.BOLD, 26));
				lblArticles.setForeground(new Color(240, 255, 255));
				lblArticles.setBounds(25, height / 16 * 7 - 32, 180, 30);
				lblArticles.setOpaque(true);
				articles.getContentPane().add(lblArticles);

				JLabel lblIssue = new JLabel("Issue:");
				lblIssue.setBackground(new Color(0, 139, 139));
				lblIssue.setForeground(new Color(240, 255, 255));
				lblIssue.setFont(new Font("Dialog", Font.BOLD, 28));
				lblIssue.setBounds(40, 60, 94, 30);
				lblIssue.setOpaque(true);
				articles.getContentPane().add(lblIssue);

				final JLabel lblIssueId = new JLabel("");
				lblIssueId.setBackground(new Color(0, 139, 139));
				lblIssueId.setForeground(new Color(240, 255, 255));
				lblIssueId.setFont(new Font("Dialog", Font.BOLD, 28));
				lblIssueId.setBounds(136, 60, 50, 30);
				lblIssueId.setText(Integer.toString(issue_id));
				lblIssueId.setOpaque(true);
				articles.getContentPane().add(lblIssueId);

				JButton btnClose = new JButton("Close");
				btnClose.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						articles.setVisible(false);
						if (issues == null) {
							articles.dispose();
							dashboard();
						} else if (!issues.isVisible()) {
							articles.dispose();
							issues.setVisible(true);
						} else {
							articles.dispose();
						}
					}
				});
				btnClose.setBounds(15, 20, 100, 29);
				articles.getContentPane().add(btnClose);
				buttonColumn.setMnemonic(KeyEvent.VK_D);
				buttonColumn2.setMnemonic(KeyEvent.VK_D);
				buttonColumn3.setMnemonic(KeyEvent.VK_D);
				ImageIcon db_icon = new ImageIcon("src/lib/db_xxs.png");
				JButton btnSaveData = new JButton(db_icon);
				btnSaveData.setFont(new Font("Dialog", Font.BOLD, 24));
				btnSaveData.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						database_save();
					}
				});
				btnSaveData.setBounds(26, height - 117, 70, 40);
				articles.getContentPane().add(btnSaveData);
				JLabel lblUpdateDb = new JLabel("Update");
				lblUpdateDb.setForeground(Color.WHITE);
				lblUpdateDb.setHorizontalAlignment(SwingConstants.CENTER);
				lblUpdateDb.setBounds(26, height - 132, 70, 15);
				articles.getContentPane().add(lblUpdateDb);
				Panel footer_border = new Panel();
				footer_border.setBackground(new Color(0, 139, 139));
				footer_border.setBounds(0, height - 74, width, 10);
				articles.getContentPane().add(footer_border);

				Panel footer = new Panel();
				footer.setBackground(new Color(0, 128, 128));
				footer.setBounds(0, height - 74, width, 120);
				articles.getContentPane().add(footer);

				Panel panel = new Panel();
				panel.setBackground(new Color(0, 139, 139));
				panel.setBounds(0, 55, width, 40);
				articles.getContentPane().add(panel);

				Panel panel_1 = new Panel();
				panel_1.setBackground(new Color(47, 79, 79));
				panel_1.setBounds(0, 95, width, 5);
				articles.getContentPane().add(panel_1);
				JButton btnAdd = new JButton("Add");
				btnAdd.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int issue_id = Integer.parseInt(lblIssueId.getText());
						int a_id = list_issues.get(issue_id) + 1;
						list_issues.replace(issue_id, a_id);
						Date current = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
						Object[] new_row = { a_id, issue_id, 1, "title", 1, "abstract", sdf.format(current), "View","Edit",
								"Delete" };
						HashMap<Integer, JFrame> issue_articles = article_screens.get(issue_id);
						issue_articles.put(a_id, new JFrame());
						article_screens.put(issue_id, issue_articles);
						((DefaultTableModel) article_table.getModel()).addRow(new_row);
						article_table.repaint();
					}
				});
				btnAdd.setBounds(width - 150, height / 16 * 7 - 27, 117, 25);
				articles.getContentPane().add(btnAdd);
				issue_screens.put(i_id, articles);

				JPanel panel3 = new JPanel();
				panel3.setBackground(SystemColor.window);
				panel3.setBounds(20, 108, width - 40, (height / 16) * 2);
				articles.getContentPane().add(panel3);
				panel3.setLayout(null);
				panel3.setAutoscrolls(true);
				int space = 0;

				panel3.setPreferredSize(new Dimension(width, height / 8));
				JScrollPane abstractSection = new JScrollPane(panel3);
				panel3.setAutoscrolls(true);
				/*
				 * JLabel lblTitle = new JLabel("Title:");
				 * lblTitle.setBounds(space+20, 10, 35, 16);
				 * panel3.add(lblTitle); JLabel lblTitleText = new
				 * JLabel("Abstract"); lblTitleText.setBounds(space+60, 10, 61,
				 * 16); panel3.add(lblTitleText);
				 * 
				 * JLabel lblVolume = new JLabel("Volume:");
				 * lblVolume.setBounds(space+145, 10, 50, 16);
				 * panel3.add(lblVolume); JLabel lblVolumeText = new
				 * JLabel("123"); lblVolumeText.setBounds(space+200, 10, 25,
				 * 16); panel3.add(lblVolumeText);
				 * 
				 * JLabel lblNumber= new JLabel("Number:");
				 * lblNumber.setBounds(space+249, 10, 55, 16);
				 * panel3.add(lblNumber); JLabel lblNumberText = new
				 * JLabel("1"); lblNumberText.setBounds(space+310, 10, 25, 16);
				 * panel3.add(lblNumberText);
				 * 
				 * JLabel lblYear= new JLabel("Year:");
				 * lblYear.setBounds(space+350, 10, 40, 16);
				 * panel3.add(lblYear); JLabel lblYearText = new JLabel("2015");
				 * lblYearText.setBounds(space+390, 10, 35, 16);
				 * panel3.add(lblYearText);
				 * 
				 * JLabel lblDate= new JLabel("Date Published:");
				 * lblDate.setBounds(space+449, 10, 100, 16);
				 * panel3.add(lblDate); JLabel lblDateText = new
				 * JLabel("2015/11/20"); lblDateText.setBounds(space+550, 10,
				 * 80, 16); panel3.add(lblDateText);
				 */
				Date date = new Date();
				Object issue_rowData[][] = { { 1, "title", 1, 1, 2015, sdf.format(date) } };
				Object issue_columnNames[] = { "ID", "Title", "Volume", "Number", "Year", "Date Published" };

				DefaultTableModel issue_dtm = new DefaultTableModel(issue_rowData, issue_columnNames);

				final JTable issuedetails = new JTable(issue_dtm){
					//**** Source: http://stackoverflow.com/questions/9467093/how-to-add-a-tooltip-to-a-cell-in-a-jtable ****//
		            //Implement table cell tool tips.           
		            public String getToolTipText(MouseEvent e) {
		                String tip = null;
		                java.awt.Point p = e.getPoint();
		                int rowIndex = rowAtPoint(p);
		                int colIndex = columnAtPoint(p);

		                try {
		                    tip = getValueAt(rowIndex, colIndex).toString();
		                } catch (RuntimeException e1) {
		                    //catch null pointer exception if mouse is over an empty line
		                }

		                return tip;
		            }
		        };
				issuedetails.setAutoCreateRowSorter(true);
				issuedetails.setColumnSelectionAllowed(true);
				issuedetails.setBounds(20, 15, width, 20);
				issuedetails.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				issuedetails.setCellSelectionEnabled(true);
				issuedetails.setRowHeight(23);
				issuedetails.setEnabled(false);
				abstractSection.setViewportView(issuedetails);
				abstractSection.setPreferredSize(new Dimension(320, 205));
				// (int)((height/32)*5.5)
				abstractSection.setBounds(20, height / 16 * 7 - 106, width - 40, 50);
				// scrollSettings.setViewportView(scrollFrame);
				articles.getContentPane().add(abstractSection);

				JLabel lblIssueDetails = new JLabel("Details");
				lblIssueDetails.setBackground(new Color(128, 128, 128));
				lblIssueDetails.setFont(new Font("Dialog", Font.BOLD, 26));
				lblIssueDetails.setForeground(new Color(240, 255, 255));
				lblIssueDetails.setBounds((width - 95) / 2, 118, 95, 30);
				lblIssueDetails.setOpaque(true);
				articles.getContentPane().add(lblIssueDetails);
			}

		} else {
			login("dashboard");
		}
	}

	public void article(final int issue_id, int article_id) {
		if (logged_in) {
			if (article_screens.containsKey(issue_id) && article_screens.get(issue_id).containsKey(article_id)
					&& !article_screens.get(issue_id).get(article_id).isVisible()) {
				int width_small = 0;
				int height_small = 0;
				if (height >= 768 && width >= 640) {
					width_small = (int) (width - (width * (37.5 / 100)));
					height_small = (int) (height - (height * (5 / 100)));
				} else {
					width_small = (int) (640 + (640 * (37.5 / 100)));
					height_small = (int) (768 - (768 * (5 / 100)));
				}
				final JFrame article = new JFrame();
				article.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				// article.setSize(width_small, height_small);
				article.setSize(width_small, height_small);
				article.getContentPane().setBackground(new Color(128, 128, 128));
				article.setVisible(true);
				article.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						// database_save();
					}
				});
				Object rowData[][] = { { article_id, "Row1-Column1", "Row1-Column2", "View", "Delete" }, };

				Object columnNames[] = { "ID", "Issue", "Section", "Title", "Pages", "Abstract", "Date Published", "",
						"" };
				article.getContentPane().setLayout(null);

				JLabel lblArticleDetails = new JLabel("Article Details");
				lblArticleDetails.setHorizontalAlignment(SwingConstants.CENTER);
				lblArticleDetails.setFont(new Font("Dialog", Font.BOLD, 20));
				lblArticleDetails.setForeground(new Color(255, 255, 255));
				lblArticleDetails.setBackground(new Color(204, 153, 102));
				lblArticleDetails.setBounds(width_small / 4, 65, width_small / 2, 40);
				lblArticleDetails.setOpaque(true);
				article.getContentPane().add(lblArticleDetails);

				/*
				 * final JButton btnSync = new JButton("Sync");
				 * btnSync.setBounds(width_small - 150, 21, 70, 24);
				 * article.getContentPane().add(btnSync);
				 * 
				 * final Label internetCheck = new Label("  ONLINE");
				 * internetCheck.setFont(new Font("Dialog", Font.BOLD |
				 * Font.ITALIC, 12)); internetCheck.setBackground(Color.GREEN);
				 * internetCheck.setAlignment(1);
				 * internetCheck.setForeground(new Color(255, 255, 255));
				 * internetCheck.setBounds(width_small - 80, 22, 65, 22);
				 * article.getContentPane().add(internetCheck);
				 * 
				 * ActionListener taskPerformer1 = new ActionListener() { public
				 * void actionPerformed(ActionEvent evt) { try { Socket sock =
				 * new Socket(); InetSocketAddress addr = new
				 * InetSocketAddress("www.google.com", 80);
				 * sock.setSoTimeout(500); sock.connect(addr, 3000);
				 * 
				 * internetCheck.setBackground(Color.GREEN);
				 * internetCheck.setText("ONLINE"); btnSync.setEnabled(true);
				 * sock.close();
				 * 
				 * } catch (Exception e) {
				 * internetCheck.setBackground(Color.RED);
				 * internetCheck.setText("OFFLINE"); btnSync.setEnabled(false);
				 * } } }; new Timer(delay, taskPerformer1).start();
				 * DefaultTableModel model = new DefaultTableModel(rowData,
				 * columnNames); Action delete = new AbstractAction() { public
				 * void actionPerformed(ActionEvent e) { JTable table = (JTable)
				 * e.getSource(); int modelRow =
				 * Integer.valueOf(e.getActionCommand());
				 * JOptionPane.showMessageDialog(null, "Deleted"); // / //
				 * ((DefaultTableModel)table.getModel()).removeRow(modelRow); }
				 * }; Action view = new AbstractAction() { public void
				 * actionPerformed(ActionEvent e) { JTable table = (JTable)
				 * e.getSource(); int modelRow =
				 * Integer.valueOf(e.getActionCommand());
				 * JOptionPane.showMessageDialog(null, modelRow); // / //
				 * ((DefaultTableModel)table.getModel()).removeRow(modelRow); }
				 * };
				 */

				JButton btnGoBack = new JButton("Close");
				btnGoBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (articles == null) {
							article.dispose();
							issue(issue_id);
						} else if (!articles.isVisible()) {
							article.dispose();
							articles.setVisible(true);
						} else {
							article.dispose();
						}
					}
				});
				btnGoBack.setBounds(width_small - 150, 17, 117, 30);
				article.getContentPane().add(btnGoBack);
				JScrollPane scrollSettings = new JScrollPane();
				scrollSettings.setBounds(40, 180, 320, 200);

				JPanel panel = new JPanel();
				panel.setBackground(SystemColor.window);
				panel.setBounds(50, 107, 300, 307);
				article.getContentPane().add(panel);
				panel.setLayout(null);
				panel.setAutoscrolls(true);
				int y = 10;
				int fields = 5;
				int settings_height = 210 + 30 * (fields - 8);

				Panel panel_1 = new Panel();
				panel_1.setBackground(new Color(204, 153, 102));
				panel_1.setBounds(0, 65, width_small, 45);
				article.getContentPane().add(panel_1);

				Panel panel_2 = new Panel();
				panel_2.setBackground(new Color(153, 102, 51));
				panel_2.setBounds(0, 110, width_small, 5);
				article.getContentPane().add(panel_2);
				panel.setPreferredSize(new Dimension(320, settings_height));
				JScrollPane articleSection = new JScrollPane(panel);
				panel.setAutoscrolls(true);
				articleSection.setPreferredSize(new Dimension(320, 200));
				articleSection.setBounds(40, 132, width_small / 2 - 100, height_small - 280);
				// scrollSettings.setViewportView(scrollFrame);
				article.getContentPane().add(articleSection);

				JPanel panel3 = new JPanel();
				panel3.setBackground(SystemColor.window);
				panel3.setBounds(50, 107, 320, 307);
				article.getContentPane().add(panel3);
				panel3.setLayout(null);
				panel3.setAutoscrolls(true);
				Panel panel4 = new Panel();
				panel4.setBackground(new Color(204, 153, 102));
				panel4.setBounds(0, 65, width_small, 45);
				article.getContentPane().add(panel4);

				Panel panel5 = new Panel();
				panel5.setBackground(new Color(153, 102, 51));
				panel5.setBounds(0, 110, width_small, 5);
				article.getContentPane().add(panel5);
				panel3.setPreferredSize(new Dimension(320, settings_height));
				JScrollPane abstractSection = new JScrollPane(panel3);
				panel3.setAutoscrolls(true);

				JLabel lblNewLabel_1 = new JLabel("Abstract");
				lblNewLabel_1.setBounds(16, 6, 61, 16);
				panel3.add(lblNewLabel_1);

				JTextArea lblAbstract = new JTextArea("abstract");
				lblAbstract.setEditable(false);
				lblAbstract.setBounds(16, 28, 400, 180);
				lblAbstract.setOpaque(true);
				lblAbstract.setBackground(SystemColor.window);
				panel3.add(lblAbstract);
				abstractSection.setPreferredSize(new Dimension(320, 200));
				abstractSection.setBounds(width_small / 2 - 40, 132, width_small / 2, height_small / 2 - 150);
				// scrollSettings.setViewportView(scrollFrame);
				article.getContentPane().add(abstractSection);

				JPanel panel6 = new JPanel();
				panel6.setBackground(SystemColor.window);
				panel6.setBounds(50, 107, 180 * 2, 307);
				article.getContentPane().add(panel6);
				panel6.setLayout(null);
				panel6.setAutoscrolls(true);
				Panel panel7 = new Panel();
				panel7.setBackground(new Color(204, 153, 102));
				panel7.setBounds(0, 65, width_small, 45);
				article.getContentPane().add(panel7);

				Panel panel8 = new Panel();
				panel8.setBackground(new Color(153, 102, 51));
				panel8.setBounds(0, 110, width_small, 5);
				article.getContentPane().add(panel8);
				panel6.setPreferredSize(new Dimension(210 * 2, settings_height)); // scrollable
																					// size
				JScrollPane authorSection = new JScrollPane(panel6);
				panel6.setAutoscrolls(true);

				JLabel lblNewLabel_3 = new JLabel("Author Information");
				lblNewLabel_3.setBounds(6, 6, 156, 16);
				panel6.add(lblNewLabel_3);

				JTextArea lblAuthorInfo = new JTextArea(
						"First Name: Pete\tFirst Name: Bob 2 \nLast Name: User\tLast Name: User 2 \n ");
				lblAuthorInfo.setEditable(false);
				lblAuthorInfo.setBounds(16, 34, 205 * 2, 175); // white box
				lblAuthorInfo.setOpaque(true);
				lblAuthorInfo.setBackground(SystemColor.window);
				panel6.add(lblAuthorInfo);

				authorSection.setPreferredSize(new Dimension(220 * 2, 200));
				authorSection.setBounds(width_small / 2 - 40, 132 + height_small / 2 - 130, width_small / 2,
						height_small / 2 - 150);
				// scrollSettings.setViewportView(scrollFrame);
				article.getContentPane().add(authorSection);

				JLabel lblIssues = new JLabel("Article:");
				lblIssues.setBounds(24, 18, 110, 30);
				panel.add(lblIssues);
				lblIssues.setFont(new Font("Dialog", Font.BOLD, 14));
				lblIssues.setForeground(Color.BLACK);

				JLabel lblIssue = new JLabel("Issue:");
				lblIssue.setBounds(24, 48, 94, 30);
				panel.add(lblIssue);
				lblIssue.setForeground(Color.BLACK);
				lblIssue.setFont(new Font("Dialog", Font.BOLD, 14));

				JLabel lblIssueId = new JLabel("34");
				lblIssueId.setBounds(160, 48, 94, 30);
				panel.add(lblIssueId);
				lblIssueId.setForeground(Color.BLACK);
				lblIssueId.setFont(new Font("Dialog", Font.BOLD, 14));
				lblIssueId.setText(Integer.toString(issue_id));

				JLabel lblDatePublished = new JLabel("Date Published:");
				lblDatePublished.setForeground(Color.BLACK);
				lblDatePublished.setFont(new Font("Dialog", Font.BOLD, 14));
				lblDatePublished.setBounds(24, 195, 150, 30);
				panel.add(lblDatePublished);
				Date current = new Date();

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

				JLabel lblDate = new JLabel(sdf.format(current));
				lblDate.setVerticalAlignment(SwingConstants.TOP);
				lblDate.setForeground(Color.BLACK);
				lblDate.setFont(new Font("Dialog", Font.BOLD, 14));
				lblDate.setBounds(160, 203, 125, 30);
				panel.add(lblDate);

				JLabel lblArticleId = new JLabel("1");
				lblArticleId.setBounds(160, 19, 94, 30);
				panel.add(lblArticleId);
				lblArticleId.setForeground(Color.BLACK);
				lblArticleId.setFont(new Font("Dialog", Font.BOLD, 14));
				lblArticleId.setText(Integer.toString(article_id));

				JLabel lblPages = new JLabel("Pages:");
				lblPages.setForeground(Color.BLACK);
				lblPages.setFont(new Font("Dialog", Font.BOLD, 14));
				lblPages.setBounds(24, 165, 94, 30);
				panel.add(lblPages);

				JLabel lblPageNum = new JLabel("1");
				lblPageNum.setVerticalAlignment(SwingConstants.TOP);
				lblPageNum.setForeground(Color.BLACK);
				lblPageNum.setFont(new Font("Dialog", Font.BOLD, 14));
				lblPageNum.setBounds(160, 171, 125, 30);
				panel.add(lblPageNum);

				JLabel lblTitle = new JLabel("Title:");
				lblTitle.setForeground(Color.BLACK);
				lblTitle.setFont(new Font("Dialog", Font.BOLD, 14));
				lblTitle.setBounds(24, 115, 94, 30);
				panel.add(lblTitle);

				Panel panel9 = new Panel();
				panel9.setBackground(new Color(153, 102, 51));
				panel9.setBounds(85, 118, 180, 80);
				JTextArea lblTitleText = new JTextArea("title");
				lblTitleText.setEditable(false);
				lblTitleText.setOpaque(true);
				lblTitleText.setBackground(SystemColor.window);
				lblTitleText.setForeground(Color.BLACK);
				lblTitleText.setFont(new Font("Dialog", Font.BOLD, 14));
			
				panel9.add(lblTitleText);
				JScrollPane titleSection = new JScrollPane(lblTitleText);
				titleSection.setPreferredSize(new Dimension(300 * 2, 200));
				titleSection.setBounds(85, 113, 225, 50);
				titleSection.add(panel9);
				titleSection.createHorizontalScrollBar();
				panel.add(titleSection);

				JLabel lblSectionId = new JLabel("1");
				lblSectionId.setForeground(Color.BLACK);
				lblSectionId.setFont(new Font("Dialog", Font.BOLD, 14));
				lblSectionId.setBounds(160, 81, 94, 30);
				panel.add(lblSectionId);

				JLabel lblSection = new JLabel("Section:");
				lblSection.setForeground(Color.BLACK);
				lblSection.setFont(new Font("Dialog", Font.BOLD, 14));
				lblSection.setBounds(24, 80, 94, 30);
				panel.add(lblSection);
				if (article_screens.containsKey(issue_id)) {
					HashMap<Integer, JFrame> issue_articles = article_screens.get(issue_id);
					issue_articles.put(article_id, article);
					article_screens.put(issue_id, issue_articles);
				}
			}

		} else {
			login("dashboard");
		}
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public Main() {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (UnsupportedLookAndFeelException e) {
			// handle exception
		} catch (ClassNotFoundException e) {
			// handle exception
		} catch (InstantiationException e) {
			// handle exception
		} catch (IllegalAccessException e) {
			// handle exception
		}
		login("dashboard");
	}

	public static void main(String[] args) {

		database_setup();
		populate_variables();
		new Main();
	}
}