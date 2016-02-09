package java_ojs;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import org.apache.commons.io.IOUtils;

import javax.ws.rs.ext.MessageBodyWorkers;
import org.apache.http.HttpVersion;
import org.apache.http.auth.AuthSchemeRegistry;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.fluent.Response;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.auth.DigestScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonFactory;

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
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.Authenticator;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.ServerSocket;
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
import org.jsoup.Jsoup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.net.httpserver.Headers;
import com.sun.org.apache.xerces.internal.util.URI;
import com.sun.org.apache.xerces.internal.util.URI.MalformedURIException;

import models.Article;
import models.ArticleFile;
import models.Author;
import models.Issue;
import models.Journal;
import models.Metadata;
import models.Section;

public class Main {
	JFrame login, api, issues, settings;
	private JTextField access_key, username;
	private JXTable issues_table, article_table;
	private static int delay = 1000; // milliseconds
	private JPasswordField passwordField;
	private static HashMap<String, String> list_settings;
	private static HashMap<Long, Long> list_issues;
	private static HashMap<Long, JFrame> issue_screens;
	private static HashMap<Long, Issue> issue_storage;
	private static HashMap<String, String> app_settings;
	private static HashMap<Long, Metadata> metadata_storage;
	private static HashMap<Long, Section> section_storage;
	private static HashMap<Long, Journal> journal_storage;
	private static HashMap<Long, Author> author_storage;
	private static HashMap<Long, Article> article_storage;
	private static HashMap<Long, ArrayList<Author>> article_author_storage;
	private static String journal_url = "";
	private static String user_url = "";
	private static HashMap<Long, HashMap<Long, Boolean>> author_primary_storage;
	private static HashMap<Long, HashMap<Long, ArticleFile>> file_storage;
	private static HashMap<Long, HashMap<Long, JFrame>> article_screens;
	private static ArrayList<String> setting_keys = new ArrayList<String>();
	private static Connection c = null;
	private static Statement stmt = null;
	private String api_insert_or_replace_statement = "INSERT OR REPLACE INTO API(journal_id, intersect_user_id, user_id, key) VALUES (?,?,?,?)";
	private String journal_insert_or_replace_statement = "INSERT OR REPLACE INTO JOURNAL(id,path,seq,primary_locale,enabled) VALUES (?,?,?,?,?)";
	private String settings_insert_or_replace_statement = "INSERT OR REPLACE INTO SETTING(NAME,VALUE) VALUES (?,?)";
	private String issue_insert_or_replace_statement = "INSERT OR REPLACE INTO ISSUE(id,title,volume,number,year,show_title,show_volume,show_number,show_year,date_published,date_accepted, published, current, access_status) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private String section_insert_or_replace_statement = "INSERT OR REPLACE INTO SECTION(id,title) VALUES (?,?)";
	private String author_insert_or_replace_statement = "INSERT OR REPLACE INTO AUTHOR(id,first_name,middle_name,last_name,email,affiliation,bio,orcid,department,country) VALUES (?,?,?,?,?,?,?,?,?,?)";
	private String unique_authors_insert_or_replace_statement = "INSERT OR REPLACE INTO UNIQUE_AUTHORS(first_name,middle_name,last_name,email,affiliation,bio,orcid,department,country) VALUES (?,?,?,?,?,?,?,?,?)";
	private String article_insert_or_replace_statement = "INSERT OR REPLACE INTO ARTICLE(id,title,section_id,pages,abstract,date_published,date_accepted,date_submitted,locale,language,status,submission_progress,current_round,fast_tracked,hide_author,comments_status,user_id,doi) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private String article_author_insert_or_replace_statement = "INSERT OR REPLACE INTO ARTICLE_AUTHOR(id,article_id,author_id,primary_author) VALUES (?,?,?,?)";
	private String issue_journal_insert_or_replace_statement = "INSERT OR REPLACE INTO ISSUE_JOURNAL(id,journal_id,issue_id) VALUES (?,?,?)";
	private String issue_article_insert_or_replace_statement = "INSERT OR REPLACE INTO ISSUE_ARTICLE(id,article_id,issue_id) VALUES (?,?,?)";
	private String file_insert_or_replace_statement = "INSERT OR REPLACE INTO FILE(id,article_id,path) VALUES (?,?,?)";
	private String metadata_insert_or_replace_statement = "INSERT OR REPLACE INTO METADATA(id,article_id,competing_interests,funding) VALUES (?,?,?,?)";
	private static DefaultHttpClient httpClient = new DefaultHttpClient();
	private String delete_issue_statement = "DELETE FROM ISSUE WHERE id=?";
	int width = 800;
	private int height = 600;
	private static Boolean logged_in = false;
	private static Boolean has_app_settings = false;
	private static long i_id = 0;
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	private static long journal_id = 0;
	private static long articles_id = 0;
	private static long file_id = 0;
	private static long author_id = 0;
	private static long section_db_id = 0;
	private static long metadata_id = 0;
	private static final int SOCKET_OPERATION_TIMEOUT = 5 * 1000;
	private static String base_url = "http://127.0.0.1:8000";
	CookieStore cookieStore = new BasicCookieStore();
	HttpContext httpContext = new BasicHttpContext();
	static String encoding = "";

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
			c.setAutoCommit(false);
			stmt.executeUpdate("DELETE FROM ISSUE_ARTICLE");
			stmt.executeUpdate("DELETE FROM ARTICLE_AUTHOR");
			stmt.executeUpdate("DELETE FROM AUTHOR");
			stmt.executeUpdate("DELETE FROM ARTICLE");
			stmt.executeUpdate("DELETE FROM SECTION");
			stmt.executeUpdate("DELETE FROM ISSUE_JOURNAL");
			stmt.executeUpdate("DELETE FROM ISSUE");
			stmt.executeUpdate("DELETE FROM JOURNAL");
			stmt.executeUpdate("DELETE FROM API");
			stmt.executeUpdate("DELETE FROM FILE");
			stmt.executeUpdate("DELETE FROM METADATA");

			PreparedStatement prep = c.prepareStatement(api_insert_or_replace_statement);
			prep.setFloat(1, Long.parseLong(app_settings.get("journal_id")));
			prep.setFloat(2, Long.parseLong(app_settings.get("intersect_user_id")));
			prep.setFloat(3, Long.parseLong(app_settings.get("user_id")));
			prep.setString(4, app_settings.get("key"));
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

			Set<Long> section_keys = section_storage.keySet();
			for (long key : section_keys) {
				Section save_section = section_storage.get(key);
				PreparedStatement section_prep = c.prepareStatement(section_insert_or_replace_statement);
				section_prep.setInt(1, (int) (long) save_section.getId());
				section_prep.setString(2, save_section.getTitle());
				section_prep.executeUpdate();
			}

			Set<Long> author_keys = author_storage.keySet();
			for (long key : author_keys) {
				Author save_author = author_storage.get(key);
				System.out.println("Author: " + key);
				PreparedStatement author_prep = c.prepareStatement(author_insert_or_replace_statement);
				author_prep.setInt(1, (int) (long) save_author.getId());
				author_prep.setString(2, save_author.getFirst_name());
				author_prep.setString(3, save_author.getMiddle_name());
				author_prep.setString(4, save_author.getLast_name());
				author_prep.setString(5, save_author.getEmail());
				author_prep.setString(6, save_author.getAffiliation() == null ? "" : save_author.getAffiliation());
				author_prep.setString(7, save_author.getBio() == null ? "" : save_author.getBio());
				author_prep.setString(8, save_author.getOrcid() == null ? "" : save_author.getOrcid());
				author_prep.setString(9, save_author.getDepartment() == null ? "" : save_author.getDepartment());
				author_prep.setString(10, save_author.getCountry() == null ? "" : save_author.getCountry());
				author_prep.executeUpdate();

			}
			Set<Long> file_art_keys = file_storage.keySet();

			for (long key : file_art_keys) {
				HashMap<Long, ArticleFile> files = file_storage.get(key);
				Set<Long> file_keys = files.keySet();
				for (long f_key : file_keys) {
					ArticleFile current_file = files.get((long) f_key);
					PreparedStatement file_prep = c.prepareStatement(file_insert_or_replace_statement);
					file_prep.setInt(1, (int) (long) current_file.getId());
					file_prep.setInt(2, (int) (long) current_file.getArticle_id());
					file_prep.setString(3, current_file.getPath());
					file_prep.executeUpdate();
				}
			}
			Set<Long> journal_keys = journal_storage.keySet();
			for (long key : journal_keys) {
				Journal current_journal = journal_storage.get(key);
				PreparedStatement journal_prep = c.prepareStatement(journal_insert_or_replace_statement);
				journal_prep.setInt(1, (int) (long) current_journal.getId());
				journal_prep.setString(2, current_journal.getPath());
				journal_prep.setFloat(3, current_journal.getSeq());
				journal_prep.setString(4, current_journal.getPrimary_locale());
				journal_prep.setFloat(5, current_journal.getEnabled());
				journal_prep.executeUpdate();
			}
			int journal_issue_count = 0;
			Set<Long> issue_keys = issue_storage.keySet();
			for (long key : issue_keys) {
				Issue save_issue = issue_storage.get(key);
				HashMap<Long, Article> articles = save_issue.getArticles_list();
				Set<Long> article_keys = articles.keySet();
				PreparedStatement issue_prep = c.prepareStatement(issue_insert_or_replace_statement);
				issue_prep.setInt(1, (int) (long) save_issue.getId());
				issue_prep.setString(2, save_issue.getTitle());
				issue_prep.setInt(3, save_issue.getVolume());
				issue_prep.setInt(4, save_issue.getNumber());
				issue_prep.setInt(5, save_issue.getYear());
				issue_prep.setInt(6, save_issue.getShow_title());
				issue_prep.setInt(7, save_issue.getShow_volume());
				issue_prep.setInt(8, save_issue.getShow_number());
				issue_prep.setInt(9, save_issue.getShow_year());

				issue_prep.setString(10, sdf
						.format(save_issue.getDate_published() == null ? new Date() : save_issue.getDate_published()));

				issue_prep.setString(11,
						sdf.format(save_issue.getDate_accepted() == null ? new Date() : save_issue.getDate_accepted()));
				issue_prep.setInt(12, save_issue.getPublished());
				issue_prep.setInt(12, save_issue.getAccess_status());
				issue_prep.setInt(12, save_issue.getCurrent());

				issue_prep.executeUpdate();
				Journal issue_journal = save_issue.getJournal();
				PreparedStatement issue_journal_prep = c.prepareStatement(issue_journal_insert_or_replace_statement);
				issue_journal_prep.setInt(1, journal_issue_count);
				issue_journal_prep.setInt(2, (int) (long) issue_journal.getId());
				issue_journal_prep.setInt(3, (int) (long) save_issue.getId());
				issue_journal_prep.executeUpdate();
				journal_issue_count++;
				int i = 1;
				int j = 1;
				for (long art_key : article_keys) {
					Article save_article = articles.get(art_key);
					PreparedStatement article_prep = c.prepareStatement(article_insert_or_replace_statement);
					article_prep.setInt(1, (int) (long) save_article.getId());
					article_prep.setString(2, save_article.getTitle());
					article_prep.setInt(3, (int) (long) save_article.getSection_id());
					article_prep.setString(4, save_article.getPages());
					article_prep.setString(5, save_article.getAbstract_text());
					article_prep.setString(6, sdf.format(
							save_article.getDate_published() == null ? new Date() : save_article.getDate_published()));

					article_prep.setString(7, sdf.format(
							save_article.getDate_accepted() == null ? new Date() : save_article.getDate_accepted()));
					article_prep.setString(8, sdf.format(
							save_article.getDate_submitted() == null ? new Date() : save_article.getDate_submitted()));
					article_prep.setString(9, save_article.getLocale());
					article_prep.setString(10, save_article.getLanguage());
					article_prep.setInt(11, save_article.getStatus());
					article_prep.setInt(12, save_article.getSubmission_progress());
					article_prep.setInt(13, save_article.getCurrent_round());
					article_prep.setInt(14, save_article.getFast_tracked());
					article_prep.setInt(15, save_article.getHide_author());
					article_prep.setInt(16, save_article.getComments_status());
					article_prep.setInt(17, (int) (long) save_article.getUser_id());
					article_prep.setString(18, save_article.getDoi());
					article_prep.executeUpdate();
					PreparedStatement issue_article_prep = c
							.prepareStatement(issue_article_insert_or_replace_statement);
					issue_article_prep.setInt(1, i);
					issue_article_prep.setInt(2, (int) (long) save_article.getId());
					issue_article_prep.setInt(3, (int) (long) save_issue.getId());
					issue_article_prep.executeUpdate();
					ArrayList<Author> authors = save_article.getAuthors();
					for (int b = 0; b < authors.size(); b++) {
						Author author = authors.get(b);
						PreparedStatement article_author_prep = c
								.prepareStatement(article_author_insert_or_replace_statement);
						article_author_prep.setInt(1, j);
						article_author_prep.setInt(2, (int) (long) save_article.getId());
						article_author_prep.setInt(3, (int) (long) author.getId());
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
			Set<Long> metadata_keys = metadata_storage.keySet();

			for (Long key : metadata_keys) {
				Metadata meta = metadata_storage.get(key);
				PreparedStatement meta_prep = c.prepareStatement(metadata_insert_or_replace_statement);
				meta_prep.setInt(1, (int) (long) meta.getId());
				meta_prep.setInt(2, (int) (long) meta.getArticle_id());
				meta_prep.setString(3, meta.getCompeting_interests());
				meta_prep.setString(4, meta.getFunding());
				meta_prep.executeUpdate();

			}
			c.commit();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Done.");
		JOptionPane.showMessageDialog(null, "Successfully updated database", "Save to Database",
				JOptionPane.INFORMATION_MESSAGE);

	}

	public static void app_settings_exist() throws SQLException {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c = DriverManager.getConnection("jdbc:sqlite:local_datatabse.db");
		stmt = c.createStatement();
		app_settings = new HashMap<String, String>();
		ResultSet rs = c.createStatement().executeQuery("SELECT * FROM API ;");
		boolean has = false;
		while (rs.next()) {
			app_settings.put("user_id", Long.toString((long) rs.getFloat("user_id")));
			app_settings.put("intersect_user_id", Long.toString((long) rs.getFloat("intersect_user_id")));
			app_settings.put("journal_id", Long.toString((long) rs.getFloat("journal_id")));
			app_settings.put("key", rs.getString("key"));
			has = true;
		}
		has_app_settings = has;
	}

	public static void populate_api(String user_id) throws SQLException {
		// Journal test_journal = new Journal(1, "up", (float) 2.0, "en_US", 0);
		// journal_storage.put((long)1, test_journal);

		if (!has_app_settings) {
			boolean profile_exists = false;
			try {
				profile_exists = get_profile_details(Long.parseLong(user_id));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!profile_exists) {
				app_settings.put("user_id", null);
				app_settings.put("intersect_user_id", null);
				app_settings.put("journal_id", null);
				app_settings.put("key", null);
			}

		}
	}

	public static void populate_variables() throws ParseException, java.text.ParseException {
		System.out.println("Retrieving data from local database");
		list_settings = new HashMap<String, String>();
		list_issues = new HashMap<Long, Long>();
		issue_storage = new HashMap<Long, Issue>();
		issue_screens = new HashMap<Long, JFrame>();
		file_storage = new HashMap<Long, HashMap<Long, ArticleFile>>();
		article_screens = new HashMap<Long, HashMap<Long, JFrame>>();
		author_storage = new HashMap<Long, Author>();
		section_storage = new HashMap<Long, Section>();
		author_primary_storage = new HashMap<Long, HashMap<Long, Boolean>>();
		metadata_storage = new HashMap<Long, Metadata>();
		journal_storage = new HashMap<Long, Journal>();
		article_author_storage = new HashMap<Long, ArrayList<Author>>();
		article_storage = new HashMap<Long, Article>();
		// Journal test_journal = new Journal(1, "up", (float) 2.0, "en_US", 0);
		// journal_storage.put((long)1, test_journal);
		try {
			app_settings_exist();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		try {

			JSONParser parser = new JSONParser();

			Object obj = null;
			try {
				obj = null;
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
			ResultSet rs = c.createStatement().executeQuery("SELECT * FROM JOURNAL ORDER BY id ASC;");
			while (rs.next()) {
				long id = rs.getInt("id");
				String path = rs.getString("path");
				float seq = rs.getFloat("seq");
				String primary_locale = rs.getString("primary_locale");
				int enabled = rs.getInt("enabled");

				Journal journal = null;
				journal = new Journal(id, path, seq, primary_locale, enabled);

				// JOptionPane.showMessageDialog(null, "Deleted");

				journal_storage.put(id, journal);

				System.out.println(journal_storage);
				journal_id = id;
			}

			System.out.println(journal_storage);
			rs.close();
			rs = c.createStatement().executeQuery("SELECT * FROM ISSUE ORDER BY id ASC;");
			while (rs.next()) {
				long id = rs.getInt("id");
				String title = rs.getString("title");
				int volume = rs.getInt("volume");
				int number = rs.getInt("number");
				int year = rs.getInt("year");
				int show_title = rs.getInt("show_title");
				int show_volume = rs.getInt("show_volume");
				int show_number = rs.getInt("show_number");
				int show_year = rs.getInt("show_year");
				int published = rs.getInt("published");
				int current = rs.getInt("current");
				int access_status = rs.getInt("access_status");

				String date_accepted = rs.getString("date_accepted");
				String date = rs.getString("date_published");
				Issue issue = null;
				issue = new Issue(id, title, volume, number, year, show_title, show_volume, show_number, show_year,
						sdf.parse(date_accepted), sdf.parse(date), published, current, access_status,
						new Journal(1, "up", (float) 2.0, "en_US", 0));

				// JOptionPane.showMessageDialog(null, "Deleted");

				list_issues.put(id, (long) 1);
				issue_screens.put(id, new JFrame());
				article_screens.put(id, new HashMap<Long, JFrame>());
				issue_storage.put(id, issue);
				i_id = id;
			}
			rs.close();
			System.out.println("Loading Section data....");
			ResultSet sect_s = c.createStatement().executeQuery("SELECT * FROM SECTION ORDER BY id ASC;");
			while (sect_s.next()) {
				long id = sect_s.getInt("id");
				String title = sect_s.getString("title");
				Section new_section = new Section(id, title);
				section_storage.put(id, new_section);
				section_db_id = id;
			}
			sect_s.close();

			System.out.println("Loading Author data....");
			ResultSet authors_s = c.createStatement().executeQuery("SELECT * FROM AUTHOR ORDER BY id ASC;");
			ResultSetMetaData author_rsmd = authors_s.getMetaData();
			while (authors_s.next()) {
				long id = authors_s.getInt("id");
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
				author_id = id;
				author_storage.put(id, author);
				System.out.println(author_storage.size());
			}
			authors_s.close();
			System.out.println("Loading Article data....");
			ResultSet art_s = c.createStatement().executeQuery("SELECT * FROM ARTICLE ORDER BY id ASC;");
			ResultSetMetaData rsmd = art_s.getMetaData();
			System.out.println(rsmd.getColumnName(2));
			ResultSet rs_issue=null;
			while (art_s.next()) {
				long id = art_s.getInt("id");
				String title = art_s.getString("title");
				int section_id = art_s.getInt("section_id");
				author_primary_storage.put(id, new HashMap<Long, Boolean>());

				HashMap<Long, ArticleFile> files = new HashMap<Long, ArticleFile>();
				file_storage.put((long) id, files);
				String pages = art_s.getString(rsmd.getColumnName(4));
				String abstract_text = art_s.getString(rsmd.getColumnName(5));

				String date = art_s.getString(rsmd.getColumnName(6));
				String date_accepted = art_s.getString("date_accepted");
				String date_submitted = art_s.getString("date_submitted");
				String doi = art_s.getString("doi");
				Article article = null;
				article = new Article(id, title, section_id, pages, abstract_text, sdf.parse(date_accepted),
						sdf.parse(date), sdf.parse(date_submitted), new Journal(1, "up", (float) 2.0, "en_US", 0));
				article.setDoi(doi);
				rs_issue = c.createStatement()
						.executeQuery("SELECT issue_id FROM ISSUE_ARTICLE WHERE article_id=" + Long.toString(id) + ";");
				long issue_id = rs_issue.getInt("issue_id");
				
				Issue update_issue = issue_storage.get(issue_id);
				update_issue.add_article(id, article);
				issue_storage.put(issue_id, update_issue);
				article.setIssue_fk(update_issue);
				article_storage.put(id, article);
				articles_id = id;
				// JOptionPane.showMessageDialog(null, "Deleted");

				HashMap<Long, JFrame> issue_articles = article_screens.get(issue_id);

				issue_articles.put(id, new JFrame());
				ArrayList<Author> new_authors = new ArrayList<Author>();
				article_author_storage.put(id, new_authors);
				article_screens.put(issue_id, issue_articles);

				rs_issue.close();
			}

			art_s.close();

			System.out.println("Loading File data....");
			ResultSet rs_files = c.createStatement().executeQuery("SELECT id,article_id,path FROM FILE");
			while (rs_files.next()) {
				long id = rs_files.getInt(1);
				long article_id = rs_files.getInt(2);
				String path = rs_files.getString(3);
				System.out.println("ARTICLE FILES: " + id);
				HashMap<Long, ArticleFile> files = file_storage.get((long) article_id);
				if ((long) file_id < id) {
					file_id = id;
				}
				if (files == null) {
					files = new HashMap<Long, ArticleFile>();
				}
				files.put((long) id, new ArticleFile(id, article_id, path));
				file_storage.put((long) article_id, files);
				System.out.println("FILES LOADED: " + article_id);

			}
			rs_files.close();
			System.out.println("Loading File data....");
			ResultSet rs_metadata = c.createStatement()
					.executeQuery("SELECT id,article_id,competing_interests,funding FROM METADATA");
			while (rs_metadata.next()) {
				long id = rs_metadata.getInt(1);
				long article_id = rs_metadata.getInt(2);
				String competing_interests = rs_metadata.getString(3);
				String funding = rs_metadata.getString(4);
				Metadata meta = new Metadata(id, article_id, competing_interests, funding);
				metadata_storage.put(article_id, meta);
				if (id > metadata_id) {
					metadata_id = id;
				}
			}
			rs_metadata.close();
			Set<Long> author_keys = author_storage.keySet();
			for (long key_author : author_keys) {
				Author author = author_storage.get(key_author);
				try {
					PreparedStatement prep = c.prepareStatement(
							"SELECT author_id,article_id,primary_author FROM ARTICLE_AUTHOR WHERE author_id="
									+ author.getId() + ";");

					ResultSet rs_author = prep.executeQuery();
					while (rs_author.next()) {
						long author_id = rs_author.getLong(1);

						long article_id = rs_author.getLong(2);

						Boolean primary = rs_author.getBoolean(3);
						System.out.println(author_id + " art:" + article_id);
						author.setArticle_id(article_id);
						author_storage.put(author.getId(), author);
						// System.out.println(author_id + " - " +
						// author.getId());

						// System.out.println(author.getFull_name() + " " +
						// Long.toString(article_id));
						HashMap<Long, Boolean> primary_authors = author_primary_storage.get(article_id);
						primary_authors.put(author_id, primary);
						// System.out.println("Author: " + author_id + "
						// Primary: " + primary);
						author_primary_storage.put(article_id, primary_authors);
						if (!article_author_storage.containsKey(author.getArticle_id())) {
							ArrayList<Author> new_authors = new ArrayList<Author>();
							new_authors.add(author);
							article_author_storage.put(author.getArticle_id(), new_authors);
							// System.out.println("new ADDED - - " +
							// author.getArticle_id());

						} else {
							ArrayList<Author> existing_authors = article_author_storage.get(author.getArticle_id());
							existing_authors.add(author);
							article_author_storage.put(author.getArticle_id(), existing_authors);
							// System.out.println("ADDED - - " +
							// author.getArticle_id());
						}
						ResultSet rs_new_issue = c.createStatement()
								.executeQuery("SELECT issue_id FROM ISSUE_ARTICLE;");
						while (rs_new_issue.next()) {
							long issue_id = rs_new_issue.getInt("issue_id");
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
		try {
			latest_ids();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done.");
	}

	public static boolean status_online() {
		boolean online = true;
		HttpGet issue_exists = new HttpGet(String.format("%s/", base_url));

		issue_exists.addHeader("Authorization", "Basic " + encoding);
		issue_exists.setHeader("Accept", "application/json");
		issue_exists.addHeader("Content-type", "application/json");

		HttpResponse response = null;
		try {
			response = httpClient.execute(issue_exists);
		} catch (ClientProtocolException e2) {
			// TODO Auto-generated catch block
			delay = 1000;
			return false;
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			delay = 1000;
			return false;
		}
		try {
			InputStream is = response.getEntity().getContent();
			is.close();
		} catch (IOException exc) {
			// TODO Auto-generated catch block
			exc.printStackTrace();
		}

		delay = 2500;
		return online;
	}

	public static boolean get_profile_details(long id) throws IllegalStateException, IOException {
		boolean exists = false;
		try {
			HttpGet httpGet = new HttpGet(String.format("%s/profiles/%s/?format=json", base_url, id));
			httpGet.addHeader("Authorization", "Basic " + encoding);
			httpGet.setHeader("Accept", "application/json");
			httpGet.addHeader("Content-type", "application/json");

			HttpResponse response = null;
			try {
				response = httpClient.execute(httpGet);
			} catch (ClientProtocolException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			JsonFactory jsonf = new JsonFactory();
			InputStream result = response.getEntity().getContent();
			org.json.simple.parser.JSONParser jsonParser = new JSONParser();

			JSONObject latest_json = new JSONObject();
			journal_url = "";
			user_url = "";
			try {
				JSONObject latest_obj = (JSONObject) jsonParser.parse(IOUtils.toString(result));

				journal_url = (String) latest_obj.get("journal");
				user_url = (String) latest_obj.get("user");
				exists = true;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				InputStream is = response.getEntity().getContent();
				is.close();
			} catch (IOException exc) {
				// TODO Auto-generated catch block
				exc.printStackTrace();
			}
			httpGet = new HttpGet(user_url);
			httpGet.addHeader("Authorization", "Basic " + encoding);
			httpGet.setHeader("Accept", "application/json");
			httpGet.addHeader("Content-type", "application/json");

			response = null;
			try {
				response = httpClient.execute(httpGet);
			} catch (ClientProtocolException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			jsonf = new JsonFactory();
			result = response.getEntity().getContent();
			jsonParser = new JSONParser();

			latest_json = new JSONObject();
			try {
				JSONObject latest_obj = (JSONObject) jsonParser.parse(IOUtils.toString(result));

				app_settings.put("user_id", Long.toString((long) latest_obj.get("id")));
				exists = true;
			} catch (ParseException e) {
				// TODO Auto-generated catch block

				e.printStackTrace();
			}

			try {
				InputStream is = response.getEntity().getContent();
				is.close();
			} catch (IOException exc) {
				// TODO Auto-generated catch block
				exc.printStackTrace();
			}
			httpGet = new HttpGet(journal_url);
			httpGet.addHeader("Authorization", "Basic " + encoding);
			httpGet.setHeader("Accept", "application/json");
			httpGet.addHeader("Content-type", "application/json");

			response = null;
			try {
				response = httpClient.execute(httpGet);
			} catch (ClientProtocolException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			jsonf = new JsonFactory();
			result = response.getEntity().getContent();
			jsonParser = new JSONParser();

			latest_json = new JSONObject();
			try {
				JSONObject latest_obj = (JSONObject) jsonParser.parse(IOUtils.toString(result));
				String journal = Long.toString((long) latest_obj.get("id"));
				app_settings.put("journal_id", journal);
				if (!journal_storage.containsKey(Long.parseLong(journal))) {
					Journal j = new Journal(Long.parseLong(journal), (String) latest_obj.get("path"),
							Float.parseFloat(Double.toString((double) latest_obj.get("seq"))),
							(String) latest_obj.get("primary_locale"), (long) latest_obj.get("enabled"));
					journal_storage.put(Long.parseLong(journal), j);
				}
				exists = true;
			} catch (ParseException e) {
				// TODO Auto-generated catch block

				e.printStackTrace();
			}

			try {
				InputStream is = response.getEntity().getContent();
				is.close();
			} catch (IOException exc) {
				// TODO Auto-generated catch block
				exc.printStackTrace();
			}
		} catch (ConnectException e) {
			throw e;
		}
		System.out.println(app_settings);
		return exists;
	}

	public static Long get_intersect_id() throws IllegalStateException, IOException {
		long id = -1;
		try {
			HttpGet httpGet = new HttpGet(String.format("%s/get/user_id/?format=json", base_url));
			httpGet.addHeader("Authorization", "Basic " + encoding);
			httpGet.setHeader("Accept", "application/json");
			httpGet.addHeader("Content-type", "application/json");

			HttpResponse response = null;
			try {
				response = httpClient.execute(httpGet);
			} catch (ClientProtocolException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			JsonFactory jsonf = new JsonFactory();
			InputStream result = response.getEntity().getContent();
			org.json.simple.parser.JSONParser jsonParser = new JSONParser();
			boolean exists = true;
			JSONObject latest_json = new JSONObject();
			try {
				JSONObject latest_obj = (JSONObject) jsonParser.parse(IOUtils.toString(result));
				String detail = (String) latest_obj.get("detail");
				System.out.println(latest_obj);
				if (detail != null) {
					exists = false;
				} else {
					id = (long) latest_obj.get("id");
					System.out.println(id);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(id);
		} catch (ConnectException e) {
			throw e;
		}
		return id;
	}

	public static Long get_remote_id(String type) throws IllegalStateException, IOException {

		long latest = 1;
		boolean status = status_online();
		System.out.println(status);
		if (!status) {
			return latest;
		}
		if (type.compareTo("article") == 0) {
			latest = articles_id;
		} else if (type.compareTo("issue") == 0) {
			latest = i_id;
		} else if (type.compareTo("journal") == 0) {
			latest = journal_id;
		} else if (type.compareTo("author") == 0) {
			latest = author_id;
		} else {
			latest = 1;
		}

		try {
			HttpGet httpGet = new HttpGet(String.format("%s/get/latest/%s/?format=json", base_url, type));
			httpGet.addHeader("Authorization", "Basic " + encoding);
			httpGet.setHeader("Accept", "application/json");
			httpGet.addHeader("Content-type", "application/json");

			HttpResponse response = null;
			try {
				response = httpClient.execute(httpGet);
			} catch (ClientProtocolException e2) {
				// TODO Auto-generated catch block
				return latest = 1;
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				return latest = 1;
			}
			JsonFactory jsonf = new JsonFactory();
			InputStream result = response.getEntity().getContent();
			org.json.simple.parser.JSONParser jsonParser = new JSONParser();
			boolean exists = true;
			JSONObject latest_json = new JSONObject();
			try {
				JSONObject latest_obj = (JSONObject) jsonParser.parse(IOUtils.toString(result));
				String detail = (String) latest_obj.get("detail");
				System.out.println(latest_obj);
				if (detail != null) {
					exists = false;
				} else {
					JSONArray results = (JSONArray) latest_obj.get("results");
					System.out.println(latest_obj);
					System.out.println(results.get(0));
					latest_json = (JSONObject) results.get(0);
					latest = (long) latest_json.get("id");
					System.out.println(latest);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(latest);
		} catch (ConnectException e) {
			throw e;
		}
		return latest;
	}

	public static void latest_ids() throws IllegalStateException, IOException {

		long remote_issue_id = 0;
		long remote_article_id = 0;
		long remote_journal_id = 0;
		long remote_author_id = 0;
		long remote_file_id = 0;
		remote_issue_id = get_remote_id("issue");
		remote_article_id = get_remote_id("article");
		remote_journal_id = get_remote_id("journal");
		remote_author_id = get_remote_id("author");
		remote_file_id = get_remote_id("file");
		if (remote_issue_id > i_id) {
			i_id = remote_issue_id;
		}
		if (remote_article_id > articles_id) {
			articles_id = remote_article_id;
		}
		if (remote_journal_id > journal_id) {
			journal_id = remote_journal_id;
		}
		if (remote_author_id > author_id) {
			author_id = remote_author_id;
		}
		if (remote_file_id > file_id) {
			file_id = remote_file_id;
		}
		System.out.println(remote_issue_id);
		System.out.println(remote_article_id);
		System.out.println(remote_journal_id);
		System.out.println(remote_file_id);
		System.out.println(remote_author_id + "-" + author_id);
	}

	public static void database_setup() {

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:local_datatabse.db");
			stmt = c.createStatement();

			String sql = "CREATE TABLE IF NOT EXISTS API"
					+ "(journal_id REAL, intersect_user_id REAL NOT NULL, user_id REAL PRIMARY KEY,"
					+ " key CHAR(256) NOT NULL)";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS JOURNAL" + "(id INTEGER PRIMARY KEY," + " path CHAR(32) UNIQUE,"
					+ "seq REAL," + "primary_locale CHAR(5)," + "enabled REAL)";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS ISSUE" + "(id INTEGER PRIMARY KEY," + " title CHAR(500) NOT NULL,"
					+ "volume INTEGER," + "number INTEGER," + "year INTEGER," + "published INTEGER,"
					+ " show_title INTEGER," + "show_volume INTEGER," + "show_number INTEGER,"
					+ "show_year INTEGER," + "access_status INTEGER," + "current INTEGER," + "date_published CHAR(50),"
					+ "date_accepted CHAR(50)" + ")";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS ISSUE_JOURNAL" + "(id INTEGER PRIMARY KEY," + " journal_id INTEGER,"
					+ " issue_id INTEGER," + "FOREIGN KEY (journal_id) REFERENCES JOURNAL(id),"
					+ "FOREIGN KEY (issue_id) REFERENCES ISSUE(id)" + ")";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS SECTION" + "(id INTEGER PRIMARY KEY," + " title CHAR(250) NOT NULL)";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS AUTHOR" + "(id INTEGER PRIMARY KEY," + " first_name CHAR(200) NOT NULL,"
					+ " middle_name CHAR(200) NOT NULL," + " last_name CHAR(200) NOT NULL,"
					+ " email CHAR(400) NOT NULL," + " affiliation CHAR(500) NOT NULL," + " bio CHAR(800),"
					+ " orcid CHAR(100)," + " department CHAR(300) NOT NULL," + " country CHAR(300) NOT NULL" + ")";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS UNIQUE_AUTHORS" + "(first_name CHAR(200) NOT NULL,"
					+ " middle_name CHAR(200) NOT NULL," + " last_name CHAR(200) NOT NULL,"
					+ " email CHAR(400) PRIMARY KEY," + " affiliation CHAR(500) NOT NULL," + " bio CHAR(800),"
					+ " orcid CHAR(100)," + " department CHAR(300) NOT NULL," + " country CHAR(300) NOT NULL" + ")";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS ARTICLE" + "(id INTEGER PRIMARY KEY," + " title CHAR(500) NOT NULL,"
					+ "section_id INTEGER," + "pages CHAR(255)," + " abstract CHAR(2000)," + "date_published CHAR(50),"
					+ "date_accepted CHAR(50)," + "date_submitted CHAR(50)," + "status INTEGER,"
					+ "submission_progress INTEGER," + "current_round INTEGER," + "fast_tracked INTEGER,"
					+ "hide_author INTEGER," + "comments_status INTEGER," + " language CHAR(50) NOT NULL,"
					+ " locale CHAR(50) NOT NULL," + "user_id REAL NOT NULL, "+ " doi CHAR(1000),"
					+ "FOREIGN KEY (section_id) REFERENCES SECTION(id)" + ")";
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
			if (has_app_settings) {
				logged_in = true;
				api(true);
			} else {

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
				login.setSize(width_small, height_small);// 400 width and 500
															// height
				login.getContentPane().setLayout(null);// using no layout
														// managers
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
				username.setColumns(4);
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
						BASE64Encoder encoder = new BASE64Encoder();
						encoding = encoder.encode(String.format("%s:%s", user, pass).getBytes());
						long user_id = -1;
						try {
							user_id = get_intersect_id();
						} catch (IllegalStateException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							populate_api(Long.toString(user_id));
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						app_settings.put("intersect_user_id", Long.toString(user_id));
						if (user_id != -1) {
							logged_in = true;
							login.setVisible(false);
							login.dispose();
							System.out.println(app_settings);
							if (app_settings.get("key") == null || app_settings.get("key").compareTo("") == 0) {
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
						BASE64Encoder encoder = new BASE64Encoder();
						encoding = encoder.encode(String.format("%s:%s", user, pass).getBytes());
						long user_id = -1;
						try {
							user_id = get_intersect_id();

						} catch (IllegalStateException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							populate_api(Long.toString(user_id));
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						app_settings.put("intersect_user_id", Long.toString(user_id));
						if (user_id != -1) {
							login.setVisible(false);
							if (app_settings.get("key") == null || app_settings.get("key").compareTo("") == 0) {
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
						if (status_online()) {
							internetCheck.setBackground(Color.GREEN);
							internetCheck.setText("ONLINE");
							btnSync1.setEnabled(true);

						} else {
							internetCheck.setBackground(Color.RED);
							internetCheck.setText("OFFLINE");
							btnSync1.setEnabled(false);
						}
					}
				};
				new Timer(delay, taskPerformer1).start();
				login.setVisible(true);// making the frame visible
			}
		} else {
			if (app_settings.get("key") == null || app_settings.get("key").compareTo("") == 0) {
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
						if (status_online()) {
							internetCheck.setBackground(Color.GREEN);
							internetCheck.setText("ONLINE");
							btnSync1.setEnabled(true);

						} else {
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

	public void api(final boolean exists) {
		if (logged_in) {
			if (exists) {
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
					api.getContentPane().setLayout(null);// using no layout
															// managers
					JLabel lblNewLabel = new JLabel("TORU");
					lblNewLabel.setForeground(new Color(255, 250, 250));
					lblNewLabel.setBackground(new Color(230, 230, 250));
					lblNewLabel.setFont(new Font("Trattatello", Font.BOLD, 24));
					lblNewLabel.setToolTipText("Welcome\n");

					lblNewLabel.setBounds((width_small / 2) - 34, 15, 95, 25);
					api.getContentPane().add(lblNewLabel);

					JPanel title_background = new JPanel();
					title_background.setBackground(new Color(0, 0, 0));
					title_background.setBounds(-17, 0, width_small + 33, 54);
					api.getContentPane().add(title_background);

					JPasswordField access_key = new JPasswordField();
					access_key.setColumns(4);
					access_key.setText("");
					access_key.setBounds(100, 270, width_small - 200, 26);
					api.getContentPane().add(access_key);
					JLabel lblAccessKey = new JLabel("Enter access key:");
					lblAccessKey.setHorizontalAlignment(SwingConstants.CENTER);
					lblAccessKey.setForeground(new Color(245, 255, 250));
					lblAccessKey.setBounds(80, 250, width_small - 161, 16);
					api.getContentPane().add(lblAccessKey);

					JButton btnSubmit = new JButton("Enter");
					Action actionSubmit = new AbstractAction() {
						@Override
						public void actionPerformed(ActionEvent e) {
							String key = String.valueOf(access_key.getPassword());
							System.out.println(key.hashCode());
							System.out.println(decodeHash(app_settings.get("key")));
							if (key.hashCode() == decodeHash(app_settings.get("key"))) {
								api.setVisible(false);
								api.dispose();
								dashboard();
							} else {
								JOptionPane.showMessageDialog(null, "Wrong access key");

							}

						}
					};
					access_key.addActionListener(actionSubmit);
					btnSubmit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String key = String.valueOf(access_key.getPassword());

							System.out.println(key.hashCode());
							System.out.println(decodeHash(app_settings.get("key")));
							if (key.hashCode() == decodeHash(app_settings.get("key"))) {
								api.setVisible(false);
								dashboard();
							} else {
								JOptionPane.showMessageDialog(null, "Wrong access key");
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
							if (status_online()) {
								internetCheck.setBackground(Color.GREEN);
								internetCheck.setText("ONLINE");
								btnSync1.setEnabled(true);

							} else {
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

			}

			else {
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
					api.getContentPane().setLayout(null);// using no layout
															// managers
					JLabel lblNewLabel = new JLabel("TORU");
					lblNewLabel.setForeground(new Color(255, 250, 250));
					lblNewLabel.setBackground(new Color(230, 230, 250));
					lblNewLabel.setFont(new Font("Trattatello", Font.BOLD, 24));
					lblNewLabel.setToolTipText("Welcome\n");

					lblNewLabel.setBounds((width_small / 2) - 34, 15, 95, 25);
					api.getContentPane().add(lblNewLabel);

					JPanel title_background = new JPanel();
					title_background.setBackground(new Color(0, 0, 0));
					title_background.setBounds(-17, 0, width_small + 33, 54);
					api.getContentPane().add(title_background);

					access_key = new JTextField();
					access_key.setColumns(4);
					access_key.setText("");
					access_key.setBounds(100, 270, width_small - 200, 26);
					api.getContentPane().add(access_key);
					JLabel lblAccessKey = new JLabel("Enter access key for future offline and online access:");
					lblAccessKey.setHorizontalAlignment(SwingConstants.CENTER);
					lblAccessKey.setForeground(new Color(245, 255, 250));
					lblAccessKey.setBounds(80, 250, width_small - 161, 16);
					api.getContentPane().add(lblAccessKey);

					JButton btnSubmit = new JButton("Submit");
					if (exists) {
						btnSubmit.setText("Save");
					}
					Action actionSubmit = new AbstractAction() {
						@Override
						public void actionPerformed(ActionEvent e) {
							String key = access_key.getText();
							app_settings.put("key", encodeHash(key));
							System.out.println(app_settings);
							api.setVisible(false);
							database_save();
							if (!exists) {
								dashboard();
							}

						}
					};
					access_key.addActionListener(actionSubmit);
					btnSubmit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String key = access_key.getText();
							api.setVisible(false);
							dashboard();
							app_settings.put("key", encodeHash(key));

							System.out.println(app_settings);

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
							if (status_online()) {
								internetCheck.setBackground(Color.GREEN);
								internetCheck.setText("ONLINE");
								btnSync1.setEnabled(true);

							} else {
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
			}
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

				Set<Long> issue_keys = issue_storage.keySet();
				ArrayList<List<Object>> rowData = new ArrayList<List<Object>>();
				Object[][] rows = new Object[issue_keys.size()][6];
				int i = 0;
				for (long id : issue_keys) {
					Issue row_issue = issue_storage.get(id);
					ArrayList<Object> data = new ArrayList<Object>();
					issue_screens.put(id, new JFrame());
					article_screens.put(id, new HashMap<Long, JFrame>());

					data.add(Long.toString(row_issue.getId()));
					data.add(row_issue.getShow_title()==1?row_issue.getTitle():"Hidden");
					data.add(row_issue.getShow_volume()==1?Integer.toString(row_issue.getVolume()):"Hidden");
					data.add(row_issue.getShow_number()==1?Integer.toString(row_issue.getNumber()):"Hidden");
					data.add(row_issue.getShow_year()==1?Integer.toString(row_issue.getYear()):"Hidden");
					data.add(row_issue.getDate_published() == null ? "/" : sdf.format(row_issue.getDate_published()));
					data.add("View");
					data.add("Edit");
					data.add("Delete");
					Object[] row = { row_issue.getId(), row_issue.getShow_title()==1?row_issue.getTitle():"Hidden", row_issue.getShow_volume()==1?Integer.toString(row_issue.getVolume()):"Hidden",
							row_issue.getShow_number()==1?Integer.toString(row_issue.getNumber()):"Hidden", row_issue.getShow_year()==1?Integer.toString(row_issue.getYear()):"Hidden",
							row_issue.getDate_accepted() == null ? "/" : sdf.format(row_issue.getDate_accepted()),
							row_issue.getDate_published() == null ? "/" : sdf.format(row_issue.getDate_published()),
							"View", "Edit", "Delete" };
					rows[i] = row;
					i++;
					rowData.add(data);

				}
				Object columnNames[] = { "ID", "Title", "Volume", "Number", "Year", "Date Accepted", "Date Published",
						"", "", "" };
				issues.getContentPane().setLayout(null);

				final JButton btnSync = new JButton("Sync");
				btnSync.setBounds(width - 155, 21, 70, 24);

				btnSync.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int dialogResult = -1;
						boolean skipped_dialog = false;
						Set<Long> issue_keys = issue_storage.keySet();
						if (issue_keys.isEmpty()) {
							skipped_dialog = true;
						}
						for (long key : issue_keys) {
							Issue current_issue = issue_storage.get(key);

							long issue_id = current_issue.getId();

							dialogResult = JOptionPane.showConfirmDialog(null,
									String.format(
											"Issue %s <%s>: Would You Like to replace local data (Yes) or update remote data (No)",
											current_issue.getTitle(), Long.toString(issue_id)),
									"Warning", 1);
							if (dialogResult == JOptionPane.NO_OPTION) {

								try {
									update_issue_intersect(current_issue, encoding);

								} catch (IllegalStateException | IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							} else if (dialogResult == JOptionPane.YES_OPTION) {
								System.out.println("update local");

								try {
									Issue updated_issue = update_issue_local(current_issue, encoding);

									issue_storage.put(issue_id, updated_issue);
									System.out.println(updated_issue);

								} catch (IllegalStateException | IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							if (dialogResult == JOptionPane.NO_OPTION) {

								try {
									update_articles_intersect(current_issue, encoding);

								} catch (IllegalStateException | IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							} else if (dialogResult == JOptionPane.YES_OPTION) {
								System.out.println("update local");

								try {
									update_articles_local(current_issue, encoding);

								} catch (IllegalStateException | IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

							}

							if (dialogResult == JOptionPane.NO_OPTION) {

								try {
									try {
										sync_authors_intersect(issue_id, encoding, false);
									} catch (IllegalStateException e2) {
										// TODO Auto-generated catch block
										e2.printStackTrace();
									} catch (IOException e2) {
										// TODO Auto-generated catch block
										e2.printStackTrace();
									}
								} catch (IllegalStateException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							} else if (dialogResult == JOptionPane.YES_OPTION) {
								try {
									get_authors_remote(issue_id, encoding, false);
								} catch (IllegalStateException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}

						}
						issues.repaint();
						ArrayList<Issue> new_issues = new ArrayList<Issue>();
						try {
							new_issues = update_get_issues_from_remote(encoding, false);
						} catch (IllegalStateException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						for (Issue current_issue : new_issues) {
							long issue_id = current_issue.getId();
							if (skipped_dialog) {
								try {
									update_articles_local(current_issue, encoding);
									get_authors_remote(issue_id, encoding, false);
								} catch (IllegalStateException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

							} else {
								if (dialogResult == JOptionPane.NO_OPTION) {

									try {
										update_articles_intersect(current_issue, encoding);

									} catch (IllegalStateException | IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								} else if (dialogResult == JOptionPane.YES_OPTION) {
									System.out.println("update local");

									try {
										update_articles_local(current_issue, encoding);

									} catch (IllegalStateException | IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}

								}

								if (dialogResult == JOptionPane.NO_OPTION) {

									try {
										try {
											sync_authors_intersect(issue_id, encoding, false);
										} catch (IllegalStateException e2) {
											// TODO Auto-generated catch block
											e2.printStackTrace();
										} catch (IOException e2) {
											// TODO Auto-generated catch block
											e2.printStackTrace();
										}
									} catch (IllegalStateException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								} else if (dialogResult == JOptionPane.YES_OPTION) {
									try {
										get_authors_remote(issue_id, encoding, false);
									} catch (IllegalStateException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							}
						}
						Set<Long> update_issue_keys = issue_storage.keySet();
						ArrayList<List<Object>> rowData = new ArrayList<List<Object>>();
						Object[][] rows = new Object[update_issue_keys.size()][6];
						boolean empty_table = false;
						int num_rows = ((DefaultTableModel) issues_table.getModel()).getRowCount();
						if (num_rows != 0) {
							for (int i = num_rows - 1; i >= 0; i--) {
								((DefaultTableModel) issues_table.getModel()).removeRow(i);
							}
						}
						int i = 0;
						for (long id : update_issue_keys) {
							Issue row_issue = issue_storage.get(id);
							issue_screens.put(id, new JFrame());
							article_screens.put(id, new HashMap<Long, JFrame>());

							Object[] row = { row_issue.getId(), row_issue.getShow_title()==1?row_issue.getTitle():"Hidden", row_issue.getShow_volume()==1?row_issue.getVolume():"Hidden",
									row_issue.getShow_number()==1?row_issue.getNumber():"Hidden", row_issue.getShow_year()==1?row_issue.getYear():"Hidden",
									row_issue.getDate_accepted() == null ? "/"
											: sdf.format(row_issue.getDate_accepted()),
									row_issue.getDate_published() == null ? "/"
											: sdf.format(row_issue.getDate_published()),
									"View", "Edit", "Delete" };
							rows[i] = row;
							((DefaultTableModel) issues_table.getModel()).insertRow(0, row);
							i++;

						}
						((DefaultTableModel) issues_table.getModel()).fireTableRowsUpdated(0,
								update_issue_keys.size() - 1);
						issues_table.repaint();
						issues.getContentPane().repaint();
						issues.repaint();
					}
				});

				issues.getContentPane().add(btnSync);
				Set<Long> author_keys = author_storage.keySet();
				ArrayList<Long> author_list = new ArrayList<Long>();
				String listData[] = new String[author_keys.size()];
				int j = 0;
				DefaultListModel listModel = new DefaultListModel();
				for (long key : author_keys) {
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
						if (status_online()) {
							internetCheck.setBackground(Color.GREEN);
							internetCheck.setText("ONLINE");
							btnSync.setEnabled(true);

						} else {
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
							long selected_issue = (long) selectedObject;
							if (issue_screens.get(selected_issue).isVisible()
									|| !(issue_screens.get(selected_issue) == null)) {
								HashMap<Long, JFrame> opened = article_screens.get(selected_issue);
								Set<Long> ids = opened.keySet();
								System.out.println("Issue: " + Long.toString(selected_issue));
								for (long id : ids) {
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
						long selected_issue = (long) selectedObject;
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
						long selected_issue = (long) selectedObject;

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

				/*
				 * JButton btnApi = new JButton("API");
				 * btnApi.addActionListener(new ActionListener() { public void
				 * actionPerformed(ActionEvent e) { api(true); } });
				 * btnApi.setBounds(103, 20, 90, 29);
				 * issues.getContentPane().add(btnApi);
				 */
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
			System.out.println("Loading log in window.");
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
			final long current_id = i_id + 1;
			height_small = (int) (680 - (680 * (5 / 100)));
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
			title.setBounds(100, 218, 250, 26);
			edit_issue.getContentPane().add(title);
			title.setColumns(4);

			JLabel lblTitleText = new JLabel("Title");
			lblTitleText.setForeground(new Color(245, 255, 250));
			lblTitleText.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitleText.setBounds(100, 200, 250, 16);
			edit_issue.getContentPane().add(lblTitleText);
			JPanel title_background = new JPanel();
			title_background.setBackground(new Color(0, 0, 0));
			title_background.setBounds(-17, 0, width_small + 33, 54);
			edit_issue.getContentPane().add(title_background);

			final JTextField volume = new JTextField();
			volume.setColumns(4);
			volume.setBounds(100, 270, 250, 26);
			edit_issue.getContentPane().add(volume);
			JLabel lblvolume = new JLabel("Volume");
			lblvolume.setHorizontalAlignment(SwingConstants.CENTER);
			lblvolume.setForeground(new Color(245, 255, 250));
			lblvolume.setBounds(100, 250, 250, 16);
			edit_issue.getContentPane().add(lblvolume);

			final JTextField number = new JTextField();
			number.setColumns(4);
			number.setBounds(100, 317, 250, 26);
			edit_issue.getContentPane().add(number);
			JLabel lblnumber = new JLabel("Number");
			lblnumber.setHorizontalAlignment(SwingConstants.CENTER);
			lblnumber.setForeground(new Color(245, 255, 250));
			lblnumber.setBounds(100, 300, 250, 16);
			edit_issue.getContentPane().add(lblnumber);

			final JTextField year = new JTextField();
			year.setColumns(4);
			year.setBounds(100, 364, 250, 26);
			edit_issue.getContentPane().add(year);
			JLabel lblyear = new JLabel("Year");
			lblyear.setHorizontalAlignment(SwingConstants.CENTER);
			lblyear.setForeground(new Color(245, 255, 250));
			lblyear.setBounds(100, 347, 250, 16);
			edit_issue.getContentPane().add(lblyear);

			JLabel lblDateAccepted = new JLabel("Date Submitted");
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
			
			final JCheckBox published_check = new JCheckBox("", true);
			
			published_check.setBounds(425, height_small-165, 100, 26);
			edit_issue.getContentPane().add(published_check);
			JLabel lblPublished = new JLabel("Published");
			lblPublished.setForeground(new Color(245, 255, 250));
			lblPublished.setHorizontalAlignment(SwingConstants.CENTER);
			lblPublished.setBounds(340, height_small-161, 100, 16);
			edit_issue.getContentPane().add(lblPublished);
			
			final JCheckBox current_check = new JCheckBox();
			current_check.setBounds(425, height_small-145, 100, 26);
			edit_issue.getContentPane().add(current_check);
			JLabel lblCurrent = new JLabel("Current");
			lblCurrent.setForeground(new Color(245, 255, 250));
			lblCurrent.setHorizontalAlignment(SwingConstants.CENTER);
			lblCurrent.setBounds(340, height_small-141, 100, 16);
			edit_issue.getContentPane().add(lblCurrent);
			
			final JCheckBox access_status_check = new JCheckBox();
			access_status_check.setBounds(425, height_small-125, 100, 26);
			edit_issue.getContentPane().add(access_status_check);
			JLabel lblStatus = new JLabel("Access Status");
			lblStatus.setForeground(new Color(245, 255, 250));
			lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
			lblStatus.setBounds(305, height_small-121, 150, 16);
			edit_issue.getContentPane().add(lblStatus);
			
			
			final JCheckBox show_title = new JCheckBox("",true);
			show_title.setBounds(459, 218, 250, 26);
			edit_issue.getContentPane().add(show_title);

			JLabel lblShowTitleText = new JLabel("Show Title");
			lblShowTitleText.setForeground(new Color(245, 255, 250));
			lblShowTitleText.setHorizontalAlignment(SwingConstants.CENTER);
			lblShowTitleText.setBounds(340, 200, 250, 16);
			edit_issue.getContentPane().add(lblShowTitleText);

			final JCheckBox show_volume = new JCheckBox("",true);
			show_volume.setBounds(459, 270, 250, 26);
			edit_issue.getContentPane().add(show_volume);

			JLabel lblShowVolume = new JLabel("Show Volume");
			lblShowVolume.setForeground(new Color(245, 255, 250));
			lblShowVolume.setHorizontalAlignment(SwingConstants.CENTER);
			lblShowVolume.setBounds(340, 250, 250, 16);
			edit_issue.getContentPane().add(lblShowVolume);

			final JCheckBox show_number = new JCheckBox("",true);
			show_number.setBounds(459, 317, 250, 26);
			edit_issue.getContentPane().add(show_number);

			JLabel lblShowNumber = new JLabel("Show Number");
			lblShowNumber.setForeground(new Color(245, 255, 250));
			lblShowNumber.setHorizontalAlignment(SwingConstants.CENTER);
			lblShowNumber.setBounds(340, 300, 250, 16);
			edit_issue.getContentPane().add(lblShowNumber);

			final JCheckBox show_year = new JCheckBox("",true);
			show_year.setBounds(459, 364, 250, 26);
			edit_issue.getContentPane().add(show_year);

			JLabel lblShowYear = new JLabel("Show Year");
			lblShowYear.setForeground(new Color(245, 255, 250));
			lblShowYear.setHorizontalAlignment(SwingConstants.CENTER);
			lblShowYear.setBounds(340, 347, 250, 16);
			edit_issue.getContentPane().add(lblShowYear);
			
			JButton btnSubmit = new JButton("Create");

			btnSubmit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Boolean validation = true;
					int entered_volume = 0;
					int entered_number = 0;
					int entered_year = 0;
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
						issue.setShow_title(show_title.isSelected()==true? 1:0);
						issue.setShow_volume(show_volume.isSelected()==true? 1:0);
						issue.setShow_year(show_year.isSelected()==true? 1:0);
						issue.setShow_number(show_number.isSelected()==true? 1:0);
					
						issue.setPublished(	published_check.isSelected() == true? 1:0);
						issue.setCurrent(current_check.isSelected() == true? 1:0);
						issue.setAccess_status(access_status_check.isSelected() == true? 1:0);
						issue.setJournal(new Journal(1, "up", (float) 2.0, "en_US", 0));
						// JOptionPane.showMessageDialog(null, "Deleted");

						list_issues.put(i_id, (long) 1);
						issue_screens.put(i_id, new JFrame());
						article_screens.put(i_id, new HashMap<Long, JFrame>());
						issue_storage.put(i_id, issue);
						Object[] new_row = { i_id, show_title.isSelected()==true?title.getText():"Hidden", show_volume.isSelected()==true?Integer.parseInt(volume.getText()):"Hidden",
								show_number.isSelected()==true?Integer.parseInt(number.getText()):"Hidden",show_year.isSelected()==true?Integer.parseInt(year.getText()):"Hidden",
								datePicker.getDate() == null ? "/" : sdf.format(datePicker.getDate()),datePickerPublished.getDate() == null ? "/" : sdf.format(datePickerPublished.getDate()), "View", "Edit",
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
				btnSubmit.setBounds(((width_small / 3) * 2) / 2, height_small - 69, width_small / 3, 29);
			} else {
				btnSubmit.setBounds(((width_small / 3) * 2) / 2, 339, width_small / 3, 29);
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

	public void edit_issue(final long issue_id) {
		if (logged_in) {
			if (issue_screens.containsKey(issue_id) && !issue_screens.get(issue_id).isVisible()) {
				int width_small = 0;
				int height_small = 0;
				if (height >= 480 && width >= 640) {
					width_small = (int) (1024 - (1024 * (37.5 / 100)));
				} else {
					width_small = (int) (960 - (960 * (37.5 / 100)));
				}
				height_small = (int) (680 - (680 * (5 / 100)));
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
				title.setBounds(100, 218, 250, 26);
				title.setText(current_issue.getTitle());
				edit_issue.getContentPane().add(title);
				title.setColumns(4);

				JLabel lblTitleText = new JLabel("Title");
				lblTitleText.setForeground(new Color(245, 255, 250));
				lblTitleText.setHorizontalAlignment(SwingConstants.CENTER);
				lblTitleText.setBounds(100, 200, 250, 16);
				edit_issue.getContentPane().add(lblTitleText);
				JPanel title_background = new JPanel();
				title_background.setBackground(new Color(0, 0, 0));
				title_background.setBounds(-17, 0, width_small + 33, 54);
				edit_issue.getContentPane().add(title_background);

				final JTextField volume = new JTextField();
				volume.setColumns(4);
				volume.setText(Integer.toString(current_issue.getVolume()));
				volume.setBounds(100, 270, 250, 26);
				edit_issue.getContentPane().add(volume);
				JLabel lblvolume = new JLabel("Volume");
				lblvolume.setHorizontalAlignment(SwingConstants.CENTER);
				lblvolume.setForeground(new Color(245, 255, 250));
				lblvolume.setBounds(100, 250, 250, 16);
				edit_issue.getContentPane().add(lblvolume);

				final JTextField number = new JTextField();
				number.setColumns(4);
				number.setText(Integer.toString(current_issue.getNumber()));
				number.setBounds(100, 317, 250, 26);
				edit_issue.getContentPane().add(number);
				JLabel lblnumber = new JLabel("Number");
				lblnumber.setHorizontalAlignment(SwingConstants.CENTER);
				lblnumber.setForeground(new Color(245, 255, 250));
				lblnumber.setBounds(100, 300, 250, 16);
				edit_issue.getContentPane().add(lblnumber);

				final JTextField year = new JTextField();
				year.setColumns(4);
				year.setText(Integer.toString(current_issue.getYear()));
				year.setBounds(100, 364, 250, 26);
				edit_issue.getContentPane().add(year);
				JLabel lblyear = new JLabel("Year");
				lblyear.setHorizontalAlignment(SwingConstants.CENTER);
				lblyear.setForeground(new Color(245, 255, 250));
				lblyear.setBounds(100, 347, 250, 16);
				edit_issue.getContentPane().add(lblyear);

				JLabel lblDateAccepted = new JLabel("Date Submitted");
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
				
				final JCheckBox published_check = new JCheckBox("", current_issue.getPublished()==1?true:false);
				
				published_check.setBounds(425, height_small-165, 100, 26);
				edit_issue.getContentPane().add(published_check);
				JLabel lblPublished = new JLabel("Published");
				lblPublished.setForeground(new Color(245, 255, 250));
				lblPublished.setHorizontalAlignment(SwingConstants.CENTER);
				lblPublished.setBounds(340, height_small-161, 100, 16);
				edit_issue.getContentPane().add(lblPublished);
				
				final JCheckBox current_check = new JCheckBox("", current_issue.getCurrent()==1?true:false);
				current_check.setBounds(425, height_small-145, 100, 26);
				edit_issue.getContentPane().add(current_check);
				JLabel lblCurrent = new JLabel("Current");
				lblCurrent.setForeground(new Color(245, 255, 250));
				lblCurrent.setHorizontalAlignment(SwingConstants.CENTER);
				lblCurrent.setBounds(340, height_small-141, 100, 16);
				edit_issue.getContentPane().add(lblCurrent);
				
				final JCheckBox access_status_check = new JCheckBox("", current_issue.getAccess_status()==1?true:false);
				access_status_check.setBounds(425, height_small-125, 100, 26);
				edit_issue.getContentPane().add(access_status_check);
				JLabel lblStatus = new JLabel("Access Status");
				lblStatus.setForeground(new Color(245, 255, 250));
				lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
				lblStatus.setBounds(305, height_small-121, 150, 16);
				edit_issue.getContentPane().add(lblStatus);
				final JCheckBox show_title = new JCheckBox("",current_issue.getShow_title()>=1?true:false);
				show_title.setBounds(459, 218, 250, 26);
				edit_issue.getContentPane().add(show_title);
				
				JLabel lblShowTitleText = new JLabel("Show Title");
				lblShowTitleText.setForeground(new Color(245, 255, 250));
				lblShowTitleText.setHorizontalAlignment(SwingConstants.CENTER);
				lblShowTitleText.setBounds(340, 200, 250, 16);
				edit_issue.getContentPane().add(lblShowTitleText);

				final JCheckBox show_volume = new JCheckBox("",current_issue.getShow_volume()>=1?true:false);
				show_volume.setBounds(459, 270, 250, 26);
				edit_issue.getContentPane().add(show_volume);

				JLabel lblShowVolume = new JLabel("Show Volume");
				lblShowVolume.setForeground(new Color(245, 255, 250));
				lblShowVolume.setHorizontalAlignment(SwingConstants.CENTER);
				lblShowVolume.setBounds(340, 250, 250, 16);
				edit_issue.getContentPane().add(lblShowVolume);

				final JCheckBox show_number = new JCheckBox("",current_issue.getShow_number()>=1?true:false);
				show_number.setBounds(459, 317, 250, 26);
				edit_issue.getContentPane().add(show_number);

				JLabel lblShowNumber = new JLabel("Show Number");
				lblShowNumber.setForeground(new Color(245, 255, 250));
				lblShowNumber.setHorizontalAlignment(SwingConstants.CENTER);
				lblShowNumber.setBounds(340, 300, 250, 16);
				edit_issue.getContentPane().add(lblShowNumber);

				final JCheckBox show_year = new JCheckBox("",current_issue.getShow_year()>=1?true:false);
				show_year.setBounds(459, 364, 250, 26);
				edit_issue.getContentPane().add(show_year);

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
							current_issue.setShow_title(show_title.isSelected()==true? 1:0);
							current_issue.setShow_volume(show_volume.isSelected()==true? 1:0);
							current_issue.setShow_year(show_year.isSelected()==true? 1:0);
							current_issue.setShow_number(show_number.isSelected()==true? 1:0);
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
							current_issue.setShow_title(show_title.isSelected()==true?1:0);
							current_issue.setShow_volume(show_volume.isSelected()==true?1:0);
							current_issue.setShow_year(show_year.isSelected()==true?1:0);
							current_issue.setShow_number(show_number.isSelected()==true?1:0);
							edit_issue.dispose();
							issue_storage.put(issue_id, current_issue);
							issues.dispose();
							dashboard();
						}

					}
				});
				if (height_small - 150 > 300) {
					btnSubmit.setBounds(((width_small / 3) * 2) / 2, height_small - 69, width_small / 3, 29);
				} else {
					btnSubmit.setBounds(((width_small / 3) * 2) / 2, 339, width_small / 3, 29);
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

	public void issue(final long issue_id) {
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
				HashMap<Long, JFrame> issue_articles = new HashMap<Long, JFrame>();
				articles.getContentPane().setBackground(new Color(128, 128, 128));

				articles.setLocationRelativeTo(null);
				articles.setTitle("Issue <" + Long.toString(issue_id) + ">");
				articles.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						// database_save();
					}
				});
				Date current = new Date();

				Issue current_issue = issue_storage.get(issue_id);
				HashMap<Long, Article> current_articles = current_issue.getArticles_list();
				Set<Long> art_keys = current_articles.keySet();
				ArrayList<List<Object>> rowData = new ArrayList<List<Object>>();
				Object[][] rows = new Object[art_keys.size()][11];
				int i = 0;
				for (long id : art_keys) {
					ArrayList<Object> data = new ArrayList<Object>();
					issue_articles.put(id, new JFrame());
					System.out.println("Article: " + current_articles.get(id).getId());
					data.add(Long.toString(current_articles.get(id).getId()));
					data.add(Long.toString(issue_id));
					data.add(Long.toString((current_articles.get(id).getSection_id())));
					data.add(current_articles.get(id).getTitle());
					data.add(current_articles.get(id).getPages() == null ? current_articles.get(id).getPages() == null
							: "/");
					data.add(current_articles.get(id).getAbstract_text());
					data.add(sdf.format(current));
					data.add("View");
					data.add("Edit");
					data.add("Delete");
					Date date_submitted = current_articles.get(id).getDate_submitted();
					String date_submit = "";
					if (date_submitted == null) {
						date_submit = "/";
					} else {
						date_submit = sdf.format(current_articles.get(id).getDate_submitted());
					}
					Date date_published = current_articles.get(id).getDate_published();
					String date_pub = "";
					if (date_published == null) {
						date_pub = "/";
					} else {
						date_pub = sdf.format(current_articles.get(id).getDate_published());
					}
					Object[] row = { current_articles.get(id).getId(), issue_id,
							current_articles.get(id).getSection_id(), current_articles.get(id).getTitle(),
							current_articles.get(id).getPages(), current_articles.get(id).getAbstract_text(),
							date_submit, date_pub, "View", "Edit", "Delete" };
					rows[i] = row;
					i++;
					rowData.add(data);

				}

				article_screens.put(issue_id, issue_articles);
				Object columnNames[] = { "ID", "Issue", "Section", "Title", "Pages", "Abstract", "Date Accepted",
						"Date Published", "", "", "" };

				articles.getContentPane().setLayout(null);

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

				final JButton btnSync = new JButton("Sync");
				btnSync.setBounds(width - 155, 21, 70, 24);
				btnSync.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println(current_issue.getArticles_list().isEmpty());

						boolean update_table = false;
						if (!current_issue.getArticles_list().isEmpty()) {
							int dialogResult = JOptionPane.showConfirmDialog(null,
									"Would You Like to replace local data (Yes) or update remote data (No)", "Warning",
									1);
							if (dialogResult == JOptionPane.NO_OPTION) {

								try {
									update_articles_intersect(current_issue, encoding);

								} catch (IllegalStateException | IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							} else if (dialogResult == JOptionPane.YES_OPTION) {
								System.out.println("update local");

								try {
									update_table = true;
									update_articles_local(current_issue, encoding);
									articles.dispose();
									issue(issue_id);
								} catch (IllegalStateException | IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								articles.repaint();

							}
						} else {
							System.out.println("Local");
							try {
								update_articles_local(current_issue, encoding);
								articles.dispose();
								issue(issue_id);
							} catch (IllegalStateException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						int dialogResult = JOptionPane.showConfirmDialog(null,
								"Would You Like to replace local Author data (Yes) or update remote Author data (No)",
								"Warning", 1);

						if (dialogResult == JOptionPane.NO_OPTION) {

							try {
								try {
									sync_authors_intersect(issue_id, encoding, false);
								} catch (IllegalStateException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								} catch (IOException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
							} catch (IllegalStateException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else if (dialogResult == JOptionPane.YES_OPTION) {
							try {
								get_authors_remote(issue_id, encoding, false);
							} catch (IllegalStateException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						if (update_table) {
							HashMap<Long, Article> all_articles = current_issue.getArticles_list();
							Set<Long> keys = all_articles.keySet();
							ArrayList<List<Object>> rowData = new ArrayList<List<Object>>();
							Object[][] rows = new Object[all_articles.size()][11];
							boolean empty_table = false;
							int num_rows = 0;
							try {
								num_rows = article_table.getRowCount();
							} catch (NullPointerException n_e) {
							}
							if (num_rows != 0) {
								for (int i = num_rows - 1; i >= 0; i--) {
									System.out.println(num_rows);
									((DefaultTableModel) article_table.getModel()).removeRow(i);

									System.out.println(
											"--" + ((DefaultTableModel) article_table.getModel()).getRowCount());
								}
							}
							int i = 0;
							try {
								get_authors_remote(issue_id, encoding, false);
							} catch (IllegalStateException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							for (long id : keys) {
								Article row_article = all_articles.get(id);
								
								ArrayList<Object> data = new ArrayList<Object>();
								issue_articles.put(id, new JFrame());

								data.add(Long.toString(all_articles.get(id).getId()));
								data.add(Long.toString(current_issue.getId()));
								data.add(Long.toString((all_articles.get(id).getSection_id())));
								data.add(all_articles.get(id).getTitle());
								data.add(all_articles.get(id).getPages() == null ? "/"
										: all_articles.get(id).getPages());
								data.add(all_articles.get(id).getAbstract_text());
								data.add(sdf.format(current));
								data.add("View");
								data.add("Edit");
								data.add("Delete");
								Date date_submitted = all_articles.get(id).getDate_submitted();
								String date_submit = "";
								if (date_submitted == null) {
									date_submit = "/";
								} else {
									date_submit = sdf.format(all_articles.get(id).getDate_submitted());
								}
								Date date_published = all_articles.get(id).getDate_published();
								String date_pub = "";
								if (date_published == null) {
									date_pub = "/";
								} else {
									date_pub = sdf.format(all_articles.get(id).getDate_published());
								}
								Object[] row = { all_articles.get(id).getId(), issue_id,
										all_articles.get(id).getSection_id(), current_articles.get(id).getTitle(),
										all_articles.get(id).getPages(), all_articles.get(id).getAbstract_text(),
										date_submit, date_pub, "View", "Edit", "Delete" };
								rows[i] = row;
								rowData.add(data);
								((DefaultTableModel) article_table.getModel()).insertRow(0, row);
								i++;
								System.out.println("++" + ((DefaultTableModel) article_table.getModel()).getRowCount());

							}
							System.out.println(num_rows);
							try {
								num_rows = ((DefaultTableModel) article_table.getModel()).getRowCount();
							} catch (NullPointerException n_e) {
							}
							System.out.println(":::" + num_rows);
							if (num_rows != 0) {
								((DefaultTableModel) article_table.getModel()).fireTableRowsUpdated(0, num_rows - 1);
							}

						}
						article_table.repaint();
						articles.getContentPane().repaint();
						articles.repaint();
					}

				});
				articles.getContentPane().add(btnSync);

				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				scrollPane.setBounds(15, height / 16 * 7, width - 30, (height - 130) - (height / 16 * 7) - 10);
				articles.getContentPane().add(scrollPane);
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
						if (status_online()) {
							internetCheck.setBackground(Color.GREEN);
							internetCheck.setText("ONLINE");
							btnSync.setEnabled(true);

						} else {
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
							long selected_article = (long) selectedObject;
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
						long selected_article = (long) selectedObject;
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
						long selected_article = (long) selectedObject;
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
				lblIssueId.setBounds(136, 60, 300, 30);
				lblIssueId.setText(Long.toString(issue_id));
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
						row_issue.getNumber(), row_issue.getYear(),
						row_issue.getDate_accepted() == null ? "/" : sdf.format(row_issue.getDate_accepted()),
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

	public void article(final long issue_id, final long article_id) {
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
				String setting_meta = list_settings.get("Metadata");
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
				articleSection.setBounds(40, 132, width_small / 2 - 100, height_small - 246);
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
				txtCompetingInterests.setColumns(4);
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
				txtFunding.setColumns(4);
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
				if (setting_meta.compareToIgnoreCase("true") == 0) {
					panel.add(btnAddMetadata);
					article.getContentPane().add(btnAddMetadata);
				}
				// scrollSettings.setViewportView(scrollFrame);
				JPanel panel3 = new JPanel();
				panel3.setBackground(SystemColor.window);
				panel3.setBounds(50, height_small - 260, 320, 120);
				article.getContentPane().add(panel3);
				panel3.setLayout(null);
				panel3.setAutoscrolls(true);

				panel3.setPreferredSize(new Dimension(640, 640));
				JScrollPane abstractSection = new JScrollPane(panel3);
				abstractSection.setHorizontalScrollBarPolicy(abstractSection.HORIZONTAL_SCROLLBAR_ALWAYS);

				abstractSection.setPreferredSize(new Dimension(600, 8));
				abstractSection.setBounds(width_small / 2 - 40, 132 + height_small / 2 - 130, width_small / 2,
						height_small / 2 - 150);
				panel3.setAutoscrolls(true);

				JLabel lblNewLabel_1 = new JLabel("Abstract");
				lblNewLabel_1.setBounds(16, 6, 61, 16);
				panel3.add(lblNewLabel_1);
				String[] lines = current_article.getAbstract_text().split("\r\n");

				JTextArea lblAbstract = new JTextArea(current_article.getAbstract_text());
				lblAbstract.setEditable(false);
				lblAbstract.setBounds(16, 28, 560, 24 * lines.length);
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

				JLabel lblIssueId = new JLabel(Long.toString(issue_id));
				lblIssueId.setBounds(160, 48, 94, 30);
				panel.add(lblIssueId);
				lblIssueId.setForeground(Color.BLACK);
				lblIssueId.setFont(new Font("Dialog", Font.BOLD, 14));
				lblIssueId.setText(Long.toString(issue_id));

				final JLabel lblDateAccepted = new JLabel("Date Submitted:");
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

				JLabel lblDate = new JLabel(current_article.getDate_published() == null ? "/"
						: sdf.format(current_article.getDate_published()));
				lblDate.setVerticalAlignment(SwingConstants.TOP);
				lblDate.setForeground(Color.BLACK);
				lblDate.setFont(new Font("Dialog", Font.BOLD, 14));
				lblDate.setBounds(160, 240, 125, 30);
				panel.add(lblDate);
				JLabel lblDateAccept = new JLabel(current_article.getDate_accepted() == null ? "/"
						: sdf.format(current_article.getDate_accepted()));
				lblDateAccept.setVerticalAlignment(SwingConstants.TOP);
				lblDateAccept.setForeground(Color.BLACK);
				lblDateAccept.setFont(new Font("Dialog", Font.BOLD, 14));
				lblDateAccept.setBounds(160, 207, 125, 30);
				panel.add(lblDateAccept);
				JLabel lblArticleId = new JLabel(Long.toString(current_article.getId()));
				lblArticleId.setBounds(160, 19, 94, 30);
				panel.add(lblArticleId);
				lblArticleId.setForeground(Color.BLACK);
				lblArticleId.setFont(new Font("Dialog", Font.BOLD, 14));
				lblArticleId.setText(Long.toString(article_id));

				JLabel lblPages = new JLabel("Pages:");
				lblPages.setForeground(Color.BLACK);
				lblPages.setFont(new Font("Dialog", Font.BOLD, 14));
				lblPages.setBounds(24, 165, 94, 30);
				panel.add(lblPages);

				JLabel lblPageNum = new JLabel(current_article.getPages());
				lblPageNum.setVerticalAlignment(SwingConstants.TOP);
				lblPageNum.setForeground(Color.BLACK);
				lblPageNum.setFont(new Font("Dialog", Font.BOLD, 14));
				lblPageNum.setBounds(160, 171, 125, 30);
				panel.add(lblPageNum);
				JLabel lblDoi = new JLabel("DOI:");
				lblDoi.setForeground(Color.BLACK);
				lblDoi.setFont(new Font("Dialog", Font.BOLD, 14));
				lblDoi.setBounds(24, 268, 94, 30);
				panel.add(lblDoi);

				final JLabel doi = new JLabel(current_article.getDoi());
				doi.setForeground(Color.BLACK);
				doi.setFont(new Font("Dialog", Font.BOLD, 14));
				doi.setBounds(85, 269, 180, 30);
				panel.add(doi);
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

				JLabel lblSectionId = new JLabel(Long.toString(current_article.getSection_id()));
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

				System.out.println("ID: " + (long) article_id);
				System.out.println("Files: " + file_storage.containsKey((long) article_id));

				if (file_storage.containsKey((long) article_id)) {
					HashMap<Long, ArticleFile> files = file_storage.get((long) article_id);
					Set<Long> keys = files.keySet();
					System.out.println("Files: " + keys.size());
					int y_f = 23;
					for (long key : keys) {
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
						JLabel file_l = new JLabel(files.get((long) key).getPath()
								.substring(files.get((long) key).getPath().lastIndexOf("/") + 1));
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
								fileChooser.setSelectedFile(new File(files.get((long) key).getPath()
										.substring(files.get((long) key).getPath().lastIndexOf("/") + 1)));
								fileChooser.setFileFilter(file);
								// File new_f=new
								// File(files.get((long)key).getPath());
								int userSelection = fileChooser.showSaveDialog(panel);

								// fileChooser.setSelectedFile(new
								// File(files.get((long)key).getPath()));
								if (userSelection == JFileChooser.APPROVE_OPTION) {
									System.out.println("Save as file: " + fileChooser.getCurrentDirectory().getPath()
											+ files.get((long) key).getPath()
													.substring(files.get((long) key).getPath().lastIndexOf("/")));
									String output_path = fileChooser.getCurrentDirectory().getPath() + "/"
											+ fileChooser.getSelectedFile().getName();
									System.out.println(output_path);
									System.out.println(fileChooser.getSelectedFile().getName());
									// files.get((long)key).getPath().substring(files.get((long)key).getPath().lastIndexOf("/"));

									try {
										Files.copy(Paths.get(files.get((long) key).getPath()), Paths.get(output_path),
												StandardCopyOption.REPLACE_EXISTING);
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										JOptionPane.showMessageDialog(null,
												String.format("File %s does not exist locally.",
														files.get((long) key).getPath().substring(
																files.get((long) key).getPath().lastIndexOf("/") + 1)));
										boolean is_online = status_online();
										if (is_online) {
											int dialogResult = JOptionPane.showConfirmDialog(null,
													"Would you like to download it ?",

													"Warning", 1);
											if (dialogResult == JOptionPane.YES_OPTION) {
												try {
													file_download(article_id, key);
												} catch (IllegalStateException e2) {
													// TODO Auto-generated catch
													// block
													e2.printStackTrace();
												} catch (IOException e2) {
													// TODO Auto-generated catch
													// block
													e2.printStackTrace();
												}
											} else {
											}
										}
									}

									// System.out.println("Save as file: " +
									// fileToSave.getAbsolutePath());
								}
							}
						});
						btnDeleteFile.setAction(new AbstractAction() {
							public void actionPerformed(ActionEvent e) {

								File f = new File(files.get((long) key).getPath());
								f.delete();
								HashMap<Long, ArticleFile> deleted = file_storage.get((long) article_id);
								deleted.remove(key);
								file_storage.put((long) article_id, deleted);
								article.dispose();
								article(issue_id, article_id);

							}
						});
						btnDeleteFile.setIcon(deleteicon);
						btnSaveFile.setIcon(saveicon);
						panel11.add(btnSaveFile);
						panel11.add(btnDeleteFile);
						String path = files.get((long) key).getPath();
						label_text = label_text + path.substring(path.lastIndexOf("/") + 1) + "\n";
					}
				}
				JTextArea lblFile = new JTextArea(label_text);
				lblFile.setForeground(Color.WHITE);
				lblFile.setEnabled(false);
				lblFile.setBounds(115, 346, 225, 160);
				lblFile.setToolTipText("");
				JScrollPane fileSection = new JScrollPane(panel11);

				fileSection.setPreferredSize(new Dimension(300 * 2, 100 * file_storage.size()));
				fileSection.setBounds(20, 346, 265, 160);
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
				select.setBounds(20, 310, 90, 30);
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
				upload.setBounds(150, 310, 90, 30);

				panel.add(upload);

				article.setVisible(true);
				if (article_screens.containsKey(issue_id)) {
					HashMap<Long, JFrame> issue_articles = article_screens.get(issue_id);
					issue_articles.put(article_id, article);
					article_screens.put(issue_id, issue_articles);
				}
			}

		} else {
			login("dashboard");
		}
	}

	public void edit_article(final long issue_id, final long article_id) {
		if (logged_in) {
			if (article_screens.containsKey(issue_id) && article_screens.get(issue_id).containsKey(article_id)
					&& !article_screens.get(issue_id).get(article_id).isVisible()) {
				Article current_article = issue_storage.get(issue_id).getArticles_list().get(article_id);
				long initial_file_num = file_id;
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

				String setting_meta = list_settings.get("Metadata");
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
				articleSection.setBounds(40, 132, width_small / 2 - 100, height_small - 246);
				// scrollSettings.setViewportView(scrollFrame);
				article.getContentPane().add(articleSection);

				JPanel panel3 = new JPanel();
				panel3.setBackground(SystemColor.window);
				panel3.setBounds(50, 107, 320, 307);
				article.getContentPane().add(panel3);
				panel3.setLayout(null);
				panel3.setAutoscrolls(true);

				panel3.setPreferredSize(new Dimension(640, 960));
				JScrollPane abstractSection = new JScrollPane(panel3);
				panel3.setAutoscrolls(true);

				JLabel lblNewLabel_1 = new JLabel("Abstract");
				lblNewLabel_1.setBounds(16, 6, 61, 16);
				panel3.add(lblNewLabel_1);

				final JTextArea lblAbstract = new JTextArea(current_article.getAbstract_text());
				lblAbstract.setEditable(true);
				String[] lines = current_article.getAbstract_text().split("\r\n");
				lblAbstract.setBounds(16, 28, 560, 480 + 10 * lines.length);
				lblAbstract.setOpaque(true);
				panel3.add(lblAbstract);
				abstractSection.setHorizontalScrollBarPolicy(abstractSection.HORIZONTAL_SCROLLBAR_ALWAYS);

				abstractSection.setPreferredSize(new Dimension(600, 800));
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

				JButton btnAddAuthor = new JButton("+ Add Author");
				btnAddAuthor.setBounds(290, 6, 125, 25);
				panel6.add(btnAddAuthor);
				JPanel panelAuthor = new JPanel();
				panelAuthor.setBounds(0, 0, 480, 800);
				panelAuthor.setLayout(null);

				JTextField txtFirstName = new JTextField();
				txtFirstName.setBounds(90, 65, 300, 30);
				panelAuthor.add(txtFirstName);
				txtFirstName.setColumns(4);

				JLabel lblFirstName = new JLabel("First name");
				lblFirstName.setHorizontalAlignment(SwingConstants.CENTER);
				lblFirstName.setBounds(190, 40, 100, 20);
				panelAuthor.add(lblFirstName);

				JLabel lblMiddleName = new JLabel("Middle name");
				lblMiddleName.setHorizontalAlignment(SwingConstants.CENTER);
				lblMiddleName.setBounds(190, 96, 100, 20);
				panelAuthor.add(lblMiddleName);

				JTextField txtMiddleName = new JTextField();
				txtMiddleName.setColumns(4);
				txtMiddleName.setBounds(90, 117, 300, 30);
				panelAuthor.add(txtMiddleName);

				JLabel lblLastName = new JLabel("Last name");
				lblLastName.setHorizontalAlignment(SwingConstants.CENTER);
				lblLastName.setBounds(190, 148, 100, 20);
				panelAuthor.add(lblLastName);

				JTextField txtLastName = new JTextField();
				txtLastName.setColumns(4);
				txtLastName.setBounds(90, 169, 300, 30);
				panelAuthor.add(txtLastName);

				JLabel lblEmail = new JLabel("Email");
				lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
				lblEmail.setBounds(190, 200, 100, 20);
				panelAuthor.add(lblEmail);

				JTextField txtEmail = new JTextField();
				txtEmail.setColumns(4);
				txtEmail.setBounds(90, 221, 300, 30);
				panelAuthor.add(txtEmail);

				JLabel lblAffiliation = new JLabel("Affiliation");
				lblAffiliation.setHorizontalAlignment(SwingConstants.CENTER);
				lblAffiliation.setBounds(190, 252, 100, 20);
				panelAuthor.add(lblAffiliation);

				JTextField txtAffiliation = new JTextField();
				txtAffiliation.setColumns(4);
				txtAffiliation.setBounds(90, 273, 300, 30);
				panelAuthor.add(txtAffiliation);

				JLabel lblBio = new JLabel("Bio");
				lblBio.setHorizontalAlignment(SwingConstants.CENTER);
				lblBio.setBounds(190, 304, 100, 20);
				panelAuthor.add(lblBio);

				JTextArea txtBio = new JTextArea();
				txtBio.setColumns(4);
				txtBio.setBounds(90, 325, 300, 60);
				panelAuthor.add(txtBio);

				JLabel lblOrcID = new JLabel("OrcID");
				lblOrcID.setHorizontalAlignment(SwingConstants.CENTER);
				lblOrcID.setBounds(190, 386, 100, 20);
				panelAuthor.add(lblOrcID);

				JTextField txtOrcID = new JTextField();
				txtOrcID.setColumns(4);
				txtOrcID.setBounds(90, 407, 300, 30);
				panelAuthor.add(txtOrcID);

				JLabel lblDepartment = new JLabel("Department");
				lblDepartment.setHorizontalAlignment(SwingConstants.CENTER);
				lblDepartment.setBounds(190, 438, 100, 20);
				panelAuthor.add(lblDepartment);

				JTextField txtDepartment = new JTextField();
				txtDepartment.setColumns(4);
				txtDepartment.setBounds(90, 459, 300, 30);
				panelAuthor.add(txtDepartment);

				JLabel lblCountry = new JLabel("Country");
				lblCountry.setHorizontalAlignment(SwingConstants.CENTER);
				lblCountry.setBounds(190, 490, 100, 20);
				panelAuthor.add(lblCountry);

				JTextField txtCountry = new JTextField();
				txtCountry.setColumns(4);
				txtCountry.setBounds(90, 511, 300, 30);
				panelAuthor.add(txtCountry);
				panelAuthor.setVisible(true);
				panelAuthor.setSize(new Dimension(480, 800));
				panelAuthor.setPreferredSize(new Dimension(480, 600));
				panelAuthor.setVisible(true);

				JButton btnEditAuthors = new JButton("Edit Authors");
				btnEditAuthors.setBounds(165, 6, 125, 25);
				panel6.add(btnEditAuthors);

				ArrayList<Author> article_authors = new ArrayList<Author>();
				article_authors = article_author_storage.get(current_article.getId());

				DefaultListModel listModel = new DefaultListModel();
				ArrayList<Long> author_list = new ArrayList<Long>();
				String listData[] = new String[article_authors.size()];
				int j = 0;
				int a = 0;
				int selections = article_authors.size();
				int[] selected = new int[selections];
				for (Author author : article_authors) {
					listModel.addElement(author.getFull_name());
					listData[j] = author.getFull_name();
					author_list.add((long) author.getId());
					System.out.println(a + " - " + j);
					selected[a] = j;
					a = a + 1;

					j = j + 1;
				}
				;

				System.out.println("authors: " + selected.length);
				System.out.println("authors: " + listData.length);
				for (int index : selected) {
					System.out.println("Selected: " + index);
				}
				System.out.println(selected.toString());
				panel15.setBounds(50, 107, 180 * 2, 100 + 25 * article_authors.size());
				panel15.setLayout(null);
				panel15.setAutoscrolls(true);
				panel15.setPreferredSize(new Dimension(320, 100 + 25 * article_authors.size()));
				// Create a new listbox control
				JList listbox = new JList();
				listbox.setModel(listModel);
				listbox.setSelectedIndices(selected);
				listbox.setBounds(10, 10, 300, 50 + 25 * author_list.size());
				listbox.setBackground(Color.white);
				listbox.setVisible(true);
				panel15.add(listbox);
				btnAddAuthor.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						panelAuthor.setVisible(true);
						panelAuthor.setEnabled(true);
						int result = JOptionPane.showConfirmDialog(null, panelAuthor, "Add Author",
								JOptionPane.OK_CANCEL_OPTION);
						if (result == JOptionPane.OK_OPTION) {
							author_id++;
							Author new_author = new Author(author_id, txtFirstName.getText(), txtMiddleName.getText(),
									txtLastName.getText(), txtEmail.getText(), txtAffiliation.getText(),
									txtBio.getText(), txtOrcID.getText(), txtDepartment.getText(),
									txtCountry.getText());
							author_storage.put(author_id, new_author);
							System.out.println("Author id :" + author_id);
							System.out.println(new_author);
							HashMap<Long, Boolean> current_authors = author_primary_storage.get((long) article_id);
							current_authors.put((long) author_id, false);
							author_primary_storage.put((long) article_id, current_authors);

							author_list.add(author_id);
							listModel.addElement(new_author.getFull_name());
							listbox.setSelectedIndex(author_list.size() - 1);
							listbox.repaint();

							Article c_art = article_storage.get((long) article_id);

							ArrayList<Author> author_array = c_art.getAuthors();
							article_storage.put((long) article_id, c_art);
							if (!author_array.contains(new_author)) {
								author_array.add(new_author);
								c_art.add_author(new_author);
							}
							article_author_storage.put((long) article_id, author_array);

							article.dispose();
							edit_article(issue_id, article_id);
						}
					}
				});
				btnEditAuthors.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int result = JOptionPane.showConfirmDialog(null, panel15, "Edit Authors",
								JOptionPane.OK_CANCEL_OPTION);
						if (result == JOptionPane.OK_OPTION) {
							int[] selections = listbox.getSelectedIndices();
							Issue current_issue = issue_storage.get(issue_id);

							ArrayList<Long> removed_authors = new ArrayList<Long>();
							ArrayList<Long> new_authors = new ArrayList<Long>();
							for (int index : selections) {
								new_authors.add(author_list.get(index));
							}
							Set<Long> previous_authors = author_primary_storage.get(article_id).keySet();
							for (long index : previous_authors) {
								if (!new_authors.contains(index)) {
									removed_authors.add(index);
								}

							}

							HashMap<Long, Boolean> primary_storage = author_primary_storage.get(article_id);
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
							ArrayList<Author> new_set_authors = new ArrayList<Author>();
							for (int index : selections) {
								Author new_author = author_storage.get(author_list.get(index));
								current_issue.add_author(article_id, new_author);
								new_set_authors.add(new_author);

							}
							article_author_storage.put(article_id, new_set_authors);
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
				txtCompetingInterests.setColumns(4);
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
				txtFunding.setColumns(4);
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

				if (setting_meta.compareToIgnoreCase("true") == 0) {
					panel.add(btnAddMetadata);
					article.getContentPane().add(btnAddMetadata);
				}
				final HashMap<Long, HashMap<Long, JTextField>> author_fields = new HashMap<Long, HashMap<Long, JTextField>>();
				final HashMap<Long, JTextArea> authors_bio = new HashMap<Long, JTextArea>();

				final HashMap<Long, JButton> primary_buttons = new HashMap<Long, JButton>();
				final HashMap<Long, JLabel> primary_labels = new HashMap<Long, JLabel>();

				int author_x = 16;
				int author_y = 60;
				int separation_horizontal = 205;
				int separation_vertical = 30;
				int label_field_separation = 4;
				int i = 0;
				for (Author author : article_authors) {

					separation_horizontal = 205 * i;
					author_x = author_x + separation_horizontal;

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
						long a_id = author.getId();
						author_primary_btn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {

								HashMap<Long, Boolean> update_primary = author_primary_storage.get(article_id);

								Set<Long> primary_keys = update_primary.keySet();
								for (long pkey : primary_keys) {
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

								Set<Long> label_keys = primary_labels.keySet();
								for (long key_lbl : label_keys) {
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
						long a_id = author.getId();
						author_primary_btn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {

								HashMap<Long, Boolean> update_primary = author_primary_storage.get(article_id);

								Set<Long> primary_keys = update_primary.keySet();
								for (long pkey : primary_keys) {
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

								Set<Long> label_keys = primary_labels.keySet();
								for (long key_lbl : label_keys) {
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
						i++;
						primary_buttons.put(a_id, author_primary_btn);
					}
					HashMap<Long, JTextField> author_components = new HashMap<Long, JTextField>();

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
					author_components.put((long) 1, field);
					author_fields.put(author.getId(), author_components);
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
					author_components.put((long) 2, field);
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
					author_components.put((long) 3, field);
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
					author_components.put((long) 4, field);
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
					author_components.put((long) 5, field);
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
					author_components.put((long) 6, field);
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
					author_components.put((long) 7, field);
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
					author_components.put((long) 8, field);
					author_fields.put(author.getId(), author_components);
					author_y = author_y + separation_vertical;
					System.out.println("fields : " + author_components.keySet().size());
					author_y = 60;
					author_x = 16;

					System.out.println("Components: " + author_components.size());

				}
				panel6.setPreferredSize(new Dimension(210 * article_authors.size(), 500)); // scrollable

				System.out.println(author_fields.keySet());
				/*
				 * JTextArea lblAuthorInfo = new JTextArea(
				 * "First Name: Pete\tFirst Name: Bob 2 \nLast Name: User\tLast Name: User 2 \n "
				 * ); lblAuthorInfo.setEditable(true);
				 * lblAuthorInfo.setBounds(16, 34, 205 * 2, 175)s; // white box
				 * lblAuthorInfo.setOpaque(true); panel6.add(lblAuthorInfo)
				 */

				authorSection.setPreferredSize(new Dimension(220 * article_authors.size(), 200));
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

				JLabel lblIssueId = new JLabel(Long.toString(issue_id));
				lblIssueId.setBounds(160, 48, 94, 30);
				panel.add(lblIssueId);
				lblIssueId.setForeground(Color.BLACK);
				lblIssueId.setFont(new Font("Dialog", Font.BOLD, 14));
				lblIssueId.setText(Long.toString(issue_id));

				final JLabel lblDateAccepted = new JLabel("Date Submitted:");
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
				lblArticleId.setText(Long.toString(article_id));

				JLabel lblPages = new JLabel("Pages:");
				lblPages.setForeground(Color.BLACK);
				lblPages.setFont(new Font("Dialog", Font.BOLD, 14));
				lblPages.setBounds(24, 165, 94, 30);
				panel.add(lblPages);

				final JTextField lblPageNum = new JTextField(current_article.getPages());
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
				Set<Long> section_keys = section_storage.keySet();
				ArrayList<Section> sections = new ArrayList<Section>();
				int selected_section = -1;
				int count = 0;
				for (long key : section_keys) {
					lblSectionId.addItem(section_storage.get(key).getTitle());
					sections.add(section_storage.get(key));
					System.out.println("Count: " + count + " Section: " + current_article.getSection_id());
					if (current_article.getSection_id() == section_storage.get(key).getId()) {
						selected_section = count;
						System.out.println("selected section: " + selected_section);
					}
					count++;
				}
				if (selected_section != -1) {
					lblSectionId.setSelectedIndex(selected_section);
				}
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
				txtSectionTitle.setColumns(4);

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
				JLabel lblDoi = new JLabel("DOI:");
				lblDoi.setForeground(Color.BLACK);
				lblDoi.setFont(new Font("Dialog", Font.BOLD, 14));
				lblDoi.setBounds(24, 268, 94, 30);
				panel.add(lblDoi);

				final JTextField doi = new JTextField(current_article.getDoi());
				doi.setForeground(Color.BLACK);
				doi.setFont(new Font("Dialog", Font.BOLD, 14));
				doi.setBounds(85, 269, 180, 30);
				panel.add(doi);
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
						long entered_sectionID = 0;
						String entered_pages = lblPageNum.getText();

						try {
							entered_sectionID = sections.get(lblSectionId.getSelectedIndex()).getId();

							System.out.println("Section id: " + entered_sectionID);

							lblSectionId.setBackground(new Color(255, 255, 255));
							lblSectionId.setForeground(new Color(0, 0, 0));
							lblPageNum.setBackground(new Color(255, 255, 255));
							lblPageNum.setForeground(new Color(0, 0, 0));
						} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
							validation = false;
							lblSectionId.setBackground(new Color(255, 0, 0));
							lblSectionId.setForeground(new Color(255, 0, 0));

							JOptionPane.showMessageDialog(null, "Select a valid section item from the dropdown list. ");
						}

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
							if (setting_meta.compareToIgnoreCase("true") == 0) {
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
								}
							}
							Article a = article_storage.get(article_id);
							a.setTitle(lblTitleText.getText());
							ArrayList<Author> updated_authors = a.getAuthors();
							for (int i = 0; i < updated_authors.size(); i++) {
								Author author = updated_authors.get(i);
								HashMap<Long, JTextField> a_fields = author_fields.get(updated_authors.get(i).getId());

								author.setFirst_name(a_fields.get((long) 1).getText());
								author.setMiddle_name(a_fields.get((long) 2).getText());
								author.setLast_name(a_fields.get((long) 3).getText());
								author.setEmail(a_fields.get((long) 4).getText());
								author.setAffiliation(a_fields.get((long) 5).getText());
								author.setBio(authors_bio.get(updated_authors.get(i).getId()).getText());
								author.setOrcid(a_fields.get((long) 6).getText());
								author.setDepartment(a_fields.get((long) 7).getText());
								author.setCountry(a_fields.get((long) 8).getText());
								updated_authors.set(i, author);
								author_storage.put(updated_authors.get(i).getId(), author);
							}
							a.setAuthors(updated_authors);
							a.setAbstract_text(lblAbstract.getText());
							a.setSection_id(sections.get(lblSectionId.getSelectedIndex()).getId());
							a.setPages(lblPageNum.getText());
							a.setDate_published(datePicker.getDate());
							a.setDate_accepted(datePickerAccepted.getDate());
							a.setDoi(doi.getText());
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
				if (file_storage.containsKey((long) article_id)) {
					HashMap<Long, ArticleFile> files = file_storage.get((long) article_id);
					Set<Long> keys = files.keySet();
					for (long key : keys) {
						String path = files.get((long) key).getPath();
						label_text = label_text + path.substring(path.lastIndexOf("/") + 1) + "\n";
					}
				}
				lblFile.setText(label_text);
				lblFile.setForeground(Color.WHITE);
				lblFile.setEnabled(false);
				lblFile.setBounds(115, 344, 225, 160);
				lblFile.setToolTipText("");
				JScrollPane fileSection = new JScrollPane(lblFile);
				fileSection.setPreferredSize(new Dimension(300 * 2, 200));
				fileSection.setBounds(20, 344, 225, 160);
				fileSection.add(panel10);
				fileSection.createHorizontalScrollBar();
				panel.add(fileSection);
				ArrayList<File> uploaded_files = new ArrayList<File>();
				JFileChooser chooser = new JFileChooser();
				JButton btnClear = new JButton("Clear");
				btnClear.setEnabled(false);
				btnClear.setBounds(252, 349, 65, 30);
				panel.add(btnClear);
				JButton select = new JButton("Browse");

				select.setBounds(20, 310, 90, 30);
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
						HashMap<Long, ArticleFile> files = null;
						if (file_storage.containsKey((long) article_id)) {
							files = file_storage.get((long) article_id);
						} else {

							files = new HashMap<Long, ArticleFile>();
						}
						for (File f : uploaded_files) {
							file_copy(article_id, f.getPath().toString());

							System.out.println("U-Files: " + uploaded_files.size());
						}
						if (!uploaded_files.isEmpty()) {
							select.setEnabled(false);
						}
						if (file_storage.containsKey((long) article_id)) {
							String label_text = "";
							HashMap<Long, ArticleFile> files_existing = file_storage.get((long) article_id);
							Set<Long> keys = files_existing.keySet();
							for (long k : keys) {
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
						if (file_storage.containsKey((long) article_id)) {
							String label_text = "";
							HashMap<Long, ArticleFile> files_existing = file_storage.get((long) article_id);
							Set<Long> keys = files_existing.keySet();
							for (long k : keys) {
								ArticleFile a_file = files_existing.get(k);
								label_text = label_text
										+ a_file.getPath().substring(a_file.getPath().lastIndexOf("/") + 1) + "\n";
							}
							lblFile.setText(label_text);
							/*
							 * if (file_storage.containsKey((long)article_id)) {
							 * HashMap<Integer, ArticleFile> up_files =
							 * file_storage.get((long)article_id); Set<Integer>
							 * keys = up_files.keySet(); file_id =
							 * initial_file_num; for (int key : keys) { File f =
							 * new File(up_files.get((long)key).getPath());
							 * f.delete(); }
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
				upload.setBounds(150, 310, 140, 30);

				panel.add(upload);
				article.getContentPane().add(btnSave);
				if (article_screens.containsKey(issue_id)) {
					HashMap<Long, JFrame> issue_articles = article_screens.get(issue_id);
					issue_articles.put(article_id, article);
					article_screens.put(issue_id, issue_articles);
				}
			}

		} else {
			login("dashboard");
		}
	}

	public void add_article(final long issue_id) {
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
			HashMap<Long, Boolean> author_primary = new HashMap<Long, Boolean>();
			String setting_meta = list_settings.get("Metadata");
			long current_id = articles_id + 1;
			long initial_file_num = file_id;
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
					if (file_storage.containsKey((long) current_id)) {
						HashMap<Long, ArticleFile> up_files = file_storage.get((long) current_id);
						Set<Long> keys = up_files.keySet();
						file_id = initial_file_num;
						for (long key : keys) {
							File f = new File(up_files.get((long) key).getPath());
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
			txtCompetingInterests.setColumns(4);
			txtCompetingInterests.setBounds(35, 70, 145, 100);

			JScrollPane cmptinterests = new JScrollPane(txtCompetingInterests);
			cmptinterests.setBounds(35, 70, 145, 100);
			panelMetadata.add(cmptinterests);
			// scrollSettings.setViewportView(scrollFrame);

			JLabel lblFunding = new JLabel("Funding");
			lblFunding.setBounds(35, 175, 145, 15);
			panelMetadata.add(lblFunding);

			JTextArea txtFunding = new JTextArea();
			txtFunding.setColumns(4);
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

			if (setting_meta.compareToIgnoreCase("true") == 0) {
				panel.add(btnAddMetadata);
				article.getContentPane().add(btnAddMetadata);
			}
			panel.setPreferredSize(new Dimension(320, settings_height));
			JScrollPane articleSection = new JScrollPane(panel);
			panel.setAutoscrolls(true);
			articleSection.setPreferredSize(new Dimension(320, 200));
			articleSection.setBounds(40, 132, width_small / 2 - 100, height_small - 246);
			// scrollSettings.setViewportView(scrollFrame);
			article.getContentPane().add(articleSection);

			JPanel panel3 = new JPanel();
			panel3.setBackground(SystemColor.window);
			panel3.setBounds(50, 107, 320, 307);
			article.getContentPane().add(panel3);
			panel3.setLayout(null);
			panel3.setAutoscrolls(true);

			panel3.setPreferredSize(new Dimension(640, 960));
			JScrollPane abstractSection = new JScrollPane(panel3);
			panel3.setAutoscrolls(true);

			JLabel lblNewLabel_1 = new JLabel("Abstract");
			lblNewLabel_1.setBounds(16, 6, 61, 16);
			panel3.add(lblNewLabel_1);

			final JTextArea lblAbstract = new JTextArea();
			lblAbstract.setEditable(true);
			lblAbstract.setBounds(16, 28, 400, 800);
			lblAbstract.setOpaque(true);
			panel3.add(lblAbstract);
			abstractSection.setHorizontalScrollBarPolicy(abstractSection.HORIZONTAL_SCROLLBAR_ALWAYS);

			abstractSection.setPreferredSize(new Dimension(600, 800));
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
			ArrayList<Author> article_authors = new ArrayList<Author>();

			DefaultListModel listModel = new DefaultListModel();
			ArrayList<Long> author_list = new ArrayList<Long>();
			int j = 0;
			int a = 0;
			int selections = article_authors.size();
			int[] selected = new int[selections];
			for (Author author : article_authors) {
				listModel.addElement(author.getFull_name());
				author_list.add((long) author.getId());
				System.out.println(a + " - " + j);
				selected[a] = j;
				a = a + 1;

				j = j + 1;
			}

			// Create a new listbox control
			JButton btnAddAuthor = new JButton("+ Add new Author");
			btnAddAuthor.setBounds(156, 12, 180, 25);
			panel6.add(btnAddAuthor);
			JList listbox = new JList();
			listbox.setModel(listModel);
			listbox.setBounds(15, 40, 320, 300);
			listbox.setBackground(Color.white);
			listbox.setVisible(true);
			panel6.add(listbox);
			JPanel panelAuthor = new JPanel();
			panelAuthor.setBounds(0, 0, 480, 800);
			panelAuthor.setLayout(null);

			JTextField txtFirstName = new JTextField();
			txtFirstName.setBounds(90, 65, 300, 30);
			panelAuthor.add(txtFirstName);
			txtFirstName.setColumns(4);

			JLabel lblFirstName = new JLabel("First name");
			lblFirstName.setHorizontalAlignment(SwingConstants.CENTER);
			lblFirstName.setBounds(190, 40, 100, 20);
			panelAuthor.add(lblFirstName);

			JLabel lblMiddleName = new JLabel("Middle name");
			lblMiddleName.setHorizontalAlignment(SwingConstants.CENTER);
			lblMiddleName.setBounds(190, 96, 100, 20);
			panelAuthor.add(lblMiddleName);

			JTextField txtMiddleName = new JTextField();
			txtMiddleName.setColumns(4);
			txtMiddleName.setBounds(90, 117, 300, 30);
			panelAuthor.add(txtMiddleName);

			JLabel lblLastName = new JLabel("Last name");
			lblLastName.setHorizontalAlignment(SwingConstants.CENTER);
			lblLastName.setBounds(190, 148, 100, 20);
			panelAuthor.add(lblLastName);

			JTextField txtLastName = new JTextField();
			txtLastName.setColumns(4);
			txtLastName.setBounds(90, 169, 300, 30);
			panelAuthor.add(txtLastName);

			JLabel lblEmail = new JLabel("Email");
			lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
			lblEmail.setBounds(190, 200, 100, 20);
			panelAuthor.add(lblEmail);

			JTextField txtEmail = new JTextField();
			txtEmail.setColumns(4);
			txtEmail.setBounds(90, 221, 300, 30);
			panelAuthor.add(txtEmail);

			JLabel lblAffiliation = new JLabel("Affiliation");
			lblAffiliation.setHorizontalAlignment(SwingConstants.CENTER);
			lblAffiliation.setBounds(190, 252, 100, 20);
			panelAuthor.add(lblAffiliation);

			JTextField txtAffiliation = new JTextField();
			txtAffiliation.setColumns(4);
			txtAffiliation.setBounds(90, 273, 300, 30);
			panelAuthor.add(txtAffiliation);

			JLabel lblBio = new JLabel("Bio");
			lblBio.setHorizontalAlignment(SwingConstants.CENTER);
			lblBio.setBounds(190, 304, 100, 20);
			panelAuthor.add(lblBio);

			JTextArea txtBio = new JTextArea();
			txtBio.setColumns(4);
			txtBio.setBounds(90, 325, 300, 60);
			panelAuthor.add(txtBio);

			JLabel lblOrcID = new JLabel("OrcID");
			lblOrcID.setHorizontalAlignment(SwingConstants.CENTER);
			lblOrcID.setBounds(190, 386, 100, 20);
			panelAuthor.add(lblOrcID);

			JTextField txtOrcID = new JTextField();
			txtOrcID.setColumns(4);
			txtOrcID.setBounds(90, 407, 300, 30);
			panelAuthor.add(txtOrcID);

			JLabel lblDepartment = new JLabel("Department");
			lblDepartment.setHorizontalAlignment(SwingConstants.CENTER);
			lblDepartment.setBounds(190, 438, 100, 20);
			panelAuthor.add(lblDepartment);

			JTextField txtDepartment = new JTextField();
			txtDepartment.setColumns(4);
			txtDepartment.setBounds(90, 459, 300, 30);
			panelAuthor.add(txtDepartment);

			JLabel lblCountry = new JLabel("Country");
			lblCountry.setHorizontalAlignment(SwingConstants.CENTER);
			lblCountry.setBounds(190, 490, 100, 20);
			panelAuthor.add(lblCountry);

			JTextField txtCountry = new JTextField();
			txtCountry.setColumns(4);
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
						System.out.println("Author id :" + author_id);
						System.out.println(new_author);
						author_primary.put((long) author_id, false);
						author_list.add(author_id);
						listModel.addElement(new_author.getFull_name());
						listbox.setSelectedIndex(author_list.size() - 1);
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

			JLabel lblIssueId = new JLabel(Long.toString(issue_id));
			lblIssueId.setBounds(160, 48, 94, 30);
			panel.add(lblIssueId);
			lblIssueId.setForeground(Color.BLACK);
			lblIssueId.setFont(new Font("Dialog", Font.BOLD, 14));
			lblIssueId.setText(Long.toString(issue_id));

			final JLabel lblDateAccepted = new JLabel("Date Submitted:");
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
			Set<Long> section_keys = section_storage.keySet();
			ArrayList<Section> sections = new ArrayList<Section>();
			for (long key : section_keys) {
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
			txtSectionTitle.setColumns(4);

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
			JLabel lblDoi = new JLabel("DOI:");
			lblDoi.setForeground(Color.BLACK);
			lblDoi.setFont(new Font("Dialog", Font.BOLD, 14));
			lblDoi.setBounds(24, 268, 94, 30);
			panel.add(lblDoi);

			final JTextField doi = new JTextField();
			doi.setForeground(Color.BLACK);
			doi.setFont(new Font("Dialog", Font.BOLD, 14));
			doi.setBounds(85, 269, 180, 30);
			panel.add(doi);
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
					long entered_sectionID = 0;
					String entered_pages = lblPageNum.getText();

					try {
						entered_sectionID = sections.get(lblSectionId.getSelectedIndex()).getId();

						System.out.println("Section id: " + entered_sectionID);

						lblSectionId.setBackground(new Color(255, 255, 255));
						lblSectionId.setForeground(new Color(0, 0, 0));
						lblPageNum.setBackground(new Color(255, 255, 255));
						lblPageNum.setForeground(new Color(0, 0, 0));
					} catch (NumberFormatException e) {
						validation = false;

						lblPageNum.setBackground(new Color(255, 0, 0));
						lblPageNum.setForeground(new Color(255, 255, 255));
						JOptionPane.showMessageDialog(null, "Select a valid section item from the dropdown list. ");
					}

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
						if (setting_meta.compareToIgnoreCase("true") == 0) {
							System.out.println("Metadata id: " + metadata_id);
							metadata_id++;

							System.out.println("Metadata id: " + metadata_id);
							meta_update = new Metadata(metadata_id, current_id, txtCompetingInterests.getText(),
									txtFunding.getText());
						}
						issue_screens.get(issue_id).dispose();
						articles_id++;

						if (setting_meta.compareToIgnoreCase("true") == 0) {
							metadata_storage.put(articles_id, meta_update);
							System.out.println("Metadata added");

							System.out.println(metadata_storage.get(articles_id).getCompeting_interests());
						}
						list_issues.replace(issue_id, articles_id);
						Issue current_issue = issue_storage.get(issue_id);
						Article new_article = new Article(articles_id, lblTitleText.getText(), entered_sectionID,
								entered_pages, lblAbstract.getText(), datePickerAccepted.getDate(),
								datePicker.getDate(), current_issue, datePickerAccepted.getDate(),
								new Journal(1, "up", (float) 2.0, "en_US", 0));
						new_article.setDoi(doi.getText());
						author_primary_storage.put(articles_id, author_primary);
						ArrayList<Author> selected_authors = new ArrayList<Author>();
						int[] selections = listbox.getSelectedIndices();
						HashMap<Long, Boolean> author_primary = new HashMap<Long, Boolean>();
						author_primary_storage.put(articles_id, author_primary);
						System.out.println("Selected authors: " + selections.length);
						for (int index : selections) {
							author_primary.put(author_storage.get(author_list.get(index)).getId(), false);
							selected_authors.add(author_storage.get(author_list.get(index)));
							new_article.add_author(author_storage.get(author_list.get(index)));

							System.out.println("Added: " + author_storage.get(author_list.get(index)).getFull_name());
							System.out.println("selected_authors: " + selected_authors.size());
							System.out.println("new_article: " + new_article.getAuthors().size());

						}
						current_issue.add_article(articles_id, new_article);

						article_storage.put(articles_id, new_article);
						article_author_storage.put(articles_id, selected_authors);

						author_primary_storage.put(articles_id, author_primary);
						article.dispose();
						issue_storage.put(issue_id, current_issue);
						Object[] new_row = { articles_id, issue_id, 1, "title", 1, "abstract", sdf.format(current),
								"View", "Edit", "Delete" };
						HashMap<Long, JFrame> issue_articles = article_screens.get(issue_id);
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
			lblFile.setBounds(115, 344, 225, 160);
			lblFile.setToolTipText("");
			JScrollPane fileSection = new JScrollPane(lblFile);
			fileSection.setPreferredSize(new Dimension(300 * 2, 200));
			fileSection.setBounds(20, 344, 225, 160);
			fileSection.add(panel10);
			fileSection.createHorizontalScrollBar();
			panel.add(fileSection);
			ArrayList<File> uploaded_files = new ArrayList<File>();
			JFileChooser chooser = new JFileChooser();
			JButton select = new JButton("Browse");

			select.setBounds(20, 310, 90, 30);
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
					if (file_storage.containsKey((long) current_id)) {
						String label_text = "";
						HashMap<Long, ArticleFile> files_existing = file_storage.get((long) current_id);
						Set<Long> keys = files_existing.keySet();
						for (long k : keys) {
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
			upload.setBounds(156, 310, 90, 30);
			btnClear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (file_storage.containsKey((long) current_id)) {
						HashMap<Long, ArticleFile> up_files = file_storage.get((long) current_id);
						Set<Long> keys = up_files.keySet();
						file_id = initial_file_num;
						for (long key : keys) {
							File f = new File(up_files.get((long) key).getPath());
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

			btnClear.setBounds(252, 349, 65, 30);
			panel.add(btnClear);
			panel.add(upload);

			article.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					if (file_storage.containsKey((long) current_id) && current_id != articles_id) {
						HashMap<Long, ArticleFile> up_files = file_storage.get((long) current_id);
						Set<Long> keys = up_files.keySet();
						file_id = initial_file_num;

						for (long key : keys) {
							File f = new File(up_files.get((long) key).getPath());
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
					if (file_storage.containsKey((long) current_id) && current_id != articles_id) {
						HashMap<Long, ArticleFile> up_files = file_storage.get((long) current_id);
						Set<Long> keys = up_files.keySet();
						file_id = initial_file_num;
						for (Long key : keys) {
							File f = new File(up_files.get((long) key).getPath());
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
				HashMap<Long, JFrame> issue_articles = article_screens.get(issue_id);
				issue_articles.put(current_id, article);
				article_screens.put(issue_id, issue_articles);
			}

		} else {
			login("dashboard");
		}
	}

	public static void update_article_intersect(Article article, String credentials)
			throws IllegalStateException, IOException {

		boolean status = status_online();
		if (!status) {
			return;
		}
		JSONObject obj = ArticleToJSON(article);
		HttpGet article_exists = new HttpGet(String.format("%s/articles/%s/", base_url, article.getId()));

		article_exists.addHeader("Authorization", "Basic " + credentials);
		article_exists.setHeader("Accept", "application/json");
		article_exists.addHeader("Content-type", "application/json");

		HttpResponse response = null;
		try {
			response = httpClient.execute(article_exists);
		} catch (ClientProtocolException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		boolean article_created = false;
		if (response.getStatusLine().getStatusCode() == 200) {
			article_created = true;
		}
		try {
			InputStream is = response.getEntity().getContent();
			is.close();
		} catch (IOException exc) {
			// TODO Auto-generated catch block
			exc.printStackTrace();
		}
		if (article_created) {
			HttpPut httpPut = new HttpPut(String.format("%s/articles/%s/", base_url, article.getId()));
			httpPut.setEntity(new StringEntity(obj.toJSONString()));
			httpPut.addHeader("Authorization", "Basic " + credentials);
			httpPut.setHeader("Accept", "application/json");
			httpPut.addHeader("Content-type", "application/json");

			response = null;
			try {
				response = httpClient.execute(httpPut);
			} catch (ClientProtocolException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				InputStream is = response.getEntity().getContent();
				is.close();
			} catch (IOException exc) {
				// TODO Auto-generated catch block
				exc.printStackTrace();
			}
		} else {
			HttpPost createArticle = new HttpPost(String.format("%s/articles/", base_url));
			createArticle.setEntity(new StringEntity(obj.toJSONString()));
			createArticle.addHeader("Authorization", "Basic " + credentials);
			createArticle.setHeader("Accept", "application/json");
			createArticle.addHeader("Content-type", "application/json");

			response = null;
			try {
				response = httpClient.execute(createArticle);
			} catch (ClientProtocolException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				InputStream is = response.getEntity().getContent();
				is.close();
			} catch (IOException exc) {
				// TODO Auto-generated catch block
				exc.printStackTrace();
			}
		}
		System.out.println(response.toString());
		HttpGet settingCheck = new HttpGet(
				String.format("%s/get/setting/title/article/%s/?format=json", base_url, article.getId()));
		// settingCheck.setEntity(new StringEntity(obj.toJSONString()));
		settingCheck.addHeader("Authorization", "Basic " + credentials);
		settingCheck.setHeader("Accept", "application/json");
		settingCheck.addHeader("Content-type", "application/json");

		response = null;
		try {
			response = httpClient.execute(settingCheck);
		} catch (ClientProtocolException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		JsonFactory jsonf = new JsonFactory();
		InputStream result = response.getEntity().getContent();
		Long setting_pk = (long) -1;
		org.json.simple.parser.JSONParser jsonParser = new JSONParser();
		boolean exists = true;
		JSONObject setting_json = new JSONObject();
		try {
			JSONObject setting = (JSONObject) jsonParser.parse(IOUtils.toString(result));
			System.out.println(setting.get("count"));
			System.out.println(setting);
			Long count = (Long) setting.get("count");
			if (count == 0) {
				exists = false;
			} else {
				JSONArray results = (JSONArray) setting.get("results");
				System.out.println(results.get(0));
				setting_json = (JSONObject) results.get(0);
				System.out.println(setting_json.get("pk"));
				System.out.println(setting_json.get("setting_name"));
				System.out.println(setting_json.get("setting_value"));
				setting_pk = (long) setting_json.get("pk");
				setting_json.put("setting_value", article.getTitle());
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			InputStream is = response.getEntity().getContent();
			is.close();
		} catch (IOException exc) {
			// TODO Auto-generated catch block
			exc.printStackTrace();
		}
		System.out.println(setting_json.isEmpty());
		System.out.println(exists);
		System.out.println(setting_pk);
		if (setting_json.isEmpty()) {
			setting_json = SettingToJSON("article", article.getId(), "title", article.getTitle(), "string", "en_US");
		}
		System.out.println(setting_json);
		if (!exists) {
			String value = setting_json.toJSONString();
			byte[] b = value.getBytes("windows-1252");
			for (byte bi : b) {
				System.out.print(bi + " ");
			}
			System.out.println();
			String setting_value = new String(b, "UTF-8");

			System.out.println(setting_value.getBytes());
			HttpPost httpPost = new HttpPost(String.format("%s/article-settings/", base_url));
			httpPost.setEntity(new StringEntity(setting_value));
			httpPost.addHeader("Authorization", "Basic " + credentials);
			httpPost.setHeader("Accept", "application/json");
			httpPost.addHeader("Content-type", "application/json");
			try {
				response = httpClient.execute(httpPost);
			} catch (ClientProtocolException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		} else {
			String value = setting_json.toJSONString();
			byte[] b = value.getBytes("windows-1252");
			for (byte bi : b) {
				System.out.print(bi + " ");
			}
			System.out.println();
			String setting_value = new String(b, "UTF-8");

			System.out.println(setting_value.getBytes());
			HttpPut httpPost = new HttpPut(String.format("%s/article-settings/%s/", base_url, setting_json.get("pk")));
			httpPost.setEntity(new StringEntity(setting_value));
			httpPost.addHeader("Authorization", "Basic " + credentials);
			httpPost.setHeader("Accept", "application/json");
			httpPost.addHeader("Content-type", "application/json");
			try {
				response = httpClient.execute(httpPost);
			} catch (ClientProtocolException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		try {
			InputStream is = response.getEntity().getContent();
			is.close();
		} catch (IOException exc) {
			// TODO Auto-generated catch block
			exc.printStackTrace();
		}
		settingCheck = new HttpGet(
				String.format("%s/get/setting/abstract/article/%s/?format=json", base_url, article.getId()));
		// settingCheck.setEntity(new StringEntity(obj.toJSONString()));
		settingCheck.addHeader("Authorization", "Basic " + credentials);
		settingCheck.setHeader("Accept", "application/json");
		settingCheck.addHeader("Content-type", "application/json");

		response = null;
		try {
			response = httpClient.execute(settingCheck);
		} catch (ClientProtocolException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		jsonf = new JsonFactory();
		result = response.getEntity().getContent();
		setting_pk = (long) -1;
		jsonParser = new JSONParser();
		exists = true;
		setting_json = new JSONObject();
		try {
			JSONObject setting = (JSONObject) jsonParser.parse(IOUtils.toString(result));
			System.out.println(setting.get("count"));
			System.out.println(setting);
			Long count = (Long) setting.get("count");
			if (count == 0) {
				exists = false;
			} else {
				JSONArray results = (JSONArray) setting.get("results");
				System.out.println(results.get(0));
				setting_json = (JSONObject) results.get(0);
				System.out.println(setting_json.get("pk"));
				System.out.println(setting_json.get("setting_name"));
				System.out.println(setting_json.get("setting_value"));
				setting_pk = (long) setting_json.get("pk");
				setting_json.put("setting_value", article.getAbstract_text());
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			InputStream is = response.getEntity().getContent();
			is.close();
		} catch (IOException exc) {
			// TODO Auto-generated catch block
			exc.printStackTrace();
		}
		System.out.println(setting_json.isEmpty());
		System.out.println(exists);
		System.out.println(setting_pk);
		if (setting_json.isEmpty()) {
			setting_json = SettingToJSON("article", article.getId(), "abstract",
					"<p class=\"p1\">" + article.getAbstract_text().replace("\r\n", "") + "</p>", "string", "en_US");
		}
		System.out.println(setting_json);
		if (!exists) {
			String value = setting_json.toJSONString();
			byte[] b = value.getBytes("windows-1252");
			for (byte bi : b) {
				System.out.print(bi + " ");
			}
			System.out.println();
			String setting_value = new String(b, "UTF-8");

			System.out.println(setting_value.getBytes());
			HttpPost httpPost = new HttpPost(String.format("%s/article-settings/", base_url));
			httpPost.setEntity(new StringEntity(setting_value));
			httpPost.addHeader("Authorization", "Basic " + credentials);
			httpPost.setHeader("Accept", "application/json");
			httpPost.addHeader("Content-type", "application/json");
			try {
				response = httpClient.execute(httpPost);
			} catch (ClientProtocolException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		} else {
			String value = setting_json.toJSONString();
			byte[] b = value.getBytes("windows-1252");
			for (byte bi : b) {
				System.out.print(bi + " ");
			}
			System.out.println();
			String setting_value = new String(b, "UTF-8");

			System.out.println(setting_value.getBytes());
			HttpPut httpPost = new HttpPut(String.format("%s/article-settings/%s/", base_url, setting_json.get("pk")));
			httpPost.setEntity(new StringEntity(setting_value));
			httpPost.addHeader("Authorization", "Basic " + credentials);
			httpPost.setHeader("Accept", "application/json");
			httpPost.addHeader("Content-type", "application/json");
			try {
				response = httpClient.execute(httpPost);
			} catch (ClientProtocolException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		try {
			InputStream is = response.getEntity().getContent();
			is.close();
		} catch (IOException exc) {
			// TODO Auto-generated catch block
			exc.printStackTrace();
		}
		settingCheck = new HttpGet(
				String.format("%s/get/setting/pub-id::doi/article/%s/?format=json", base_url, article.getId()));
		// settingCheck.setEntity(new StringEntity(obj.toJSONString()));
		settingCheck.addHeader("Authorization", "Basic " + credentials);
		settingCheck.setHeader("Accept", "application/json");
		settingCheck.addHeader("Content-type", "application/json");

		response = null;
		try {
			response = httpClient.execute(settingCheck);
		} catch (ClientProtocolException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		jsonf = new JsonFactory();
		result = response.getEntity().getContent();
		setting_pk = (long) -1;
		jsonParser = new JSONParser();
		exists = true;
		setting_json = new JSONObject();
		try {
			JSONObject setting = (JSONObject) jsonParser.parse(IOUtils.toString(result));
			System.out.println(setting.get("count"));
			System.out.println(setting);
			Long count = (Long) setting.get("count");
			if (count == 0) {
				exists = false;
			} else {
				JSONArray results = (JSONArray) setting.get("results");
				System.out.println(results.get(0));
				setting_json = (JSONObject) results.get(0);
				System.out.println(setting_json.get("pk"));
				System.out.println(setting_json.get("setting_name"));
				System.out.println(setting_json.get("setting_value"));
				setting_pk = (long) setting_json.get("pk");
				setting_json.put("setting_value", article.getDoi());
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			InputStream is = response.getEntity().getContent();
			is.close();
		} catch (IOException exc) {
			// TODO Auto-generated catch block
			exc.printStackTrace();
		}
		System.out.println(setting_json.isEmpty());
		System.out.println(exists);
		System.out.println(setting_pk);
		if (setting_json.isEmpty()) {
			setting_json = SettingToJSON("article", article.getId(), "pub-id::doi",
					article.getDoi(), "string", "en_US");
		}
		System.out.println(setting_json);
		if (!exists) {
			String value = setting_json.toJSONString();
			byte[] b = value.getBytes("windows-1252");
			for (byte bi : b) {
				System.out.print(bi + " ");
			}
			System.out.println();
			String setting_value = new String(b, "UTF-8");

			System.out.println(setting_value.getBytes());
			HttpPost httpPost = new HttpPost(String.format("%s/article-settings/", base_url));
			httpPost.setEntity(new StringEntity(setting_value));
			httpPost.addHeader("Authorization", "Basic " + credentials);
			httpPost.setHeader("Accept", "application/json");
			httpPost.addHeader("Content-type", "application/json");
			try {
				response = httpClient.execute(httpPost);
			} catch (ClientProtocolException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		} else {
			String value = setting_json.toJSONString();
			byte[] b = value.getBytes("windows-1252");
			for (byte bi : b) {
				System.out.print(bi + " ");
			}
			System.out.println();
			String setting_value = new String(b, "UTF-8");

			System.out.println(setting_value.getBytes());
			HttpPut httpPost = new HttpPut(String.format("%s/article-settings/%s/", base_url, setting_json.get("pk")));
			httpPost.setEntity(new StringEntity(setting_value));
			httpPost.addHeader("Authorization", "Basic " + credentials);
			httpPost.setHeader("Accept", "application/json");
			httpPost.addHeader("Content-type", "application/json");
			try {
				response = httpClient.execute(httpPost);
			} catch (ClientProtocolException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		try {
			InputStream is = response.getEntity().getContent();
			is.close();
		} catch (IOException exc) {
			// TODO Auto-generated catch block
			exc.printStackTrace();
		}
		/*
		 * response = null; try { response = httpClient.execute(httpPost); }
		 * catch (ClientProtocolException e2) { // TODO Auto-generated catch
		 * block e2.printStackTrace(); } catch (IOException e2) { // TODO
		 * Auto-generated catch block e2.printStackTrace(); }
		 */
	}

	public static void update_issue_intersect(Issue issue, String credentials)
			throws IllegalStateException, IOException {

		boolean status = status_online();
		if (!status) {
			return;
		}
		JSONObject obj = IssueToJSON(issue);
		HttpGet issue_exists = new HttpGet(String.format("%s/issues/%s/", base_url, issue.getId()));

		issue_exists.addHeader("Authorization", "Basic " + credentials);
		issue_exists.setHeader("Accept", "application/json");
		issue_exists.addHeader("Content-type", "application/json");

		HttpResponse response = null;
		try {
			response = httpClient.execute(issue_exists);
		} catch (ClientProtocolException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		boolean issue_created = false;
		if (response.getStatusLine().getStatusCode() == 200) {
			issue_created = true;
		}
		try {
			InputStream is = response.getEntity().getContent();
			is.close();
		} catch (IOException exc) {
			// TODO Auto-generated catch block
			exc.printStackTrace();
		}
		if (issue_created) {
			HttpPut httpPut = new HttpPut(String.format("%s/issues/%s/", base_url, issue.getId()));
			httpPut.setEntity(new StringEntity(obj.toJSONString()));
			httpPut.addHeader("Authorization", "Basic " + credentials);
			httpPut.setHeader("Accept", "application/json");
			httpPut.addHeader("Content-type", "application/json");

			response = null;
			try {
				response = httpClient.execute(httpPut);
			} catch (ClientProtocolException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				InputStream is = response.getEntity().getContent();
				is.close();
			} catch (IOException exc) {
				// TODO Auto-generated catch block
				exc.printStackTrace();
			}
		} else {
			HttpPost createIssue = new HttpPost(String.format("%s/issues/", base_url));
			createIssue.setEntity(new StringEntity(obj.toJSONString()));
			createIssue.addHeader("Authorization", "Basic " + credentials);
			createIssue.setHeader("Accept", "application/json");
			createIssue.addHeader("Content-type", "application/json");

			response = null;
			try {
				response = httpClient.execute(createIssue);
			} catch (ClientProtocolException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				InputStream is = response.getEntity().getContent();
				is.close();
			} catch (IOException exc) {
				// TODO Auto-generated catch block
				exc.printStackTrace();
			}
		}
		System.out.println(response.toString());
		HttpGet settingCheck = new HttpGet(
				String.format("%s/get/setting/title/issue/%s/?format=json", base_url, issue.getId()));
		// settingCheck.setEntity(new StringEntity(obj.toJSONString()));
		settingCheck.addHeader("Authorization", "Basic " + credentials);
		settingCheck.setHeader("Accept", "application/json");
		settingCheck.addHeader("Content-type", "application/json");

		response = null;
		try {
			response = httpClient.execute(settingCheck);
		} catch (ClientProtocolException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		JsonFactory jsonf = new JsonFactory();
		InputStream result = response.getEntity().getContent();
		Long setting_pk = (long) -1;
		org.json.simple.parser.JSONParser jsonParser = new JSONParser();
		boolean exists = true;
		JSONObject setting_json = new JSONObject();
		try {
			JSONObject setting = (JSONObject) jsonParser.parse(IOUtils.toString(result));
			System.out.println(setting.get("count"));
			System.out.println(setting);
			Long count = (Long) setting.get("count");
			if (count == 0) {
				exists = false;
			} else {
				JSONArray results = (JSONArray) setting.get("results");
				System.out.println(results.get(0));
				setting_json = (JSONObject) results.get(0);
				System.out.println(setting_json.get("pk"));
				System.out.println(setting_json.get("setting_name"));
				System.out.println(setting_json.get("setting_value"));
				setting_pk = (long) setting_json.get("pk");
				setting_json.put("setting_value", issue.getTitle());
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			InputStream is = response.getEntity().getContent();
			is.close();
		} catch (IOException exc) {
			// TODO Auto-generated catch block
			exc.printStackTrace();
		}
		System.out.println(setting_json.isEmpty());
		System.out.println(exists);
		System.out.println(setting_pk);
		if (setting_json.isEmpty()) {
			setting_json = SettingToJSON("issue", issue.getId(), "title", issue.getTitle(), "string", "en_US");
		}
		System.out.println(setting_json);
		if (!exists) {
			String value = setting_json.toJSONString();
			byte[] b = value.getBytes("windows-1252");
			for (byte bi : b) {
				System.out.print(bi + " ");
			}
			System.out.println();
			String setting_value = new String(b, "UTF-8");

			System.out.println(setting_value.getBytes());
			HttpPost httpPost = new HttpPost(String.format("%s/issue-settings/", base_url));
			httpPost.setEntity(new StringEntity(setting_value));
			httpPost.addHeader("Authorization", "Basic " + credentials);
			httpPost.setHeader("Accept", "application/json");
			httpPost.addHeader("Content-type", "application/json");
			try {
				response = httpClient.execute(httpPost);
			} catch (ClientProtocolException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		} else {
			HttpPut httpPost = new HttpPut(
					String.format("%s/update/issue/setting/%s/", base_url, setting_json.get("pk")));
			httpPost.setEntity(new StringEntity(setting_json.toJSONString()));
			httpPost.addHeader("Authorization", "Basic " + credentials);
			httpPost.setHeader("Accept", "application/json");
			httpPost.addHeader("Content-type", "application/json");
			try {
				response = httpClient.execute(httpPost);
			} catch (ClientProtocolException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		try {
			InputStream is = response.getEntity().getContent();
			is.close();
		} catch (IOException exc) {
			// TODO Auto-generated catch block
			exc.printStackTrace();
		}
		/*
		 * response = null; try { response = httpClient.execute(httpPost); }
		 * catch (ClientProtocolException e2) { // TODO Auto-generated catch
		 * block e2.printStackTrace(); } catch (IOException e2) { // TODO
		 * Auto-generated catch block e2.printStackTrace(); }
		 */
	}

	public static void update_articles_intersect(Issue issue, String credentials)
			throws IllegalStateException, IOException {

		boolean status = status_online();
		if (!status) {
			return;
		}
		System.out.println("Getting Articles");

		ArrayList<Article> articles_list = new ArrayList<Article>();
		JSONObject obj = IssueToJSON(issue);
		HttpGet issue_exists = new HttpGet(String.format("%s/issues/%s/", base_url, issue.getId()));

		issue_exists.addHeader("Authorization", "Basic " + credentials);
		issue_exists.setHeader("Accept", "application/json");
		issue_exists.addHeader("Content-type", "application/json");

		HttpResponse response = null;
		try {
			response = httpClient.execute(issue_exists);
		} catch (ClientProtocolException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		boolean issue_created = false;
		if (response.getStatusLine().getStatusCode() == 200) {
			issue_created = true;
		}
		try {
			InputStream is = response.getEntity().getContent();
			is.close();
		} catch (IOException exc) {
			// TODO Auto-generated catch block
			exc.printStackTrace();
		}
		if (!issue_created) {
			update_issue_intersect(issue, credentials);
		}
		HashMap<Long, Article> articles = issue.getArticles_list();
		Set<Long> article_keys = articles.keySet();
		for (Long key : article_keys) {
			Article current_article = articles.get(key);
			update_article_intersect(current_article, credentials);
			HashMap<Long, ArticleFile> files = file_storage.get((long) current_article.getId());
			Set<Long> file_keys = files.keySet();
			HttpGet article_files = new HttpGet(
					String.format("%s/get/files/%s/?format=json", base_url, current_article.getId()));
			// settingCheck.setEntity(new
			// StringEntity(obj.toJSONString()));
			article_files.addHeader("Authorization", "Basic " + credentials);
			article_files.setHeader("Accept", "application/json");
			article_files.addHeader("Content-type", "application/json");

			response = null;
			try {
				response = httpClient.execute(article_files);
			} catch (ClientProtocolException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			JsonFactory jsonf = new JsonFactory();
			InputStream result = response.getEntity().getContent();
			org.json.simple.parser.JSONParser jsonParser = new JSONParser();

			long setting_pk = (long) -1;
			boolean exists = true;
			JSONObject setting_json = new JSONObject();

			JSONObject setting;
			try {
				setting = (JSONObject) jsonParser.parse(IOUtils.toString(result));

				try {
					InputStream is = response.getEntity().getContent();
					is.close();
				} catch (IOException exc) {
					// TODO Auto-generated catch block
					exc.printStackTrace();
				}
				System.out.println(setting);
				if (setting == null) {
					exists = false;
				} else {
					String[] file_ids = null;
					String ids = ((String) setting.get("files"));
					if (ids.contains(",")) {
						file_ids = ((String) setting.get("files")).split(",");
					}
					if (file_ids != null) {
						for (String file_id : file_ids) {
							if (!files.containsKey((long)Long.parseLong(file_id))){
							delete_file((long)Long.parseLong(file_id));
							}
						}
					}
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("FILES TO UPLOAD: " + file_keys.size());
			if (file_keys.size() > 0) {
				for (long f_key : file_keys) {

					System.out.println("FILE TO UPLOAD: " + f_key);
					ArticleFile current_file = files.get((long) f_key);
					file_upload_intersect(current_article.getId(), current_file);
				}
			}
		}
	}

	public static void update_articles_local(Issue issue, String credentials)
			throws IllegalStateException, IOException {

		boolean status = status_online();
		if (!status) {
			return;
		}
		System.out.println("Getting Articles");

		ArrayList<Article> articles_list = new ArrayList<Article>();
		JSONObject obj = IssueToJSON(issue);
		HttpGet issue_exists = new HttpGet(String.format("%s/issues/%s/", base_url, issue.getId()));

		issue_exists.addHeader("Authorization", "Basic " + credentials);
		issue_exists.setHeader("Accept", "application/json");
		issue_exists.addHeader("Content-type", "application/json");

		HttpResponse response = null;
		try {
			response = httpClient.execute(issue_exists);
		} catch (ClientProtocolException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		boolean issue_created = false;
		if (response.getStatusLine().getStatusCode() == 200) {
			issue_created = true;
		}
		try {
			InputStream is = response.getEntity().getContent();
			is.close();
		} catch (IOException exc) {
			// TODO Auto-generated catch block
			exc.printStackTrace();
		}
		if (!issue_created) {
			update_issue_intersect(issue, credentials);
		}

		System.out.println(response.toString());
		HttpGet published_articles = new HttpGet(
				String.format("%s/get/published-articles/%s/?format=json", base_url, issue.getId()));
		// settingCheck.setEntity(new StringEntity(obj.toJSONString()));
		published_articles.addHeader("Authorization", "Basic " + credentials);
		published_articles.setHeader("Accept", "application/json");
		published_articles.addHeader("Content-type", "application/json");

		response = null;
		try {
			response = httpClient.execute(published_articles);
		} catch (ClientProtocolException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		JsonFactory jsonf = new JsonFactory();
		InputStream result = response.getEntity().getContent();
		Long setting_pk = (long) -1;
		org.json.simple.parser.JSONParser jsonParser = new JSONParser();
		boolean exists = true;
		JSONObject setting_json = new JSONObject();

		try {
			JSONObject setting = (JSONObject) jsonParser.parse(IOUtils.toString(result));
			try {
				InputStream is = response.getEntity().getContent();
				is.close();
			} catch (IOException exc) {
				// TODO Auto-generated catch block
				exc.printStackTrace();
			}
			System.out.println(setting);
			if (setting == null) {
				exists = false;
			} else {
				String[] article_ids = null;
				String ids = ((String) setting.get("articles"));
				if (ids.contains(",")) {
					article_ids = ((String) setting.get("articles")).split(",");
				}
				try {
					for (String id : article_ids) {

						System.out.println(id);
						HttpGet single_article = new HttpGet(
								String.format("%s/articles/%s/?format=json", base_url, id));
						// settingCheck.setEntity(new
						// StringEntity(obj.toJSONString()));
						single_article.addHeader("Authorization", "Basic " + credentials);
						single_article.setHeader("Accept", "application/json");
						single_article.addHeader("Content-type", "application/json");

						response = null;
						try {
							response = httpClient.execute(single_article);
						} catch (ClientProtocolException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						result = response.getEntity().getContent();
						jsonParser = new JSONParser();
						exists = true;
						setting_json = new JSONObject();
						try {
							setting = (JSONObject) jsonParser.parse(IOUtils.toString(result));
							Article new_article = JSONToArticle(setting, issue);
							System.out.println(new_article);
							try {
								InputStream is = response.getEntity().getContent();
								is.close();
							} catch (IOException exc) {
								// TODO Auto-generated catch block
								exc.printStackTrace();
							}
							HttpGet article_settings = new HttpGet(String.format(
									"%s/get/setting/abstract/article/%s/?format=json", base_url, new_article.getId()));
							// settingCheck.setEntity(new
							// StringEntity(obj.toJSONString()));
							article_settings.addHeader("Authorization", "Basic " + credentials);
							article_settings.setHeader("Accept", "application/json");
							article_settings.addHeader("Content-type", "application/json");

							response = null;
							try {
								response = httpClient.execute(article_settings);
							} catch (ClientProtocolException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							} catch (IOException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							if (response.getStatusLine().getStatusCode() == 200) {
								result = response.getEntity().getContent();
								jsonParser = new JSONParser();
								exists = true;
								setting_json = new JSONObject();

								try {
									setting = (JSONObject) jsonParser.parse(IOUtils.toString(result));
									JSONArray results = (JSONArray) setting.get("results");
									System.out.println(results.get(0));
									setting_json = (JSONObject) results.get(0);
									System.out.println("ABSTRACT");
									System.out.println(setting_json.get("setting_value"));
									String abs = Jsoup.parse((String) setting_json.get("setting_value")).text();
									String[] words = abs.split(" ");
									String new_abs = "";
									int j = 0;
									for (String word : words) {
										new_abs = new_abs + " " + word;
										if (j % 8 == 0 && j != 0) {
											new_abs = new_abs + "\r\n";
										}
										j++;
									}
									new_article.setAbstract_text(new_abs);
									System.out.println(setting_json.get("setting_value"));
								} catch (Exception e) {
									e.printStackTrace();
								}
								try {
									InputStream is = response.getEntity().getContent();
									is.close();
								} catch (IOException exc) {
									// TODO Auto-generated catch block
									exc.printStackTrace();
								}
							} else {
								new_article.setAbstract_text("None.");

							}
							article_settings = new HttpGet(String.format("%s/get/setting/title/article/%s/?format=json",
									base_url, new_article.getId()));
							// settingCheck.setEntity(new
							// StringEntity(obj.toJSONString()));
							article_settings.addHeader("Authorization", "Basic " + credentials);
							article_settings.setHeader("Accept", "application/json");
							article_settings.addHeader("Content-type", "application/json");

							response = null;
							try {
								response = httpClient.execute(article_settings);
							} catch (ClientProtocolException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							} catch (IOException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							if (response.getStatusLine().getStatusCode() == 200) {
								result = response.getEntity().getContent();
								jsonParser = new JSONParser();
								exists = true;
								setting_json = new JSONObject();

								try {
									setting = (JSONObject) jsonParser.parse(IOUtils.toString(result));
									JSONArray results = (JSONArray) setting.get("results");
									System.out.println(results.get(0));
									setting_json = (JSONObject) results.get(0);
									new_article.setTitle((String) setting_json.get("setting_value"));
									System.out.println(setting_json.get("setting_value"));
								} catch (Exception e) {
									e.printStackTrace();
								}
								try {
									InputStream is = response.getEntity().getContent();
									is.close();
								} catch (IOException exc) {
									// TODO Auto-generated catch block
									exc.printStackTrace();
								}
							} else {
								new_article.setTitle("None.");
							}
							article_settings = new HttpGet(String.format("%s/get/setting/pub-id::doi/article/%s/?format=json",
									base_url, new_article.getId()));
							// settingCheck.setEntity(new
							// StringEntity(obj.toJSONString()));
							article_settings.addHeader("Authorization", "Basic " + credentials);
							article_settings.setHeader("Accept", "application/json");
							article_settings.addHeader("Content-type", "application/json");

							response = null;
							try {
								response = httpClient.execute(article_settings);
							} catch (ClientProtocolException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							} catch (IOException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							if (response.getStatusLine().getStatusCode() == 200) {
								result = response.getEntity().getContent();
								jsonParser = new JSONParser();
								exists = true;
								setting_json = new JSONObject();

								try {
									setting = (JSONObject) jsonParser.parse(IOUtils.toString(result));
									JSONArray results = (JSONArray) setting.get("results");
									System.out.println(results.get(0));
									setting_json = (JSONObject) results.get(0);
									new_article.setDoi((String) setting_json.get("setting_value"));
									System.out.println(setting_json.get("setting_value"));
								} catch (Exception e) {
									e.printStackTrace();
								}
								try {
									InputStream is = response.getEntity().getContent();
									is.close();
								} catch (IOException exc) {
									// TODO Auto-generated catch block
									exc.printStackTrace();
								}
							} else {
								new_article.setTitle("None.");
							}
							HttpGet published_art = new HttpGet(String
									.format("%s/get/article/published/%s/?format=json", base_url, new_article.getId()));
							published_art.addHeader("Authorization", "Basic " + credentials);
							published_art.setHeader("Accept", "application/json");
							published_art.addHeader("Content-type", "application/json");

							response = null;
							try {
								response = httpClient.execute(published_art);
							} catch (ClientProtocolException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							} catch (IOException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							if (response.getStatusLine().getStatusCode() == 200) {
								result = response.getEntity().getContent();
								jsonParser = new JSONParser();
								exists = true;
								setting_json = new JSONObject();

								try {
									setting = (JSONObject) jsonParser.parse(IOUtils.toString(result));

									new_article.setDate_published(
											sdf.parse(((String) setting.get("date_published")).replace("-", "/")));
								} catch (Exception e) {
									e.printStackTrace();
								}
								try {
									InputStream is = response.getEntity().getContent();
									is.close();
								} catch (IOException exc) {
									// TODO Auto-generated catch block
									exc.printStackTrace();
								}
							}

							article_author_storage.put(new_article.getId(), new ArrayList<Author>());
							articles_list.add(new_article);

						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} catch (NullPointerException e_n) {
					return;
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Article a : articles_list) {

			HttpGet article_files = new HttpGet(String.format("%s/get/files/%s/?format=json", base_url, a.getId()));
			// settingCheck.setEntity(new
			// StringEntity(obj.toJSONString()));
			article_files.addHeader("Authorization", "Basic " + credentials);
			article_files.setHeader("Accept", "application/json");
			article_files.addHeader("Content-type", "application/json");

			response = null;
			try {
				response = httpClient.execute(article_files);
			} catch (ClientProtocolException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			jsonf = new JsonFactory();
			result = response.getEntity().getContent();
			jsonParser = new JSONParser();

			setting_pk = (long) -1;
			exists = true;
			setting_json = new JSONObject();

			JSONObject setting;
			try {
				setting = (JSONObject) jsonParser.parse(IOUtils.toString(result));

				try {
					InputStream is = response.getEntity().getContent();
					is.close();
				} catch (IOException exc) {
					// TODO Auto-generated catch block
					exc.printStackTrace();
				}
				System.out.println(setting);
				if (setting == null) {
					exists = false;
				} else {
					String[] file_ids = null;
					String ids = ((String) setting.get("files"));
					if (ids.contains(",")) {
						file_ids = ((String) setting.get("files")).split(",");
					}
					if (file_ids != null) {
						for (String file_id : file_ids) {
							file_download(a.getId(), Long.parseLong(file_id));
						}
					}
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			a.setIssue_fk(issue);
			article_storage.put(a.getId(), a);
			author_primary_storage.put(a.getId(), new HashMap<Long, Boolean>());
			System.out.println(a.getIssue_fk().getId());
			issue.add_article(a.getId(), a);
		}
		System.out.println(articles_list.size() + " - " + article_author_storage.size());
		issue_storage.put(issue.getId(), issue);
	}

	public static Journal get_journal(long journal_id, String credentials) throws IOException {
		Journal journal = null;
		if (journal_storage.containsKey(journal_id)) {
			return journal_storage.get(journal_id);
		} else {
			HttpGet settingCheck = new HttpGet(String.format("%s/journals/%s/?format=json", base_url, journal_id));
			// settingCheck.setEntity(new
			// StringEntity(obj.toJSONString()));
			settingCheck.addHeader("Authorization", "Basic " + credentials);
			settingCheck.setHeader("Accept", "application/json");
			settingCheck.addHeader("Content-type", "application/json");

			HttpResponse response = null;
			try {
				response = httpClient.execute(settingCheck);
			} catch (ClientProtocolException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			JsonFactory jsonf = new JsonFactory();
			InputStream result = response.getEntity().getContent();
			org.json.simple.parser.JSONParser jsonParser = new JSONParser();
			Long setting_pk = (long) -1;
			jsonParser = new JSONParser();
			boolean exists = true;
			JSONObject setting_json = new JSONObject();
			try {
				JSONObject journal_json = (JSONObject) jsonParser.parse(IOUtils.toString(result));
				journal = new Journal((long) journal_json.get("id"), (String) journal_json.get("path"),
						Float.parseFloat(Double.toString((double) journal_json.get("seq"))),
						(String) journal_json.get("primary_locale"), (long) journal_json.get("enabled"));
				journal_storage.put(journal.getId(), journal);
				try {
					InputStream is = response.getEntity().getContent();
					is.close();
				} catch (IOException exc) {
					// TODO Auto-generated catch block
					exc.printStackTrace();
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return journal;
		}
	}

	public static void get_issue_from_remote(String credentials, long issue_id, boolean update_local)
			throws IllegalStateException, IOException {
		boolean status = status_online();

		if (!status) {
			return;
		}
		HttpGet httpGet = new HttpGet(String.format("%s/issues/%s/?format=json", base_url, issue_id));
		httpGet.addHeader("Authorization", "Basic " + credentials);
		httpGet.setHeader("Accept", "application/json");
		httpGet.addHeader("Content-type", "application/json");

		HttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
		} catch (ClientProtocolException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		JsonFactory jsonf = new JsonFactory();
		InputStream result = response.getEntity().getContent();
		org.json.simple.parser.JSONParser jsonParser = new JSONParser();
		boolean exists = true;
		JSONObject issue_json = new JSONObject();
		Issue issue = null;
		try {
			issue_json = (JSONObject) jsonParser.parse(IOUtils.toString(result));

			issue_id = (long) issue_json.get("id");
			if (issue_storage.containsKey(issue_id)) {
				if (update_local) {
					update_issue_local(issue_storage.get(issue_id), credentials);
				}
			} else {
				Issue new_issue = new Issue(issue_id);

				new_issue = JSONToIssue(issue_json, new_issue);
				String journal_link = (String) issue_json.get("journal");
				journal_link = journal_link.substring(0, journal_link.lastIndexOf("/"));
				System.out.println(journal_link);
				journal_link = journal_link.substring(journal_link.lastIndexOf("/") + 1);
				System.out.println(journal_link);
				Journal journal = get_journal(Long.parseLong(journal_link), credentials);
				new_issue.setJournal(journal);

				System.out.println(new_issue);

				System.out.println(issue_id);
				System.out.println(response.toString());
				HttpGet settingCheck = new HttpGet(
						String.format("%s/get/setting/title/issue/%s/?format=json", base_url, new_issue.getId()));
				// settingCheck.setEntity(new
				// StringEntity(obj.toJSONString()));
				settingCheck.addHeader("Authorization", "Basic " + credentials);
				settingCheck.setHeader("Accept", "application/json");
				settingCheck.addHeader("Content-type", "application/json");

				response = null;
				try {
					response = httpClient.execute(settingCheck);
				} catch (ClientProtocolException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				jsonf = new JsonFactory();
				result = response.getEntity().getContent();
				Long setting_pk = (long) -1;
				jsonParser = new JSONParser();
				exists = true;
				JSONObject setting_json = new JSONObject();
				try {
					JSONObject setting = (JSONObject) jsonParser.parse(IOUtils.toString(result));
					System.out.println(setting.get("count"));
					System.out.println(setting);
					Long count = (Long) setting.get("count");
					if (count == 0) {
						exists = false;
					} else {
						JSONArray results = (JSONArray) setting.get("results");
						System.out.println(results.get(0));
						setting_json = (JSONObject) results.get(0);
						new_issue.setTitle((String) setting_json.get("setting_value"));
					//	new_issue.setShow_title((String) setting_json.get("setting_value"));
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				issue_storage.put(issue_id, new_issue);
				issue_screens.put(issue_id, new JFrame());
				article_screens.put(issue_id, new HashMap<Long, JFrame>());

			}
			/*
			 * System.out.println(issue_obj.get("count"));
			 * System.out.println(issue_obj); String detail = (String)
			 * issue_obj.get("detail"); if (detail == null) { exists = false; }
			 * else { JSONArray results = (JSONArray) issue_obj.get("results");
			 * System.out.println(results.get(0)); issue_json = (JSONObject)
			 * results.get(0); System.out.println(issue_json.get("id")); issue =
			 * JSONToIssue(issue_json, issue); }
			 */
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			InputStream is = response.getEntity().getContent();
			is.close();
		} catch (IOException exc) {
			// TODO Auto-generated catch block
			exc.printStackTrace();
		}
	}

	public static ArrayList<Issue> update_get_issues_from_remote(String credentials, boolean update_local)
			throws IllegalStateException, IOException {
		boolean status = status_online();

		ArrayList<Issue> new_issues = new ArrayList<Issue>();
		if (!status) {
			return new_issues;
		}
		HttpGet httpGet = new HttpGet(String.format("%s/issues/?format=json", base_url));
		httpGet.addHeader("Authorization", "Basic " + credentials);
		httpGet.setHeader("Accept", "application/json");
		httpGet.addHeader("Content-type", "application/json");

		HttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
		} catch (ClientProtocolException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		JsonFactory jsonf = new JsonFactory();
		InputStream result = response.getEntity().getContent();
		org.json.simple.parser.JSONParser jsonParser = new JSONParser();
		boolean exists = true;
		JSONObject issue_json = new JSONObject();
		Issue issue = null;
		try {
			JSONObject issue_obj = (JSONObject) jsonParser.parse(IOUtils.toString(result));
			JSONArray array = (JSONArray) issue_obj.get("results");
			System.out.println(array);
			for (int i = 0; i < array.size(); i++) {
				issue_json = (JSONObject) array.get(i);
				long issue_id = (long) issue_json.get("id");
				if (issue_storage.containsKey(issue_id)) {
					if (update_local) {
						update_issue_local(issue_storage.get(issue_id), credentials);
					}
					continue;
				} else {
					Issue new_issue = new Issue(issue_id);

					new_issue = JSONToIssue(issue_json, new_issue);
					String journal_link = (String) issue_json.get("journal");
					journal_link = journal_link.substring(0, journal_link.lastIndexOf("/"));
					System.out.println(journal_link);
					journal_link = journal_link.substring(journal_link.lastIndexOf("/") + 1);
					System.out.println(journal_link);
					Journal journal = get_journal(Long.parseLong(journal_link), credentials);
					new_issue.setJournal(journal);
					System.out.println(new_issue);

					System.out.println(issue_id);
					System.out.println(response.toString());
					HttpGet settingCheck = new HttpGet(
							String.format("%s/get/setting/title/issue/%s/?format=json", base_url, new_issue.getId()));
					// settingCheck.setEntity(new
					// StringEntity(obj.toJSONString()));
					settingCheck.addHeader("Authorization", "Basic " + credentials);
					settingCheck.setHeader("Accept", "application/json");
					settingCheck.addHeader("Content-type", "application/json");

					response = null;
					try {
						response = httpClient.execute(settingCheck);
					} catch (ClientProtocolException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					jsonf = new JsonFactory();
					result = response.getEntity().getContent();
					Long setting_pk = (long) -1;
					jsonParser = new JSONParser();
					exists = true;
					JSONObject setting_json = new JSONObject();
					try {
						JSONObject setting = (JSONObject) jsonParser.parse(IOUtils.toString(result));
						System.out.println(setting.get("count"));
						System.out.println(setting);
						Long count = (Long) setting.get("count");
						if (count == 0) {
							exists = false;
						} else {
							JSONArray results = (JSONArray) setting.get("results");
							System.out.println(results.get(0));
							setting_json = (JSONObject) results.get(0);
							new_issue.setTitle((String) setting_json.get("setting_value"));
						//	new_issue.setShow_title((String) setting_json.get("setting_value"));
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					new_issues.add(new_issue);
					issue_storage.put(issue_id, new_issue);
					issue_screens.put(issue_id, new JFrame());
					article_screens.put(issue_id, new HashMap<Long, JFrame>());

				}
			}
			/*
			 * System.out.println(issue_obj.get("count"));
			 * System.out.println(issue_obj); String detail = (String)
			 * issue_obj.get("detail"); if (detail == null) { exists = false; }
			 * else { JSONArray results = (JSONArray) issue_obj.get("results");
			 * System.out.println(results.get(0)); issue_json = (JSONObject)
			 * results.get(0); System.out.println(issue_json.get("id")); issue =
			 * JSONToIssue(issue_json, issue); }
			 */
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			InputStream is = response.getEntity().getContent();
			is.close();
		} catch (IOException exc) {
			// TODO Auto-generated catch block
			exc.printStackTrace();
		}
		return new_issues;
	}

	public static void get_authors_remote(long issue_id, String credentials, boolean update_local)
			throws IllegalStateException, IOException {
		boolean status = status_online();
		if (!status) {
			return;
		}

		int author_count = 0;
		HttpGet httpGet = new HttpGet(String.format("%s/get/issue/authors/%s/?format=json", base_url, issue_id));
		httpGet.addHeader("Authorization", "Basic " + credentials);
		httpGet.setHeader("Accept", "application/json");
		httpGet.addHeader("Content-type", "application/json");

		HttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
		} catch (ClientProtocolException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		JsonFactory jsonf = new JsonFactory();
		InputStream result = response.getEntity().getContent();
		org.json.simple.parser.JSONParser jsonParser = new JSONParser();
		boolean exists = true;
		JSONObject author_json = new JSONObject();
		try {
			JSONObject issue_obj = (JSONObject) jsonParser.parse(IOUtils.toString(result));
			JSONArray author_ids = (JSONArray) issue_obj.get("authors");
			System.out.println(author_ids);
			try {
				InputStream is = response.getEntity().getContent();
				is.close();
			} catch (IOException exc) {
				// TODO Auto-generated catch block
				exc.printStackTrace();
			}
			for (int i = 0; i < author_ids.size(); i++) {
				long author_current_id = (long) author_ids.get(i);
				httpGet = new HttpGet(String.format("%s/authors/%s/?format=json", base_url, author_current_id));
				httpGet.addHeader("Authorization", "Basic " + credentials);
				httpGet.setHeader("Accept", "application/json");
				httpGet.addHeader("Content-type", "application/json");

				response = null;
				try {
					response = httpClient.execute(httpGet);
				} catch (ClientProtocolException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				result = response.getEntity().getContent();
				jsonParser = new JSONParser();
				exists = true;
				author_json = (JSONObject) jsonParser.parse(IOUtils.toString(result));

				long author_id = (long) author_json.get("id");
				if (author_storage.containsKey(author_id)) {
					if (update_local) {
						// update_issue_local(issue_storage.get(issue_id),
						// credentials);
					}
					continue;
				} else {
					Author new_author = new Author(author_id);

					new_author = JSONToAuthor(author_json, new_author);

					System.out.println(author_json);
					HttpGet settingCheck = new HttpGet(String.format("%s/get/setting/biography/author/%s/?format=json",
							base_url, new_author.getId()));
					// settingCheck.setEntity(new
					// StringEntity(obj.toJSONString()));
					settingCheck.addHeader("Authorization", "Basic " + credentials);
					settingCheck.setHeader("Accept", "application/json");
					settingCheck.addHeader("Content-type", "application/json");

					response = null;
					try {
						response = httpClient.execute(settingCheck);
					} catch (ClientProtocolException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					jsonf = new JsonFactory();
					result = response.getEntity().getContent();
					Long setting_pk = (long) -1;
					jsonParser = new JSONParser();
					exists = true;
					JSONObject setting_json = new JSONObject();
					try {
						JSONObject setting = (JSONObject) jsonParser.parse(IOUtils.toString(result));

						Long count = (Long) setting.get("count");
						if (count == 0) {
							exists = false;
						} else {
							JSONArray results = (JSONArray) setting.get("results");

							setting_json = (JSONObject) results.get(0);
							new_author.setBio((String) setting_json.get("setting_value"));
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					if (article_storage.containsKey(new_author.getArticle_id())) {
						if (!article_author_storage.containsKey(new_author.getArticle_id())) {
							ArrayList<Author> new_authors = new ArrayList<Author>();
							new_authors.add(new_author);
							article_author_storage.put(new_author.getArticle_id(), new_authors);
						} else {
							ArrayList<Author> existing_authors = article_author_storage.get(new_author.getArticle_id());
							existing_authors.add(new_author);
							article_author_storage.put(new_author.getArticle_id(), existing_authors);
						}
						Article this_article = article_storage.get(new_author.getArticle_id());
						issue_id = this_article.getIssue_fk().getId();
						Issue this_issue = issue_storage.get(issue_id);
						
						this_issue.add_author(this_article.getId(), new_author);
						this_article.add_author(new_author);
						article_storage.put(this_article.getId(), this_article);
						this_issue.add_article(this_article.getId(), this_article);
						issue_storage.put(issue_id, this_issue);
						System.out.println(new_author);
						author_storage.put(new_author.getId(), new_author);
						HashMap<Long, Boolean> primary = author_primary_storage.get(this_article.getId());
						primary.put(new_author.getId(), false);
						author_primary_storage.put(this_article.getId(), primary);
					}
				}
			}
			/*
			 * System.out.println(issue_obj.get("count"));
			 * System.out.println(issue_obj); String detail = (String)
			 * issue_obj.get("detail"); if (detail == null) { exists = false; }
			 * else { JSONArray results = (JSONArray) issue_obj.get("results");
			 * System.out.println(results.get(0)); issue_json = (JSONObject)
			 * results.get(0); System.out.println(issue_json.get("id")); issue =
			 * JSONToIssue(issue_json, issue); }
			 */
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			InputStream is = response.getEntity().getContent();
			is.close();
		} catch (IOException exc) {
			// TODO Auto-generated catch block
			exc.printStackTrace();
		}

		System.out.println("Authors: " + author_storage.size());
		System.out.println("Authors: " + author_count);
	}

	public static void sync_authors_intersect(long issue_id, String credentials, boolean update_local)
			throws IllegalStateException, IOException {
		boolean status = status_online();
		if (!status) {
			return;
		}
		Issue issue = issue_storage.get(issue_id);
		ArrayList<Author> issue_authors = issue.getAuthors();
		int author_count = 0;
		for (Author author : issue_authors) {

			JSONObject obj = AuthorToJSON(author);
			HttpGet httpGet = new HttpGet(String.format("%s/authors/%s/?format=json", base_url, author.getId()));
			httpGet.addHeader("Authorization", "Basic " + credentials);
			httpGet.setHeader("Accept", "application/json");
			httpGet.addHeader("Content-type", "application/json");

			HttpResponse response = null;
			try {
				response = httpClient.execute(httpGet);
			} catch (ClientProtocolException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			boolean author_created = false;
			if (response.getStatusLine().getStatusCode() == 200) {
				author_created = true;
			}
			JsonFactory jsonf = new JsonFactory();
			InputStream result = response.getEntity().getContent();
			try {
				InputStream is = response.getEntity().getContent();
				is.close();
			} catch (IOException exc) {
				// TODO Auto-generated catch block
				exc.printStackTrace();
			}
			org.json.simple.parser.JSONParser jsonParser = new JSONParser();
			if (author_created) {
				HttpPut httpPut = new HttpPut(String.format("%s/authors/%s/", base_url, author.getId()));
				httpPut.setEntity(new StringEntity(obj.toJSONString()));
				httpPut.addHeader("Authorization", "Basic " + credentials);
				httpPut.setHeader("Accept", "application/json");
				httpPut.addHeader("Content-type", "application/json");

				response = null;
				try {
					response = httpClient.execute(httpPut);
				} catch (ClientProtocolException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					InputStream is = response.getEntity().getContent();
					is.close();
				} catch (IOException exc) {
					// TODO Auto-generated catch block
					exc.printStackTrace();
				}
			} else {
				System.out.println(obj.toJSONString());
				HttpPost createAuthor = new HttpPost(String.format("%s/authors/", base_url));
				createAuthor.setEntity(new StringEntity(obj.toJSONString()));
				createAuthor.addHeader("Authorization", "Basic " + credentials);
				createAuthor.setHeader("Accept", "application/json");
				createAuthor.addHeader("Content-type", "application/json");

				response = null;
				try {
					response = httpClient.execute(createAuthor);
				} catch (ClientProtocolException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					InputStream is = response.getEntity().getContent();
					is.close();
				} catch (IOException exc) {
					// TODO Auto-generated catch block
					exc.printStackTrace();
				}
			}
			HttpGet settingCheck = new HttpGet(
					String.format("%s/get/setting/biography/author/%s/?format=json", base_url, author.getId()));
			// settingCheck.setEntity(new StringEntity(obj.toJSONString()));
			settingCheck.addHeader("Authorization", "Basic " + credentials);
			settingCheck.setHeader("Accept", "application/json");
			settingCheck.addHeader("Content-type", "application/json");

			response = null;
			try {
				response = httpClient.execute(settingCheck);
			} catch (ClientProtocolException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			jsonf = new JsonFactory();
			result = response.getEntity().getContent();
			boolean setting_created = false;
			if (response.getStatusLine().getStatusCode() == 200) {
				author_created = true;
			}
			Long setting_pk = (long) -1;
			jsonParser = new JSONParser();
			boolean exists = true;
			JSONObject setting_json = new JSONObject();
			try {
				JSONObject setting = (JSONObject) jsonParser.parse(IOUtils.toString(result));
				System.out.println(setting.get("count"));
				System.out.println(setting);
				Long count = (Long) setting.get("count");
				if (count == 0) {
					exists = false;
				} else {
					JSONArray results = (JSONArray) setting.get("results");
					System.out.println(results.get(0));
					setting_json = (JSONObject) results.get(0);
					System.out.println(setting_json.get("pk"));
					System.out.println(setting_json.get("setting_name"));
					System.out.println(setting_json.get("setting_value"));
					setting_pk = (long) setting_json.get("pk");
					setting_json.put("setting_value", author.getBio());
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				InputStream is = response.getEntity().getContent();
				is.close();
			} catch (IOException exc) {
				// TODO Auto-generated catch block
				exc.printStackTrace();
			}
			System.out.println(setting_json.isEmpty());
			System.out.println(exists);
			System.out.println(setting_pk);
			if (setting_json.isEmpty()) {
				setting_json = SettingToJSON("author", author.getId(), "biography", author.getBio(), "string", "en_US");
			}

			System.out.println(setting_json);
			if (!exists) {
				HttpPost httpPost = new HttpPost(String.format("%s/author-settings/", base_url));
				httpPost.setEntity(new StringEntity(setting_json.toJSONString()));
				httpPost.addHeader("Authorization", "Basic " + credentials);
				httpPost.setHeader("Accept", "application/json");
				httpPost.addHeader("Content-type", "application/json");
				try {
					response = httpClient.execute(httpPost);
				} catch (ClientProtocolException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			} else {

				String value = setting_json.toJSONString();
				byte[] b = value.getBytes("windows-1252");
				for (byte bi : b) {
					System.out.print(bi + " ");
				}
				System.out.println();
				String setting_value = new String(b, "UTF-8");

				System.out.println(setting_value.getBytes());
				HttpPut httpPost = new HttpPut(String.format("%s/author-settings/%s/", base_url, setting_pk));
				httpPost.setEntity(new StringEntity(setting_value));
				httpPost.addHeader("Authorization", "Basic " + credentials);
				httpPost.setHeader("Accept", "application/json");
				httpPost.addHeader("Content-type", "application/json");
				try {
					response = httpClient.execute(httpPost);
				} catch (ClientProtocolException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
			if (response.getStatusLine().getStatusCode() == 400) {
				System.out.println("ERROR!! " + setting_json.toJSONString());

				System.out.println("ERROR!! Author " + author.getId());

				System.out.println("ERROR!!" + IOUtils.toString(response.getEntity().getContent()));
			}
			try {
				InputStream is = response.getEntity().getContent();
				is.close();
			} catch (IOException exc) {
				// TODO Auto-generated catch block
				exc.printStackTrace();
			}
		}
	}

	public static Issue update_issue_local(Issue issue, String credentials) throws IllegalStateException, IOException {
		boolean status = status_online();
		if (!status) {
			return issue;
		}
		HttpGet httpGet = new HttpGet(String.format("%s/issues/%s/?format=json", base_url, issue.getId()));
		httpGet.addHeader("Authorization", "Basic " + credentials);
		httpGet.setHeader("Accept", "application/json");
		httpGet.addHeader("Content-type", "application/json");

		HttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
		} catch (ClientProtocolException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		JsonFactory jsonf = new JsonFactory();
		InputStream result = response.getEntity().getContent();
		org.json.simple.parser.JSONParser jsonParser = new JSONParser();
		boolean exists = true;
		JSONObject issue_json = new JSONObject();
		try {
			JSONObject issue_obj = (JSONObject) jsonParser.parse(IOUtils.toString(result));
			System.out.println(issue_obj.get("count"));
			System.out.println(issue_obj);
			String detail = (String) issue_obj.get("detail");
			if (detail == null) {
				exists = false;
			} else {
				JSONArray results = (JSONArray) issue_obj.get("results");
				System.out.println(results.get(0));
				issue_json = (JSONObject) results.get(0);
				System.out.println(issue_json.get("id"));
				issue = JSONToIssue(issue_json, issue);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			InputStream is = response.getEntity().getContent();
			is.close();
		} catch (IOException exc) {
			// TODO Auto-generated catch block
			exc.printStackTrace();
		}
		System.out.println(response.toString());
		HttpGet settingCheck = new HttpGet(
				String.format("%s/get/setting/title/issue/%s/?format=json", base_url, issue.getId()));
		// settingCheck.setEntity(new StringEntity(obj.toJSONString()));
		settingCheck.addHeader("Authorization", "Basic " + credentials);
		settingCheck.setHeader("Accept", "application/json");
		settingCheck.addHeader("Content-type", "application/json");

		response = null;
		try {
			response = httpClient.execute(settingCheck);
		} catch (ClientProtocolException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		jsonf = new JsonFactory();
		result = response.getEntity().getContent();
		Long setting_pk = (long) -1;
		jsonParser = new JSONParser();
		exists = true;
		JSONObject setting_json = new JSONObject();
		try {
			JSONObject setting = (JSONObject) jsonParser.parse(IOUtils.toString(result));
			System.out.println(setting.get("count"));
			System.out.println(setting);
			Long count = (Long) setting.get("count");
			if (count == 0) {
				exists = false;
			} else {
				JSONArray results = (JSONArray) setting.get("results");
				System.out.println(results.get(0));
				setting_json = (JSONObject) results.get(0);
				issue.setTitle((String) setting_json.get("setting_value"));
			//	issue.setShow_title((String) setting_json.get("setting_value"));
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return issue;
	}

	public static JSONObject SettingToJSON(String model, long id, String name, String value, String type, String locale)
			throws UnsupportedEncodingException {
		JSONObject obj = new JSONObject();
		obj.put(model, String.format("%s/%s/%s/", base_url, model + "s", id));
		obj.put("locale", locale);
		obj.put("setting_name", name);
		obj.put("setting_value", value);
		obj.put("setting_type", type);
		return obj;
	}

	public static Issue JSONToIssue(JSONObject obj, Issue issue) {
		issue.setVolume((int) (long) obj.get("volume"));
		issue.setNumber(Integer.parseInt((String) obj.get("number")));
		issue.setYear((int) (long) obj.get("year"));
		issue.setPublished((int) (long) obj.get("published"));
		issue.setShow_volume((int) (long) obj.get("show_volume"));
		issue.setShow_number((int) (long) obj.get("show_number"));
		issue.setShow_year((int) (long) obj.get("show_year"));
		issue.setShow_title((int) (long) obj.get("show_title"));
		issue.setCurrent((int) (long) obj.get("current"));
		issue.setPublished((int) (long) obj.get("published"));
		issue.setAccess_status((int) (long) obj.get("access_status"));
		String date_p = (String) obj.get("date_published");

		if (date_p != null && date_p.compareTo("") != 0) {

			try {
				issue.setDate_published((Date) sdf.parse(date_p.substring(0, 10).replace('-', '/')));
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return issue;
	}

	public static JSONObject IssueToJSON(Issue issue) {
		JSONObject obj = new JSONObject();
		obj.put("id", issue.getId());
		obj.put("journal", String.format("%s/journals/%s/", base_url, issue.getJournal().getId()));
		obj.put("volume", issue.getVolume());
		obj.put("number", issue.getNumber());
		obj.put("year", issue.getYear());
		obj.put("published", issue.getPublished());
		obj.put("show_volume", issue.getShow_volume());
		obj.put("show_number", issue.getShow_number());
		obj.put("show_year", issue.getShow_year());
		obj.put("show_title",  issue.getShow_title());
		obj.put("current", issue.getCurrent());
		obj.put("published", issue.getPublished());
		obj.put("access_status", issue.getAccess_status());
		return obj;
	}

	public static JSONObject ArticleToJSON(Article article) {
		JSONObject obj = new JSONObject();
		obj.put("id", article.getId());
		obj.put("journal", String.format("%s/journals/%s/", base_url,
				article.getJournal() == null ? 1 : article.getJournal().getId()));
		obj.put("section_id", article.getSection_id());
		obj.put("user", String.format("%s/users/%s/", base_url, article.getUser_id()));
		SimpleDateFormat upload_sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formated_date = upload_sdf
				.format(article.getDate_submitted() == null ? new Date() : article.getDate_submitted());
		obj.put("date_submitted", formated_date + "T00:00:00Z");
		obj.put("pages", article.getPages());
		obj.put("locale", article.getLocale());
		obj.put("language", article.getLanguage());
		obj.put("status", article.getStatus());
		obj.put("submission_progress", article.getSubmission_progress());
		obj.put("current_round", article.getCurrent_round());
		obj.put("fast_tracked", article.getFast_tracked());
		obj.put("hide_author", article.getHide_author());
		obj.put("comments_status", article.getComments_status());
		return obj;
	}

	public static Article JSONToArticle(JSONObject obj, Issue issue) {
		String date_p = (String) obj.get("date_submitted");
		String user_id = (String) obj.get("user");
		user_id = user_id.substring(0, user_id.lastIndexOf("/"));
		user_id = user_id.substring(user_id.lastIndexOf("/") + 1);
		System.out.println(user_id);

		Article new_article = new Article((long) obj.get("id"), (long) obj.get("section_id"), (String) obj.get("pages"),
				Long.parseLong(user_id), (String) obj.get("locale"), (String) obj.get("language"),
				(int) (long) obj.get("status"), (int) (long) obj.get("submission_progress"),
				(int) (long) obj.get("current_round"), (int) (long) obj.get("fast_tracked"),
				(int) (long) obj.get("hide_author"), (int) (long) obj.get("comments_status"), issue);
		if (date_p != null && date_p.compareTo("") != 0) {

			try {
				new_article.setDate_submitted((Date) sdf.parse(date_p.substring(0, 10).replace('-', '/')));
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new_article;
	}

	public static Author JSONToAuthor(JSONObject obj, Author author) {

		long id = (long) obj.get("id");
		String article_url = (String) obj.get("article");

		article_url = article_url.substring(0, article_url.lastIndexOf("/"));

		article_url = article_url.substring(article_url.lastIndexOf("/") + 1);

		long article_id = Long.parseLong(article_url);

		author.setArticle_id(article_id);

		String first_name = (String) obj.get("first_name");

		String middle_name = (String) obj.get("middle_name");
		String last_name = (String) obj.get("last_name");
		String email = (String) obj.get("email");
		String country = (String) obj.get("country");
		author.setFirst_name(first_name);
		author.setMiddle_name(middle_name);
		author.setLast_name(last_name);
		author.setEmail(email);
		author.setCountry(country);

		return author;
	}

	public static JSONObject AuthorToJSON(Author author) {
		JSONObject obj = new JSONObject();
		obj.put("id", author.getId());
		obj.put("article", String.format("%s/articles/%s/", base_url, author.getArticle_id()));
		obj.put("first_name", author.getFirst_name());
		obj.put("middle_name", author.getMiddle_name());
		obj.put("last_name", author.getLast_name());
		obj.put("email", author.getEmail());
		obj.put("country", author.getCountry());
		obj.put("seq", author.getSeq());
		obj.put("url", author.getUrl());
		obj.put("primary_contact", author.getPrimary_contact());
		obj.put("user_group", String.format("%s/groups/%s/", base_url, author.getUser_group()));

		return obj;
	}

	/**
	 * @throws IOException
	 * @throws java.text.ParseException
	 * @wbp.parser.entryPoint
	 */
	@SuppressWarnings("deprecation")
	public Main() throws IOException, java.text.ParseException {
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
		HttpParams params = new BasicHttpParams();

		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params, HTTP.CRLF);
		HttpProtocolParams.setUseExpectContinue(params, true);

		HttpConnectionParams.setStaleCheckingEnabled(params, false);
		HttpConnectionParams.setConnectionTimeout(params, SOCKET_OPERATION_TIMEOUT);
		HttpConnectionParams.setSoTimeout(params, SOCKET_OPERATION_TIMEOUT);
		HttpConnectionParams.setSocketBufferSize(params, 8192);

		SchemeRegistry schReg = new SchemeRegistry();
		schReg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		schReg.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
		ClientConnectionManager conMgr = new ThreadSafeClientConnManager(params, schReg);

		DefaultHttpClient httpClient = new DefaultHttpClient(conMgr, params);
		ClientConnectionManager mgr = httpClient.getConnectionManager();

		httpClient.getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),
				new UsernamePasswordCredentials("ioannis", "root"));
		System.out.println("Loading dashboard");
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
		txtCompetingInterests.setColumns(4);
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
		txtFunding.setColumns(4);
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
		 * txtMiddleName.setColumns(4); txtMiddleName.setBounds(90, 117, 300,
		 * 30); panelSection.add(txtMiddleName);
		 * 
		 * JLabel lblLastName = new JLabel("Last name");
		 * lblLastName.setHorizontalAlignment(SwingConstants.CENTER);
		 * lblLastName.setBounds(190, 148, 100, 20);
		 * panelSection.add(lblLastName);
		 * 
		 * JTextField txtLastName = new JTextField(); txtLastName.setColumns(4);
		 * txtLastName.setBounds(90, 169, 300, 30);
		 * panelSection.add(txtLastName);
		 * 
		 * JLabel lblEmail = new JLabel("Email");
		 * lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		 * lblEmail.setBounds(190, 200, 100, 20); panelSection.add(lblEmail);
		 * 
		 * JTextField txtEmail = new JTextField(); txtEmail.setColumns(4);
		 * txtEmail.setBounds(90, 221, 300, 30); panelSection.add(txtEmail);
		 * 
		 * JLabel lblAffiliation = new JLabel("Affiliation");
		 * lblAffiliation.setHorizontalAlignment(SwingConstants.CENTER);
		 * lblAffiliation.setBounds(190, 252, 100, 20);
		 * panelSection.add(lblAffiliation);
		 * 
		 * JTextField txtAffiliation = new JTextField();
		 * txtAffiliation.setColumns(4); txtAffiliation.setBounds(90, 273, 300,
		 * 30); panelSection.add(txtAffiliation);
		 * 
		 * JLabel lblBio = new JLabel("Bio");
		 * lblBio.setHorizontalAlignment(SwingConstants.CENTER);
		 * lblBio.setBounds(190, 304, 100, 20); panelSection.add(lblBio);
		 * 
		 * JTextArea txtBio = new JTextArea(); txtBio.setColumns(4);
		 * txtBio.setBounds(90, 325, 300, 60); panelSection.add(txtBio);
		 * 
		 * JLabel lblOrcID = new JLabel("OrcID");
		 * lblOrcID.setHorizontalAlignment(SwingConstants.CENTER);
		 * lblOrcID.setBounds(190, 386, 100, 20); panelSection.add(lblOrcID);
		 * 
		 * JTextField txtOrcID = new JTextField(); txtOrcID.setColumns(4);
		 * txtOrcID.setBounds(90, 407, 300, 30); panelSection.add(txtOrcID);
		 * 
		 * JLabel lblDepartment = new JLabel("Department");
		 * lblDepartment.setHorizontalAlignment(SwingConstants.CENTER);
		 * lblDepartment.setBounds(190, 438, 100, 20);
		 * panelSection.add(lblDepartment);
		 * 
		 * JTextField txtDepartment = new JTextField();
		 * txtDepartment.setColumns(4); txtDepartment.setBounds(90, 459, 300,
		 * 30); panelSection.add(txtDepartment);
		 * 
		 * JLabel lblCountry = new JLabel("Country");
		 * lblCountry.setHorizontalAlignment(SwingConstants.CENTER);
		 * lblCountry.setBounds(190, 490, 100, 20);
		 * panelSection.add(lblCountry);
		 * 
		 * JTextField txtCountry = new JTextField(); txtCountry.setColumns(4);
		 * txtCountry.setBounds(90, 511, 300, 30); panelSection.add(txtCountry);
		 * panelSection.setBounds(0, 0, 480, 150); panelSection.setSize(new
		 * Dimension(480,150)); panelSection.setPreferredSize(new
		 * Dimension(480,150)); panelSection.setVisible(true);
		 */

		// JOptionPane.showMessageDialog(api,panelAuthor,"Information",JOptionPane.INFORMATION_MESSAGE);

	}

	public static void file_copy(long art_id, String source) {
		try {
			String filename = source.substring(source.lastIndexOf("/") + 1);
			System.out.print(String.format("src/files/%d/%s", art_id, filename));
			File dir = new File(String.format("src/files/%d/", art_id));
			dir.mkdirs();
			file_id++;
			HashMap<Long, ArticleFile> article_files = null;
			if (!file_storage.containsKey((long) art_id)) {
				article_files = new HashMap<Long, ArticleFile>();
			} else {
				article_files = file_storage.get((long) art_id);
			}
			article_files.put(file_id,
					new ArticleFile(file_id, art_id, String.format("src/files/%d/%s", art_id, filename)));

			file_storage.put((long) art_id, article_files);
			Files.copy(Paths.get(source), Paths.get(String.format("src/files/%d/%s", art_id, filename)),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static int decodeHash(String encodedpassword) {
		int restore_hash = Integer.parseInt(StringUtils.newStringUtf8(Base64.decodeBase64(encodedpassword)));
		return (restore_hash - 64);
	}

	public static String encodeHash(String userpass) {
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(Integer.toString((userpass.hashCode()) + 64).getBytes());

	}

	public String decodeSetting(String value) {
		return StringUtils.newStringUtf8(Base64.decodeBase64(value));
	}

	public static void file_upload_intersect(long article_id, String path) throws IllegalStateException, IOException {
		boolean status = status_online();

		if (!status) {
			return;
		}
		File f = new File(path);
		System.out.println("File Length = " + f.length());

		FileInputStream input = new FileInputStream(f);
		HttpPost fileUpload = new HttpPost(String.format("%s/upload/file/%s/", base_url, article_id));

		fileUpload.addHeader("Authorization", "Basic " + encoding);

		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

		/* example for adding an image part */
		FileBody fileBody = new FileBody(f); // image should be a String
		builder.addPart("file", fileBody);

		fileUpload.setEntity(builder.build());

		// : attachment; filename=upload.jpg.

		HttpResponse response = null;
		try {
			response = httpClient.execute(fileUpload);
		} catch (ClientProtocolException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		JsonFactory jsonf = new JsonFactory();
		System.out.println(response.getStatusLine().getStatusCode());
		if (response.getStatusLine().getStatusCode() != 204) {
			InputStream result = response.getEntity().getContent();
			System.out.println(IOUtils.toString(result));
		}
	}

	public static void file_upload_intersect(long article_id, String path, long specific_id)
			throws IllegalStateException, IOException {
		boolean status = status_online();

		if (!status) {
			return;
		}
		File f = new File(path);
		System.out.println("File Length = " + f.length());

		FileInputStream input = new FileInputStream(f);
		HttpPost fileUpload = new HttpPost(
				String.format("%s/upload/specific/file/%d/%d/", base_url, article_id, specific_id));

		fileUpload.addHeader("Authorization", "Basic " + encoding);

		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

		/* example for adding an image part */
		FileBody fileBody = new FileBody(f); // image should be a String
		builder.addPart("file", fileBody);

		fileUpload.setEntity(builder.build());

		// : attachment; filename=upload.jpg.

		HttpResponse response = null;
		try {
			response = httpClient.execute(fileUpload);
		} catch (ClientProtocolException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		JsonFactory jsonf = new JsonFactory();
		System.out.println(response.getStatusLine().getStatusCode());
		if (response.getStatusLine().getStatusCode() != 204) {
			InputStream result = response.getEntity().getContent();
			System.out.println(IOUtils.toString(result));
		}
	}

	public static void file_upload_intersect(long article_id, ArticleFile file)
			throws IllegalStateException, IOException {
		boolean status = status_online();

		if (!status) {
			return;
		}
		File f = new File(file.getPath());
		System.out.println("File Length = " + f.length());

		FileInputStream input = new FileInputStream(f);
		HttpPost fileUpload = new HttpPost(
				String.format("%s/upload/specific/file/%d/%d/", base_url, article_id, file.getId()));

		fileUpload.addHeader("Authorization", "Basic " + encoding);

		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

		/* example for adding an image part */
		FileBody fileBody = new FileBody(f); // image should be a String
		builder.addPart("file", fileBody);

		fileUpload.setEntity(builder.build());

		// : attachment; filename=upload.jpg.

		HttpResponse response = null;
		try {
			response = httpClient.execute(fileUpload);
		} catch (ClientProtocolException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		JsonFactory jsonf = new JsonFactory();
		System.out.println(response.getStatusLine().getStatusCode());
		if (response.getStatusLine().getStatusCode() != 204) {
			InputStream result = response.getEntity().getContent();
			System.out.println(IOUtils.toString(result));
		}
	}

	public static void delete_file(long file_id) throws IOException {
		boolean status = status_online();

		if (!status) {
			return;
		}
		HttpDelete fileUpload = new HttpDelete(String.format("%s/delete/file/%s/", base_url, file_id));

		fileUpload.addHeader("Authorization", "Basic " + encoding);

		// : attachment; filename=upload.jpg.

		HttpResponse response = null;
		try {
			response = httpClient.execute(fileUpload);
		} catch (ClientProtocolException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		JsonFactory jsonf = new JsonFactory();
		System.out.println(response.getStatusLine().getStatusCode());
		if (response.getStatusLine().getStatusCode() != 204) {
			InputStream result = response.getEntity().getContent();
			System.out.println(IOUtils.toString(result));
		}
	}

	public static void file_download(long article_id, long file_id) throws IllegalStateException, IOException {
		boolean status = status_online();

		if (!status) {
			return;
		}
		String filename = "";
		HttpGet fileDetails = new HttpGet(String.format("%s/files/%s/", base_url, file_id));
		File dir = new File(String.format("src/files/%d/", article_id));
		dir.mkdirs();
		fileDetails.addHeader("Authorization", "Basic " + encoding);
		HttpResponse response = null;
		try {
			response = httpClient.execute(fileDetails);
		} catch (ClientProtocolException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		InputStream result = response.getEntity().getContent();
		JsonFactory jsonf = new JsonFactory();
		org.json.simple.parser.JSONParser jsonParser = new JSONParser();
		boolean exists = true;
		JSONObject author_json = new JSONObject();
		JSONObject issue_obj;
		try {
			issue_obj = (JSONObject) jsonParser.parse(IOUtils.toString(result));

			filename = (String) issue_obj.get("original_filename");
			System.out.println(filename);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HttpGet fileDownload = new HttpGet(String.format("%s/download/file/%s/%s/", base_url, article_id, file_id));

		fileDownload.addHeader("Authorization", "Basic " + encoding);
		response = null;
		try {
			response = httpClient.execute(fileDownload);
		} catch (ClientProtocolException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		InputStream is = response.getEntity().getContent();
		String filePath = String.format("src/files/%s/%s/", article_id, filename);
		FileOutputStream fos = new FileOutputStream(new File(filePath));
		int inByte;
		while ((inByte = is.read()) != -1)
			fos.write(inByte);
		is.close();
		fos.close();
		HashMap<Long, ArticleFile> article_files = null;
		if (!file_storage.containsKey((long) article_id)) {
			article_files = new HashMap<Long, ArticleFile>();
		} else {
			article_files = file_storage.get((long) article_id);
			if (article_files == null) {
				article_files = new HashMap<Long, ArticleFile>();
			}
		}
		article_files.put(file_id,
				new ArticleFile(file_id, article_id, String.format("src/files/%d/%s", article_id, filename)));

		file_storage.put((long) article_id, article_files);
		System.out.println("Downloaded: " + article_files.size());

		// System.out.println(file_storage.get((long)125).get((long)5));
	}

	public static void main(String[] args) throws ParseException, java.text.ParseException, IOException {
		BASE64Encoder encoder = new BASE64Encoder();
		encoding = encoder.encode("ioannis:root".getBytes());
		String encodedpassword = encodeHash("ioannis:root");
		System.out.println(decodeHash(encodedpassword));
		System.out.println("ioannis:root".hashCode());
		database_setup();
		populate_variables();

		// get_issue_from_remote(encoding, (long) 5, false);

		// update_articles_local(issue_storage.get((long) 5), encoding);
		System.out.println();
		// file copy to use for file upload
		// file_copy(1,"src/lib/db_xxs.png");
		// get_authors_remote(5, encoding, false);
		// sync_authors_intersect(5, encoding, false);
		// System.out.println("Latest author id: " + author_id);
		// ();
		new Main();
		// file_upload_intersect((long)125,"/home/ioannis/code/toru-app/java_ojs/miglayout-src.zip",25);
		// file_download(125,16);
		// System.out.println(file_storage.get((long)125).get((long)5));
	}
}