package java_ojs;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.HttpParams;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.sort.RowFilters;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.*;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

import models.Article;
import models.ArticleFile;
import models.Author;
import models.Issue;
import models.Metadata;
import models.Section;

public class Main {
	JFrame login, api, issues, settings;
	private JTextField access_key, api_url, username;
	private JXTable issues_table;
	private int delay = 2000; // milliseconds
	private static String source_api = "";
	private static String source_access_key = "";
	private JPasswordField passwordField;
	private static HashMap<String, String> list_settings;
	private static HashMap<Integer, Integer> list_issues;
	private static HashMap<Integer, JFrame> issue_screens;
	private static HashMap<Integer, Issue> issue_storage;

	private static HashMap<Integer, Metadata> metadata_storage;
	private static HashMap<Integer, Section> section_storage;
	private static HashMap<Integer, Author> author_storage;

	private static HashMap<Integer, HashMap<Integer, Boolean>> author_primary_storage;
	private static HashMap<Integer, HashMap<Integer, ArticleFile>> file_storage;
	private static HashMap<Integer, HashMap<Integer, JFrame>> article_screens;
	private static ArrayList<String> setting_keys = new ArrayList<String>();
	private static Connection c = null;
	private static Statement stmt = null;
	private String api_insert_or_replace_statement = "INSERT OR REPLACE INTO API(URL,ACCESS_KEY) VALUES (?,?)";
	private String settings_insert_or_replace_statement = "INSERT OR REPLACE INTO SETTING(NAME,VALUE) VALUES (?,?)";
	private String issue_insert_or_replace_statement = "INSERT OR REPLACE INTO ISSUE(id,title,volume,number,year,show_title,show_volume,show_number,show_year,date_published,date_accepted) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	private String section_insert_or_replace_statement = "INSERT OR REPLACE INTO SECTION(id,title) VALUES (?,?)";
	private String author_insert_or_replace_statement = "INSERT OR REPLACE INTO AUTHOR(id,first_name,middle_name,last_name,email,affiliation,bio,orcid,department,country) VALUES (?,?,?,?,?,?,?,?,?,?)";
	private String article_insert_or_replace_statement = "INSERT OR REPLACE INTO ARTICLE(id,title,section_id,pages,abstract,date_published,date_accepted) VALUES (?,?,?,?,?,?,?)";
	private String article_author_insert_or_replace_statement = "INSERT OR REPLACE INTO ARTICLE_AUTHOR(id,article_id,author_id,primary_author) VALUES (?,?,?,?)";
	private String issue_article_insert_or_replace_statement = "INSERT OR REPLACE INTO ISSUE_ARTICLE(id,article_id,issue_id) VALUES (?,?,?)";
	private String file_insert_or_replace_statement = "INSERT OR REPLACE INTO FILE(id,article_id,path) VALUES (?,?,?)";
	private String metadata_insert_or_replace_statement = "INSERT OR REPLACE INTO METADATA(id,article_id,competing_interests,funding) VALUES (?,?,?,?)";
	private DefaultHttpClient httpClient = new DefaultHttpClient(); 
	private String delete_issue_statement = "DELETE FROM ISSUE WHERE id=?";
	int width = 800;
	private int height = 600;
	private Boolean logged_in = true;
	private static int i_id = 0;
	private static int articles_id = 0;
	private static int file_id = 0;
	private static int author_id = 0;
	private static int section_db_id = 0;
	private static int metadata_id = 0;
	
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

			stmt.executeUpdate("DELETE FROM ISSUE_ARTICLE");
			stmt.executeUpdate("DELETE FROM ARTICLE_AUTHOR");
			stmt.executeUpdate("DELETE FROM AUTHOR");
			stmt.executeUpdate("DELETE FROM ARTICLE");
			stmt.executeUpdate("DELETE FROM SECTION");
			stmt.executeUpdate("DELETE FROM ISSUE");
			stmt.executeUpdate("DELETE FROM API");
			stmt.executeUpdate("DELETE FROM FILE");
			stmt.executeUpdate("DELETE FROM METADATA");
			PreparedStatement prep = c.prepareStatement(api_insert_or_replace_statement);
			prep.setString(1, source_api);
			prep.setString(2, source_access_key);
			prep.executeUpdate();

			JSONObject json_file = new JSONObject();
			for (int i = 0; i < list_settings.size(); i++) {
				String setting_name = setting_keys.get(i);

				String value = list_settings.get(setting_keys.get(i));
				System.out.println(setting_name + " " + value);

				try {
					int value_int = Integer.parseInt(value);

					json_file.put(setting_name, value_int);

				} catch (Exception e) {

					json_file.put(setting_name, value);
				}

			}

			StringWriter out = new StringWriter();
			try {
				json_file.writeJSONString(out);
				String s = json_file.toJSONString();
				FileWriter new_jsn = new FileWriter("./settings.json");
				new_jsn.write(out.toString());
				new_jsn.flush();
				new_jsn.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

			Set<Integer> section_keys = section_storage.keySet();
			for (int key : section_keys) {
				Section save_section = section_storage.get(key);
				PreparedStatement section_prep = c.prepareStatement(section_insert_or_replace_statement);
				section_prep.setInt(1, save_section.getId());
				section_prep.setString(2, save_section.getTitle());
				section_prep.executeUpdate();
			}

			Set<Integer> author_keys = author_storage.keySet();
			for (int key : author_keys) {
				Author save_author = author_storage.get(key);
				PreparedStatement author_prep = c.prepareStatement(author_insert_or_replace_statement);
				author_prep.setInt(1, save_author.getId());
				author_prep.setString(2, save_author.getFirst_name());
				author_prep.setString(3, save_author.getMiddle_name());
				author_prep.setString(4, save_author.getLast_name());
				author_prep.setString(5, save_author.getEmail());
				author_prep.setString(6, save_author.getAffiliation());
				author_prep.setString(7, save_author.getBio());
				author_prep.setString(8, save_author.getOrcid());
				author_prep.setString(9, save_author.getDepartment());
				author_prep.setString(10, save_author.getCountry());
				author_prep.executeUpdate();

			}
			Set<Integer> file_art_keys = file_storage.keySet();

			for (int key : file_art_keys) {
				HashMap<Integer, ArticleFile> files = file_storage.get(key);
				Set<Integer> file_keys = files.keySet();
				for (int f_key : file_keys) {
					ArticleFile current_file = files.get(f_key);
					PreparedStatement file_prep = c.prepareStatement(file_insert_or_replace_statement);
					file_prep.setInt(1, current_file.getId());
					file_prep.setInt(2, current_file.getArticle_id());
					file_prep.setString(3, current_file.getPath());
					file_prep.executeUpdate();
				}
			}
			Set<Integer> issue_keys = issue_storage.keySet();
			for (int key : issue_keys) {
				Issue save_issue = issue_storage.get(key);
				HashMap<Integer, Article> articles = save_issue.getArticles_list();
				Set<Integer> article_keys = articles.keySet();
				PreparedStatement issue_prep = c.prepareStatement(issue_insert_or_replace_statement);
				issue_prep.setInt(1, save_issue.getId());
				issue_prep.setString(2, save_issue.getTitle());
				issue_prep.setInt(3, save_issue.getVolume());
				issue_prep.setInt(4, save_issue.getNumber());
				issue_prep.setInt(5, save_issue.getYear());
				issue_prep.setString(6, save_issue.getShow_title());
				issue_prep.setInt(7, save_issue.getShow_volume());
				issue_prep.setInt(8, save_issue.getShow_number());
				issue_prep.setInt(9, save_issue.getShow_year());
				issue_prep.setString(10, sdf.format(save_issue.getDate_published()));

				issue_prep.setString(11, sdf.format(save_issue.getDate_accepted()));
				issue_prep.executeUpdate();
				int i = 1;
				int j = 1;
				for (int art_key : article_keys) {
					Article save_article = articles.get(art_key);
					PreparedStatement article_prep = c.prepareStatement(article_insert_or_replace_statement);
					article_prep.setInt(1, save_article.getId());
					article_prep.setString(2, save_article.getTitle());
					article_prep.setInt(3, save_article.getSection_id());
					article_prep.setInt(4, save_article.getPages());
					article_prep.setString(5, save_article.getAbstract_text());
					article_prep.setString(6, sdf.format(save_article.getDate_published()));

					article_prep.setString(7, sdf.format(save_article.getDate_accepted()));
					article_prep.executeUpdate();
					PreparedStatement issue_article_prep = c
							.prepareStatement(issue_article_insert_or_replace_statement);
					issue_article_prep.setInt(1, i);
					issue_article_prep.setInt(2, save_article.getId());
					issue_article_prep.setInt(3, save_issue.getId());
					issue_article_prep.executeUpdate();
					ArrayList<Author> authors = save_article.getAuthors();
					for (int b = 0; b < authors.size(); b++) {
						Author author = authors.get(b);
						PreparedStatement article_author_prep = c
								.prepareStatement(article_author_insert_or_replace_statement);
						article_author_prep.setInt(1, j);
						article_author_prep.setInt(2, save_article.getId());
						article_author_prep.setInt(3, author.getId());
						article_author_prep.setBoolean(4,
								author_primary_storage.get(save_article.getId()).get(author.getId()));

						System.out.println("Author: " + author.getId() + " Primary: "
								+ author_primary_storage.get(save_article.getId()).get(author.getId()));
						article_author_prep.executeUpdate();
						j = j + 1;
					}
					i = i + 1;
				}
			}
			Set<Integer> metadata_keys = metadata_storage.keySet();

			for (int key : metadata_keys) {
				Metadata meta = metadata_storage.get(key);
				PreparedStatement meta_prep = c.prepareStatement(metadata_insert_or_replace_statement);
				meta_prep.setInt(1, meta.getId());
				meta_prep.setInt(2, meta.getArticle_id());
				meta_prep.setString(3, meta.getCompeting_interests());
				meta_prep.setString(4, meta.getFunding());
				meta_prep.executeUpdate();

			}
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Done.");
		JOptionPane.showMessageDialog(null, "Successfully updated database", "Save to Database",
				JOptionPane.INFORMATION_MESSAGE);

	}

	public static void populate_variables() throws ParseException, java.text.ParseException {
		System.out.println("Retrieving data from local database");
		list_settings = new HashMap<String, String>();
		list_issues = new HashMap<Integer, Integer>();
		issue_storage = new HashMap<Integer, Issue>();
		issue_screens = new HashMap<Integer, JFrame>();
		file_storage = new HashMap<Integer, HashMap<Integer, ArticleFile>>();
		article_screens = new HashMap<Integer, HashMap<Integer, JFrame>>();
		author_storage = new HashMap<Integer, Author>();
		section_storage = new HashMap<Integer, Section>();
		author_primary_storage = new HashMap<Integer, HashMap<Integer, Boolean>>();
		metadata_storage = new HashMap<Integer, Metadata>();
		try {
			ResultSet rs = c.createStatement().executeQuery("SELECT * FROM API WHERE URL=" + "'api'" + ";");
			while (rs.next()) {
				source_api = rs.getString("url");
				source_access_key = rs.getString("access_key");
				System.out.println("URL: " + source_api);
				System.out.println("ACCESS KEY: " + source_access_key);
			}
			JSONParser parser = new JSONParser();

			try {
				Object obj = null;
				try {
					obj = parser.parse(new FileReader("./settings.json"));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JSONObject array = (JSONObject) obj;

				Set<Map> keys = array.keySet();
				Object jsn_keys[] = keys.toArray();
				System.out.println("Loading settings....");
				for (Object k : jsn_keys) {
					String setting_name = k.toString();

					String value = array.get(k).toString();
					System.out.println(setting_name + " " + value);

					list_settings.put(setting_name, value);
					setting_keys.add(setting_name);

				}

			} catch (ParseException pe) {

				System.out.println("position: " + pe.getPosition());
				System.out.println(pe);
			}

			System.out.println("Loading Issue data....");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			rs = c.createStatement().executeQuery("SELECT * FROM ISSUE ORDER BY id ASC;");
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				int volume = rs.getInt("volume");
				int number = rs.getInt("number");
				int year = rs.getInt("year");
				String show_title = rs.getString("title");
				int show_volume = rs.getInt("volume");
				int show_number = rs.getInt("number");
				int show_year = rs.getInt("year");

				String date_accepted = rs.getString("date_accepted");
				String date = rs.getString("date_published");
				Issue issue = null;
				issue = new Issue(id, title, volume, number, year, show_title, show_volume, show_number, show_year,
						sdf.parse(date_accepted), sdf.parse(date));

				// JOptionPane.showMessageDialog(null, "Deleted");

				list_issues.put(id, 1);
				issue_screens.put(id, new JFrame());
				article_screens.put(id, new HashMap<Integer, JFrame>());
				issue_storage.put(id, issue);
				i_id = id;
			}
			rs.close();
			System.out.println("Loading Section data....");
			ResultSet sect_s = c.createStatement().executeQuery("SELECT * FROM SECTION ORDER BY id ASC;");
			while (sect_s.next()) {
				int id = sect_s.getInt("id");
				String title = sect_s.getString("title");
				Section new_section = new Section(id, title);
				section_storage.put(id, new_section);
				section_db_id = id;
			}
			sect_s.close();
			System.out.println("Loading Article data....");
			ResultSet art_s = c.createStatement().executeQuery("SELECT * FROM ARTICLE ORDER BY id ASC;");
			ResultSetMetaData rsmd = art_s.getMetaData();
			System.out.println(rsmd.getColumnName(2));
			while (art_s.next()) {
				int id = art_s.getInt("id");
				String title = art_s.getString("title");
				int section_id = art_s.getInt("section_id");
				author_primary_storage.put(id, new HashMap<Integer, Boolean>());
				HashMap<Integer, ArticleFile> files = new HashMap<Integer, ArticleFile>();
				file_storage.put(id, files);
				int pages = art_s.getInt(rsmd.getColumnName(4));
				String abstract_text = art_s.getString(rsmd.getColumnName(5));

				String date = art_s.getString(rsmd.getColumnName(6));
				String date_accepted = art_s.getString(rsmd.getColumnName(7));
				Article article = null;
				article = new Article(id, title, section_id, pages, abstract_text, sdf.parse(date_accepted),
						sdf.parse(date));
				ResultSet rs_issue = c.createStatement().executeQuery(
						"SELECT issue_id FROM ISSUE_ARTICLE WHERE article_id=" + Integer.toString(id) + ";");
				int issue_id = rs_issue.getInt("issue_id");
				Issue update_issue = issue_storage.get(issue_id);
				update_issue.add_article(id, article);
				issue_storage.put(issue_id, update_issue);
				articles_id = id;
				// JOptionPane.showMessageDialog(null, "Deleted");

				HashMap<Integer, JFrame> issue_articles = article_screens.get(issue_id);

				issue_articles.put(id, new JFrame());

				article_screens.put(issue_id, issue_articles);
				rs_issue.close();
			}
			art_s.close();

			System.out.println("Loading Author data....");
			ResultSet authors_s = c.createStatement().executeQuery("SELECT * FROM AUTHOR ORDER BY id ASC;");
			ResultSetMetaData author_rsmd = authors_s.getMetaData();
			while (authors_s.next()) {
				int id = authors_s.getInt("id");
				String first_name = authors_s.getString("first_name");
				String middle_name = authors_s.getString("middle_name");
				String last_name = authors_s.getString("last_name");
				String email = authors_s.getString("email");
				String affiliation = authors_s.getString("affiliation");
				String bio = authors_s.getString("bio");
				String orcid = authors_s.getString("orcid");
				String department = authors_s.getString("department");
				String country = authors_s.getString("country");

				Author author = null;
				author = new Author(id, first_name, middle_name, last_name, email, affiliation, bio, orcid, department,
						country);

				author_storage.put(id, author);
				System.out.println(author_storage.size());
			}
			authors_s.close();

			System.out.println("Loading File data....");
			ResultSet rs_files = c.createStatement().executeQuery("SELECT id,article_id,path FROM FILE");
			while (rs_files.next()) {
				int id = rs_files.getInt(1);
				int article_id = rs_files.getInt(2);
				String path = rs_files.getString(3);
				HashMap<Integer, ArticleFile> files = file_storage.get(article_id);
				if (file_id < id) {
					file_id = id;
				}
				files.put(id, new ArticleFile(id, article_id, path));
				file_storage.put(article_id, files);
			}
			rs_files.close();
			System.out.println("Loading File data....");
			ResultSet rs_metadata = c.createStatement()
					.executeQuery("SELECT id,article_id,competing_interests,funding FROM METADATA");
			while (rs_metadata.next()) {
				int id = rs_metadata.getInt(1);
				int article_id = rs_metadata.getInt(2);
				String competing_interests = rs_metadata.getString(3);
				String funding = rs_metadata.getString(4);
				Metadata meta = new Metadata(id, article_id, competing_interests, funding);
				metadata_storage.put(article_id, meta);
				if (id > metadata_id) {
					metadata_id = id;
				}
			}
			rs_metadata.close();

			Set<Integer> author_keys = author_storage.keySet();
			for (int key_author : author_keys) {
				Author author = author_storage.get(key_author);
				try {
					PreparedStatement prep = c
							.prepareStatement("SELECT author_id,article_id,primary_author FROM ARTICLE_AUTHOR");

					ResultSet rs_author = prep.executeQuery();
					while (rs_author.next()) {
						int author_id = rs_author.getInt(1);

						int article_id = rs_author.getInt(2);

						Boolean primary = rs_author.getBoolean(3);
						System.out.println(author_id + " - " + author.getId());
						if (author_id == author.getId()) {
							System.out.println(author.getFull_name() + " " + Integer.toString(article_id));
							HashMap<Integer, Boolean> primary_authors = author_primary_storage.get(article_id);
							primary_authors.put(author_id, primary);
							System.out.println("Author: " + author_id + " Primary: " + primary);
							author_primary_storage.put(article_id, primary_authors);
							ResultSet rs_new_issue = c.createStatement()
									.executeQuery("SELECT issue_id FROM ISSUE_ARTICLE;");
							while (rs_new_issue.next()) {
								int issue_id = rs_new_issue.getInt("issue_id");
								Issue update_issue = issue_storage.get(issue_id);
								if (update_issue.getArticles_list().containsKey(article_id)) {
									update_issue.add_author(article_id, author);
									System.out.println("Author size: "
											+ update_issue.getArticles_list().get(article_id).getAuthors().size());
									issue_storage.put(issue_id, update_issue);
								}

								rs_new_issue.close();
							}
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();

				}
			}
			// JOptionPane.showMessageDialog(null, "Dele
			c.close();
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

			String sql = "CREATE TABLE IF NOT EXISTS API" + "(URL CHAR(250) PRIMARY KEY NOT NULL,"
					+ " ACCESS_KEY CHAR(100) NOT NULL)";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS ISSUE" + "(id INTEGER PRIMARY KEY," + " title CHAR(500) NOT NULL,"
					+ "volume INTEGER," + "number INTEGER," + "year INTEGER," + " show_title CHAR(500) NOT NULL,"
					+ "show_volume INTEGER," + "show_number INTEGER," + "show_year INTEGER,"
					+ "date_published CHAR(50)," + "date_accepted CHAR(50)" + ")";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS SECTION" + "(id INTEGER PRIMARY KEY," + " title CHAR(250) NOT NULL)";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS AUTHOR" + "(id INTEGER PRIMARY KEY," + " first_name CHAR(200) NOT NULL,"
					+ " middle_name CHAR(200) NOT NULL," + " last_name CHAR(200) NOT NULL,"
					+ " email CHAR(400) NOT NULL," + " affiliation CHAR(500) NOT NULL," + " bio CHAR(800),"
					+ " orcid CHAR(100)," + " department CHAR(300) NOT NULL," + " country CHAR(300) NOT NULL" + ")";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS ARTICLE" + "(id INTEGER PRIMARY KEY," + " title CHAR(500) NOT NULL,"
					+ "section_id INTEGER," + "pages INTEGER," + " abstract CHAR(2000)," + "date_published CHAR(50),"
					+ "date_accepted CHAR(50)," + "FOREIGN KEY (section_id) REFERENCES SECTION(id)" + ")";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS FILE" + "(id INTEGER PRIMARY KEY," + " article_id INTEGER,"
					+ "path CHAR(1000) NOT NULL," + "FOREIGN KEY (article_id) REFERENCES ARTICLE(id)" + ")";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS ISSUE_ARTICLE" + "(id INTEGER PRIMARY KEY," + " article_id INTEGER,"
					+ " issue_id INTEGER," + "FOREIGN KEY (article_id) REFERENCES ARTICLE(id),"
					+ "FOREIGN KEY (issue_id) REFERENCES ISSUE(id)" + ")";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS ARTICLE_AUTHOR" + "(id INTEGER PRIMARY KEY," + " article_id INTEGER,"
					+ " author_id INTEGER," + "primary_author BOOLEAN DEFAULT FALSE,"
					+ "FOREIGN KEY (article_id) REFERENCES ARTICLE(id),"
					+ "FOREIGN KEY (author_id) REFERENCES AUTHOR(id)" + ")";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS METADATA" + "(id INTEGER PRIMARY KEY," + " article_id INTEGER,"
					+ " competing_interests CHAR(1000)," + "funding CHAR(1000),"
					+ "FOREIGN KEY (article_id) REFERENCES ARTICLE(id)" + ")";
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
			login.setTitle("TORU - Log In");
			login.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					// database_save();
				}
			});
			login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			login.getContentPane().setForeground(Color.WHITE);
			login.getContentPane().setBackground(new Color(128, 128, 128));

			login.setLocationRelativeTo(null);
			login.setSize(width_small, height_small);// 400 width and 500 height
			login.getContentPane().setLayout(null);// using no layout managers
			JLabel lblNewLabel = new JLabel("TORU");

			lblNewLabel.setForeground(new Color(255, 250, 250));
			lblNewLabel.setBackground(new Color(230, 230, 250));
			lblNewLabel.setFont(new Font("Trattatello", Font.BOLD, 24));
			lblNewLabel.setToolTipText("Welcome\n");
			lblNewLabel.setBounds((width_small / 2) - 34, 15, 95, 25);
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
				settings.setTitle("Settings");

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

				lblNewLabel.setBounds((width_small / 2) - 34, 15, 95, 25);
				settings.getContentPane().add(lblNewLabel);
				JPanel title_background = new JPanel();
				title_background.setBackground(new Color(0, 0, 0));
				title_background.setBounds(-17, 0, width_small + 33, 54);
				settings.getContentPane().add(title_background);
				final JButton btnSync1 = new JButton("Sync");
				btnSync1.setBounds(width_small - 155, 68, 70, 25);
				settings.getContentPane().add(btnSync1);
				final JButton btnBasic = new JButton("Basic");
				btnBasic.setBounds(width_small / 2 + 20, 185, 100, 25);
				settings.getContentPane().add(btnBasic);
				final JButton btnAdvanced = new JButton("Advanced");

				settings.getContentPane().add(btnAdvanced);

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
				int y = 30;
				int settings_height = 210 + 30 * (setting_keys.size() - 8);
				panelSettings.setPreferredSize(new Dimension(width_small - 80, settings_height));
				JScrollPane scrollFrame = new JScrollPane(panelSettings);
				panelSettings.setAutoscrolls(true);
				scrollFrame.setPreferredSize(new Dimension(320, 200));
				scrollFrame.setBounds(40, 220, width_small - 80, height_small - 350);
				// scrollSettings.setViewportView(scrollFrame);
				settings.getContentPane().add(scrollFrame);
				ArrayList<JCheckBox> checkboxes = new ArrayList<JCheckBox>();
				btnAdvanced.setBounds(width_small / 2 + 120, 185, 100, 25);
				btnAdvanced.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						for (int i = 0; i < checkboxes.size(); i++) {
							checkboxes.get(i).setSelected(true);
							list_settings.remove(checkboxes.get(i).getText());
							list_settings.put(checkboxes.get(i).getText(), "true");
						}

						panelSettings.repaint();
					}
				});
				ArrayList<String> basic = new ArrayList<String>();
				basic.add("Optimize PDFs");
				basic.add("Optimize Images");
				basic.add("Validate XML (JATS)");
				basic.add("Insert DOIs in PDFs");
				btnBasic.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						for (int i = 0; i < checkboxes.size(); i++) {
							if (basic.contains(checkboxes.get(i).getText())) {
								checkboxes.get(i).setSelected(true);
								list_settings.remove(checkboxes.get(i).getText());
								list_settings.put(checkboxes.get(i).getText(), "true");
							} else {
								checkboxes.get(i).setSelected(false);
								list_settings.remove(checkboxes.get(i).getText());
								list_settings.put(checkboxes.get(i).getText(), "false");
							}
						}

						panelSettings.repaint();
					}
				});
				for (int i = 0; i < setting_keys.size(); i++) {
					String value = list_settings.get(setting_keys.get(i));
					boolean done = false;
					if (value.compareTo("true") == 0 || value.compareTo("false") == 0) {
						Boolean processed = Boolean.parseBoolean(value);

						final JCheckBox chckbxSampleSetting = new JCheckBox(setting_keys.get(i));
						final int s = i;
						chckbxSampleSetting.setName(Integer.toString(i));
						chckbxSampleSetting.setBounds(81, y, 150, 23);
						chckbxSampleSetting.setSelected(processed);
						chckbxSampleSetting.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								list_settings.remove(setting_keys.get(s));
								list_settings.put(setting_keys.get(s),
										Boolean.toString(chckbxSampleSetting.isSelected()));
							}
						});
						done = true;
						checkboxes.add(chckbxSampleSetting);
						panelSettings.add(chckbxSampleSetting);
						y = y + 28;
					}
					if (!done) {
						try {
							int processed = Integer.parseInt(value);
							final JLabel intSampleSettinglbl = new JLabel(setting_keys.get(i));
							intSampleSettinglbl.setBounds(81, y, 150, 23);
							final JTextField intSampleSetting = new JTextField(list_settings.get(setting_keys.get(i)));
							final int s = i;
							intSampleSetting.setName(Integer.toString(i));
							intSampleSetting.setBounds(81, y + 25, 150, 23);
							intSampleSetting.getDocument().addDocumentListener(new DocumentListener() {

								@Override
								public void insertUpdate(DocumentEvent de) {
									try {
										int processed = Integer.parseInt(intSampleSetting.getText());
										list_settings.remove(setting_keys.get(s));
										list_settings.put(setting_keys.get(s), intSampleSetting.getText());
									} catch (Exception ex) {
										JOptionPane.showMessageDialog(null,
												"Use only numbers in field: " + setting_keys.get(s));

									}
								}

								@Override
								public void removeUpdate(DocumentEvent de) {
									try {
										int processed = Integer.parseInt(intSampleSetting.getText());
										list_settings.remove(setting_keys.get(s));
										list_settings.put(setting_keys.get(s), intSampleSetting.getText());
									} catch (Exception ex) {
										JOptionPane.showMessageDialog(null,
												"Use only numbers in field: " + setting_keys.get(s));

									}
								}

								@Override
								public void changedUpdate(DocumentEvent de) {
									try {
										int processed = Integer.parseInt(intSampleSetting.getText());
										list_settings.remove(setting_keys.get(s));
										list_settings.put(setting_keys.get(s), intSampleSetting.getText());
									} catch (Exception ex) {
										JOptionPane.showMessageDialog(null,
												"Use only numbers in field: " + setting_keys.get(s));

									}
								}
							});

							y = y + 60;

							done = true;
							panelSettings.add(intSampleSettinglbl);
							panelSettings.add(intSampleSetting);
						} catch (Exception e) {

							final JLabel stringSampleSettinglbl = new JLabel(setting_keys.get(i));
							stringSampleSettinglbl.setBounds(81, y, 150, 23);
							final JTextField stringSampleSetting = new JTextField(
									list_settings.get(setting_keys.get(i)));
							final int s = i;
							stringSampleSetting.setName(Integer.toString(i));
							stringSampleSetting.setBounds(81, y + 25, 150, 23);

							stringSampleSetting.getDocument().addDocumentListener(new DocumentListener() {

								@Override
								public void insertUpdate(DocumentEvent de) {
									list_settings.remove(setting_keys.get(s));
									list_settings.put(setting_keys.get(s), stringSampleSetting.getText());
								}

								@Override
								public void removeUpdate(DocumentEvent de) {
									list_settings.remove(setting_keys.get(s));
									list_settings.put(setting_keys.get(s), stringSampleSetting.getText());
								}

								@Override
								public void changedUpdate(DocumentEvent de) {
									list_settings.remove(setting_keys.get(s));
									list_settings.put(setting_keys.get(s), stringSampleSetting.getText());
								}
							});

							y = y + 60;

							panelSettings.add(stringSampleSettinglbl);
							panelSettings.add(stringSampleSetting);
						}
					}

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
				api.setTitle("API Information");

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

				lblNewLabel.setBounds((width_small / 2) - 34, 15, 95, 25);
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

				// Issue issue = new Issue(i_id, "title", 1, 1, 2015, "title",
				// 1, 1, 2015, date);
				// Issue Table [title, volume, number, year, show_title,
				// show_volume,
				// show_number, show_year, date_published]
				issues = new JFrame();
				issues.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				if (height >= 640 && width >= 900) {
					issues.setSize(width, height);
				} else {
					width = 900;
					height = 640;
					issues.setSize(900, 640);
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				issues.getContentPane().setBackground(new Color(105, 105, 105));
				issues.setVisible(true);
				issues.setTitle("Dashboard");
				issues.setLocationRelativeTo(null);
				issues.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						// database_save();
					}
				});
				// Object rowData[][] = { { 1, "title", 1, 1, 2015,
				// sdf.format(date), "View", "Edit", "Delete" } };

				Set<Integer> issue_keys = issue_storage.keySet();
				ArrayList<List<Object>> rowData = new ArrayList<List<Object>>();
				Object[][] rows = new Object[issue_keys.size()][6];
				int i = 0;
				for (int id : issue_keys) {
					Issue row_issue = issue_storage.get(id);
					ArrayList<Object> data = new ArrayList<Object>();
					issue_screens.put(id, new JFrame());
					article_screens.put(id, new HashMap<Integer, JFrame>());

					data.add(Integer.toString(row_issue.getId()));
					data.add(row_issue.getShow_title());
					data.add(Integer.toString(row_issue.getShow_volume()));
					data.add(Integer.toString(row_issue.getShow_number()));
					data.add(Integer.toString(row_issue.getShow_year()));
					data.add(sdf.format(row_issue.getDate_published()));
					data.add("View");
					data.add("Edit");
					data.add("Delete");
					Object[] row = { row_issue.getId(), row_issue.getShow_title(), row_issue.getShow_volume(),
							row_issue.getShow_number(), row_issue.getShow_year(),
							sdf.format(row_issue.getDate_accepted()), sdf.format(row_issue.getDate_published()), "View",
							"Edit", "Delete" };
					rows[i] = row;
					i++;
					rowData.add(data);

				}
				Object columnNames[] = { "ID", "Title", "Volume", "Number", "Year", "Date Accepted", "Date Published",
						"", "", "" };
				issues.getContentPane().setLayout(null);

				final JButton btnSync = new JButton("Sync");
				btnSync.setBounds(width - 155, 21, 70, 24);
			
				String postUrl="www.site.com";// put in your url
				
				issues.getContentPane().add(btnSync);
				Set<Integer> author_keys = author_storage.keySet();
				ArrayList<Integer> author_list = new ArrayList<Integer>();
				String listData[] = new String[author_keys.size()];
				int j = 0;
				DefaultListModel listModel = new DefaultListModel();
				for (int key : author_keys) {
					listModel.addElement(author_storage.get(key).getFull_name());
					listData[j] = author_storage.get(key).getFull_name();
					author_list.add(key);
					j = j + 1;
				}
				;

				// Create a new listbox control

				JList listbox = new JList();
				listbox.setModel(listModel);
				listbox.setBounds(15, 40, 320, 25 * author_list.size());
				listbox.setBackground(Color.white);
				listbox.setVisible(true);
				JButton btnAddAuthor = new JButton("Add new Author");

				btnAddAuthor.setBounds(15, 15, 320, 25);

				JScrollPane scrollPane = new JScrollPane();

				scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				scrollPane.setBounds(20, 140, width - 40, height - 285);
				issues.getContentPane().add(scrollPane);

				DefaultTableModel dtm = new DefaultTableModel(rows, columnNames);

				issues_table = new JXTable(dtm) {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					// **** Source:
					// http://stackoverflow.com/questions/9467093/how-to-add-a-tooltip-to-a-cell-in-a-jtable
					// ****//
					// Implement table cell tool tips.
					public String getToolTipText(MouseEvent e) {
						String tip = null;
						java.awt.Point p = e.getPoint();
						int rowIndex = rowAtPoint(p);
						int colIndex = columnAtPoint(p);

						try {
							tip = getValueAt(rowIndex, colIndex).toString();
						} catch (RuntimeException e1) {
							// catch null pointer exception if mouse is over an
							// empty line
						}

						return tip;
					}
				};
				// reference:
				// https://svn.java.net/svn/swinglabs-demos~svn/trunk/src/java/org/jdesktop/demo/sample/
				final JTextField filter = new JTextField("");
				filter.setBounds(50, 110, 120, 25);

				final JButton search = new JButton("Search");
				final JButton clear = new JButton("Clear");
				search.setBounds(170, 110, 90, 25);
				clear.setBounds(258, 110, 65, 25);
				issues.getContentPane().add(filter);
				issues.getContentPane().add(search);
				issues.getContentPane().add(clear);
				filter.setAction(new AbstractAction("Search") {
					public void actionPerformed(ActionEvent e) {

						String searchString = filter.getText().trim();
						if (searchString.length() > 0) {
							issues_table.setRowFilter(RowFilters.regexFilter(0, searchString));
						} else {
							issues_table.setRowFilter(null);
						}
					}
				});
				search.setAction(new AbstractAction("Search") {
					public void actionPerformed(ActionEvent e) {

						String searchString = filter.getText().trim();
						if (searchString.length() > 0) {
							issues_table.setRowFilter(RowFilters.regexFilter(0, searchString));
						}
					}
				});
				clear.setAction(new AbstractAction("Clear") {
					public void actionPerformed(ActionEvent e) {
						issues_table.setRowFilter(null);
					}
				});
				scrollPane.setViewportView(issues_table);
				issues_table.setColumnSelectionAllowed(true);
				issues_table.getColumnModel().getColumn(5).setMinWidth(95);
				issues_table.getColumnModel().getColumn(6).setMinWidth(50);
				issues_table.getColumnModel().getColumn(7).setMinWidth(40);
				issues_table.getColumnModel().getColumn(8).setMinWidth(50);
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
				DefaultTableModel model = new DefaultTableModel(rows, columnNames);
				Action delete = new AbstractAction() {
					public void actionPerformed(ActionEvent e) {
						JTable table = (JTable) e.getSource();
						int modelRow = Integer.valueOf(e.getActionCommand());
						// JOptionPane.showMessageDialog(null, "Deleted");
						int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this issue?",
								"Delete ?", JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {

							int issue_row = table.getSelectedRow();
							int selectedColumnIndex = 0;
							Object selectedObject = (Object) table.getModel().getValueAt(issue_row,
									selectedColumnIndex);
							int selected_issue = (int) selectedObject;
							if (issue_screens.get(selected_issue).isVisible()
									|| !(issue_screens.get(selected_issue) == null)) {
								HashMap<Integer, JFrame> opened = article_screens.get(selected_issue);
								Set<Integer> ids = opened.keySet();
								System.out.println("Issue: " + Integer.toString(selected_issue));
								for (int id : ids) {
									System.out.println(id);
									JFrame art_window = opened.get(id);
									if (art_window.isVisible() || !(art_window == null)) {
										art_window.dispose();
									}
								}
								issue_storage.remove(selected_issue);
								article_screens.remove(selected_issue);
								issue_screens.get(selected_issue).dispose();
								System.out.println(issue_screens.get(selected_issue) == null);
								issue_screens.remove(selected_issue);
								((DefaultTableModel) table.getModel()).removeRow(modelRow);
								table.repaint();
							}
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

						edit_issue(selected_issue);

						// /
						// ((DefaultTableModel)table.getModel()).removeRow(modelRow);
					}
				};
				ButtonColumn buttonColumn = new ButtonColumn(issues_table, view, 7);
				ButtonColumn buttonColumn2 = new ButtonColumn(issues_table, edit, 8);
				ButtonColumn buttonColumn3 = new ButtonColumn(issues_table, delete, 9);

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
				issues.getContentPane().add(btnApi);

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
						add_issue();
						/*
						 * i_id = i_id + 1;
						 * 
						 * // JOptionPane.showMessageDialog(null, "Deleted");
						 * Date date = new Date(); SimpleDateFormat sdf = new
						 * SimpleDateFormat("yyyy/MM/dd"); Issue issue = new
						 * Issue(i_id, "title", 1, 1, 2015, "title", 1, 1, 2015,
						 * date);
						 * 
						 * list_issues.put(i_id, 1); issue_screens.put(i_id, new
						 * JFrame()); article_screens.put(i_id, new
						 * HashMap<Integer, JFrame>()); issue_storage.put(i_id,
						 * issue); Object[] new_row = { i_id, "title", 1, 1,
						 * 2015, sdf.format(date), "View", "Edit", "Delete" };
						 * 
						 * ((DefaultTableModel)
						 * issues_table.getModel()).addRow(new_row);
						 * issues_table.repaint();
						 */
					}
				});
				btnAdd.setBounds(width - 150, 112, 117, 25);
				issues.getContentPane().add(btnAdd);
				buttonColumn.setMnemonic(KeyEvent.VK_D);
				buttonColumn2.setMnemonic(KeyEvent.VK_D);
				buttonColumn3.setMnemonic(KeyEvent.VK_D);
				issues.setVisible(true);
				issues.repaint();
			}
		} else {
			login("dashboard");
		}

	}

	public void add_issue() {
		if (logged_in) {
			int width_small = 0;
			int height_small = 0;
			if (height >= 480 && width >= 640) {
				width_small = (int) (1024 - (1024 * (37.5 / 100)));
			} else {
				width_small = (int) (960 - (960 * (37.5 / 100)));
			}
			final int current_id = i_id + 1;
			height_small = (int) (600 - (600 * (5 / 100)));
			final JFrame edit_issue = new JFrame();
			issue_screens.put(i_id, edit_issue);
			edit_issue.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			edit_issue.getContentPane().setBackground(new Color(128, 128, 128));
			edit_issue.setTitle("New Issue");
			edit_issue.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					// database_save();

				}
			});
			edit_issue.setSize(width_small, height_small);// 400 width and 500
			// height
			edit_issue.getContentPane().setLayout(null);// using no layout
														// managers

			JLabel lblNewLabel = new JLabel("TORU");
			lblNewLabel.setForeground(new Color(255, 250, 250));
			lblNewLabel.setBackground(new Color(230, 230, 250));
			lblNewLabel.setFont(new Font("Trattatello", Font.BOLD, 24));
			lblNewLabel.setToolTipText("Welcome\n");

			lblNewLabel.setBounds((width_small / 2) - 34, 15, 95, 25);
			edit_issue.getContentPane().add(lblNewLabel);
			final JTextField title = new JTextField();
			title.setBounds(50, 218, 250, 26);
			edit_issue.getContentPane().add(title);
			title.setColumns(10);

			JLabel lblTitleText = new JLabel("Title");
			lblTitleText.setForeground(new Color(245, 255, 250));
			lblTitleText.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitleText.setBounds(50, 200, 250, 16);
			edit_issue.getContentPane().add(lblTitleText);
			JPanel title_background = new JPanel();
			title_background.setBackground(new Color(0, 0, 0));
			title_background.setBounds(-17, 0, width_small + 33, 54);
			edit_issue.getContentPane().add(title_background);

			final JTextField volume = new JTextField();
			volume.setColumns(10);
			volume.setBounds(50, 270, 250, 26);
			edit_issue.getContentPane().add(volume);
			JLabel lblvolume = new JLabel("Volume");
			lblvolume.setHorizontalAlignment(SwingConstants.CENTER);
			lblvolume.setForeground(new Color(245, 255, 250));
			lblvolume.setBounds(50, 250, 250, 16);
			edit_issue.getContentPane().add(lblvolume);

			final JTextField number = new JTextField();
			number.setColumns(10);
			number.setBounds(50, 317, 250, 26);
			edit_issue.getContentPane().add(number);
			JLabel lblnumber = new JLabel("Number");
			lblnumber.setHorizontalAlignment(SwingConstants.CENTER);
			lblnumber.setForeground(new Color(245, 255, 250));
			lblnumber.setBounds(50, 300, 250, 16);
			edit_issue.getContentPane().add(lblnumber);

			final JTextField year = new JTextField();
			year.setColumns(10);
			year.setBounds(50, 364, 250, 26);
			edit_issue.getContentPane().add(year);
			JLabel lblyear = new JLabel("Year");
			lblyear.setHorizontalAlignment(SwingConstants.CENTER);
			lblyear.setForeground(new Color(245, 255, 250));
			lblyear.setBounds(50, 347, 250, 16);
			edit_issue.getContentPane().add(lblyear);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			JLabel lblDateAccepted = new JLabel("Date Accepted");
			lblDateAccepted.setHorizontalAlignment(SwingConstants.CENTER);
			lblDateAccepted.setForeground(new Color(245, 255, 250));
			lblDateAccepted.setBounds(80, 394, width_small - 161, 16);
			edit_issue.getContentPane().add(lblDateAccepted);

			final JXDatePicker datePicker = new JXDatePicker();
			datePicker.setFormats(sdf);

			datePicker.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println(datePicker.getDate());
				}
			});
			;
			datePicker.setBounds(100, 410, width_small - 200, 30);
			// panel.add(label);
			edit_issue.getContentPane().add(datePicker);
			JLabel lblDatePublished = new JLabel("Date Published");
			lblDatePublished.setHorizontalAlignment(SwingConstants.CENTER);
			lblDatePublished.setForeground(new Color(245, 255, 250));
			lblDatePublished.setBounds(80, 441, width_small - 161, 16);
			edit_issue.getContentPane().add(lblDatePublished);

			final JXDatePicker datePickerPublished = new JXDatePicker();
			datePickerPublished.setFormats(sdf);

			datePickerPublished.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println(datePickerPublished.getDate());
				}
			});
			;
			datePickerPublished.setBounds(100, 460, width_small - 200, 30);
			// panel.add(label);
			edit_issue.getContentPane().add(datePickerPublished);
			JLabel lblShowDisplay = new JLabel("---- Display Values ----");
			lblShowDisplay.setForeground(new Color(245, 255, 250));
			lblShowDisplay.setHorizontalAlignment(SwingConstants.CENTER);
			lblShowDisplay.setBounds(340, 170, 250, 16);
			edit_issue.getContentPane().add(lblShowDisplay);

			final JTextField show_title = new JTextField();
			show_title.setBounds(340, 218, 250, 26);
			edit_issue.getContentPane().add(show_title);
			show_title.setColumns(10);

			JLabel lblShowTitleText = new JLabel("Show Title");
			lblShowTitleText.setForeground(new Color(245, 255, 250));
			lblShowTitleText.setHorizontalAlignment(SwingConstants.CENTER);
			lblShowTitleText.setBounds(340, 200, 250, 16);
			edit_issue.getContentPane().add(lblShowTitleText);

			final JTextField show_volume = new JTextField();
			show_volume.setBounds(340, 270, 250, 26);
			edit_issue.getContentPane().add(show_volume);
			show_volume.setColumns(10);

			JLabel lblShowVolume = new JLabel("Show Volume");
			lblShowVolume.setForeground(new Color(245, 255, 250));
			lblShowVolume.setHorizontalAlignment(SwingConstants.CENTER);
			lblShowVolume.setBounds(340, 250, 250, 16);
			edit_issue.getContentPane().add(lblShowVolume);

			final JTextField show_number = new JTextField();
			show_number.setBounds(340, 317, 250, 26);
			edit_issue.getContentPane().add(show_number);
			show_number.setColumns(10);

			JLabel lblShowNumber = new JLabel("Show Number");
			lblShowNumber.setForeground(new Color(245, 255, 250));
			lblShowNumber.setHorizontalAlignment(SwingConstants.CENTER);
			lblShowNumber.setBounds(340, 300, 250, 16);
			edit_issue.getContentPane().add(lblShowNumber);

			final JTextField show_year = new JTextField();
			show_year.setBounds(340, 364, 250, 26);
			edit_issue.getContentPane().add(show_year);
			show_year.setColumns(10);

			JLabel lblShowYear = new JLabel("Show Year");
			lblShowYear.setForeground(new Color(245, 255, 250));
			lblShowYear.setHorizontalAlignment(SwingConstants.CENTER);
			lblShowYear.setBounds(340, 347, 250, 16);
			edit_issue.getContentPane().add(lblShowYear);

			title.getDocument().addDocumentListener(new DocumentListener() {

				@Override
				public void insertUpdate(DocumentEvent de) {
					show_title.setText(title.getText());
				}

				@Override
				public void removeUpdate(DocumentEvent de) {
					show_title.setText(title.getText());
				}

				@Override
				public void changedUpdate(DocumentEvent de) {
					show_title.setText(title.getText());
				}
			});
			year.getDocument().addDocumentListener(new DocumentListener() {

				@Override
				public void insertUpdate(DocumentEvent de) {
					show_year.setText(year.getText());
				}

				@Override
				public void removeUpdate(DocumentEvent de) {
					show_year.setText(year.getText());
				}

				@Override
				public void changedUpdate(DocumentEvent de) {
					show_year.setText(year.getText());
				}
			});
			volume.getDocument().addDocumentListener(new DocumentListener() {

				@Override
				public void insertUpdate(DocumentEvent de) {
					show_volume.setText(volume.getText());
				}

				@Override
				public void removeUpdate(DocumentEvent de) {
					show_volume.setText(volume.getText());
				}

				@Override
				public void changedUpdate(DocumentEvent de) {
					show_volume.setText(volume.getText());
				}
			});
			number.getDocument().addDocumentListener(new DocumentListener() {

				@Override
				public void insertUpdate(DocumentEvent de) {
					show_number.setText(number.getText());
				}

				@Override
				public void removeUpdate(DocumentEvent de) {
					show_number.setText(number.getText());
				}

				@Override
				public void changedUpdate(DocumentEvent de) {
					show_number.setText(number.getText());
				}
			});
			JButton btnSubmit = new JButton("Create");

			btnSubmit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Boolean validation = true;
					int entered_volume = 0;
					int entered_number = 0;
					int entered_year = 0;
					int entered_show_volume = 0;
					int entered_show_number = 0;
					int entered_show_year = 0;
					try {

						entered_volume = Integer.parseInt(volume.getText());
						entered_number = Integer.parseInt(number.getText());
						entered_year = Integer.parseInt(year.getText());

						number.setBackground(new Color(255, 255, 255));
						number.setForeground(new Color(0, 0, 0));
						volume.setBackground(new Color(255, 255, 255));
						volume.setForeground(new Color(0, 0, 0));
						year.setBackground(new Color(255, 255, 255));
						year.setForeground(new Color(0, 0, 0));
					} catch (Exception ex) {
						validation = false;
						number.setBackground(new Color(255, 0, 0));
						number.setForeground(new Color(255, 255, 255));
						volume.setBackground(new Color(255, 0, 0));
						volume.setForeground(new Color(255, 255, 255));
						year.setBackground(new Color(255, 0, 0));
						year.setForeground(new Color(255, 255, 255));
						JOptionPane.showMessageDialog(null, "Use only numbers in fields: Volume, Number, Year ");
					}
					try {
						entered_show_volume = Integer.parseInt(show_volume.getText());
						entered_show_number = Integer.parseInt(show_number.getText());
						entered_show_year = Integer.parseInt(show_year.getText());
						show_number.setBackground(new Color(255, 255, 255));
						show_number.setForeground(new Color(0, 0, 0));
						show_volume.setBackground(new Color(255, 255, 255));
						show_volume.setForeground(new Color(0, 0, 0));
						show_year.setBackground(new Color(255, 255, 255));
						show_year.setForeground(new Color(0, 0, 0));
					} catch (Exception ex) {
						validation = false;
						show_number.setBackground(new Color(255, 0, 0));
						show_number.setForeground(new Color(255, 255, 255));
						show_volume.setBackground(new Color(255, 0, 0));
						show_volume.setForeground(new Color(255, 255, 255));
						show_year.setBackground(new Color(255, 0, 0));
						show_year.setForeground(new Color(255, 255, 255));
						JOptionPane.showMessageDialog(null,
								"Use only numbers in fields: Show_Volume, Show_Number, Show_Year ");
					}
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
					try {

						String test_accepted = sdf.format(datePicker.getDate());
						String test_published = sdf.format(datePickerPublished.getDate());

					} catch (Exception ex) {
						validation = false;
						JOptionPane.showMessageDialog(null,
								"Use dates from calendar for fields: Date Published and Date Accepted");
					}

					if (validation) {
						i_id++;
						Issue issue = new Issue(i_id, title.getText(), entered_volume, entered_number, entered_year,
								datePicker.getDate(), datePickerPublished.getDate());
						issue.setShow_title(show_title.getText());
						issue.setShow_volume(entered_show_volume);
						issue.setShow_year(entered_show_year);
						issue.setShow_number(entered_show_number);

						// JOptionPane.showMessageDialog(null, "Deleted");

						list_issues.put(i_id, 1);
						issue_screens.put(i_id, new JFrame());
						article_screens.put(i_id, new HashMap<Integer, JFrame>());
						issue_storage.put(i_id, issue);
						Object[] new_row = { i_id, title.getText(), Integer.parseInt(volume.getText()),
								Integer.parseInt(number.getText()), sdf.format(datePicker.getDate()), "View", "Edit",
								"Delete" };

						((DefaultTableModel) issues_table.getModel()).addRow(new_row);
						issues_table.repaint();
						edit_issue.dispose();
						issues.dispose();
						dashboard();
					}

				}
			});
			if (height_small - 150 > 300) {
				btnSubmit.setBounds(((width_small / 3) * 2) / 2, height_small - 70, width_small / 3, 29);
			} else {
				btnSubmit.setBounds(((width_small / 3) * 2) / 2, 380, width_small / 3, 29);
			}

			edit_issue.getContentPane().add(btnSubmit);

			JLabel lblApiInformation = new JLabel("Issue Details");
			lblApiInformation.setBackground(new Color(218, 207, 2));
			lblApiInformation.setHorizontalAlignment(SwingConstants.CENTER);
			lblApiInformation.setForeground(new Color(255, 255, 255));
			lblApiInformation.setFont(new Font("Dialog", Font.BOLD, 20));
			lblApiInformation.setBounds((width_small / 2) - 145, 108, 309, 40);
			lblApiInformation.setOpaque(true);
			edit_issue.getContentPane().add(lblApiInformation);

			Panel panel = new Panel();
			panel.setBackground(new Color(204, 51, 51));
			panel.setBounds(0, 54, width_small, 5);
			edit_issue.getContentPane().add(panel);

			edit_issue.setVisible(true);// making the frame visible
			edit_issue.repaint();
			issue_screens.put(i_id, edit_issue);
			Panel panel_2 = new Panel();
			panel_2.setBackground(new Color(192, 183, 3));
			panel_2.setBounds(0, 150, width_small, 5);
			edit_issue.getContentPane().add(panel_2);
			Panel panel_1 = new Panel();
			panel_1.setBackground(new Color(218, 207, 2));
			panel_1.setBounds(0, 105, width_small, 45);
			edit_issue.getContentPane().add(panel_1);

		} else {
			login("dashboard");
		}
	}

	public void edit_issue(final int issue_id) {
		if (logged_in) {
			if (issue_screens.containsKey(issue_id) && !issue_screens.get(issue_id).isVisible()) {
				int width_small = 0;
				int height_small = 0;
				if (height >= 480 && width >= 640) {
					width_small = (int) (1024 - (1024 * (37.5 / 100)));
				} else {
					width_small = (int) (960 - (960 * (37.5 / 100)));
				}
				height_small = (int) (600 - (600 * (5 / 100)));
				final JFrame edit_issue = new JFrame();
				issue_screens.put(issue_id, edit_issue);
				edit_issue.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				edit_issue.getContentPane().setBackground(new Color(128, 128, 128));
				edit_issue.setTitle("Issue Details - Editing");
				edit_issue.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						// database_save();
					}
				});
				edit_issue.setSize(width_small, height_small);// 400 width and
																// 500
				// height
				edit_issue.getContentPane().setLayout(null);// using no layout
															// managers
				final Issue current_issue = issue_storage.get(issue_id);
				JLabel lblNewLabel = new JLabel("TORU");
				lblNewLabel.setForeground(new Color(255, 250, 250));
				lblNewLabel.setBackground(new Color(230, 230, 250));
				lblNewLabel.setFont(new Font("Trattatello", Font.BOLD, 24));
				lblNewLabel.setToolTipText("Welcome\n");

				lblNewLabel.setBounds((width_small / 2) - 34, 15, 95, 25);
				edit_issue.getContentPane().add(lblNewLabel);
				final JTextField title = new JTextField();
				title.setBounds(50, 218, 250, 26);
				title.setText(current_issue.getTitle());
				edit_issue.getContentPane().add(title);
				title.setColumns(10);

				JLabel lblTitleText = new JLabel("Title");
				lblTitleText.setForeground(new Color(245, 255, 250));
				lblTitleText.setHorizontalAlignment(SwingConstants.CENTER);
				lblTitleText.setBounds(50, 200, 250, 16);
				edit_issue.getContentPane().add(lblTitleText);
				JPanel title_background = new JPanel();
				title_background.setBackground(new Color(0, 0, 0));
				title_background.setBounds(-17, 0, width_small + 33, 54);
				edit_issue.getContentPane().add(title_background);

				final JTextField volume = new JTextField();
				volume.setColumns(10);
				volume.setText(Integer.toString(current_issue.getVolume()));
				volume.setBounds(50, 270, 250, 26);
				edit_issue.getContentPane().add(volume);
				JLabel lblvolume = new JLabel("Volume");
				lblvolume.setHorizontalAlignment(SwingConstants.CENTER);
				lblvolume.setForeground(new Color(245, 255, 250));
				lblvolume.setBounds(50, 250, 250, 16);
				edit_issue.getContentPane().add(lblvolume);

				final JTextField number = new JTextField();
				number.setColumns(10);
				number.setText(Integer.toString(current_issue.getNumber()));
				number.setBounds(50, 317, 250, 26);
				edit_issue.getContentPane().add(number);
				JLabel lblnumber = new JLabel("Number");
				lblnumber.setHorizontalAlignment(SwingConstants.CENTER);
				lblnumber.setForeground(new Color(245, 255, 250));
				lblnumber.setBounds(50, 300, 250, 16);
				edit_issue.getContentPane().add(lblnumber);

				final JTextField year = new JTextField();
				year.setColumns(10);
				year.setText(Integer.toString(current_issue.getYear()));
				year.setBounds(50, 364, 250, 26);
				edit_issue.getContentPane().add(year);
				JLabel lblyear = new JLabel("Year");
				lblyear.setHorizontalAlignment(SwingConstants.CENTER);
				lblyear.setForeground(new Color(245, 255, 250));
				lblyear.setBounds(50, 347, 250, 16);
				edit_issue.getContentPane().add(lblyear);

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				JLabel lblDateAccepted = new JLabel("Date Accepted");
				lblDateAccepted.setHorizontalAlignment(SwingConstants.CENTER);
				lblDateAccepted.setForeground(new Color(245, 255, 250));
				lblDateAccepted.setBounds(80, 394, width_small - 161, 16);
				edit_issue.getContentPane().add(lblDateAccepted);

				final JXDatePicker datePicker = new JXDatePicker();
				datePicker.setFormats(sdf);

				datePicker.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println(datePicker.getDate());
					}
				});
				;
				datePicker.setBounds(100, 410, width_small - 200, 30);

				datePicker.setDate(current_issue.getDate_accepted());
				// panel.add(label);
				edit_issue.getContentPane().add(datePicker);
				JLabel lblDatePublished = new JLabel("Date Published");
				lblDatePublished.setHorizontalAlignment(SwingConstants.CENTER);
				lblDatePublished.setForeground(new Color(245, 255, 250));
				lblDatePublished.setBounds(80, 441, width_small - 161, 16);
				edit_issue.getContentPane().add(lblDatePublished);

				final JXDatePicker datePickerPublished = new JXDatePicker();
				datePickerPublished.setFormats(sdf);

				datePickerPublished.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println(datePickerPublished.getDate());
					}
				});
				;
				datePickerPublished.setDate(current_issue.getDate_published());
				datePickerPublished.setBounds(100, 460, width_small - 200, 30);
				// panel.add(label);
				edit_issue.getContentPane().add(datePickerPublished);

				JLabel lblShowDisplay = new JLabel("---- Display Values ----");
				lblShowDisplay.setForeground(new Color(245, 255, 250));
				lblShowDisplay.setHorizontalAlignment(SwingConstants.CENTER);
				lblShowDisplay.setBounds(340, 170, 250, 16);
				edit_issue.getContentPane().add(lblShowDisplay);

				final JTextField show_title = new JTextField(current_issue.getShow_title());
				show_title.setBounds(340, 218, 250, 26);
				edit_issue.getContentPane().add(show_title);
				show_title.setColumns(10);

				JLabel lblShowTitleText = new JLabel("Show Title");
				lblShowTitleText.setForeground(new Color(245, 255, 250));
				lblShowTitleText.setHorizontalAlignment(SwingConstants.CENTER);
				lblShowTitleText.setBounds(340, 200, 250, 16);
				edit_issue.getContentPane().add(lblShowTitleText);

				final JTextField show_volume = new JTextField(Integer.toString(current_issue.getShow_volume()));
				show_volume.setBounds(340, 270, 250, 26);
				edit_issue.getContentPane().add(show_volume);
				show_volume.setColumns(10);

				JLabel lblShowVolume = new JLabel("Show Volume");
				lblShowVolume.setForeground(new Color(245, 255, 250));
				lblShowVolume.setHorizontalAlignment(SwingConstants.CENTER);
				lblShowVolume.setBounds(340, 250, 250, 16);
				edit_issue.getContentPane().add(lblShowVolume);

				final JTextField show_number = new JTextField(Integer.toString(current_issue.getShow_number()));
				show_number.setBounds(340, 317, 250, 26);
				edit_issue.getContentPane().add(show_number);
				show_number.setColumns(10);

				JLabel lblShowNumber = new JLabel("Show Number");
				lblShowNumber.setForeground(new Color(245, 255, 250));
				lblShowNumber.setHorizontalAlignment(SwingConstants.CENTER);
				lblShowNumber.setBounds(340, 300, 250, 16);
				edit_issue.getContentPane().add(lblShowNumber);

				final JTextField show_year = new JTextField(Integer.toString(current_issue.getShow_year()));
				show_year.setBounds(340, 364, 250, 26);
				edit_issue.getContentPane().add(show_year);
				show_year.setColumns(10);

				JLabel lblShowYear = new JLabel("Show Year");
				lblShowYear.setForeground(new Color(245, 255, 250));
				lblShowYear.setHorizontalAlignment(SwingConstants.CENTER);
				lblShowYear.setBounds(340, 347, 250, 16);
				edit_issue.getContentPane().add(lblShowYear);
				JButton btnSubmit = new JButton("Save");

				Action actionSubmit = new AbstractAction() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Boolean validation = true;
						int entered_volume = 0;
						int entered_number = 0;
						int entered_year = 0;
						int entered_show_volume = 0;
						int entered_show_number = 0;
						int entered_show_year = 0;
						try {

							entered_volume = Integer.parseInt(volume.getText());
							entered_number = Integer.parseInt(number.getText());
							entered_year = Integer.parseInt(year.getText());

							number.setBackground(new Color(255, 255, 255));
							number.setForeground(new Color(0, 0, 0));
							volume.setBackground(new Color(255, 255, 255));
							volume.setForeground(new Color(0, 0, 0));
							year.setBackground(new Color(255, 255, 255));
							year.setForeground(new Color(0, 0, 0));
						} catch (Exception ex) {
							validation = false;
							number.setBackground(new Color(255, 0, 0));
							number.setForeground(new Color(255, 255, 255));
							volume.setBackground(new Color(255, 0, 0));
							volume.setForeground(new Color(255, 255, 255));
							year.setBackground(new Color(255, 0, 0));
							year.setForeground(new Color(255, 255, 255));
							JOptionPane.showMessageDialog(null, "Use only numbers in fields: Volume, Number, Year ");
						}
						try {
							entered_show_volume = Integer.parseInt(show_volume.getText());
							entered_show_number = Integer.parseInt(show_number.getText());
							entered_show_year = Integer.parseInt(show_year.getText());
							show_number.setBackground(new Color(255, 255, 255));
							show_number.setForeground(new Color(0, 0, 0));
							show_volume.setBackground(new Color(255, 255, 255));
							show_volume.setForeground(new Color(0, 0, 0));
							show_year.setBackground(new Color(255, 255, 255));
							show_year.setForeground(new Color(0, 0, 0));
						} catch (Exception ex) {
							validation = false;
							show_number.setBackground(new Color(255, 0, 0));
							show_number.setForeground(new Color(255, 255, 255));
							show_volume.setBackground(new Color(255, 0, 0));
							show_volume.setForeground(new Color(255, 255, 255));
							show_year.setBackground(new Color(255, 0, 0));
							show_year.setForeground(new Color(255, 255, 255));
							JOptionPane.showMessageDialog(null,
									"Use only numbers in fields: Show_Volume, Show_Number, Show_Year ");
						}
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
						try {

							String test_accepted = sdf.format(datePicker.getDate());
							String test_published = sdf.format(datePickerPublished.getDate());

						} catch (Exception ex) {
							validation = false;
							JOptionPane.showMessageDialog(null,
									"Use dates from calendar for fields: Date Published and Date Accepted");
						}

						if (validation) {

							current_issue.setTitle(title.getText());
							current_issue.setVolume(entered_volume);
							current_issue.setNumber(entered_number);
							current_issue.setYear(entered_year);
							current_issue.setDate_published(datePickerPublished.getDate());
							current_issue.setDate_accepted(datePicker.getDate());
							current_issue.setShow_title(show_title.getText());
							current_issue.setShow_volume(entered_show_volume);
							current_issue.setShow_year(entered_show_year);
							current_issue.setShow_number(entered_show_number);
							edit_issue.dispose();
							issue_storage.put(issue_id, current_issue);
							issues.dispose();
							dashboard();
						}

					}
				};
				title.addActionListener(actionSubmit);
				volume.addActionListener(actionSubmit);
				number.addActionListener(actionSubmit);
				year.addActionListener(actionSubmit);
				datePicker.addActionListener(actionSubmit);
				btnSubmit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Boolean validation = true;
						int entered_volume = 0;
						int entered_number = 0;
						int entered_year = 0;
						int entered_show_volume = 0;
						int entered_show_number = 0;
						int entered_show_year = 0;
						try {

							entered_volume = Integer.parseInt(volume.getText());
							entered_number = Integer.parseInt(number.getText());
							entered_year = Integer.parseInt(year.getText());

							number.setBackground(new Color(255, 255, 255));
							number.setForeground(new Color(0, 0, 0));
							volume.setBackground(new Color(255, 255, 255));
							volume.setForeground(new Color(0, 0, 0));
							year.setBackground(new Color(255, 255, 255));
							year.setForeground(new Color(0, 0, 0));
						} catch (Exception ex) {
							validation = false;
							number.setBackground(new Color(255, 0, 0));
							number.setForeground(new Color(255, 255, 255));
							volume.setBackground(new Color(255, 0, 0));
							volume.setForeground(new Color(255, 255, 255));
							year.setBackground(new Color(255, 0, 0));
							year.setForeground(new Color(255, 255, 255));
							JOptionPane.showMessageDialog(null, "Use only numbers in fields: Volume, Number, Year ");
						}
						try {
							entered_show_volume = Integer.parseInt(show_volume.getText());
							entered_show_number = Integer.parseInt(show_number.getText());
							entered_show_year = Integer.parseInt(show_year.getText());
							show_number.setBackground(new Color(255, 255, 255));
							show_number.setForeground(new Color(0, 0, 0));
							show_volume.setBackground(new Color(255, 255, 255));
							show_volume.setForeground(new Color(0, 0, 0));
							show_year.setBackground(new Color(255, 255, 255));
							show_year.setForeground(new Color(0, 0, 0));
						} catch (Exception ex) {
							validation = false;
							show_number.setBackground(new Color(255, 0, 0));
							show_number.setForeground(new Color(255, 255, 255));
							show_volume.setBackground(new Color(255, 0, 0));
							show_volume.setForeground(new Color(255, 255, 255));
							show_year.setBackground(new Color(255, 0, 0));
							show_year.setForeground(new Color(255, 255, 255));
							JOptionPane.showMessageDialog(null,
									"Use only numbers in fields: Show_Volume, Show_Number, Show_Year ");
						}
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
						try {

							String test_accepted = sdf.format(datePicker.getDate());
							String test_published = sdf.format(datePickerPublished.getDate());

						} catch (Exception ex) {
							validation = false;
							JOptionPane.showMessageDialog(null,
									"Use dates from calendar for fields: Date Published and Date Accepted");
						}

						if (validation) {

							current_issue.setTitle(title.getText());
							current_issue.setVolume(entered_volume);
							current_issue.setNumber(entered_number);
							current_issue.setYear(entered_year);
							current_issue.setDate_published(datePickerPublished.getDate());
							current_issue.setDate_accepted(datePicker.getDate());
							current_issue.setShow_title(show_title.getText());
							current_issue.setShow_volume(entered_show_volume);
							current_issue.setShow_year(entered_show_year);
							current_issue.setShow_number(entered_show_number);
							edit_issue.dispose();
							issue_storage.put(issue_id, current_issue);
							issues.dispose();
							dashboard();
						}

					}
				});
				if (height_small - 150 > 300) {
					btnSubmit.setBounds(((width_small / 3) * 2) / 2, height_small - 70, width_small / 3, 29);
				} else {
					btnSubmit.setBounds(((width_small / 3) * 2) / 2, 380, width_small / 3, 29);
				}

				edit_issue.getContentPane().add(btnSubmit);

				JLabel lblApiInformation = new JLabel("Issue Details");
				lblApiInformation.setBackground(new Color(2, 216, 138));
				lblApiInformation.setHorizontalAlignment(SwingConstants.CENTER);
				lblApiInformation.setForeground(new Color(255, 255, 255));
				lblApiInformation.setFont(new Font("Dialog", Font.BOLD, 20));
				lblApiInformation.setBounds((width_small / 2) - 145, 108, 309, 40);
				lblApiInformation.setOpaque(true);
				edit_issue.getContentPane().add(lblApiInformation);

				Panel panel = new Panel();
				panel.setBackground(new Color(204, 51, 51));
				panel.setBounds(0, 54, width_small, 5);
				edit_issue.getContentPane().add(panel);

				edit_issue.setVisible(true);// making the frame visible
				edit_issue.repaint();
				issue_screens.put(issue_id, edit_issue);
				Panel panel_2 = new Panel();
				panel_2.setBackground(new Color(6, 141, 91));
				panel_2.setBounds(0, 150, width_small, 5);
				edit_issue.getContentPane().add(panel_2);
				Panel panel_1 = new Panel();
				panel_1.setBackground(new Color(2, 216, 138));
				panel_1.setBounds(0, 105, width_small, 45);
				edit_issue.getContentPane().add(panel_1);
			}
		} else {
			login("dashboard");
		}
	}

	public void issue(final int issue_id) {
		if (logged_in) {
			if (issue_screens.containsKey(issue_id) && !issue_screens.get(issue_id).isVisible()) {

				final JFrame articles = new JFrame();
				articles.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				if (height >= 640 && width >= 960) {
					articles.setSize(width, height);
				} else {
					width = 960;
					height = 640;
					articles.setSize(960, 640);
				}
				HashMap<Integer, JFrame> issue_articles = new HashMap<Integer, JFrame>();
				articles.getContentPane().setBackground(new Color(128, 128, 128));

				articles.setLocationRelativeTo(null);
				articles.setTitle("Issue <" + Integer.toString(issue_id) + ">");
				articles.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						// database_save();
					}
				});
				Date current = new Date();

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				Issue current_issue = issue_storage.get(issue_id);
				HashMap<Integer, Article> current_articles = current_issue.getArticles_list();
				Set<Integer> art_keys = current_articles.keySet();
				ArrayList<List<Object>> rowData = new ArrayList<List<Object>>();
				Object[][] rows = new Object[art_keys.size()][11];
				int i = 0;
				for (int id : art_keys) {
					ArrayList<Object> data = new ArrayList<Object>();
					issue_articles.put(id, new JFrame());

					data.add(Integer.toString(current_articles.get(id).getId()));
					data.add(Integer.toString(issue_id));
					data.add(Integer.toString((current_articles.get(id).getSection_id())));
					data.add(current_articles.get(id).getTitle());
					data.add(Integer.toString(current_articles.get(id).getPages()));
					data.add(current_articles.get(id).getAbstract_text());
					data.add(sdf.format(current));
					data.add("View");
					data.add("Edit");
					data.add("Delete");
					Object[] row = { current_articles.get(id).getId(), issue_id,
							current_articles.get(id).getSection_id(), current_articles.get(id).getTitle(),
							current_articles.get(id).getPages(), current_articles.get(id).getAbstract_text(),
							sdf.format(current_articles.get(id).getDate_accepted()),
							sdf.format(current_articles.get(id).getDate_published()), "View", "Edit", "Delete" };
					rows[i] = row;
					i++;
					rowData.add(data);

				}

				article_screens.put(issue_id, issue_articles);
				Object columnNames[] = { "ID", "Issue", "Section", "Title", "Pages", "Abstract", "Date Accepted",
						"Date Published", "", "", "" };

				articles.getContentPane().setLayout(null);

				final JButton btnSync = new JButton("Sync");
				btnSync.setBounds(width - 155, 21, 70, 24);
				btnSync.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							httpClient.execute(new HttpGet("http://127.0.0.1:8000/issues/"));
						} catch (ClientProtocolException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						CookieStore cookieStore = httpClient.getCookieStore();
						List<org.apache.http.cookie.Cookie> cookies =  cookieStore.getCookies();
						System.out.println( cookies);
						for (org.apache.http.cookie.Cookie cookie: cookies) {
						   System.out.println( cookie.getValue());
						    
						}
						Gson gson= new Gson();
						HttpPost post = new HttpPost("http://127.0.0.1:8000/issues/");
						StringEntity postingString = null;
						try {
							postingString = new StringEntity(gson.toJson(current_issue));
						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}//convert your pojo to   json
						post.setEntity(postingString);
						System.out.println(postingString);
						post.setHeader("Content-type", "application/json");
						try {
							HttpResponse  response = httpClient.execute(post);

									System.out.println(response);
						} catch (ClientProtocolException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				articles.getContentPane().add(btnSync);

				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				scrollPane.setBounds(15, height / 16 * 7, width - 30, (height - 130) - (height / 16 * 7) - 10);
				articles.getContentPane().add(scrollPane);

				DefaultTableModel dtm = new DefaultTableModel(rows, columnNames);

				final JXTable article_table = new JXTable(dtm) {
					// **** Source:
					// http://stackoverflow.com/questions/9467093/how-to-add-a-tooltip-to-a-cell-in-a-jtable
					// ****//
					// Implement table cell tool tips.
					public String getToolTipText(MouseEvent e) {
						String tip = null;
						java.awt.Point p = e.getPoint();
						int rowIndex = rowAtPoint(p);
						int colIndex = columnAtPoint(p);

						try {
							tip = getValueAt(rowIndex, colIndex).toString();
						} catch (RuntimeException e1) {
							// catch null pointer exception if mouse is over an
							// empty line
						}

						return tip;
					}
				};
				article_table.setAutoResizeMode(article_table.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
				// reference:
				// https://svn.java.net/svn/swinglabs-demos~svn/trunk/src/java/org/jdesktop/demo/sample/
				final JTextField filter = new JTextField("");
				filter.setBounds(150, height / 16 * 7 - 27, 117, 25);

				final JButton search = new JButton("Search");
				final JButton clear = new JButton("Clear");
				search.setBounds(268, height / 16 * 7 - 27, 90, 25);

				clear.setBounds(355, height / 16 * 7 - 27, 90, 25);
				;
				articles.getContentPane().add(filter);
				articles.getContentPane().add(search);
				articles.getContentPane().add(clear);
				filter.setAction(new AbstractAction("Search") {
					public void actionPerformed(ActionEvent e) {

						String searchString = filter.getText().trim();
						if (searchString.length() > 0) {
							article_table.setRowFilter(RowFilters.regexFilter(0, searchString));
						} else {
							article_table.setRowFilter(null);
						}
					}
				});
				search.setAction(new AbstractAction("Search") {
					public void actionPerformed(ActionEvent e) {

						String searchString = filter.getText().trim();
						if (searchString.length() > 0) {
							article_table.setRowFilter(RowFilters.regexFilter(0, searchString));
						}
					}
				});
				clear.setAction(new AbstractAction("Clear") {
					public void actionPerformed(ActionEvent e) {
						article_table.setRowFilter(null);
					}
				});
				article_table.setAutoCreateRowSorter(true);
				scrollPane.setViewportView(article_table);
				article_table.getColumnModel().getColumn(6).setMinWidth(50);
				article_table.getColumnModel().getColumn(7).setMinWidth(40);
				article_table.getColumnModel().getColumn(8).setMinWidth(40);
				article_table.getColumnModel().getColumn(9).setMinWidth(50);
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
				new Timer(delay * 2, taskPerformer1).start();
				DefaultTableModel model = new DefaultTableModel(rows, columnNames);
				Action delete = new AbstractAction() {
					public void actionPerformed(ActionEvent e) {
						JTable table = (JTable) e.getSource();
						int modelRow = Integer.valueOf(e.getActionCommand());
						// JOptionPane.showMessageDialog(null, "Deleted");
						int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this article?",
								"Delete ?", JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {
							int article_row = table.getSelectedRow();
							int selectedColumnIndex = 0;
							Object selectedObject = (Object) table.getModel().getValueAt(article_row,
									selectedColumnIndex);
							int selected_article = (int) selectedObject;
							Issue current_issue = issue_storage.get(issue_id);
							current_issue.remove_article(selected_article);
							issue_storage.put(issue_id, current_issue);
							System.out.println("Article ID: " + selected_article + " "
									+ article_screens.get(issue_id).containsKey(selected_article));
							System.out.println(article_screens.get(issue_id).size());

							if (article_screens.get(issue_id).get(selected_article).isVisible()) {
								article_screens.get(issue_id).get(selected_article).dispose();
							}
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

						articles.dispose();
						int modelRow = Integer.valueOf(e.getActionCommand());
						int article_row = table.getSelectedRow();
						int selectedColumnIndex = 0;
						Object selectedObject = (Object) table.getModel().getValueAt(article_row, selectedColumnIndex);
						int selected_article = (int) selectedObject;
						if (article_screens.get(issue_id).get(selected_article).isVisible()) {
							article_screens.get(issue_id).get(selected_article).dispose();
						}
						article(issue_id, selected_article);
						// /
						// ((DefaultTableModel)table.getModel()).removeRow(modelRow);
					}
				};
				Action edit = new AbstractAction() {
					public void actionPerformed(ActionEvent e) {
						JTable table = (JTable) e.getSource();
						articles.dispose();
						int modelRow = Integer.valueOf(e.getActionCommand());
						int article_row = table.getSelectedRow();
						int selectedColumnIndex = 0;
						Object selectedObject = (Object) table.getModel().getValueAt(article_row, selectedColumnIndex);
						int selected_article = (int) selectedObject;
						if (article_screens.get(issue_id).get(selected_article).isVisible()) {
							article_screens.get(issue_id).get(selected_article).dispose();
						}
						edit_article(issue_id, selected_article);
						// /
						// ((DefaultTableModel)table.getModel()).removeRow(modelRow);
					}
				};
				ButtonColumn buttonColumn = new ButtonColumn(article_table, view, 8);
				ButtonColumn buttonColumn2 = new ButtonColumn(article_table, edit, 9);
				ButtonColumn buttonColumn3 = new ButtonColumn(article_table, delete, 10);

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

						add_article(issue_id);

					}
				});
				btnAdd.setBounds(width - 150, height / 16 * 7 - 27, 117, 25);
				articles.getContentPane().add(btnAdd);
				issue_screens.put(issue_id, articles);

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
				Issue row_issue = issue_storage.get(issue_id);
				Object issue_rowData[][] = { { row_issue.getId(), row_issue.getTitle(), row_issue.getVolume(),
						row_issue.getNumber(), row_issue.getYear(), sdf.format(row_issue.getDate_accepted()),
						sdf.format(row_issue.getDate_published()) } };
				Object issue_columnNames[] = { "ID", "Title", "Volume", "Number", "Year", "Date Accepted",
						"Date Published" };

				DefaultTableModel issue_dtm = new DefaultTableModel(issue_rowData, issue_columnNames);

				final JTable issuedetails = new JTable(issue_dtm) {
					// **** Source:
					// http://stackoverflow.com/questions/9467093/how-to-add-a-tooltip-to-a-cell-in-a-jtable
					// ****//
					// Implement table cell tool tips.
					public String getToolTipText(MouseEvent e) {
						String tip = null;
						java.awt.Point p = e.getPoint();
						int rowIndex = rowAtPoint(p);
						int colIndex = columnAtPoint(p);

						try {
							tip = getValueAt(rowIndex, colIndex).toString();
						} catch (RuntimeException e1) {
							// catch null pointer exception if mouse is over an
							// empty line
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
				lblIssueDetails.setBounds((width - 95) / 2, 118, 125, 30);
				lblIssueDetails.setOpaque(true);
				articles.getContentPane().add(lblIssueDetails);
				articles.setVisible(true);
				articles.repaint();
			}

		} else {
			login("dashboard");
		}
	}

	public void article(final int issue_id, final int article_id) {
		if (logged_in) {
			if (article_screens.containsKey(issue_id) && article_screens.get(issue_id).containsKey(article_id)
					&& !article_screens.get(issue_id).get(article_id).isVisible()) {
				Article current_article = issue_storage.get(issue_id).getArticles_list().get(article_id);
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
				String setting_meta=list_settings.get("Metadata");
				Metadata meta = null;
				if (metadata_storage.containsKey(article_id)) {
					meta = metadata_storage.get(article_id);
				}
				article.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				// article.setSize(width_small, height_small);
				article.setSize(width_small, height_small);
				article.getContentPane().setBackground(new Color(128, 128, 128));

				article.setLocationRelativeTo(null);
				article.setTitle("Article <" + article_id + "> Details");
				article.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {

						issue(issue_id);
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

						if (issue_screens.get(issue_id) == null) {
							article.dispose();
							issue(issue_id);
						} else if (!issue_screens.get(issue_id).isVisible()) {
							article.dispose();
							issue_screens.get(issue_id).setVisible(true);
						} else {
							article.dispose();
						}
					}
				});
				btnGoBack.setBounds(width_small - 150, 17, 117, 30);
				article.getContentPane().add(btnGoBack);
				JButton btnEdit = new JButton("Edit");
				btnEdit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						article.dispose();
						edit_article(issue_id, article_id);
					}
				});
				btnEdit.setBounds(25, 17, 117, 30);
				article.getContentPane().add(btnEdit);
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
				
				JPanel panelMetadata = new JPanel();
				panelMetadata.setBackground(SystemColor.window);
				panelMetadata.setBounds(50, height_small - 260, 300, 307);
				panelMetadata.setLayout(null);
				panelMetadata.setAutoscrolls(true);

				JLabel lblCompetingInterests = new JLabel("Competing Interests");
				lblCompetingInterests.setBounds(35, 40, 145, 15);
				panelMetadata.add(lblCompetingInterests);
				JTextArea txtCompetingInterests = new JTextArea();
				if (meta != null) {
					txtCompetingInterests.setText(meta.getCompeting_interests());
				}
				txtCompetingInterests.setColumns(10);
				txtCompetingInterests.setEditable(false);
				txtCompetingInterests.setBounds(35, 70, 145, 100);

				JScrollPane cmptinterests = new JScrollPane(txtCompetingInterests);
				cmptinterests.setBounds(35, 70, 145, 100);
				panelMetadata.add(cmptinterests);
				// scrollSettings.setViewportView(scrollFrame);

				JLabel lblFunding = new JLabel("Funding");
				lblFunding.setBounds(35, 175, 145, 15);
				panelMetadata.add(lblFunding);
				JTextArea txtFunding = new JTextArea();
				if (meta != null) {
					txtFunding.setText(meta.getFunding());
				}
				txtFunding.setColumns(10);
				txtFunding.setEditable(false);
				txtFunding.setBounds(35, 195, 145, 100);

				JScrollPane funding = new JScrollPane(txtFunding);
				funding.setBounds(35, 195, 145, 100);
				panelMetadata.add(funding);
				panelMetadata.setPreferredSize(new Dimension(240, 380));

				panelMetadata.setSize(new Dimension(240, 380));
				JButton btnAddMetadata = new JButton("View Metadata");
				btnAddMetadata.setBounds(40, height_small - 150, 160, 50);
				btnAddMetadata.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int result = JOptionPane.showConfirmDialog(null, panelMetadata, "Edit Authors",
								JOptionPane.OK_CANCEL_OPTION);
						if (result == JOptionPane.OK_OPTION) {
						}
					}
				});
				if(setting_meta.compareToIgnoreCase("true")==0){
				panel.add(btnAddMetadata);
				article.getContentPane().add(btnAddMetadata);}
				JPanel panel3 = new JPanel();
				panel3.setBackground(SystemColor.window);
				panel3.setBounds(50, height_small - 260, 320, 120);
				article.getContentPane().add(panel3);
				panel3.setLayout(null);
				panel3.setAutoscrolls(true);

				panel3.setPreferredSize(new Dimension(320, settings_height));
				JScrollPane abstractSection = new JScrollPane(panel3);
				panel3.setAutoscrolls(true);

				JLabel lblNewLabel_1 = new JLabel("Abstract");
				lblNewLabel_1.setBounds(16, 6, 61, 16);
				panel3.add(lblNewLabel_1);

				JTextArea lblAbstract = new JTextArea(current_article.getAbstract_text());
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
				panel6.setBounds(50, 107, 500 * 2, 307);
				article.getContentPane().add(panel6);
				panel6.setLayout(null);
				panel6.setAutoscrolls(true);

				// size
				JScrollPane authorSection = new JScrollPane(panel6);
				panel6.setAutoscrolls(true);
				authorSection.setHorizontalScrollBarPolicy(authorSection.HORIZONTAL_SCROLLBAR_ALWAYS);
				JLabel lblNewLabel_3 = new JLabel("Author Information");
				lblNewLabel_3.setBounds(15, 6, 156, 16);
				panel6.add(lblNewLabel_3);

				ArrayList<Author> authors = current_article.getAuthors();

				int author_x = 16;
				int author_y = 60;
				int separation_horizontal = 205;
				int separation_vertical = 30;
				int label_field_separation = 4;
				for (int i = 0; i < authors.size(); i++) {
					separation_horizontal = 205 * i;
					author_x = author_x + separation_horizontal;
					Author author = authors.get(i);

					Boolean primary = author_primary_storage.get(article_id).get(author.getId());

					if (primary) {
						JLabel author_num = new JLabel(Integer.toString(i + 1));
						author_num.setBounds(20 + author_x, 35, 40, 16);
						panel6.add(author_num);
						JLabel author_primary = new JLabel("- PRIMARY -");
						author_primary.setBounds(60 + author_x, 35, 85, 16);
						panel6.add(author_primary);
					} else {
						JLabel author_num = new JLabel(Integer.toString(i + 1));
						author_num.setBounds(80 + author_x, 35, 40, 16);
						panel6.add(author_num);
					}

					JLabel field_label = new JLabel("First name:");
					field_label.setBounds(author_x, author_y, 75, 30); // white
																		// box
					field_label.setOpaque(true);
					panel6.add(field_label);
					JLabel field = new JLabel(author.getFirst_name());
					field.setBounds(author_x + 75 + label_field_separation, author_y, 100, 30); // white
																								// box
					field.setOpaque(true);
					panel6.add(field);
					author_y = author_y + separation_vertical;

					field_label = new JLabel("Middle name:");
					field_label.setBounds(author_x, author_y, 75, 30); // white
																		// box
					field_label.setOpaque(true);
					panel6.add(field_label);
					field = new JLabel(author.getMiddle_name());
					field.setBounds(author_x + 75 + label_field_separation, author_y, 100, 30); // white
																								// box
					field.setOpaque(true);
					panel6.add(field);
					author_y = author_y + separation_vertical;

					field_label = new JLabel("Last name:");
					field_label.setBounds(author_x, author_y, 75, 30); // white
																		// box
					field_label.setOpaque(true);
					panel6.add(field_label);
					field = new JLabel(author.getLast_name());
					field.setBounds(author_x + 75 + label_field_separation, author_y, 100, 30); // white
																								// box
					field.setOpaque(true);
					panel6.add(field);
					author_y = author_y + separation_vertical;

					field_label = new JLabel("Email:");
					field_label.setBounds(author_x, author_y, 75, 30); // white
																		// box
					field_label.setOpaque(true);
					panel6.add(field_label);
					field = new JLabel(author.getEmail());
					field.setBounds(author_x + 75 + label_field_separation, author_y, 100, 30); // white
																								// box
					field.setOpaque(true);
					panel6.add(field);
					author_y = author_y + separation_vertical;

					field_label = new JLabel("Affiliation:");
					field_label.setBounds(author_x, author_y, 75, 30); // white
																		// box
					field_label.setOpaque(true);
					panel6.add(field_label);
					field = new JLabel(author.getAffiliation());
					field.setBounds(author_x + 75 + label_field_separation, author_y, 100, 30); // white
																								// box
					field.setOpaque(true);
					panel6.add(field);
					author_y = author_y + separation_vertical;

					field_label = new JLabel("Bio:");
					field_label.setBounds(author_x, author_y, 75, 30); // white
																		// box
					field_label.setOpaque(true);

					panel6.add(field_label);
					JTextArea field_a = new JTextArea(author.getBio());
					field_a.setBounds(author_x + 75 + label_field_separation, author_y, 100, 60); // white
					field_a.setEditable(false);
					field_a.setBackground(SystemColor.window);
					field_a.setOpaque(true);
					JScrollPane scroll_bio = new JScrollPane(field_a);
					scroll_bio.setBounds(author_x + 75 + label_field_separation, author_y, 100, 60);
					scroll_bio.setPreferredSize(new Dimension(250, 200));
					panel6.add(scroll_bio);
					author_y = author_y + separation_vertical + 30;

					field_label = new JLabel("OrcID:");
					field_label.setBounds(author_x, author_y, 75, 30); // white
																		// box
					field_label.setOpaque(true);
					panel6.add(field_label);
					field = new JLabel(author.getOrcid());
					field.setBounds(author_x + 75 + label_field_separation, author_y, 100, 30); // white
																								// box
					field.setOpaque(true);
					panel6.add(field);
					author_y = author_y + separation_vertical;

					field_label = new JLabel("Department:");
					field_label.setBounds(author_x, author_y, 75, 30); // white
																		// box
					field_label.setOpaque(true);
					panel6.add(field_label);
					field = new JLabel(author.getDepartment());
					field.setBounds(author_x + 75 + label_field_separation, author_y, 100, 30); // white
																								// box
					field.setOpaque(true);
					panel6.add(field);
					author_y = author_y + separation_vertical;

					field_label = new JLabel("Country:");
					field_label.setBounds(author_x, author_y, 75, 30); // white
																		// box
					field_label.setOpaque(true);
					panel6.add(field_label);
					field = new JLabel(author.getCountry());
					field.setBounds(author_x + 75 + label_field_separation, author_y, 100, 30); // white
																								// box
					field.setOpaque(true);
					panel6.add(field);
					author_y = author_y + separation_vertical;

					author_y = 60;
					author_x = 16;

				}
				panel6.setPreferredSize(new Dimension(210 * authors.size(), 500)); // scrollable

				/*
				 * JTextArea lblAuthorInfo = new JTextArea(
				 * "First Name: Pete\tFirst Name: Bob 2 \nLast Name: User\tLast Name: User 2 \n "
				 * ); lblAuthorInfo.setEditable(false);
				 * lblAuthorInfo.setBounds(16, 34, 205 * 2, 175); // white box
				 * lblAuthorInfo.setOpaque(true);
				 * lblAuthorInfo.setBackground(SystemColor.window);
				 * panel6.add(lblAuthorInfo);
				 */
				authorSection.setHorizontalScrollBarPolicy(authorSection.HORIZONTAL_SCROLLBAR_ALWAYS);

				authorSection.setPreferredSize(new Dimension(600 * authors.size(), 8));
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

				JLabel lblIssueId = new JLabel(Integer.toString(issue_id));
				lblIssueId.setBounds(160, 48, 94, 30);
				panel.add(lblIssueId);
				lblIssueId.setForeground(Color.BLACK);
				lblIssueId.setFont(new Font("Dialog", Font.BOLD, 14));
				lblIssueId.setText(Integer.toString(issue_id));

				final JLabel lblDateAccepted = new JLabel("Date Accepted:");
				lblDateAccepted.setForeground(Color.BLACK);
				lblDateAccepted.setFont(new Font("Dialog", Font.BOLD, 14));
				lblDateAccepted.setBounds(24, 199, 150, 30);
				panel.add(lblDateAccepted);
				final JLabel lblDatePublished = new JLabel("Date Published:");
				lblDatePublished.setForeground(Color.BLACK);
				lblDatePublished.setFont(new Font("Dialog", Font.BOLD, 14));
				lblDatePublished.setBounds(24, 231, 150, 30);
				panel.add(lblDatePublished);
				Date current = new Date();

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

				JLabel lblDate = new JLabel(sdf.format(current_article.getDate_published()));
				lblDate.setVerticalAlignment(SwingConstants.TOP);
				lblDate.setForeground(Color.BLACK);
				lblDate.setFont(new Font("Dialog", Font.BOLD, 14));
				lblDate.setBounds(160, 240, 125, 30);
				panel.add(lblDate);
				JLabel lblDateAccept = new JLabel(sdf.format(current_article.getDate_accepted()));
				lblDateAccept.setVerticalAlignment(SwingConstants.TOP);
				lblDateAccept.setForeground(Color.BLACK);
				lblDateAccept.setFont(new Font("Dialog", Font.BOLD, 14));
				lblDateAccept.setBounds(160, 207, 125, 30);
				panel.add(lblDateAccept);
				JLabel lblArticleId = new JLabel(Integer.toString(current_article.getId()));
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

				JLabel lblPageNum = new JLabel(Integer.toString(current_article.getPages()));
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
				JTextArea lblTitleText = new JTextArea(current_article.getTitle());
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

				JLabel lblSectionId = new JLabel(Integer.toString(current_article.getSection_id()));
				lblSectionId.setForeground(Color.BLACK);
				lblSectionId.setFont(new Font("Dialog", Font.BOLD, 14));
				lblSectionId.setBounds(160, 81, 94, 30);
				panel.add(lblSectionId);

				JLabel lblSection = new JLabel("Section:");
				lblSection.setForeground(Color.BLACK);
				lblSection.setFont(new Font("Dialog", Font.BOLD, 14));
				lblSection.setBounds(24, 80, 94, 30);
				panel.add(lblSection);
				Panel panel10 = new Panel();
				panel10.setBackground(new Color(153, 102, 51));
				panel10.setBounds(115, 315, 225, 160);

				JPanel panel11 = new JPanel();
				panel11.setBounds(265, 285, 265, 190 + 100 * file_storage.keySet().size());
				panel11.setLayout(null);
				panel11.setAutoscrolls(true);
				panel11.setPreferredSize(new Dimension(250, 190 + 280 * file_storage.keySet().size()));
				article.getContentPane().add(panel11);
				String label_text = "";
				System.out.println(file_storage.keySet().toString());
				if (file_storage.containsKey(article_id)) {
					HashMap<Integer, ArticleFile> files = file_storage.get(article_id);
					Set<Integer> keys = files.keySet();
					int y_f = 23;
					for (int key : keys) {
						ImageIcon deleteicon = new ImageIcon("src/lib/remove_xs.png");
						JButton btnDeleteFile = new JButton(deleteicon);
						btnDeleteFile.setMargin(new Insets(0, 0, 0, 0));
						btnDeleteFile.setBorder(null);
						btnDeleteFile.setFont(new Font("Dialog", Font.BOLD, 12));

						btnDeleteFile.setBounds(195, y_f, 40, 24);
						article.getContentPane().add(btnDeleteFile);

						ImageIcon saveicon = new ImageIcon("src/lib/save_xs.png");
						JButton btnSaveFile = new JButton(saveicon);
						btnSaveFile.setMargin(new Insets(0, 0, 0, 0));
						btnSaveFile.setBorder(null);
						btnSaveFile.setFont(new Font("Dialog", Font.BOLD, 12));

						btnSaveFile.setBounds(150, y_f, 40, 24);
						article.getContentPane().add(btnSaveFile);
						JLabel file_l = new JLabel(
								files.get(key).getPath().substring(files.get(key).getPath().lastIndexOf("/") + 1));
						file_l.setBounds(10, y_f, 120, 24);
						panel11.add(file_l);
						y_f = y_f + 20;
						btnSaveFile.setAction(new AbstractAction() {
							public void actionPerformed(ActionEvent e) {

								JFileChooser fileChooser = new JFileChooser();
								fileChooser.setDialogTitle("Specify a file to save");
								FileNameExtensionFilter file = new FileNameExtensionFilter(
										"Galleys (pdf,xml,html) or Images(jpg,png)", "pdf", "xml", "html", "jpg",
										"png");
								fileChooser.setSelectedFile(new File(files.get(key).getPath()
										.substring(files.get(key).getPath().lastIndexOf("/") + 1)));
								fileChooser.setFileFilter(file);
								// File new_f=new
								// File(files.get(key).getPath());
								int userSelection = fileChooser.showSaveDialog(panel);

								// fileChooser.setSelectedFile(new
								// File(files.get(key).getPath()));
								if (userSelection == JFileChooser.APPROVE_OPTION) {
									System.out.println("Save as file: " + fileChooser.getCurrentDirectory().getPath()
											+ files.get(key).getPath()
													.substring(files.get(key).getPath().lastIndexOf("/")));
									String output_path = fileChooser.getCurrentDirectory().getPath() + "/"
											+ fileChooser.getSelectedFile().getName();
									System.out.println(output_path);
									System.out.println(fileChooser.getSelectedFile().getName());
									// files.get(key).getPath().substring(files.get(key).getPath().lastIndexOf("/"));

									try {
										Files.copy(Paths.get(files.get(key).getPath()), Paths.get(output_path),
												StandardCopyOption.REPLACE_EXISTING);
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}

									// System.out.println("Save as file: " +
									// fileToSave.getAbsolutePath());
								}
							}
						});
						btnDeleteFile.setAction(new AbstractAction() {
							public void actionPerformed(ActionEvent e) {

								File f = new File(files.get(key).getPath());
								f.delete();
								HashMap<Integer, ArticleFile> deleted = file_storage.get(article_id);
								deleted.remove(key);
								file_storage.put(article_id, deleted);
								article.dispose();
								article(issue_id, article_id);

							}
						});
						btnDeleteFile.setIcon(deleteicon);
						btnSaveFile.setIcon(saveicon);
						panel11.add(btnSaveFile);
						panel11.add(btnDeleteFile);
						String path = files.get(key).getPath();
						label_text = label_text + path.substring(path.lastIndexOf("/") + 1) + "\n";
					}
				}
				JTextArea lblFile = new JTextArea(label_text);
				lblFile.setForeground(Color.WHITE);
				lblFile.setEnabled(false);
				lblFile.setBounds(115, 312, 225, 160);
				lblFile.setToolTipText("");
				JScrollPane fileSection = new JScrollPane(panel11);

				fileSection.setPreferredSize(new Dimension(300 * 2, 100 * file_storage.size()));
				fileSection.setBounds(20, 312, 265, 160);
				fileSection.add(panel10);
				fileSection.createHorizontalScrollBar();
				panel.add(fileSection);
				ArrayList<File> uploaded_files = new ArrayList<File>();
				JFileChooser chooser = new JFileChooser();
				JButton select = new JButton("Browse");
				select.setEnabled(false);
				select.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						FileNameExtensionFilter file = new FileNameExtensionFilter(
								"Galleys (pdf,xml,html) or Images(jpg,png)", "pdf", "xml", "html", "jpg", "png");
						chooser.setFileFilter(file);
						chooser.setMultiSelectionEnabled(true);
						int returnVal = chooser.showOpenDialog(article);

						if (returnVal == JFileChooser.APPROVE_OPTION) {
							File[] files = chooser.getSelectedFiles();
							String label_text = "";
							String label_tooltip = "";
							for (File f : files) {
								uploaded_files.add(f);
								label_text = label_text + f.getName() + "\n";
							}

							label_tooltip = files.length + " files";
							lblFile.setText(label_text);
							lblFile.setToolTipText(label_tooltip);
						}
					}
				});
				select.setBounds(20, 276, 90, 30);
				panel.add(select);
				JButton upload = new JButton("Upload");
				upload.setEnabled(false);
				upload.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						for (File f : uploaded_files) {
							file_copy(article_id, f.getPath().toString());
							System.out.println(chooser.getSelectedFile().getPath().toString());
						}
						if (!uploaded_files.isEmpty()) {
							select.setEnabled(false);
						}
					}
				});
				upload.setBounds(150, 276, 90, 30);

				panel.add(upload);

				article.setVisible(true);
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

	public void edit_article(final int issue_id, final int article_id) {
		if (logged_in) {
			if (article_screens.containsKey(issue_id) && article_screens.get(issue_id).containsKey(article_id)
					&& !article_screens.get(issue_id).get(article_id).isVisible()) {
				Article current_article = issue_storage.get(issue_id).getArticles_list().get(article_id);
				int initial_file_num = file_id;
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
				Metadata meta = null;
				if (metadata_storage.containsKey(article_id)) {
					meta = metadata_storage.get(article_id);
				}
				article.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				// article.setSize(width_small, height_small);
				article.setSize(width_small, height_small);
				article.setTitle("Editing - Article <" + article_id + ">");
				article.getContentPane().setBackground(new Color(128, 128, 128));
				article.setVisible(true);

				String setting_meta=list_settings.get("Metadata");
				article.setLocationRelativeTo(null);
				article.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {

						issue(issue_id);
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
				lblArticleDetails.setBackground(new Color(2, 216, 138));
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

						if (issue_screens.get(issue_id) == null) {
							article.dispose();
							issue(issue_id);
						} else if (!issue_screens.get(issue_id).isVisible()) {
							article.dispose();
							issue_screens.get(issue_id).setVisible(true);
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
				panel_1.setBackground(new Color(2, 216, 138));
				panel_1.setBounds(0, 65, width_small, 45);
				article.getContentPane().add(panel_1);

				Panel panel_2 = new Panel();
				panel_2.setBackground(new Color(6, 141, 91));
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

				panel3.setPreferredSize(new Dimension(320, settings_height));
				JScrollPane abstractSection = new JScrollPane(panel3);
				panel3.setAutoscrolls(true);

				JLabel lblNewLabel_1 = new JLabel("Abstract");
				lblNewLabel_1.setBounds(16, 6, 61, 16);
				panel3.add(lblNewLabel_1);

				final JTextArea lblAbstract = new JTextArea(current_article.getAbstract_text());
				lblAbstract.setEditable(true);
				lblAbstract.setBounds(16, 28, 400, 180);
				lblAbstract.setOpaque(true);
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

				JPanel panel15 = new JPanel();
				panel15.setBackground(SystemColor.window);

				// size
				JScrollPane authorSection = new JScrollPane(panel6);
				panel6.setAutoscrolls(true);

				JLabel lblNewLabel_3 = new JLabel("Author Information");
				lblNewLabel_3.setBounds(15, 6, 156, 16);
				panel6.add(lblNewLabel_3);
				JButton btnAddAuthors = new JButton("Edit Authors");
				btnAddAuthors.setBounds(165, 6, 125, 25);
				panel6.add(btnAddAuthors);

				Set<Integer> author_keys = author_storage.keySet();
				DefaultListModel listModel = new DefaultListModel();
				ArrayList<Integer> author_list = new ArrayList<Integer>();
				String listData[] = new String[author_keys.size()];
				int j = 0;
				int a = 0;
				int selections = 0;

				for (int key : author_keys) {
					listModel.addElement(author_storage.get(key).getFull_name());
					listData[j] = author_storage.get(key).getFull_name();
					author_list.add(key);
					Article current_art = issue_storage.get(issue_id).getArticles_list().get(article_id);
					ArrayList<Author> current_athors = current_art.getAuthors();
					for (int b = 0; b < current_athors.size(); b++) {
						if (author_storage.get(key).getId() == current_art.getAuthors().get(b).getId()) {
							selections++;
						}
					}

					j = j + 1;
				}
				;
				j = 0;
				int[] selected = new int[selections];
				for (int key : author_keys) {
					Article current_art = issue_storage.get(issue_id).getArticles_list().get(article_id);
					ArrayList<Author> current_athors = current_art.getAuthors();
					for (int b = 0; b < current_athors.size(); b++) {
						if (author_storage.get(key).getId() == current_art.getAuthors().get(b).getId()) {
							System.out.println(a + " - " + j);
							selected[a] = j;
							a = a + 1;
						}
					}

					j = j + 1;
				}
				;
				System.out.println("authors: " + selected);
				for (int index : selected) {
					System.out.println("Selected: " + index);
				}
				System.out.println(selected.toString());
				panel15.setBounds(50, 107, 180 * 2, 45 * author_keys.size());
				panel15.setLayout(null);
				panel15.setAutoscrolls(true);
				panel15.setPreferredSize(new Dimension(320, 45 * author_keys.size()));
				// Create a new listbox control
				JList listbox = new JList();
				listbox.setModel(listModel);
				listbox.setSelectedIndices(selected);
				listbox.setBounds(15, 40, 320, 25 * author_list.size());
				listbox.setBackground(Color.white);
				listbox.setVisible(true);
				panel15.add(listbox);

				btnAddAuthors.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int result = JOptionPane.showConfirmDialog(null, panel15, "Edit Authors",
								JOptionPane.OK_CANCEL_OPTION);
						if (result == JOptionPane.OK_OPTION) {
							int[] selections = listbox.getSelectedIndices();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
							Issue current_issue = issue_storage.get(issue_id);

							ArrayList<Integer> removed_authors = new ArrayList<Integer>();
							ArrayList<Integer> new_authors = new ArrayList<Integer>();
							for (int index : selections) {
								new_authors.add(author_list.get(index));
							}
							Set<Integer> previous_authors = author_primary_storage.get(article_id).keySet();
							for (int index : previous_authors) {
								if (!new_authors.contains(index)) {
									removed_authors.add(index);
								}
							}
							HashMap<Integer, Boolean> primary_storage = author_primary_storage.get(article_id);
							for (int i = 0; i < removed_authors.size(); i++) {
								primary_storage.remove(removed_authors.get(i));
							}
							for (int i = 0; i < new_authors.size(); i++) {
								if (!primary_storage.containsKey(new_authors.get(i))) {
									primary_storage.put(new_authors.get(i), false);
								}
							}
							author_primary_storage.put(article_id, primary_storage);
							current_issue.reset_authors(article_id);
							for (int index : selections) {
								Author new_author = author_storage.get(author_list.get(index));
								current_issue.add_author(article_id, new_author);

							}
							issue_storage.put(issue_id, current_issue);
							article.dispose();
							edit_article(issue_id, article_id);

						}
					}
				});

				JPanel panelMetadata = new JPanel();
				panelMetadata.setBackground(SystemColor.window);
				panelMetadata.setBounds(50, height_small - 260, 300, 307);
				panelMetadata.setLayout(null);
				panelMetadata.setAutoscrolls(true);

				JLabel lblCompetingInterests = new JLabel("Competing Interests");
				lblCompetingInterests.setBounds(35, 40, 145, 15);
				panelMetadata.add(lblCompetingInterests);
				JTextArea txtCompetingInterests = new JTextArea();
				if (meta != null) {
					txtCompetingInterests.setText(meta.getCompeting_interests());
				}
				txtCompetingInterests.setColumns(10);
				txtCompetingInterests.setBounds(35, 70, 145, 100);

				JScrollPane cmptinterests = new JScrollPane(txtCompetingInterests);
				cmptinterests.setBounds(35, 70, 145, 100);
				panelMetadata.add(cmptinterests);
				// scrollSettings.setViewportView(scrollFrame);

				JLabel lblFunding = new JLabel("Funding");
				lblFunding.setBounds(35, 175, 145, 15);
				panelMetadata.add(lblFunding);

				JTextArea txtFunding = new JTextArea();
				if (meta != null) {
					txtFunding.setText(meta.getFunding());
				}
				txtFunding.setColumns(10);
				txtFunding.setBounds(35, 195, 145, 100);

				JScrollPane funding = new JScrollPane(txtFunding);
				funding.setBounds(35, 195, 145, 100);
				panelMetadata.add(funding);
				panelMetadata.setPreferredSize(new Dimension(240, 380));

				panelMetadata.setSize(new Dimension(240, 380));
				JButton btnAddMetadata = new JButton("Edit Metadata");
				btnAddMetadata.setBounds(40, height_small - 150, 160, 50);
				btnAddMetadata.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int result = JOptionPane.showConfirmDialog(null, panelMetadata, "Edit Authors",
								JOptionPane.OK_CANCEL_OPTION);
						if (result == JOptionPane.OK_OPTION) {
						}
					}
				});

				if(setting_meta.compareToIgnoreCase("true")==0){
				panel.add(btnAddMetadata);
				article.getContentPane().add(btnAddMetadata);
				}
				final HashMap<Integer, HashMap<Integer, JTextField>> author_fields = new HashMap<Integer, HashMap<Integer, JTextField>>();
				final HashMap<Integer, JTextArea> authors_bio = new HashMap<Integer, JTextArea>();

				ArrayList<Author> authors = current_article.getAuthors();
				final HashMap<Integer, JButton> primary_buttons = new HashMap<Integer, JButton>();
				final HashMap<Integer, JLabel> primary_labels = new HashMap<Integer, JLabel>();

				int author_x = 16;
				int author_y = 60;
				int separation_horizontal = 205;
				int separation_vertical = 30;
				int label_field_separation = 4;
				for (int i = 0; i < authors.size(); i++) {

					separation_horizontal = 205 * i;
					author_x = author_x + separation_horizontal;
					Author author = authors.get(i);
					JLabel author_num = new JLabel(Integer.toString(i + 1));
					author_num.setBounds(20 + author_x, 35, 40, 16);

					panel6.add(author_num);
					Boolean primary = author_primary_storage.get(article_id).get(author.getId());

					if (primary) {
						JLabel author_primary = new JLabel("PRIMARY");
						author_primary.setBounds(60 + author_x, 35, 80, 16);
						primary_labels.put(author.getId(), author_primary);
						panel6.add(author_primary);

						JButton author_primary_btn = new JButton("Make Primary");
						author_primary_btn.setBounds(60 + author_x, 33, 120, 25);
						int a_id = author.getId();
						author_primary_btn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {

								HashMap<Integer, Boolean> update_primary = author_primary_storage.get(article_id);

								Set<Integer> primary_keys = update_primary.keySet();
								for (int pkey : primary_keys) {
									if (pkey != a_id) {
										update_primary.put(pkey, false);
									} else {
										update_primary.put(pkey, true);

										System.out.println(pkey + " - " + a_id);
									}

								}
								author_primary_storage.put(article_id, update_primary);

								System.out.println(a_id + " " + update_primary.get(a_id));

								System.out.println(a_id + " " + author_primary_storage.get(article_id).get(a_id));

								Set<Integer> label_keys = primary_labels.keySet();
								for (int key_lbl : label_keys) {
									if (primary_labels.get(key_lbl).getParent() == panel6) {
										panel6.remove(primary_labels.get(key_lbl));

										panel6.add(primary_buttons.get(key_lbl));
									}
								}
								panel6.remove(primary_buttons.get(a_id));
								panel6.add(primary_labels.get(a_id));
								panel6.repaint();

							}
						});
						primary_buttons.put(a_id, author_primary_btn);
					} else {

						JLabel author_primary = new JLabel("PRIMARY");
						author_primary.setBounds(60 + author_x, 35, 80, 16);
						primary_labels.put(author.getId(), author_primary);
						JButton author_primary_btn = new JButton("Make Primary");
						author_primary_btn.setBounds(60 + author_x, 33, 120, 25);
						panel6.add(author_primary_btn);
						int a_id = author.getId();
						author_primary_btn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {

								HashMap<Integer, Boolean> update_primary = author_primary_storage.get(article_id);

								Set<Integer> primary_keys = update_primary.keySet();
								for (int pkey : primary_keys) {
									if (pkey != a_id) {
										update_primary.put(pkey, false);
									} else {
										update_primary.put(pkey, true);

										System.out.println(pkey + " - " + a_id);
									}

								}
								author_primary_storage.put(article_id, update_primary);

								System.out.println(a_id + " " + update_primary.get(a_id));
								System.out.println(a_id + " " + author_primary_storage.get(article_id).get(a_id));

								Set<Integer> label_keys = primary_labels.keySet();
								for (int key_lbl : label_keys) {
									if (primary_labels.get(key_lbl).getParent() == panel6) {
										panel6.remove(primary_labels.get(key_lbl));

										panel6.add(primary_buttons.get(key_lbl));
									}
								}
								panel6.remove(primary_buttons.get(a_id));
								panel6.add(primary_labels.get(a_id));
								panel6.repaint();

							}
						});

						primary_buttons.put(a_id, author_primary_btn);
					}
					HashMap<Integer, JTextField> author_components = new HashMap<Integer, JTextField>();

					JLabel field_label = new JLabel("First name:");
					field_label.setBounds(author_x, author_y, 75, 30); // white
																		// box
					field_label.setOpaque(true);
					panel6.add(field_label);
					JTextField field = new JTextField(author.getFirst_name());
					field.setBounds(author_x + 75 + label_field_separation, author_y, 100, 30); // white
																								// box
					field.setOpaque(true);
					panel6.add(field);
					author_components.put(1, field);
					author_y = author_y + separation_vertical;

					field_label = new JLabel("Middle name:");
					field_label.setBounds(author_x, author_y, 75, 30); // white
																		// box
					field_label.setOpaque(true);
					panel6.add(field_label);
					field = new JTextField(author.getMiddle_name());
					field.setBounds(author_x + 75 + label_field_separation, author_y, 100, 30); // white
																								// box
					field.setOpaque(true);
					panel6.add(field);
					author_components.put(2, field);
					author_fields.put(author.getId(), author_components);
					author_y = author_y + separation_vertical;

					field_label = new JLabel("Last name:");
					field_label.setBounds(author_x, author_y, 75, 30); // white
																		// box
					field_label.setOpaque(true);
					panel6.add(field_label);
					field = new JTextField(author.getLast_name());
					field.setBounds(author_x + 75 + label_field_separation, author_y, 100, 30); // white
																								// box
					field.setOpaque(true);
					panel6.add(field);
					author_components.put(3, field);
					author_fields.put(author.getId(), author_components);
					author_y = author_y + separation_vertical;

					field_label = new JLabel("Email:");
					field_label.setBounds(author_x, author_y, 75, 30); // white
																		// box
					field_label.setOpaque(true);
					panel6.add(field_label);
					field = new JTextField(author.getEmail());
					field.setBounds(author_x + 75 + label_field_separation, author_y, 100, 30); // white
																								// box
					field.setOpaque(true);
					panel6.add(field);
					author_components.put(4, field);
					author_fields.put(author.getId(), author_components);
					author_y = author_y + separation_vertical;

					field_label = new JLabel("Affiliation:");
					field_label.setBounds(author_x, author_y, 75, 30); // white
																		// box
					field_label.setOpaque(true);
					panel6.add(field_label);
					field = new JTextField(author.getAffiliation());
					field.setBounds(author_x + 75 + label_field_separation, author_y, 100, 30); // white
																								// box
					field.setOpaque(true);
					panel6.add(field);
					author_components.put(5, field);
					author_fields.put(author.getId(), author_components);
					author_y = author_y + separation_vertical;

					field_label = new JLabel("Bio:");
					field_label.setBounds(author_x, author_y, 75, 30); // white
																		// box
					field_label.setOpaque(true);
					panel6.add(field_label);
					JTextArea field_area = new JTextArea(author.getBio());

					field_area.setBounds(author_x + 75 + label_field_separation, author_y, 100, 60); // white
					// box
					field_area.setOpaque(true);
					JScrollPane scroll_bio = new JScrollPane(field_area);
					scroll_bio.setBounds(author_x + 75 + label_field_separation, author_y, 100, 60);
					scroll_bio.setPreferredSize(new Dimension(250, 200));
					panel6.add(scroll_bio);
					authors_bio.put(author.getId(), field_area);
					author_fields.put(author.getId(), author_components);
					author_y = author_y + separation_vertical + 30;

					field_label = new JLabel("OrcID:");
					field_label.setBounds(author_x, author_y, 75, 30); // white
																		// box
					field_label.setOpaque(true);
					panel6.add(field_label);
					field = new JTextField(author.getOrcid());
					field.setBounds(author_x + 75 + label_field_separation, author_y, 100, 30); // white
																								// box
					field.setOpaque(true);
					panel6.add(field);
					author_components.put(7, field);
					author_fields.put(author.getId(), author_components);
					author_y = author_y + separation_vertical;

					field_label = new JLabel("Department:");
					field_label.setBounds(author_x, author_y, 75, 30); // white
																		// box
					field_label.setOpaque(true);
					panel6.add(field_label);
					field = new JTextField(author.getDepartment());
					field.setBounds(author_x + 75 + label_field_separation, author_y, 100, 30); // white
																								// box
					field.setOpaque(true);
					panel6.add(field);
					author_components.put(8, field);
					author_fields.put(author.getId(), author_components);
					author_y = author_y + separation_vertical;

					field_label = new JLabel("Country:");
					field_label.setBounds(author_x, author_y, 75, 30); // white
																		// box
					field_label.setOpaque(true);
					panel6.add(field_label);
					field = new JTextField(author.getCountry());
					field.setBounds(author_x + 75 + label_field_separation, author_y, 100, 30); // white
																								// box
					field.setOpaque(true);
					panel6.add(field);
					author_components.put(9, field);
					author_fields.put(author.getId(), author_components);
					author_y = author_y + separation_vertical;

					author_y = 60;
					author_x = 16;

				}
				panel6.setPreferredSize(new Dimension(210 * authors.size(), 500)); // scrollable

				System.out.println(author_fields.size());
				/*
				 * JTextArea lblAuthorInfo = new JTextArea(
				 * "First Name: Pete\tFirst Name: Bob 2 \nLast Name: User\tLast Name: User 2 \n "
				 * ); lblAuthorInfo.setEditable(true);
				 * lblAuthorInfo.setBounds(16, 34, 205 * 2, 175); // white box
				 * lblAuthorInfo.setOpaque(true); panel6.add(lblAuthorInfo)
				 */

				authorSection.setPreferredSize(new Dimension(220 * authors.size(), 200));
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

				JLabel lblIssueId = new JLabel(Integer.toString(issue_id));
				lblIssueId.setBounds(160, 48, 94, 30);
				panel.add(lblIssueId);
				lblIssueId.setForeground(Color.BLACK);
				lblIssueId.setFont(new Font("Dialog", Font.BOLD, 14));
				lblIssueId.setText(Integer.toString(issue_id));

				final JLabel lblDateAccepted = new JLabel("Date Accepted:");
				lblDateAccepted.setForeground(Color.BLACK);
				lblDateAccepted.setFont(new Font("Dialog", Font.BOLD, 14));
				lblDateAccepted.setBounds(24, 199, 150, 30);
				panel.add(lblDateAccepted);
				final JLabel lblDatePublished = new JLabel("Date Published:");
				lblDatePublished.setForeground(Color.BLACK);
				lblDatePublished.setFont(new Font("Dialog", Font.BOLD, 14));
				lblDatePublished.setBounds(24, 231, 150, 30);
				panel.add(lblDatePublished);
				Date current = new Date();

				final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

				/*
				 * JLabel lblDate = new JLabel(sdf.format(current));
				 * lblDate.setVerticalAlignment(SwingConstants.TOP);
				 * lblDate.setForeground(Color.BLACK); lblDate.setFont(new
				 * Font("Dialog", Font.BOLD, 14)); lblDate.setBounds(160, 203,
				 * 125, 30); panel.add(lblDate);
				 */

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

				final JTextField lblPageNum = new JTextField(Integer.toString(current_article.getPages()));
				lblPageNum.setForeground(Color.BLACK);
				lblPageNum.setFont(new Font("Dialog", Font.BOLD, 14));
				lblPageNum.setBounds(156, 171, 125, 30);
				panel.add(lblPageNum);

				JLabel lblTitle = new JLabel("Title:");
				lblTitle.setForeground(Color.BLACK);
				lblTitle.setFont(new Font("Dialog", Font.BOLD, 14));
				lblTitle.setBounds(24, 115, 94, 30);
				panel.add(lblTitle);

				Panel panel9 = new Panel();
				panel9.setBackground(new Color(153, 102, 51));
				panel9.setBounds(85, 118, 180, 80);
				final JTextArea lblTitleText = new JTextArea(current_article.getTitle());
				lblTitleText.setEditable(true);
				lblTitleText.setOpaque(true);
				lblTitleText.setForeground(Color.BLACK);
				lblTitleText.setFont(new Font("Dialog", Font.BOLD, 14));

				panel9.add(lblTitleText);
				JScrollPane titleSection = new JScrollPane(lblTitleText);
				titleSection.setPreferredSize(new Dimension(300 * 2, 200));
				titleSection.setBounds(85, 113, 225, 50);
				titleSection.add(panel9);
				titleSection.createHorizontalScrollBar();
				panel.add(titleSection);

				final JComboBox<String> lblSectionId = new JComboBox();
				Set<Integer> section_keys = section_storage.keySet();
				ArrayList<Section> sections = new ArrayList<Section>();
				int selected_section = 0;
				int count = 0;
				for (int key : section_keys) {
					lblSectionId.addItem(section_storage.get(key).getTitle());
					sections.add(section_storage.get(key));
					System.out.println("Count: " + count + " Section: " + current_article.getSection_id());
					if (current_article.getSection_id() == section_storage.get(key).getId()) {
						selected_section = count;
						System.out.println("selected section: " + selected_section);
					}
					count++;
				}
				lblSectionId.setSelectedIndex(selected_section);
				// final JTextField lblSectionId = new
				// JTextField(Integer.toString(current_article.getSection_id()));
				lblSectionId.setForeground(Color.BLACK);
				lblSectionId.setFont(new Font("Dialog", Font.BOLD, 12));
				lblSectionId.setBounds(95, 83, 140, 26);
				panel.add(lblSectionId);
				JButton btnAddSections = new JButton("+ Add");
				btnAddSections.setBounds(236, 83, 85, 27);
				panel.add(btnAddSections);

				JPanel panelSection = new JPanel();
				panelSection.setBounds(0, 0, 480, 150);
				panelSection.setLayout(null);

				JTextField txtSectionTitle = new JTextField();
				txtSectionTitle.setBounds(90, 65, 300, 30);
				panelSection.add(txtSectionTitle);
				txtSectionTitle.setColumns(10);

				JLabel lblTitleSection = new JLabel("Title");
				lblTitleSection.setHorizontalAlignment(SwingConstants.CENTER);
				lblTitleSection.setBounds(190, 40, 100, 20);
				panelSection.add(lblTitleSection);
				panelSection.setBounds(0, 0, 480, 150);
				panelSection.setSize(new Dimension(480, 150));
				panelSection.setPreferredSize(new Dimension(480, 150));
				panelSection.setVisible(true);
				btnAddSections.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						panelSection.setVisible(true);
						panelSection.setEnabled(true);
						int result = JOptionPane.showConfirmDialog(null, panelSection, "Add Section",
								JOptionPane.OK_CANCEL_OPTION);
						if (result == JOptionPane.OK_OPTION && txtSectionTitle.getText().isEmpty() == false) {
							section_db_id++;
							Section new_section = new Section(section_db_id, txtSectionTitle.getText());
							section_storage.put(section_db_id, new_section);
							lblSectionId.addItem(new_section.getTitle());
							sections.add(new_section);
							lblSectionId.repaint();
						}
					}
				});

				final JXDatePicker datePickerAccepted = new JXDatePicker();
				datePickerAccepted.setFormats(sdf);
				final JLabel label_accepted = new JLabel();
				label_accepted.setText("Choose Date by selecting below.");
				datePickerAccepted.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						label_accepted.setText(datePickerAccepted.getDate().toString());
						System.out.println(datePickerAccepted.getDate());
					}
				});
				label_accepted.setBounds(160, 250, 100, 25);
				datePickerAccepted.setBounds(156, 202, 160, 30);

				datePickerAccepted.setDate(current_article.getDate_accepted());
				// panel.add(label);
				panel.add(datePickerAccepted);
				final JLabel label = new JLabel();
				label.setText("Choose Date by selecting below.");
				final JXDatePicker datePicker = new JXDatePicker();
				datePicker.setFormats(sdf);

				datePicker.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						label.setText(datePicker.getDate().toString());
						System.out.println(datePicker.getDate());
					}
				});
				label.setBounds(160, 250, 100, 25);
				datePicker.setDate(current_article.getDate_published());
				datePicker.setBounds(156, 234, 160, 30);
				// panel.add(label);
				panel.add(datePicker);

				JLabel lblSection = new JLabel("Section:");
				lblSection.setForeground(Color.BLACK);
				lblSection.setFont(new Font("Dialog", Font.BOLD, 14));
				lblSection.setBounds(24, 80, 94, 30);
				panel.add(lblSection);

				JTextArea lblFile = new JTextArea("");
				String label_text = "";
				JButton btnSave = new JButton("Save");
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Boolean validation = true;
						int entered_sectionID = 0;
						int entered_pages = 0;
						try {
							entered_sectionID = sections.get(lblSectionId.getSelectedIndex()).getId();
							entered_pages = Integer.parseInt(lblPageNum.getText());

							System.out.println("Section id: " + entered_sectionID);

							lblSectionId.setBackground(new Color(255, 255, 255));
							lblSectionId.setForeground(new Color(0, 0, 0));
							lblPageNum.setBackground(new Color(255, 255, 255));
							lblPageNum.setForeground(new Color(0, 0, 0));
						} catch (NumberFormatException e) {
							validation = false;

							lblPageNum.setBackground(new Color(255, 0, 0));
							lblPageNum.setForeground(new Color(255, 255, 255));
							JOptionPane.showMessageDialog(null,
									"Use only numbers in the Pages field and select a valid section item from the dropdown list. ");
						}

						SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
						try {

							String test_accepted = sdf.format(datePickerAccepted.getDate());
							String test_published = sdf.format(datePicker.getDate());

						} catch (Exception ex) {
							validation = false;
							JOptionPane.showMessageDialog(null,
									"Use dates from calendar for fields: Date Published and Date Accepted");
						}
						if (lblFile.getText().contains("Not Uploaded")) {
							validation = false;
							JOptionPane.showMessageDialog(null,
									"There are files that have not been uploaded yet. Upload the files or clear the selection.");

						}
						if (validation) {
							article.dispose();
							if(setting_meta.compareToIgnoreCase("true")==0){
							if (metadata_storage.containsKey(article_id)) {
								Metadata meta_update = metadata_storage.get(article_id);
								meta_update.setCompeting_interests(txtCompetingInterests.getText());
								meta_update.setFunding(txtFunding.getText());
								metadata_storage.put(article_id, meta_update);
							} else {
								metadata_id++;
								Metadata meta_update = new Metadata(metadata_id, article_id,
										txtCompetingInterests.getText(), txtFunding.getText());
								metadata_storage.put(article_id, meta_update);
							}}
							Article a = issue_storage.get(issue_id).getArticles_list().get(article_id);
							a.setTitle(lblTitleText.getText());
							ArrayList<Author> updated_authors = a.getAuthors();
							for (int i = 0; i < updated_authors.size(); i++) {
								Author author = updated_authors.get(i);
								HashMap<Integer, JTextField> a_fields = author_fields
										.get(updated_authors.get(i).getId());
								author.setFirst_name(a_fields.get(1).getText());
								author.setMiddle_name(a_fields.get(2).getText());
								author.setLast_name(a_fields.get(3).getText());
								author.setEmail(a_fields.get(4).getText());
								author.setAffiliation(a_fields.get(5).getText());
								author.setBio(authors_bio.get(updated_authors.get(i).getId()).getText());
								author.setOrcid(a_fields.get(7).getText());
								author.setDepartment(a_fields.get(8).getText());
								author.setCountry(a_fields.get(9).getText());
								updated_authors.set(i, author);
								author_storage.put(updated_authors.get(i).getId(), author);
							}
							a.setAuthors(updated_authors);
							a.setAbstract_text(lblAbstract.getText());
							a.setSection_id(sections.get(lblSectionId.getSelectedIndex()).getId());
							a.setPages(Integer.parseInt(lblPageNum.getText()));
							a.setDate_published(datePicker.getDate());
							a.setDate_accepted(datePickerAccepted.getDate());
							Issue current_issue = issue_storage.get(issue_id);
							issue_storage.get(issue_id).update_article(article_id, a);
							issue_storage.put(issue_id, current_issue);
							issue(issue_id);
						}
					}
				});
				btnSave.setBounds((width_small - 200) / 2, height_small - 100, 200, 30);
				Panel panel10 = new Panel();
				panel10.setBackground(new Color(153, 102, 51));
				panel10.setBounds(115, 310, 225, 160);
				if (file_storage.containsKey(article_id)) {
					HashMap<Integer, ArticleFile> files = file_storage.get(article_id);
					Set<Integer> keys = files.keySet();
					for (int key : keys) {
						String path = files.get(key).getPath();
						label_text = label_text + path.substring(path.lastIndexOf("/") + 1) + "\n";
					}
				}
				lblFile.setText(label_text);
				lblFile.setForeground(Color.WHITE);
				lblFile.setEnabled(false);
				lblFile.setBounds(115, 310, 225, 160);
				lblFile.setToolTipText("");
				JScrollPane fileSection = new JScrollPane(lblFile);
				fileSection.setPreferredSize(new Dimension(300 * 2, 200));
				fileSection.setBounds(20, 310, 225, 160);
				fileSection.add(panel10);
				fileSection.createHorizontalScrollBar();
				panel.add(fileSection);
				ArrayList<File> uploaded_files = new ArrayList<File>();
				JFileChooser chooser = new JFileChooser();
				JButton btnClear = new JButton("Clear");
				btnClear.setEnabled(false);
				btnClear.setBounds(252, 315, 65, 30);
				panel.add(btnClear);
				JButton select = new JButton("Browse");

				select.setBounds(20, 276, 90, 30);
				panel.add(select);
				JButton upload = new JButton("Upload & Save");
				upload.setEnabled(false);
				btnClear.setEnabled(false);
				select.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						FileNameExtensionFilter file = new FileNameExtensionFilter(
								"Galleys (pdf,xml,html) or Images(jpg,png)", "pdf", "xml", "html", "jpg", "png");
						chooser.setFileFilter(file);
						chooser.setMultiSelectionEnabled(true);
						int returnVal = chooser.showOpenDialog(article);

						if (returnVal == JFileChooser.APPROVE_OPTION) {
							upload.setEnabled(true);
							btnClear.setEnabled(true);
							File[] files = chooser.getSelectedFiles();
							String label_text = "";
							label_text = label_text + lblFile.getText() + "----Not Uploaded-----[\n";
							String label_tooltip = "";
							for (File f : files) {
								uploaded_files.add(f);
								label_text = label_text + f.getName() + "\n";
							}

							System.out.println("Uploaded " + uploaded_files.size() + " files");
							label_text = label_text + "]----Not Uploaded-----";
							label_tooltip = files.length + " files";

							lblFile.setText(label_text);
							lblFile.setToolTipText(label_tooltip);
						}
					}
				});
				upload.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						select.setEnabled(false);
						btnClear.setEnabled(false);
						upload.setEnabled(false);
						HashMap<Integer, ArticleFile> files = null;
						if (file_storage.containsKey(article_id)) {
							files = file_storage.get(article_id);
						} else {

							files = new HashMap<Integer, ArticleFile>();
						}
						for (File f : uploaded_files) {
							file_copy(article_id, f.getPath().toString());

							System.out.println("U-Files: " + uploaded_files.size());
						}
						if (!uploaded_files.isEmpty()) {
							select.setEnabled(false);
						}
						if (file_storage.containsKey(article_id)) {
							String label_text = "";
							HashMap<Integer, ArticleFile> files_existing = file_storage.get(article_id);
							Set<Integer> keys = files_existing.keySet();
							for (int k : keys) {
								ArticleFile a_file = files_existing.get(k);
								System.out.println("Files: " + keys.size());
								label_text = label_text
										+ a_file.getPath().substring(a_file.getPath().lastIndexOf("/") + 1) + "\n";

								System.out.println("::::" + label_text);
							}
							lblFile.setText(label_text);
						}
					}
				});
				btnClear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						uploaded_files.clear();
						if (file_storage.containsKey(article_id)) {
							String label_text = "";
							HashMap<Integer, ArticleFile> files_existing = file_storage.get(article_id);
							Set<Integer> keys = files_existing.keySet();
							for (int k : keys) {
								ArticleFile a_file = files_existing.get(k);
								label_text = label_text
										+ a_file.getPath().substring(a_file.getPath().lastIndexOf("/") + 1) + "\n";
							}
							lblFile.setText(label_text);
							/*
							 * if (file_storage.containsKey(article_id)) {
							 * HashMap<Integer, ArticleFile> up_files =
							 * file_storage.get(article_id); Set<Integer> keys =
							 * up_files.keySet(); file_id = initial_file_num;
							 * for (int key : keys) { File f = new
							 * File(up_files.get(key).getPath()); f.delete(); }
							 * 
							 * 
							 * 
							 * }
							 */
							select.setEnabled(true);
							upload.setEnabled(false);
							btnClear.setEnabled(false);
							lblFile.setToolTipText(label_text);

						}
					}
				});
				upload.setBounds(150, 276, 140, 30);

				panel.add(upload);
				article.getContentPane().add(btnSave);
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

	public void add_article(final int issue_id) {
		if (logged_in) {
			issue_screens.get(issue_id).dispose();
			int width_small = 0;
			int height_small = 0;
			if (height >= 768 && width >= 640) {
				width_small = (int) (width - (width * (37.5 / 100)));
				height_small = (int) (height - (height * (5 / 100)));
			} else {
				width_small = (int) (640 + (640 * (37.5 / 100)));
				height_small = (int) (768 - (768 * (5 / 100)));
			}

			String setting_meta=list_settings.get("Metadata");
			int current_id = articles_id + 1;
			int initial_file_num = file_id;
			final JFrame article = new JFrame();
			article.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			// article.setSize(width_small, height_small);
			article.setSize(width_small, height_small);
			article.setTitle("New Article");
			article.getContentPane().setBackground(new Color(128, 128, 128));
			article.setVisible(true);
			article.setLocationRelativeTo(null);
			article.getContentPane().setLayout(null);
		
			JLabel lblArticleDetails = new JLabel("Article Details");
			lblArticleDetails.setHorizontalAlignment(SwingConstants.CENTER);
			lblArticleDetails.setFont(new Font("Dialog", Font.BOLD, 20));
			lblArticleDetails.setForeground(new Color(255, 255, 255));
			lblArticleDetails.setBackground(new Color(218, 207, 2));
			lblArticleDetails.setBounds(width_small / 4, 65, width_small / 2, 40);
			lblArticleDetails.setOpaque(true);
			article.getContentPane().add(lblArticleDetails);

			/*
			 * final JButton btnSync = new JButton("Sync");
			 * btnSync.setBounds(width_small - 150, 21, 70, 24);
			 * article.getContentPane().add(btnSync);
			 * 
			 * final Label internetCheck = new Label("  ONLINE");
			 * internetCheck.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC,
			 * 12)); internetCheck.setBackground(Color.GREEN);
			 * internetCheck.setAlignment(1); internetCheck.setForeground(new
			 * Color(255, 255, 255)); internetCheck.setBounds(width_small - 80,
			 * 22, 65, 22); article.getContentPane().add(internetCheck);
			 * 
			 * ActionListener taskPerformer1 = new ActionListener() { public
			 * void actionPerformed(ActionEvent evt) { try { Socket sock = new
			 * Socket(); InetSocketAddress addr = new
			 * InetSocketAddress("www.google.com", 80); sock.setSoTimeout(500);
			 * sock.connect(addr, 3000);
			 * 
			 * internetCheck.setBackground(Color.GREEN);
			 * internetCheck.setText("ONLINE"); btnSync.setEnabled(true);
			 * sock.close();
			 * 
			 * } catch (Exception e) { internetCheck.setBackground(Color.RED);
			 * internetCheck.setText("OFFLINE"); btnSync.setEnabled(false); } }
			 * }; new Timer(delay, taskPerformer1).start(); DefaultTableModel
			 * model = new DefaultTableModel(rowData, columnNames); Action
			 * delete = new AbstractAction() { public void
			 * actionPerformed(ActionEvent e) { JTable table = (JTable)
			 * e.getSource(); int modelRow =
			 * Integer.valueOf(e.getActionCommand());
			 * JOptionPane.showMessageDialog(null, "Deleted"); // / //
			 * ((DefaultTableModel)table.getModel()).removeRow(modelRow); } };
			 * Action view = new AbstractAction() { public void
			 * actionPerformed(ActionEvent e) { JTable table = (JTable)
			 * e.getSource(); int modelRow =
			 * Integer.valueOf(e.getActionCommand());
			 * JOptionPane.showMessageDialog(null, modelRow); // / //
			 * ((DefaultTableModel)table.getModel()).removeRow(modelRow); } };
			 */
			JButton btnClear = new JButton("Clear");

			JButton btnGoBack = new JButton("Close");
			btnGoBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (file_storage.containsKey(current_id)) {
						HashMap<Integer, ArticleFile> up_files = file_storage.get(current_id);
						Set<Integer> keys = up_files.keySet();
						file_id = initial_file_num;
						for (int key : keys) {
							File f = new File(up_files.get(key).getPath());
							f.delete();
						}
						File folder = new File(String.format("src/files/%d/", current_id));
						folder.delete();
						file_storage.remove(current_id);
					}
					// database_save();
					if (issue_screens.get(issue_id) == null) {
						article.dispose();
						issue(issue_id);
					} else if (!issue_screens.get(issue_id).isVisible()) {
						article.dispose();
						issue_screens.get(issue_id).setVisible(true);
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
			panel_1.setBackground(new Color(218, 207, 2));
			panel_1.setBounds(0, 65, width_small, 45);
			article.getContentPane().add(panel_1);

			Panel panel_2 = new Panel();
			panel_2.setBackground(new Color(192, 183, 3));
			panel_2.setBounds(0, 110, width_small, 5);

			article.getContentPane().add(panel_2);
			JPanel panelMetadata = new JPanel();
			panelMetadata.setBackground(SystemColor.window);
			panelMetadata.setBounds(50, height_small - 260, 300, 307);
			panelMetadata.setLayout(null);
			panelMetadata.setAutoscrolls(true);

			JLabel lblCompetingInterests = new JLabel("Competing Interests");
			lblCompetingInterests.setBounds(35, 40, 145, 15);
			panelMetadata.add(lblCompetingInterests);
			JTextArea txtCompetingInterests = new JTextArea();
			txtCompetingInterests.setColumns(10);
			txtCompetingInterests.setBounds(35, 70, 145, 100);

			JScrollPane cmptinterests = new JScrollPane(txtCompetingInterests);
			cmptinterests.setBounds(35, 70, 145, 100);
			panelMetadata.add(cmptinterests);
			// scrollSettings.setViewportView(scrollFrame);

			JLabel lblFunding = new JLabel("Funding");
			lblFunding.setBounds(35, 175, 145, 15);
			panelMetadata.add(lblFunding);

			JTextArea txtFunding = new JTextArea();
			txtFunding.setColumns(10);
			txtFunding.setBounds(35, 195, 145, 100);

			JScrollPane funding = new JScrollPane(txtFunding);
			funding.setBounds(35, 195, 145, 100);
			panelMetadata.add(funding);
			panelMetadata.setPreferredSize(new Dimension(240, 380));

			panelMetadata.setSize(new Dimension(240, 380));
			JButton btnAddMetadata = new JButton("Add Metadata");
			btnAddMetadata.setBounds(40, height_small - 150, 160, 50);
			btnAddMetadata.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int result = JOptionPane.showConfirmDialog(null, panelMetadata, "Edit Authors",
							JOptionPane.OK_CANCEL_OPTION);
					if (result == JOptionPane.OK_OPTION) {
					}
				}
			});

			if(setting_meta.compareToIgnoreCase("true")==0){
			panel.add(btnAddMetadata);
			article.getContentPane().add(btnAddMetadata);}
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

			panel3.setPreferredSize(new Dimension(320, settings_height));
			JScrollPane abstractSection = new JScrollPane(panel3);
			panel3.setAutoscrolls(true);

			JLabel lblNewLabel_1 = new JLabel("Abstract");
			lblNewLabel_1.setBounds(16, 6, 61, 16);
			panel3.add(lblNewLabel_1);

			final JTextArea lblAbstract = new JTextArea();
			lblAbstract.setEditable(true);
			lblAbstract.setBounds(16, 28, 400, 180);
			lblAbstract.setOpaque(true);
			panel3.add(lblAbstract);
			abstractSection.setPreferredSize(new Dimension(320, 200));
			abstractSection.setBounds(width_small / 2 - 40, 132, width_small / 2, height_small / 2 - 150);
			// scrollSettings.setViewportView(scrollFrame);
			article.getContentPane().add(abstractSection);

			JPanel panel6 = new JPanel();
			panel6.setBackground(SystemColor.window);
			panel6.setBounds(50, 515, 320, 307);

			panel6.setLayout(null);
			panel6.setAutoscrolls(true);

			// size
			JScrollPane authorSection = new JScrollPane(panel6);
			panel6.setAutoscrolls(true);

			JLabel lblNewLabel_3 = new JLabel("Authors");
			lblNewLabel_3.setBounds(15, 10, 280, 16);
			panel6.add(lblNewLabel_3);
			final HashMap<Integer, HashMap<Integer, JTextField>> author_fields = new HashMap<Integer, HashMap<Integer, JTextField>>();
			/*
			 * ArrayList<Author> authors = current_article.getAuthors();
			 * 
			 * int author_x = 16; int author_y = 60; int separation_horizontal =
			 * 205; int separation_vertical = 30; int label_field_separation =
			 * 4; for (int i = 0; i < authors.size(); i++) {
			 * 
			 * separation_horizontal = 205 * i; author_x = author_x +
			 * separation_horizontal;
			 * 
			 * JLabel author_num = new JLabel(Integer.toString(i+1));
			 * author_num.setBounds(87+author_x, 35, 156, 16);
			 * panel6.add(author_num);
			 * 
			 * HashMap<Integer, JTextField> author_components = new
			 * HashMap<Integer, JTextField>(); Author author = authors.get(i);
			 * JLabel field_label = new JLabel("First name:");
			 * field_label.setBounds(author_x, author_y, 75, 30); // white //
			 * box field_label.setOpaque(true); panel6.add(field_label);
			 * JTextField field = new JTextField(author.getFirst_name());
			 * field.setBounds(author_x + 75 + label_field_separation, author_y,
			 * 100, 30); // white // box field.setOpaque(true);
			 * panel6.add(field); author_components.put(1, field); author_y =
			 * author_y + separation_vertical; field_label = new JLabel(
			 * "Last name:"); field_label.setBounds(author_x, author_y, 75, 30);
			 * // white // box field_label.setOpaque(true);
			 * panel6.add(field_label); field = new
			 * JTextField(author.getLast_name()); field.setBounds(author_x + 75
			 * + label_field_separation, author_y, 100, 30); // white // box
			 * field.setOpaque(true); panel6.add(field);
			 * author_components.put(2, field);
			 * author_fields.put(author.getId(), author_components); author_y =
			 * author_y + separation_vertical; author_y = 60; author_x = 16;
			 * 
			 * } panel6.setPreferredSize(new Dimension(210 *
			 * authors.size(),500)); // scrollable
			 */
			System.out.println(author_fields.size());
			/*
			 * JTextArea lblAuthorInfo = new JTextArea(
			 * "First Name: Pete\tFirst Name: Bob 2 \nLast Name: User\tLast Name: User 2 \n "
			 * ); lblAuthorInfo.setEditable(true); lblAuthorInfo.setBounds(16,
			 * 34, 205 * 2, 175); // white box lblAuthorInfo.setOpaque(true);
			 * panel6.add(lblAuthorInfo)
			 */
			Set<Integer> author_keys = author_storage.keySet();
			DefaultListModel listModel = new DefaultListModel();
			ArrayList<Integer> author_list = new ArrayList<Integer>();
			String listData[] = new String[author_keys.size()];
			int j = 0;
			for (int key : author_keys) {
				listModel.addElement(author_storage.get(key).getFull_name());
				listData[j] = author_storage.get(key).getFull_name();
				author_list.add(key);
				j = j + 1;
			}
			;

			// Create a new listbox control
			JButton btnAddAuthor = new JButton("+ Add new Author");
			btnAddAuthor.setBounds(156, 12, 180, 25);
			panel6.add(btnAddAuthor);
			JList listbox = new JList();
			listbox.setModel(listModel);
			listbox.setBounds(15, 40, 320, 25 * author_list.size());
			listbox.setBackground(Color.white);
			listbox.setVisible(true);
			panel6.add(listbox);
			JPanel panelAuthor = new JPanel();
			panelAuthor.setBounds(0, 0, 480, 800);
			panelAuthor.setLayout(null);

			JTextField txtFirstName = new JTextField();
			txtFirstName.setBounds(90, 65, 300, 30);
			panelAuthor.add(txtFirstName);
			txtFirstName.setColumns(10);

			JLabel lblFirstName = new JLabel("First name");
			lblFirstName.setHorizontalAlignment(SwingConstants.CENTER);
			lblFirstName.setBounds(190, 40, 100, 20);
			panelAuthor.add(lblFirstName);

			JLabel lblMiddleName = new JLabel("Middle name");
			lblMiddleName.setHorizontalAlignment(SwingConstants.CENTER);
			lblMiddleName.setBounds(190, 96, 100, 20);
			panelAuthor.add(lblMiddleName);

			JTextField txtMiddleName = new JTextField();
			txtMiddleName.setColumns(10);
			txtMiddleName.setBounds(90, 117, 300, 30);
			panelAuthor.add(txtMiddleName);

			JLabel lblLastName = new JLabel("Last name");
			lblLastName.setHorizontalAlignment(SwingConstants.CENTER);
			lblLastName.setBounds(190, 148, 100, 20);
			panelAuthor.add(lblLastName);

			JTextField txtLastName = new JTextField();
			txtLastName.setColumns(10);
			txtLastName.setBounds(90, 169, 300, 30);
			panelAuthor.add(txtLastName);

			JLabel lblEmail = new JLabel("Email");
			lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
			lblEmail.setBounds(190, 200, 100, 20);
			panelAuthor.add(lblEmail);

			JTextField txtEmail = new JTextField();
			txtEmail.setColumns(10);
			txtEmail.setBounds(90, 221, 300, 30);
			panelAuthor.add(txtEmail);

			JLabel lblAffiliation = new JLabel("Affiliation");
			lblAffiliation.setHorizontalAlignment(SwingConstants.CENTER);
			lblAffiliation.setBounds(190, 252, 100, 20);
			panelAuthor.add(lblAffiliation);

			JTextField txtAffiliation = new JTextField();
			txtAffiliation.setColumns(10);
			txtAffiliation.setBounds(90, 273, 300, 30);
			panelAuthor.add(txtAffiliation);

			JLabel lblBio = new JLabel("Bio");
			lblBio.setHorizontalAlignment(SwingConstants.CENTER);
			lblBio.setBounds(190, 304, 100, 20);
			panelAuthor.add(lblBio);

			JTextArea txtBio = new JTextArea();
			txtBio.setColumns(10);
			txtBio.setBounds(90, 325, 300, 60);
			panelAuthor.add(txtBio);

			JLabel lblOrcID = new JLabel("OrcID");
			lblOrcID.setHorizontalAlignment(SwingConstants.CENTER);
			lblOrcID.setBounds(190, 386, 100, 20);
			panelAuthor.add(lblOrcID);

			JTextField txtOrcID = new JTextField();
			txtOrcID.setColumns(10);
			txtOrcID.setBounds(90, 407, 300, 30);
			panelAuthor.add(txtOrcID);

			JLabel lblDepartment = new JLabel("Department");
			lblDepartment.setHorizontalAlignment(SwingConstants.CENTER);
			lblDepartment.setBounds(190, 438, 100, 20);
			panelAuthor.add(lblDepartment);

			JTextField txtDepartment = new JTextField();
			txtDepartment.setColumns(10);
			txtDepartment.setBounds(90, 459, 300, 30);
			panelAuthor.add(txtDepartment);

			JLabel lblCountry = new JLabel("Country");
			lblCountry.setHorizontalAlignment(SwingConstants.CENTER);
			lblCountry.setBounds(190, 490, 100, 20);
			panelAuthor.add(lblCountry);

			JTextField txtCountry = new JTextField();
			txtCountry.setColumns(10);
			txtCountry.setBounds(90, 511, 300, 30);
			panelAuthor.add(txtCountry);
			panelAuthor.setVisible(true);
			panelAuthor.setSize(new Dimension(480, 800));
			panelAuthor.setPreferredSize(new Dimension(480, 600));
			panelAuthor.setVisible(true);
			btnAddAuthor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					panelAuthor.setVisible(true);
					panelAuthor.setEnabled(true);
					int result = JOptionPane.showConfirmDialog(null, panelAuthor, "Add Author",
							JOptionPane.OK_CANCEL_OPTION);
					if (result == JOptionPane.OK_OPTION) {
						author_id++;
						Author new_author = new Author(author_id, txtFirstName.getText(), txtMiddleName.getText(),
								txtLastName.getText(), txtEmail.getText(), txtAffiliation.getText(), txtBio.getText(),
								txtOrcID.getText(), txtDepartment.getText(), txtCountry.getText());
						author_storage.put(author_id, new_author);
						author_list.add(author_id);
						listModel.addElement(new_author.getFull_name());
						listbox.repaint();
					}
				}
			});

			authorSection.add(panel6);
			authorSection.setViewportView(panel6);
			authorSection.setPreferredSize(new Dimension(220 * 1, 200));
			authorSection.setBounds(width_small / 2 - 40, 132 + height_small / 2 - 130, width_small / 2,
					height_small / 2 - 150);

			// scrollSettings.setViewportView(scrollFrame);
			article.getContentPane().add(authorSection);

			/*
			 * JLabel lblIssues = new JLabel("Article:");
			 * lblIssues.setBounds(24, 18, 110, 30); panel.add(lblIssues);
			 * lblIssues.setFont(new Font("Dialog", Font.BOLD, 14));
			 * lblIssues.setForeground(Color.BLACK);
			 */

			JLabel lblIssue = new JLabel("Issue:");
			lblIssue.setBounds(24, 48, 94, 30);
			panel.add(lblIssue);
			lblIssue.setForeground(Color.BLACK);
			lblIssue.setFont(new Font("Dialog", Font.BOLD, 14));

			JLabel lblIssueId = new JLabel(Integer.toString(issue_id));
			lblIssueId.setBounds(160, 48, 94, 30);
			panel.add(lblIssueId);
			lblIssueId.setForeground(Color.BLACK);
			lblIssueId.setFont(new Font("Dialog", Font.BOLD, 14));
			lblIssueId.setText(Integer.toString(issue_id));

			final JLabel lblDateAccepted = new JLabel("Date Accepted:");
			lblDateAccepted.setForeground(Color.BLACK);
			lblDateAccepted.setFont(new Font("Dialog", Font.BOLD, 14));
			lblDateAccepted.setBounds(24, 199, 150, 30);
			panel.add(lblDateAccepted);
			final JLabel lblDatePublished = new JLabel("Date Published:");
			lblDatePublished.setForeground(Color.BLACK);
			lblDatePublished.setFont(new Font("Dialog", Font.BOLD, 14));
			lblDatePublished.setBounds(24, 231, 150, 30);
			panel.add(lblDatePublished);
			Date current = new Date();

			final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

			/*
			 * JLabel lblDate = new JLabel(sdf.format(current));
			 * lblDate.setVerticalAlignment(SwingConstants.TOP);
			 * lblDate.setForeground(Color.BLACK); lblDate.setFont(new
			 * Font("Dialog", Font.BOLD, 14)); lblDate.setBounds(160, 203, 125,
			 * 30); panel.add(lblDate);
			 * 
			 * 
			 * JLabel lblArticleId = new JLabel("1");
			 * lblArticleId.setBounds(160, 19, 94, 30); panel.add(lblArticleId);
			 * lblArticleId.setForeground(Color.BLACK); lblArticleId.setFont(new
			 * Font("Dialog", Font.BOLD, 14));
			 * lblArticleId.setText(Integer.toString(article_id));
			 */

			JLabel lblPages = new JLabel("Pages:");
			lblPages.setForeground(Color.BLACK);
			lblPages.setFont(new Font("Dialog", Font.BOLD, 14));
			lblPages.setBounds(24, 165, 94, 30);
			panel.add(lblPages);

			final JTextField lblPageNum = new JTextField();
			lblPageNum.setForeground(Color.BLACK);
			lblPageNum.setFont(new Font("Dialog", Font.BOLD, 14));
			lblPageNum.setBounds(156, 171, 125, 30);
			panel.add(lblPageNum);

			JLabel lblTitle = new JLabel("Title:");
			lblTitle.setForeground(Color.BLACK);
			lblTitle.setFont(new Font("Dialog", Font.BOLD, 14));
			lblTitle.setBounds(24, 115, 94, 30);
			panel.add(lblTitle);

			Panel panel9 = new Panel();
			panel9.setBackground(new Color(153, 102, 51));
			panel9.setBounds(85, 118, 180, 80);
			final JTextArea lblTitleText = new JTextArea();
			lblTitleText.setEditable(true);
			lblTitleText.setOpaque(true);
			lblTitleText.setForeground(Color.BLACK);
			lblTitleText.setFont(new Font("Dialog", Font.BOLD, 14));

			panel9.add(lblTitleText);
			JScrollPane titleSection = new JScrollPane(lblTitleText);
			titleSection.setPreferredSize(new Dimension(300 * 2, 200));
			titleSection.setBounds(85, 113, 225, 50);
			titleSection.add(panel9);
			titleSection.createHorizontalScrollBar();
			panel.add(titleSection);

			final JComboBox<String> lblSectionId = new JComboBox();
			Set<Integer> section_keys = section_storage.keySet();
			ArrayList<Section> sections = new ArrayList<Section>();
			for (int key : section_keys) {
				lblSectionId.addItem(section_storage.get(key).getTitle());
				sections.add(section_storage.get(key));
			}

			lblSectionId.setForeground(Color.BLACK);
			lblSectionId.setFont(new Font("Dialog", Font.BOLD, 12));
			lblSectionId.setBounds(95, 83, 140, 26);
			panel.add(lblSectionId);
			JButton btnAddSections = new JButton("+ Add");
			btnAddSections.setBounds(236, 83, 85, 27);
			panel.add(btnAddSections);

			JPanel panelSection = new JPanel();
			panelSection.setBounds(0, 0, 480, 150);
			panelSection.setLayout(null);

			JTextField txtSectionTitle = new JTextField();
			txtSectionTitle.setBounds(90, 65, 300, 30);
			panelSection.add(txtSectionTitle);
			txtSectionTitle.setColumns(10);

			JLabel lblTitleSection = new JLabel("Title");
			lblTitleSection.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitleSection.setBounds(190, 40, 100, 20);
			panelSection.add(lblTitleSection);
			panelSection.setBounds(0, 0, 480, 150);
			panelSection.setSize(new Dimension(480, 150));
			panelSection.setPreferredSize(new Dimension(480, 150));
			panelSection.setVisible(true);
			btnAddSections.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					panelSection.setVisible(true);
					panelSection.setEnabled(true);
					int result = JOptionPane.showConfirmDialog(null, panelSection, "Add Section",
							JOptionPane.OK_CANCEL_OPTION);
					if (result == JOptionPane.OK_OPTION && txtSectionTitle.getText().isEmpty() == false) {
						System.out.println(txtSectionTitle.getText());
						section_db_id++;
						Section new_section = new Section(section_db_id, txtSectionTitle.getText());
						section_storage.put(section_db_id, new_section);
						sections.add(new_section);
						lblSectionId.addItem(new_section.getTitle());
						lblSectionId.repaint();
					}
				}
			});

			final JXDatePicker datePickerAccepted = new JXDatePicker();
			datePickerAccepted.setFormats(sdf);
			final JLabel label_accepted = new JLabel();
			label_accepted.setText("Choose Date by selecting below.");
			datePickerAccepted.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					label_accepted.setText(datePickerAccepted.getDate().toString());
					System.out.println(datePickerAccepted.getDate());
				}
			});
			label_accepted.setBounds(160, 250, 100, 25);
			datePickerAccepted.setBounds(156, 202, 160, 30);
			// panel.add(label);
			panel.add(datePickerAccepted);
			final JLabel label = new JLabel();
			label.setText("Choose Date by selecting below.");
			final JXDatePicker datePicker = new JXDatePicker();
			datePicker.setFormats(sdf);

			datePicker.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					label.setText(datePicker.getDate().toString());
					System.out.println(datePicker.getDate());
				}
			});
			label.setBounds(160, 250, 100, 25);
			datePicker.setBounds(156, 234, 160, 30);
			// panel.add(label);
			panel.add(datePicker);

			JLabel lblSection = new JLabel("Section:");
			lblSection.setForeground(Color.BLACK);
			lblSection.setFont(new Font("Dialog", Font.BOLD, 14));
			lblSection.setBounds(24, 80, 94, 30);
			panel.add(lblSection);
			JTextArea lblFile = new JTextArea("");
			JButton btnSave = new JButton("Create");
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Boolean validation = true;
					int entered_sectionID = 0;
					int entered_pages = 0;
					try {
						entered_sectionID = sections.get(lblSectionId.getSelectedIndex()).getId();
						entered_pages = Integer.parseInt(lblPageNum.getText());

						System.out.println("Section id: " + entered_sectionID);

						lblSectionId.setBackground(new Color(255, 255, 255));
						lblSectionId.setForeground(new Color(0, 0, 0));
						lblPageNum.setBackground(new Color(255, 255, 255));
						lblPageNum.setForeground(new Color(0, 0, 0));
					} catch (NumberFormatException e) {
						validation = false;

						lblPageNum.setBackground(new Color(255, 0, 0));
						lblPageNum.setForeground(new Color(255, 255, 255));
						JOptionPane.showMessageDialog(null,
								"Use only numbers in the Pages field and select a valid section item from the dropdown list. ");
					}
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
					try {

						String test_accepted = sdf.format(datePickerAccepted.getDate());
						String test_published = sdf.format(datePicker.getDate());

					} catch (Exception ex) {
						validation = false;
						JOptionPane.showMessageDialog(null,
								"Use dates from calendar for fields: Date Published and Date Accepted");
					}
					if (lblFile.getText().contains("Not Uploaded")) {
						validation = false;
						JOptionPane.showMessageDialog(null,
								"There are files that have not been uploaded yet. Upload the files or clear the selection.");

					}
					if (validation) {
						Metadata meta_update = null;
						if(setting_meta.compareToIgnoreCase("true")==0){
						System.out.println("Metadata id: "+ metadata_id);
						metadata_id++;

						System.out.println("Metadata id: "+ metadata_id);
						meta_update = new Metadata(metadata_id, current_id, txtCompetingInterests.getText(),
								txtFunding.getText());
						}
						issue_screens.get(issue_id).dispose();
						articles_id++;

						if(setting_meta.compareToIgnoreCase("true")==0){
						metadata_storage.put(articles_id, meta_update);
						System.out.println("Metadata added");

						System.out.println(metadata_storage.get(articles_id).getCompeting_interests());}
						list_issues.replace(issue_id, articles_id);
						Issue current_issue = issue_storage.get(issue_id);

						current_issue.add_article(articles_id,
								new Article(articles_id, lblTitleText.getText(), entered_sectionID, entered_pages,
										lblAbstract.getText(), datePickerAccepted.getDate(), datePicker.getDate(),
										current_issue));
						int[] selections = listbox.getSelectedIndices();
						HashMap<Integer, Boolean> author_primary = new HashMap<Integer, Boolean>();
						author_primary_storage.put(articles_id, author_primary);
						for (int index : selections) {
							current_issue.add_author(articles_id, author_storage.get(author_list.get(index)));
							author_primary.put(author_storage.get(author_list.get(index)).getId(), false);
						}
						author_primary_storage.put(articles_id, author_primary);
						article.dispose();
						issue_storage.put(issue_id, current_issue);
						Object[] new_row = { articles_id, issue_id, 1, "title", 1, "abstract", sdf.format(current),
								"View", "Edit", "Delete" };
						HashMap<Integer, JFrame> issue_articles = article_screens.get(issue_id);
						System.out.println(articles_id);
						issue_articles.put(articles_id, new JFrame());

						article_screens.put(issue_id, issue_articles);
						System.out.println(article_screens.get(issue_id).containsKey(articles_id));

						issue(issue_id);
					}
					/*
					 * Article a =
					 * issue_storage.get(issue_id).getArticles_list().get(
					 * article_id); a.setTitle(lblTitleText.getText());
					 * ArrayList<Author> updated_authors = a.getAuthors(); for
					 * (int i = 0; i < updated_authors.size(); i++) { Author
					 * author = updated_authors.get(i); HashMap<Integer,
					 * JTextField> a_fields =
					 * author_fields.get(updated_authors.get(i).getId());
					 * author.setFirst_name(a_fields.get(1).getText());
					 * author.setLast_name(a_fields.get(2).getText());
					 * updated_authors.set(i, author); }
					 * a.setTitle(lblTitleText.getText());
					 * a.setAuthors(updated_authors);
					 * a.setAbstract_text(lblAbstract.getText());
					 * a.setSection_id(Integer.parseInt(lblSectionId.getText()))
					 * ; a.setPages(Integer.parseInt(lblPageNum.getText()));
					 * a.setDate_published(datePicker.getDate());
					 * 
					 * issue_storage.get(issue_id).update_article(a.getId(), a);
					 * article(issue_id, article_id);
					 */
				}
			});
			btnSave.setBounds((width_small - 200) / 2, height_small - 100, 200, 30);
			article.getContentPane().add(btnSave);
			Panel panel10 = new Panel();
			panel10.setBackground(new Color(153, 102, 51));
			panel10.setBounds(115, 310, 225, 160);

			lblFile.setForeground(Color.WHITE);
			lblFile.setEnabled(false);
			lblFile.setBounds(115, 310, 225, 160);
			lblFile.setToolTipText("");
			JScrollPane fileSection = new JScrollPane(lblFile);
			fileSection.setPreferredSize(new Dimension(300 * 2, 200));
			fileSection.setBounds(20, 310, 225, 160);
			fileSection.add(panel10);
			fileSection.createHorizontalScrollBar();
			panel.add(fileSection);
			ArrayList<File> uploaded_files = new ArrayList<File>();
			JFileChooser chooser = new JFileChooser();
			JButton select = new JButton("Browse");

			select.setBounds(20, 276, 90, 30);
			panel.add(select);
			JButton upload = new JButton("Upload");
			upload.setEnabled(false);
			select.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					FileNameExtensionFilter file = new FileNameExtensionFilter(
							"Galleys (pdf,xml,html) or Images(jpg,png)", "pdf", "xml", "html", "jpg", "png");
					chooser.setFileFilter(file);
					chooser.setMultiSelectionEnabled(true);
					int returnVal = chooser.showOpenDialog(article);

					if (returnVal == JFileChooser.APPROVE_OPTION) {
						upload.setEnabled(true);
						btnClear.setEnabled(true);
						File[] files = chooser.getSelectedFiles();
						String label_text = "";
						label_text = label_text + lblFile.getText() + "----Not Uploaded-----[\n";
						String label_tooltip = "";
						for (File f : files) {
							uploaded_files.add(f);
							label_text = label_text + f.getName() + "\n";
						}

						label_text = label_text + "]----Not Uploaded-----";
						label_tooltip = files.length + " files";
						lblFile.setText(label_text);
						lblFile.setToolTipText(label_tooltip);
					}
				}
			});
			upload.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					for (File f : uploaded_files) {
						file_copy(current_id, f.getPath().toString());
						System.out.println(chooser.getSelectedFile().getPath().toString());
					}
					if (!uploaded_files.isEmpty()) {
						select.setEnabled(false);
					}
					if (file_storage.containsKey(current_id)) {
						String label_text = "";
						HashMap<Integer, ArticleFile> files_existing = file_storage.get(current_id);
						Set<Integer> keys = files_existing.keySet();
						for (int k : keys) {
							ArticleFile a_file = files_existing.get(k);
							label_text = label_text + a_file.getPath().substring(a_file.getPath().lastIndexOf("/") + 1)
									+ "\n";
							System.out.println("::::" + label_text);
						}
						lblFile.setText(label_text);
					}
				}
			});

			btnClear.setEnabled(false);
			upload.setBounds(156, 276, 90, 30);
			btnClear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (file_storage.containsKey(current_id)) {
						HashMap<Integer, ArticleFile> up_files = file_storage.get(current_id);
						Set<Integer> keys = up_files.keySet();
						file_id = initial_file_num;
						for (int key : keys) {
							File f = new File(up_files.get(key).getPath());
							f.delete();
						}
						File folder = new File(String.format("src/files/%d/", current_id));
						folder.delete();
						file_storage.remove(current_id);

					}
					select.setEnabled(true);
					upload.setEnabled(false);
					btnClear.setEnabled(false);
					lblFile.setText("");
					lblFile.setToolTipText("");

				}
			});

			btnClear.setBounds(252, 315, 65, 30);
			panel.add(btnClear);
			panel.add(upload);

			article.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					if (file_storage.containsKey(current_id) && current_id != articles_id) {
						HashMap<Integer, ArticleFile> up_files = file_storage.get(current_id);
						Set<Integer> keys = up_files.keySet();
						file_id = initial_file_num;

						for (int key : keys) {
							File f = new File(up_files.get(key).getPath());
							f.delete();
						}
						File folder = new File(String.format("src/files/%d/", current_id));
						folder.delete();
						file_storage.remove(current_id);
						metadata_storage.remove(current_id);
					}
					issue(issue_id);
					// database_save();
				}
			});
			article.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					if (file_storage.containsKey(current_id) && current_id != articles_id) {
						HashMap<Integer, ArticleFile> up_files = file_storage.get(current_id);
						Set<Integer> keys = up_files.keySet();
						file_id = initial_file_num;
						for (int key : keys) {
							File f = new File(up_files.get(key).getPath());
							f.delete();
						}
						metadata_storage.remove(current_id);
						File folder = new File(String.format("src/files/%d/", current_id));
						folder.delete();
						metadata_storage.remove(current_id);
						file_storage.remove(current_id);
					}

					issue(issue_id);
					// database_save();
				}
			});
			article.repaint();
			panel6.repaint();
			if (article_screens.containsKey(issue_id)) {
				HashMap<Integer, JFrame> issue_articles = article_screens.get(issue_id);
				issue_articles.put(current_id, article);
				article_screens.put(issue_id, issue_articles);
			}

		} else {
			login("dashboard");
		}
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	@SuppressWarnings("deprecation")
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
		 ClientConnectionManager mgr = httpClient.getConnectionManager();
		    HttpParams params = httpClient.getParams();
		    httpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(params,mgr.getSchemeRegistry()), params);
		    httpClient.getCredentialsProvider().setCredentials(
                    new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT), 
                    new UsernamePasswordCredentials("ioannis", "root"));
		    try {
				httpClient.execute(new HttpGet("http://127.0.0.1:8000/api-auth/login/"));
			} catch (ClientProtocolException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			CookieStore cookieStore = httpClient.getCookieStore();
			List<org.apache.http.cookie.Cookie> cookies =  cookieStore.getCookies();
			System.out.println( cookies);
			String csrf = "";
			for (org.apache.http.cookie.Cookie cookie: cookies) {
				if (cookie.getName().compareTo("csrftoken")==0){
			   System.out.println( cookie.getValue());
			   csrf = cookie.getValue();
				}
			    
			}
		System.setProperty("http.agent", "Jakarta Commons-HttpClient/3.1");
		byte[] encoding = Base64.encodeBase64("ioannis:root".getBytes());
		HttpPost httppost = new HttpPost("http://127.0.0.1:8000/api-auth/login/");
		httppost.setHeader("Authorization", "Basic " +encoding);

		System.out.println("Authorization"+ "Basic " +encoding);
		httppost.setHeader("X-CSRFToken", csrf);
		System.out.println("executing request " + httppost.getRequestLine());
		HttpResponse response = null;
		try {
			httpClient.getParams().setBooleanParameter("http.protocol.expect-continue", false); 

			response = httpClient.execute(httppost);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(httppost.getAllHeaders().toString());
		HttpEntity entity = response.getEntity();
		System.out.println(response.toString());
		author_id = 6;
		author_storage.put(1, new Author(1, "Peter", "M.", "FakeAuthor", "fake_author@fakeaddress.com", "affiliation",
				"bio", "orcid", "testing", "gb"));
		author_storage.put(2, new Author(2, "Paul", "C.", "FakeAuthor", "fake_author@fakeaddress.com", "affiliation",
				"bio", "orcid", "testing", "gb"));
		author_storage.put(3, new Author(3, "Celia", "C.", "FakeAuthor", "fake_author@fakeaddress.com", "affiliation",
				"bio", "orcid", "testing", "gb"));
		author_storage.put(4, new Author(4, "Sen", "C.", "FakeAuthor", "fake_author@fakeaddress.com", "affiliation",
				"bio", "orcid", "testing", "gb"));
		author_storage.put(5, new Author(5, "Chihiro", "C.", "FakeAuthor", "fake_author@fakeaddress.com", "affiliation",
				"bio", "orcid", "testing", "gb"));
		author_storage.put(6, new Author(6, "Morty", "C.", "FakeAuthor", "fake_author@fakeaddress.com", "affiliation",
				"bio", "orcid", "testing", "gb"));
		section_db_id = 2;
		section_storage.put(1, new Section(1, "Section 1"));
		section_storage.put(2, new Section(2, "Section 2"));
		dashboard();
	}

	public void add_author() {

		api = new JFrame();
		api.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		api.getContentPane().setBackground(new Color(128, 128, 128));
		api.setTitle("API Information");
		api.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// database_save();
			}
		});
		api.setSize(480, 800);// 400 width and 500
								// height
		api.getContentPane().setLayout(null);// using no layout managers
		api.setVisible(true);

		JPanel panelMetadata = new JPanel();
		panelMetadata.setBackground(SystemColor.window);
		panelMetadata.setBounds(50, 107, 300, 307);
		panelMetadata.setLayout(null);
		panelMetadata.setAutoscrolls(true);
		JScrollPane articleSection = new JScrollPane(panelMetadata);
		panelMetadata.setAutoscrolls(true);

		JLabel lblCompetingInterests = new JLabel("Competing Interests");
		lblCompetingInterests.setBounds(35, 40, 131, 15);
		panelMetadata.add(lblCompetingInterests);
		JTextArea txtCompetingInterests = new JTextArea();
		txtCompetingInterests.setColumns(10);
		txtCompetingInterests.setBounds(35, 70, 131, 100);

		JScrollPane cmptinterests = new JScrollPane(txtCompetingInterests);
		cmptinterests.setBounds(35, 70, 131, 100);
		panelMetadata.add(cmptinterests);
		articleSection.setPreferredSize(new Dimension(320, 200));
		articleSection.setBounds(40, 132, 600 / 2 - 100, 720 - 280);
		// scrollSettings.setViewportView(scrollFrame);
		api.getContentPane().add(articleSection);

		JLabel lblFunding = new JLabel("Funding");
		lblFunding.setBounds(35, 175, 131, 15);
		panelMetadata.add(lblFunding);

		JTextArea txtFunding = new JTextArea();
		txtFunding.setColumns(10);
		txtFunding.setBounds(35, 195, 131, 100);

		JScrollPane funding = new JScrollPane(txtFunding);
		funding.setBounds(35, 195, 131, 100);
		panelMetadata.add(funding);
		panelMetadata.setVisible(false);
		/*
		 * JLabel lblMiddleName = new JLabel("Middle name");
		 * lblMiddleName.setHorizontalAlignment(SwingConstants.CENTER);
		 * lblMiddleName.setBounds(190, 96, 100, 20);
		 * panelSection.add(lblMiddleName);
		 * 
		 * JTextField txtMiddleName = new JTextField();
		 * txtMiddleName.setColumns(10); txtMiddleName.setBounds(90, 117, 300,
		 * 30); panelSection.add(txtMiddleName);
		 * 
		 * JLabel lblLastName = new JLabel("Last name");
		 * lblLastName.setHorizontalAlignment(SwingConstants.CENTER);
		 * lblLastName.setBounds(190, 148, 100, 20);
		 * panelSection.add(lblLastName);
		 * 
		 * JTextField txtLastName = new JTextField();
		 * txtLastName.setColumns(10); txtLastName.setBounds(90, 169, 300, 30);
		 * panelSection.add(txtLastName);
		 * 
		 * JLabel lblEmail = new JLabel("Email");
		 * lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		 * lblEmail.setBounds(190, 200, 100, 20); panelSection.add(lblEmail);
		 * 
		 * JTextField txtEmail = new JTextField(); txtEmail.setColumns(10);
		 * txtEmail.setBounds(90, 221, 300, 30); panelSection.add(txtEmail);
		 * 
		 * JLabel lblAffiliation = new JLabel("Affiliation");
		 * lblAffiliation.setHorizontalAlignment(SwingConstants.CENTER);
		 * lblAffiliation.setBounds(190, 252, 100, 20);
		 * panelSection.add(lblAffiliation);
		 * 
		 * JTextField txtAffiliation = new JTextField();
		 * txtAffiliation.setColumns(10); txtAffiliation.setBounds(90, 273, 300,
		 * 30); panelSection.add(txtAffiliation);
		 * 
		 * JLabel lblBio = new JLabel("Bio");
		 * lblBio.setHorizontalAlignment(SwingConstants.CENTER);
		 * lblBio.setBounds(190, 304, 100, 20); panelSection.add(lblBio);
		 * 
		 * JTextArea txtBio = new JTextArea(); txtBio.setColumns(10);
		 * txtBio.setBounds(90, 325, 300, 60); panelSection.add(txtBio);
		 * 
		 * JLabel lblOrcID = new JLabel("OrcID");
		 * lblOrcID.setHorizontalAlignment(SwingConstants.CENTER);
		 * lblOrcID.setBounds(190, 386, 100, 20); panelSection.add(lblOrcID);
		 * 
		 * JTextField txtOrcID = new JTextField(); txtOrcID.setColumns(10);
		 * txtOrcID.setBounds(90, 407, 300, 30); panelSection.add(txtOrcID);
		 * 
		 * JLabel lblDepartment = new JLabel("Department");
		 * lblDepartment.setHorizontalAlignment(SwingConstants.CENTER);
		 * lblDepartment.setBounds(190, 438, 100, 20);
		 * panelSection.add(lblDepartment);
		 * 
		 * JTextField txtDepartment = new JTextField();
		 * txtDepartment.setColumns(10); txtDepartment.setBounds(90, 459, 300,
		 * 30); panelSection.add(txtDepartment);
		 * 
		 * JLabel lblCountry = new JLabel("Country");
		 * lblCountry.setHorizontalAlignment(SwingConstants.CENTER);
		 * lblCountry.setBounds(190, 490, 100, 20);
		 * panelSection.add(lblCountry);
		 * 
		 * JTextField txtCountry = new JTextField(); txtCountry.setColumns(10);
		 * txtCountry.setBounds(90, 511, 300, 30); panelSection.add(txtCountry);
		 * panelSection.setBounds(0, 0, 480, 150); panelSection.setSize(new
		 * Dimension(480,150)); panelSection.setPreferredSize(new
		 * Dimension(480,150)); panelSection.setVisible(true);
		 */

		// JOptionPane.showMessageDialog(api,panelAuthor,"Information",JOptionPane.INFORMATION_MESSAGE);

	}

	public static void file_copy(int art_id, String source) {
		try {
			String filename = source.substring(source.lastIndexOf("/") + 1);
			System.out.print(String.format("src/files/%d/%s", art_id, filename));
			File dir = new File(String.format("src/files/%d/", art_id));
			dir.mkdirs();
			file_id++;
			HashMap<Integer, ArticleFile> article_files = null;
			if (!file_storage.containsKey(art_id)) {
				article_files = new HashMap<Integer, ArticleFile>();
			} else {
				article_files = file_storage.get(art_id);
			}
			article_files.put(file_id,
					new ArticleFile(file_id, art_id, String.format("src/files/%d/%s", art_id, filename)));

			file_storage.put(art_id, article_files);
			Files.copy(Paths.get(source), Paths.get(String.format("src/files/%d/%s", art_id, filename)),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws ParseException, java.text.ParseException {

		database_setup();
		populate_variables();

		// file copy to use for file upload
		// file_copy(1,"src/lib/db_xxs.png");

		new Main();
	}
}