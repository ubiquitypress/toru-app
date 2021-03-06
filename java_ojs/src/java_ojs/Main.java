package java_ojs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
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
import java.net.ConnectException;
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
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
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
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.DefaultTableModel;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.codehaus.jackson.JsonFactory;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.sort.RowFilters;
import org.json.HTTP;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.w3c.dom.Document;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import jdk.internal.org.xml.sax.SAXException;
import models.Article;
import models.ArticleFile;
import models.Author;
import models.Issue;
import models.Journal;
import models.Metadata;
import models.Section;
import sun.misc.BASE64Encoder;

@SuppressWarnings({ "deprecation", "unused" })
public class Main {
	static JFrame login, api, issues, settings, section_screen, unpublished_articles_screen;
	private JTextField access_key, username;
	private JXTable issues_table;
	private static int delay = 5000; // milliseconds
	private Executor progress_executor = Executors.newSingleThreadExecutor();
	// private Executor progress_executord = Executors.newFixedThreadPool(5);
	private Executor connection_executor = Executors.newSingleThreadExecutor();
	private JPasswordField passwordField;
	private static ConcurrentHashMap<String, String> list_settings;
	private static ConcurrentHashMap<Long, Long> list_issues;
	private static ConcurrentHashMap<Long, JFrame> issue_screens;
	private static ConcurrentHashMap<Long, Issue> issue_storage;
	private static boolean new_issues_process_done = false;
	private static ConcurrentHashMap<Long, Boolean> issue_countdown_storage;
	private static ConcurrentHashMap<String, String> app_settings;
	private static ConcurrentHashMap<Long, Metadata> metadata_storage;
	private static ConcurrentHashMap<Long, Section> section_storage;
	private static ConcurrentHashMap<Long, Journal> journal_storage;
	private static ConcurrentHashMap<Long, Author> author_storage;
	private static ConcurrentHashMap<Long, Article> article_storage;

	private static ConcurrentHashMap<Long, Article> unpublished_article_storage;
	private static ConcurrentHashMap<Long, ArrayList<Author>> article_author_storage;
	private static String journal_url = "";
	private static String user_url = "";
	private static String directory = "";
	private static ArrayList<Issue> new_issues = new ArrayList<Issue>();
	private static ConcurrentHashMap<Long, ConcurrentHashMap<Long, Boolean>> author_primary_storage;
	private static ConcurrentHashMap<Long, ConcurrentHashMap<Long, ArticleFile>> file_storage;
	private static ConcurrentHashMap<Long, ConcurrentHashMap<Long, JFrame>> article_screens;
	private static ConcurrentHashMap<Long, JFrame> section_screens;
	private static ArrayList<String> setting_keys = new ArrayList<String>();
	private static Connection c = null;
	private static Statement stmt = null;
	private static String api_insert_or_replace_statement = "INSERT OR REPLACE INTO API(journal_id, intersect_user_id, user_id, key,credentials) VALUES (?,?,?,?,?)";
	private static String journal_insert_or_replace_statement = "INSERT OR REPLACE INTO JOURNAL(id,path,seq,primary_locale,enabled,title) VALUES (?,?,?,?,?,?)";
	private static String issue_insert_or_replace_statement = "INSERT OR REPLACE INTO ISSUE(id,title,volume,number,year,show_title,show_volume,show_number,show_year,date_published,date_accepted, published, current, access_status,sync,deleted) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static String section_insert_or_replace_statement = "INSERT OR REPLACE INTO SECTION(id,title,seq, editor_restricted, meta_indexed, meta_reviewed, abstracts_not_required, hide_title, hide_author, hide_about, disable_comments, abstract_word_count,sync,deleted) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static String author_insert_or_replace_statement = "INSERT OR REPLACE INTO AUTHOR(id,first_name,middle_name,last_name,email,affiliation,bio,orcid,department,country,twitter,deleted) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
	private static String article_insert_or_replace_statement = "INSERT OR REPLACE INTO ARTICLE(id,title,section_id,pages,abstract,date_published,date_accepted,date_submitted,locale,language,status,submission_progress,current_round,fast_tracked,hide_author,comments_status,user_id,doi,published_pk,sync,deleted,unpublished_pk) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static String unpublished_article_insert_or_replace_statement = "INSERT OR REPLACE INTO UNPUBLISHED_ARTICLE(id,title,section_id,pages,abstract,date_accepted,date_submitted,locale,language,status,submission_progress,current_round,fast_tracked,hide_author,comments_status,user_id,doi,sync,deleted) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	private static String article_author_insert_or_replace_statement = "INSERT OR REPLACE INTO ARTICLE_AUTHOR(article_id,author_id,primary_author) VALUES (?,?,?)";
	private static String issue_journal_insert_or_replace_statement = "INSERT OR REPLACE INTO ISSUE_JOURNAL(id,journal_id,issue_id) VALUES (?,?,?)";
	private static String issue_article_insert_or_replace_statement = "INSERT OR REPLACE INTO ISSUE_ARTICLE(article_id,issue_id) VALUES (?,?)";
	private static String file_insert_or_replace_statement = "INSERT OR REPLACE INTO FILE(id,article_id,path) VALUES (?,?,?)";
	private static String metadata_insert_or_replace_statement = "INSERT OR REPLACE INTO METADATA(id,article_id,competing_interests,funding) VALUES (?,?,?,?)";
	private static DefaultHttpClient httpClient = new DefaultHttpClient();
	int width = 800;
	private int height = 600;
	private static Boolean logged_in = false;
	private static Boolean has_app_settings = false;
	private static long i_id = 0;
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	private static long journal_id = 0;
	private static long articles_id = 0;

	private static long published_articles_id = 0;
	private static long file_id = 0;

	private int dialogResult = -1;
	private static long author_id = 0;
	private static long section_db_id = 0;
	private static long metadata_id = 0;
	private static final int SOCKET_OPERATION_TIMEOUT = 10 * 1000;
	private static String base_url = "http://intersect.ubiquity.press";

	CookieStore cookieStore = new BasicCookieStore();
	HttpContext httpContext = new BasicHttpContext();
	static String encoding = "";

	/*
	 * Initial setup test
	 */

	@SuppressWarnings("unchecked")
	public static void database_save() {
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
			stmt.executeUpdate("DELETE FROM UNPUBLISHED_ARTICLE");
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
			prep.setString(5, app_settings.get("credentials"));
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
				json_file.toJSONString();
				FileWriter new_jsn = new FileWriter(String.format("%s/required_files/%s", directory, "settings.json"));
				new_jsn.write(out.toString());
				new_jsn.flush();
				new_jsn.close();
			} catch (IOException e) {

				e.printStackTrace();
			}

			Set<Long> section_keys = section_storage.keySet();
			for (long key : section_keys) {
				Section save_section = section_storage.get(key);
				PreparedStatement section_prep = c.prepareStatement(section_insert_or_replace_statement);
				section_prep.setInt(1, (int) (long) save_section.getId());
				section_prep.setString(2, save_section.getTitle());
				section_prep.setDouble(3, save_section.getSeq());
				section_prep.setInt(4, save_section.getEditor_restricted());
				section_prep.setInt(5, save_section.getMeta_indexed());

				section_prep.setInt(6, save_section.getMeta_reviewed());
				section_prep.setInt(7, save_section.getAbstracts_not_required());
				section_prep.setInt(8, save_section.getHide_title());
				section_prep.setInt(9, save_section.getHide_author());
				section_prep.setInt(10, save_section.getHide_about());
				section_prep.setInt(11, save_section.getDisable_comments());

				section_prep.setInt(12, (int) (long) save_section.getAbstract_word_count());

				section_prep.setBoolean(13, save_section.shouldBeSynced());
				section_prep.setBoolean(14, save_section.isDeleted());

				section_prep.executeUpdate();
			}

			Set<Long> author_keys = author_storage.keySet();
			for (long key : author_keys) {
				Author save_author = author_storage.get(key);
				// System.out.println("Author: " + key);
				PreparedStatement author_prep = c.prepareStatement(author_insert_or_replace_statement);
				author_prep.setInt(1, (int) (long) save_author.getId());
				author_prep.setString(2, save_author.getFirst_name());
				author_prep.setString(3, save_author.getMiddle_name());
				author_prep.setString(4, save_author.getLast_name());
				author_prep.setString(5, save_author.getEmail());
				author_prep.setString(6, save_author.getAffiliation() == null ? "" : save_author.getAffiliation());
				author_prep.setString(7,
						save_author.getBio() == null ? "" : Jsoup.parse((String) save_author.getBio()).text());
				author_prep.setString(8, save_author.getOrcid() == null ? "" : save_author.getOrcid());
				author_prep.setString(9, save_author.getDepartment() == null ? "" : save_author.getDepartment());
				author_prep.setString(10, save_author.getCountry() == null ? "" : save_author.getCountry());
				author_prep.setString(11, save_author.getTwitter() == null ? "" : save_author.getTwitter());
				author_prep.setBoolean(12, save_author.isDeleted());

				author_prep.executeUpdate();

			}
			Set<Long> file_art_keys = file_storage.keySet();

			for (long key : file_art_keys) {
				ConcurrentHashMap<Long, ArticleFile> files = file_storage.get(key);
				if (files != null) {
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
				journal_prep.setString(6, current_journal.getTitle());
				journal_prep.executeUpdate();
			}

			Set<Long> unpublished_article_keys = unpublished_article_storage.keySet();
			for (long art_key : unpublished_article_keys) {
				Article save_article = unpublished_article_storage.get(art_key);
				PreparedStatement article_prep = c.prepareStatement(unpublished_article_insert_or_replace_statement);
				article_prep.setInt(1, (int) (long) save_article.getId());
				article_prep.setString(2, save_article.getTitle() == null ? "" : save_article.getTitle());
				article_prep.setInt(3, (int) (long) save_article.getSection_id());
				article_prep.setString(4, save_article.getPages());
				article_prep.setString(5, save_article.getAbstract_text() == null ? ""
						: Jsoup.parse((String) save_article.getAbstract_text()).text());

				article_prep.setString(6,
						save_article.getDate_accepted() == null ? "/" : sdf.format(save_article.getDate_accepted()));
				article_prep.setString(7,
						save_article.getDate_submitted() == null ? "/" : sdf.format(save_article.getDate_submitted()));
				article_prep.setString(8, save_article.getLocale());
				article_prep.setString(9, save_article.getLanguage());
				article_prep.setInt(10, save_article.getStatus());
				article_prep.setInt(11, save_article.getSubmission_progress());
				article_prep.setInt(12, save_article.getCurrent_round());
				article_prep.setInt(13, save_article.getFast_tracked());
				article_prep.setInt(14, save_article.getHide_author());
				article_prep.setInt(15, save_article.getComments_status());
				article_prep.setInt(16, (int) (long) save_article.getUser_id());
				article_prep.setString(17, save_article.getDoi());
				article_prep.setBoolean(18, save_article.shouldBeSynced());
				article_prep.setBoolean(19, save_article.isDeleted());
				article_prep.executeUpdate();

				// System.out.println(
				// "ISSUE ARTICLE: ISSUE: " + save_issue.getId() + "
				// ARTICLE: " + save_article.getId());
				ArrayList<Author> authors = save_article.getAuthors();
				for (int b = 0; b < authors.size(); b++) {
					Author author = authors.get(b);
					PreparedStatement article_author_prep = c
							.prepareStatement(article_author_insert_or_replace_statement);

					article_author_prep.setInt(1, (int) (long) save_article.getId());
					article_author_prep.setInt(2, (int) (long) author.getId());
					article_author_prep.setBoolean(3,
							author_primary_storage.get(save_article.getId()).get(author.getId()));

					// System.out.println("Author: " + author.getId() + "
					// Primary: "
					// +
					// author_primary_storage.get(save_article.getId()).get(author.getId()));
					article_author_prep.executeUpdate();

				}
			}
			int journal_issue_count = 0;
			Set<Long> issue_keys = issue_storage.keySet();
			for (long key : issue_keys) {
				Issue save_issue = issue_storage.get(key);
				ConcurrentHashMap<Long, Article> articles = save_issue.getArticles_list();
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

				issue_prep.setString(10,
						save_issue.getDate_published() == null ? "/" : sdf.format(save_issue.getDate_published()));

				issue_prep.setString(11,
						save_issue.getDate_accepted() == null ? "/" : sdf.format(save_issue.getDate_accepted()));
				issue_prep.setInt(12, save_issue.getPublished());
				issue_prep.setInt(13, save_issue.getAccess_status());
				issue_prep.setInt(14, save_issue.getCurrent());
				issue_prep.setBoolean(15, save_issue.shouldBeSynced());
				issue_prep.setBoolean(16, save_issue.isDeleted());

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
					article_prep.setString(2,
							save_article.getTitle() == null || save_article.getTitle().isEmpty() == true ? "Missing"
									: save_article.getTitle());
					article_prep.setInt(3, (int) (long) save_article.getSection_id());
					article_prep.setString(4, save_article.getPages());
					article_prep.setString(5, save_article.getAbstract_text() == null ? ""
							: Jsoup.parse((String) save_article.getAbstract_text()).text());
					article_prep.setString(6, save_article.getDate_published() == null ? "/"
							: sdf.format(save_article.getDate_published()));

					article_prep.setString(7, save_article.getDate_accepted() == null ? "/"
							: sdf.format(save_article.getDate_accepted()));
					article_prep.setString(8, save_article.getDate_submitted() == null ? "/"
							: sdf.format(save_article.getDate_submitted()));
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
					article_prep.setLong(19, save_article.getPublished_pk());
					article_prep.setBoolean(20, save_article.shouldBeSynced());
					article_prep.setBoolean(21, save_article.isDeleted());
					article_prep.setLong(22, save_article.getUnpublished_pk());
					article_prep.executeUpdate();
					PreparedStatement issue_article_prep = c
							.prepareStatement(issue_article_insert_or_replace_statement);
					issue_article_prep.setInt(1, (int) (long) save_article.getId());
					issue_article_prep.setInt(2, (int) (long) save_issue.getId());
					issue_article_prep.executeUpdate();
					// System.out.println(
					// "ISSUE ARTICLE: ISSUE: " + save_issue.getId() + "
					// ARTICLE: " + save_article.getId());
					ArrayList<Author> authors = save_article.getAuthors();
					for (int b = 0; b < authors.size(); b++) {
						Author author = authors.get(b);
						PreparedStatement article_author_prep = c
								.prepareStatement(article_author_insert_or_replace_statement);

						article_author_prep.setInt(1, (int) (long) save_article.getId());
						article_author_prep.setInt(2, (int) (long) author.getId());
						article_author_prep.setBoolean(3,
								author_primary_storage.get(save_article.getId()).get(author.getId()));

						// System.out.println("Author: " + author.getId() + "
						// Primary: "
						// +
						// author_primary_storage.get(save_article.getId()).get(author.getId()));
						article_author_prep.executeUpdate();
						j = j + 1;
					}
					i = i + 1;
				}

				c.commit();
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
			JOptionPane.showMessageDialog(null, String.format("DATABASE SAVE error: %s", e.getMessage()));
			e.printStackTrace();
		}

		System.out.println("Done.");
		JOptionPane.showMessageDialog(null, "Successfully updated local database", "Save to Database",
				JOptionPane.INFORMATION_MESSAGE);

	}

	public static void app_settings_exist() throws SQLException {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		c = DriverManager.getConnection("jdbc:sqlite:local_datatabse.db");
		stmt = c.createStatement();
		app_settings = new ConcurrentHashMap<String, String>();
		ResultSet rs = c.createStatement().executeQuery("SELECT * FROM API ;");
		boolean has = false;
		while (rs.next()) {
			app_settings.put("user_id", Long.toString((long) rs.getFloat("user_id")));
			app_settings.put("intersect_user_id", Long.toString((long) rs.getFloat("intersect_user_id")));
			app_settings.put("journal_id", Long.toString((long) rs.getFloat("journal_id")));
			String credentials = rs.getString("credentials");
			app_settings.put("key", rs.getString("key"));
			encoding = credentials;
			app_settings.put("credentials", credentials);
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

				e.printStackTrace();
			} catch (IllegalStateException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
			if (!profile_exists) {
				app_settings.put("user_id", "");
				app_settings.put("intersect_user_id", "");
				app_settings.put("journal_id", "");
				app_settings.put("key", "");
			}

		}
	}

	public static void populate_variables() throws ParseException, java.text.ParseException {
		System.out.println("Retrieving data from local database");
		try {
			list_settings = new ConcurrentHashMap<String, String>();
			list_issues = new ConcurrentHashMap<Long, Long>();
			issue_storage = new ConcurrentHashMap<Long, Issue>();
			issue_countdown_storage = new ConcurrentHashMap<Long, Boolean>();
			issue_screens = new ConcurrentHashMap<Long, JFrame>();
			file_storage = new ConcurrentHashMap<Long, ConcurrentHashMap<Long, ArticleFile>>();
			article_screens = new ConcurrentHashMap<Long, ConcurrentHashMap<Long, JFrame>>();
			section_screens = new ConcurrentHashMap<Long, JFrame>();
			author_storage = new ConcurrentHashMap<Long, Author>();
			section_storage = new ConcurrentHashMap<Long, Section>();
			author_primary_storage = new ConcurrentHashMap<Long, ConcurrentHashMap<Long, Boolean>>();
			metadata_storage = new ConcurrentHashMap<Long, Metadata>();
			journal_storage = new ConcurrentHashMap<Long, Journal>();
			article_author_storage = new ConcurrentHashMap<Long, ArrayList<Author>>();
			article_storage = new ConcurrentHashMap<Long, Article>();
			unpublished_article_storage = new ConcurrentHashMap<Long, Article>();
			// Journal test_journal = new Journal(1, "up", (float) 2.0, "en_US",
			// 0);
			// journal_storage.put((long)1, test_journal);
			try {
				app_settings_exist();
			} catch (SQLException e2) {

				e2.printStackTrace();
			}

			try {

				JSONParser parser = new JSONParser();

				Object obj = null;
				try {
					obj = null;
					try {
						obj = parser.parse(
								new FileReader(String.format("%s/required_files/%s", directory, "settings.json")));
					} catch (FileNotFoundException e1) {

						e1.printStackTrace();
					} catch (IOException e1) {

						e1.printStackTrace();
					}
					JSONObject array = (JSONObject) obj;

					@SuppressWarnings({ "unchecked", "rawtypes" })
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

				System.out.println("Loading Journal data....");
				ResultSet rs = c.createStatement().executeQuery("SELECT * FROM JOURNAL ORDER BY id ASC;");
				while (rs.next()) {
					long id = rs.getInt("id");
					String path = rs.getString("path");
					float seq = rs.getFloat("seq");
					String primary_locale = rs.getString("primary_locale");
					int enabled = rs.getInt("enabled");
					String title = rs.getString("title");

					Journal journal = null;
					journal = new Journal(id, path, seq, primary_locale, enabled);
					journal.setTitle(title);
					// JOptionPane.showMessageDialog(null, "Deleted");

					journal_storage.put(id, journal);

					System.out.println(journal_storage);
					journal_id = id;
				}

				System.out.println(journal_storage);
				rs.close();
				System.out.println("Loading Issue data....");
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

					Boolean sync = rs.getBoolean("sync");
					Boolean deleted = rs.getBoolean("deleted");
					Issue issue = null;
					issue = new Issue(id, title, volume, number, year, show_title, show_volume, show_number, show_year,
							date_accepted.compareTo("/") == 0 ? null : sdf.parse(date_accepted),
							date.compareTo("/") == 0 ? null : sdf.parse(date), published, current, access_status,
							journal_storage.get(Long.parseLong(app_settings.get("journal_id"))));

					// JOptionPane.showMessageDialog(null, "Deleted");
					issue.setSync(sync == null ? false : sync);
					issue.setDeleted(deleted == null ? false : deleted);
					list_issues.put(id, (long) 1);
					issue_screens.put(id, new JFrame());
					article_screens.put(id, new ConcurrentHashMap<Long, JFrame>());
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
					new_section.setSeq(sect_s.getDouble("seq"));
					new_section.setEditor_restricted(sect_s.getInt("editor_restricted"));
					new_section.setMeta_indexed(sect_s.getInt("meta_indexed"));
					new_section.setMeta_reviewed(sect_s.getInt("meta_reviewed"));
					new_section.setAbstracts_not_required(sect_s.getInt("abstracts_not_required"));
					new_section.setHide_title(sect_s.getInt("hide_title"));
					new_section.setHide_author(sect_s.getInt("hide_author"));
					new_section.setHide_about(sect_s.getInt("hide_about"));
					new_section.setDisable_comments(sect_s.getInt("disable_comments"));
					new_section.setAbstract_word_count(sect_s.getInt("abstract_word_count"));
					new_section.setSync(sect_s.getBoolean("sync"));
					new_section.setDeleted(sect_s.getBoolean("deleted"));

					section_storage.put(id, new_section);
					section_screens.put(id, new JFrame());
					section_db_id = id;
				}
				sect_s.close();

				System.out.println("Loading Author data....");
				ResultSet authors_s = c.createStatement().executeQuery("SELECT * FROM AUTHOR ORDER BY id ASC;");
				authors_s.getMetaData();
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
					Boolean deleted = authors_s.getBoolean("deleted");

					Author author = null;
					author = new Author(id, first_name, middle_name, last_name, email, affiliation, bio, orcid,
							department, country);
					author.setDeleted(deleted == null ? false : deleted);
					author_id = id;
					author_storage.put(id, author);
					System.out.println(author_storage.size());
				}
				authors_s.close();
				System.out.println("Loading Article data....");
				ResultSet art_s = c.createStatement().executeQuery("SELECT * FROM ARTICLE ORDER BY id ASC;");
				ResultSetMetaData rsmd = art_s.getMetaData();
				System.out.println(rsmd.getColumnName(2));

				while (art_s.next()) {
					long id = art_s.getInt("id");
					String title = art_s.getString("title");
					int section_id = art_s.getInt("section_id");
					author_primary_storage.put(id, new ConcurrentHashMap<Long, Boolean>());

					ConcurrentHashMap<Long, ArticleFile> files = new ConcurrentHashMap<Long, ArticleFile>();
					file_storage.put((long) id, files);
					String pages = art_s.getString(rsmd.getColumnName(4));
					String abstract_text = art_s.getString(rsmd.getColumnName(5));

					String date = art_s.getString(rsmd.getColumnName(6));
					String date_accepted = art_s.getString("date_accepted");
					String date_submitted = art_s.getString("date_submitted");
					String date_published = art_s.getString("date_published");
					String doi = art_s.getString("doi");
					Integer published_pk = art_s.getInt("published_pk");
					Integer unpublished_pk = art_s.getInt("unpublished_pk");
					Boolean sync = art_s.getBoolean("sync");
					Boolean deleted = art_s.getBoolean("deleted");
					Article article = null;

					article = new Article(id, title, section_id, pages, abstract_text, sdf.parse(date_submitted),
							journal_storage.get(Long.parseLong(app_settings.get("journal_id"))));
					try {
						article.setDate_published(sdf.parse(date_published));
					} catch (Exception e) {

					}
					try {
						article.setDate_accepted(sdf.parse(date_accepted));
					} catch (Exception e) {

					}
					article.setDoi(doi);
					article.setSync(sync == null ? false : sync);
					article.setDeleted(deleted == null ? false : deleted);
					article.setPublished_pk(published_pk == null ? -1 : published_pk);
					article.setUnpublished_pk(unpublished_pk == null ? -1 : unpublished_pk);
					try {
						Date test_date = sdf.parse(date);
						article.setDate_published(test_date);
					} catch (Exception e) {

					}

					article_storage.put(id, article);

					if (articles_id <= id) {
						articles_id = id;
					}
					if (published_articles_id <= published_pk) {
						published_articles_id = published_pk;
					}

					// JOptionPane.showMessageDialog(null, "Deleted");

					ArrayList<Author> new_authors = new ArrayList<Author>();
					article_author_storage.put(id, new_authors);

				}

				art_s.close();

				System.out.println("Loading Article data....");
				art_s = c.createStatement().executeQuery("SELECT * FROM UNPUBLISHED_ARTICLE ORDER BY id ASC;");
				rsmd = art_s.getMetaData();
				System.out.println(rsmd.getColumnName(2));

				while (art_s.next()) {
					long id = art_s.getInt("id");
					String title = art_s.getString("title");
					int section_id = art_s.getInt("section_id");
					author_primary_storage.put(id, new ConcurrentHashMap<Long, Boolean>());

					ConcurrentHashMap<Long, ArticleFile> files = new ConcurrentHashMap<Long, ArticleFile>();
					file_storage.put((long) id, files);
					String pages = art_s.getString(rsmd.getColumnName(4));
					String abstract_text = art_s.getString(rsmd.getColumnName(5));
					String date = art_s.getString(rsmd.getColumnName(6));
					String date_accepted = art_s.getString("date_accepted");
					String date_submitted = art_s.getString("date_submitted");
					String doi = art_s.getString("doi");
					Boolean sync = art_s.getBoolean("sync");
					Boolean deleted = art_s.getBoolean("deleted");
					Article article = null;

					article = new Article(id, title, section_id, pages, abstract_text, sdf.parse(date_submitted),
							journal_storage.get(Long.parseLong(app_settings.get("journal_id"))));

					try {
						article.setDate_accepted(sdf.parse(date_accepted));
					} catch (Exception e) {

					}
					article.setDoi(doi);
					article.setSync(sync == null ? false : sync);
					article.setDeleted(deleted == null ? false : deleted);

					unpublished_article_storage.put(id, article);

					if (articles_id <= id) {
						articles_id = id;
					}

					// JOptionPane.showMessageDialog(null, "Deleted");

					ArrayList<Author> new_authors = new ArrayList<Author>();
					article_author_storage.put(id, new_authors);

				}

				art_s.close();

				System.out.println("Loading File data....");
				ResultSet rs_files = c.createStatement().executeQuery("SELECT id,article_id,path FROM FILE");
				while (rs_files.next()) {
					long id = rs_files.getInt(1);
					long article_id = rs_files.getInt(2);
					String path = rs_files.getString(3);
					System.out.println("ARTICLE FILES: " + id);
					ConcurrentHashMap<Long, ArticleFile> files = file_storage.get((long) article_id);
					if ((long) file_id < id) {
						file_id = id;
					}
					if (files == null) {
						files = new ConcurrentHashMap<Long, ArticleFile>();
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
				Set<Long> issue_k = issue_storage.keySet();
				for (long i_k : issue_k) {
					Statement rs_new = c.createStatement();
					ResultSet rs_new_issue = rs_new
							.executeQuery("SELECT * FROM ISSUE_ARTICLE WHERE issue_id=" + Long.toString(i_k) + ";");
					boolean has_next = rs_new_issue.next();
					while (has_next) {
						long issue_id = rs_new_issue.getInt("issue_id");
						long current_article_id = rs_new_issue.getInt("article_id");
						System.out.println("Issue: " + issue_id + " Article: " + current_article_id);

						Article current_article = article_storage.get(current_article_id);

						Issue update_issue = issue_storage.get(issue_id);
						current_article.setIssue_fk(update_issue);
						update_issue.add_article(current_article_id, current_article);
						issue_storage.put(issue_id, update_issue);
						article_storage.put(current_article_id, current_article);
						ConcurrentHashMap<Long, JFrame> issue_articles = article_screens.get(issue_id);

						issue_articles.put(current_article_id, new JFrame());
						article_screens.put(issue_id, issue_articles);
						has_next = rs_new_issue.next();
					}
					rs_new_issue.close();
				}
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
							Article current_article = article_storage.get(article_id);

							Boolean primary = rs_author.getBoolean(3);
							System.out.println(author_id + " art:" + article_id);
							author.setArticle_id(article_id);
							author_storage.put(author.getId(), author);
							// System.out.println(author_id + " - " +
							// author.getId());

							// System.out.println(author.getFull_name() + " " +
							// Long.toString(article_id));
							ConcurrentHashMap<Long, Boolean> primary_authors = author_primary_storage.get(article_id);
							primary_authors.put(author_id, primary);
							// System.out.println("Author: " + author_id + "
							// Primary: " + primary);
							author_primary_storage.put(article_id, primary_authors);
							Issue current_issue = issue_storage.get(current_article.getIssue_fk().getId());
							if (!article_author_storage.containsKey(author.getArticle_id())) {
								ArrayList<Author> new_authors = new ArrayList<Author>();
								new_authors.add(author);
								current_article.add_author(author);
								article_author_storage.put(author.getArticle_id(), new_authors);
								System.out.println("new ADDED - - " + author.getArticle_id());
								System.out.println("Size:  " + current_article.getAuthors().size());

							} else {
								ArrayList<Author> existing_authors = article_author_storage.get(author.getArticle_id());
								existing_authors.add(author);

								current_article.add_author(author);
								article_author_storage.put(author.getArticle_id(), existing_authors);
								System.out.println("ADDED - - " + author.getArticle_id());
								System.out.println("Size:  " + current_article.getAuthors().size());
							}
							article_storage.put(article_id, current_article);
							current_issue.update_article(article_id, current_article);
							issue_storage.put(current_issue.getId(), current_issue);
							System.out.println(
									"current " + current_issue.getArticles_list().get(article_id).getAuthors().size());
							System.out.println("storage " + issue_storage.get(current_issue.getId()).getArticles_list()
									.get(article_id).getAuthors().size());
							System.out.println("article_author " + article_author_storage.get(article_id).size());

						}
					} catch (SQLException e) {
						e.printStackTrace();

					}
				}
				// JOptionPane.showMessageDialog(null, "Dele
				c.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			try {
				latest_ids();
			} catch (IllegalStateException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
			System.out.println("Done.");
		} catch (Exception e) {
			e.printStackTrace();
		}
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

			delay = 2500;
			return false;
		} catch (IOException e2) {

			delay = 2500;
			return false;
		}
		if (response.getStatusLine().getStatusCode() == 401) {

			if (settings != null && settings.isVisible() == true) {
				settings.dispose();
			}
			if (section_screen != null && section_screen.isVisible() == true) {
				section_screen.dispose();
			}
			if (unpublished_articles_screen != null && unpublished_articles_screen.isVisible() == true) {
				unpublished_articles_screen.dispose();
			}
			logged_in = false;
			has_app_settings = false;
			app_settings = new ConcurrentHashMap<String, String>();

		}
		try {
			InputStream is = response.getEntity().getContent();
			is.close();
		} catch (IOException exc) {

			exc.printStackTrace();
		}

		delay = 8000;
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

				e2.printStackTrace();
			} catch (IOException e2) {

				e2.printStackTrace();
			}
			new JsonFactory();
			InputStream result = response.getEntity().getContent();
			org.json.simple.parser.JSONParser jsonParser = new JSONParser();

			new JSONObject();
			journal_url = null;
			user_url = null;
			try {
				JSONObject latest_obj = (JSONObject) jsonParser.parse(IOUtils.toString(result));
				System.out.println(latest_obj);
				journal_url = String.format("%s/journals/%s", base_url, latest_obj.get("journal"));
				user_url = String.format("%s/users/%s", base_url, latest_obj.get("user"));
				exists = true;
			} catch (ParseException e) {

				e.printStackTrace();
			}

			System.out.println(user_url);
			System.out.println(journal_url);
			if (user_url == null || user_url.compareTo(String.format("%s/users/null", base_url)) == 0
					|| journal_url == null || journal_url.compareTo(String.format("%s/journals/null", base_url)) == 0) {

				JOptionPane.showMessageDialog(null, "Profile missing or incomplete. Contact admin.");
				return false;
			}
			try {
				InputStream is = response.getEntity().getContent();
				is.close();
			} catch (IOException exc) {

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

				e2.printStackTrace();
			} catch (IOException e2) {

				e2.printStackTrace();
			}
			new JsonFactory();
			result = response.getEntity().getContent();
			jsonParser = new JSONParser();

			new JSONObject();
			try {
				JSONObject latest_obj = (JSONObject) jsonParser.parse(IOUtils.toString(result));

				app_settings.put("user_id", Long.toString((long) latest_obj.get("id")));
				exists = true;
			} catch (ParseException e) {

				e.printStackTrace();
			}

			try {
				InputStream is = response.getEntity().getContent();
				is.close();
			} catch (IOException exc) {

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

				e2.printStackTrace();
			} catch (IOException e2) {

				e2.printStackTrace();
			}
			new JsonFactory();
			result = response.getEntity().getContent();
			jsonParser = new JSONParser();
			Journal j = null;
			new JSONObject();
			try {
				JSONObject latest_obj = (JSONObject) jsonParser.parse(IOUtils.toString(result));
				String journal = Long.toString((long) latest_obj.get("id"));
				app_settings.put("journal_id", journal);
				if (!journal_storage.containsKey(Long.parseLong(journal))) {
					j = new Journal(Long.parseLong(journal), (String) latest_obj.get("path"),
							Float.parseFloat(Double.toString((double) latest_obj.get("seq"))),
							(String) latest_obj.get("primary_locale"), (long) latest_obj.get("enabled"));
					journal_storage.put(Long.parseLong(journal), j);
				}
				exists = true;
			} catch (ParseException e) {

				e.printStackTrace();
			}

			try {
				InputStream is = response.getEntity().getContent();
				is.close();
			} catch (IOException exc) {

				exc.printStackTrace();
			}
			if (j != null) {
				httpGet = new HttpGet(
						String.format("%s/get/setting/title/journal/%s/?format=json", base_url, j.getId()));
				httpGet.addHeader("Authorization", "Basic " + encoding);
				httpGet.setHeader("Accept", "application/json");
				httpGet.addHeader("Content-type", "application/json");

				response = null;
				try {
					response = httpClient.execute(httpGet);
				} catch (ClientProtocolException e2) {

					e2.printStackTrace();
				} catch (IOException e2) {

					e2.printStackTrace();
				}
				new JsonFactory();
				result = response.getEntity().getContent();
				jsonParser = new JSONParser();
				new JSONObject();
				System.out.println(String.format("%s/get/setting/title/journal/%s/?format=json", base_url, j.getId()));
				System.out.println(response.getStatusLine().getStatusCode());
				if (response.getStatusLine().getStatusCode() == 200) {
					JSONObject setting_json = new JSONObject();
					try {
						JSONObject setting = (JSONObject) jsonParser.parse(IOUtils.toString(result));
						System.out.println(setting.get("count"));
						System.out.println(setting);
						Long count = (Long) setting.get("count");
						if (count == null || count == 0) {
							exists = false;
						} else {
							JSONArray results = (JSONArray) setting.get("results");
							System.out.println(results.get(0));
							setting_json = (JSONObject) results.get(0);
							j.setTitle((String) setting_json.get("setting_value"));
							// new_issue.setShow_title((String)
							// setting_json.get("setting_value"));
						}
					} catch (ParseException e) {

						e.printStackTrace();
					}
				}
				try {
					InputStream is = response.getEntity().getContent();
					is.close();
				} catch (IOException exc) {

					exc.printStackTrace();
				}
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

				e2.printStackTrace();
			} catch (IOException e2) {

				e2.printStackTrace();
			}
			new JsonFactory();
			InputStream result = response.getEntity().getContent();
			org.json.simple.parser.JSONParser jsonParser = new JSONParser();
			new JSONObject();
			try {
				JSONObject latest_obj = (JSONObject) jsonParser.parse(IOUtils.toString(result));
				String detail = (String) latest_obj.get("detail");
				System.out.println(latest_obj);
				if (detail != null) {
				} else {
					id = (long) latest_obj.get("id");
					System.out.println(id);
				}
			} catch (ParseException e) {

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

				return latest = 1;
			} catch (IOException e2) {

				return latest = 1;
			}
			new JsonFactory();
			InputStream result = response.getEntity().getContent();
			org.json.simple.parser.JSONParser jsonParser = new JSONParser();
			JSONObject latest_json = new JSONObject();
			try {
				JSONObject latest_obj = (JSONObject) jsonParser.parse(IOUtils.toString(result));
				Long count = (Long) latest_obj.get("count");
				System.out.println(latest_obj);
				if (count == null || count == 0) {
					return latest;
				} else {
					JSONArray results = (JSONArray) latest_obj.get("results");
					System.out.println(latest_obj);
					System.out.println(results.get(0));
					latest_json = (JSONObject) results.get(0);
					latest = (long) latest_json.get("id");
					System.out.println(latest);
				}
			} catch (ParseException e) {

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
		long remote_published_article_id = 0;
		long remote_journal_id = 0;
		long remote_author_id = 0;
		long remote_file_id = 0;
		long remote_section_id = 0;
		remote_section_id = get_remote_id("section");
		remote_issue_id = get_remote_id("issue");
		remote_article_id = get_remote_id("article");
		remote_published_article_id = get_remote_id("published-article");
		remote_journal_id = get_remote_id("journal");
		remote_author_id = get_remote_id("author");
		remote_file_id = get_remote_id("file");
		if (remote_issue_id > i_id) {
			i_id = remote_issue_id;
		}
		if (remote_article_id > articles_id) {
			articles_id = remote_article_id;
		}
		if (remote_published_article_id > published_articles_id) {
			published_articles_id = remote_published_article_id;
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
		if (remote_section_id > section_db_id) {
			section_db_id = remote_section_id;
		}

		System.out.println("Latest IDS");
		System.out.println("Latest IDS");
		System.out.println("Issue " + remote_issue_id);
		System.out.println("Article " + remote_article_id);
		System.out.println("Journal " + remote_journal_id);
		System.out.println("File " + remote_file_id);
		System.out.println("Section " + remote_section_id);
		System.out.println("Published Article " + remote_published_article_id);
		System.out.println("Author " + remote_author_id + "-" + author_id);
		System.out.println("Latest IDS");
		System.out.println("Latest IDS");
	}

	public static void database_setup() {

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:local_datatabse.db");
			stmt = c.createStatement();

			String sql = "CREATE TABLE IF NOT EXISTS API"
					+ "(journal_id REAL, intersect_user_id REAL NOT NULL, user_id REAL PRIMARY KEY,"
					+ "credentials CHAR(500)," + " key CHAR(256) NOT NULL)";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS JOURNAL" + "(id INTEGER PRIMARY KEY," + " path CHAR(32) UNIQUE,"
					+ "seq REAL," + "primary_locale CHAR(5)," + " title CHAR(500)," + "enabled REAL)";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS ISSUE" + "(id INTEGER PRIMARY KEY," + " title CHAR(500) NOT NULL,"
					+ "volume INTEGER," + "number INTEGER," + "year INTEGER," + "published INTEGER,"
					+ " show_title INTEGER," + "show_volume INTEGER," + "show_number INTEGER," + "show_year INTEGER,"
					+ "access_status INTEGER," + "current INTEGER," + "date_published CHAR(50),"
					+ "date_accepted CHAR(50)," + "sync BOOLEAN DEFAULT FALSE," + "deleted BOOLEAN DEFAULT FALSE" + ")";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS ISSUE_JOURNAL" + "(id INTEGER PRIMARY KEY," + " journal_id INTEGER,"
					+ " issue_id INTEGER," + "FOREIGN KEY (journal_id) REFERENCES JOURNAL(id),"
					+ "FOREIGN KEY (issue_id) REFERENCES ISSUE(id)" + ")";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS SECTION" + "(id INTEGER PRIMARY KEY," + " title CHAR(250) NOT NULL,"
					+ "editor_restricted INTEGER DEFAULT 0," + "meta_indexed INTEGER DEFAULT 0,"
					+ "meta_reviewed INTEGER DEFAULT 0," + "abstracts_not_required INTEGER DEFAULT 0,"
					+ "hide_title INTEGER DEFAULT 0," + "hide_author INTEGER DEFAULT 0,"
					+ "hide_about INTEGER DEFAULT 0," + "seq REAL," + "disable_comments INTEGER DEFAULT 0,"
					+ "abstract_word_count INTEGER DEFAULT 0," + "sync BOOLEAN DEFAULT FALSE,"
					+ "deleted BOOLEAN DEFAULT FALSE" + ");";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS AUTHOR" + "(id INTEGER PRIMARY KEY," + " first_name CHAR(200) NOT NULL,"
					+ " middle_name CHAR(200) NOT NULL," + " last_name CHAR(200) NOT NULL,"
					+ " email CHAR(400) NOT NULL," + " affiliation CHAR(500) NOT NULL," + " bio CHAR(800),"
					+ " orcid CHAR(100)," + " twitter CHAR(100)," + " department CHAR(300) NOT NULL,"
					+ " country CHAR(300) NOT NULL," + "deleted BOOLEAN DEFAULT FALSE" + ")";
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
					+ " locale CHAR(50) NOT NULL," + "user_id REAL NOT NULL, " + " doi CHAR(1000),"
					+ "published_pk INTEGER," + "sync BOOLEAN DEFAULT FALSE," + "deleted BOOLEAN DEFAULT FALSE,"
					+ "unpublished_pk INTEGER," + "FOREIGN KEY (section_id) REFERENCES SECTION(id)" + ")";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS UNPUBLISHED_ARTICLE" + "(id INTEGER PRIMARY KEY,"
					+ " title CHAR(500) NOT NULL," + "section_id INTEGER," + "pages CHAR(255),"
					+ " abstract CHAR(2000)," + "date_accepted CHAR(50)," + "date_submitted CHAR(50),"
					+ "status INTEGER," + "submission_progress INTEGER," + "current_round INTEGER,"
					+ "fast_tracked INTEGER," + "hide_author INTEGER," + "comments_status INTEGER,"
					+ " language CHAR(50) NOT NULL," + "locale CHAR(50) NOT NULL," + "user_id REAL NOT NULL, "
					+ " doi CHAR(1000)," + "sync BOOLEAN DEFAULT FALSE," + "deleted BOOLEAN DEFAULT FALSE,"
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
				height_small = (int) (380 - (380 * (5 / 100)));

				login = new JFrame();
				login.setResizable(false);
				login.setTitle("TORU - Log In");
				login.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						// database_save();
					}
				});
				login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				login.getContentPane().setForeground(Color.WHITE);
				login.getContentPane().setBackground(new Color(213, 213, 213));
				Dimension sreen = Toolkit.getDefaultToolkit().getScreenSize();

				login.setSize(sreen.width / 2 - login.getSize().width / 2,
						sreen.height / 2 - login.getSize().height / 2);
				// login.setLocationRelativeTo(null);
				login.setSize(width_small, height_small);// 400 width and 500
															// height
				login.getContentPane().setLayout(null);// using no layout
														// managers
				username = new JTextField();
				username.setBounds(80, 160, width_small - 161, 26);
				login.getContentPane().add(username);
				username.setColumns(4);
				JLabel lblUsername = new JLabel("Username");
				lblUsername.setForeground(new Color(0, 0, 0));
				lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
				lblUsername.setBounds(80, 140, width_small - 161, 16);
				login.getContentPane().add(lblUsername);
				JLabel lblPassword = new JLabel("Password");
				lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
				lblPassword.setForeground(new Color(0, 0, 0));
				lblPassword.setBounds(80, 200, width_small - 161, 16);
				login.getContentPane().add(lblPassword);
				passwordField = new JPasswordField();
				passwordField.setBounds(80, 220, width_small - 161, 26);
				login.getContentPane().add(passwordField);
				JButton btnLogin = new JButton("Login");
				Action actionSubmit = new AbstractAction() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void actionPerformed(ActionEvent e) {
						if (status_online()) {
							String user = username.getText();
							String pass = String.valueOf(passwordField.getPassword());
							if (pass == null || user == null || user == "" || pass == "") {
								JOptionPane.showMessageDialog(null, "Wrong username or password");
							} else {
								BASE64Encoder encoder = new BASE64Encoder();
								encoding = encoder.encode(String.format("%s:%s", user, pass).getBytes());
								app_settings.put("credentials", encoding);
								long user_id = -1;
								try {
									user_id = get_intersect_id();
								} catch (IllegalStateException e1) {

									e1.printStackTrace();
								} catch (IOException e1) {

									e1.printStackTrace();
								}

								if (user_id != -1) {
									try {
										populate_api(Long.toString(user_id));
									} catch (SQLException e1) {

										e1.printStackTrace();
									}
									if (app_settings.get("user_id").compareTo("") != 0) {
										app_settings.put("intersect_user_id", Long.toString(user_id));
										logged_in = true;
										login.setVisible(false);
										login.dispose();
										System.out.println(app_settings);
										if (app_settings.get("key") == null
												|| app_settings.get("key").compareTo("") == 0) {
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
								} else {
									JOptionPane.showMessageDialog(null, "Wrong username or password");
								}
							}
						} else {
							JOptionPane.showMessageDialog(null, "Unable to connect to server.");

						}

					}
				};
				username.addActionListener(actionSubmit);
				passwordField.addActionListener(actionSubmit);
				btnLogin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (status_online()) {
							String user = username.getText();
							String pass = String.valueOf(passwordField.getPassword());
							if (user.isEmpty() || pass.isEmpty() || pass == null || user == null || user == ""
									|| pass == "") {
								JOptionPane.showMessageDialog(null, "Wrong username or password");
							} else {
								BASE64Encoder encoder = new BASE64Encoder();
								encoding = encoder.encode(String.format("%s:%s", user, pass).getBytes());
								app_settings.put("credentials", encoding);
								long user_id = -1;
								try {
									user_id = get_intersect_id();

								} catch (IllegalStateException e1) {

									e1.printStackTrace();
								} catch (IOException e1) {

									e1.printStackTrace();
								}

								if (user_id != -1) {
									try {
										populate_api(Long.toString(user_id));
									} catch (SQLException e1) {

										e1.printStackTrace();
									}
									if (app_settings.get("user_id").compareTo("") != 0) {
										app_settings.put("intersect_user_id", Long.toString(user_id));
										login.setVisible(false);
										if (app_settings.get("key") == null
												|| app_settings.get("key").compareTo("") == 0) {
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
								} else {
									JOptionPane.showMessageDialog(null, "Wrong username or password");
								}
							}
						} else {
							JOptionPane.showMessageDialog(null, "Unable to connect to server.");

						}
					}
				});

				btnLogin.setBounds(width_small / 3, 265, width_small / 3, 29);
				login.getContentPane().add(btnLogin);

				final Label btnSync1 = new Label("Status");
				btnSync1.setBounds(width_small - 135, 89, 45, 25);
				btnSync1.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
				btnSync1.setForeground(Color.black);
				login.getContentPane().add(btnSync1);

				final Label internetCheck = new Label("ONLINE");
				internetCheck.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT | Font.ITALIC, 12));
				internetCheck.setBackground(Color.lightGray);
				internetCheck.setForeground(new Color(255, 255, 255));
				internetCheck.setAlignment(1);
				internetCheck.setBounds(width_small - 85, 90, 65, 22);
				login.getContentPane().add(internetCheck);

				JLabel lblIssue = new JLabel("Login");
				lblIssue.setBackground(new Color(46, 46, 46));
				lblIssue.setForeground(new Color(255, 255, 255));
				lblIssue.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 26));
				lblIssue.setBounds(width_small - 120, 26, 280, 40);
				lblIssue.setOpaque(true);
				login.getContentPane().add(lblIssue);

				JPanel panel = new JPanel();
				panel.setBackground(new Color(46, 46, 46));
				panel.setLayout(null);
				panel.setBounds(0, 0, width, 70);
				ImageIcon icon = new ImageIcon(String.format("%s/required_files/%s", directory, "toru-ui-logo.png"));
				JLabel logo = new JLabel(icon);
				logo.setBounds(10, 20, 140, 40);
				logo.setBackground(new Color(46, 46, 46));
				panel.add(logo);
				panel.repaint();
				logo.repaint();
				login.getContentPane().add(panel);

				Panel panel_1 = new Panel();
				panel_1.setBackground(new Color(25, 25, 25));
				panel_1.setBounds(0, 70, width, 6);
				login.getContentPane().add(panel_1);

				ActionListener taskPerformer1 = new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						if (status_online()) {
							internetCheck.setBackground(Color.lightGray);
							internetCheck.setText("ONLINE");

						} else {
							internetCheck.setBackground(Color.RED);
							internetCheck.setText("OFFLINE");
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
				int height_small = (int) ((height - 20) - ((height - 20) * (5 / 100)));
				settings = new JFrame();
				settings.setResizable(false);
				settings.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				settings.getContentPane().setBackground(new Color(213, 213, 213));
				settings.setTitle("Settings");

				settings.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						// database_save();
					}
				});
				settings.setSize(width_small, height_small - 80);// 400 width
																	// and 500
																	// height
				settings.getContentPane().setLayout(null);

				final JButton btnBasic = new JButton("Basic");
				btnBasic.setBounds(35, 115, 100, 25);
				settings.getContentPane().add(btnBasic);
				final JButton btnAdvanced = new JButton("Advanced");

				settings.getContentPane().add(btnAdvanced);
				JScrollPane scrollSettings = new JScrollPane();
				scrollSettings.setBounds(40, 155, width_small - 80, height_small - 280);

				JPanel panelSettings = new JPanel();

				panelSettings.setLayout(null);
				panelSettings.setAutoscrolls(true);
				panelSettings.setBackground(new Color(185, 185, 185));
				int y = 30;
				int settings_height = 210 + 30 * (setting_keys.size() - 8);
				panelSettings.setPreferredSize(new Dimension(width_small - 80, settings_height));
				JScrollPane scrollFrame = new JScrollPane(panelSettings);
				panelSettings.setAutoscrolls(true);
				scrollFrame.setPreferredSize(new Dimension(320, 200));
				scrollFrame.setBounds(40, 150, width_small - 80, height_small - 350);
				scrollFrame.setBackground(new Color(185, 185, 185));

				// scrollSettings.setViewportView(scrollFrame);
				settings.getContentPane().add(scrollFrame);
				ArrayList<JCheckBox> checkboxes = new ArrayList<JCheckBox>();
				btnAdvanced.setBounds(135, 115, 100, 25);
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
							Integer.parseInt(value);
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
										Integer.parseInt(intSampleSetting.getText());
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
										Integer.parseInt(intSampleSetting.getText());
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
										Integer.parseInt(intSampleSetting.getText());
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
				btnSave.setBounds((width_small / 2) - 85, height_small - 165, 180, 30);
				settings.getContentPane().add(btnSave);
				final Label btnSync1 = new Label("Status");
				btnSync1.setBounds(width_small - 135, 89, 45, 25);
				btnSync1.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
				btnSync1.setForeground(Color.black);
				settings.getContentPane().add(btnSync1);

				final Label internetCheck = new Label("ONLINE");
				internetCheck.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT | Font.ITALIC, 12));
				internetCheck.setBackground(Color.lightGray);
				internetCheck.setForeground(new Color(255, 255, 255));
				internetCheck.setAlignment(1);
				internetCheck.setBounds(width_small - 85, 90, 65, 22);
				settings.getContentPane().add(internetCheck);

				JLabel lblIssue = new JLabel("Settings");
				lblIssue.setBackground(new Color(46, 46, 46));
				lblIssue.setForeground(new Color(255, 255, 255));
				lblIssue.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 26));
				lblIssue.setBounds(width_small - 120, 26, 280, 40);
				lblIssue.setOpaque(true);
				settings.getContentPane().add(lblIssue);

				JPanel panel = new JPanel();
				panel.setBackground(new Color(46, 46, 46));
				panel.setLayout(null);
				panel.setBounds(0, 0, width, 70);
				ImageIcon icon = new ImageIcon(String.format("%s/required_files/%s", directory, "toru-ui-logo.png"));
				JLabel logo = new JLabel(icon);
				logo.setBounds(10, 20, 140, 40);
				logo.setBackground(new Color(46, 46, 46));
				panel.add(logo);
				panel.repaint();
				logo.repaint();
				settings.getContentPane().add(panel);

				Panel panel_1 = new Panel();
				panel_1.setBackground(new Color(25, 25, 25));
				panel_1.setBounds(0, 70, width, 6);
				settings.getContentPane().add(panel_1);

				ActionListener taskPerformer1 = new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						if (status_online()) {
							internetCheck.setBackground(Color.lightGray);
							internetCheck.setText("ONLINE");

						} else {
							internetCheck.setBackground(Color.RED);
							internetCheck.setText("OFFLINE");
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

					height_small = (int) (360 - (360 * (5 / 100)));
					api = new JFrame();
					api.setResizable(false);
					api.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					api.getContentPane().setBackground(new Color(213, 213, 213));
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
					JPasswordField access_key = new JPasswordField();
					access_key.setColumns(4);
					access_key.setText("");
					access_key.setBounds(100, 170, width_small - 200, 26);
					api.getContentPane().add(access_key);
					JLabel lblAccessKey = new JLabel("Enter access key:");
					lblAccessKey.setHorizontalAlignment(SwingConstants.CENTER);
					lblAccessKey.setForeground(new Color(0, 0, 0));
					lblAccessKey.setBounds(80, 150, width_small - 161, 16);
					api.getContentPane().add(lblAccessKey);

					JButton btnSubmit = new JButton("Enter");
					JButton btnReset = new JButton("Reset");
					Action actionSubmit = new AbstractAction() {
						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

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
					btnReset.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							has_app_settings = false;
							logged_in = false;
							login("dashboard");
							api.dispose();
							app_settings = new ConcurrentHashMap<String, String>();

						}
					});
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
						btnSubmit.setBounds(((width_small / 3) * 2) / 2, height_small - 235, width_small / 3, 29);
						btnReset.setBounds(((width_small / 3) * 2) / 2 + (width_small / 3 - width_small / 5) / 2,
								height_small - 206, width_small / 5, 29);
					} else {
						btnSubmit.setBounds(((width_small / 3) * 2) / 2, 210, width_small / 3, 29);
						btnReset.setBounds(((width_small / 3) * 2) / 2 + (width_small / 3 - width_small / 5) / 2, 255,
								width_small / 5, 29);
					}

					api.getContentPane().add(btnSubmit);
					api.getContentPane().add(btnReset);

					final Label btnSync1 = new Label("Status");
					btnSync1.setBounds(width_small - 135, 89, 45, 25);
					btnSync1.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
					btnSync1.setForeground(Color.black);
					api.getContentPane().add(btnSync1);

					final Label internetCheck = new Label("ONLINE");
					internetCheck.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT | Font.ITALIC, 12));
					internetCheck.setBackground(Color.lightGray);
					internetCheck.setForeground(new Color(255, 255, 255));
					internetCheck.setAlignment(1);
					internetCheck.setBounds(width_small - 85, 90, 65, 22);
					api.getContentPane().add(internetCheck);

					JLabel lblIssue = new JLabel("API Information");
					lblIssue.setBackground(new Color(46, 46, 46));
					lblIssue.setForeground(new Color(255, 255, 255));
					lblIssue.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 26));
					lblIssue.setBounds(width_small - 260, 26, 280, 40);
					lblIssue.setOpaque(true);
					api.getContentPane().add(lblIssue);

					JPanel panel = new JPanel();
					panel.setBackground(new Color(46, 46, 46));
					panel.setLayout(null);
					panel.setBounds(0, 0, width, 70);
					ImageIcon icon = new ImageIcon(
							String.format("%s/required_files/%s", directory, "toru-ui-logo.png"));
					JLabel logo = new JLabel(icon);
					logo.setBounds(10, 20, 140, 40);
					logo.setBackground(new Color(46, 46, 46));
					panel.add(logo);
					panel.repaint();
					logo.repaint();
					api.getContentPane().add(panel);

					Panel panel_1 = new Panel();
					panel_1.setBackground(new Color(25, 25, 25));
					panel_1.setBounds(0, 70, width, 6);
					api.getContentPane().add(panel_1);

					ActionListener taskPerformer1 = new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							if (status_online()) {
								internetCheck.setBackground(Color.lightGray);
								internetCheck.setText("ONLINE");

							} else {
								internetCheck.setBackground(Color.RED);
								internetCheck.setText("OFFLINE");
							}
						}
					};
					new Timer(delay, taskPerformer1).start();
					api.setVisible(true);// making the frame visible

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

					height_small = (int) (360 - (360 * (5 / 100)));
					api = new JFrame();
					api.setResizable(false);
					api.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					api.getContentPane().setBackground(new Color(213, 213, 213));
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

					access_key = new JTextField();
					access_key.setColumns(4);
					access_key.setText("");
					access_key.setBounds(100, 170, width_small - 200, 26);
					api.getContentPane().add(access_key);
					JLabel lblAccessKey = new JLabel("Enter access key for future offline and online access:");
					lblAccessKey.setHorizontalAlignment(SwingConstants.CENTER);
					lblAccessKey.setForeground(new Color(0, 0, 0));
					lblAccessKey.setBounds(80, 150, width_small - 161, 16);
					api.getContentPane().add(lblAccessKey);

					JButton btnSubmit = new JButton("Submit");
					if (exists)

					{
						btnSubmit.setText("Save");
					}

					Action actionSubmit = new AbstractAction() {
						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

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

							database_save();

						}
					});
					if (height_small - 150 > 300) {
						btnSubmit.setBounds(((width_small / 3) * 2) / 2, height_small - 235, width_small / 3, 29);
					} else {
						btnSubmit.setBounds(((width_small / 3) * 2) / 2, 220, width_small / 3, 29);
					}

					api.getContentPane().add(btnSubmit);

					final Label btnSync1 = new Label("Status");
					btnSync1.setBounds(width_small - 135, 89, 45, 25);
					btnSync1.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
					btnSync1.setForeground(Color.black);
					api.getContentPane().add(btnSync1);

					final Label internetCheck = new Label("ONLINE");
					internetCheck.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT | Font.ITALIC, 12));
					internetCheck.setBackground(Color.lightGray);
					internetCheck.setForeground(new Color(255, 255, 255));
					internetCheck.setAlignment(1);
					internetCheck.setBounds(width_small - 85, 90, 65, 22);
					api.getContentPane().add(internetCheck);

					JLabel lblIssue = new JLabel("API");
					lblIssue.setBackground(new Color(46, 46, 46));
					lblIssue.setForeground(new Color(255, 255, 255));
					lblIssue.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 26));
					lblIssue.setBounds(width_small - 120, 26, 280, 40);
					lblIssue.setOpaque(true);
					api.getContentPane().add(lblIssue);

					JPanel panel = new JPanel();
					panel.setBackground(new Color(46, 46, 46));
					panel.setLayout(null);
					panel.setBounds(0, 0, width, 70);
					ImageIcon icon = new ImageIcon(
							String.format("%s/required_files/%s", directory, "toru-ui-logo.png"));
					JLabel logo = new JLabel(icon);
					logo.setBounds(10, 20, 140, 40);
					logo.setBackground(new Color(46, 46, 46));
					panel.add(logo);
					panel.repaint();
					logo.repaint();
					api.getContentPane().add(panel);

					Panel panel_1 = new Panel();
					panel_1.setBackground(new Color(25, 25, 25));
					panel_1.setBounds(0, 70, width, 6);
					api.getContentPane().add(panel_1);

					ActionListener taskPerformer1 = new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							if (status_online()) {
								internetCheck.setBackground(Color.lightGray);
								internetCheck.setText("ONLINE");

							} else {
								internetCheck.setBackground(Color.RED);
								internetCheck.setText("OFFLINE");
							}
						}
					};
					new Timer(delay, taskPerformer1).start();
					api.setVisible(true);// making
											// the
											// frame
											// visible

				}
			}
		}
	}

	public void dashboard() {
		if (logged_in) {

			try {
				if (issues == null || !issues.isVisible()) {

					// Issue issue = new Issue(i_id, "title", 1, 1, 2015,
					// "title",
					// 1, 1, 2015, date);
					// Issue Table [title, volume, number, year, show_title,
					// show_volume,
					// show_number, show_year, date_published]
					if (status_online()) {
						try {
							latest_ids();
						} catch (IllegalStateException e) {

							e.printStackTrace();
						} catch (IOException e) {

							e.printStackTrace();
						}
					}
					issues = new JFrame();
					issues.setResizable(false);
					issues.setVisible(true);

					issues.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					if (height >= 640 && width >= 900) {
						issues.setSize(width, height);
					} else {
						width = 1024;
						height = 768;
						issues.setSize(1024, 768);
					}
					issues.getContentPane().setBackground(new Color(213, 213, 213));

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
						if (!row_issue.isDeleted()) {
							ArrayList<Object> data = new ArrayList<Object>();
							issue_screens.put(id, new JFrame());
							article_screens.put(id, new ConcurrentHashMap<Long, JFrame>());

							data.add(Long.toString(row_issue.getId()));
							data.add(row_issue.getShow_title() == 1 ? row_issue.getTitle() : "Hidden");
							data.add(row_issue.getShow_volume() == 1 ? Integer.toString(row_issue.getVolume())
									: "Hidden");
							data.add(row_issue.getShow_number() == 1 ? Integer.toString(row_issue.getNumber())
									: "Hidden");
							data.add(row_issue.getShow_year() == 1 ? Integer.toString(row_issue.getYear()) : "Hidden");
							data.add(row_issue.getDate_published() == null ? "/"
									: sdf.format(row_issue.getDate_published()));
							data.add("View");
							data.add("Edit");
							data.add("Delete");
							Object[] row = { row_issue.getId(),
									row_issue.getShow_title() == 1 ? row_issue.getTitle() : "Hidden",
									row_issue.getShow_volume() == 1 ? Integer.toString(row_issue.getVolume())
											: "Hidden",
									row_issue.getShow_number() == 1 ? Integer.toString(row_issue.getNumber())
											: "Hidden",
									row_issue.getShow_year() == 1 ? Integer.toString(row_issue.getYear()) : "Hidden",
									row_issue.getDate_accepted() == null ? "/"
											: sdf.format(row_issue.getDate_accepted()),
									row_issue.getDate_published() == null ? "/"
											: sdf.format(row_issue.getDate_published()),
									"View", "Edit", "Delete" };
							rows[i] = row;
							i++;
							rowData.add(data);
						}

					}
					Object columnNames[] = { "ID", "Title", "Volume", "Number", "Year", "Date Submitted",
							"Date Published", "", "", "" };
					issues.getContentPane().setLayout(null);

					final JButton btnSync = new JButton("Sync");

					btnSync.setBounds(width - 155, 83, 70, 24);

					final JButton btnSection = new JButton("Sections");
					btnSection.setBounds(112, 82, 120, 29);

					btnSection.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							sections();
						}
					});
					final JButton btnUnpublished = new JButton("Unpublished Articles");
					btnUnpublished.setBounds(238, 82, 200, 29);

					btnUnpublished.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							unpublished_articles();
						}
					});
					btnSync.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {

							int num_rows = ((DefaultTableModel) issues_table.getModel()).getRowCount();

							if (status_online()) {
								Set<Long> issue_keys = issue_storage.keySet();
								if (issue_keys.isEmpty()) {
								}
								CopyOnWriteArrayList<Future<?>> futures = new CopyOnWriteArrayList<Future<?>>();
								ExecutorService exec = Executors.newFixedThreadPool(4);
								final JProgressBar progressBar = new JProgressBar();
								progressBar.setValue(0);
								progressBar.setStringPainted(true);
								progressBar.setBounds(width / 2 - 170, height - 100, 375, 32);
								JLabel progress_msg = new JLabel("Estimated progress per Issue:");

								progress_msg.setBounds(width / 2 - 75, height - 132, 200, 40);

								for (long key : issue_keys) {
									issue_countdown_storage.put((long) key, false);
								}
								if (!issue_keys.isEmpty()) {
									Object[] options = { "All Remote Data", "All Local Data", "Remote Data",
											"Local Data", "Cancel" };
									int dialogResult2 = JOptionPane.showOptionDialog(null,
											"Would You Like to replace local Section data or update remote Secton data",
											"Warning", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
											options, options[3]);
									if (dialogResult2 == 3 || dialogResult2 == 1) {
										Future<?> f = exec.submit(new Runnable() {
											public void run() {

												try {
													get_sections(Long.parseLong(app_settings.get("journal_id")),
															encoding, false);

												} catch (NumberFormatException e) {

													e.printStackTrace();
												} catch (IllegalStateException e) {

													e.printStackTrace();
												} catch (IOException e) {

													e.printStackTrace();
												}
											}
										});
										futures.add(f);
									} else if (dialogResult2 == 2 || dialogResult2 == 0) {
										Future<?> f = exec.submit(new Runnable() {
											public void run() {
												try {
													update_sections(Long.parseLong(app_settings.get("journal_id")),
															encoding, false);
												} catch (NumberFormatException e1) {

													e1.printStackTrace();
												} catch (IllegalStateException e1) {

													e1.printStackTrace();
												} catch (IOException e1) {

													e1.printStackTrace();
												}
											}
										});
										futures.add(f);
									}
									boolean all = false;
									if (dialogResult2 == 0 || dialogResult2 == 1) {
										all = true;
									}
									for (long key : issue_keys) {

										// progress_increment
										Issue current_issue = issue_storage.get(key);

										long issue_id = current_issue.getId();
										if (!all) {
											dialogResult = JOptionPane.showOptionDialog(null,
													String.format(
															"Issue %s <%s>: Would You Like to replace local data or update remote data",
															current_issue.getTitle(), Long.toString(issue_id)),
													"Warning", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
													null, options, options[3]);
											if (dialogResult == 0 || dialogResult == 1) {
												all = true;
											}
										}

										if (dialogResult == 4) {
											issue_countdown_storage.put((long) current_issue.getId(), true);
										}
										if (dialogResult == 2 || dialogResult == 0) {
											progress_executor.execute(new Runnable() {
												public void run() {
													int countdown = current_issue.getArticles_list().size() * 7 + 120
															+ current_issue.getAuthors().size() * 5;
													if (current_issue.getArticles_list().size() == 0) {
														countdown = 100;
														for (int i = 0; i < countdown; i++) {
															final int percent = i;
															if (issue_countdown_storage.containsKey((long) key)
																	&& issue_countdown_storage
																			.get((long) key) == true) {
																progressBar.setValue(100);
																progressBar.repaint();
																break;
															}
															SwingUtilities.invokeLater(new Runnable() {
																public void run() {
																	if (issue_countdown_storage.containsKey((long) key)
																			&& issue_countdown_storage
																					.get((long) key) == true) {
																		progressBar.setValue(100);

																	} else {
																		progressBar.setValue(percent);
																	}
																	progressBar.repaint();
																}
															});

															try {
																Thread.sleep(100);
															} catch (InterruptedException e) {
																e.printStackTrace();
															}
														}
													} else {
														System.out.println("countdown " + countdown);
														double decimal = (current_issue.getArticles_list().size() * 7
																+ 120 + current_issue.getAuthors().size() * 5) / 100;
														System.out.println(decimal);
														for (int i = 0; i < countdown; i++) {
															final int percent = i;
															if (issue_countdown_storage.containsKey((long) key)
																	&& issue_countdown_storage
																			.get((long) key) == true) {
																progressBar.setValue(100);
																progressBar.repaint();
																break;
															}
															SwingUtilities.invokeLater(new Runnable() {
																public void run() {
																	if (issue_countdown_storage.containsKey((long) key)
																			&& issue_countdown_storage
																					.get((long) key) == true) {
																		progressBar.setValue(100);

																	} else {
																		progressBar.setValue(percent == 0 ? 0
																				: (int) Double
																						.parseDouble(String.format("%s",
																								percent / decimal)));
																	}
																	progressBar.repaint();
																}
															});

															try {
																Thread.sleep(100);
															} catch (InterruptedException e) {
																e.printStackTrace();
															}
														}
													}
												}
											});
											issues.add(progress_msg);
											issues.add(progressBar);
											issues.repaint();
											Future<?> f = exec.submit(new Runnable() {
												public void run() {
													try {
														update_issue_intersect(current_issue, encoding, false);

													} catch (IllegalStateException | IOException e1) {

														e1.printStackTrace();
													}
												}
											});
											futures.add(f);

										} else if (dialogResult == 1 || dialogResult == 3) {
											progress_executor.execute(new Runnable() {
												public void run() {
													int countdown = current_issue.getArticles_list().size() * 7 + 120
															+ current_issue.getAuthors().size() * 5;
													if (current_issue.getArticles_list().size() == 0) {
														countdown = 100;
														for (int i = 0; i < countdown; i++) {
															final int percent = i;
															SwingUtilities.invokeLater(new Runnable() {
																public void run() {
																	progressBar.setValue(percent);
																	progressBar.repaint();
																}
															});

															try {
																Thread.sleep(100);
															} catch (InterruptedException e) {
																e.printStackTrace();
															}
														}
													} else {
														System.out.println("countdown " + countdown);
														double decimal = (current_issue.getArticles_list().size() * 7
																+ 120 + current_issue.getAuthors().size() * 5) / 100;
														System.out.println(decimal);
														for (int i = 0; i < countdown; i++) {
															final int percent = i;
															SwingUtilities.invokeLater(new Runnable() {
																public void run() {
																	progressBar.setValue(percent == 0 ? 0
																			: (int) Double.parseDouble(String
																					.format("%s", percent / decimal)));
																	progressBar.repaint();
																}
															});

															try {
																Thread.sleep(100);
															} catch (InterruptedException e) {
																e.printStackTrace();
															}
														}
													}
												}
											});
											issues.add(progress_msg);
											issues.add(progressBar);
											issues.repaint();
											System.out.println("update local");

											Future<?> f = exec.submit(new Runnable() {

												public void run() {
													try {
														Issue updated_issue = update_issue_local(current_issue,
																encoding);

														issue_storage.put(issue_id, updated_issue);
														System.out.println(updated_issue);
													} catch (IllegalStateException | IOException e1) {

														e1.printStackTrace();

													}
												}
											});

											futures.add(f);

										}
										if (!current_issue.isDeleted()) {
											if (dialogResult == 0 || dialogResult == 2) {

												Future<?> f = exec.submit(new Runnable() {

													public void run() {
														try {
															update_articles_intersect(current_issue, encoding);
															// sync_authors_intersect(issue_id,
															// encoding, false);

														} catch (IllegalStateException | IOException e1) {

															e1.printStackTrace();
														}
													}
												});

												futures.add(f);
											} else if (dialogResult == 1 || dialogResult == 3) {
												System.out.println("update local");
												Future<?> f = exec.submit(new Runnable() {

													public void run() {
														try {
															update_articles_local_single_request(current_issue,
																	encoding);
															get_authors_remote_single_request(issue_id, encoding,
																	false);
														} catch (IllegalStateException | IOException e1) {

															e1.printStackTrace();
														}
													}
												});
												futures.add(f);
											}

										}
										issues.repaint();
									}
									progress_executor.execute(new Runnable() {
										public void run() {
											synchronized (futures) {
												for (Future<?> future : futures) {
													try {
														future.get();
													} catch (InterruptedException e1) {

														e1.printStackTrace();
													} catch (ExecutionException e1) {

														e1.printStackTrace();
													}
												}
											}
										}
									});
									progress_executor.execute(new Runnable() {
										public void run() {
											synchronized (futures) {
												for (Future<?> future : futures) {
													try {
														future.get();
													} catch (InterruptedException e1) {

														e1.printStackTrace();
													} catch (ExecutionException e1) {

														e1.printStackTrace();
													}
												}
												issues.remove(progressBar);

												issues.remove(progress_msg);
												issues.repaint();
												progressBar.setIndeterminate(true);

											}
										}
									});
								}
								try {
									System.out.println(check_new_issues(encoding));
									if (check_new_issues(encoding)) {
										new_issues_process_done = false;
										Future<?> f = exec.submit(new Runnable() {

											public void run() {

												issue_storage.keySet();

												new_issues = new ArrayList<Issue>();

												progress_executor.execute(new Runnable() {
													public void run() {
														int countdown = 300;

														for (int i = 0; i < countdown; i++) {
															final int percent = i;
															final double decimal = countdown / 100;
															if (new_issues_process_done) {
																progressBar.setValue(100);
																progressBar.repaint();
																break;
															}
															SwingUtilities.invokeLater(new Runnable() {
																public void run() {
																	if (new_issues_process_done) {
																		progressBar.setValue(100);
																		progressBar.repaint();
																	} else {
																		progressBar.setValue(

																				(int) Double.parseDouble(String.format(
																						"%s", percent / decimal)));
																		progressBar.repaint();
																	}
																}
															});

															try {
																Thread.sleep(100);
															} catch (InterruptedException e) {
															}

														}

													}
												});
												issues.add(progress_msg);
												issues.add(progressBar);
												issues.repaint();
												Future<?> f = exec.submit(new Runnable() {

													public void run() {
														try {
															get_sections(Long.parseLong(app_settings.get("journal_id")),
																	encoding, false);
															new_issues = update_get_issues_from_remote(encoding, false);
														} catch (IllegalStateException e) {

															e.printStackTrace();
														} catch (IOException e) {

															e.printStackTrace();
														}
													}
												});
												try {
													f.get();
												} catch (InterruptedException | ExecutionException e) {
													// TODO Auto-generated catch
													// block
													e.printStackTrace();
												}
												futures.add(f);

											}
										});
										futures.add(f);
									}
								} catch (IOException e2) {

									e2.printStackTrace();
								}
								progress_executor.execute(new Runnable() {
									public void run() {
										synchronized (futures) {
											for (Future<?> future : futures) {
												try {
													if (future != null) {
														future.get();
													}
												} catch (InterruptedException e1) {

													e1.printStackTrace();
												} catch (ExecutionException e1) {

													e1.printStackTrace();
												}
											}
											issues.remove(progressBar);

											issues.remove(progress_msg);
											issues.repaint();
											JOptionPane.showMessageDialog(null, "Sync completed.");
											int dialogResult = JOptionPane.showConfirmDialog(null,
													"Save changes to local database?", "Warning", 1);

											if (dialogResult == 1 || dialogResult == 3) {
												database_save();
											}
											if (num_rows == 0) {
												issues.dispose();
												dashboard();
											} else {
												Set<Long> update_issue_keys = issue_storage.keySet();
												new ArrayList<List<Object>>();
												Object[][] rows = new Object[update_issue_keys.size()][6];
												if (num_rows != 0) {
													for (int i = num_rows - 1; i >= 0; i--) {
														((DefaultTableModel) issues_table.getModel()).removeRow(i);
													}
												}
												int i = 0;
												for (long id : update_issue_keys) {
													Issue row_issue = issue_storage.get(id);
													issue_screens.put(id, new JFrame());
													article_screens.put(id, new ConcurrentHashMap<Long, JFrame>());

													Object[] row = { row_issue.getId(),
															row_issue.getShow_title() == 1 ? row_issue.getTitle()
																	: "Hidden",
															row_issue.getShow_volume() == 1 ? row_issue.getVolume()
																	: "Hidden",
															row_issue.getShow_number() == 1 ? row_issue.getNumber()
																	: "Hidden",
															row_issue.getShow_year() == 1 ? row_issue.getYear()
																	: "Hidden",
															row_issue.getDate_accepted() == null ? "/"
																	: sdf.format(row_issue.getDate_accepted()),
															row_issue.getDate_published() == null ? "/"
																	: sdf.format(row_issue.getDate_published()),
															"View", "Edit", "Delete" };
													rows[i] = row;
													((DefaultTableModel) issues_table.getModel()).insertRow(0, row);
													i++;

												}
												if (num_rows != 0) {
													((DefaultTableModel) issues_table.getModel())
															.fireTableRowsUpdated(0, update_issue_keys.size() - 1);
												}
												issues_table.repaint();
												issues.getContentPane().repaint();
												issues.repaint();

												issues.repaint();

											}
										}
									}
								});

							} else {
								JOptionPane.showMessageDialog(null, "Unable to connect to server.");

							}

							issues.repaint();
						}
					});

					issues.getContentPane().add(btnSync);
					issues.getContentPane().add(btnSection);
					issues.getContentPane().add(btnUnpublished);
					Set<Long> author_keys = author_storage.keySet();
					ArrayList<Long> author_list = new ArrayList<Long>();
					String listData[] = new String[author_keys.size()];
					int j = 0;
					DefaultListModel<String> listModel = new DefaultListModel<String>();
					for (long key : author_keys) {
						listModel.addElement(author_storage.get(key).getFull_name());
						listData[j] = author_storage.get(key).getFull_name();
						author_list.add(key);
						j = j + 1;
					}
					;

					// Create a new listbox control

					JList<String> listbox = new JList<String>();
					listbox.setModel(listModel);
					listbox.setBounds(15, 40, 320, 25 * author_list.size());
					listbox.setBackground(Color.white);
					listbox.setVisible(true);
					JButton btnAddAuthor = new JButton("Add new Author");

					btnAddAuthor.setBounds(15, 15, 320, 25);

					JScrollPane scrollPane = new JScrollPane();

					scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
					scrollPane.setBounds(20, 167, width - 40, height - 305);
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
							if (colIndex > -1 && rowIndex > -1) {
								try {
									tip = getValueAt(rowIndex, colIndex).toString();
								} catch (RuntimeException e1) {
									// catch null pointer exception if mouse is
									// over
									// an
									// empty line
									e1.printStackTrace();
								}

								return tip;
							} else {
								return "";
							}
						}
					};
					// reference:
					// https://svn.java.net/svn/swinglabs-demos~svn/trunk/src/java/org/jdesktop/demo/sample/
					final JTextField filter = new JTextField("");
					filter.setBounds(148, 139, 120, 25);

					final JButton search = new JButton("Search");
					final JButton clear = new JButton("Clear");
					search.setBounds(268, 139, 90, 25);
					clear.setBounds(361, 139, 65, 25);
					issues.getContentPane().add(filter);
					issues.getContentPane().add(search);
					issues.getContentPane().add(clear);
					filter.setAction(new AbstractAction("Search") {
						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

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
						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

						public void actionPerformed(ActionEvent e) {

							String searchString = filter.getText().trim();
							if (searchString.length() > 0) {
								issues_table.setRowFilter(RowFilters.regexFilter(0, searchString));
							}
						}
					});
					clear.setAction(new AbstractAction("Clear") {
						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

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
					internetCheck.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT | Font.ITALIC, 12));
					internetCheck.setBackground(Color.lightGray);
					internetCheck.setBounds(width - 85, 85, 65, 22);
					internetCheck.setForeground(new Color(255, 255, 255));
					internetCheck.setAlignment(1);
					issues.getContentPane().add(internetCheck);

					ActionListener taskPerformer1 = new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							issues.repaint();
							issues.getContentPane().repaint();

							if (status_online()) {
								internetCheck.setBackground(Color.lightGray);
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
					new DefaultTableModel(rows, columnNames);
					Action delete = new AbstractAction() {
						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

						public void actionPerformed(ActionEvent e) {
							JTable table = (JTable) e.getSource();
							int modelRow = Integer.valueOf(e.getActionCommand());
							// JOptionPane.showMessageDialog(null, "Deleted");
							int reply = JOptionPane.showConfirmDialog(null,
									"Are you sure you want to delete this issue?", "Delete ?",
									JOptionPane.YES_NO_OPTION);
							if (reply == JOptionPane.YES_OPTION) {

								int issue_row = table.getSelectedRow();
								long selected_issue = (long) table.getModel()
										.getValueAt(table.convertRowIndexToModel(issue_row), 0);
								if (issue_screens.get(selected_issue).isVisible()
										|| !(issue_screens.get(selected_issue) == null)) {
									ConcurrentHashMap<Long, JFrame> opened = article_screens.get(selected_issue);
									Set<Long> ids = opened.keySet();
									System.out.println("Issue: " + Long.toString(selected_issue));
									for (long id : ids) {
										System.out.println(id);
										JFrame art_window = opened.get(id);
										if (art_window.isVisible() || !(art_window == null)) {
											art_window.dispose();
										}
									}
									Issue current_issue = issue_storage.get((long) selected_issue);
									current_issue.setDeleted(true);
									current_issue.setSync(true);
									ConcurrentHashMap<Long, Article> articles = current_issue.getArticles_list();
									Set<Long> art_keys = articles.keySet();
									for (long art : art_keys) {
										Article current_article = articles.get((long) art);
										current_article.setDeleted(true);
										current_article.setSync(true);
										articles.put((long) art, current_article);
									}
									current_issue.setArticles_list(articles);
									issue_storage.put((long) selected_issue, current_issue);
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
						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

						public void actionPerformed(ActionEvent e) {
							JTable table = (JTable) e.getSource();
							Integer.valueOf(e.getActionCommand());
							int issue_row = table.getSelectedRow();
							long selected_issue = (long) table.getModel()
									.getValueAt(table.convertRowIndexToModel(issue_row), 0);
							if (!issue_screens.get(selected_issue).isVisible()) {
								issue(selected_issue);
							}
							// /
							// ((DefaultTableModel)table.getModel()).removeRow(modelRow);
						}
					};
					Action edit = new AbstractAction() {
						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

						public void actionPerformed(ActionEvent e) {
							JTable table = (JTable) e.getSource();
							Integer.valueOf(e.getActionCommand());
							int issue_row = table.getSelectedRow();
							long selected_issue = (long) table.getModel()
									.getValueAt(table.convertRowIndexToModel(issue_row), 0);

							edit_issue(selected_issue);

							// /
							// ((DefaultTableModel)table.getModel()).removeRow(modelRow);
						}
					};
					ButtonColumn buttonColumn = new ButtonColumn(issues_table, view, 7);
					ButtonColumn buttonColumn2 = new ButtonColumn(issues_table, edit, 8);
					ButtonColumn buttonColumn3 = new ButtonColumn(issues_table, delete, 9);

					JButton btnSettings = new JButton("Settings");
					btnSettings.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							settings();
						}
					});
					btnSettings.setBounds(15, 82, 90, 29);
					issues.getContentPane().add(btnSettings);

					/*
					 * JButton pi = new JButton("API"); pi.addActionListener(new
					 * ActionListener() { public void
					 * actionPerformed(ActionEvent e) { api(true); } });
					 * btnApi.setBounds(103, 20, 90, 29);
					 * issues.getContentPane().add(btnApi);
					 */
					ImageIcon db_icon = new ImageIcon(String.format("%s/required_files/%s", directory, "db_xxs.png"));
					JButton btnSaveData = new JButton(db_icon);
					btnSaveData.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 24));
					btnSaveData.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							database_save();
						}
					});
					btnSaveData.setBounds(45, height - 105, 70, 40);
					issues.getContentPane().add(btnSaveData);

					JLabel lblUpdateDb = new JLabel("Update Local Database");
					lblUpdateDb.setForeground(Color.BLACK);
					lblUpdateDb.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 11));
					lblUpdateDb.setHorizontalAlignment(SwingConstants.CENTER);
					lblUpdateDb.setBounds(14, height - 120, 140, 15);
					issues.getContentPane().add(lblUpdateDb);

					JLabel lblIssue = new JLabel("Dashboard");
					lblIssue.setBackground(new Color(46, 46, 46));
					lblIssue.setForeground(new Color(255, 255, 255));
					lblIssue.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 26));
					lblIssue.setBounds(width - 180, 26, 280, 40);
					lblIssue.setOpaque(true);
					issues.getContentPane().add(lblIssue);
					JLabel lblArticles = new JLabel("Issues");
					lblArticles.setBackground(new Color(213, 213, 213));
					lblArticles.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 24));
					lblArticles.setForeground(new Color(0, 0, 0));
					lblArticles.setBounds(30, 133, 125, 30);
					lblArticles.setOpaque(true);
					issues.getContentPane().add(lblArticles);
					JPanel panel = new JPanel();
					panel.setBackground(new Color(46, 46, 46));
					panel.setLayout(null);
					panel.setBounds(-5, 0, width + 100, 70);
					ImageIcon icon = new ImageIcon(
							String.format("%s/required_files/%s", directory, "toru-ui-logo.png"));
					JLabel logo = new JLabel(icon);
					logo.setBounds(10, 20, 140, 40);
					logo.setBackground(new Color(46, 46, 46));
					panel.add(logo);
					panel.repaint();
					logo.repaint();
					issues.getContentPane().add(panel);

					Panel panel_1 = new Panel();
					panel_1.setBackground(new Color(25, 25, 25));
					panel_1.setBounds(-5, 70, width + 100, 6);
					issues.getContentPane().add(panel_1);

					issues.getContentPane().repaint();
					JButton btnAdd = new JButton("Add");
					btnAdd.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							add_issue();
							/*
							 * i_id = i_id + 1;
							 * 
							 * // JOptionPane.showMessageDialog(null,
							 * "Deleted"); Date date = new Date();
							 * SimpleDateFormat sdf = new
							 * SimpleDateFormat("yyyy/MM/dd"); Issue issue = new
							 * Issue(i_id, "title", 1, 1, 2015, "title", 1, 1,
							 * 2015, date);
							 * 
							 * list_issues.put(i_id, 1); issue_screens.put(i_id,
							 * new JFrame()); article_screens.put(i_id, new
							 * ConcurrentHashMap<Integer, JFrame>());
							 * issue_storage.put(i_id, issue); Object[] new_row
							 * = { i_id, "title", 1, 1, 2015, sdf.format(date),
							 * "View", "Edit", "Delete" };
							 * 
							 * ((DefaultTableModel)
							 * issues_table.getModel()).addRow(new_row);
							 * issues_table.repaint();
							 */
						}
					});
					btnAdd.setBounds(width - 150, 139, 117, 25);
					issues.getContentPane().add(btnAdd);
					buttonColumn.setMnemonic(KeyEvent.VK_D);
					buttonColumn2.setMnemonic(KeyEvent.VK_D);
					buttonColumn3.setMnemonic(KeyEvent.VK_D);

					final JButton btnDashboard = new JButton("Dashboard");
					btnDashboard.setBounds(width - 135, height - 80, 120, 29);
					btnDashboard.setEnabled(false);
					btnDashboard.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							dashboard();
						}
					});
					issues.getContentPane().add(btnDashboard);

					issues.repaint();
					issues.getContentPane().repaint();
					issues.setVisible(true);
				}
			} catch (Exception e) {
				e.printStackTrace();
				dashboard();
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
			int move = 52;
			height_small = (int) (680 - (680 * (5 / 100)));
			final JFrame edit_issue = new JFrame();
			edit_issue.setResizable(false);
			edit_issue.setVisible(true);
			issue_screens.put(i_id, edit_issue);
			edit_issue.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			edit_issue.getContentPane().setBackground(new Color(170, 170, 170));
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

			final JTextField title = new JTextField();
			title.setBounds(100, 218 - move, 250, 26);
			edit_issue.getContentPane().add(title);
			title.setColumns(4);

			JLabel lblTitleText = new JLabel("Title");
			lblTitleText.setForeground(new Color(255, 255, 255));
			lblTitleText.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitleText.setBounds(100, 200 - move, 250, 16);
			edit_issue.getContentPane().add(lblTitleText);
			SpinnerModel number_model = new SpinnerNumberModel(0, 0, 1000, 1);

			SpinnerModel volume_model = new SpinnerNumberModel(0, 0, 1000, 1);
			final JSpinner volume = new JSpinner(volume_model);
			volume.setBounds(100, 270 - move, 250, 26);
			edit_issue.getContentPane().add(volume);
			JLabel lblvolume = new JLabel("Volume");
			lblvolume.setHorizontalAlignment(SwingConstants.CENTER);
			lblvolume.setForeground(new Color(255, 255, 255));
			lblvolume.setBounds(100, 250 - move, 250, 16);
			edit_issue.getContentPane().add(lblvolume);

			final JSpinner number = new JSpinner(number_model);
			number.setAlignmentX(JSpinner.LEFT_ALIGNMENT);
			number.setBounds(100, 317 - move, 250, 26);
			edit_issue.getContentPane().add(number);
			JLabel lblnumber = new JLabel("Number");
			lblnumber.setHorizontalAlignment(SwingConstants.CENTER);
			lblnumber.setForeground(new Color(255, 255, 255));
			lblnumber.setBounds(100, 300 - move, 250, 16);
			edit_issue.getContentPane().add(lblnumber);

			Date currentYear = Calendar.getInstance().getTime();
			SpinnerDateModel year_model = new SpinnerDateModel(currentYear, null, null, Calendar.YEAR);
			final JSpinner year = new JSpinner(year_model);
			year.setEditor(new JSpinner.DateEditor(year, "YYYY"));
			year.setBounds(100, 364 - move, 250, 26);
			edit_issue.getContentPane().add(year);

			JLabel lblyear = new JLabel("Year");
			lblyear.setHorizontalAlignment(SwingConstants.CENTER);
			lblyear.setForeground(new Color(255, 255, 255));
			lblyear.setBounds(100, 347 - move, 250, 16);
			edit_issue.getContentPane().add(lblyear);

			JLabel lblDateAccepted = new JLabel("Date Submitted");
			lblDateAccepted.setHorizontalAlignment(SwingConstants.CENTER);
			lblDateAccepted.setForeground(new Color(255, 255, 255));
			lblDateAccepted.setBounds(80, 394 - move, width_small - 161, 16);
			edit_issue.getContentPane().add(lblDateAccepted);

			final JXDatePicker datePicker = new JXDatePicker();
			datePicker.setFormats(sdf);

			datePicker.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println(datePicker.getDate());
				}
			});
			;
			datePicker.setBounds(100, 410 - move, width_small - 200, 30);
			// panel.add(label);
			edit_issue.getContentPane().add(datePicker);
			JLabel lblDatePublished = new JLabel("Date Published");
			lblDatePublished.setHorizontalAlignment(SwingConstants.CENTER);
			lblDatePublished.setForeground(new Color(255, 255, 255));
			lblDatePublished.setBounds(80, 441 - move, width_small - 161, 16);
			edit_issue.getContentPane().add(lblDatePublished);

			final JXDatePicker datePickerPublished = new JXDatePicker();
			datePickerPublished.setFormats(sdf);

			datePickerPublished.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println(datePickerPublished.getDate());
				}
			});
			;
			datePickerPublished.setBounds(100, 460 - move, width_small - 200, 30);
			// panel.add(label);
			edit_issue.getContentPane().add(datePickerPublished);
			JLabel lblShowDisplay = new JLabel("---- Display Values ----");
			lblShowDisplay.setForeground(new Color(255, 255, 255));
			lblShowDisplay.setHorizontalAlignment(SwingConstants.CENTER);
			lblShowDisplay.setBounds(340, 170 - move, 250, 16);
			edit_issue.getContentPane().add(lblShowDisplay);

			final JCheckBox published_check = new JCheckBox("", true);

			published_check.setBounds(425, height_small - 165 - move, 100, 26);
			edit_issue.getContentPane().add(published_check);
			JLabel lblPublished = new JLabel("Published");
			lblPublished.setForeground(new Color(255, 255, 255));
			lblPublished.setHorizontalAlignment(SwingConstants.CENTER);
			lblPublished.setBounds(340, height_small - 161 - move, 100, 16);
			edit_issue.getContentPane().add(lblPublished);

			final JCheckBox current_check = new JCheckBox();
			current_check.setBounds(425, height_small - 145 - move, 100, 26);
			edit_issue.getContentPane().add(current_check);
			JLabel lblCurrent = new JLabel("Current");
			lblCurrent.setForeground(new Color(255, 255, 255));
			lblCurrent.setHorizontalAlignment(SwingConstants.CENTER);
			lblCurrent.setBounds(340, height_small - 141 - move, 100, 16);
			edit_issue.getContentPane().add(lblCurrent);

			final JCheckBox access_status_check = new JCheckBox();
			access_status_check.setBounds(425, height_small - 125 - move, 100, 26);
			edit_issue.getContentPane().add(access_status_check);
			JLabel lblStatus = new JLabel("Access Status");
			lblStatus.setForeground(new Color(255, 255, 255));
			lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
			lblStatus.setBounds(305, height_small - 121 - move, 150, 16);
			edit_issue.getContentPane().add(lblStatus);

			final JCheckBox show_title = new JCheckBox("", true);
			show_title.setBounds(459, 218 - move, 250, 26);
			edit_issue.getContentPane().add(show_title);

			JLabel lblShowTitleText = new JLabel("Show Title");
			lblShowTitleText.setForeground(new Color(255, 255, 255));
			lblShowTitleText.setHorizontalAlignment(SwingConstants.CENTER);
			lblShowTitleText.setBounds(340, 200 - move, 250, 16);
			edit_issue.getContentPane().add(lblShowTitleText);

			final JCheckBox show_volume = new JCheckBox("", true);
			show_volume.setBounds(459, 270 - move, 250, 26);
			edit_issue.getContentPane().add(show_volume);

			JLabel lblShowVolume = new JLabel("Show Volume");
			lblShowVolume.setForeground(new Color(255, 255, 255));
			lblShowVolume.setHorizontalAlignment(SwingConstants.CENTER);
			lblShowVolume.setBounds(340, 250 - move, 250, 16);
			edit_issue.getContentPane().add(lblShowVolume);

			final JCheckBox show_number = new JCheckBox("", true);
			show_number.setBounds(459, 317 - move, 250, 26);
			edit_issue.getContentPane().add(show_number);

			JLabel lblShowNumber = new JLabel("Show Number");
			lblShowNumber.setForeground(new Color(255, 255, 255));
			lblShowNumber.setHorizontalAlignment(SwingConstants.CENTER);
			lblShowNumber.setBounds(340, 300 - move, 250, 16);
			edit_issue.getContentPane().add(lblShowNumber);

			final JCheckBox show_year = new JCheckBox("", true);
			show_year.setBounds(459, 364 - move, 250, 26);
			edit_issue.getContentPane().add(show_year);

			JLabel lblShowYear = new JLabel("Show Year");
			lblShowYear.setForeground(new Color(255, 255, 255));
			lblShowYear.setHorizontalAlignment(SwingConstants.CENTER);
			lblShowYear.setBounds(340, 347 - move, 250, 16);
			edit_issue.getContentPane().add(lblShowYear);

			JButton btnSubmit = new JButton("Create");

			btnSubmit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Boolean validation = true;
					int entered_volume = 0;
					int entered_number = 0;
					Date entered_year = new Date();
					try {

						entered_volume = (Integer) volume.getValue();
						entered_number = (Integer) number.getValue();
						entered_year = (Date) year.getValue();

						number.setBackground(new Color(255, 255, 255));
						number.setForeground(new Color(0, 0, 0));
						volume.setBackground(new Color(255, 255, 255));
						volume.setForeground(new Color(0, 0, 0));
						year.setBackground(new Color(255, 255, 255));
						year.setForeground(new Color(0, 0, 0));
					} catch (Exception ex) {
						ex.printStackTrace();
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

						sdf.format(datePicker.getDate());
						// sdf.format(datePickerPublished.getDate());

					} catch (Exception ex) {
						validation = false;
						JOptionPane.showMessageDialog(null, "Use dates from calendar for fields: Date Submitted");
					}
					if (published_check.isSelected()) {
						try {

							// sdf.format(datePicker.getDate());
							sdf.format(datePickerPublished.getDate());

						} catch (Exception ex) {
							validation = false;
							JOptionPane.showMessageDialog(null,
									"Use dates from calendar for field 'Date Published' or Untick 'Published'");
						}
					}
					if (validation) {
						i_id++;
						System.out.println("NEW ID " + i_id);
						SimpleDateFormat year_sdf = new SimpleDateFormat("yyyy");
						Issue issue = new Issue(i_id, title.getText(), entered_volume, entered_number,
								Integer.parseInt(year_sdf.format(entered_year)), datePicker.getDate());
						issue.setShow_title(show_title.isSelected() == true ? 1 : 0);
						issue.setShow_volume(show_volume.isSelected() == true ? 1 : 0);
						issue.setShow_year(show_year.isSelected() == true ? 1 : 0);
						issue.setShow_number(show_number.isSelected() == true ? 1 : 0);
						try {
							String publ = sdf.format(datePickerPublished.getDate());
							issue.setDate_published(datePickerPublished.getDate());
						} catch (Exception se) {
							// issue.setDate_published();
						}
						issue.setPublished(published_check.isSelected() == true ? 1 : 0);
						issue.setCurrent(current_check.isSelected() == true ? 1 : 0);
						issue.setAccess_status(access_status_check.isSelected() == true ? 1 : 0);
						issue.setSync(true);
						issue.setJournal(journal_storage.get(Long.parseLong(app_settings.get("journal_id"))));
						// JOptionPane.showMessageDialog(null, "Deleted");

						list_issues.put(i_id, (long) 1);
						issue_screens.put(i_id, new JFrame());
						article_screens.put(i_id, new ConcurrentHashMap<Long, JFrame>());
						issue_storage.put(i_id, issue);
						Object[] new_row = { i_id, show_title.isSelected() == true ? title.getText() : "Hidden",
								show_volume.isSelected() == true ? (Integer) volume.getValue() : "Hidden",
								show_number.isSelected() == true ? (Integer) number.getValue() : "Hidden",
								show_year.isSelected() == true ? Integer.parseInt(year_sdf.format(entered_year))
										: "Hidden",
								datePicker.getDate() == null ? "/" : sdf.format(datePicker.getDate()),
								datePickerPublished.getDate() == null ? "/" : sdf.format(datePickerPublished.getDate()),
								"View", "Edit", "Delete" };

						((DefaultTableModel) issues_table.getModel()).addRow(new_row);
						issues_table.repaint();
						edit_issue.dispose();
						issues.dispose();
						dashboard();
					}

				}
			});
			if (height_small - 150 > 300) {
				btnSubmit.setBounds(((width_small / 3) * 2) / 2, height_small - 69 - move, width_small / 3, 29);
			} else {
				btnSubmit.setBounds(((width_small / 3) * 2) / 2, 339 - move, width_small / 3, 29);
			}

			edit_issue.getContentPane().add(btnSubmit);

			JLabel lblIssue = new JLabel("Add Issue");
			lblIssue.setBackground(new Color(46, 46, 46));
			lblIssue.setForeground(new Color(255, 255, 255));
			lblIssue.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 26));
			lblIssue.setBounds(width_small - 150, 26, 280, 40);
			lblIssue.setOpaque(true);
			edit_issue.getContentPane().add(lblIssue);

			JPanel panel = new JPanel();
			panel.setBackground(new Color(46, 46, 46));
			panel.setLayout(null);
			panel.setBounds(0, 0, width, 70);
			ImageIcon icon = new ImageIcon(String.format("%s/required_files/%s", directory, "toru-ui-logo.png"));
			JLabel logo = new JLabel(icon);
			logo.setBounds(10, 20, 140, 40);
			logo.setBackground(new Color(46, 46, 46));
			panel.add(logo);
			panel.repaint();
			logo.repaint();
			edit_issue.getContentPane().add(panel);

			Panel panel_1 = new Panel();
			panel_1.setBackground(new Color(25, 25, 25));
			panel_1.setBounds(0, 70, width, 6);
			edit_issue.getContentPane().add(panel_1);
			edit_issue.repaint();
			edit_issue.setVisible(true);// making the frame visible
			issue_screens.put(i_id, edit_issue);

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

				int move = 52;
				final JFrame edit_issue = new JFrame();
				edit_issue.setVisible(true);
				edit_issue.setResizable(false);
				issue_screens.put(issue_id, edit_issue);
				edit_issue.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				edit_issue.getContentPane().setBackground(new Color(170, 170, 170));
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

				final JTextField title = new JTextField();
				title.setBounds(100, 218 - move, 250, 26);
				title.setText(current_issue.getTitle());
				edit_issue.getContentPane().add(title);
				title.setColumns(4);

				JLabel lblTitleText = new JLabel("Title");
				lblTitleText.setForeground(new Color(255, 255, 255));
				lblTitleText.setHorizontalAlignment(SwingConstants.CENTER);
				lblTitleText.setBounds(100, 200 - move, 250, 16);
				edit_issue.getContentPane().add(lblTitleText);
				SpinnerModel number_model = new SpinnerNumberModel(current_issue.getNumber(), 0, 1000, 1);

				SpinnerModel volume_model = new SpinnerNumberModel(current_issue.getVolume(), 0, 1000, 1);
				final JSpinner volume = new JSpinner(volume_model);
				volume.setBounds(100, 270 - move, 250, 26);
				edit_issue.getContentPane().add(volume);
				JLabel lblvolume = new JLabel("Volume");
				lblvolume.setHorizontalAlignment(SwingConstants.CENTER);
				lblvolume.setForeground(new Color(255, 255, 255));
				lblvolume.setBounds(100, 250 - move, 250, 16);
				edit_issue.getContentPane().add(lblvolume);

				final JSpinner number = new JSpinner(number_model);
				number.setBounds(100, 317 - move, 250, 26);
				edit_issue.getContentPane().add(number);
				JLabel lblnumber = new JLabel("Number");
				lblnumber.setHorizontalAlignment(SwingConstants.CENTER);
				lblnumber.setForeground(new Color(255, 255, 255));
				lblnumber.setBounds(100, 300 - move, 250, 16);
				edit_issue.getContentPane().add(lblnumber);

				SimpleDateFormat year_sdf = new SimpleDateFormat("yyyy");
				Date currentYear = new Date();
				try {
					currentYear = year_sdf.parse(Integer.toString(current_issue.getYear()));
				} catch (java.text.ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				SpinnerDateModel year_model = new SpinnerDateModel(currentYear, null, null, Calendar.YEAR);
				final JSpinner year = new JSpinner(year_model);
				year.setEditor(new JSpinner.DateEditor(year, "YYYY"));
				year.setBounds(100, 364 - move, 250, 26);
				edit_issue.getContentPane().add(year);
				JLabel lblyear = new JLabel("Year");
				lblyear.setHorizontalAlignment(SwingConstants.CENTER);
				lblyear.setForeground(new Color(255, 255, 255));
				lblyear.setBounds(100, 347 - move, 250, 16);
				edit_issue.getContentPane().add(lblyear);

				JLabel lblDateAccepted = new JLabel("Date Submitted");
				lblDateAccepted.setHorizontalAlignment(SwingConstants.CENTER);
				lblDateAccepted.setForeground(new Color(255, 255, 255));
				lblDateAccepted.setBounds(80, 394 - move, width_small - 161, 16);
				edit_issue.getContentPane().add(lblDateAccepted);

				final JXDatePicker datePicker = new JXDatePicker();
				datePicker.setFormats(sdf);

				datePicker.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println(datePicker.getDate());
					}
				});
				;
				datePicker.setBounds(100, 410 - move, width_small - 200, 30);

				datePicker.setDate(current_issue.getDate_accepted());
				// panel.add(label);
				edit_issue.getContentPane().add(datePicker);
				JLabel lblDatePublished = new JLabel("Date Published");
				lblDatePublished.setHorizontalAlignment(SwingConstants.CENTER);
				lblDatePublished.setForeground(new Color(255, 255, 255));
				lblDatePublished.setBounds(80, 441 - move, width_small - 161, 16);
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
				datePickerPublished.setBounds(100, 460 - move, width_small - 200, 30);
				// panel.add(label);
				edit_issue.getContentPane().add(datePickerPublished);

				JLabel lblShowDisplay = new JLabel("---- Display Values ----");
				lblShowDisplay.setForeground(new Color(255, 255, 255));
				lblShowDisplay.setHorizontalAlignment(SwingConstants.CENTER);
				lblShowDisplay.setBounds(340, 170 - move, 250, 16);
				edit_issue.getContentPane().add(lblShowDisplay);

				final JCheckBox published_check = new JCheckBox("", current_issue.getPublished() == 1 ? true : false);

				published_check.setBounds(425, height_small - 165 - move, 100, 26);
				edit_issue.getContentPane().add(published_check);
				JLabel lblPublished = new JLabel("Published");
				lblPublished.setForeground(new Color(255, 255, 255));
				lblPublished.setHorizontalAlignment(SwingConstants.CENTER);
				lblPublished.setBounds(340, height_small - 161 - move, 100, 16);
				edit_issue.getContentPane().add(lblPublished);

				final JCheckBox current_check = new JCheckBox("", current_issue.getCurrent() == 1 ? true : false);
				current_check.setBounds(425, height_small - 145 - move, 100, 26);
				edit_issue.getContentPane().add(current_check);
				JLabel lblCurrent = new JLabel("Current");
				lblCurrent.setForeground(new Color(255, 255, 255));
				lblCurrent.setHorizontalAlignment(SwingConstants.CENTER);
				lblCurrent.setBounds(340, height_small - 141 - move, 100, 16);
				edit_issue.getContentPane().add(lblCurrent);

				final JCheckBox access_status_check = new JCheckBox("",
						current_issue.getAccess_status() == 1 ? true : false);
				access_status_check.setBounds(425, height_small - 125 - move, 100, 26);
				edit_issue.getContentPane().add(access_status_check);
				JLabel lblStatus = new JLabel("Access Status");
				lblStatus.setForeground(new Color(255, 255, 255));
				lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
				lblStatus.setBounds(305, height_small - 121 - move, 150, 16);
				edit_issue.getContentPane().add(lblStatus);
				final JCheckBox show_title = new JCheckBox("", current_issue.getShow_title() >= 1 ? true : false);
				show_title.setBounds(459, 218 - move, 250, 26);
				edit_issue.getContentPane().add(show_title);

				JLabel lblShowTitleText = new JLabel("Show Title");
				lblShowTitleText.setForeground(new Color(255, 255, 255));
				lblShowTitleText.setHorizontalAlignment(SwingConstants.CENTER);
				lblShowTitleText.setBounds(340, 200 - move, 250, 16);
				edit_issue.getContentPane().add(lblShowTitleText);

				final JCheckBox show_volume = new JCheckBox("", current_issue.getShow_volume() >= 1 ? true : false);
				show_volume.setBounds(459, 270 - move, 250, 26);
				edit_issue.getContentPane().add(show_volume);

				JLabel lblShowVolume = new JLabel("Show Volume");
				lblShowVolume.setForeground(new Color(255, 255, 255));
				lblShowVolume.setHorizontalAlignment(SwingConstants.CENTER);
				lblShowVolume.setBounds(340, 250 - move, 250, 16);
				edit_issue.getContentPane().add(lblShowVolume);

				final JCheckBox show_number = new JCheckBox("", current_issue.getShow_number() >= 1 ? true : false);
				show_number.setBounds(459, 317 - move, 250, 26);
				edit_issue.getContentPane().add(show_number);

				JLabel lblShowNumber = new JLabel("Show Number");
				lblShowNumber.setForeground(new Color(255, 255, 255));
				lblShowNumber.setHorizontalAlignment(SwingConstants.CENTER);
				lblShowNumber.setBounds(340, 300 - move, 250, 16);
				edit_issue.getContentPane().add(lblShowNumber);

				final JCheckBox show_year = new JCheckBox("", current_issue.getShow_year() >= 1 ? true : false);
				show_year.setBounds(459, 364 - move, 250, 26);
				edit_issue.getContentPane().add(show_year);

				JLabel lblShowYear = new JLabel("Show Year");
				lblShowYear.setForeground(new Color(255, 255, 255));
				lblShowYear.setHorizontalAlignment(SwingConstants.CENTER);
				lblShowYear.setBounds(340, 347 - move, 250, 16);
				edit_issue.getContentPane().add(lblShowYear);
				JButton btnSubmit = new JButton("Save");
				Action actionSubmit = new AbstractAction() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void actionPerformed(ActionEvent e) {
						Boolean validation = true;
						int entered_volume = 0;
						int entered_number = 0;
						int entered_year = 0;

						SimpleDateFormat year_sdf = new SimpleDateFormat("yyyy");
						try {

							entered_volume = (Integer) volume.getValue();
							entered_number = (Integer) number.getValue();
							entered_year = Integer.parseInt(year_sdf.format(year.getValue()));

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

							sdf.format(datePicker.getDate());
							// sdf.format(datePickerPublished.getDate());

						} catch (Exception ex) {
							validation = false;
							JOptionPane.showMessageDialog(null, "Use dates from calendar for fields: Date Submitted");
						}
						if (published_check.isSelected()) {
							try {

								// sdf.format(datePicker.getDate());
								sdf.format(datePickerPublished.getDate());

							} catch (Exception ex) {
								validation = false;
								JOptionPane.showMessageDialog(null,
										"Use dates from calendar for field 'Date Published' or Untick 'Published'");
							}
						}
						if (validation) {

							current_issue.setTitle(title.getText());
							current_issue.setVolume(entered_volume);
							current_issue.setNumber(entered_number);
							current_issue.setYear(entered_year);

							try {
								String publ = sdf.format(datePickerPublished.getDate());
								current_issue.setDate_published(datePickerPublished.getDate());
							} catch (Exception se) {
								// issue.setDate_published();
								current_issue.setDate_published(null);
							}
							current_issue.setDate_accepted(datePicker.getDate());
							current_issue.setShow_title(show_title.isSelected() == true ? 1 : 0);
							current_issue.setShow_volume(show_volume.isSelected() == true ? 1 : 0);
							current_issue.setShow_year(show_year.isSelected() == true ? 1 : 0);
							current_issue.setShow_number(show_number.isSelected() == true ? 1 : 0);
							edit_issue.dispose();
							current_issue.setSync(true);
							issue_storage.put(issue_id, current_issue);
							issues.dispose();
							dashboard();
						}

					}
				};
				/*
				 * title.addActionListener(actionSubmit);
				 * volume.addChangeListener((ChangeListener) actionSubmit);
				 * number.addChangeListener((ChangeListener) actionSubmit);
				 * year.addActionListener(actionSubmit);
				 * datePicker.addActionListener(actionSubmit);
				 */
				btnSubmit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Boolean validation = true;
						int entered_volume = 0;
						int entered_number = 0;
						int entered_year = 0;
						try {

							entered_volume = (Integer) volume.getValue();
							entered_number = (Integer) number.getValue();
							entered_year = Integer.parseInt(year_sdf.format(year.getValue()));

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

							sdf.format(datePicker.getDate());
							// sdf.format(datePickerPublished.getDate());

						} catch (Exception ex) {
							validation = false;
							JOptionPane.showMessageDialog(null, "Use dates from calendar for fields: Date Submitted");
						}
						if (published_check.isSelected()) {
							try {

								// sdf.format(datePicker.getDate());
								sdf.format(datePickerPublished.getDate());

							} catch (Exception ex) {
								validation = false;
								JOptionPane.showMessageDialog(null,
										"Use dates from calendar for field 'Date Published' or Untick 'Published'");
							}
						}
						if (validation) {

							current_issue.setTitle(title.getText());
							current_issue.setVolume(entered_volume);
							current_issue.setNumber(entered_number);
							current_issue.setYear(entered_year);

							try {
								String publ = sdf.format(datePickerPublished.getDate());
								current_issue.setDate_published(datePickerPublished.getDate());
							} catch (Exception se) {
								// issue.setDate_published();
								current_issue.setDate_published(null);
							}
							current_issue.setDate_accepted(datePicker.getDate());
							current_issue.setShow_title(show_title.isSelected() == true ? 1 : 0);
							current_issue.setShow_volume(show_volume.isSelected() == true ? 1 : 0);
							current_issue.setShow_year(show_year.isSelected() == true ? 1 : 0);
							current_issue.setShow_number(show_number.isSelected() == true ? 1 : 0);
							edit_issue.dispose();

							current_issue.setSync(true);
							issue_storage.put(issue_id, current_issue);
							issues.dispose();
							dashboard();
						}

					}
				});
				if (height_small - 150 > 300) {
					btnSubmit.setBounds(((width_small / 3) * 2) / 2, height_small - 69 - move, width_small / 3, 29);
				} else {
					btnSubmit.setBounds(((width_small / 3) * 2) / 2, 339 - move, width_small / 3, 29);
				}

				edit_issue.getContentPane().add(btnSubmit);

				JLabel lblIssue = new JLabel("Edit Issue");
				lblIssue.setBackground(new Color(46, 46, 46));
				lblIssue.setForeground(new Color(255, 255, 255));
				lblIssue.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 26));
				lblIssue.setBounds(width_small - 150, 26, 280, 40);
				lblIssue.setOpaque(true);
				edit_issue.getContentPane().add(lblIssue);

				JPanel panel = new JPanel();
				panel.setBackground(new Color(46, 46, 46));
				panel.setLayout(null);
				panel.setBounds(0, 0, width, 70);
				ImageIcon icon = new ImageIcon(String.format("%s/required_files/%s", directory, "toru-ui-logo.png"));
				JLabel logo = new JLabel(icon);
				logo.setBounds(10, 20, 140, 40);
				logo.setBackground(new Color(46, 46, 46));
				panel.add(logo);
				panel.repaint();
				logo.repaint();
				edit_issue.getContentPane().add(panel);

				Panel panel_1 = new Panel();
				panel_1.setBackground(new Color(25, 25, 25));
				panel_1.setBounds(0, 70, width, 6);
				edit_issue.getContentPane().add(panel_1);
				edit_issue.repaint();
				edit_issue.setVisible(true);// making the frame visible
				issue_screens.put(issue_id, edit_issue);

			}
		} else {
			login("dashboard");
		}
	}

	public JPanel get_view_section_panel(final long section_id) {
		Section section = section_storage.get(section_id);
		JPanel panelSection = new JPanel();
		panelSection.setBounds(0, 0, 480, 240);
		panelSection.setLayout(null);

		JTextField txtSectionTitle = new JTextField(section.getTitle());
		txtSectionTitle.setBounds(90, 45, 300, 30);
		panelSection.add(txtSectionTitle);
		txtSectionTitle.setColumns(4);
		final JLabel disable_comments = new JLabel(
				String.format("Disable Comments: %s", section.getDisable_comments() == 1 ? true : false));
		disable_comments.setBounds(240, 80, 200, 26);
		panelSection.add(disable_comments);

		final JLabel abstracts_not_required = new JLabel(
				String.format("Abstracts Not Required: %s", section.getAbstracts_not_required() == 1 ? true : false));

		abstracts_not_required.setBounds(240, 106, 240, 26);
		panelSection.add(abstracts_not_required);
		final JLabel editor_restricted = new JLabel(
				String.format("Editor Restricted: %s", section.getEditor_restricted() == 1 ? true : false));

		editor_restricted.setBounds(240, 132, 200, 26);
		panelSection.add(editor_restricted);
		final JLabel hide_title = new JLabel(
				String.format("Hide Title: %s", section.getHide_title() == 1 ? true : false));

		hide_title.setBounds(80, 80, 200, 26);
		panelSection.add(hide_title);
		final JLabel hide_author = new JLabel(
				String.format("Hide Author: %s", section.getHide_author() == 1 ? true : false));

		hide_author.setBounds(80, 106, 200, 26);
		panelSection.add(hide_author);
		final JLabel hide_about = new JLabel(
				String.format("Hide About: %s", section.getHide_about() == 1 ? true : false));

		hide_about.setBounds(80, 132, 200, 26);
		panelSection.add(hide_about);

		final JLabel metadata_indexed = new JLabel(
				String.format("Metadata Indexed: %s", section.getMeta_indexed() == 1 ? true : false));

		metadata_indexed.setBounds(80, 158, 220, 26);
		panelSection.add(metadata_indexed);

		final JLabel metadata_reviewed = new JLabel(
				String.format("Metadata Reviewed: %s", section.getMeta_reviewed() == 1 ? true : false));

		metadata_reviewed.setBounds(80, 184, 220, 26);
		panelSection.add(metadata_reviewed);

		JLabel lblSeq = new JLabel("Seq");
		lblSeq.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeq.setBounds(40, 215, 200, 20);
		panelSection.add(lblSeq);

		JTextField txtSeq = new JTextField(Double.toString(section.getSeq()));
		txtSeq.setEditable(false);
		txtSeq.setBounds(70, 236, 150, 30);
		panelSection.add(txtSeq);
		txtSeq.setColumns(4);

		JLabel lblAbstractCount = new JLabel("Abstract Word Count");
		lblAbstractCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbstractCount.setBounds(240, 215, 200, 20);
		panelSection.add(lblAbstractCount);

		JTextField txtAbstractCount = new JTextField(Long.toString(section.getAbstract_word_count()));
		txtAbstractCount.setBounds(270, 236, 150, 30);
		txtAbstractCount.setEditable(false);
		panelSection.add(txtAbstractCount);
		txtAbstractCount.setColumns(4);

		JLabel lblTitleSection = new JLabel("Title");
		lblTitleSection.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleSection.setBounds(190, 25, 100, 20);
		panelSection.add(lblTitleSection);
		panelSection.setBounds(0, 0, 480, 280);
		panelSection.setSize(new Dimension(480, 280));
		panelSection.setPreferredSize(new Dimension(480, 280));
		panelSection.setVisible(true);

		return panelSection;
	}

	public JPanel get_edit_section_panel(final long section_id) {
		Section section = section_storage.get(section_id);
		JPanel panelSection = new JPanel();
		panelSection.setBounds(0, 0, 480, 240);
		panelSection.setLayout(null);

		JTextField txtSectionTitle = new JTextField(section.getTitle());
		txtSectionTitle.setBounds(90, 45, 300, 30);
		panelSection.add(txtSectionTitle);
		txtSectionTitle.setColumns(4);
		final JCheckBox disable_comments = new JCheckBox("Disable Comments",
				section.getDisable_comments() == 1 ? true : false);

		disable_comments.setBounds(240, 80, 140, 26);
		panelSection.add(disable_comments);

		final JCheckBox abstracts_not_required = new JCheckBox("Abstracts Not Required",
				section.getAbstracts_not_required() == 1 ? true : false);

		abstracts_not_required.setBounds(240, 106, 200, 26);
		panelSection.add(abstracts_not_required);
		final JCheckBox editor_restricted = new JCheckBox("Editor Restricted",
				section.getEditor_restricted() == 1 ? true : false);

		editor_restricted.setBounds(240, 132, 200, 26);
		panelSection.add(editor_restricted);
		final JCheckBox hide_title = new JCheckBox("Hide Title", section.getHide_title() == 1 ? true : false);

		hide_title.setBounds(80, 80, 100, 26);
		panelSection.add(hide_title);
		final JCheckBox hide_author = new JCheckBox("Hide Author", section.getHide_author() == 1 ? true : false);

		hide_author.setBounds(80, 106, 100, 26);
		panelSection.add(hide_author);
		final JCheckBox hide_about = new JCheckBox("Hide About", section.getHide_about() == 1 ? true : false);

		hide_about.setBounds(80, 132, 100, 26);
		panelSection.add(hide_about);

		final JCheckBox metadata_indexed = new JCheckBox("Metadata Indexed",
				section.getMeta_indexed() == 1 ? true : false);

		metadata_indexed.setBounds(80, 158, 180, 26);
		panelSection.add(metadata_indexed);

		final JCheckBox metadata_reviewed = new JCheckBox("Metadata Reviewed",
				section.getMeta_reviewed() == 1 ? true : false);

		metadata_reviewed.setBounds(80, 184, 180, 26);
		panelSection.add(metadata_reviewed);

		JLabel lblSeq = new JLabel("Seq");
		lblSeq.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeq.setBounds(40, 215, 200, 20);
		panelSection.add(lblSeq);

		JTextField txtSeq = new JTextField(Double.toString(section.getSeq()));
		txtSeq.setBounds(70, 236, 150, 30);
		panelSection.add(txtSeq);
		txtSeq.setColumns(4);

		JLabel lblAbstractCount = new JLabel("Abstract Word Count");
		lblAbstractCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbstractCount.setBounds(240, 215, 200, 20);
		panelSection.add(lblAbstractCount);

		JTextField txtAbstractCount = new JTextField(Long.toString(section.getAbstract_word_count()));
		txtAbstractCount.setBounds(270, 236, 150, 30);
		panelSection.add(txtAbstractCount);
		txtAbstractCount.setColumns(4);

		JLabel lblTitleSection = new JLabel("Title");
		lblTitleSection.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleSection.setBounds(190, 25, 100, 20);
		panelSection.add(lblTitleSection);
		panelSection.setBounds(0, 0, 480, 280);
		panelSection.setSize(new Dimension(480, 280));
		panelSection.setPreferredSize(new Dimension(480, 280));
		panelSection.setVisible(true);

		return panelSection;
	}

	public void edit_section(final long section_id) {
		JFrame screen = section_screens.get(section_id);
		screen.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// database_save();
				screen.dispose();
				sections();
			}
		});
		int height_small = 420;
		int width_small = 540;
		screen.setSize(540, 420);
		Section section = section_storage.get(section_id);
		System.out.println(section.getAbstract_word_count());
		SpinnerModel count_model = new SpinnerNumberModel(
				section.getAbstract_word_count() < 0 ? 0 : section.getAbstract_word_count(), 0, 5000, 1);
		SpinnerNumberModel seq_model = new SpinnerNumberModel(section.getSeq() < 0 ? 0 : section.getSeq(), -1000.0,
				1000.0, 0.1);

		screen.setTitle(String.format("Edit Section <%s>", section.getId()));
		JPanel panelSection = new JPanel();
		panelSection.setBounds(0, 0, 540, 240);
		panelSection.setLayout(null);

		JTextField txtSectionTitle = new JTextField(section.getTitle());
		txtSectionTitle.setBounds(90, 45, 300, 30);
		panelSection.add(txtSectionTitle);
		txtSectionTitle.setColumns(4);
		final JCheckBox disable_comments = new JCheckBox("Disable Comments",
				section.getDisable_comments() == 1 ? true : false);

		disable_comments.setBounds(240, 80, 140, 26);
		panelSection.add(disable_comments);

		final JCheckBox abstracts_not_required = new JCheckBox("Abstracts Not Required",
				section.getAbstracts_not_required() == 1 ? true : false);

		abstracts_not_required.setBounds(240, 106, 200, 26);
		panelSection.add(abstracts_not_required);
		final JCheckBox editor_restricted = new JCheckBox("Editor Restricted",
				section.getEditor_restricted() == 1 ? true : false);

		editor_restricted.setBounds(240, 132, 200, 26);
		panelSection.add(editor_restricted);
		final JCheckBox hide_title = new JCheckBox("Hide Title", section.getHide_title() == 1 ? true : false);

		hide_title.setBounds(80, 80, 100, 26);
		panelSection.add(hide_title);
		final JCheckBox hide_author = new JCheckBox("Hide Author", section.getHide_author() == 1 ? true : false);

		hide_author.setBounds(80, 106, 100, 26);
		panelSection.add(hide_author);
		final JCheckBox hide_about = new JCheckBox("Hide About", section.getHide_about() == 1 ? true : false);

		hide_about.setBounds(80, 132, 100, 26);
		panelSection.add(hide_about);

		final JCheckBox metadata_indexed = new JCheckBox("Metadata Indexed",
				section.getMeta_indexed() == 1 ? true : false);

		metadata_indexed.setBounds(80, 158, 180, 26);
		panelSection.add(metadata_indexed);

		final JCheckBox metadata_reviewed = new JCheckBox("Metadata Reviewed",
				section.getMeta_reviewed() == 1 ? true : false);

		metadata_reviewed.setBounds(80, 184, 180, 26);
		panelSection.add(metadata_reviewed);

		JLabel lblSeq = new JLabel("Seq");
		lblSeq.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeq.setBounds(40, 215, 200, 20);
		panelSection.add(lblSeq);

		JSpinner txtSeq = new JSpinner(seq_model);
		txtSeq.setBounds(70, 236, 150, 30);
		panelSection.add(txtSeq);

		JLabel lblAbstractCount = new JLabel("Abstract Word Count");
		lblAbstractCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbstractCount.setBounds(240, 215, 200, 20);
		panelSection.add(lblAbstractCount);

		JSpinner txtAbstractCount = new JSpinner(count_model);
		txtAbstractCount.setBounds(270, 236, 150, 30);
		panelSection.add(txtAbstractCount);

		JLabel lblTitleSection = new JLabel("Title");
		lblTitleSection.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleSection.setBounds(190, 25, 100, 20);
		panelSection.add(lblTitleSection);
		panelSection.setBounds(0, 0, 540, 280);
		panelSection.setSize(new Dimension(540, 280));
		panelSection.setPreferredSize(new Dimension(540, 280));
		panelSection.setVisible(true);

		JButton btnSubmit = new JButton("Save");
		Action actionSubmit = new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {

				panelSection.setVisible(true);
				panelSection.setEnabled(true);
				boolean validation = false;

				String title = txtSectionTitle.getText();
				if (title == null || title.isEmpty() || title.compareTo("") == 0 || title.compareTo(" ") == 0) {
					validation = false;
				} else {
					validation = true;
				}
				try {
					int wordcount = (Integer) txtAbstractCount.getValue();
					double seq = (Double) txtSeq.getValue();
					validation = true;
				} catch (Exception es) {
					validation = false;
				}
				if (validation) {
					section.setAbstract_word_count((Integer) txtAbstractCount.getValue());
					section.setSeq((Double) txtSeq.getValue());
					section.setHide_about(hide_about.isSelected() == true ? 1 : 0);
					section.setHide_author(hide_author.isSelected() == true ? 1 : 0);
					section.setHide_title(hide_title.isSelected() == true ? 1 : 0);
					section.setEditor_restricted(editor_restricted.isSelected() == true ? 1 : 0);
					section.setMeta_indexed(metadata_indexed.isSelected() == true ? 1 : 0);
					section.setMeta_reviewed(metadata_reviewed.isSelected() == true ? 1 : 0);
					section.setDisable_comments(disable_comments.isSelected() == true ? 1 : 0);
					section.setAbstracts_not_required(abstracts_not_required.isSelected() == true ? 1 : 0);
					section.setSync(true);
					section_storage.put(section.getId(), section);
					screen.dispose();
					sections();
				}

			}

		};
		/*
		 * title.addActionListener(actionSubmit);
		 * volume.addChangeListener((ChangeListener) actionSubmit);
		 * number.addChangeListener((ChangeListener) actionSubmit);
		 * year.addActionListener(actionSubmit);
		 * datePicker.addActionListener(actionSubmit);
		 */
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				panelSection.setVisible(true);
				panelSection.setEnabled(true);
				boolean validation = false;

				String title = txtSectionTitle.getText();
				if (title == null || title.isEmpty() || title.compareTo("") == 0 || title.compareTo(" ") == 0) {
					validation = false;
				} else {
					validation = true;
				}
				try {
					int wordcount = (Integer) txtAbstractCount.getValue();
					double seq = (Double) txtSeq.getValue();
					validation = true;
				} catch (Exception es) {
					validation = false;
				}
				if (validation) {
					section.setTitle(title);
					section.setAbstract_word_count((int) (double) txtAbstractCount.getValue());
					section.setSeq((Double) txtSeq.getValue());
					section.setHide_about(hide_about.isSelected() == true ? 1 : 0);
					section.setHide_author(hide_author.isSelected() == true ? 1 : 0);
					section.setHide_title(hide_title.isSelected() == true ? 1 : 0);
					section.setEditor_restricted(editor_restricted.isSelected() == true ? 1 : 0);
					section.setMeta_indexed(metadata_indexed.isSelected() == true ? 1 : 0);
					section.setMeta_reviewed(metadata_reviewed.isSelected() == true ? 1 : 0);
					section.setDisable_comments(disable_comments.isSelected() == true ? 1 : 0);
					section.setAbstracts_not_required(abstracts_not_required.isSelected() == true ? 1 : 0);
					section.setSync(true);
					section_storage.put(section.getId(), section);
					screen.dispose();
					sections();
				}
			}

		});
		btnSubmit.setBounds(25, height_small - 100, 150, 30);

		JButton btnCancel = new JButton("Cancel");

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				screen.dispose();
				sections();

			}

		});
		btnCancel.setBounds(width_small - 170, height_small - 100, 150, 30);

		panelSection.add(btnSubmit);
		panelSection.add(btnCancel);
		screen.getContentPane().add(panelSection);

		screen.repaint();

		screen.setVisible(true);
	}

	public void sections() {
		if (logged_in) {
			if (section_screen == null || !section_screen.isVisible()) {

				final JFrame section_screen = new JFrame();

				section_screen.setResizable(false);
				section_screen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				width = 640;
				height = 480;
				section_screen.setSize(640, 480);
				ConcurrentHashMap<Long, JFrame> issue_articles = new ConcurrentHashMap<Long, JFrame>();
				section_screen.getContentPane().setBackground(new Color(170, 170, 170));

				section_screen.setLocationRelativeTo(null);
				section_screen.setTitle("Sections");
				section_screen.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						// database_save();
					}
				});
				Date current = new Date();

				Set<Long> sect_keys = section_storage.keySet();
				ArrayList<List<Object>> rowData = new ArrayList<List<Object>>();
				int row_num = 0;
				for (long id : sect_keys) {
					Section current_section = section_storage.get(id);
					if (!current_section.isDeleted()) {
						row_num++;
					}
				}
				Object[][] rows = new Object[row_num][11];
				int i = 0;
				for (long id : sect_keys) {

					Section current_section = section_storage.get(id);
					if (!current_section.isDeleted()) {
						ArrayList<Object> data = new ArrayList<Object>();
						section_screens.put(id, new JFrame());
						data.add(Long.toString(current_section.getId()));
						data.add(current_section.getTitle());
						data.add("View");
						data.add("Edit");
						data.add("Delete");
						Object[] row = { current_section.getId(), current_section.getTitle(), "View", "Edit",
								"Delete" };
						rows[i] = row;
						i++;
						rowData.add(data);
					}
				}
				Object columnNames[] = { "ID", "Title", "", "", "" };

				section_screen.getContentPane().setLayout(null);

				DefaultTableModel dtm = new DefaultTableModel(rows, columnNames);

				final JXTable section_table = new JXTable(dtm) {
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
				section_table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

				final JButton btnSync = new JButton("Sync");
				btnSync.setBounds(width - 155, 85, 70, 24);
				btnSync.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JProgressBar progressBar = new JProgressBar();
						Executor article_progress_executor = Executors.newSingleThreadExecutor();
						ExecutorService exec = Executors.newFixedThreadPool(1);

						progressBar.setValue(0);
						progressBar.setStringPainted(true);

						progressBar.setIndeterminate(true);
						progressBar.setBounds(width / 2 - 170, height - 115, 375, 32);
						JLabel progress_msg = new JLabel("Estimated progress per Section:");

						progress_msg.setBounds(width / 2 - 75, height - 147, 200, 40);

						section_screen.add(progress_msg);
						section_screen.add(progressBar);
						section_screen.repaint();
						List<Future<?>> futures = new ArrayList<Future<?>>();

						boolean update_table = false;
						Object[] options_2 = { "Remote Data", "Local Data", "Cancel" };
						int dialogResult2 = JOptionPane.showOptionDialog(null,
								"Would You Like to replace local Section data or update remote Secton data", "Warning",
								JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options_2,
								options_2[0]);


						if (dialogResult2 == 1) {
							Future<?> f = exec.submit(new Runnable() {
								public void run() {

									try {
										get_sections(Long.parseLong(app_settings.get("journal_id")), encoding, false);

									} catch (NumberFormatException e) {

										e.printStackTrace();
									} catch (IllegalStateException e) {

										e.printStackTrace();
									} catch (IOException e) {

										e.printStackTrace();
									}
								}
							});
							futures.add(f);
						} else if (dialogResult2 == 0) {
							Future<?> f = exec.submit(new Runnable() {
								public void run() {
									try {
										update_sections(Long.parseLong(app_settings.get("journal_id")), encoding,
												false);
									} catch (NumberFormatException e1) {

										e1.printStackTrace();
									} catch (IllegalStateException e1) {

										e1.printStackTrace();
									} catch (IOException e1) {

										e1.printStackTrace();
									}
								}
							});
							futures.add(f);
						}

						boolean update_q = update_table;
						article_progress_executor.execute(new Runnable() {
							public void run() {
								for (Future<?> f : futures) {
									try {
										f.get();
									} catch (InterruptedException e) {

										e.printStackTrace();
									} catch (ExecutionException e) {

										e.printStackTrace();
									}

								}
								section_screen.dispose();

								int dialogResult = JOptionPane.showConfirmDialog(null,
										"Save changes to local database?", "Warning", 1);

								if (dialogResult == JOptionPane.YES_OPTION) {
									database_save();
								}
								System.out.println("updatet able" + update_q);
								// change
								if (update_q) {
									Set<Long> keys = section_storage.keySet();
									ArrayList<List<Object>> rowData = new ArrayList<List<Object>>();
									Object[][] rows = new Object[keys.size()][11];
									int num_rows = 0;
									try {
										num_rows = section_table.getRowCount();
									} catch (NullPointerException n_e) {
									}
									if (num_rows != 0) {
										for (int i = num_rows - 1; i >= 0; i--) {
											System.out.println(num_rows);
											((DefaultTableModel) section_table.getModel()).removeRow(i);

											System.out.println("--"
													+ ((DefaultTableModel) section_table.getModel()).getRowCount());
										}
									}
									int i = 0;

									for (long id : keys) {
										Section current_section = section_storage.get(id);
										ArrayList<Object> data = new ArrayList<Object>();
										issue_articles.put(id, new JFrame());

										data.add(Long.toString(current_section.getId()));
										data.add(current_section.getTitle());
										data.add("View");
										data.add("Edit");
										data.add("Delete");
										Object[] row = { current_section.getId(), current_section.getTitle(), "View",
												"Edit", "Delete" };
										rows[i] = row;
										rowData.add(data);
										((DefaultTableModel) section_table.getModel()).insertRow(0, row);
										i++;
										System.out.println(
												"++" + ((DefaultTableModel) section_table.getModel()).getRowCount());

									}
									System.out.println(num_rows);
									try {
										num_rows = ((DefaultTableModel) section_table.getModel()).getRowCount();
									} catch (NullPointerException n_e) {
									}
									System.out.println(":::" + num_rows);
									if (num_rows != 0) {
										((DefaultTableModel) section_table.getModel()).fireTableRowsUpdated(0,
												num_rows - 1);
									}

								}

								section_table.repaint();
								section_screen.getContentPane().repaint();
								section_screen.remove(progress_msg);
								section_screen.remove(progressBar);
								section_screen.repaint();
								section_screen.dispose();
								sections();

							}
						});

					}

				});
				section_screen.getContentPane().add(btnSync);

				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				scrollPane.setBounds(15, height / 16 * 7 - 50, width - 30, (height - 110) - (height / 16 * 7));
				section_screen.getContentPane().add(scrollPane);
				// reference:
				// https://svn.java.net/svn/swinglabs-demos~svn/trunk/src/java/org/jdesktop/demo/sample/
				final JTextField filter = new JTextField("");
				filter.setBounds(150, height / 16 * 7 - 80, 117, 25);

				final JButton search = new JButton("Search");
				final JButton clear = new JButton("Clear");
				search.setBounds(268, height / 16 * 7 - 80, 90, 25);

				clear.setBounds(355, height / 16 * 7 - 80, 90, 25);
				;
				section_screen.getContentPane().add(filter);
				section_screen.getContentPane().add(search);
				section_screen.getContentPane().add(clear);
				filter.setAction(new AbstractAction("Search") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					public void actionPerformed(ActionEvent e) {

						String searchString = filter.getText().trim();
						if (searchString.length() > 0) {
							section_table.setRowFilter(RowFilters.regexFilter(0, searchString));
						} else {
							section_table.setRowFilter(null);
						}
					}
				});
				search.setAction(new AbstractAction("Search") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					public void actionPerformed(ActionEvent e) {

						String searchString = filter.getText().trim();
						if (searchString.length() > 0) {
							section_table.setRowFilter(RowFilters.regexFilter(0, searchString));
						}
					}
				});
				clear.setAction(new AbstractAction("Clear") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					public void actionPerformed(ActionEvent e) {
						section_table.setRowFilter(null);
					}
				});
				section_table.setAutoCreateRowSorter(true);
				scrollPane.setViewportView(section_table);
				section_table.getColumnModel().getColumn(2).setMinWidth(50);
				section_table.getColumnModel().getColumn(3).setMinWidth(40);
				section_table.getColumnModel().getColumn(4).setMinWidth(40);
				section_table.setColumnSelectionAllowed(true);
				section_table.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				section_table.setCellSelectionEnabled(true);
				section_table.setRowHeight(23);
				final Label internetCheck = new Label("  ONLINE");
				internetCheck.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT | Font.ITALIC, 12));
				internetCheck.setBackground(Color.lightGray);
				internetCheck.setAlignment(1);
				internetCheck.setForeground(new Color(255, 255, 255));
				internetCheck.setBounds(width - 85, 86, 65, 22);
				section_screen.getContentPane().add(internetCheck);

				ActionListener taskPerformer1 = new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						if (status_online()) {
							internetCheck.setBackground(Color.lightGray);
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
				new DefaultTableModel(rows, columnNames);
				Action delete = new AbstractAction() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					public void actionPerformed(ActionEvent e) {
						JTable table = (JTable) e.getSource();
						int modelRow = Integer.valueOf(e.getActionCommand());
						// JOptionPane.showMessageDialog(null, "Deleted");
						int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this section?",
								"Delete ?", JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {
							int section_row = table.getSelectedRow();
							long selected_section = (long) table.getModel()
									.getValueAt(table.convertRowIndexToModel(section_row), 0);
							Section current_section = section_storage.get((long) selected_section);
							current_section.setSync(true);
							current_section.setDeleted(true);
							section_storage.put((long) selected_section, current_section);
							if (section_screens.get(selected_section).isVisible()) {
								section_screens.get(selected_section).dispose();
							}
							((DefaultTableModel) table.getModel()).removeRow(modelRow);
							table.repaint();
						}

						// /
						// ((DefaultTableModel)table.getModel()).removeRow(modelRow);
					}
				};
				Action view = new AbstractAction() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					public void actionPerformed(ActionEvent e) {
						JTable table = (JTable) e.getSource();

						section_screen.dispose();
						Integer.valueOf(e.getActionCommand());
						int article_row = table.getSelectedRow();
						long selected_article = (long) table.getModel()
								.getValueAt(table.convertRowIndexToModel(article_row), 0);
						if (section_screens.get(selected_article).isVisible()) {
							section_screens.get(selected_article).dispose();
						}
						int result = JOptionPane.showConfirmDialog(null, get_view_section_panel(selected_article),
								"Add Section", JOptionPane.OK_CANCEL_OPTION);
						if (result == JOptionPane.OK_OPTION) {

						}
						// /
						// ((DefaultTableModel)table.getModel()).removeRow(modelRow);
					}
				};
				Action edit = new AbstractAction() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					public void actionPerformed(ActionEvent e) {
						JTable table = (JTable) e.getSource();
						section_screen.dispose();
						Integer.valueOf(e.getActionCommand());
						int article_row = table.getSelectedRow();
						long selected_article = (long) table.getModel()
								.getValueAt(table.convertRowIndexToModel(article_row), 0);
						if (section_screens.get(selected_article).isVisible()) {
							section_screens.get(selected_article).dispose();
						}
						edit_section(selected_article);
						// /
						// ((DefaultTableModel)table.getModel()).removeRow(modelRow);
					}
				};
				ButtonColumn buttonColumn = new ButtonColumn(section_table, view, 2);
				ButtonColumn buttonColumn2 = new ButtonColumn(section_table, edit, 3);
				ButtonColumn buttonColumn3 = new ButtonColumn(section_table, delete, 4);

				JButton btnClose = new JButton("Close");
				btnClose.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						section_screen.setVisible(false);
						if (issues == null) {
							section_screen.dispose();
							dashboard();
						} else if (!issues.isVisible()) {
							section_screen.dispose();
							issues.setVisible(true);
						} else {
							section_screen.dispose();
						}
					}
				});
				btnClose.setBounds(15, 85, 100, 29);
				section_screen.getContentPane().add(btnClose);
				buttonColumn.setMnemonic(KeyEvent.VK_D);
				buttonColumn2.setMnemonic(KeyEvent.VK_D);
				buttonColumn3.setMnemonic(KeyEvent.VK_D);
				ImageIcon db_icon = new ImageIcon(String.format("%s/required_files/%s", directory, "db_xxs.png"));
				JButton btnSaveData = new JButton(db_icon);
				btnSaveData.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 24));
				btnSaveData.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						database_save();
					}
				});
				btnSaveData.setBounds(26, height - 117, 70, 40);
				section_screen.getContentPane().add(btnSaveData);
				JLabel lblUpdateDb = new JLabel("Update");
				lblUpdateDb.setForeground(Color.WHITE);
				lblUpdateDb.setHorizontalAlignment(SwingConstants.CENTER);
				lblUpdateDb.setBounds(26, height - 132, 70, 15);
				section_screen.getContentPane().add(lblUpdateDb);
				JLabel lblIssue = new JLabel("Sections");
				lblIssue.setBackground(new Color(46, 46, 46));
				lblIssue.setForeground(new Color(255, 255, 255));
				lblIssue.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 28));
				lblIssue.setBounds(width - 220, 22, 350, 40);
				lblIssue.setOpaque(true);
				section_screen.getContentPane().add(lblIssue);

				JPanel panel = new JPanel();
				panel.setBackground(new Color(46, 46, 46));
				panel.setLayout(null);
				panel.setBounds(0, 0, width, 70);
				ImageIcon icon = new ImageIcon(String.format("%s/required_files/%s", directory, "toru-ui-logo.png"));
				JLabel logo = new JLabel(icon);
				logo.setBounds(10, 20, 140, 40);
				logo.setBackground(new Color(46, 46, 46));
				panel.add(logo);
				panel.repaint();
				logo.repaint();
				section_screen.getContentPane().add(panel);

				Panel panel_1 = new Panel();
				panel_1.setBackground(new Color(25, 25, 25));
				panel_1.setBounds(0, 70, width, 6);
				section_screen.getContentPane().add(panel_1);

				JButton btnAdd = new JButton("Add");

				JPanel panelSection = new JPanel();
				panelSection.setBounds(0, 0, 480, 240);
				panelSection.setLayout(null);

				JTextField txtSectionTitle = new JTextField();
				txtSectionTitle.setBounds(90, 45, 300, 30);
				panelSection.add(txtSectionTitle);
				txtSectionTitle.setColumns(4);
				final JCheckBox disable_comments = new JCheckBox("Disable Comments", false);

				disable_comments.setBounds(240, 80, 140, 26);
				panelSection.add(disable_comments);
				final JCheckBox abstracts_not_required = new JCheckBox("Abstracts Not Required", false);

				abstracts_not_required.setBounds(240, 106, 200, 26);
				panelSection.add(abstracts_not_required);
				final JCheckBox editor_restricted = new JCheckBox("Editor Restricted", false);

				editor_restricted.setBounds(240, 132, 200, 26);
				panelSection.add(editor_restricted);
				final JCheckBox hide_title = new JCheckBox("Hide Title", false);

				hide_title.setBounds(80, 80, 100, 26);
				panelSection.add(hide_title);
				final JCheckBox hide_author = new JCheckBox("Hide Author", false);

				hide_author.setBounds(80, 106, 100, 26);
				panelSection.add(hide_author);
				final JCheckBox hide_about = new JCheckBox("Hide About", false);

				hide_about.setBounds(80, 132, 100, 26);
				panelSection.add(hide_about);

				final JCheckBox metadata_indexed = new JCheckBox("Metadata Indexed", false);

				metadata_indexed.setBounds(80, 158, 180, 26);
				panelSection.add(metadata_indexed);

				final JCheckBox metadata_reviewed = new JCheckBox("Metadata Reviewed", false);

				metadata_reviewed.setBounds(80, 184, 180, 26);
				panelSection.add(metadata_reviewed);
				SpinnerNumberModel seq_model = new SpinnerNumberModel(0.0, -1000.0, 1000.0, 0.1);

				JLabel lblSeq = new JLabel("Seq");
				lblSeq.setHorizontalAlignment(SwingConstants.CENTER);
				lblSeq.setBounds(40, 215, 200, 20);
				panelSection.add(lblSeq);

				JSpinner txtSeq = new JSpinner(seq_model);
				txtSeq.setBounds(70, 236, 150, 30);
				panelSection.add(txtSeq);

				JLabel lblAbstractCount = new JLabel("Abstract Word Count");
				lblAbstractCount.setHorizontalAlignment(SwingConstants.CENTER);
				lblAbstractCount.setBounds(240, 215, 200, 20);
				panelSection.add(lblAbstractCount);
				SpinnerModel count_model = new SpinnerNumberModel(0, 0, 5000, 1);

				JSpinner txtAbstractCount = new JSpinner(count_model);
				txtAbstractCount.setBounds(270, 236, 150, 30);
				panelSection.add(txtAbstractCount);

				JLabel lblTitleSection = new JLabel("Title");
				lblTitleSection.setHorizontalAlignment(SwingConstants.CENTER);
				lblTitleSection.setBounds(190, 25, 100, 20);
				panelSection.add(lblTitleSection);
				panelSection.setBounds(0, 0, 480, 280);
				panelSection.setSize(new Dimension(480, 280));
				panelSection.setPreferredSize(new Dimension(480, 280));
				panelSection.setVisible(true);

				btnAdd.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						panelSection.setVisible(true);
						panelSection.setEnabled(true);
						boolean validation = false;
						int result = JOptionPane.showConfirmDialog(null, panelSection, "Add Section",
								JOptionPane.OK_CANCEL_OPTION);

						String title = txtSectionTitle.getText();
						if (title == null || title.isEmpty() || title.compareTo("") == 0 || title.compareTo(" ") == 0) {
							validation = false;
						} else {
							validation = true;
						}
						try {
							int wordcount = (Integer) txtAbstractCount.getValue();
							double seq = (Double) txtSeq.getValue();
							validation = true;
						} catch (Exception es) {
							validation = false;
						}

						if (result == JOptionPane.OK_OPTION && validation == true) {
							section_db_id++;
							Section new_section = new Section(section_db_id, txtSectionTitle.getText());
							new_section.setAbstract_word_count((Integer) txtAbstractCount.getValue());
							new_section.setSeq((Double) txtSeq.getValue());
							new_section.setHide_about(hide_about.isSelected() == true ? 1 : 0);
							new_section.setHide_author(hide_author.isSelected() == true ? 1 : 0);
							new_section.setHide_title(hide_title.isSelected() == true ? 1 : 0);
							new_section.setEditor_restricted(editor_restricted.isSelected() == true ? 1 : 0);
							new_section.setMeta_indexed(metadata_indexed.isSelected() == true ? 1 : 0);
							new_section.setMeta_reviewed(metadata_reviewed.isSelected() == true ? 1 : 0);
							new_section.setDisable_comments(disable_comments.isSelected() == true ? 1 : 0);
							new_section.setAbstracts_not_required(abstracts_not_required.isSelected() == true ? 1 : 0);
							new_section.setSync(true);
							section_storage.put(section_db_id, new_section);
							int num_rows = ((DefaultTableModel) section_table.getModel()).getRowCount();
							Set<Long> updated_sect_keys = section_storage.keySet();
							Object[][] rows = new Object[updated_sect_keys.size()][6];
							if (num_rows != 0) {
								for (int i = num_rows - 1; i >= 0; i--) {
									((DefaultTableModel) section_table.getModel()).removeRow(i);
								}
							}
							int i = 0;
							for (long id : updated_sect_keys) {
								Section current_section = section_storage.get(id);
								section_screens.put(id, new JFrame());

								Object[] row = { current_section.getId(), current_section.getTitle(), "View", "Edit",
										"Delete" };

								rows[i] = row;
								((DefaultTableModel) section_table.getModel()).insertRow(0, row);
								i++;

							}
							if (num_rows != 0) {
								((DefaultTableModel) section_table.getModel()).fireTableRowsUpdated(0,
										updated_sect_keys.size() - 1);
							}
							section_table.repaint();
							section_screen.getContentPane().repaint();
							section_screen.repaint();
						}

					}
				});
				btnAdd.setBounds(width - 150, height / 16 * 7 - 80, 117, 25);
				section_screen.getContentPane().add(btnAdd);

				JPanel panel3 = new JPanel();
				panel3.setBackground(SystemColor.window);
				panel3.setBounds(20, 108, width - 40, (height / 16) * 2);
				section_screen.getContentPane().add(panel3);
				panel3.setLayout(null);
				panel3.setAutoscrolls(true);
				panel3.setPreferredSize(new Dimension(width, height / 8));
				JScrollPane abstractSection = new JScrollPane(panel3);
				panel3.setAutoscrolls(true);
				final JButton btnDashboard = new JButton("Dashboard");
				btnDashboard.setBounds(width - 245, height - 80, 120, 29);
				btnDashboard.setEnabled(true);
				btnDashboard.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						section_screen.dispose();
						dashboard();
					}
				});
				section_screen.getContentPane().add(btnDashboard);
				final JButton btnIssue = new JButton("Sections");
				btnIssue.setBounds(width - 125, height - 80, 120, 29);
				btnIssue.setEnabled(false);
				btnIssue.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						sections();
					}
				});
				section_screen.getContentPane().add(btnIssue);

				section_screen.setVisible(true);

			}

		} else {
			login("dashboard");
		}
	}

	public void unpublished_articles() {
		if (logged_in) {
			if (unpublished_articles_screen == null || !unpublished_articles_screen.isVisible()) {

				unpublished_articles_screen = new JFrame();
				unpublished_articles_screen.setResizable(false);
				unpublished_articles_screen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				if (height >= 640 && width >= 960) {
					unpublished_articles_screen.setSize(width, height);
				} else {
					width = 960;
					height = 640;
					unpublished_articles_screen.setSize(960, 640);
				}
				ConcurrentHashMap<Long, JFrame> issue_articles = new ConcurrentHashMap<Long, JFrame>();
				unpublished_articles_screen.getContentPane().setBackground(new Color(213, 213, 213));

				unpublished_articles_screen.setLocationRelativeTo(null);
				unpublished_articles_screen.setTitle("Unpublished Articles");
				unpublished_articles_screen.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						// database_save();
					}
				});
				Date current = new Date();

				Set<Long> art_keys = unpublished_article_storage.keySet();
				ArrayList<List<Object>> rowData = new ArrayList<List<Object>>();
				Object[][] rows = new Object[art_keys.size()][7];
				int i = 0;
				for (long id : art_keys) {
					if (!unpublished_article_storage.get(id).isDeleted()) {
						ArrayList<Object> data = new ArrayList<Object>();
						issue_articles.put(id, new JFrame());
						System.out.println("Article: " + unpublished_article_storage.get(id).getId());
						data.add(Long.toString(unpublished_article_storage.get(id).getId()));

						data.add(Long.toString((unpublished_article_storage.get(id).getSection_id())));
						data.add(unpublished_article_storage.get(id).getTitle());
						data.add(unpublished_article_storage.get(id).getPages() == null
								? unpublished_article_storage.get(id).getPages() == null : "/");
						data.add(unpublished_article_storage.get(id).getAbstract_text());
						data.add(sdf.format(current));
						data.add("View");
						data.add("Edit");
						data.add("Delete");
						Date date_submitted = unpublished_article_storage.get(id).getDate_submitted();
						String date_submit = "";
						if (date_submitted == null) {
							date_submit = "/";
						} else {
							date_submit = sdf.format(unpublished_article_storage.get(id).getDate_submitted());
						}

						Object[] row = { unpublished_article_storage.get(id).getId(),
								unpublished_article_storage.get(id).getSection_id(),
								unpublished_article_storage.get(id).getTitle(),
								unpublished_article_storage.get(id).getPages(),
								unpublished_article_storage.get(id).getAbstract_text(), date_submit, "Move to" };
						rows[i] = row;
						i++;
						rowData.add(data);
					}
				}

				Object columnNames[] = { "ID", "Section", "Title", "Pages", "Abstract", "Date Accepted", "" };

				unpublished_articles_screen.getContentPane().setLayout(null);

				DefaultTableModel dtm = new DefaultTableModel(rows, columnNames);

				final JXTable article_table = new JXTable(dtm) {
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
				article_table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

				final JButton btnSync = new JButton("Sync");
				btnSync.setBounds(width - 155, 84, 70, 24);
				btnSync.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JProgressBar progressBar = new JProgressBar();
						Executor article_progress_executor = Executors.newSingleThreadExecutor();
						ExecutorService exec = Executors.newSingleThreadExecutor();

						progressBar.setValue(0);
						progressBar.setStringPainted(true);

						progressBar.setIndeterminate(true);
						progressBar.setBounds(width / 2 - 170, height - 115, 375, 32);
						JLabel progress_msg = new JLabel("Estimated progress per Issue:");

						progress_msg.setBounds(width / 2 - 75, height - 147, 200, 40);

						unpublished_articles_screen.add(progress_msg);
						unpublished_articles_screen.add(progressBar);
						unpublished_articles_screen.repaint();
						List<Future<?>> futures = new ArrayList<Future<?>>();
						issue_countdown_storage.put((long) -1, false);
						boolean update_table = false;

						System.out.println("Local");

						article_progress_executor.execute(new Runnable() {
							public void run() {
								int countdown = 300;
								// System.out.println("countdown " +
								// countdown);
								double decimal = 300 / 100;
								System.out.println(decimal);
								for (int i = 0; i < countdown; i++) {
									final int percent = i;
									// System.out.println("countdown:
									// "+issue_countdown_storage.get((long)
									// current_issue.getId()));
									if (issue_countdown_storage.containsKey((long) -1)
											&& issue_countdown_storage.get((long) -1) == true) {
										progressBar.setValue(100);
										progressBar.repaint();
										break;
									}
									SwingUtilities.invokeLater(new Runnable() {
										public void run() {
											// System.out.println("countdown:
											// "+issue_countdown_storage.get((long)
											// current_issue.getId()));

											if (issue_countdown_storage.containsKey((long) -1)
													&& issue_countdown_storage.get((long) -1) == true) {
												progressBar.setValue(100);
												progressBar.repaint();
											} else {
												progressBar.setValue(percent == 0 ? 0
														: (int) Double
																.parseDouble(String.format("%s", percent / decimal)));
												progressBar.repaint();
											}
										}
									});

									try {
										Thread.sleep(100);
									} catch (InterruptedException e) {
									}
								}
							}
						});
						unpublished_articles_screen.add(progress_msg);
						unpublished_articles_screen.add(progressBar);
						unpublished_articles_screen.repaint();

						exec.submit(new Runnable() {

							public void run() {
								try {
									// get_sections(Long.parseLong(app_settings.get("journal_id")),
									// encoding, false);
									ArrayList<Long> articles = update_unpublished_articles_local_single_request(
											encoding);
									for (long id : articles) {
										get_article_authors_remote_single_request(id, encoding, false);
									}
									issue_countdown_storage.put((long) -1, true);

								} catch (IllegalStateException e1) {

									e1.printStackTrace();
								} catch (IOException e1) {

									e1.printStackTrace();
								}
							}
						});

						boolean update_q = update_table;
						article_progress_executor.execute(new Runnable() {
							public void run() {
								for (Future<?> f : futures) {
									try {
										f.get();
									} catch (InterruptedException e) {

										e.printStackTrace();
									} catch (ExecutionException e) {

										e.printStackTrace();
									}

								}
							}
						});

						article_progress_executor.execute(new Runnable() {
							public void run() {
								for (Future<?> f : futures) {
									try {
										f.get();
									} catch (InterruptedException e) {

										e.printStackTrace();
									} catch (ExecutionException e) {

										e.printStackTrace();
									}

								}
							}
						});
						article_progress_executor.execute(new Runnable() {
							public void run() {
								unpublished_articles_screen.dispose();
								unpublished_articles();

								int dialogResult = JOptionPane.showConfirmDialog(null,
										"Save changes to local database?", "Warning", 1);

								if (dialogResult == JOptionPane.YES_OPTION) {
									database_save();
								}
								System.out.println("updatet able" + update_q);
								// change
								if (update_q) {
									Set<Long> keys = unpublished_article_storage.keySet();
									ArrayList<List<Object>> rowData = new ArrayList<List<Object>>();
									Object[][] rows = new Object[unpublished_article_storage.size()][7];
									int num_rows = 0;
									try {
										num_rows = article_table.getRowCount();
									} catch (NullPointerException n_e) {
									}
									if (num_rows != 0) {
										for (int i = num_rows - 1; i >= 0; i--) {
											System.out.println(num_rows);
											((DefaultTableModel) article_table.getModel()).removeRow(i);

											System.out.println("--"
													+ ((DefaultTableModel) article_table.getModel()).getRowCount());
										}
									}
									int i = 0;

									for (long id : keys) {

										ArrayList<Object> data = new ArrayList<Object>();
										issue_articles.put(id, new JFrame());

										data.add(Long.toString(unpublished_article_storage.get(id).getId()));
										data.add(Long.toString((unpublished_article_storage.get(id).getSection_id())));
										data.add(unpublished_article_storage.get(id).getTitle());
										data.add(unpublished_article_storage.get(id).getPages() == null ? "/"
												: unpublished_article_storage.get(id).getPages());
										data.add(unpublished_article_storage.get(id).getAbstract_text());
										data.add(sdf.format(current));
										data.add("Move to");
										Date date_submitted = unpublished_article_storage.get(id).getDate_submitted();
										String date_submit = "";
										if (date_submitted == null) {
											date_submit = "/";
										} else {
											date_submit = sdf
													.format(unpublished_article_storage.get(id).getDate_submitted());
										}

										Object[] row = { unpublished_article_storage.get(id).getId(),
												unpublished_article_storage.get(id).getSection_id(),
												unpublished_article_storage.get(id).getTitle(),
												unpublished_article_storage.get(id).getPages(),
												unpublished_article_storage.get(id).getAbstract_text(), date_submit,
												"Move to" };
										rows[i] = row;
										rowData.add(data);
										((DefaultTableModel) article_table.getModel()).insertRow(0, row);
										i++;
										System.out.println(
												"++" + ((DefaultTableModel) article_table.getModel()).getRowCount());

									}
									System.out.println(num_rows);
									try {
										num_rows = ((DefaultTableModel) article_table.getModel()).getRowCount();
									} catch (NullPointerException n_e) {
									}
									System.out.println(":::" + num_rows);
									if (num_rows != 0) {
										((DefaultTableModel) article_table.getModel()).fireTableRowsUpdated(0,
												num_rows - 1);
									}

								}

								article_table.repaint();
								unpublished_articles_screen.getContentPane().repaint();
								unpublished_articles_screen.remove(progress_msg);
								unpublished_articles_screen.remove(progressBar);
								unpublished_articles_screen.repaint();
							}
						});

					}

				});
				unpublished_articles_screen.getContentPane().add(btnSync);

				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				scrollPane.setBounds(15, height / 16 * 7 - 100, width - 30, (height - 30) - (height / 16 * 7) - 10);
				unpublished_articles_screen.getContentPane().add(scrollPane);
				// reference:
				// https://svn.java.net/svn/swinglabs-demos~svn/trunk/src/java/org/jdesktop/demo/sample/
				final JTextField filter = new JTextField("");
				filter.setBounds(150, height / 16 * 7 - 127, 117, 25);

				final JButton search = new JButton("Search");
				final JButton clear = new JButton("Clear");
				search.setBounds(268, height / 16 * 7 - 127, 90, 25);

				clear.setBounds(355, height / 16 * 7 - 127, 90, 25);
				;
				unpublished_articles_screen.getContentPane().add(filter);
				unpublished_articles_screen.getContentPane().add(search);
				unpublished_articles_screen.getContentPane().add(clear);
				filter.setAction(new AbstractAction("Search") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

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
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					public void actionPerformed(ActionEvent e) {

						String searchString = filter.getText().trim();
						if (searchString.length() > 0) {
							article_table.setRowFilter(RowFilters.regexFilter(0, searchString));
						}
					}
				});
				clear.setAction(new AbstractAction("Clear") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					public void actionPerformed(ActionEvent e) {
						article_table.setRowFilter(null);
					}
				});
				article_table.setAutoCreateRowSorter(true);
				scrollPane.setViewportView(article_table);
				article_table.getColumnModel().getColumn(6).setMinWidth(50);
				article_table.setColumnSelectionAllowed(true);
				article_table.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				article_table.setCellSelectionEnabled(true);
				article_table.setRowHeight(23);
				final Label internetCheck = new Label("ONLINE");
				internetCheck.setFont(new Font(Font.SANS_SERIF, Font.BOLD | Font.ITALIC, 12));
				internetCheck.setBackground(Color.lightGray);
				internetCheck.setAlignment(1);
				internetCheck.setForeground(new Color(255, 255, 255));
				internetCheck.setBounds(width - 85, 85, 65, 22);
				unpublished_articles_screen.getContentPane().add(internetCheck);

				ActionListener taskPerformer1 = new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						if (status_online()) {
							internetCheck.setBackground(Color.lightGray);
							internetCheck.setText("ONLINE");
							btnSync.setEnabled(true);

						} else {
							internetCheck.setBackground(Color.DARK_GRAY);
							internetCheck.setText("OFFLINE");
							btnSync.setEnabled(false);
						}
					}
				};
				new Timer(delay * 2, taskPerformer1).start();
				new DefaultTableModel(rows, columnNames);
				JPanel panelIssue = new JPanel();
				panelIssue.setBounds(0, 0, 280, 60);
				panelIssue.setPreferredSize(new Dimension(280, 60));
				panelIssue.setLayout(null);

				JLabel title_move_to = new JLabel("Move to Issue:");
				title_move_to.setBounds(0, 5, 100, 26);
				final JComboBox<String> lblIssueId = new JComboBox<String>();
				Set<Long> issue_keys = issue_storage.keySet();
				ArrayList<Issue> issue_list = new ArrayList<Issue>();
				for (long key : issue_keys) {
					lblIssueId
							.addItem(String.format("Issue <%s> - Volume: %s Year: %s ", issue_storage.get(key).getId(),
									issue_storage.get(key).getVolume(), issue_storage.get(key).getYear()));
					issue_list.add(issue_storage.get(key));
				}

				lblIssueId.setForeground(Color.BLACK);
				lblIssueId.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 12));
				lblIssueId.setBounds(0, 30, 280, 26);
				panelIssue.add(title_move_to);
				panelIssue.add(lblIssueId);
				panelIssue.repaint();
				Action move_to = new AbstractAction() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					public void actionPerformed(ActionEvent e) {
						JTable table = (JTable) e.getSource();
						Integer.valueOf(e.getActionCommand());
						int article_row = table.getSelectedRow();
						long selected_article = (long) table.getModel()
								.getValueAt(table.convertRowIndexToModel(article_row), 0);

						panelIssue.setVisible(true);
						panelIssue.setEnabled(true);
						panelIssue.repaint();
						boolean validation = false;
						int result = JOptionPane.showConfirmDialog(null, panelIssue,
								String.format("Article<%s>", selected_article), JOptionPane.OK_CANCEL_OPTION);
						if (unpublished_article_storage.containsKey((long) selected_article)) {
							Article current_article = unpublished_article_storage.get(selected_article);
							int selected_issue = lblIssueId.getSelectedIndex();

							if (result == JOptionPane.OK_OPTION && selected_issue != -1) {
								System.out.println(issue_list.get((int) selected_issue));
								if (unpublished_articles_screen.isVisible()) {
									unpublished_articles_screen.dispose();
								}
								Issue current_issue = issue_storage.get((long) issue_list.get(selected_issue).getId());
								current_article.setIssue_fk(current_issue);
								current_article.setSync(true);
								current_issue.add_article(current_article.getId(), current_article);
								current_issue.setSync(true);
								issue_storage.put((long) current_issue.getId(), current_issue);
								unpublished_article_storage.remove((long) selected_article);
								article_storage.put((long) selected_article, current_article);

								((DefaultTableModel) table.getModel())
										.removeRow(table.convertColumnIndexToModel(article_row));

								unpublished_articles();
							} else {
								if (unpublished_articles_screen.isVisible()) {
									unpublished_articles_screen.dispose();
								}
								unpublished_articles();
							}
						} else {
							System.out.println("does not contain " + selected_article);
							System.out.println(unpublished_article_storage.containsKey((long) selected_article));
						}
						// edit_article(issue_id, selected_article);
						// /
						// ((DefaultTableModel)table.getModel()).removeRow(modelRow);
					}
				};
				ButtonColumn buttonColumn3 = new ButtonColumn(article_table, move_to, 6);

				JLabel lblArticles = new JLabel("Articles");
				lblArticles.setBackground(new Color(213, 213, 213));
				lblArticles.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 24));
				lblArticles.setForeground(new Color(0, 0, 0));
				lblArticles.setBounds(30, height / 16 * 7 - 132, 180, 30);
				lblArticles.setOpaque(true);
				unpublished_articles_screen.getContentPane().add(lblArticles);

				JLabel lblIssue = new JLabel("Unpublished Articles");
				lblIssue.setBackground(new Color(46, 46, 46));
				lblIssue.setForeground(new Color(255, 255, 255));
				lblIssue.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 28));
				lblIssue.setBounds(width - 355, 22, 350, 40);
				lblIssue.setOpaque(true);
				unpublished_articles_screen.getContentPane().add(lblIssue);

				JButton btnClose = new JButton("Close");
				btnClose.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						unpublished_articles_screen.setVisible(false);
						if (issues == null) {
							unpublished_articles_screen.dispose();
							dashboard();
						} else if (!issues.isVisible()) {
							unpublished_articles_screen.dispose();
							issues.setVisible(true);
						} else {
							unpublished_articles_screen.dispose();
						}
					}
				});
				btnClose.setBounds(15, 85, 100, 29);
				unpublished_articles_screen.getContentPane().add(btnClose);
				buttonColumn3.setMnemonic(KeyEvent.VK_D);
				ImageIcon db_icon = new ImageIcon(String.format("%s/required_files/%s", directory, "db_xxs.png"));
				JButton btnSaveData = new JButton(db_icon);
				btnSaveData.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 24));
				btnSaveData.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						database_save();
					}
				});
				btnSaveData.setBounds(45, height - 117, 70, 40);
				unpublished_articles_screen.getContentPane().add(btnSaveData);
				JLabel lblUpdateDb = new JLabel("Update Local Database");
				lblUpdateDb.setForeground(Color.BLACK);
				lblUpdateDb.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 11));
				lblUpdateDb.setHorizontalAlignment(SwingConstants.CENTER);
				lblUpdateDb.setBounds(14, height - 132, 140, 15);
				unpublished_articles_screen.getContentPane().add(lblUpdateDb);

				JPanel panel = new JPanel();
				panel.setBackground(new Color(46, 46, 46));
				panel.setLayout(null);
				panel.setBounds(0, 0, width, 70);
				ImageIcon icon = new ImageIcon(String.format("%s/required_files/%s", directory, "toru-ui-logo.png"));
				JLabel logo = new JLabel(icon);
				logo.setBounds(10, 20, 140, 40);
				logo.setBackground(new Color(46, 46, 46));
				panel.add(logo);
				panel.repaint();
				logo.repaint();
				unpublished_articles_screen.getContentPane().add(panel);

				Panel panel_1 = new Panel();
				panel_1.setBackground(new Color(25, 25, 25));
				panel_1.setBounds(0, 70, width, 6);
				unpublished_articles_screen.getContentPane().add(panel_1);
				final JButton btnDashboard = new JButton("Dashboard");
				btnDashboard.setBounds(width - 325, height - 80, 120, 29);
				btnDashboard.setEnabled(true);
				btnDashboard.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						unpublished_articles_screen.dispose();
						dashboard();
					}
				});
				unpublished_articles_screen.getContentPane().add(btnDashboard);
				final JButton btnIssue = new JButton("Unpublished Articles");
				btnIssue.setBounds(width - 205, height - 80, 195, 29);
				btnIssue.setEnabled(false);
				btnIssue.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						unpublished_articles();
					}
				});
				unpublished_articles_screen.getContentPane().add(btnIssue);
				unpublished_articles_screen.repaint();
				unpublished_articles_screen.getContentPane().repaint();

				unpublished_articles_screen.setVisible(true);

			}

		} else {
			login("dashboard");
		}
	}

	public void issue(final long issue_id) {
		if (logged_in) {
			if (issue_screens.containsKey(issue_id) && !issue_screens.get(issue_id).isVisible()) {

				final JFrame articles = new JFrame();
				articles.setResizable(false);
				articles.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				if (height >= 640 && width >= 960) {
					articles.setSize(width, height);
				} else {
					width = 960;
					height = 640;
					articles.setSize(960, 640);
				}
				ConcurrentHashMap<Long, JFrame> issue_articles = new ConcurrentHashMap<Long, JFrame>();
				articles.getContentPane().setBackground(new Color(213, 213, 213));

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
				ConcurrentHashMap<Long, Article> current_articles = current_issue.getArticles_list();
				Set<Long> art_keys = current_articles.keySet();
				ArrayList<List<Object>> rowData = new ArrayList<List<Object>>();
				Object[][] rows = new Object[art_keys.size()][11];
				int i = 0;
				for (long id : art_keys) {
					if (!current_articles.get(id).isDeleted()) {
						ArrayList<Object> data = new ArrayList<Object>();
						issue_articles.put(id, new JFrame());
						System.out.println("Article: " + current_articles.get(id).getId());
						data.add(Long.toString(current_articles.get(id).getId()));
						data.add(Long.toString(issue_id));
						data.add(Long.toString((current_articles.get(id).getSection_id())));
						data.add(current_articles.get(id).getTitle());
						data.add(current_articles.get(id).getPages() == null
								? current_articles.get(id).getPages() == null : "/");
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
				}

				article_screens.put(issue_id, issue_articles);
				Object columnNames[] = { "ID", "Issue", "Section", "Title", "Pages", "Abstract", "Date Accepted",
						"Date Published", "", "", "" };

				articles.getContentPane().setLayout(null);

				DefaultTableModel dtm = new DefaultTableModel(rows, columnNames);

				final JXTable article_table = new JXTable(dtm) {
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
				article_table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

				final JButton btnSync = new JButton("Sync");
				btnSync.setBounds(width - 155, 89, 70, 24);
				btnSync.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println(current_issue.getArticles_list().isEmpty());
						JProgressBar progressBar = new JProgressBar();
						Executor article_progress_executor = Executors.newSingleThreadExecutor();
						ExecutorService exec = Executors.newFixedThreadPool(1);

						progressBar.setValue(0);
						progressBar.setStringPainted(true);

						progressBar.setIndeterminate(true);
						progressBar.setBounds(width / 2 - 170, height - 100, 375, 32);
						JLabel progress_msg = new JLabel("Estimated progress per Issue:");

						progress_msg.setBounds(width / 2 - 75, height - 132, 200, 40);

						articles.add(progress_msg);
						articles.add(progressBar);
						articles.repaint();
						List<Future<?>> futures = new ArrayList<Future<?>>();

						boolean update_table = false;

						if (!current_issue.getArticles_list().isEmpty()) {

							issue_countdown_storage.put((long) current_issue.getId(), false);
							Object[] options_2 = { "Remote Data", "Local Data", "Cancel" };
							int dialogResult2 = JOptionPane.showOptionDialog(null,
									"Would You Like to replace local Section data or update remote Secton data", "Warning",
									JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options_2,
									options_2[0]);

							if (dialogResult2 == 1) {
								Future<?> f = exec.submit(new Runnable() {
									public void run() {

										try {
											get_sections(Long.parseLong(app_settings.get("journal_id")), encoding,
													false);

										} catch (NumberFormatException e) {

											e.printStackTrace();
										} catch (IllegalStateException e) {

											e.printStackTrace();
										} catch (IOException e) {

											e.printStackTrace();
										}
									}
								});
								futures.add(f);
							} else if (dialogResult2 == 0) {
								Future<?> f = exec.submit(new Runnable() {
									public void run() {
										try {
											update_sections(Long.parseLong(app_settings.get("journal_id")), encoding,
													false);
										} catch (NumberFormatException e1) {

											e1.printStackTrace();
										} catch (IllegalStateException e1) {

											e1.printStackTrace();
										} catch (IOException e1) {

											e1.printStackTrace();
										}
									}
								});
								futures.add(f);
							}
							int dialogResult = JOptionPane.showOptionDialog(null,
									"Would You Like to replace local data or update remote data", "Warning",
									JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options_2,
									options_2[0]);

							if (dialogResult == 2) {
								issue_countdown_storage.put((long) current_issue.getId(), true);
							}
							if (dialogResult == 0) {
								article_progress_executor.execute(new Runnable() {
									public void run() {
										int countdown = current_issue.getArticles_list().size() * 6 + 40
												+ current_issue.getAuthors().size() * 6;
										// System.out.println("countdown " +
										// countdown);
										double decimal = (current_issue.getArticles_list().size() * 6 + 40
												+ current_issue.getAuthors().size() * 6) / 100;
										System.out.println(decimal);
										for (int i = 0; i < countdown; i++) {
											final int percent = i;
											// System.out.println("countdown:
											// "+issue_countdown_storage.get((long)
											// current_issue.getId()));

											if (issue_countdown_storage.containsKey((long) current_issue.getId())
													&& issue_countdown_storage.get((long) current_issue.getId())) {
												progressBar.setValue(100);
												progressBar.repaint();
												break;
											}
											SwingUtilities.invokeLater(new Runnable() {
												public void run() {
													// System.out.println("countdown:
													// "+issue_countdown_storage.get((long)
													// current_issue.getId()));
													if (issue_countdown_storage
															.containsKey((long) current_issue.getId())
															&& issue_countdown_storage
																	.get((long) current_issue.getId())) {
														progressBar.setValue(100);
													} else {
														progressBar.setValue(percent == 0 ? 0
																: (int) Double.parseDouble(
																		String.format("%s", percent / decimal)));
														progressBar.repaint();
													}
												}
											});

											try {
												Thread.sleep(95);
											} catch (InterruptedException e) {
											}
										}
									}
								});
								articles.add(progress_msg);
								articles.add(progressBar);
								articles.repaint();
								Future<?> f = exec.submit(new Runnable() {
									public void run() {
										try {
											update_issue_intersect(current_issue, encoding, false);

										} catch (IllegalStateException | IOException e1) {

											e1.printStackTrace();
										}
									}
								});
								futures.add(f);

								f = exec.submit(new Runnable() {

									public void run() {
										try {
											System.out.println("articles preparing");
											update_articles_intersect(current_issue, encoding);

										} catch (IllegalStateException | IOException e1) {

											e1.printStackTrace();
										}
									}
								});

								futures.add(f);
							} else if (dialogResult == 1) {
								System.out.println("update local");
								article_progress_executor.execute(new Runnable() {
									public void run() {
										int countdown = current_issue.getArticles_list().size() * 6 + 40
												+ current_issue.getAuthors().size() * 6;
										// System.out.println("countdown " +
										// countdown);
										double decimal = (current_issue.getArticles_list().size() * 6 + 40
												+ current_issue.getAuthors().size() * 6) / 100;
										// System.out.println(decimal);
										for (int i = 0; i < countdown; i++) {
											final int percent = i;
											// System.out.println("countdown:
											// "+issue_countdown_storage.get((long)
											// current_issue.getId()));

											if (issue_countdown_storage.containsKey((long) current_issue.getId())
													&& issue_countdown_storage
															.get((long) current_issue.getId()) == true) {
												progressBar.setValue(100);
												progressBar.repaint();
												break;
											}
											SwingUtilities.invokeLater(new Runnable() {
												public void run() {
													// System.out.println("countdown:
													// "+issue_countdown_storage.get((long)
													// current_issue.getId()));

													if (issue_countdown_storage
															.containsKey((long) current_issue.getId())
															&& issue_countdown_storage
																	.get((long) current_issue.getId()) == true) {
														progressBar.setValue(100);
														progressBar.repaint();
													} else {
														progressBar.setValue(percent == 0 ? 0
																: (int) Double.parseDouble(
																		String.format("%s", percent / decimal)));
														progressBar.repaint();
													}
												}
											});

											try {
												Thread.sleep(100);
											} catch (InterruptedException e) {
											}
										}
									}
								});
								update_table = true;
								Future<?> f = exec.submit(new Runnable() {
									public void run() {
										try {
											update_issue_local(current_issue, encoding);

										} catch (IllegalStateException | IOException e1) {

											e1.printStackTrace();
										}
									}
								});
								futures.add(f);
								f = exec.submit(new Runnable() {

									public void run() {

										try {
											update_articles_local_single_request(current_issue, encoding);
										} catch (IllegalStateException | IOException e1) {

											e1.printStackTrace();
										}
									}
								});
								futures.add(f);

							}
						} else {

							System.out.println("Local");

							article_progress_executor.execute(new Runnable() {
								public void run() {
									int countdown = current_issue.getArticles_list().size() * 6 + 40
											+ current_issue.getAuthors().size() * 6;
									// System.out.println("countdown " +
									// countdown);
									double decimal = (current_issue.getArticles_list().size() * 6 + 40
											+ current_issue.getAuthors().size() * 6) / 100;
									System.out.println(decimal);
									for (int i = 0; i < countdown; i++) {
										final int percent = i;
										// System.out.println("countdown:
										// "+issue_countdown_storage.get((long)
										// current_issue.getId()));

										if (issue_countdown_storage.containsKey((long) current_issue.getId())
												&& issue_countdown_storage.get((long) current_issue.getId()) == true) {
											progressBar.setValue(100);
											progressBar.repaint();
											break;
										}
										SwingUtilities.invokeLater(new Runnable() {
											public void run() {
												// System.out.println("countdown:
												// "+issue_countdown_storage.get((long)
												// current_issue.getId()));

												if (issue_countdown_storage.containsKey((long) current_issue.getId())
														&& issue_countdown_storage
																.get((long) current_issue.getId()) == true) {
													progressBar.setValue(100);
													progressBar.repaint();
												} else {
													progressBar.setValue(percent == 0 ? 0
															: (int) Double.parseDouble(
																	String.format("%s", percent / decimal)));
													progressBar.repaint();
												}
											}
										});

										try {
											Thread.sleep(100);
										} catch (InterruptedException e) {
										}
									}
								}
							});
							articles.add(progress_msg);
							articles.add(progressBar);
							articles.repaint();

							exec.submit(new Runnable() {

								public void run() {
									try {
										get_sections(Long.parseLong(app_settings.get("journal_id")), encoding, false);
										update_articles_local_single_request(current_issue, encoding);

									} catch (IllegalStateException e1) {

										e1.printStackTrace();
									} catch (IOException e1) {

										e1.printStackTrace();
									}
								}
							});
						}

						boolean update_q = update_table;
						article_progress_executor.execute(new Runnable() {
							public void run() {
								for (Future<?> f : futures) {
									try {
										f.get();
									} catch (InterruptedException e) {

										e.printStackTrace();
									} catch (ExecutionException e) {

										e.printStackTrace();
									}

								}
							}
						});
						System.out.println("ARTICLES: " + current_issue.getArticles_list().size());
						System.out.println("ARTICLES: "
								+ issue_storage.get((long) current_issue.getId()).getArticles_list().size());
						if (status_online()) {
							Object[] options_2 = { "Remote Data", "Local Data", "Cancel" };
							
							dialogResult = JOptionPane.showOptionDialog(null,
									"Would You Like to replace local Author data or update remote Author data", "Warning",
									JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options_2,
									options_2[0]);
						

							if (dialogResult == 0) {
								/*
								 * Future<?> f = exec.submit(new Runnable() {
								 * 
								 * public void run() {
								 * 
								 * try { sync_authors_intersect(issue_id,
								 * encoding, false); } catch
								 * (IllegalStateException e2) {
								 * 
								 * e2.printStackTrace(); } catch (IOException
								 * e2) {
								 * 
								 * e2.printStackTrace(); } } });
								 * 
								 * futures.add(f);
								 */
							} else if (dialogResult == 1) {
								Future<?> f = exec.submit(new Runnable() {

									public void run() {

										try {
											get_authors_remote_single_request(issue_id, encoding, false);
										} catch (IllegalStateException e1) {

											e1.printStackTrace();
										} catch (IOException e1) {

											e1.printStackTrace();
										}
									}
								});
								futures.add(f);
							}
						}

						article_progress_executor.execute(new Runnable() {
							public void run() {
								for (Future<?> f : futures) {
									try {
										f.get();
									} catch (InterruptedException e) {

										e.printStackTrace();
									} catch (ExecutionException e) {

										e.printStackTrace();
									}

								}
							}
						});
						article_progress_executor.execute(new Runnable() {
							public void run() {
								System.out.println("AFTER ARTICLES: " + current_issue.getArticles_list().size());
								System.out.println(" AFTER ARTICLES: "
										+ issue_storage.get((long) current_issue.getId()).getArticles_list().size());

								articles.dispose();
								issue(issue_id);

								int dialogResult = JOptionPane.showConfirmDialog(null,
										"Save changes to local database?", "Warning", 1);

								if (dialogResult == JOptionPane.YES_OPTION) {
									database_save();
								}
								System.out.println("updatet able" + update_q);
								// change
								if (update_q) {
									ConcurrentHashMap<Long, Article> all_articles = current_issue.getArticles_list();
									Set<Long> keys = all_articles.keySet();
									ArrayList<List<Object>> rowData = new ArrayList<List<Object>>();
									Object[][] rows = new Object[all_articles.size()][11];
									int num_rows = 0;
									try {
										num_rows = article_table.getRowCount();
									} catch (NullPointerException n_e) {
									}
									if (num_rows != 0) {
										for (int i = num_rows - 1; i >= 0; i--) {
											System.out.println(num_rows);
											((DefaultTableModel) article_table.getModel()).removeRow(i);

											System.out.println("--"
													+ ((DefaultTableModel) article_table.getModel()).getRowCount());
										}
									}
									int i = 0;
									try {
										get_authors_remote_single_request(issue_id, encoding, false);
									} catch (IllegalStateException e1) {

										e1.printStackTrace();
									} catch (IOException e1) {

										e1.printStackTrace();
									}
									for (long id : keys) {
										all_articles.get(id);

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
												all_articles.get(id).getSection_id(),
												current_articles.get(id).getTitle(), all_articles.get(id).getPages(),
												all_articles.get(id).getAbstract_text(), date_submit, date_pub, "View",
												"Edit", "Delete" };
										rows[i] = row;
										rowData.add(data);
										((DefaultTableModel) article_table.getModel()).insertRow(0, row);
										i++;
										System.out.println(
												"++" + ((DefaultTableModel) article_table.getModel()).getRowCount());

									}
									System.out.println(num_rows);
									try {
										num_rows = ((DefaultTableModel) article_table.getModel()).getRowCount();
									} catch (NullPointerException n_e) {
									}
									System.out.println(":::" + num_rows);
									if (num_rows != 0) {
										((DefaultTableModel) article_table.getModel()).fireTableRowsUpdated(0,
												num_rows - 1);
									}

								}

								article_table.repaint();
								articles.getContentPane().repaint();
								articles.remove(progress_msg);
								articles.remove(progressBar);
								articles.repaint();
							}
						});

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
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

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
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					public void actionPerformed(ActionEvent e) {

						String searchString = filter.getText().trim();
						if (searchString.length() > 0) {
							article_table.setRowFilter(RowFilters.regexFilter(0, searchString));
						}
					}
				});
				clear.setAction(new AbstractAction("Clear") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

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
				final Label internetCheck = new Label("ONLINE");
				internetCheck.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT | Font.ITALIC, 12));
				internetCheck.setBackground(Color.lightGray);
				internetCheck.setAlignment(1);
				internetCheck.setForeground(new Color(255, 255, 255));
				internetCheck.setBounds(width - 85, 91, 65, 22);
				articles.getContentPane().add(internetCheck);

				ActionListener taskPerformer1 = new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						if (status_online()) {
							internetCheck.setBackground(Color.lightGray);
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
				new DefaultTableModel(rows, columnNames);
				Action delete = new AbstractAction() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					public void actionPerformed(ActionEvent e) {
						JTable table = (JTable) e.getSource();
						int modelRow = Integer.valueOf(e.getActionCommand());
						// JOptionPane.showMessageDialog(null, "Deleted");
						int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this article?",
								"Delete ?", JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {
							int article_row = table.getSelectedRow();
							long selected_article = (long) table.getModel()
									.getValueAt(table.convertRowIndexToModel(article_row), 0);
							Issue current_issue = issue_storage.get(issue_id);
							Article current_article = current_issue.getArticles_list().get((long) selected_article);
							current_article.setDeleted(true);
							current_article.setSync(true);
							current_issue.add_article((long) selected_article, current_article);
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
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					public void actionPerformed(ActionEvent e) {
						JTable table = (JTable) e.getSource();

						articles.dispose();
						Integer.valueOf(e.getActionCommand());
						int article_row = table.getSelectedRow();
						long selected_article = (long) table.getModel()
								.getValueAt(table.convertRowIndexToModel(article_row), 0);
						if (article_screens.get(issue_id).get(selected_article).isVisible()) {
							article_screens.get(issue_id).get(selected_article).dispose();
						}
						article(issue_id, selected_article);
						// /
						// ((DefaultTableModel)table.getModel()).removeRow(modelRow);
					}
				};
				Action edit = new AbstractAction() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					public void actionPerformed(ActionEvent e) {
						JTable table = (JTable) e.getSource();
						articles.dispose();
						Integer.valueOf(e.getActionCommand());
						int article_row = table.getSelectedRow();
						long selected_article = (long) table.getModel()
								.getValueAt(table.convertRowIndexToModel(article_row), 0);
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
				lblArticles.setBackground(new Color(213, 213, 213));
				lblArticles.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 26));
				lblArticles.setForeground(new Color(0, 0, 0));
				lblArticles.setBounds(25, height / 16 * 7 - 36, 180, 30);
				lblArticles.setOpaque(true);
				articles.getContentPane().add(lblArticles);

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
				btnClose.setBounds(15, 90, 100, 29);
				articles.getContentPane().add(btnClose);
				buttonColumn.setMnemonic(KeyEvent.VK_D);
				buttonColumn2.setMnemonic(KeyEvent.VK_D);
				buttonColumn3.setMnemonic(KeyEvent.VK_D);
				ImageIcon db_icon = new ImageIcon(String.format("%s/required_files/%s", directory, "db_xxs.png"));
				JButton btnSaveData = new JButton(db_icon);
				btnSaveData.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 24));
				btnSaveData.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						database_save();
					}
				});
				btnSaveData.setBounds(46, height - 110, 70, 40);
				articles.getContentPane().add(btnSaveData);
				JLabel lblUpdateDb = new JLabel("Update Local Database");
				lblUpdateDb.setForeground(Color.black);
				lblUpdateDb.setHorizontalAlignment(SwingConstants.CENTER);
				lblUpdateDb.setBounds(14, height - 125, 170, 15);
				articles.getContentPane().add(lblUpdateDb);

				JLabel lblIssue = new JLabel("Issue: " + issue_id);
				lblIssue.setBackground(new Color(46, 46, 46));
				lblIssue.setForeground(new Color(255, 255, 255));
				lblIssue.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 26));
				lblIssue.setBounds(width - 150, 26, 280, 40);
				lblIssue.setOpaque(true);
				articles.getContentPane().add(lblIssue);

				JPanel panel = new JPanel();
				panel.setBackground(new Color(46, 46, 46));
				panel.setLayout(null);
				panel.setBounds(0, 0, width, 70);
				ImageIcon icon = new ImageIcon(String.format("%s/required_files/%s", directory, "toru-ui-logo.png"));
				JLabel logo = new JLabel(icon);
				logo.setBounds(10, 20, 140, 40);
				logo.setBackground(new Color(46, 46, 46));
				panel.add(logo);
				panel.repaint();
				logo.repaint();
				articles.getContentPane().add(panel);

				Panel panel_1 = new Panel();
				panel_1.setBackground(new Color(25, 25, 25));
				panel_1.setBounds(0, 70, width, 6);
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
				panel3.setPreferredSize(new Dimension(width, height / 8));
				JScrollPane abstractSection = new JScrollPane(panel3);
				panel3.setAutoscrolls(true);
				new Date();
				Issue row_issue = issue_storage.get(issue_id);
				Object issue_rowData[][] = { { row_issue.getId(), row_issue.getTitle(), row_issue.getVolume(),
						row_issue.getNumber(), row_issue.getYear(),
						row_issue.getDate_accepted() == null ? "/" : sdf.format(row_issue.getDate_accepted()),
						row_issue.getDate_published() == null ? "/" : sdf.format(row_issue.getDate_published()) } };
				Object issue_columnNames[] = { "ID", "Title", "Volume", "Number", "Year", "Date Accepted",
						"Date Published" };

				DefaultTableModel issue_dtm = new DefaultTableModel(issue_rowData, issue_columnNames);

				final JTable issuedetails = new JTable(issue_dtm) {
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
				lblIssueDetails.setBackground(new Color(213, 213, 213));
				lblIssueDetails.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 26));
				lblIssueDetails.setForeground(new Color(0, 0, 0));
				lblIssueDetails.setBounds((width - 70) / 2, 135, 125, 30);
				lblIssueDetails.setOpaque(true);
				articles.getContentPane().add(lblIssueDetails);

				final JButton btnDashboard = new JButton("Dashboard");
				btnDashboard.setBounds(width - 255, height - 80, 120, 29);
				btnDashboard.setEnabled(true);
				btnDashboard.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						articles.dispose();
						dashboard();
					}
				});
				articles.getContentPane().add(btnDashboard);
				final JButton btnIssue = new JButton("Issue");
				btnIssue.setBounds(width - 135, height - 80, 120, 29);
				btnIssue.setEnabled(false);
				btnIssue.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						issue(issue_id);
					}
				});
				articles.getContentPane().add(btnIssue);
				articles.repaint();
				articles.getContentPane().repaint();
				articles.setVisible(true);
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

				width_small = 920;
				height_small = 768;

				final JFrame article = new JFrame();
				article.setResizable(false);
				String setting_meta = list_settings.get("Metadata");
				Metadata meta = null;
				if (metadata_storage.containsKey(article_id)) {
					meta = metadata_storage.get(article_id);
				}
				article.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				// article.setSize(width_small, height_small);
				article.setSize(width_small, height_small);
				article.getContentPane().setBackground(new Color(213, 213, 213));

				article.setLocationRelativeTo(null);
				article.setTitle("Article <" + article_id + "> Details");
				article.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {

						issue(issue_id);
						// database_save();
					}
				});
				article.getContentPane().setLayout(null);

				/*
				 * final JButton btnSync = new JButton("Sync");
				 * btnSync.setBounds(width_small - 150, 21, 70, 24);
				 * article.getContentPane().add(btnSync);
				 * 
				 * final Label internetCheck = new Label("  ONLINE");
				 * internetCheck.setFont(new Font(Font.SANS_SERIF,
				 * Font.TRUETYPE_FONT | Font.ITALIC, 12));
				 * internetCheck.setBackground(Color.lightGray);
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
				 * internetCheck.setBackground(Color.lightGray);
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
				btnGoBack.setBounds(width_small - 150, 86, 117, 30);
				article.getContentPane().add(btnGoBack);
				JButton btnEdit = new JButton("Edit");
				btnEdit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						article.dispose();
						edit_article(issue_id, article_id);
					}
				});
				btnEdit.setBounds(25, 86, 117, 30);
				article.getContentPane().add(btnEdit);
				JScrollPane scrollSettings = new JScrollPane();
				scrollSettings.setBounds(40, 180, 320, 200);

				JPanel panel = new JPanel();
				panel.setBackground(SystemColor.window);
				panel.setBounds(50, 107, 300, 307);
				article.getContentPane().add(panel);
				panel.setLayout(null);
				panel.setAutoscrolls(true);
				int fields = 5;
				int settings_height = 210 + 30 * (fields - 8);
				JLabel lblIssueT = new JLabel("Article: " + article_id);
				lblIssueT.setBackground(new Color(46, 46, 46));
				lblIssueT.setForeground(new Color(255, 255, 255));
				lblIssueT.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 26));
				lblIssueT.setBounds(width_small - 242, 26, 280, 40);
				lblIssueT.setOpaque(true);
				article.getContentPane().add(lblIssueT);

				JPanel panel_1 = new JPanel();
				panel_1.setBackground(new Color(46, 46, 46));
				panel_1.setLayout(null);
				panel_1.setBounds(0, 0, width, 70);
				ImageIcon icon = new ImageIcon(String.format("%s/required_files/%s", directory, "toru-ui-logo.png"));
				JLabel logo = new JLabel(icon);
				logo.setBounds(10, 20, 140, 40);
				logo.setBackground(new Color(46, 46, 46));
				panel_1.add(logo);
				panel_1.repaint();
				logo.repaint();
				article.getContentPane().add(panel_1);

				Panel panel_2 = new Panel();
				panel_2.setBackground(new Color(25, 25, 25));
				panel_2.setBounds(0, 70, width, 6);
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
				btnAddMetadata.setBounds(40, height_small - 115, 160, 50);
				btnAddMetadata.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int result = JOptionPane.showConfirmDialog(null, panelMetadata, "Edit Metadata",
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
				abstractSection.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

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
				authorSection.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
				JLabel lblNewLabel_3 = new JLabel("Author Information");
				lblNewLabel_3.setBounds(15, 6, 156, 16);
				panel6.add(lblNewLabel_3);

				ArrayList<Author> authors = article_author_storage.get(current_article.getId());

				int author_x = 16;
				int author_y = 60;
				int separation_horizontal = 205;
				int separation_vertical = 30;
				int label_field_separation = 4;
				System.out.println("ARTICLE AUTHORS: " + authors.size());
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
				authorSection.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

				authorSection.setPreferredSize(new Dimension(600 * authors.size(), 8));
				authorSection.setBounds(width_small / 2 - 40, 132 + height_small / 2 - 130, width_small / 2,
						height_small / 2 - 150);
				// scrollSettings.setViewportView(scrollFrame);
				article.getContentPane().add(authorSection);

				JLabel lblIssues = new JLabel("Article:");
				lblIssues.setBounds(24, 18, 110, 30);
				panel.add(lblIssues);
				lblIssues.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
				lblIssues.setForeground(Color.BLACK);

				JLabel lblIssue = new JLabel("Issue:");
				lblIssue.setBounds(24, 48, 94, 30);
				panel.add(lblIssue);
				lblIssue.setForeground(Color.BLACK);
				lblIssue.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));

				JLabel lblIssueId = new JLabel(Long.toString(issue_id));
				lblIssueId.setBounds(160, 48, 94, 30);
				panel.add(lblIssueId);
				lblIssueId.setForeground(Color.BLACK);
				lblIssueId.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
				lblIssueId.setText(Long.toString(issue_id));

				final JLabel lblDateAccepted = new JLabel("Date Submitted:");
				lblDateAccepted.setForeground(Color.BLACK);
				lblDateAccepted.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
				lblDateAccepted.setBounds(24, 199, 150, 30);
				panel.add(lblDateAccepted);
				final JLabel lblDatePublished = new JLabel("Date Published:");
				lblDatePublished.setForeground(Color.BLACK);
				lblDatePublished.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
				lblDatePublished.setBounds(24, 231, 150, 30);
				panel.add(lblDatePublished);
				new Date();

				JLabel lblDate = new JLabel(current_article.getDate_published() == null ? "/"
						: sdf.format(current_article.getDate_published()));
				lblDate.setVerticalAlignment(SwingConstants.TOP);
				lblDate.setForeground(Color.BLACK);
				lblDate.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
				lblDate.setBounds(160, 240, 125, 30);
				panel.add(lblDate);
				JLabel lblDateAccept = new JLabel(current_article.getDate_submitted() == null ? "/"
						: sdf.format(current_article.getDate_submitted()));
				lblDateAccept.setVerticalAlignment(SwingConstants.TOP);
				lblDateAccept.setForeground(Color.BLACK);
				lblDateAccept.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
				lblDateAccept.setBounds(160, 207, 125, 30);
				panel.add(lblDateAccept);
				JLabel lblArticleId = new JLabel(Long.toString(current_article.getId()));
				lblArticleId.setBounds(160, 19, 94, 30);
				panel.add(lblArticleId);
				lblArticleId.setForeground(Color.BLACK);
				lblArticleId.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
				lblArticleId.setText(Long.toString(article_id));

				JLabel lblPages = new JLabel("Pages:");
				lblPages.setForeground(Color.BLACK);
				lblPages.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
				lblPages.setBounds(24, 165, 94, 30);
				panel.add(lblPages);

				JLabel lblPageNum = new JLabel(current_article.getPages());
				lblPageNum.setVerticalAlignment(SwingConstants.TOP);
				lblPageNum.setForeground(Color.BLACK);
				lblPageNum.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
				lblPageNum.setBounds(160, 171, 125, 30);
				panel.add(lblPageNum);
				JLabel lblDoi = new JLabel("DOI:");
				lblDoi.setForeground(Color.BLACK);
				lblDoi.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
				lblDoi.setBounds(24, 268, 94, 30);
				panel.add(lblDoi);

				final JLabel doi = new JLabel(current_article.getDoi());
				doi.setForeground(Color.BLACK);
				doi.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
				doi.setBounds(85, 269, 180, 30);
				panel.add(doi);
				JLabel lblTitle = new JLabel("Title:");
				lblTitle.setForeground(Color.BLACK);
				lblTitle.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
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
				lblTitleText.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));

				panel9.add(lblTitleText);
				JScrollPane titleSection = new JScrollPane(lblTitleText);
				titleSection.setPreferredSize(new Dimension(300 * 2, 200));
				titleSection.setBounds(85, 113, 225, 50);
				titleSection.add(panel9);
				titleSection.createHorizontalScrollBar();
				panel.add(titleSection);

				JLabel lblSectionId;
				if (section_storage.containsKey((long) current_article.getSection_id())
						&& !section_storage.get((long) current_article.getSection_id()).isDeleted()) {
					lblSectionId = new JLabel(
							section_storage.get((long) current_article.getSection_id()).getTitle().compareTo("") == 0
									? Long.toString(section_storage.get((long) current_article.getSection_id()).getId())
									: section_storage.get((long) current_article.getSection_id()).getTitle());
				} else {
					lblSectionId = new JLabel("None.");
				}
				lblSectionId.setForeground(Color.BLACK);
				lblSectionId.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
				lblSectionId.setBounds(110, 81, 149, 30);
				panel.add(lblSectionId);

				JLabel lblSection = new JLabel("Section:");
				lblSection.setForeground(Color.BLACK);
				lblSection.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
				lblSection.setBounds(24, 80, 94, 30);
				panel.add(lblSection);
				Panel panel10 = new Panel();
				panel10.setBackground(new Color(153, 102, 51));
				panel10.setBounds(115, 315, 225, 160);

				JPanel panel11 = new JPanel();
				panel11.setBounds(265, 285, 265, 190 + 100 * file_storage.keySet().size());
				panel11.setLayout(null);
				panel11.setAutoscrolls(true);
				panel11.setPreferredSize(new Dimension(340, 190 + 280 * file_storage.keySet().size()));
				article.getContentPane().add(panel11);
				String label_text = "";
				System.out.println(file_storage.keySet().toString());

				System.out.println("ID: " + (long) article_id);
				System.out.println("Files: " + file_storage.containsKey((long) article_id));

				if (file_storage.containsKey((long) article_id)) {
					ConcurrentHashMap<Long, ArticleFile> files = file_storage.get((long) article_id);
					if (files != null) {
						Set<Long> keys = files.keySet();
						System.out.println("Files: " + keys.size());
						int y_f = 23;
						for (long key : keys) {
							ImageIcon deleteicon = new ImageIcon(
									String.format("%s/required_files/%s", directory, "remove_xs.png"));
							ImageIcon previewicon = new ImageIcon(
									String.format("%s/required_files/%s", directory, "preview_xs.png"));
							JButton btnDeleteFile = new JButton(deleteicon);

							btnDeleteFile.setMargin(new Insets(0, 0, 0, 0));
							btnDeleteFile.setBorder(null);
							btnDeleteFile.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 12));

							btnDeleteFile.setBounds(240, y_f, 40, 24);
							article.getContentPane().add(btnDeleteFile);
							JButton btnPreview = new JButton(previewicon);

							btnPreview.setMargin(new Insets(0, 0, 0, 0));
							btnPreview.setBorder(null);
							btnPreview.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 12));

							btnPreview.setBounds(150, y_f, 40, 24);
							article.getContentPane().add(btnPreview);
							File f = new File(files.get((long) key).getPath());
							final String filename_process = f.getPath().toString();
							int last_index = -1;
							last_index = filename_process.lastIndexOf("/");
							if (last_index == -1) {
								last_index = filename_process.lastIndexOf("\\");
							}
							final String filename = filename_process.substring(last_index + 1);
							String type = filename.substring(filename.lastIndexOf(".") + 1);

							btnPreview.setAction(new AbstractAction() {
								/**
								 * 
								 */
								private static final long serialVersionUID = 1L;

								public void actionPerformed(ActionEvent e) {
									if (type.toLowerCase().compareTo("jpg") == 0
											|| type.toLowerCase().compareTo("jpeg") == 0
											|| type.toLowerCase().compareTo("png") == 0) {

										try {
											BufferedImage img = ImageIO.read(f);
											int scale_width = 640;
											if (img.getWidth() < scale_width) {
												scale_width = img.getWidth();
											}
											ImageIcon icon = new ImageIcon(
													img.getScaledInstance(scale_width, -1, Image.SCALE_SMOOTH));
											JLabel label = new JLabel(icon);

											JOptionPane.showMessageDialog(null, label,
													String.format("Preview of %s", filename),
													JOptionPane.INFORMATION_MESSAGE);
										} catch (IOException e1) {

											JOptionPane.showMessageDialog(null, String.format(
													"File %s does not exist locally.",
													files.get((long) key).getPath().substring(
															files.get((long) key).getPath().lastIndexOf("/") + 1)));
											boolean is_online = status_online();
											if (is_online) {
												int dialogResult = JOptionPane.showConfirmDialog(null,
														"Would you like to download it ?",

														"Warning", 1);
												if (dialogResult == JOptionPane.YES_OPTION) {
													try {
														file_download(article_id, key, true);
													} catch (IllegalStateException e2) {

														e2.printStackTrace();
													} catch (IOException e2) {

														e2.printStackTrace();
													}
												}
											}
										} catch (NullPointerException ne) {

											JOptionPane.showMessageDialog(null, String.format(
													"File %s does not exist locally.",
													files.get((long) key).getPath().substring(
															files.get((long) key).getPath().lastIndexOf("/") + 1)));
											boolean is_online = status_online();
											if (is_online) {
												int dialogResult = JOptionPane.showConfirmDialog(null,
														"Would you like to download it ?",

														"Warning", 1);
												if (dialogResult == JOptionPane.YES_OPTION) {
													try {
														file_download(article_id, key, true);
													} catch (IllegalStateException e2) {

														e2.printStackTrace();
													} catch (IOException e2) {

														e2.printStackTrace();
													}
												}
											}
										}
									} else if (type.toLowerCase().compareTo("pdf") == 0) {
										String filePath = f.getAbsolutePath();

										// build a component controller
										SwingController controller = new SwingController();

										SwingViewBuilder factory = new SwingViewBuilder(controller);

										JPanel viewerComponentPanel = factory.buildViewerPanel();

										// add interactive mouse link annotation
										// support via callback
										controller.getDocumentViewController()
												.setAnnotationCallback(new org.icepdf.ri.common.MyAnnotationCallback(
														controller.getDocumentViewController()));

										JFrame applicationFrame = new JFrame();
										applicationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
										applicationFrame.getContentPane().add(viewerComponentPanel);

										// Now that the GUI is all in place, we
										// can try openning a PDF
										controller.openDocument(filePath);

										// show the component
										applicationFrame.pack();
										applicationFrame.setVisible(true);

										// Now that the GUI is all in place, we
										// can try openning a PDF
										controller.openDocument(filePath);

										// show the component
									} else if (type.toLowerCase().compareTo("txt") == 0
											|| type.toLowerCase().compareTo("html") == 0
											|| type.toLowerCase().compareTo("xml") == 0) {

										try {

											JTextArea ta = new JTextArea(20, 60);
											ta.read(new FileReader(f), null);
											ta.setEditable(false);
											JOptionPane.showMessageDialog(null, new JScrollPane(ta),
													String.format("Preview of %s", filename),
													JOptionPane.INFORMATION_MESSAGE);

										} catch (IOException e1) {

											JOptionPane.showMessageDialog(null, String.format(
													"File %s does not exist locally.",
													files.get((long) key).getPath().substring(
															files.get((long) key).getPath().lastIndexOf("/") + 1)));
											boolean is_online = status_online();
											if (is_online) {
												int dialogResult = JOptionPane.showConfirmDialog(null,
														"Would you like to download it ?",

														"Warning", 1);
												if (dialogResult == JOptionPane.YES_OPTION) {
													try {
														file_download(article_id, key, true);
													} catch (IllegalStateException e2) {

														e2.printStackTrace();
													} catch (IOException e2) {

														e2.printStackTrace();
													}
												}
											}
										} catch (NullPointerException ne) {

											JOptionPane.showMessageDialog(null, String.format(
													"File %s does not exist locally.",
													files.get((long) key).getPath().substring(
															files.get((long) key).getPath().lastIndexOf("/") + 1)));
											boolean is_online = status_online();
											if (is_online) {
												int dialogResult = JOptionPane.showConfirmDialog(null,
														"Would you like to download it ?",

														"Warning", 1);
												if (dialogResult == JOptionPane.YES_OPTION) {
													try {
														file_download(article_id, key, true);
													} catch (IllegalStateException e2) {

														e2.printStackTrace();
													} catch (IOException e2) {

														e2.printStackTrace();
													}
												}
											}
										}
									} else if (type.toLowerCase().compareTo("doc") == 0
											|| type.toLowerCase().compareTo("docx") == 0) {

										try {
											String extractedText = "Cannot render file.";
											Tika tika = new Tika();
											try {
												extractedText = tika.parseToString(f);
											} catch (TikaException e1) {
												// TODO Auto-generated catch
												// block
												e1.printStackTrace();
											}
											JTextArea ta = new JTextArea(20, 60);
											ta.setText(extractedText);
											ta.setEditable(false);
											JOptionPane.showMessageDialog(null, new JScrollPane(ta),
													String.format("Preview of %s", filename),
													JOptionPane.INFORMATION_MESSAGE);

										} catch (IOException e1) {

											JOptionPane.showMessageDialog(null, String.format(
													"File %s does not exist locally.",
													files.get((long) key).getPath().substring(
															files.get((long) key).getPath().lastIndexOf("/") + 1)));
											boolean is_online = status_online();
											if (is_online) {
												int dialogResult = JOptionPane.showConfirmDialog(null,
														"Would you like to download it ?",

														"Warning", 1);
												if (dialogResult == JOptionPane.YES_OPTION) {
													try {
														file_download(article_id, key, true);
													} catch (IllegalStateException e2) {

														e2.printStackTrace();
													} catch (IOException e2) {

														e2.printStackTrace();
													}
												}
											}
										} catch (NullPointerException ne) {

											JOptionPane.showMessageDialog(null, String.format(
													"File %s does not exist locally.",
													files.get((long) key).getPath().substring(
															files.get((long) key).getPath().lastIndexOf("/") + 1)));
											boolean is_online = status_online();
											if (is_online) {
												int dialogResult = JOptionPane.showConfirmDialog(null,
														"Would you like to download it ?",

														"Warning", 1);
												if (dialogResult == JOptionPane.YES_OPTION) {
													try {
														file_download(article_id, key, true);
													} catch (IllegalStateException e2) {

														e2.printStackTrace();
													} catch (IOException e2) {

														e2.printStackTrace();
													}
												}
											}
										}

									}

								}
							});
							btnPreview.setIcon(previewicon);
							panel11.add(btnPreview);
							if (type.toLowerCase().compareTo("jpg") == 0 || type.toLowerCase().compareTo("jpeg") == 0
									|| type.toLowerCase().compareTo("png") == 0
									|| type.toLowerCase().compareTo("pdf") == 0
									|| type.toLowerCase().compareTo("txt") == 0
									|| type.toLowerCase().compareTo("html") == 0
									|| type.toLowerCase().compareTo("xml") == 0
									|| type.toLowerCase().compareTo("doc") == 0
									|| type.toLowerCase().compareTo("docx") == 0) {
								btnPreview.setEnabled(true);
							} else {
								btnPreview.setEnabled(false);
								btnPreview.repaint();
								panel11.repaint();
							}
							ImageIcon saveicon = new ImageIcon(
									String.format("%s/required_files/%s", directory, "save_xs.png"));
							JButton btnSaveFile = new JButton(saveicon);
							btnSaveFile.setMargin(new Insets(0, 0, 0, 0));
							btnSaveFile.setBorder(null);
							btnSaveFile.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 12));

							btnSaveFile.setBounds(195, y_f, 40, 24);
							article.getContentPane().add(btnSaveFile);
							JLabel file_l = new JLabel(files.get((long) key).getPath()
									.substring(files.get((long) key).getPath().lastIndexOf("/") + 1));
							file_l.setBounds(10, y_f, 120, 24);
							panel11.add(file_l);
							y_f = y_f + 20;
							btnSaveFile.setAction(new AbstractAction() {
								/**
								 * 
								 */
								private static final long serialVersionUID = 1L;

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
										System.out
												.println("Save as file: " + fileChooser.getCurrentDirectory().getPath()
														+ files.get((long) key).getPath().substring(
																files.get((long) key).getPath().lastIndexOf("/")));
										String output_path = fileChooser.getCurrentDirectory().getPath() + "/"
												+ fileChooser.getSelectedFile().getName();
										System.out.println(output_path);
										System.out.println(fileChooser.getSelectedFile().getName());
										// files.get((long)key).getPath().substring(files.get((long)key).getPath().lastIndexOf("/"));

										try {
											Files.copy(Paths.get(files.get((long) key).getPath()),
													Paths.get(output_path), StandardCopyOption.REPLACE_EXISTING);
										} catch (IOException e1) {

											JOptionPane.showMessageDialog(null, String.format(
													"File %s does not exist locally.",
													files.get((long) key).getPath().substring(
															files.get((long) key).getPath().lastIndexOf("/") + 1)));
											boolean is_online = status_online();
											if (is_online) {
												int dialogResult = JOptionPane.showConfirmDialog(null,
														"Would you like to download it ?",

														"Warning", 1);
												if (dialogResult == JOptionPane.YES_OPTION) {
													try {
														file_download(article_id, key, true);
													} catch (IllegalStateException e2) {

														e2.printStackTrace();
													} catch (IOException e2) {

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
								/**
								 * 
								 */
								private static final long serialVersionUID = 1L;

								public void actionPerformed(ActionEvent e) {

									File f = new File(files.get((long) key).getPath());
									f.delete();
									ConcurrentHashMap<Long, ArticleFile> deleted = file_storage.get((long) article_id);
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
				}
				JTextArea lblFile = new JTextArea(label_text);
				lblFile.setForeground(Color.WHITE);
				lblFile.setEnabled(false);
				lblFile.setBounds(115, 346, 225, 160);
				lblFile.setToolTipText("");
				JScrollPane fileSection = new JScrollPane(panel11);

				fileSection.setPreferredSize(new Dimension(300 * 2, 100 * file_storage.size()));
				fileSection.setBounds(10, 346, 310, 160);
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
								String type = FilenameUtils.getExtension(f.getPath());
								if (type.toLowerCase().compareTo("xml") == 0) {
									if (list_settings.containsKey("Validate XML (JATS)")
											&& Boolean.parseBoolean(list_settings.get("Validate XML (JATS)"))) {

										try {
											boolean valid = false;
											valid = validate_xml(f);
											if (!valid) {
												JOptionPane.showMessageDialog(null,
														"Invalid XML file. Note: JATS version 1.1 is used for validation.");
											} else {
												uploaded_files.add(f);
												label_text = label_text + f.getName() + "\n";
											}
										} catch (HeadlessException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										} catch (org.xml.sax.SAXException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										} catch (IOException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										} catch (ParserConfigurationException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}

									} else {
										uploaded_files.add(f);
										label_text = label_text + f.getName() + "\n";
									}
								} else if (type.toLowerCase().compareTo("png") == 0
										|| type.toLowerCase().compareTo("jpg") == 0
										|| type.toLowerCase().compareTo("jpeg") == 0) {
									if (list_settings.containsKey("Optimize Images")
											&& Boolean.parseBoolean(list_settings.get("Optimize Images"))) {
										try {
											File optimized = optimize_image(f);
											uploaded_files.add(optimized);
											label_text = label_text + optimized.getName() + "\n";
										} catch (IOException e1) {
											uploaded_files.add(f);
											label_text = label_text + f.getName() + "\n";

										}
									}
								} else if (type.toLowerCase().compareTo("pdf") == 0) {
									if (list_settings.containsKey("Optimize PDFs")
											&& Boolean.parseBoolean(list_settings.get("Optimize PDFs"))) {

										File optimized;
										try {
											optimized = optimize_pdf(f);
											uploaded_files.add(optimized);
											label_text = label_text + optimized.getName() + "\n";

										} catch (IOException e1) {
											uploaded_files.add(f);
											label_text = label_text + f.getName() + "\n";

										} catch (DocumentException e1) {
											uploaded_files.add(f);
											label_text = label_text + f.getName() + "\n";

										}
									} else {
										uploaded_files.add(f);
										label_text = label_text + f.getName() + "\n";

									}
								} else {
									uploaded_files.add(f);
									label_text = label_text + f.getName() + "\n";

								}
							}
							if (!uploaded_files.isEmpty()) {
								label_tooltip = files.length + " files";
								lblFile.setText(label_text);
								lblFile.setToolTipText(label_tooltip);
							}
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
						File folder = new File(
								String.format(String.format("%s/required_files/%s", directory, "images/")));
						folder.delete();
						try {
							FileUtils.deleteDirectory(folder);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						System.out.println("deleted images");
						folder = new File(String.format(String.format("%s/required_files/%s", directory, "pdf/")));
						folder.delete();
						try {
							FileUtils.deleteDirectory(folder);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						System.out.println("deleted pdf");
					}
				});
				upload.setBounds(150, 310, 90, 30);

				panel.add(upload);
				final JButton btnDashboard = new JButton("Dashboard");
				btnDashboard.setBounds(width_small - 375, height_small - 80, 120, 29);
				btnDashboard.setEnabled(true);
				btnDashboard.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						article.dispose();
						dashboard();
					}
				});
				article.getContentPane().add(btnDashboard);
				final JButton btnIssue = new JButton("Issue");
				btnIssue.setBounds(width_small - 255, height_small - 80, 120, 29);
				btnIssue.setEnabled(true);
				btnIssue.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						article.dispose();
						issue(issue_id);
					}
				});
				article.getContentPane().add(btnIssue);
				final JButton btnArticle = new JButton("Article");
				btnArticle.setBounds(width_small - 135, height_small - 80, 120, 29);
				btnArticle.setEnabled(false);
				btnArticle.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						article.dispose();
						article(issue_id, article_id);
					}
				});
				article.getContentPane().add(btnArticle);
				article.repaint();
				article.getContentPane().repaint();
				article.setVisible(true);
				if (article_screens.containsKey(issue_id)) {
					ConcurrentHashMap<Long, JFrame> issue_articles = article_screens.get(issue_id);
					issue_articles.put(article_id, article);
					article_screens.put(issue_id, issue_articles);
				}
			}

		} else {
			login("dashboard");
		}
	}

	// reference:
	// http://developers.itextpdf.com/examples/itext-action-second-edition/chapter-6#253-concatenatestamp.java
	public File insert_doi_pdf(File f, String doi) {
		try {
			File compressed = null;
			String filename = f.getPath().toString();
			int last_index = -1;
			last_index = filename.lastIndexOf("/");
			if (last_index == -1) {
				last_index = filename.lastIndexOf("\\");
			}
			filename = filename.substring(last_index + 1);

			File file_directory = new File(String.format("%s/required_files/pdf/", directory));
			file_directory.mkdirs();
			PdfReader reader = new PdfReader(new FileInputStream(f));

			com.itextpdf.text.Document document = new com.itextpdf.text.Document();
			// com.itextpdf.text.Document document = new
			// com.itextpdf.text.Document(f);

			PdfCopy copy = new PdfCopy(document,
					new FileOutputStream(String.format("%s/required_files/pdf/%s", directory, filename)));
			document.open();
			PdfCopy.PageStamp stamp;
			PdfImportedPage page;
			int total = reader.getNumberOfPages();
			if (total == 1) {
				page = copy.getImportedPage(reader, 1);
				stamp = copy.createPageStamp(page);
				ColumnText.showTextAligned(stamp.getUnderContent(), Element.ALIGN_CENTER,
						new Phrase(String.format("%s", doi)), 297.5f, 28, 0);
				stamp.alterContents();
				copy.addPage(page);
			} else {
				for (int i = 1; i < total;) {
					page = copy.getImportedPage(reader, ++i);
					stamp = copy.createPageStamp(page);
					ColumnText.showTextAligned(stamp.getUnderContent(), Element.ALIGN_CENTER,
							new Phrase(String.format("%s", doi)), 297.5f, 28, 0);
					stamp.alterContents();
					copy.addPage(page);
				}
			}
			document.close();
			reader.close();
			if (list_settings.containsKey("Optimize PDFs")
					&& Boolean.parseBoolean(list_settings.get("Optimize PDFs"))) {
				copy.setFullCompression();
			}
			copy.close();
			compressed = new File(String.format(String.format("%s/required_files/pdf/%s", directory, filename)));
			return compressed;
		} catch (IOException e) {
			return f;
		} catch (DocumentException es) {
			return f;

		}
	}

	public File optimize_pdf(File f) throws IOException, DocumentException {

		File compressed = null;
		String filename = f.getPath().toString();
		int last_index = -1;
		last_index = filename.lastIndexOf("/");
		if (last_index == -1) {
			last_index = filename.lastIndexOf("\\");
		}
		filename = filename.substring(last_index + 1);

		File file_directory = new File(String.format("%s/required_files/pdf/", directory));
		file_directory.mkdirs();
		PdfReader reader = new PdfReader(new FileInputStream(f));
		PdfStamper stamper = new PdfStamper(reader,
				new FileOutputStream(String.format("%s/required_files/pdf/%s", directory, filename)));
		int total = reader.getNumberOfPages() + 1;
		for (int i = 1; i < total; i++) {
			reader.setPageContent(i + 1, reader.getPageContent(i + 1));
		}
		stamper.setFullCompression();
		stamper.close();
		compressed = new File(String.format("%s/required_files/pdf/%s", directory, filename));
		return compressed;
	}

	// reference:
	// http://www.tutorialspoint.com/java_dip/image_compression_technique.htm

	public File optimize_image(File f) throws IOException {
		BufferedImage image = ImageIO.read(f);
		File compressed = null;
		String filename = f.getPath().toString();
		int last_index = -1;
		last_index = filename.lastIndexOf("/");
		if (last_index == -1) {
			last_index = filename.lastIndexOf("\\");
		}
		filename = filename.substring(last_index + 1);
		String type = filename.substring(filename.lastIndexOf(".") + 1);

		File file_directory = new File(String.format("%s/required_files/images/", directory));
		file_directory.mkdirs();
		if (type.compareTo("jpg") == 0 || type.compareTo("jpeg") == 0) {
			compressed = new File(String.format("%s/required_files/images/%s", directory, filename));
			OutputStream os = new FileOutputStream(compressed);
			System.out.println(filename.substring(filename.lastIndexOf(".") + 1));
			Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
			ImageWriter writer = (ImageWriter) writers.next();

			ImageOutputStream ios = ImageIO.createImageOutputStream(os);
			writer.setOutput(ios);

			ImageWriteParam param = writer.getDefaultWriteParam();

			param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			param.setCompressionQuality(0.8f);
			writer.write(null, new IIOImage(image, null, null), param);

			os.close();
			ios.close();
			writer.dispose();
		} else if (type.compareTo("png") == 0) {
			// reference:
			// http://www.mkyong.com/java/convert-png-to-jpeg-image-file-in-java/
			// http://stackoverflow.com/questions/17108234/setting-jpg-compression-level-with-imageio-in-java

			BufferedImage jpgImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
			jpgImage.createGraphics().drawImage(image, 0, 0, Color.WHITE, null);
			compressed = new File(String.format(String.format("%s/required_files/images/%s.jpg", directory,
					filename.substring(0, filename.lastIndexOf('.')))));
			JPEGImageWriteParam jpegParams = new JPEGImageWriteParam(null);
			jpegParams.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			jpegParams.setCompressionQuality(0.8f);

			final ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
			// specifies where the jpg image has to be written
			writer.setOutput(new FileImageOutputStream(compressed));

			// writes the file with given compression level
			// from your JPEGImageWriteParam instance
			writer.write(null, new IIOImage(jpgImage, null, null), jpegParams);

			// ImageIO.write(jpgImage, "jpg", compressed);

		}
		if (compressed == null) {
			return f;
		}
		return compressed;

	}

	public boolean validate_xml(File f) throws IOException, ParserConfigurationException, org.xml.sax.SAXException {
		boolean validation = false;
		try {
			// parse an XML document into a DOM tree
			DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();

			// create a SchemaFactory capable of understanding WXS schemas
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

			File schema_file = new File(String.format("%s/required_files/xml/JATS-journalpublishing1.xsd", directory));
			// load a WXS schema, represented by a Schema instance
			Source schemaFile = new StreamSource(schema_file);
			Schema schema = factory.newSchema(schemaFile);

			// create a Validator instance, which can be used to validate an
			// instance document
			Validator validator = schema.newValidator();

			String filename = f.getPath().toString();
			int last_index = -1;
			last_index = filename.lastIndexOf("/");
			if (last_index == -1) {
				last_index = filename.lastIndexOf("\\");
			}
			filename = filename.substring(last_index + 1);
			System.out.print(String.format("%s/required_files/xml/%s", directory, filename));
			File dir = new File(String.format("%s/required_files/xml/", directory));
			dir.mkdirs();
			Files.copy(Paths.get(f.getPath().toString()),
					Paths.get(String.format("%s/required_files/xml/%s", directory, filename)),
					StandardCopyOption.REPLACE_EXISTING);
			File temp = new File(String.format("%s/required_files/xml/%s", directory, filename));
			try {
				Document document = parser.parse(temp);
			} catch (org.xml.sax.SAXException e) {
				// instance document is invalid!
				// e.printStackTrace();
				JOptionPane.showMessageDialog(null, String.format("XML error: %s", e.getMessage()));
				validation = false;
			}

			try {
				validator.validate(new StreamSource(new File(temp.getPath())));
				validation = true;
			} catch (org.xml.sax.SAXException e) {
				// instance document is invalid!
				// e.printStackTrace();
				JOptionPane.showMessageDialog(null, String.format("XML error: %s", e.getMessage()));
				validation = false;
			}
			temp.delete();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File not found");

			validation = false;
		}

		return validation;
	}

	public void edit_article(final long issue_id, final long article_id) {
		if (logged_in) {
			if (article_screens.containsKey(issue_id) && article_screens.get(issue_id).containsKey(article_id)
					&& !article_screens.get(issue_id).get(article_id).isVisible()) {
				Article current_article = issue_storage.get(issue_id).getArticles_list().get(article_id);
				int width_small = 0;
				int height_small = 0;
				width_small = 920;
				height_small = 768;
				final JFrame article = new JFrame();
				article.setResizable(false);
				Metadata meta = null;
				if (metadata_storage.containsKey(article_id)) {
					meta = metadata_storage.get(article_id);
				}
				article.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				// article.setSize(width_small, height_small);
				article.setSize(width_small, height_small);
				article.setTitle("Editing - Article <" + article_id + ">");
				article.getContentPane().setBackground(new Color(213, 213, 213));
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
				article.getContentPane().setLayout(null);

				/*
				 * final JButton btnSync = new JButton("Sync");
				 * btnSync.setBounds(width_small - 150, 21, 70, 24);
				 * article.getContentPane().add(btnSync);
				 * 
				 * final Label internetCheck = new Label("  ONLINE");
				 * internetCheck.setFont(new Font(Font.SANS_SERIF,
				 * Font.TRUETYPE_FONT | Font.ITALIC, 12));
				 * internetCheck.setBackground(Color.lightGray);
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
				 * internetCheck.setBackground(Color.lightGray);
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
				btnGoBack.setBounds(width_small - 150, 86, 117, 30);
				article.getContentPane().add(btnGoBack);
				JScrollPane scrollSettings = new JScrollPane();
				scrollSettings.setBounds(40, 180, 320, 200);

				JPanel panel = new JPanel();
				panel.setBackground(SystemColor.window);
				panel.setBounds(50, 107, 300, 307);
				article.getContentPane().add(panel);
				panel.setLayout(null);
				panel.setAutoscrolls(true);
				int fields = 5;
				int settings_height = 210 + 30 * (fields - 8);

				JLabel lblIssueT = new JLabel("Edit Article: " + article_id);
				lblIssueT.setBackground(new Color(46, 46, 46));
				lblIssueT.setForeground(new Color(255, 255, 255));
				lblIssueT.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 26));
				lblIssueT.setBounds(width_small - 272, 26, 280, 40);
				lblIssueT.setOpaque(true);
				article.getContentPane().add(lblIssueT);

				JPanel panel_1 = new JPanel();
				panel_1.setBackground(new Color(46, 46, 46));
				panel_1.setLayout(null);
				panel_1.setBounds(0, 0, width, 70);
				ImageIcon icon = new ImageIcon(String.format("%s/required_files/%s", directory, "toru-ui-logo.png"));
				JLabel logo = new JLabel(icon);
				logo.setBounds(10, 20, 140, 40);
				logo.setBackground(new Color(46, 46, 46));
				panel_1.add(logo);
				panel_1.repaint();
				logo.repaint();
				article.getContentPane().add(panel_1);

				Panel panel_2 = new Panel();
				panel_2.setBackground(new Color(25, 25, 25));
				panel_2.setBounds(0, 70, width, 6);
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
				abstractSection.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

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

				DefaultListModel<String> listModel = new DefaultListModel<String>();
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
				JList<String> listbox = new JList<String>();
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
							new_author.setArticle_id(article_id);
							author_storage.put(author_id, new_author);
							System.out.println("Author id :" + author_id);
							System.out.println(new_author);
							ConcurrentHashMap<Long, Boolean> current_authors = author_primary_storage
									.get((long) article_id);
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

							ConcurrentHashMap<Long, Boolean> primary_storage = author_primary_storage.get(article_id);
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
				JTextArea txtCompetingInterests = new JTextArea(metadata_storage.get((long) article_id) == null ? ""
						: metadata_storage.get((long) article_id).getCompeting_interests());

				txtCompetingInterests.setColumns(4);
				txtCompetingInterests.setBounds(35, 70, 240, 100);

				JScrollPane cmptinterests = new JScrollPane(txtCompetingInterests);
				cmptinterests.setBounds(35, 70, 240, 100);
				panelMetadata.add(cmptinterests);
				// scrollSettings.setViewportView(scrollFrame);

				JLabel lblFunding = new JLabel("Funding");
				lblFunding.setBounds(35, 175, 145, 15);
				panelMetadata.add(lblFunding);

				JTextArea txtFunding = new JTextArea(metadata_storage.get((long) article_id) == null ? ""
						: metadata_storage.get((long) article_id).getFunding());
				if (meta != null) {
					txtFunding.setText(meta.getFunding());
				}
				txtFunding.setColumns(4);
				txtFunding.setBounds(35, 195, 240, 100);

				JScrollPane funding = new JScrollPane(txtFunding);
				funding.setBounds(35, 195, 240, 100);
				panelMetadata.add(funding);
				panelMetadata.setPreferredSize(new Dimension(360, 380));

				panelMetadata.setSize(new Dimension(460, 380));
				JButton btnAddMetadata = new JButton("Edit Metadata");

				btnAddMetadata.setBounds(40, height_small - 115, 160, 45);
				btnAddMetadata.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int result = JOptionPane.showConfirmDialog(null, panelMetadata, "Edit Metadata",
								JOptionPane.OK_CANCEL_OPTION);
						if (result == JOptionPane.OK_OPTION) {
							Metadata meta = metadata_storage.get((long) article_id);
							if (meta == null) {
								metadata_id++;
								meta = new Metadata(metadata_id, article_id);
							}
							meta.setCompeting_interests(txtCompetingInterests.getText());
							meta.setFunding(txtFunding.getText());
							metadata_storage.put((long) article_id, meta);
						}
					}
				});

				if (setting_meta.compareToIgnoreCase("true") == 0) {
					panel.add(btnAddMetadata);
					article.getContentPane().add(btnAddMetadata);
				}
				final ConcurrentHashMap<Long, ConcurrentHashMap<Long, JTextField>> author_fields = new ConcurrentHashMap<Long, ConcurrentHashMap<Long, JTextField>>();
				final ConcurrentHashMap<Long, JTextArea> authors_bio = new ConcurrentHashMap<Long, JTextArea>();

				final ConcurrentHashMap<Long, JButton> primary_buttons = new ConcurrentHashMap<Long, JButton>();
				final ConcurrentHashMap<Long, JLabel> primary_labels = new ConcurrentHashMap<Long, JLabel>();

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

								ConcurrentHashMap<Long, Boolean> update_primary = author_primary_storage
										.get(article_id);

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

								ConcurrentHashMap<Long, Boolean> update_primary = author_primary_storage
										.get(article_id);

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
					ConcurrentHashMap<Long, JTextField> author_components = new ConcurrentHashMap<Long, JTextField>();

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
				lblIssues.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
				lblIssues.setForeground(Color.BLACK);

				JLabel lblIssue = new JLabel("Issue:");
				lblIssue.setBounds(24, 48, 94, 30);
				panel.add(lblIssue);
				lblIssue.setForeground(Color.BLACK);
				lblIssue.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));

				JLabel lblIssueId = new JLabel(Long.toString(issue_id));
				lblIssueId.setBounds(160, 48, 94, 30);
				panel.add(lblIssueId);
				lblIssueId.setForeground(Color.BLACK);
				lblIssueId.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
				lblIssueId.setText(Long.toString(issue_id));

				final JLabel lblDateAccepted = new JLabel("Date Submitted:");
				lblDateAccepted.setForeground(Color.BLACK);
				lblDateAccepted.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
				lblDateAccepted.setBounds(24, 199, 150, 30);
				panel.add(lblDateAccepted);
				final JLabel lblDatePublished = new JLabel("Date Published:");
				lblDatePublished.setForeground(Color.BLACK);
				lblDatePublished.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
				lblDatePublished.setBounds(24, 231, 150, 30);
				panel.add(lblDatePublished);
				new Date();

				final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

				/*
				 * JLabel lblDate = new JLabel(sdf.format(current));
				 * lblDate.setVerticalAlignment(SwingConstants.TOP);
				 * lblDate.setForeground(Color.BLACK); lblDate.setFont(new
				 * Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
				 * lblDate.setBounds(160, 203, 125, 30); panel.add(lblDate);
				 */

				JLabel lblArticleId = new JLabel("1");
				lblArticleId.setBounds(160, 19, 94, 30);
				panel.add(lblArticleId);
				lblArticleId.setForeground(Color.BLACK);
				lblArticleId.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
				lblArticleId.setText(Long.toString(article_id));

				JLabel lblPages = new JLabel("Pages:");
				lblPages.setForeground(Color.BLACK);
				lblPages.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
				lblPages.setBounds(24, 165, 94, 30);
				panel.add(lblPages);

				final JTextField lblPageNum = new JTextField(current_article.getPages());
				lblPageNum.setForeground(Color.BLACK);
				lblPageNum.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
				lblPageNum.setBounds(156, 171, 125, 30);
				panel.add(lblPageNum);

				JLabel lblTitle = new JLabel("Title:");
				lblTitle.setForeground(Color.BLACK);
				lblTitle.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
				lblTitle.setBounds(24, 115, 94, 30);
				panel.add(lblTitle);

				Panel panel9 = new Panel();
				panel9.setBackground(new Color(153, 102, 51));
				panel9.setBounds(85, 118, 180, 80);
				final JTextArea lblTitleText = new JTextArea(current_article.getTitle());
				lblTitleText.setEditable(true);
				lblTitleText.setOpaque(true);
				lblTitleText.setForeground(Color.BLACK);
				lblTitleText.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));

				panel9.add(lblTitleText);
				JScrollPane titleSection = new JScrollPane(lblTitleText);
				titleSection.setPreferredSize(new Dimension(300 * 2, 200));
				titleSection.setBounds(85, 113, 225, 50);
				titleSection.add(panel9);
				titleSection.createHorizontalScrollBar();
				panel.add(titleSection);

				final JComboBox<String> lblSectionId = new JComboBox<String>();
				Set<Long> section_keys = section_storage.keySet();
				ArrayList<Section> sections = new ArrayList<Section>();
				int selected_section = -1;
				int count = 0;
				for (long key : section_keys) {
					if (!section_storage.get((long) key).isDeleted()) {
						lblSectionId.addItem(section_storage.get(key).getTitle());
						sections.add(section_storage.get(key));
						System.out.println("Count: " + count + " Section: " + current_article.getSection_id());
						if (current_article.getSection_id() == section_storage.get(key).getId()) {
							selected_section = count;
							System.out.println("selected section: " + selected_section);
						}
						count++;
					}
				}
				if (selected_section != -1) {
					lblSectionId.setSelectedIndex(selected_section);
				}

				// final JTextField lblSectionId = new
				// JTextField(Integer.toString(current_article.getSection_id()));
				lblSectionId.setForeground(Color.BLACK);
				lblSectionId.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 12));
				lblSectionId.setBounds(95, 83, 185, 26);
				panel.add(lblSectionId);
				JButton btnAddSections = new JButton("+");
				btnAddSections.setBounds(280, 83, 45, 27);
				panel.add(btnAddSections);
				SpinnerNumberModel seq_model;
				SpinnerModel count_model;

				seq_model = new SpinnerNumberModel(0.0, -1000.0, 1000.0, 0.1);

				count_model = new SpinnerNumberModel(0, 0, 5000, 1);

				JPanel panelSection = new JPanel();
				panelSection.setBounds(0, 0, 480, 240);
				panelSection.setLayout(null);

				JTextField txtSectionTitle = new JTextField();
				txtSectionTitle.setBounds(90, 45, 300, 30);
				panelSection.add(txtSectionTitle);
				txtSectionTitle.setColumns(4);
				final JCheckBox disable_comments = new JCheckBox("Disable Comments", false);

				disable_comments.setBounds(240, 80, 140, 26);
				panelSection.add(disable_comments);
				final JCheckBox abstracts_not_required = new JCheckBox("Abstracts Not Required", false);

				abstracts_not_required.setBounds(240, 106, 200, 26);
				panelSection.add(abstracts_not_required);
				final JCheckBox editor_restricted = new JCheckBox("Editor Restricted", false);

				editor_restricted.setBounds(240, 132, 200, 26);
				panelSection.add(editor_restricted);
				final JCheckBox hide_title = new JCheckBox("Hide Title", false);

				hide_title.setBounds(80, 80, 100, 26);
				panelSection.add(hide_title);
				final JCheckBox hide_author = new JCheckBox("Hide Author", false);

				hide_author.setBounds(80, 106, 100, 26);
				panelSection.add(hide_author);
				final JCheckBox hide_about = new JCheckBox("Hide About", false);

				hide_about.setBounds(80, 132, 100, 26);
				panelSection.add(hide_about);

				final JCheckBox metadata_indexed = new JCheckBox("Metadata Indexed", false);

				metadata_indexed.setBounds(80, 158, 180, 26);
				panelSection.add(metadata_indexed);

				final JCheckBox metadata_reviewed = new JCheckBox("Metadata Reviewed", false);

				metadata_reviewed.setBounds(80, 184, 180, 26);
				panelSection.add(metadata_reviewed);

				JLabel lblSeq = new JLabel("Seq");
				lblSeq.setHorizontalAlignment(SwingConstants.CENTER);
				lblSeq.setBounds(40, 215, 200, 20);
				panelSection.add(lblSeq);

				JSpinner txtSeq = new JSpinner(seq_model);
				txtSeq.setBounds(70, 236, 150, 30);
				panelSection.add(txtSeq);

				JLabel lblAbstractCount = new JLabel("Abstract Word Count");
				lblAbstractCount.setHorizontalAlignment(SwingConstants.CENTER);
				lblAbstractCount.setBounds(240, 215, 200, 20);
				panelSection.add(lblAbstractCount);

				JSpinner txtAbstractCount = new JSpinner(count_model);
				txtAbstractCount.setBounds(270, 236, 150, 30);
				panelSection.add(txtAbstractCount);

				JLabel lblTitleSection = new JLabel("Title");
				lblTitleSection.setHorizontalAlignment(SwingConstants.CENTER);
				lblTitleSection.setBounds(190, 25, 100, 20);
				panelSection.add(lblTitleSection);
				panelSection.setBounds(0, 0, 480, 280);
				panelSection.setSize(new Dimension(480, 280));
				panelSection.setPreferredSize(new Dimension(480, 280));
				panelSection.setVisible(true);
				btnAddSections.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						panelSection.setVisible(true);
						panelSection.setEnabled(true);
						boolean validation = false;
						int result = JOptionPane.showConfirmDialog(null, panelSection, "Add Section",
								JOptionPane.OK_CANCEL_OPTION);
						String title = txtSectionTitle.getText();
						if (title == null || title.isEmpty() || title.compareTo("") == 0 || title.compareTo(" ") == 0) {
							validation = false;
						} else {
							validation = true;
						}
						try {
							int wordcount = (Integer) txtAbstractCount.getValue();
							double seq = (Double) txtSeq.getValue();
							validation = true;
						} catch (Exception es) {
							validation = false;
						}

						if (result == JOptionPane.OK_OPTION && validation == true) {
							section_db_id++;
							Section new_section = new Section(section_db_id, txtSectionTitle.getText());
							new_section.setAbstract_word_count((Integer) txtAbstractCount.getValue());
							new_section.setSeq((Double) txtSeq.getValue());
							new_section.setHide_about(hide_about.isSelected() == true ? 1 : 0);
							new_section.setHide_author(hide_author.isSelected() == true ? 1 : 0);
							new_section.setHide_title(hide_title.isSelected() == true ? 1 : 0);
							new_section.setEditor_restricted(editor_restricted.isSelected() == true ? 1 : 0);
							new_section.setMeta_indexed(metadata_indexed.isSelected() == true ? 1 : 0);
							new_section.setMeta_reviewed(metadata_reviewed.isSelected() == true ? 1 : 0);
							new_section.setDisable_comments(disable_comments.isSelected() == true ? 1 : 0);
							new_section.setAbstracts_not_required(abstracts_not_required.isSelected() == true ? 1 : 0);
							new_section.setSync(true);
							section_storage.put(section_db_id, new_section);
							lblSectionId.addItem(new_section.getTitle());
							sections.add(new_section);
							lblSectionId.repaint();
						}
					}
				});

				final JXDatePicker datePickerSubmitted = new JXDatePicker();
				datePickerSubmitted.setFormats(sdf);
				final JLabel label_accepted = new JLabel();
				label_accepted.setText("Choose Date by selecting below.");
				datePickerSubmitted.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						label_accepted.setText(datePickerSubmitted.getDate().toString());
						System.out.println(datePickerSubmitted.getDate());
					}
				});
				label_accepted.setBounds(160, 250, 100, 25);
				datePickerSubmitted.setBounds(156, 202, 160, 30);

				datePickerSubmitted.setDate(current_article.getDate_submitted());
				// panel.add(label);
				panel.add(datePickerSubmitted);
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
				lblDoi.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
				lblDoi.setBounds(24, 268, 94, 30);
				panel.add(lblDoi);

				final JTextField doi = new JTextField(current_article.getDoi());
				doi.setForeground(Color.BLACK);
				doi.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
				doi.setBounds(85, 269, 180, 30);
				panel.add(doi);
				JLabel lblSection = new JLabel("Section:");
				lblSection.setForeground(Color.BLACK);
				lblSection.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
				lblSection.setBounds(24, 80, 94, 30);
				panel.add(lblSection);

				JTextArea lblFile = new JTextArea("");
				String label_text = "";
				JButton btnSave = new JButton("Save");
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Boolean validation = true;
						long entered_sectionID = 0;
						lblPageNum.getText();

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

							sdf.format(datePickerSubmitted.getDate());

						} catch (Exception ex) {
							validation = false;
							JOptionPane.showMessageDialog(null, "Use dates from calendar for fields: Date Submitted");
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
							a.setSync(true);
							a.setTitle(lblTitleText.getText());
							ArrayList<Author> updated_authors = a.getAuthors();
							for (int i = 0; i < updated_authors.size(); i++) {
								Author author = updated_authors.get(i);
								ConcurrentHashMap<Long, JTextField> a_fields = author_fields
										.get(updated_authors.get(i).getId());

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
							a.setDate_submitted(datePickerSubmitted.getDate());
							a.setDoi(doi.getText());
							Issue current_issue = issue_storage.get(issue_id);
							current_issue.setSync(true);
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
					ConcurrentHashMap<Long, ArticleFile> files = file_storage.get((long) article_id);
					if (files != null) {
						Set<Long> keys = files.keySet();
						for (long key : keys) {
							String path = files.get((long) key).getPath();
							label_text = label_text + path.substring(path.lastIndexOf("/") + 1) + "\n";
						}
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
								String type = FilenameUtils.getExtension(f.getPath());
								if (type.toLowerCase().compareTo("xml") == 0) {
									if (list_settings.containsKey("Validate XML (JATS)")
											&& Boolean.parseBoolean(list_settings.get("Validate XML (JATS)"))) {

										try {
											boolean valid = false;
											valid = validate_xml(f);
											if (!valid) {
												JOptionPane.showMessageDialog(null,
														"Invalid XML file. Note: JATS version 1.1 is used for validation.");
											} else {
												uploaded_files.add(f);
												label_text = label_text + f.getName() + "\n";
											}
										} catch (HeadlessException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										} catch (org.xml.sax.SAXException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										} catch (IOException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										} catch (ParserConfigurationException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}

									} else {
										uploaded_files.add(f);
										label_text = label_text + f.getName() + "\n";
									}
								} else if (type.toLowerCase().compareTo("png") == 0
										|| type.toLowerCase().compareTo("jpg") == 0
										|| type.toLowerCase().compareTo("jpeg") == 0) {
									if (list_settings.containsKey("Optimize Images")
											&& Boolean.parseBoolean(list_settings.get("Optimize Images"))) {
										try {
											File optimized = optimize_image(f);
											uploaded_files.add(optimized);
											label_text = label_text + optimized.getName() + "\n";
										} catch (IOException e1) {
											uploaded_files.add(f);
											label_text = label_text + f.getName() + "\n";

										}
									}
								} else if (type.toLowerCase().compareTo("pdf") == 0) {
									if (list_settings.containsKey("Insert DOIs in PDFs")
											&& Boolean.parseBoolean(list_settings.get("Insert DOIs in PDFs"))) {

										File optimized;

										optimized = insert_doi_pdf(f, current_article.getDoi());
										uploaded_files.add(optimized);
										label_text = label_text + optimized.getName() + "\n";

									} else if (list_settings.containsKey("Optimize PDFs")
											&& Boolean.parseBoolean(list_settings.get("Optimize PDFs"))) {

										File optimized;
										try {
											optimized = optimize_pdf(f);
											uploaded_files.add(optimized);
											label_text = label_text + optimized.getName() + "\n";

										} catch (IOException e1) {
											uploaded_files.add(f);
											label_text = label_text + f.getName() + "\n";

										} catch (DocumentException e1) {
											uploaded_files.add(f);
											label_text = label_text + f.getName() + "\n";

										}
									} else {
										uploaded_files.add(f);
										label_text = label_text + f.getName() + "\n";

									}
								} else {
									uploaded_files.add(f);
									label_text = label_text + f.getName() + "\n";

								}
							}
							if (!uploaded_files.isEmpty()) {
								System.out.println("Uploaded " + uploaded_files.size() + " files");
								label_text = label_text + "]----Not Uploaded-----";
								label_tooltip = files.length + " files";

								lblFile.setText(label_text);
								lblFile.setToolTipText(label_tooltip);
							}
						}
					}
				});
				upload.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						select.setEnabled(false);
						btnClear.setEnabled(false);
						upload.setEnabled(false);
						if (file_storage.containsKey((long) article_id)) {
							file_storage.get((long) article_id);
						} else {

							new ConcurrentHashMap<Long, ArticleFile>();
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
							ConcurrentHashMap<Long, ArticleFile> files_existing = file_storage.get((long) article_id);
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
						File folder = new File(String.format("%s/required_files/images/", directory));
						folder.delete();
						try {
							FileUtils.deleteDirectory(folder);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						System.out.println("deleted images");
						folder = new File(String.format("%s/required_files/pdf/", directory));
						folder.delete();
						try {
							FileUtils.deleteDirectory(folder);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						System.out.println("deleted pdf");
					}
				});
				btnClear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						uploaded_files.clear();
						if (file_storage.containsKey((long) article_id)) {
							String label_text = "";
							ConcurrentHashMap<Long, ArticleFile> files_existing = file_storage.get((long) article_id);
							Set<Long> keys = files_existing.keySet();
							for (long k : keys) {
								ArticleFile a_file = files_existing.get(k);
								label_text = label_text
										+ a_file.getPath().substring(a_file.getPath().lastIndexOf("/") + 1) + "\n";
							}
							lblFile.setText(label_text);
							/*
							 * if (file_storage.containsKey((long)article_id)) {
							 * ConcurrentHashMap<Integer, ArticleFile> up_files
							 * = file_storage.get((long)article_id);
							 * Set<Integer> keys = up_files.keySet(); file_id =
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
					ConcurrentHashMap<Long, JFrame> issue_articles = article_screens.get(issue_id);
					issue_articles.put(article_id, article);
					article_screens.put(issue_id, issue_articles);
				}
				if (selected_section == -1) {
					JOptionPane.showMessageDialog(null,
							"Section has been removed or does not exist. Please select one.");

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
			width_small = 920;
			height_small = 768;
			ConcurrentHashMap<Long, Boolean> author_primary = new ConcurrentHashMap<Long, Boolean>();
			String setting_meta = list_settings.get("Metadata");
			long current_id = articles_id + 1;

			long initial_file_num = file_id;
			final JFrame article = new JFrame();
			article.setResizable(false);
			article.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			// article.setSize(width_small, height_small);
			article.setSize(width_small, height_small);
			article.setTitle("New Article");
			article.getContentPane().setBackground(new Color(213, 213, 213));
			article.setVisible(true);
			article.setLocationRelativeTo(null);
			article.getContentPane().setLayout(null);

			/*
			 * final JButton btnSync = new JButton("Sync");
			 * btnSync.setBounds(width_small - 150, 21, 70, 24);
			 * article.getContentPane().add(btnSync);
			 * 
			 * final Label internetCheck = new Label("  ONLINE");
			 * internetCheck.setFont(new Font(Font.SANS_SERIF,
			 * Font.TRUETYPE_FONT | Font.ITALIC, 12));
			 * internetCheck.setBackground(Color.lightGray);
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
			 * internetCheck.setBackground(Color.lightGray);
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
						ConcurrentHashMap<Long, ArticleFile> up_files = file_storage.get((long) current_id);
						if (up_files != null) {
							Set<Long> keys = up_files.keySet();
							file_id = initial_file_num;
							for (long key : keys) {
								File f = new File(up_files.get((long) key).getPath());
								f.delete();
							}
							File folder = new File(String.format("%s/files/%d/", directory, current_id));
							folder.delete();

							file_storage.remove(current_id);
						}
					}
					File folder = new File(String.format("%s/required_files/images/", directory));
					folder.delete();
					try {
						FileUtils.deleteDirectory(folder);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("deleted files");
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
			btnGoBack.setBounds(width_small - 150, 86, 117, 30);
			article.getContentPane().add(btnGoBack);
			JScrollPane scrollSettings = new JScrollPane();
			scrollSettings.setBounds(40, 180, 320, 200);

			JPanel panel = new JPanel();
			panel.setBackground(SystemColor.window);
			panel.setBounds(50, 107, 300, 307);
			article.getContentPane().add(panel);
			panel.setLayout(null);
			panel.setAutoscrolls(true);
			int fields = 5;
			int settings_height = 210 + 30 * (fields - 8);
			JLabel lblIssueT = new JLabel("Add Article");
			lblIssueT.setBackground(new Color(46, 46, 46));
			lblIssueT.setForeground(new Color(255, 255, 255));
			lblIssueT.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 26));
			lblIssueT.setBounds(width_small - 150, 26, 280, 40);
			lblIssueT.setOpaque(true);
			article.getContentPane().add(lblIssueT);

			JPanel panel_1 = new JPanel();
			panel_1.setBackground(new Color(46, 46, 46));
			panel_1.setLayout(null);
			panel_1.setBounds(0, 0, width, 70);
			ImageIcon icon = new ImageIcon(String.format("%s/required_files/%s", directory, "toru-ui-logo.png"));
			JLabel logo = new JLabel(icon);
			logo.setBounds(10, 20, 140, 40);
			logo.setBackground(new Color(46, 46, 46));
			panel_1.add(logo);
			panel_1.repaint();
			logo.repaint();
			article.getContentPane().add(panel_1);

			Panel panel_2 = new Panel();
			panel_2.setBackground(new Color(25, 25, 25));
			panel_2.setBounds(0, 70, width, 6);
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
			btnAddMetadata.setBounds(40, height_small - 115, 160, 45);
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
			abstractSection.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

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
			final ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, JTextField>> author_fields = new ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, JTextField>>();
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
			 * ConcurrentHashMap<Integer, JTextField> author_components = new
			 * ConcurrentHashMap<Integer, JTextField>(); Author author =
			 * authors.get(i); JLabel field_label = new JLabel("First name:");
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

			DefaultListModel<String> listModel = new DefaultListModel<String>();
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
			JList<String> listbox = new JList<String>();
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
						new_author.setArticle_id(current_id);
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
			 * lblIssues.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT,
			 * 14)); lblIssues.setForeground(Color.BLACK);
			 */

			JLabel lblIssue = new JLabel("Issue:");
			lblIssue.setBounds(24, 48, 94, 30);
			panel.add(lblIssue);
			lblIssue.setForeground(Color.BLACK);
			lblIssue.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));

			JLabel lblIssueId = new JLabel(Long.toString(issue_id));
			lblIssueId.setBounds(160, 48, 94, 30);
			panel.add(lblIssueId);
			lblIssueId.setForeground(Color.BLACK);
			lblIssueId.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
			lblIssueId.setText(Long.toString(issue_id));

			final JLabel lblDateAccepted = new JLabel("Date Submitted:");
			lblDateAccepted.setForeground(Color.BLACK);
			lblDateAccepted.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
			lblDateAccepted.setBounds(24, 199, 150, 30);
			panel.add(lblDateAccepted);
			final JLabel lblDatePublished = new JLabel("Date Published:");
			lblDatePublished.setForeground(Color.BLACK);
			lblDatePublished.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
			lblDatePublished.setBounds(24, 231, 150, 30);
			panel.add(lblDatePublished);
			Date current = new Date();

			final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

			/*
			 * JLabel lblDate = new JLabel(sdf.format(current));
			 * lblDate.setVerticalAlignment(SwingConstants.TOP);
			 * lblDate.setForeground(Color.BLACK); lblDate.setFont(new
			 * Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
			 * lblDate.setBounds(160, 203, 125, 30); panel.add(lblDate);
			 * 
			 * 
			 * JLabel lblArticleId = new JLabel("1");
			 * lblArticleId.setBounds(160, 19, 94, 30); panel.add(lblArticleId);
			 * lblArticleId.setForeground(Color.BLACK); lblArticleId.setFont(new
			 * Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
			 * lblArticleId.setText(Integer.toString(article_id));
			 */

			JLabel lblPages = new JLabel("Pages:");
			lblPages.setForeground(Color.BLACK);
			lblPages.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
			lblPages.setBounds(24, 165, 94, 30);
			panel.add(lblPages);

			final JTextField lblPageNum = new JTextField();
			lblPageNum.setForeground(Color.BLACK);
			lblPageNum.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
			lblPageNum.setBounds(156, 171, 125, 30);
			panel.add(lblPageNum);

			JLabel lblTitle = new JLabel("Title:");
			lblTitle.setForeground(Color.BLACK);
			lblTitle.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
			lblTitle.setBounds(24, 115, 94, 30);
			panel.add(lblTitle);

			Panel panel9 = new Panel();
			panel9.setBackground(new Color(153, 102, 51));
			panel9.setBounds(85, 118, 180, 80);
			final JTextArea lblTitleText = new JTextArea();
			lblTitleText.setEditable(true);
			lblTitleText.setOpaque(true);
			lblTitleText.setForeground(Color.BLACK);
			lblTitleText.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));

			panel9.add(lblTitleText);
			JScrollPane titleSection = new JScrollPane(lblTitleText);
			titleSection.setPreferredSize(new Dimension(300 * 2, 200));
			titleSection.setBounds(85, 113, 225, 50);
			titleSection.add(panel9);
			titleSection.createHorizontalScrollBar();
			panel.add(titleSection);

			final JComboBox<String> lblSectionId = new JComboBox<String>();
			Set<Long> section_keys = section_storage.keySet();
			ArrayList<Section> sections = new ArrayList<Section>();
			for (long key : section_keys) {
				lblSectionId.addItem(section_storage.get(key).getTitle());
				sections.add(section_storage.get(key));
			}

			lblSectionId.setForeground(Color.BLACK);
			lblSectionId.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 12));
			lblSectionId.setBounds(95, 83, 185, 26);
			panel.add(lblSectionId);
			JButton btnAddSections = new JButton("+");

			btnAddSections.setBounds(280, 83, 45, 27);
			panel.add(btnAddSections);
			SpinnerNumberModel seq_model;
			SpinnerModel count_model;

			seq_model = new SpinnerNumberModel(0.0, -1000.0, 1000.0, 0.1);

			count_model = new SpinnerNumberModel(0, 0, 5000, 1);

			JPanel panelSection = new JPanel();
			panelSection.setBounds(0, 0, 480, 240);
			panelSection.setLayout(null);

			JTextField txtSectionTitle = new JTextField();
			txtSectionTitle.setBounds(90, 45, 300, 30);
			panelSection.add(txtSectionTitle);
			txtSectionTitle.setColumns(4);
			final JCheckBox disable_comments = new JCheckBox("Disable Comments", false);

			disable_comments.setBounds(240, 80, 140, 26);
			panelSection.add(disable_comments);
			final JCheckBox abstracts_not_required = new JCheckBox("Abstracts Not Required", false);

			abstracts_not_required.setBounds(240, 106, 200, 26);
			panelSection.add(abstracts_not_required);
			final JCheckBox editor_restricted = new JCheckBox("Editor Restricted", false);

			editor_restricted.setBounds(240, 132, 200, 26);
			panelSection.add(editor_restricted);
			final JCheckBox hide_title = new JCheckBox("Hide Title", false);

			hide_title.setBounds(80, 80, 100, 26);
			panelSection.add(hide_title);
			final JCheckBox hide_author = new JCheckBox("Hide Author", false);

			hide_author.setBounds(80, 106, 100, 26);
			panelSection.add(hide_author);
			final JCheckBox hide_about = new JCheckBox("Hide About", false);

			hide_about.setBounds(80, 132, 100, 26);
			panelSection.add(hide_about);

			final JCheckBox metadata_indexed = new JCheckBox("Metadata Indexed", false);

			metadata_indexed.setBounds(80, 158, 180, 26);
			panelSection.add(metadata_indexed);

			final JCheckBox metadata_reviewed = new JCheckBox("Metadata Reviewed", false);

			metadata_reviewed.setBounds(80, 184, 180, 26);
			panelSection.add(metadata_reviewed);

			JLabel lblSeq = new JLabel("Seq");
			lblSeq.setHorizontalAlignment(SwingConstants.CENTER);
			lblSeq.setBounds(40, 215, 200, 20);
			panelSection.add(lblSeq);

			JSpinner txtSeq = new JSpinner(seq_model);
			txtSeq.setBounds(70, 236, 150, 30);
			panelSection.add(txtSeq);

			JLabel lblAbstractCount = new JLabel("Abstract Word Count");
			lblAbstractCount.setHorizontalAlignment(SwingConstants.CENTER);
			lblAbstractCount.setBounds(240, 215, 200, 20);
			panelSection.add(lblAbstractCount);

			JSpinner txtAbstractCount = new JSpinner(count_model);
			txtAbstractCount.setBounds(270, 236, 150, 30);
			panelSection.add(txtAbstractCount);

			JLabel lblTitleSection = new JLabel("Title");
			lblTitleSection.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitleSection.setBounds(190, 25, 100, 20);
			panelSection.add(lblTitleSection);
			panelSection.setBounds(0, 0, 480, 280);
			panelSection.setSize(new Dimension(480, 280));
			panelSection.setPreferredSize(new Dimension(480, 280));
			panelSection.setVisible(true);
			btnAddSections.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					panelSection.setVisible(true);
					panelSection.setEnabled(true);
					boolean validation = false;
					int result = JOptionPane.showConfirmDialog(null, panelSection, "Add Section",
							JOptionPane.OK_CANCEL_OPTION);
					String title = txtSectionTitle.getText();
					if (title == null || title.isEmpty() || title.compareTo("") == 0 || title.compareTo(" ") == 0) {
						validation = false;
					} else {
						validation = true;
					}
					try {
						int wordcount = (Integer) txtAbstractCount.getValue();
						double seq = (Double) txtSeq.getValue();
						validation = true;
					} catch (Exception es) {
						validation = false;
					}

					if (result == JOptionPane.OK_OPTION && validation == true) {
						section_db_id++;
						Section new_section = new Section(section_db_id, txtSectionTitle.getText());
						new_section.setAbstract_word_count((Integer) txtAbstractCount.getValue());
						new_section.setSeq((Double) txtSeq.getValue());
						new_section.setHide_about(hide_about.isSelected() == true ? 1 : 0);
						new_section.setHide_author(hide_author.isSelected() == true ? 1 : 0);
						new_section.setHide_title(hide_title.isSelected() == true ? 1 : 0);
						new_section.setEditor_restricted(editor_restricted.isSelected() == true ? 1 : 0);
						new_section.setMeta_indexed(metadata_indexed.isSelected() == true ? 1 : 0);
						new_section.setMeta_reviewed(metadata_reviewed.isSelected() == true ? 1 : 0);
						new_section.setDisable_comments(disable_comments.isSelected() == true ? 1 : 0);
						new_section.setAbstracts_not_required(abstracts_not_required.isSelected() == true ? 1 : 0);
						new_section.setSync(true);
						section_storage.put(section_db_id, new_section);
						lblSectionId.addItem(new_section.getTitle());
						sections.add(new_section);
						lblSectionId.repaint();
					}
				}
			});

			final JXDatePicker datePickerSubmitted = new JXDatePicker();
			datePickerSubmitted.setFormats(sdf);
			final JLabel label_accepted = new JLabel();
			label_accepted.setText("Choose Date by selecting below.");
			datePickerSubmitted.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					label_accepted.setText(datePickerSubmitted.getDate().toString());
					System.out.println(datePickerSubmitted.getDate());
				}
			});
			label_accepted.setBounds(160, 250, 100, 25);
			datePickerSubmitted.setBounds(156, 202, 160, 30);
			// panel.add(label);
			panel.add(datePickerSubmitted);
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
			lblDoi.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
			lblDoi.setBounds(24, 268, 94, 30);
			panel.add(lblDoi);

			final JTextField doi = new JTextField();
			doi.setForeground(Color.BLACK);
			doi.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
			doi.setBounds(85, 269, 180, 30);
			panel.add(doi);
			JLabel lblSection = new JLabel("Section:");
			lblSection.setForeground(Color.BLACK);
			lblSection.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
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

						sdf.format(datePickerSubmitted.getDate());

					} catch (Exception ex) {
						validation = false;
						JOptionPane.showMessageDialog(null, "Use dates from calendar for fields: Date Submitted");
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
						long id_create = articles_id + 1;
						articles_id++;
						if (setting_meta.compareToIgnoreCase("true") == 0) {
							metadata_storage.put(id_create, meta_update);
							System.out.println("Metadata added");

							System.out.println(metadata_storage.get(id_create).getCompeting_interests());
						}
						list_issues.replace(issue_id, id_create);
						Issue current_issue = issue_storage.get(issue_id);
						Article new_article = new Article(id_create, lblTitleText.getText(), entered_sectionID,
								entered_pages, lblAbstract.getText(), current_issue, datePickerSubmitted.getDate(),
								journal_storage.get(Long.parseLong(app_settings.get("journal_id"))));
						new_article.setDoi(doi.getText());
						try {
							sdf.format(datePicker.getDate());
							new_article.setDate_published(datePicker.getDate());
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						new_article.setSync(true);
						try {
							sdf.format(datePicker.getDate());
							new_article.setDate_published(datePicker.getDate());
							long id_publish_create = published_articles_id + 1;
							published_articles_id++;
							new_article.setPublished_pk(id_publish_create);
						} catch (Exception ex) {
						}
						author_primary_storage.put(new_article.getId(), author_primary);
						ArrayList<Author> selected_authors = new ArrayList<Author>();
						int[] selections = listbox.getSelectedIndices();
						ConcurrentHashMap<Long, Boolean> author_primary = new ConcurrentHashMap<Long, Boolean>();
						author_primary_storage.put(new_article.getId(), author_primary);
						System.out.println("Selected authors: " + selections.length);
						for (int index : selections) {
							author_primary.put(author_storage.get(author_list.get(index)).getId(), false);
							selected_authors.add(author_storage.get(author_list.get(index)));
							new_article.add_author(author_storage.get(author_list.get(index)));

							System.out.println("Added: " + author_storage.get(author_list.get(index)).getFull_name());
							System.out.println("selected_authors: " + selected_authors.size());
							System.out.println("new_article: " + new_article.getAuthors().size());

						}
						current_issue.add_article(new_article.getId(), new_article);
						current_issue.setSync(true);
						article_storage.put(new_article.getId(), new_article);
						article_author_storage.put(new_article.getId(), selected_authors);

						author_primary_storage.put(new_article.getId(), author_primary);
						article.dispose();
						issue_storage.put(issue_id, current_issue);
						new_article.getId();
						sdf.format(current);
						ConcurrentHashMap<Long, JFrame> issue_articles = article_screens.get(issue_id);
						System.out.println(new_article.getId());
						issue_articles.put(new_article.getId(), new JFrame());
						article_screens.put(issue_id, issue_articles);
						System.out.println(article_screens.get(issue_id).containsKey(new_article.getId()));
						Metadata meta = metadata_storage.get((long) new_article.getId());
						if (meta == null) {
							metadata_id++;
							meta = new Metadata(metadata_id, new_article.getId());
						}
						meta.setCompeting_interests(txtCompetingInterests.getText());
						meta.setFunding(txtFunding.getText());
						metadata_storage.put((long) new_article.getId(), meta);
						issue(issue_id);
					}
					/*
					 * Article a =
					 * issue_storage.get(issue_id).getArticles_list().get(
					 * article_id); a.setTitle(lblTitleText.getText());
					 * ArrayList<Author> updated_authors = a.getAuthors(); for
					 * (int i = 0; i < updated_authors.size(); i++) { Author
					 * author = updated_authors.get(i);
					 * ConcurrentHashMap<Integer, JTextField> a_fields =
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
							String type = FilenameUtils.getExtension(f.getPath());
							if (type.toLowerCase().compareTo("xml") == 0) {
								if (list_settings.containsKey("Validate XML (JATS)")
										&& Boolean.parseBoolean(list_settings.get("Validate XML (JATS)"))) {

									try {
										boolean valid = false;
										valid = validate_xml(f);
										if (!valid) {
											JOptionPane.showMessageDialog(null,
													"Invalid XML file. Note: JATS version 1.1 is used for validation.");
										} else {
											uploaded_files.add(f);
											label_text = label_text + f.getName() + "\n";
										}
									} catch (HeadlessException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									} catch (org.xml.sax.SAXException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									} catch (ParserConfigurationException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}

								} else {
									uploaded_files.add(f);
									label_text = label_text + f.getName() + "\n";
								}
							} else if (type.toLowerCase().compareTo("png") == 0
									|| type.toLowerCase().compareTo("jpg") == 0
									|| type.toLowerCase().compareTo("jpeg") == 0) {
								if (list_settings.containsKey("Optimize Images")
										&& Boolean.parseBoolean(list_settings.get("Optimize Images"))) {
									try {
										File optimized = optimize_image(f);
										uploaded_files.add(optimized);
										label_text = label_text + optimized.getName() + "\n";
									} catch (IOException e1) {
										uploaded_files.add(f);
										label_text = label_text + f.getName() + "\n";

									}
								}
							} else if (type.toLowerCase().compareTo("pdf") == 0) {
								String doi_text = doi.getText();
								if (doi_text != null && !doi_text.isEmpty() && doi_text.compareTo("") == 0
										&& list_settings.containsKey("Insert DOIs in PDFs")
										&& Boolean.parseBoolean(list_settings.get("Insert DOIs in PDFs"))) {

									File optimized;

									optimized = insert_doi_pdf(f, doi.getText());
									uploaded_files.add(optimized);
									label_text = label_text + optimized.getName() + "\n";

								} else if (list_settings.containsKey("Optimize PDFs")
										&& Boolean.parseBoolean(list_settings.get("Optimize PDFs"))) {

									File optimized;
									try {
										optimized = optimize_pdf(f);
										uploaded_files.add(optimized);
										label_text = label_text + optimized.getName() + "\n";

									} catch (IOException e1) {
										uploaded_files.add(f);
										label_text = label_text + f.getName() + "\n";

									} catch (DocumentException e1) {
										uploaded_files.add(f);
										label_text = label_text + f.getName() + "\n";

									}
								} else {
									uploaded_files.add(f);
									label_text = label_text + f.getName() + "\n";

								}
							} else {
								uploaded_files.add(f);
								label_text = label_text + f.getName() + "\n";

							}
						}
						if (!uploaded_files.isEmpty()) {
							label_text = label_text + "]----Not Uploaded-----";
							label_tooltip = files.length + " files";
							lblFile.setText(label_text);
							lblFile.setToolTipText(label_tooltip);
						}
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
						ConcurrentHashMap<Long, ArticleFile> files_existing = file_storage.get((long) current_id);
						Set<Long> keys = files_existing.keySet();
						for (long k : keys) {
							ArticleFile a_file = files_existing.get(k);
							label_text = label_text + a_file.getPath().substring(a_file.getPath().lastIndexOf("/") + 1)
									+ "\n";
							System.out.println("::::" + label_text);
						}
						lblFile.setText(label_text);
					}
					File folder = new File(String.format("%s/required_files/images/", directory));
					folder.delete();
					try {
						FileUtils.deleteDirectory(folder);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("deleted images");
					folder = new File(String.format("%s/required_files/pdf/", directory));
					folder.delete();
					try {
						FileUtils.deleteDirectory(folder);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("deleted pdf");
				}
			});

			btnClear.setEnabled(false);
			upload.setBounds(156, 310, 90, 30);
			btnClear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (file_storage.containsKey((long) current_id)) {
						ConcurrentHashMap<Long, ArticleFile> up_files = file_storage.get((long) current_id);
						if (up_files != null) {
							Set<Long> keys = up_files.keySet();
							file_id = initial_file_num;
							for (long key : keys) {
								File f = new File(up_files.get((long) key).getPath());
								f.delete();
							}
							File folder = new File(String.format("%s/files/%d/", directory, current_id));
							folder.delete();

							file_storage.remove(current_id);
						}
					}
					File folder = new File(String.format("%s/required_files/images/", directory));
					folder.delete();
					try {
						FileUtils.deleteDirectory(folder);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("deleted files");
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
						ConcurrentHashMap<Long, ArticleFile> up_files = file_storage.get((long) current_id);
						if (up_files != null) {
							Set<Long> keys = up_files.keySet();
							file_id = initial_file_num;

							for (long key : keys) {
								File f = new File(up_files.get((long) key).getPath());
								f.delete();
							}
							File folder = new File(String.format("%s/files/%d/", directory, current_id));
							folder.delete();

							file_storage.remove(current_id);
							metadata_storage.remove(current_id);
						}
					}
					File folder = new File(String.format("%s/required_files/images/", directory));
					folder.delete();
					try {
						FileUtils.deleteDirectory(folder);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("deleted files");
					issue(issue_id);
					// database_save();
				}
			});
			article.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					if (file_storage.containsKey((long) current_id) && current_id != articles_id) {
						ConcurrentHashMap<Long, ArticleFile> up_files = file_storage.get((long) current_id);
						Set<Long> keys = up_files.keySet();
						file_id = initial_file_num;
						for (Long key : keys) {
							File f = new File(up_files.get((long) key).getPath());
							f.delete();
						}
						metadata_storage.remove(current_id);
						File folder = new File(String.format("%s/files/%d/", directory, current_id));
						folder.delete();

						metadata_storage.remove(current_id);
						file_storage.remove(current_id);
					}
					File folder = new File(String.format(String.format("%s/required_files/%s", directory, "images/")));
					folder.delete();
					try {
						FileUtils.deleteDirectory(folder);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("deleted files");
					issue(issue_id);
					// database_save();
				}
			});
			article.repaint();
			panel6.repaint();
			if (article_screens.containsKey(issue_id)) {
				ConcurrentHashMap<Long, JFrame> issue_articles = article_screens.get(issue_id);
				issue_articles.put(current_id, article);
				article_screens.put(issue_id, issue_articles);
			}

		} else {
			login("dashboard");
		}
	}

	@SuppressWarnings({ "resource", "unchecked" })
	public static void update_issue_intersect(Issue issue, String credentials, boolean not_created)
			throws IllegalStateException, IOException {

		System.out.println("update instersect");
		boolean status = status_online();
		System.out.println(status);
		if (!status) {
			return;
		}
		if (!not_created && !issue.shouldBeSynced()) {
			issue_countdown_storage.put((long) issue.getId(), true);
			return;
		}
		if (!not_created && issue.shouldBeSynced() && issue.isDeleted()) {
			delete_issue((long) issue.getId());
			issue_countdown_storage.put((long) issue.getId(), true);
			return;
		}
		JSONObject obj = IssueToJSON(issue);
		try {
			HttpGet issue_exists = new HttpGet(String.format("%s/issues/%s/", base_url, issue.getId()));

			issue_exists.addHeader("Authorization", "Basic " + credentials);
			issue_exists.setHeader("Accept", "application/json");
			issue_exists.addHeader("Content-type", "application/json");

			HttpResponse response = null;

			response = httpClient.execute(issue_exists);

			boolean issue_created = false;
			if (response.getStatusLine().getStatusCode() == 200) {
				issue_created = true;
			}
			InputStream is = response.getEntity().getContent();
			is.close();

			System.out.println(issue_created);
			if (issue_created) {
				HttpPut httpPut = new HttpPut(String.format("%s/issues/%s/", base_url, issue.getId()));
				httpPut.setEntity(new StringEntity(obj.toJSONString()));
				httpPut.addHeader("Authorization", "Basic " + credentials);
				httpPut.setHeader("Accept", "application/json");
				httpPut.addHeader("Content-type", "application/json");

				response = null;
				response = httpClient.execute(httpPut);

				is = response.getEntity().getContent();
				is.close();

			} else {
				System.out.println("Creating issue");
				HttpPost createIssue = new HttpPost(String.format("%s/issues/", base_url));
				createIssue.setEntity(new StringEntity(obj.toJSONString()));
				createIssue.addHeader("Authorization", "Basic " + credentials);
				createIssue.setHeader("Accept", "application/json");
				createIssue.addHeader("Content-type", "application/json");

				response = httpClient.execute(createIssue);
				int resp_status = response.getStatusLine().getStatusCode();
				System.out.println(resp_status);
				System.out.println(obj.toJSONString());
				System.out.println(response.getEntity().getContent());
				is = response.getEntity().getContent();
				is.close();

			}
			System.out.println(response.toString());
			HttpGet settingCheck = new HttpGet(
					String.format("%s/get/setting/title/issue/%s/?format=json", base_url, issue.getId()));
			// settingCheck.setEntity(new StringEntity(obj.toJSONString()));
			settingCheck.addHeader("Authorization", "Basic " + credentials);
			settingCheck.setHeader("Accept", "application/json");
			settingCheck.addHeader("Content-type", "application/json");

			response = null;
			response = httpClient.execute(settingCheck);

			new JsonFactory();
			InputStream result = response.getEntity().getContent();
			Long setting_pk = (long) -1;
			org.json.simple.parser.JSONParser jsonParser = new JSONParser();
			boolean exists = true;
			if (response.getStatusLine().getStatusCode() != 200) {
				exists = false;
			}
			if (exists && issue.isDeleted()) {
				delete_issue((long) issue.getId());
				return;
			}
			JSONObject setting_json = new JSONObject();
			try {
				JSONObject setting = (JSONObject) jsonParser.parse(IOUtils.toString(result));
				System.out.println(setting.get("count"));
				System.out.println(setting);
				Long count = (Long) setting.get("count");
				if (count == null || count == 0) {
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

				e.printStackTrace();
			}

			is = response.getEntity().getContent();
			is.close();

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

				response = httpClient.execute(httpPost);

			} else {
				HttpPut httpPost = new HttpPut(
						String.format("%s/issue-settings/%s/", base_url, setting_json.get("pk")));
				httpPost.setEntity(new StringEntity(setting_json.toJSONString()));
				httpPost.addHeader("Authorization", "Basic " + credentials);
				httpPost.setHeader("Accept", "application/json");
				httpPost.addHeader("Content-type", "application/json");

				response = httpClient.execute(httpPost);

			}
			is = response.getEntity().getContent();
			is.close();

			/*
			 * response = null; try { response = httpClient.execute(httpPost); }
			 * catch (ClientProtocolException e2) { // TODO Auto-generated catch
			 * block e2.printStackTrace(); } catch (IOException e2) { // TODO
			 * Auto-generated catch block e2.printStackTrace(); }
			 */

			System.out.println("issue details synced");
		} catch (ClientProtocolException e2) {
			JOptionPane.showMessageDialog(null, String.format("Unable to sync Issue <%s>.", issue.getId()));

			return;
		} catch (IOException e2) {
			JOptionPane.showMessageDialog(null, String.format("Unable to sync Issue <%s>.", issue.getId()));

			return;
		}
	}

	public static void update_article_intersect_less_requests(Article article, String credentials)
			throws UnsupportedOperationException, IOException {

		boolean status = status_online();
		if (!status) {
			return;
		}

		System.out.println("articles being uploaded " + article.getId());
		JSONObject obj = ArticleToJSON(article);
		HttpGet article_exists = new HttpGet(String.format("%s/articles/%s/", base_url, article.getId()));

		article_exists.addHeader("Authorization", "Basic " + credentials);
		article_exists.setHeader("Accept", "application/json");
		article_exists.addHeader("Content-type", "application/json");

		HttpResponse response = null;
		try {
			response = httpClient.execute(article_exists);
		} catch (ClientProtocolException e2) {

			e2.printStackTrace();
		} catch (IOException e2) {

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

			exc.printStackTrace();
		}

		System.out.println("articles created " + article_created);
		if (article_created) {

			System.out.println("articles updating");
			HttpPut httpPut = new HttpPut(String.format("%s/articles/%s/", base_url, article.getId()));
			httpPut.setEntity(new StringEntity(obj.toJSONString()));
			httpPut.addHeader("Authorization", "Basic " + credentials);
			httpPut.setHeader("Accept", "application/json");
			httpPut.addHeader("Content-type", "application/json");

			response = null;
			try {
				response = httpClient.execute(httpPut);
			} catch (ClientProtocolException e2) {

				e2.printStackTrace();
			} catch (IOException e2) {

				e2.printStackTrace();
			}
			try {
				InputStream is = response.getEntity().getContent();
				is.close();
			} catch (IOException exc) {

				exc.printStackTrace();
			}
		} else {

			System.out.println("articles creating");
			System.out.println("articles created " + article_created);
			HttpPost createArticle = new HttpPost(String.format("%s/articles/", base_url));
			createArticle.setEntity(new StringEntity(obj.toJSONString()));
			createArticle.addHeader("Authorization", "Basic " + credentials);
			createArticle.setHeader("Accept", "application/json");
			createArticle.addHeader("Content-type", "application/json");

			response = null;
			try {
				response = httpClient.execute(createArticle);
			} catch (ClientProtocolException e2) {

				e2.printStackTrace();
			} catch (IOException e2) {

				e2.printStackTrace();
			}
			try {
				InputStream is = response.getEntity().getContent();
				is.close();
			} catch (IOException exc) {

				exc.printStackTrace();
			}
		}

		System.out.println("metadata");
		Metadata meta = metadata_storage.get((long) article.getId());
		String ci = null;
		String funding = null;
		if (meta == null) {
			ci = "";
			funding = "";
		} else {

			ci = meta.getCompeting_interests();
			byte[] b = ci.getBytes("windows-1252");
			ci = new String(b, "UTF-8");
			funding = meta.getFunding();
			b = funding.getBytes("windows-1252");
			funding = new String(b, "UTF-8");

		}

		System.out.println("abstract");
		String abstract_text = "<p class='p1'>" + article.getAbstract_text().replace("\r\n", "") + "</p>";

		byte[] b = abstract_text.getBytes("windows-1252");
		abstract_text = new String(b, "UTF-8");
		String title = article.getTitle();

		b = title.getBytes("windows-1252");
		title = new String(b, "UTF-8");

		System.out.println("title");
		String doi = article.getDoi();

		b = doi.getBytes("windows-1252");

		System.out.println("doi");
		doi = new String(b, "UTF-8");

		String json_settings = String.format(
				"{\"abstract\":\"%s\",\"title\":\"%s\",\"funding\":\"%s\",\"competing_interests\":\"%s\",\"article\":%d, \"doi\":\"%s\"}",
				abstract_text, title, funding, ci, article.getId(), doi);

		System.out.println("!article settings" + json_settings);
		HttpPut httpPut = new HttpPut(String.format("%s/custom/articles/", base_url));

		System.out.println(json_settings);
		httpPut.setEntity(new StringEntity(json_settings));
		httpPut.addHeader("Authorization", "Basic " + credentials);
		httpPut.setHeader("Accept", "application/json");
		httpPut.addHeader("Content-type", "application/json");

		response = null;
		try {
			response = httpClient.execute(httpPut);
		} catch (ClientProtocolException e2) {

			e2.printStackTrace();
		} catch (IOException e2) {

			e2.printStackTrace();
		}
		try {
			InputStream is = response.getEntity().getContent();
			is.close();
		} catch (IOException exc) {

			exc.printStackTrace();
		}
		if (article.getDate_published() != null) {
			System.out.println("PUBLISHED");
			article_exists = new HttpGet(String.format("%s/get/article/published/%s/", base_url, article.getId()));

			article_exists.addHeader("Authorization", "Basic " + credentials);
			article_exists.setHeader("Accept", "application/json");
			article_exists.addHeader("Content-type", "application/json");
			obj = ArticleToPublishedJSON(article);
			response = null;
			try {
				response = httpClient.execute(article_exists);
			} catch (ClientProtocolException e2) {

				e2.printStackTrace();
			} catch (IOException e2) {

				e2.printStackTrace();
			}
			boolean article_published = false;
			if (response.getStatusLine().getStatusCode() == 200) {
				article_published = true;
			}
			try {
				InputStream is = response.getEntity().getContent();
				is.close();
			} catch (IOException exc) {

				exc.printStackTrace();
			}
			if (article_published) {
				httpPut = new HttpPut(String.format("%s/published-articles/%s/", base_url, article.getPublished_pk()));
				httpPut.setEntity(new StringEntity(obj.toJSONString()));
				httpPut.addHeader("Authorization", "Basic " + credentials);
				httpPut.setHeader("Accept", "application/json");
				httpPut.addHeader("Content-type", "application/json");

				response = null;
				try {
					response = httpClient.execute(httpPut);
				} catch (ClientProtocolException e2) {

					e2.printStackTrace();
				} catch (IOException e2) {

					e2.printStackTrace();
				}
				try {
					InputStream is = response.getEntity().getContent();
					is.close();
				} catch (IOException exc) {

					exc.printStackTrace();
				}
			} else {
				HttpPost createArticle = new HttpPost(String.format("%s/published-articles/", base_url));
				createArticle.setEntity(new StringEntity(obj.toJSONString()));
				createArticle.addHeader("Authorization", "Basic " + credentials);
				createArticle.setHeader("Accept", "application/json");
				createArticle.addHeader("Content-type", "application/json");

				response = null;
				try {
					response = httpClient.execute(createArticle);
				} catch (ClientProtocolException e2) {

					e2.printStackTrace();
				} catch (IOException e2) {

					e2.printStackTrace();
				}
				try {
					InputStream is = response.getEntity().getContent();
					is.close();
				} catch (IOException exc) {

					exc.printStackTrace();
				}
				article_exists = new HttpGet(String.format("%s/get/article/published/%s/", base_url, article.getId()));

				article_exists.addHeader("Authorization", "Basic " + credentials);
				article_exists.setHeader("Accept", "application/json");
				article_exists.addHeader("Content-type", "application/json");
				obj = ArticleToPublishedJSON(article);
				response = null;
				try {
					response = httpClient.execute(article_exists);
				} catch (ClientProtocolException e2) {

					e2.printStackTrace();
				} catch (IOException e2) {

					e2.printStackTrace();
				}
				article_published = false;
				if (response.getStatusLine().getStatusCode() == 200) {
					article_published = true;
				}
				if (article_published) {
					InputStream result = response.getEntity().getContent();
					JSONParser jsonParser = new JSONParser();
					new JSONObject();

					try {
						JSONObject setting = (JSONObject) jsonParser.parse(IOUtils.toString(result));
						article.setPublished_pk((int) (long) setting.get("id"));
						article_storage.put(article.getId(), article);
						Issue current_issue = issue_storage.get(article.getIssue_fk().getId());
						current_issue.add_article(article.getId(), article);
						issue_storage.put(current_issue.getId(), current_issue);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						InputStream is = response.getEntity().getContent();
						is.close();
					} catch (IOException exc) {

						exc.printStackTrace();
					}
				}
				try {
					InputStream is = response.getEntity().getContent();
					is.close();
				} catch (IOException exc) {

					exc.printStackTrace();

				}

			}
		} else {

			System.out.println("UNPUBLISHED");
			article_exists = new HttpGet(String.format("%s/get/article/unpublished/%s/", base_url, article.getId()));

			article_exists.addHeader("Authorization", "Basic " + credentials);
			article_exists.setHeader("Accept", "application/json");
			article_exists.addHeader("Content-type", "application/json");
			obj = ArticleToUnPublishedJSON(article);
			response = null;
			try {
				response = httpClient.execute(article_exists);
			} catch (ClientProtocolException e2) {

				e2.printStackTrace();
			} catch (IOException e2) {

				e2.printStackTrace();
			}
			boolean article_published = false;
			if (response.getStatusLine().getStatusCode() == 200) {
				article_published = true;
			}
			try {
				InputStream is = response.getEntity().getContent();
				is.close();
			} catch (IOException exc) {

				exc.printStackTrace();
			}
			if (article_published) {
				httpPut = new HttpPut(
						String.format("%s/unpublished-articles/%s/", base_url, article.getUnpublished_pk()));
				httpPut.setEntity(new StringEntity(obj.toJSONString()));
				httpPut.addHeader("Authorization", "Basic " + credentials);
				httpPut.setHeader("Accept", "application/json");
				httpPut.addHeader("Content-type", "application/json");

				response = null;
				try {
					response = httpClient.execute(httpPut);
				} catch (ClientProtocolException e2) {

					e2.printStackTrace();
				} catch (IOException e2) {

					e2.printStackTrace();
				}
				try {
					InputStream is = response.getEntity().getContent();
					is.close();
				} catch (IOException exc) {

					exc.printStackTrace();
				}
			} else {
				HttpPost createArticle = new HttpPost(String.format("%s/unpublished-articles/", base_url));
				createArticle.setEntity(new StringEntity(obj.toJSONString()));
				createArticle.addHeader("Authorization", "Basic " + credentials);
				createArticle.setHeader("Accept", "application/json");
				createArticle.addHeader("Content-type", "application/json");

				response = null;
				try {
					response = httpClient.execute(createArticle);
				} catch (ClientProtocolException e2) {

					e2.printStackTrace();
				} catch (IOException e2) {

					e2.printStackTrace();
				}
				try {
					InputStream is = response.getEntity().getContent();
					is.close();
				} catch (IOException exc) {

					exc.printStackTrace();
				}
				article_exists = new HttpGet(
						String.format("%s/get/article/unpublished/%s/", base_url, article.getId()));

				article_exists.addHeader("Authorization", "Basic " + credentials);
				article_exists.setHeader("Accept", "application/json");
				article_exists.addHeader("Content-type", "application/json");
				obj = ArticleToUnPublishedJSON(article);
				response = null;
				try {
					response = httpClient.execute(article_exists);
				} catch (ClientProtocolException e2) {

					e2.printStackTrace();
				} catch (IOException e2) {

					e2.printStackTrace();
				}
				article_published = false;
				if (response.getStatusLine().getStatusCode() == 200) {
					article_published = true;
				}
				if (article_published) {
					InputStream result = response.getEntity().getContent();
					JSONParser jsonParser = new JSONParser();
					new JSONObject();

					try {
						JSONObject setting = (JSONObject) jsonParser.parse(IOUtils.toString(result));
						article.setUnpublished_pk((int) (long) setting.get("id"));
						article_storage.put(article.getId(), article);
						Issue current_issue = issue_storage.get(article.getIssue_fk().getId());
						current_issue.add_article(article.getId(), article);
						issue_storage.put(current_issue.getId(), current_issue);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						InputStream is = response.getEntity().getContent();
						is.close();
					} catch (IOException exc) {

						exc.printStackTrace();
					}
				}
				try {
					InputStream is = response.getEntity().getContent();
					is.close();
				} catch (IOException exc) {

					exc.printStackTrace();

				}
			}
		}
	}

	public static void update_articles_intersect(Issue issue, String credentials)
			throws IllegalStateException, IOException {

		boolean status = status_online();
		if (!status) {
			return;
		}
		System.out.println("Getting Articles");

		new ArrayList<Article>();
		IssueToJSON(issue);
		HttpGet issue_exists = new HttpGet(String.format("%s/issues/%s/", base_url, issue.getId()));

		issue_exists.addHeader("Authorization", "Basic " + credentials);
		issue_exists.setHeader("Accept", "application/json");
		issue_exists.addHeader("Content-type", "application/json");

		HttpResponse response = null;
		try {
			response = httpClient.execute(issue_exists);
		} catch (ClientProtocolException e2) {

			e2.printStackTrace();
		} catch (IOException e2) {

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

			exc.printStackTrace();
		}
		if (!issue_created) {
			update_issue_intersect(issue, credentials, true);
		}
		if (issue.isDeleted()) {
			delete_issue((long) issue.getId());
			return;
		} else {
			ConcurrentHashMap<Long, Article> allarticles = issue.getArticles_list();
			ConcurrentHashMap<Long, Article> articles = issue.getSyncArticles_list();

			Set<Long> article_keys = articles.keySet();

			System.out.println("articles to sync : " + articles.size());
			for (Long key : article_keys) {
				Article current_article = articles.get(key);

				System.out.println("sync: " + current_article.shouldBeSynced());
				if (current_article.shouldBeSynced()) {
					if (current_article.isDeleted()) {
						delete_article(current_article.getId());
					} else {
						try {
							update_article_intersect_less_requests(current_article, credentials);
							sync_authors_intersect_article(current_article, credentials, false);
						} catch (Exception es) {

							issue_countdown_storage.put((long) issue.getId(), true);
							JOptionPane.showMessageDialog(null, "Lost connection to server.");

							return;
						}
						current_article.setSync(false);
						allarticles.replace((long) current_article.getId(), current_article);
						article_storage.put((long) current_article.getId(), current_article);

						ConcurrentHashMap<Long, ArticleFile> files = file_storage.get((long) current_article.getId());
						// null
						if (files != null) {
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

								e2.printStackTrace();
							} catch (IOException e2) {

								e2.printStackTrace();
							}
							new JsonFactory();
							InputStream result = response.getEntity().getContent();
							org.json.simple.parser.JSONParser jsonParser = new JSONParser();

							new JSONObject();

							JSONObject setting;
							try {
								setting = (JSONObject) jsonParser.parse(IOUtils.toString(result));

								try {
									InputStream is = response.getEntity().getContent();
									is.close();
								} catch (IOException exc) {

									exc.printStackTrace();
								}
								System.out.println(setting);
								if (setting == null) {
								} else {
									String[] file_ids = null;
									String ids = ((String) setting.get("files"));
									if (ids.contains(",")) {
										file_ids = ((String) setting.get("files")).split(",");
									} else {

										String one_id = (String) setting.get("files");
										try {
											long one = Long.parseLong(one_id);
											file_ids = new String[1];
											file_ids[0] = (String) setting.get("files");
										} catch (Exception e) {
										}
									}
									if (file_ids != null) {
										for (String file_id : file_ids) {
											if (!files.containsKey((long) Long.parseLong(file_id))) {
												delete_file((long) Long.parseLong(file_id));
											}
										}
									}
								}
							} catch (ParseException e) {

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
				}
			}
			issue.setArticles_list(allarticles);
			issue.setSync(false);
			issue_storage.put((long) issue.getId(), issue);
		}
		issue_countdown_storage.put((long) issue.getId(), true);
		System.out.println("method - countdown: " + issue_countdown_storage.get((long) issue.getId()));

		System.out.println("END METHD: " + issue.getArticles_list().size());
	}

	public static ArrayList<Long> update_unpublished_articles_local_single_request(String credentials)
			throws IllegalStateException, IOException {
		ArrayList<Long> return_articles = new ArrayList<Long>();
		boolean status = status_online();
		if (!status) {
			return return_articles;
		}
		System.out.println("Getting Articles");

		ArrayList<Article> articles_list = new ArrayList<Article>();
		try {
			HttpGet published_articles = new HttpGet(String.format("%s/get/articles/?format=json", base_url));
			// settingCheck.setEntity(new StringEntity(obj.toJSONString()));
			published_articles.addHeader("Authorization", "Basic " + credentials);
			published_articles.setHeader("Accept", "application/json");
			published_articles.addHeader("Content-type", "application/json");

			HttpResponse response = null;

			response = httpClient.execute(published_articles);

			new JsonFactory();
			InputStream result = response.getEntity().getContent();
			org.json.simple.parser.JSONParser jsonParser = new JSONParser();
			new JSONObject();

			JSONObject setting = (JSONObject) jsonParser.parse(IOUtils.toString(result));
			try {
				InputStream is = response.getEntity().getContent();
				is.close();
			} catch (IOException exc) {

				exc.printStackTrace();
			}
			System.out.println(setting);
			if (setting == null) {
			} else {
				String[] article_ids = null;
				String ids = ((String) setting.get("unpublished_articles"));
				System.out.println(ids);
				System.out.println(setting);
				if (ids.contains(",")) {
					article_ids = ((String) setting.get("unpublished_articles")).split(",");
				} else {

					String one_id = (String) setting.get("unpublished_articles");
					try {
						long one = Long.parseLong(one_id);
						article_ids = new String[1];
						article_ids[0] = (String) setting.get("unpublished_articles");
					} catch (Exception e) {
					}
				}
				System.out.println(article_ids);
				if (article_ids != null) {

					System.out.println("NOT NULL");
					for (String id : article_ids) {
						System.out.println(id);
						System.out.println(unpublished_article_storage.containsKey((long) Long.parseLong(id)));
						System.out.println(article_storage.containsKey((long) Long.parseLong(id)));
						if (unpublished_article_storage.containsKey((long) Long.parseLong(id))
								|| article_storage.containsKey((long) Long.parseLong(id))) {
							continue;
						} else {
							System.out.println("NEW ARTICLE");
							return_articles.add(Long.parseLong(id));
							System.out.println(id);
							HttpGet single_article = new HttpGet(
									String.format("%s/app/article-settings/%s/?format=json", base_url, id));
							// settingCheck.setEntity(new
							// StringEntity(obj.toJSONString()));
							single_article.addHeader("Authorization", "Basic " + credentials);
							single_article.setHeader("Accept", "application/json");
							single_article.addHeader("Content-type", "application/json");

							response = null;

							response = httpClient.execute(single_article);
							if (response.getStatusLine().getStatusCode() == 200) {
								result = response.getEntity().getContent();

								jsonParser = new JSONParser();
								new JSONObject();

								setting = (JSONObject) jsonParser.parse(IOUtils.toString(result));
								System.out.println((JSONObject) setting.get("article"));
								System.out.println((JSONArray) setting.get("settings"));
								try {
									InputStream is = response.getEntity().getContent();
									is.close();
								} catch (IOException exc) {

									exc.printStackTrace();
								}
								JSONArray all_settings = (JSONArray) setting.get("settings");

								Article new_article = JSONToArticle_single_request((JSONObject) setting.get("article"));
								if (((long) articles_id) < ((long) new_article.getId())) {
									articles_id = new_article.getId();
								}
								System.out.println(all_settings.toJSONString());
								String funding = null;
								String ci = null;
								for (Object set : all_settings.toArray()) {
									JSONObject current_setting = (JSONObject) set;
									System.out.println(current_setting);

									System.out.println("SETTING: " + current_setting.get("setting_name"));
									switch ((String) current_setting.get("setting_name")) {
									case "title":
										new_article.setTitle((String) current_setting.get("setting_value"));
										continue;
									case "abstract":
										String abstract_text = (String) current_setting.get("setting_value");
										String new_abs = "";
										if (abstract_text != null) {
											if (abstract_text.compareTo("") != 0 || abstract_text.isEmpty() == true) {
												String abs = Jsoup.parse((String) current_setting.get("setting_value"))
														.text();
												String[] words = abs.split(" ");

												int j = 0;
												for (String word : words) {
													new_abs = new_abs + " " + word;
													if (j % 8 == 0 && j != 0) {
														new_abs = new_abs + "\r\n";
													}
													j++;
												}
											}
										}
										new_article.setAbstract_text(new_abs);
										System.out.println("ABSTRACT--- " + new_abs);
										System.out.println(abstract_text);
										continue;
									case "funding":
										funding = (String) current_setting.get("setting_value");
										continue;
									case "competingInterests":
										ci = (String) current_setting.get("setting_value");
										continue;
									case "pub-id::doi":
										new_article.setDoi((String) current_setting.get("setting_value"));
										continue;

									default:

										System.out.println("Invalid setting " + current_setting.get("setting_name"));
									}

								}
								if (ci != null || funding != null) {
									if (metadata_storage.containsKey((long) new_article.getId())) {
										Metadata meta = metadata_storage.get((long) new_article.getId());
										meta.setCompeting_interests(ci);
										meta.setFunding(funding);
										metadata_storage.put((long) new_article.getId(), meta);
									} else {
										metadata_id++;
										Metadata meta = new Metadata(metadata_id, (long) new_article.getId(), ci,
												funding);
										metadata_storage.put((long) new_article.getId(), meta);
									}

								}

								System.out.println(new_article);
								article_author_storage.put((long) new_article.getId(), new ArrayList<Author>());
								articles_list.add(new_article);

								unpublished_article_storage.put((long) new_article.getId(), new_article);
								author_primary_storage.put((long) new_article.getId(),
										new ConcurrentHashMap<Long, Boolean>());
							}
						}
					}
				}
			}

			for (Article a : articles_list) {

				HttpGet article_files = new HttpGet(String.format("%s/get/files/%s/?format=json", base_url, a.getId()));
				// settingCheck.setEntity(new
				// StringEntity(obj.toJSONString()));
				article_files.addHeader("Authorization", "Basic " + credentials);
				article_files.setHeader("Accept", "application/json");
				article_files.addHeader("Content-type", "application/json");

				response = null;
				response = httpClient.execute(article_files);

				new JsonFactory();
				result = response.getEntity().getContent();
				jsonParser = new JSONParser();

				new JSONObject();

				setting = new JSONObject();
				try {
					setting = (JSONObject) jsonParser.parse(IOUtils.toString(result));
					try {
						InputStream is = response.getEntity().getContent();
						is.close();
					} catch (IOException exc) {

						exc.printStackTrace();
					}

					System.out.println(setting);
					if (setting == null) {
					} else {
						String[] file_ids = null;
						String ids = ((String) setting.get("files"));
						if (ids.contains(",")) {
							file_ids = ((String) setting.get("files")).split(",");
						} else {

							String one_id = (String) setting.get("files");
							try {
								long one = Long.parseLong(one_id);
								file_ids = new String[1];
								file_ids[0] = (String) setting.get("files");
							} catch (Exception e) {
							}
						}
						if (file_ids != null) {
							for (String file_id : file_ids) {
								file_download(a.getId(), Long.parseLong(file_id), false);
							}
						}
					}
				} catch (ParseException e) {

					e.printStackTrace();
				}
				System.out.println("ADDING TO STORAGE: " + a.getId());

			}

		} catch (ClientProtocolException e2) {

			JOptionPane.showMessageDialog(null, "Lost connection to server.");
			return return_articles;
		} catch (IOException e2) {

			JOptionPane.showMessageDialog(null, "Lost connection to server.");
			return return_articles;
		} catch (ParseException e) {

			e.printStackTrace();
		}

		return return_articles;
	}

	public static void update_articles_local_single_request(Issue issue, String credentials)
			throws IllegalStateException, IOException {

		boolean status = status_online();
		if (!status) {
			return;
		}
		System.out.println("Getting Articles");

		ArrayList<Article> articles_list = new ArrayList<Article>();
		IssueToJSON(issue);
		HttpGet issue_exists = new HttpGet(String.format("%s/issues/%s/", base_url, issue.getId()));

		issue_exists.addHeader("Authorization", "Basic " + credentials);
		issue_exists.setHeader("Accept", "application/json");
		issue_exists.addHeader("Content-type", "application/json");

		HttpResponse response = null;
		try {
			response = httpClient.execute(issue_exists);

			boolean issue_created = false;
			if (response.getStatusLine().getStatusCode() == 200) {
				issue_created = true;
			}
			try {
				InputStream is = response.getEntity().getContent();
				is.close();
			} catch (IOException exc) {

				exc.printStackTrace();
			}
			if (!issue_created) {
				System.out.println("Issue not created");
				update_issue_intersect(issue, credentials, true);
				return;
			}

			System.out.println(response.toString());
			HttpGet published_articles = new HttpGet(
					String.format("%s/get/published-articles/%s/?format=json", base_url, issue.getId()));
			// settingCheck.setEntity(new StringEntity(obj.toJSONString()));
			published_articles.addHeader("Authorization", "Basic " + credentials);
			published_articles.setHeader("Accept", "application/json");
			published_articles.addHeader("Content-type", "application/json");

			response = null;

			response = httpClient.execute(published_articles);

			new JsonFactory();
			InputStream result = response.getEntity().getContent();
			org.json.simple.parser.JSONParser jsonParser = new JSONParser();
			new JSONObject();

			JSONObject setting = (JSONObject) jsonParser.parse(IOUtils.toString(result));
			try {
				InputStream is = response.getEntity().getContent();
				is.close();
			} catch (IOException exc) {

				exc.printStackTrace();
			}
			System.out.println(setting);
			if (setting == null) {
			} else {
				String[] article_ids = null;
				String ids = ((String) setting.get("articles"));
				if (ids.contains(",")) {
					article_ids = ((String) setting.get("articles")).split(",");
				} else {

					String one_id = (String) setting.get("articles");
					try {
						long one = Long.parseLong(one_id);
						article_ids = new String[1];
						article_ids[0] = (String) setting.get("articles");
					} catch (Exception e) {
					}
				}
				String[] unpublished_article_ids = null;
				String unpublished_ids = ((String) setting.get("unpublished"));
				if (unpublished_ids.contains(",")) {
					unpublished_article_ids = ((String) setting.get("unpublished")).split(",");
				} else {

					String one_id = (String) setting.get("unpublished");
					try {
						long one = Long.parseLong(one_id);
						unpublished_article_ids = new String[1];
						unpublished_article_ids[0] = (String) setting.get("unpublished");
					} catch (Exception e) {
					}
				}
				if (unpublished_article_ids != null) {
					for (String id : unpublished_article_ids) {

						System.out.println(id);
						HttpGet single_article = new HttpGet(
								String.format("%s/app/article-settings/%s/?format=json", base_url, id));
						// settingCheck.setEntity(new
						// StringEntity(obj.toJSONString()));
						single_article.addHeader("Authorization", "Basic " + credentials);
						single_article.setHeader("Accept", "application/json");
						single_article.addHeader("Content-type", "application/json");

						response = null;

						response = httpClient.execute(single_article);
						if (response.getStatusLine().getStatusCode() == 200) {
							result = response.getEntity().getContent();

							jsonParser = new JSONParser();
							new JSONObject();

							setting = (JSONObject) jsonParser.parse(IOUtils.toString(result));
							System.out.println((JSONObject) setting.get("article"));
							System.out.println((JSONArray) setting.get("settings"));
							try {
								InputStream is = response.getEntity().getContent();
								is.close();
							} catch (IOException exc) {

								exc.printStackTrace();
							}
							JSONArray all_settings = (JSONArray) setting.get("settings");

							Article new_article = JSONToArticle_single_request((JSONObject) setting.get("article"),
									issue);

							if (((long) articles_id) < ((long) new_article.getId())) {
								articles_id = new_article.getId();
							}

							System.out.println(all_settings.toJSONString());
							String funding = null;
							String ci = null;
							for (Object set : all_settings.toArray()) {
								JSONObject current_setting = (JSONObject) set;
								System.out.println(current_setting);

								System.out.println("SETTING: " + current_setting.get("setting_name"));
								switch ((String) current_setting.get("setting_name")) {
								case "title":
									new_article.setTitle((String) current_setting.get("setting_value"));
									continue;
								case "abstract":
									String abstract_text = (String) current_setting.get("setting_value");
									String new_abs = "";
									if (abstract_text != null) {
										if (abstract_text.compareTo("") != 0 || abstract_text.isEmpty() == true) {
											String abs = Jsoup.parse((String) current_setting.get("setting_value"))
													.text();
											String[] words = abs.split(" ");

											int j = 0;
											for (String word : words) {
												new_abs = new_abs + " " + word;
												if (j % 8 == 0 && j != 0) {
													new_abs = new_abs + "\r\n";
												}
												j++;
											}
										}
									}
									new_article.setAbstract_text(new_abs);
									System.out.println("ABSTRACT--- " + new_abs);
									System.out.println(abstract_text);
									continue;
								case "funding":
									funding = (String) current_setting.get("setting_value");
									continue;
								case "competingInterests":
									ci = (String) current_setting.get("setting_value");
									continue;
								case "pub-id::doi":
									new_article.setDoi((String) current_setting.get("setting_value"));
									continue;

								default:

									System.out.println("Invalid setting " + current_setting.get("setting_name"));
								}

							}
							if (ci != null || funding != null) {
								if (metadata_storage.containsKey((long) new_article.getId())) {
									Metadata meta = metadata_storage.get((long) new_article.getId());
									meta.setCompeting_interests(ci);
									meta.setFunding(funding);
									metadata_storage.put((long) new_article.getId(), meta);
								} else {
									metadata_id++;
									Metadata meta = new Metadata(metadata_id, (long) new_article.getId(), ci, funding);
									metadata_storage.put((long) new_article.getId(), meta);
								}

							}

							System.out.println(new_article);
							article_author_storage.put(new_article.getId(), new ArrayList<Author>());
							articles_list.add(new_article);
						}
					}

				}
				if (article_ids != null) {
					for (String id : article_ids) {

						System.out.println(id);
						HttpGet single_article = new HttpGet(
								String.format("%s/app/article-settings/%s/?format=json", base_url, id));
						// settingCheck.setEntity(new
						// StringEntity(obj.toJSONString()));
						single_article.addHeader("Authorization", "Basic " + credentials);
						single_article.setHeader("Accept", "application/json");
						single_article.addHeader("Content-type", "application/json");

						response = null;

						response = httpClient.execute(single_article);
						if (response.getStatusLine().getStatusCode() == 200) {
							result = response.getEntity().getContent();

							jsonParser = new JSONParser();
							new JSONObject();

							setting = (JSONObject) jsonParser.parse(IOUtils.toString(result));
							System.out.println((JSONObject) setting.get("article"));
							System.out.println((JSONArray) setting.get("settings"));
							try {
								InputStream is = response.getEntity().getContent();
								is.close();
							} catch (IOException exc) {

								exc.printStackTrace();
							}
							JSONArray all_settings = (JSONArray) setting.get("settings");

							Article new_article = JSONToArticle_single_request((JSONObject) setting.get("article"),
									issue);
							if (((long) articles_id) < ((long) new_article.getId())) {
								articles_id = new_article.getId();
							}
							System.out.println(all_settings.toJSONString());
							String funding = null;
							String ci = null;
							for (Object set : all_settings.toArray()) {
								JSONObject current_setting = (JSONObject) set;
								System.out.println(current_setting);

								System.out.println("SETTING: " + current_setting.get("setting_name"));
								switch ((String) current_setting.get("setting_name")) {
								case "title":
									new_article.setTitle((String) current_setting.get("setting_value"));
									continue;
								case "abstract":
									String abstract_text = (String) current_setting.get("setting_value");
									String new_abs = "";
									if (abstract_text != null) {
										if (abstract_text.compareTo("") != 0 || abstract_text.isEmpty() == true) {
											String abs = Jsoup.parse((String) current_setting.get("setting_value"))
													.text();
											String[] words = abs.split(" ");

											int j = 0;
											for (String word : words) {
												new_abs = new_abs + " " + word;
												if (j % 8 == 0 && j != 0) {
													new_abs = new_abs + "\r\n";
												}
												j++;
											}
										}
									}
									new_article.setAbstract_text(new_abs);
									System.out.println("ABSTRACT--- " + new_abs);
									System.out.println(abstract_text);
									continue;
								case "funding":
									funding = (String) current_setting.get("setting_value");
									continue;
								case "competingInterests":
									ci = (String) current_setting.get("setting_value");
									continue;
								case "pub-id::doi":
									new_article.setDoi((String) current_setting.get("setting_value"));
									continue;

								default:

									System.out.println("Invalid setting " + current_setting.get("setting_name"));
								}

							}
							if (ci != null || funding != null) {
								if (metadata_storage.containsKey((long) new_article.getId())) {
									Metadata meta = metadata_storage.get((long) new_article.getId());
									meta.setCompeting_interests(ci);
									meta.setFunding(funding);
									metadata_storage.put((long) new_article.getId(), meta);
								} else {
									metadata_id++;
									Metadata meta = new Metadata(metadata_id, (long) new_article.getId(), ci, funding);
									metadata_storage.put((long) new_article.getId(), meta);
								}

							}

							System.out.println(new_article);
							article_author_storage.put(new_article.getId(), new ArrayList<Author>());
							articles_list.add(new_article);
						}
					}
				}
			}

			for (Article a : articles_list) {

				HttpGet article_files = new HttpGet(String.format("%s/get/files/%s/?format=json", base_url, a.getId()));
				// settingCheck.setEntity(new
				// StringEntity(obj.toJSONString()));
				article_files.addHeader("Authorization", "Basic " + credentials);
				article_files.setHeader("Accept", "application/json");
				article_files.addHeader("Content-type", "application/json");

				response = null;
				response = httpClient.execute(article_files);

				new JsonFactory();
				result = response.getEntity().getContent();
				jsonParser = new JSONParser();

				new JSONObject();

				setting = new JSONObject();
				try {
					setting = (JSONObject) jsonParser.parse(IOUtils.toString(result));
					try {
						InputStream is = response.getEntity().getContent();
						is.close();
					} catch (IOException exc) {

						exc.printStackTrace();
					}

					System.out.println(setting);
					if (setting == null) {
					} else {
						String[] file_ids = null;
						String ids = ((String) setting.get("files"));
						if (ids.contains(",")) {
							file_ids = ((String) setting.get("files")).split(",");
						} else {

							String one_id = (String) setting.get("files");
							try {
								long one = Long.parseLong(one_id);
								file_ids = new String[1];
								file_ids[0] = (String) setting.get("files");
							} catch (Exception e) {
							}
						}
						if (file_ids != null) {
							for (String file_id : file_ids) {
								file_download(a.getId(), Long.parseLong(file_id), false);
							}
						}
					}
				} catch (ParseException e) {

					e.printStackTrace();
				}
				a.setIssue_fk(issue);
				article_storage.put(a.getId(), a);
				author_primary_storage.put(a.getId(), new ConcurrentHashMap<Long, Boolean>());
				System.out.println(a.getIssue_fk().getId());
				issue.add_article(a.getId(), a);

			}
			System.out.println(articles_list.size() + " - " + article_author_storage.size());
			issue_storage.put(issue.getId(), issue);
		} catch (ClientProtocolException e2) {

			JOptionPane.showMessageDialog(null, "Lost connection to server.");
			return;
		} catch (IOException e2) {

			JOptionPane.showMessageDialog(null, "Lost connection to server.");
			return;
		} catch (ParseException e) {

			e.printStackTrace();
		}
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

				e2.printStackTrace();
			} catch (IOException e2) {

				e2.printStackTrace();
			}
			new JsonFactory();
			InputStream result = response.getEntity().getContent();
			org.json.simple.parser.JSONParser jsonParser = new JSONParser();
			jsonParser = new JSONParser();
			new JSONObject();
			try {
				JSONObject journal_json = (JSONObject) jsonParser.parse(IOUtils.toString(result));
				journal = new Journal((long) journal_json.get("id"), (String) journal_json.get("path"),
						Float.parseFloat(Double.toString((double) journal_json.get("seq"))),
						(String) journal_json.get("primary_locale"), (long) journal_json.get("enabled"));
				journal_storage.put(journal.getId(), journal);

			} catch (ParseException e) {

				e.printStackTrace();
			}
			try {
				InputStream is = response.getEntity().getContent();
				is.close();
			} catch (IOException exc) {

				exc.printStackTrace();
			}
			return journal;
		}
	}

	public static boolean check_new_issues(String credentials) throws IOException {
		boolean check = false;
		if (!status_online()) {
			return false;
		}
		HttpGet totalCheck = new HttpGet(String.format("%s/get/issue-ids/?format=json", base_url));
		// settingCheck.setEntity(new
		// StringEntity(obj.toJSONString()));
		totalCheck.addHeader("Authorization", "Basic " + credentials);
		totalCheck.setHeader("Accept", "application/json");
		totalCheck.addHeader("Content-type", "application/json");

		HttpResponse response = null;
		try {
			response = httpClient.execute(totalCheck);
		} catch (ClientProtocolException e2) {

			e2.printStackTrace();
		} catch (IOException e2) {

			e2.printStackTrace();
		}
		new JsonFactory();
		InputStream result = response.getEntity().getContent();
		System.out.println(response.getStatusLine().toString());
		org.json.simple.parser.JSONParser jsonParser = new JSONParser();
		jsonParser = new JSONParser();
		new JSONObject();
		try {
			JSONObject issues_json = (JSONObject) jsonParser.parse(IOUtils.toString(result));
			String[] issue_ids = null;
			String ids = ((String) issues_json.get("issues"));
			if (ids.contains(",")) {
				issue_ids = ((String) issues_json.get("issues")).split(",");
			} else {

				String one_id = (String) issues_json.get("issues");
				try {
					long one = Long.parseLong(one_id);
					issue_ids = new String[1];
					issue_ids[0] = (String) issues_json.get("issues");
				} catch (Exception e) {
				}
			}
			System.out.println(ids);
			Set<Long> issue_keys = issue_storage.keySet();

			if (issue_ids != null) {
				for (String id : issue_ids) {
					if (!issue_keys.contains(Long.parseLong(id))) {
						check = true;
					}
				}
			}

		} catch (ParseException e) {

			e.printStackTrace();
		}
		try {
			InputStream is = response.getEntity().getContent();
			is.close();
		} catch (IOException exc) {

			exc.printStackTrace();
		}
		return check;

	}

	public static int progress_countdown_estimate_total(String credentials) throws IOException {
		int countdown = 150;
		if (!status_online()) {
			return countdown;
		}
		HttpGet totalCheck = new HttpGet(String.format("%s/get/total/count/?format=json", base_url));
		// settingCheck.setEntity(new
		// StringEntity(obj.toJSONString()));
		totalCheck.addHeader("Authorization", "Basic " + credentials);
		totalCheck.setHeader("Accept", "application/json");
		totalCheck.addHeader("Content-type", "application/json");

		HttpResponse response = null;
		try {
			response = httpClient.execute(totalCheck);
		} catch (ClientProtocolException e2) {

			e2.printStackTrace();
		} catch (IOException e2) {

			e2.printStackTrace();
		}
		new JsonFactory();
		InputStream result = response.getEntity().getContent();
		org.json.simple.parser.JSONParser jsonParser = new JSONParser();
		jsonParser = new JSONParser();
		new JSONObject();
		try {
			JSONObject countdown_json = (JSONObject) jsonParser.parse(IOUtils.toString(result));
			countdown = (int) (((int) (long) countdown_json.get("issues")) * 2
					+ ((int) (long) countdown_json.get("articles")) * 0.5
					+ ((int) (long) countdown_json.get("authors")) * 0.5);

		} catch (ParseException e) {

			e.printStackTrace();
		}
		try {
			InputStream is = response.getEntity().getContent();
			is.close();
		} catch (IOException exc) {

			exc.printStackTrace();
		}
		return 150;

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

			e2.printStackTrace();
		} catch (IOException e2) {

			e2.printStackTrace();
		}
		new JsonFactory();
		InputStream result = response.getEntity().getContent();
		org.json.simple.parser.JSONParser jsonParser = new JSONParser();
		JSONObject issue_json = new JSONObject();
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

					e2.printStackTrace();
				} catch (IOException e2) {

					e2.printStackTrace();
				}
				new JsonFactory();
				result = response.getEntity().getContent();
				jsonParser = new JSONParser();
				JSONObject setting_json = new JSONObject();
				try {
					JSONObject setting = (JSONObject) jsonParser.parse(IOUtils.toString(result));
					System.out.println(setting.get("count"));
					System.out.println(setting);
					Long count = (Long) setting.get("count");
					if (count == null || count == 0) {
					} else {
						JSONArray results = (JSONArray) setting.get("results");
						System.out.println(results.get(0));
						setting_json = (JSONObject) results.get(0);
						new_issue.setTitle((String) setting_json.get("setting_value"));
						// new_issue.setShow_title((String)
						// setting_json.get("setting_value"));
					}
				} catch (ParseException e) {

					e.printStackTrace();
				}
				new_issue.setSync(true);
				issue_storage.put(issue_id, new_issue);
				issue_screens.put(issue_id, new JFrame());
				article_screens.put(issue_id, new ConcurrentHashMap<Long, JFrame>());

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

			e.printStackTrace();
		}
		try {
			InputStream is = response.getEntity().getContent();
			is.close();
		} catch (IOException exc) {

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

			e2.printStackTrace();
		} catch (IOException e2) {

			e2.printStackTrace();
		}
		new JsonFactory();
		InputStream result = response.getEntity().getContent();
		org.json.simple.parser.JSONParser jsonParser = new JSONParser();
		JSONObject issue_json = new JSONObject();
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

					if (((long) i_id) < ((long) new_issue.getId())) {
						i_id = new_issue.getId();
					}

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

						e2.printStackTrace();
					} catch (IOException e2) {

						e2.printStackTrace();
					}
					new JsonFactory();
					result = response.getEntity().getContent();
					jsonParser = new JSONParser();
					JSONObject setting_json = new JSONObject();
					try {
						JSONObject setting = (JSONObject) jsonParser.parse(IOUtils.toString(result));
						System.out.println(setting.get("count"));
						System.out.println(setting);
						Long count = (Long) setting.get("count");
						if (count == null || count == 0) {
						} else {
							JSONArray results = (JSONArray) setting.get("results");
							System.out.println(results.get(0));
							setting_json = (JSONObject) results.get(0);
							new_issue.setTitle((String) setting_json.get("setting_value"));
							// new_issue.setShow_title((String)
							// setting_json.get("setting_value"));
						}
					} catch (ParseException e) {

						e.printStackTrace();
					}

					new_issues.add(new_issue);
					issue_storage.put(issue_id, new_issue);
					issue_screens.put(issue_id, new JFrame());
					article_screens.put(issue_id, new ConcurrentHashMap<Long, JFrame>());
					update_articles_local_single_request(new_issue, encoding);
					get_authors_remote_single_request(issue_id, encoding, false);

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

			e.printStackTrace();
		}
		try {
			InputStream is = response.getEntity().getContent();
			is.close();
		} catch (IOException exc) {

			exc.printStackTrace();
		}
		new_issues_process_done = true;

		get_sections(Long.parseLong(app_settings.get("journal_id")), encoding, false);
		return new_issues;
	}

	public static void get_authors_remote_single_request(long issue_id, String credentials, boolean update_local)
			throws IllegalStateException, IOException {
		boolean status = status_online();
		System.out.println("GETTING AUTHORS");
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
			System.out.println(String.format("%s/get/issue/authors/%s/?format=json", base_url, issue_id));
			e2.printStackTrace();
		} catch (IOException e2) {

			System.out.println(String.format("%s/get/issue/authors/%s/?format=json", base_url, issue_id));
			e2.printStackTrace();
		}
		new JsonFactory();
		if (response.getStatusLine().getStatusCode() == 200) {
			InputStream result = response.getEntity().getContent();
			org.json.simple.parser.JSONParser jsonParser = new JSONParser();
			JSONObject author_json = new JSONObject();
			try {
				JSONObject issue_obj = (JSONObject) jsonParser.parse(IOUtils.toString(result));
				JSONArray author_ids = (JSONArray) issue_obj.get("authors");
				System.out.println(author_ids);
				try {
					InputStream is = response.getEntity().getContent();
					is.close();
				} catch (IOException exc) {

					exc.printStackTrace();
				}
				for (int i = 0; i < author_ids.size(); i++) {
					long author_current_id = (long) author_ids.get(i);
					httpGet = new HttpGet(String.format("%s/app/authors/%s/?format=json", base_url, author_current_id));
					httpGet.addHeader("Authorization", "Basic " + credentials);
					httpGet.setHeader("Accept", "application/json");
					httpGet.addHeader("Content-type", "application/json");

					response = null;
					try {
						response = httpClient.execute(httpGet);
					} catch (ClientProtocolException e2) {

						e2.printStackTrace();
					} catch (IOException e2) {

						e2.printStackTrace();
					}
					result = response.getEntity().getContent();
					jsonParser = new JSONParser();
					author_json = (JSONObject) jsonParser.parse(IOUtils.toString(result));

					System.out.println(author_storage.containsKey(author_current_id));
					System.out.println("---" + author_storage.containsKey((long) author_current_id));
					long author_new_id = (long) author_json.get("id");
					System.out.println("---" + author_new_id);

					System.out.println("ELSE");
					Author new_author = new Author(author_new_id);
					if (((long) author_id) < ((long) author_new_id)) {
						author_id = author_new_id;
					}
					new_author = JSONToAuthor_single_request(author_json, new_author);

					JSONArray all_settings = (JSONArray) author_json.get("settings");

					try {
						InputStream is = response.getEntity().getContent();
						is.close();
					} catch (IOException exc) {

						exc.printStackTrace();
					}
					System.out.println(all_settings.toArray());
					for (Object set : all_settings.toArray()) {
						JSONObject current_setting = (JSONObject) set;
						System.out.println(current_setting);
						switch ((String) current_setting.get("setting_name")) {
						case "biography":
							new_author.setBio((String) current_setting.get("setting_value"));
							continue;
						case "twitter":
							new_author.setTwitter((String) current_setting.get("setting_value"));
							continue;
						case "department":
							new_author.setDepartment((String) current_setting.get("setting_value"));
							continue;
						case "affiliation":
							new_author.setAffiliation((String) current_setting.get("setting_value"));
							continue;
						case "orcid":
							new_author.setOrcid((String) current_setting.get("setting_value"));
							continue;
						default:
							System.out.println("Invalid setting " + current_setting.get("setting_name"));
						}

					}

					System.out.println(article_storage.containsKey((long) new_author.getArticle_id()));
					System.out.println(article_storage.containsKey(new_author.getArticle_id()));
					if (article_storage.containsKey((long) new_author.getArticle_id())) {
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
						ConcurrentHashMap<Long, Boolean> primary = author_primary_storage.get(this_article.getId());
						primary.put(new_author.getId(), false);
						author_primary_storage.put(this_article.getId(), primary);
						System.out.println("Authors: " + article_author_storage.get(new_author.getArticle_id()).size());

					}
				}

				/*
				 * System.out.println(issue_obj.get("count"));
				 * System.out.println(issue_obj); String detail = (String)
				 * issue_obj.get("detail"); if (detail == null) { exists =
				 * false; } else { JSONArray results = (JSONArray)
				 * issue_obj.get("results"); System.out.println(results.get(0));
				 * issue_json = (JSONObject) results.get(0);
				 * System.out.println(issue_json.get("id")); issue =
				 * JSONToIssue(issue_json, issue); }
				 */
			} catch (ParseException e) {

				e.printStackTrace();
			}
		}
		try {
			InputStream is = response.getEntity().getContent();
			is.close();
		} catch (IOException exc) {

			exc.printStackTrace();
		}

		System.out.println("Authors: " + author_storage.size());
		System.out.println("Authors: " + author_count);

	}

	public static void get_article_authors_remote_single_request(long article_id, String credentials,
			boolean update_local) throws IllegalStateException, IOException {
		boolean status = status_online();
		System.out.println("GETTING AUTHORS");
		if (!status) {
			return;
		}

		int author_count = 0;
		HttpGet httpGet = new HttpGet(String.format("%s/get/article/authors/%s/?format=json", base_url, article_id));
		httpGet.addHeader("Authorization", "Basic " + credentials);
		httpGet.setHeader("Accept", "application/json");
		httpGet.addHeader("Content-type", "application/json");

		HttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
		} catch (ClientProtocolException e2) {
			System.out.println(String.format("%s/get/article/authors/%s/?format=json", base_url, article_id));
			e2.printStackTrace();
		} catch (IOException e2) {

			System.out.println(String.format("%s/get/article/authors/%s/?format=json", base_url, article_id));
			e2.printStackTrace();
		}
		new JsonFactory();
		if (response.getStatusLine().getStatusCode() == 200) {
			InputStream result = response.getEntity().getContent();
			org.json.simple.parser.JSONParser jsonParser = new JSONParser();
			JSONObject author_json = new JSONObject();
			try {
				JSONObject issue_obj = (JSONObject) jsonParser.parse(IOUtils.toString(result));
				JSONArray author_ids = (JSONArray) issue_obj.get("authors");
				System.out.println(author_ids);
				try {
					InputStream is = response.getEntity().getContent();
					is.close();
				} catch (IOException exc) {

					exc.printStackTrace();
				}
				for (int i = 0; i < author_ids.size(); i++) {
					long author_current_id = (long) author_ids.get(i);
					httpGet = new HttpGet(String.format("%s/app/authors/%s/?format=json", base_url, author_current_id));
					httpGet.addHeader("Authorization", "Basic " + credentials);
					httpGet.setHeader("Accept", "application/json");
					httpGet.addHeader("Content-type", "application/json");

					response = null;
					try {
						response = httpClient.execute(httpGet);
					} catch (ClientProtocolException e2) {

						e2.printStackTrace();
					} catch (IOException e2) {

						e2.printStackTrace();
					}
					result = response.getEntity().getContent();
					jsonParser = new JSONParser();
					author_json = (JSONObject) jsonParser.parse(IOUtils.toString(result));

					long author_new_id = (long) author_json.get("id");
					if (((long) author_id) < ((long) author_new_id)) {
						author_id = author_new_id;
					}
					System.out.println("---" + author_new_id);

					System.out.println("ELSE");
					Author new_author = new Author(author_new_id);

					new_author = JSONToAuthor_single_request(author_json, new_author);

					JSONArray all_settings = (JSONArray) author_json.get("settings");

					try {
						InputStream is = response.getEntity().getContent();
						is.close();
					} catch (IOException exc) {

						exc.printStackTrace();
					}
					System.out.println(all_settings.toArray());
					for (Object set : all_settings.toArray()) {
						JSONObject current_setting = (JSONObject) set;
						System.out.println(current_setting);
						switch ((String) current_setting.get("setting_name")) {
						case "biography":
							new_author.setBio((String) current_setting.get("setting_value"));
							continue;
						case "twitter":
							new_author.setTwitter((String) current_setting.get("setting_value"));
							continue;
						case "department":
							new_author.setDepartment((String) current_setting.get("setting_value"));
							continue;
						case "affiliation":
							new_author.setAffiliation((String) current_setting.get("setting_value"));
							continue;
						case "orcid":
							new_author.setOrcid((String) current_setting.get("setting_value"));
							continue;
						default:
							System.out.println("Invalid setting " + current_setting.get("setting_name"));
						}

					}

					System.out.println(article_storage.containsKey((long) new_author.getArticle_id()));
					System.out.println(article_storage.containsKey(new_author.getArticle_id()));
					if (article_storage.containsKey((long) new_author.getArticle_id())) {
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

						this_article.add_author(new_author);
						article_storage.put(this_article.getId(), this_article);
						System.out.println(new_author);
						author_storage.put(new_author.getId(), new_author);
						ConcurrentHashMap<Long, Boolean> primary = author_primary_storage.get(this_article.getId());
						primary.put(new_author.getId(), false);
						author_primary_storage.put(this_article.getId(), primary);
						System.out.println("Authors: " + article_author_storage.get(new_author.getArticle_id()).size());

					}
				}

				/*
				 * System.out.println(issue_obj.get("count"));
				 * System.out.println(issue_obj); String detail = (String)
				 * issue_obj.get("detail"); if (detail == null) { exists =
				 * false; } else { JSONArray results = (JSONArray)
				 * issue_obj.get("results"); System.out.println(results.get(0));
				 * issue_json = (JSONObject) results.get(0);
				 * System.out.println(issue_json.get("id")); issue =
				 * JSONToIssue(issue_json, issue); }
				 */
			} catch (ParseException e) {

				e.printStackTrace();
			}
		}
		try {
			InputStream is = response.getEntity().getContent();
			is.close();
		} catch (IOException exc) {

			exc.printStackTrace();
		}

		System.out.println("Authors: " + author_storage.size());
		System.out.println("Authors: " + author_count);

	}

	@SuppressWarnings({ "unchecked" })
	public static void sync_authors_intersect(long issue_id, String credentials, boolean update_local)
			throws IllegalStateException, IOException {
		boolean status = status_online();
		if (!status) {
			return;
		}
		Issue issue = issue_storage.get(issue_id);
		if (!issue.shouldBeSynced()) {
			return;
		}
		ArrayList<Author> issue_authors = issue.getAuthors();
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

				e2.printStackTrace();
			} catch (IOException e2) {

				e2.printStackTrace();
			}
			boolean author_created = false;
			if (response.getStatusLine().getStatusCode() == 200) {
				author_created = true;
			}
			new JsonFactory();
			InputStream result = response.getEntity().getContent();
			try {
				InputStream is = response.getEntity().getContent();
				is.close();
			} catch (IOException exc) {

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

					e2.printStackTrace();
				} catch (IOException e2) {

					e2.printStackTrace();
				}
				try {
					InputStream is = response.getEntity().getContent();
					is.close();
				} catch (IOException exc) {

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

					e2.printStackTrace();
				} catch (IOException e2) {

					e2.printStackTrace();
				}
				if (response.getStatusLine().getStatusCode() == 400) {
					System.out.println(response.getEntity().getContent());
				}
				try {
					InputStream is = response.getEntity().getContent();
					is.close();
				} catch (IOException exc) {

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

				e2.printStackTrace();
			} catch (IOException e2) {

				e2.printStackTrace();
			}
			new JsonFactory();
			result = response.getEntity().getContent();
			if (response.getStatusLine().getStatusCode() == 200) {
				author_created = true;
			}
			Long setting_pk = (long) -1;
			jsonParser = new JSONParser();
			boolean exists = true;
			JSONObject setting_json = new JSONObject();
			try {
				JSONObject setting = (JSONObject) jsonParser.parse(IOUtils.toString(result));

				System.out.println(setting);
				Long count = (Long) setting.get("count");
				if (count == null || count == 0) {
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

				e.printStackTrace();
			}
			try {
				InputStream is = response.getEntity().getContent();
				is.close();
			} catch (IOException exc) {

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

					e2.printStackTrace();
				} catch (IOException e2) {

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

					e2.printStackTrace();
				} catch (IOException e2) {

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

				exc.printStackTrace();
			}

			settingCheck = new HttpGet(
					String.format("%s/get/setting/orcid/author/%s/?format=json", base_url, author.getId()));
			// settingCheck.setEntity(new StringEntity(obj.toJSONString()));
			settingCheck.addHeader("Authorization", "Basic " + credentials);
			settingCheck.setHeader("Accept", "application/json");
			settingCheck.addHeader("Content-type", "application/json");

			response = null;
			try {
				response = httpClient.execute(settingCheck);
			} catch (ClientProtocolException e2) {

				e2.printStackTrace();
			} catch (IOException e2) {

				e2.printStackTrace();
			}
			new JsonFactory();
			result = response.getEntity().getContent();
			if (response.getStatusLine().getStatusCode() == 200) {
				author_created = true;
			}
			setting_pk = (long) -1;
			jsonParser = new JSONParser();
			exists = true;
			setting_json = new JSONObject();
			try {
				JSONObject setting = (JSONObject) jsonParser.parse(IOUtils.toString(result));
				System.out.println(setting.get("count"));
				System.out.println(setting);
				Long count = (Long) setting.get("count");
				if (count == null || count == 0) {
					exists = false;
				} else {
					JSONArray results = (JSONArray) setting.get("results");
					System.out.println(results.get(0));
					setting_json = (JSONObject) results.get(0);
					System.out.println(setting_json.get("pk"));
					System.out.println(setting_json.get("setting_name"));
					System.out.println(setting_json.get("setting_value"));
					setting_pk = (long) setting_json.get("pk");
					setting_json.put("setting_value", author.getOrcid());
				}
			} catch (ParseException e) {

				e.printStackTrace();
			}
			try {
				InputStream is = response.getEntity().getContent();
				is.close();
			} catch (IOException exc) {

				exc.printStackTrace();
			}
			System.out.println(setting_json.isEmpty());
			System.out.println(exists);
			System.out.println(setting_pk);
			if (setting_json.isEmpty()) {
				setting_json = SettingToJSON("author", author.getId(), "orcid", author.getOrcid(), "string", "en_US");
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

					e2.printStackTrace();
				} catch (IOException e2) {

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

					e2.printStackTrace();
				} catch (IOException e2) {

					e2.printStackTrace();
				}
			}
			try {
				InputStream is = response.getEntity().getContent();
				is.close();
			} catch (IOException exc) {

				exc.printStackTrace();
			}
			settingCheck = new HttpGet(
					String.format("%s/get/setting/department/author/%s/?format=json", base_url, author.getId()));
			// settingCheck.setEntity(new StringEntity(obj.toJSONString()));
			settingCheck.addHeader("Authorization", "Basic " + credentials);
			settingCheck.setHeader("Accept", "application/json");
			settingCheck.addHeader("Content-type", "application/json");

			response = null;
			try {
				response = httpClient.execute(settingCheck);
			} catch (ClientProtocolException e2) {

				e2.printStackTrace();
			} catch (IOException e2) {

				e2.printStackTrace();
			}
			new JsonFactory();
			result = response.getEntity().getContent();
			if (response.getStatusLine().getStatusCode() == 200) {
				author_created = true;
			}
			setting_pk = (long) -1;
			jsonParser = new JSONParser();
			exists = true;
			setting_json = new JSONObject();
			try {
				JSONObject setting = (JSONObject) jsonParser.parse(IOUtils.toString(result));
				System.out.println(setting.get("count"));
				System.out.println(setting);
				Long count = (Long) setting.get("count");
				if (count == null || count == 0) {
					exists = false;
				} else {
					JSONArray results = (JSONArray) setting.get("results");
					System.out.println(results.get(0));
					setting_json = (JSONObject) results.get(0);
					System.out.println(setting_json.get("pk"));
					System.out.println(setting_json.get("setting_name"));
					System.out.println(setting_json.get("setting_value"));
					setting_pk = (long) setting_json.get("pk");
					setting_json.put("setting_value", author.getDepartment());
				}
			} catch (ParseException e) {

				e.printStackTrace();
			}
			try {
				InputStream is = response.getEntity().getContent();
				is.close();
			} catch (IOException exc) {

				exc.printStackTrace();
			}
			System.out.println(setting_json.isEmpty());
			System.out.println(exists);
			System.out.println(setting_pk);
			if (setting_json.isEmpty()) {
				setting_json = SettingToJSON("author", author.getId(), "department", author.getDepartment(), "string",
						"en_US");
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

					e2.printStackTrace();
				} catch (IOException e2) {

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

					e2.printStackTrace();
				} catch (IOException e2) {

					e2.printStackTrace();
				}
			}
			try {
				InputStream is = response.getEntity().getContent();
				is.close();
			} catch (IOException exc) {

				exc.printStackTrace();
			}
			//
			settingCheck = new HttpGet(
					String.format("%s/get/setting/twitter/author/%s/?format=json", base_url, author.getId()));
			// settingCheck.setEntity(new StringEntity(obj.toJSONString()));
			settingCheck.addHeader("Authorization", "Basic " + credentials);
			settingCheck.setHeader("Accept", "application/json");
			settingCheck.addHeader("Content-type", "application/json");

			response = null;
			try {
				response = httpClient.execute(settingCheck);
			} catch (ClientProtocolException e2) {

				e2.printStackTrace();
			} catch (IOException e2) {

				e2.printStackTrace();
			}
			new JsonFactory();
			result = response.getEntity().getContent();
			if (response.getStatusLine().getStatusCode() == 200) {
				author_created = true;
			}
			setting_pk = (long) -1;
			jsonParser = new JSONParser();
			exists = true;
			setting_json = new JSONObject();
			try {
				JSONObject setting = (JSONObject) jsonParser.parse(IOUtils.toString(result));
				System.out.println(setting.get("count"));
				System.out.println(setting);
				Long count = (Long) setting.get("count");
				if (count == null || count == 0) {
					exists = false;
				} else {
					JSONArray results = (JSONArray) setting.get("results");
					System.out.println(results.get(0));
					setting_json = (JSONObject) results.get(0);
					System.out.println(setting_json.get("pk"));
					System.out.println(setting_json.get("setting_name"));
					System.out.println(setting_json.get("setting_value"));
					setting_pk = (long) setting_json.get("pk");
					setting_json.put("setting_value", author.getTwitter());
				}
			} catch (ParseException e) {

				e.printStackTrace();
			}
			try {
				InputStream is = response.getEntity().getContent();
				is.close();
			} catch (IOException exc) {

				exc.printStackTrace();
			}
			System.out.println(setting_json.isEmpty());
			System.out.println(exists);
			System.out.println(setting_pk);
			if (setting_json.isEmpty()) {
				setting_json = SettingToJSON("author", author.getId(), "twitter", author.getTwitter(), "string",
						"en_US");
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

					e2.printStackTrace();
				} catch (IOException e2) {

					e2.printStackTrace();
				}
				try {
					InputStream is = response.getEntity().getContent();
					is.close();
				} catch (IOException exc) {

					exc.printStackTrace();
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

					e2.printStackTrace();
				} catch (IOException e2) {

					e2.printStackTrace();
				}
				try {
					InputStream is = response.getEntity().getContent();
					is.close();
				} catch (IOException exc) {

					exc.printStackTrace();
				}
			}

			settingCheck = new HttpGet(
					String.format("%s/get/setting/affiliation/author/%s/?format=json", base_url, author.getId()));
			// settingCheck.setEntity(new StringEntity(obj.toJSONString()));
			settingCheck.addHeader("Authorization", "Basic " + credentials);
			settingCheck.setHeader("Accept", "application/json");
			settingCheck.addHeader("Content-type", "application/json");

			response = null;
			try {
				response = httpClient.execute(settingCheck);
			} catch (ClientProtocolException e2) {

				e2.printStackTrace();
			} catch (IOException e2) {

				e2.printStackTrace();
			}
			new JsonFactory();
			result = response.getEntity().getContent();
			if (response.getStatusLine().getStatusCode() == 200) {
				author_created = true;
			}
			setting_pk = (long) -1;
			jsonParser = new JSONParser();
			exists = true;
			setting_json = new JSONObject();
			try {
				JSONObject setting = (JSONObject) jsonParser.parse(IOUtils.toString(result));
				System.out.println(setting.get("count"));
				System.out.println(setting);
				Long count = (Long) setting.get("count");
				if (count == null || count == 0) {
					exists = false;
				} else {
					JSONArray results = (JSONArray) setting.get("results");
					System.out.println(results.get(0));
					setting_json = (JSONObject) results.get(0);
					System.out.println(setting_json.get("pk"));
					System.out.println(setting_json.get("setting_name"));
					System.out.println(setting_json.get("setting_value"));
					setting_pk = (long) setting_json.get("pk");
					setting_json.put("setting_value", author.getAffiliation());
				}
			} catch (ParseException e) {

				e.printStackTrace();
			}
			try {
				InputStream is = response.getEntity().getContent();
				is.close();
			} catch (IOException exc) {

				exc.printStackTrace();
			}
			System.out.println(setting_json.isEmpty());
			System.out.println(exists);
			System.out.println(setting_pk);
			if (setting_json.isEmpty()) {
				setting_json = SettingToJSON("author", author.getId(), "affiliation", author.getAffiliation(), "string",
						"en_US");
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

					e2.printStackTrace();
				} catch (IOException e2) {

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

					e2.printStackTrace();
				} catch (IOException e2) {

					e2.printStackTrace();
				}
			}
			try {
				InputStream is = response.getEntity().getContent();
				is.close();
			} catch (IOException exc) {

				exc.printStackTrace();
			}

		}

	}

	public static void sync_authors_intersect_article(Article article, String credentials, boolean update_local)
			throws IllegalStateException, IOException {
		boolean status = status_online();
		if (!status) {
			return;
		}
		if (!article.shouldBeSynced()) {
			return;
		}
		ArrayList<Author> issue_authors = article.getAuthors();
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

				e2.printStackTrace();
			} catch (IOException e2) {

				e2.printStackTrace();
			}
			boolean author_created = false;
			if (response.getStatusLine().getStatusCode() == 200) {
				author_created = true;
			}
			new JsonFactory();
			response.getEntity().getContent();
			try {
				InputStream is = response.getEntity().getContent();
				is.close();
			} catch (IOException exc) {

				exc.printStackTrace();
			}
			new JSONParser();
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

					e2.printStackTrace();
				} catch (IOException e2) {

					e2.printStackTrace();
				}
				try {
					InputStream is = response.getEntity().getContent();
					is.close();
				} catch (IOException exc) {

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

					e2.printStackTrace();
				} catch (IOException e2) {

					e2.printStackTrace();
				}
				if (response.getStatusLine().getStatusCode() == 400) {
					System.out.println(response.getEntity().getContent());
				}
				try {
					InputStream is = response.getEntity().getContent();
					is.close();
				} catch (IOException exc) {

					exc.printStackTrace();
				}
			}

			String json_settings = String.format(
					"{\"affiliation\":\"%s\",\"biography\":\"%s\",\"department\":\"%s\",\"orcid\":\"%s\",\"author\":%d, \"twitter\":\"%s\"}",
					author.getAffiliation() == null ? ""
							: new String(author.getAffiliation().replace("\r\n", "").getBytes("windows-1252"), "UTF-8"),
					author.getBio() == null ? "" : new String(author.getBio().getBytes("windows-1252"), "UTF-8"),
					author.getDepartment() == null ? ""
							: new String(author.getDepartment().getBytes("windows-1252"), "UTF-8"),
					author.getOrcid() == null ? "" : new String(author.getOrcid().getBytes("windows-1252"), "UTF-8"),
					author.getId(), author.getTwitter() == null ? ""
							: new String(author.getTwitter().getBytes("windows-1252"), "UTF-8"));

			System.out.println(json_settings);
			HttpPut httpPut = new HttpPut(String.format("%s/custom/authors/", base_url));

			System.out.println(json_settings);
			httpPut.setEntity(new StringEntity(json_settings));
			httpPut.addHeader("Authorization", "Basic " + credentials);
			httpPut.setHeader("Accept", "application/json");
			httpPut.addHeader("Content-type", "application/json");

			response = null;
			try {
				response = httpClient.execute(httpPut);
			} catch (ClientProtocolException e2) {

				e2.printStackTrace();
			} catch (IOException e2) {

				e2.printStackTrace();
			}
			try {
				InputStream is = response.getEntity().getContent();
				is.close();
			} catch (IOException exc) {

				exc.printStackTrace();
			}

		}

	}

	@SuppressWarnings({ "unchecked" })
	public static void update_sections(long journal_id, String credentials, boolean update_local)
			throws IllegalStateException, IOException {
		boolean status = status_online();
		System.out.println("UPDATING SECTIONS");
		if (!status) {
			return;
		}
		Set<Long> section_keys = section_storage.keySet();
		for (Long key : section_keys) {
			Section section = section_storage.get(key);
			if (section.shouldBeSynced()) {
				if (section.isDeleted()) {
					System.out.println("DELETING");
					delete_section((long) key);
				} else {
					JSONObject obj = SectionToJSON(section);
					HttpGet httpGet = new HttpGet(String.format("%s/sections/%s/?format=json", base_url, key));
					httpGet.addHeader("Authorization", "Basic " + credentials);
					httpGet.setHeader("Accept", "application/json");
					httpGet.addHeader("Content-type", "application/json");

					HttpResponse response = null;
					try {
						response = httpClient.execute(httpGet);
					} catch (ClientProtocolException e2) {
						// System.out.println(String.format("%s/sections/%s/?format=json",
						// base_url, journal_id));
						e2.printStackTrace();
					} catch (IOException e2) {
						// System.out.println(String.format("%s/sections/%s/?format=json",
						// base_url, journal_id));
						e2.printStackTrace();
					}
					new JsonFactory();
					if (response.getStatusLine().getStatusCode() == 200) {
						try {
							InputStream is = response.getEntity().getContent();
							is.close();
						} catch (IOException exc) {

							exc.printStackTrace();
						}
						HttpPut httpPut = new HttpPut(String.format("%s/sections/%s/", base_url, section.getId()));
						httpPut.setEntity(new StringEntity(obj.toJSONString()));
						httpPut.addHeader("Authorization", "Basic " + credentials);
						httpPut.setHeader("Accept", "application/json");
						httpPut.addHeader("Content-type", "application/json");

						response = null;
						try {
							response = httpClient.execute(httpPut);
						} catch (ClientProtocolException e2) {

							e2.printStackTrace();
						} catch (IOException e2) {

							e2.printStackTrace();
						}
						try {
							InputStream is = response.getEntity().getContent();
							is.close();
						} catch (IOException exc) {

							exc.printStackTrace();
						}
						HttpGet settingCheck = new HttpGet(String.format("%s/get/setting/title/section/%s/?format=json",
								base_url, section.getId()));
						// settingCheck.setEntity(new
						// StringEntity(obj.toJSONString()));
						settingCheck.addHeader("Authorization", "Basic " + credentials);
						settingCheck.setHeader("Accept", "application/json");
						settingCheck.addHeader("Content-type", "application/json");

						response = null;
						try {
							response = httpClient.execute(settingCheck);
						} catch (ClientProtocolException e2) {

							e2.printStackTrace();
						} catch (IOException e2) {

							e2.printStackTrace();
						}
						new JsonFactory();
						InputStream result = response.getEntity().getContent();
						Long setting_pk = (long) -1;
						org.json.simple.parser.JSONParser jsonParser = new JSONParser();
						boolean exists = true;
						if (response.getStatusLine().getStatusCode() == 200) {
							exists = true;
						}
						JSONObject setting_json = new JSONObject();
						try {
							JSONObject setting = (JSONObject) jsonParser.parse(IOUtils.toString(result));
							// System.out.println(setting.get("count"));
							// System.out.println(setting);
							Long count = (Long) setting.get("count");
							if (count == null || count == 0) {
								exists = false;
							} else {
								JSONArray results = (JSONArray) setting.get("results");
								System.out.println(results.get(0));
								setting_json = (JSONObject) results.get(0);
								setting_pk = (long) setting_json.get("pk");
								setting_json.put("setting_value", section.getTitle());
							}
						} catch (ParseException e) {

							e.printStackTrace();
						}
						try {
							InputStream is = response.getEntity().getContent();
							is.close();
						} catch (IOException exc) {

							exc.printStackTrace();
						}

						if (setting_json.isEmpty()) {
							setting_json = SettingToJSON("section", section.getId(), "title", section.getTitle(),
									"string", "en_US");
						}
						System.out.println("Title exists: " + exists);
						if (!exists) {
							String value = setting_json.toJSONString();
							byte[] b = value.getBytes("windows-1252");
							for (byte bi : b) {
								System.out.print(bi + " ");
							}
							System.out.println();
							String setting_value = new String(b, "UTF-8");

							System.out.println("SECTION TITLE: " + setting_value);
							HttpPost httpPost = new HttpPost(String.format("%s/section-settings/", base_url));
							httpPost.setEntity(new StringEntity(setting_value));
							httpPost.addHeader("Authorization", "Basic " + credentials);
							httpPost.setHeader("Accept", "application/json");
							httpPost.addHeader("Content-type", "application/json");
							try {
								response = httpClient.execute(httpPost);
							} catch (ClientProtocolException e2) {

								e2.printStackTrace();
							} catch (IOException e2) {

								e2.printStackTrace();
							}
							try {
								InputStream is = response.getEntity().getContent();
								is.close();
							} catch (IOException exc) {

								exc.printStackTrace();
							}
						} else {
							HttpPut httpPost = new HttpPut(
									String.format("%s/section-settings/%s/", base_url, setting_json.get("pk")));
							httpPost.setEntity(new StringEntity(setting_json.toJSONString()));
							httpPost.addHeader("Authorization", "Basic " + credentials);
							httpPost.setHeader("Accept", "application/json");
							httpPost.addHeader("Content-type", "application/json");
							try {
								response = httpClient.execute(httpPost);
							} catch (ClientProtocolException e2) {

								e2.printStackTrace();
							} catch (IOException e2) {

								e2.printStackTrace();
							}
						}
						try {
							InputStream is = response.getEntity().getContent();
							is.close();
						} catch (IOException exc) {

							exc.printStackTrace();
						}
					} else {
						try {
							InputStream is = response.getEntity().getContent();
							is.close();
						} catch (IOException exc) {

							exc.printStackTrace();
						}
						JSONObject setting_json = SettingToJSON("section", section.getId(), "title", section.getTitle(),
								"string", "en_US");

						System.out.println("SECTION: " + obj.toJSONString());
						HttpPost httpPost = new HttpPost(String.format("%s/sections/", base_url));
						httpPost.setEntity(new StringEntity(obj.toJSONString()));
						httpPost.addHeader("Authorization", "Basic " + credentials);
						httpPost.setHeader("Accept", "application/json");
						httpPost.addHeader("Content-type", "application/json");

						response = null;
						try {
							response = httpClient.execute(httpPost);
						} catch (ClientProtocolException e2) {

							e2.printStackTrace();
						} catch (IOException e2) {

							e2.printStackTrace();
						}
						try {
							InputStream is = response.getEntity().getContent();
							is.close();
						} catch (IOException exc) {

							exc.printStackTrace();
						}
						String value = setting_json.toJSONString();
						byte[] b = value.getBytes("windows-1252");
						for (byte bi : b) {
							System.out.print(bi + " ");
						}
						System.out.println();
						String setting_value = new String(b, "UTF-8");

						System.out.println(setting_value);
						httpPost = new HttpPost(String.format("%s/section-settings/", base_url));
						httpPost.setEntity(new StringEntity(setting_value));
						httpPost.addHeader("Authorization", "Basic " + credentials);
						httpPost.setHeader("Accept", "application/json");
						httpPost.addHeader("Content-type", "application/json");
						try {
							response = httpClient.execute(httpPost);
						} catch (ClientProtocolException e2) {

							e2.printStackTrace();
						} catch (IOException e2) {

							e2.printStackTrace();
						}
						try {
							InputStream is = response.getEntity().getContent();
							is.close();
						} catch (IOException exc) {

							exc.printStackTrace();
						}

					}
				}
			}
		}

	}

	public static void get_sections(long journal_id, String credentials, boolean update_local)
			throws IllegalStateException, IOException {
		boolean status = status_online();
		System.out.println("GETTING SECTIONS");
		if (!status) {
			return;
		}

		int author_count = 0;
		HttpGet httpGet = new HttpGet(String.format("%s/get/sections/%s/?format=json", base_url, journal_id));
		httpGet.addHeader("Authorization", "Basic " + credentials);
		httpGet.setHeader("Accept", "application/json");
		httpGet.addHeader("Content-type", "application/json");

		HttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
		} catch (ClientProtocolException e2) {
			System.out.println(String.format("%s/get/sections/%s/?format=json", base_url, journal_id));
			e2.printStackTrace();
		} catch (IOException e2) {
			System.out.println(String.format("%s/get/sections/%s/?format=json", base_url, journal_id));
			e2.printStackTrace();
		}
		new JsonFactory();
		if (response.getStatusLine().getStatusCode() == 200) {
			InputStream result = response.getEntity().getContent();
			org.json.simple.parser.JSONParser jsonParser = new JSONParser();
			new JSONObject();
			try {
				JSONObject journal_json = (JSONObject) jsonParser.parse(IOUtils.toString(result));

				JSONArray results = (JSONArray) journal_json.get("sections");
				System.out.println(results);
				try {
					InputStream is = response.getEntity().getContent();
					is.close();
				} catch (IOException exc) {

					exc.printStackTrace();
				}
				for (int i = 0; i < results.size(); i++) {
					JSONObject section = (JSONObject) results.get(i);
					Section new_section = JSONToSection(section);
					if (((long) section_db_id) < ((long) new_section.getId())) {
						section_db_id = new_section.getId();
					}
					System.out.println(new_section);
					section_storage.put((long) new_section.getId(), new_section);
				}

				/*
				 * System.out.println(issue_obj.get("count"));
				 * System.out.println(issue_obj); String detail = (String)
				 * issue_obj.get("detail"); if (detail == null) { exists =
				 * false; } else { JSONArray results = (JSONArray)
				 * issue_obj.get("results"); System.out.println(results.get(0));
				 * issue_json = (JSONObject) results.get(0);
				 * System.out.println(issue_json.get("id")); issue =
				 * JSONToIssue(issue_json, issue); }
				 */
			} catch (ParseException e) {

				e.printStackTrace();
			}
		}
		try {
			InputStream is = response.getEntity().getContent();
			is.close();
		} catch (IOException exc) {

			exc.printStackTrace();
		}

		System.out.println("Authors: " + author_storage.size());
		System.out.println("Authors: " + author_count);

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

			e2.printStackTrace();
		} catch (IOException e2) {

			e2.printStackTrace();
		}
		new JsonFactory();
		InputStream result = response.getEntity().getContent();
		org.json.simple.parser.JSONParser jsonParser = new JSONParser();
		JSONObject issue_json = new JSONObject();
		try {
			JSONObject issue_obj = (JSONObject) jsonParser.parse(IOUtils.toString(result));
			System.out.println(issue_obj.get("count"));
			System.out.println(issue_obj);
			String detail = (String) issue_obj.get("detail");
			if (detail == null) {
			} else {
				JSONArray results = (JSONArray) issue_obj.get("results");
				System.out.println(results.get(0));
				issue_json = (JSONObject) results.get(0);
				System.out.println(issue_json.get("id"));
				issue = JSONToIssue(issue_json, issue);
			}
		} catch (ParseException e) {

			e.printStackTrace();
		}
		try {
			InputStream is = response.getEntity().getContent();
			is.close();
		} catch (IOException exc) {

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

			e2.printStackTrace();
		} catch (IOException e2) {

			e2.printStackTrace();
		}
		new JsonFactory();
		result = response.getEntity().getContent();
		jsonParser = new JSONParser();
		JSONObject setting_json = new JSONObject();
		try {
			JSONObject setting = (JSONObject) jsonParser.parse(IOUtils.toString(result));
			System.out.println(setting.get("count"));
			System.out.println(setting);
			Long count = (Long) setting.get("count");
			if (count == null || count == 0) {
			} else {
				JSONArray results = (JSONArray) setting.get("results");
				System.out.println(results.get(0));
				setting_json = (JSONObject) results.get(0);
				issue.setTitle((String) setting_json.get("setting_value"));
				// issue.setShow_title((String)
				// setting_json.get("setting_value"));
			}
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return issue;
	}

	@SuppressWarnings("unchecked")
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

				e.printStackTrace();
			}
		}
		return issue;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject IssueToJSON(Issue issue) {
		JSONObject obj = new JSONObject();
		obj.put("id", issue.getId());
		obj.put("journal", String.format("%s/journals/%s/", base_url, issue.getJournal().getId()));
		obj.put("volume", issue.getVolume());
		obj.put("number", issue.getNumber());
		obj.put("year", issue.getYear());

		SimpleDateFormat upload_sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formated_date_published = issue.getDate_published() == null ? null
				: upload_sdf.format(issue.getDate_published());
		obj.put("date_published", formated_date_published + "T00:00:00Z");
		obj.put("published", issue.getPublished());
		obj.put("show_volume", issue.getShow_volume());
		obj.put("show_number", issue.getShow_number());
		obj.put("show_year", issue.getShow_year());
		obj.put("show_title", issue.getShow_title());
		obj.put("current", issue.getCurrent());
		obj.put("published", issue.getPublished());
		obj.put("access_status", issue.getAccess_status());
		return obj;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject ArticleToPublishedJSON(Article article) {
		JSONObject obj = new JSONObject();
		obj.put("id", article.getPublished_pk());
		obj.put("seq", 1.0);
		obj.put("article", String.format("%s/articles/%s/", base_url, article.getId()));
		SimpleDateFormat upload_sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formated_date = upload_sdf
				.format(article.getDate_published() == null ? new Date() : article.getDate_published());
		obj.put("date_published", formated_date + "T00:00:00Z");
		obj.put("issue", String.format("%s/issues/%s/", base_url, article.getIssue_fk().getId()));
		obj.put("access_status", article.getIssue_fk().getAccess_status());
		return obj;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject ArticleToUnPublishedJSON(Article article) {
		JSONObject obj = new JSONObject();
		obj.put("seq", 1.0);
		obj.put("article", String.format("%s/articles/%s/", base_url, article.getId()));
		obj.put("issue", String.format("%s/issues/%s/", base_url, article.getIssue_fk().getId()));
		obj.put("access_status", article.getIssue_fk().getAccess_status());
		return obj;
	}

	@SuppressWarnings("unchecked")
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

	public static Article JSONToArticle_single_request(JSONObject obj, Issue issue) {
		String date_p = (String) obj.get("date_submitted");
		Long user_id = (Long) obj.get("user");
		System.out.println(user_id);
		String date_published = ((String) obj.get("date_published"));

		Article new_article = new Article((long) obj.get("id"), (long) obj.get("section_id"), (String) obj.get("pages"),
				user_id, (String) obj.get("locale"), (String) obj.get("language"), (int) (long) obj.get("status"),
				(int) (long) obj.get("submission_progress"), (int) (long) obj.get("current_round"),
				(int) (long) obj.get("fast_tracked"), (int) (long) obj.get("hide_author"),
				(int) (long) obj.get("comments_status"), issue);
		if (date_published != null && date_p.compareTo("") != 0 && date_published.isEmpty() != true) {
			new_article.setPublished_pk((long) obj.get("published_pk"));
			try {
				new_article.setDate_published((Date) sdf.parse(date_published.substring(0, 10).replace('-', '/')));
			} catch (java.text.ParseException e) {

				e.printStackTrace();
			}
		} else {
			new_article.setUnpublished_pk((long) obj.get("unpublished_pk"));
		}
		if (date_p != null && date_p.compareTo("") != 0 && date_p.length() > 10) {

			try {
				new_article.setDate_submitted((Date) sdf.parse(date_p.substring(0, 10).replace('-', '/')));
			} catch (java.text.ParseException e) {

				e.printStackTrace();
			}
		}
		return new_article;
	}

	public static Article JSONToArticle_single_request(JSONObject obj) {
		String date_p = (String) obj.get("date_submitted");
		Long user_id = (Long) obj.get("user");
		System.out.println(user_id);
		String date_published = ((String) obj.get("date_published"));

		Article new_article = new Article((long) obj.get("id"), (long) obj.get("section_id"), (String) obj.get("pages"),
				user_id, (String) obj.get("locale"), (String) obj.get("language"), (int) (long) obj.get("status"),
				(int) (long) obj.get("submission_progress"), (int) (long) obj.get("current_round"),
				(int) (long) obj.get("fast_tracked"), (int) (long) obj.get("hide_author"),
				(int) (long) obj.get("comments_status"));
		if (date_published != null && date_p.compareTo("") != 0 && date_published.isEmpty() != true) {
			new_article.setPublished_pk((long) obj.get("published_pk"));
			try {
				new_article.setDate_published((Date) sdf.parse(date_published.substring(0, 10).replace('-', '/')));
			} catch (java.text.ParseException e) {

				e.printStackTrace();
			}
		} else {
			new_article.setUnpublished_pk((long) obj.get("unpublished_pk"));
		}
		if (date_p != null && date_p.compareTo("") != 0 && date_p.length() > 10) {

			try {
				new_article.setDate_submitted((Date) sdf.parse(date_p.substring(0, 10).replace('-', '/')));
			} catch (java.text.ParseException e) {

				e.printStackTrace();
			}
		}
		return new_article;
	}

	public static Author JSONToAuthor(JSONObject obj, Author author) {

		obj.get("id");
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

	public static Section JSONToSection(JSONObject obj) {

		long id = (long) obj.get("id");
		obj.get("seq");
		String title = (String) obj.get("title");
		int editor_restricted = (int) (long) obj.get("editor_restricted");
		int meta_indexed = (int) (long) obj.get("meta_indexed");
		int meta_reviewed = (int) (long) obj.get("meta_reviewed");
		int abstracts_not_required = (int) (long) obj.get("abstracts_not_required");
		int hide_title = (int) (long) obj.get("hide_title");
		int hide_author = (int) (long) obj.get("hide_author");
		int hide_about = (int) (long) obj.get("hide_about");
		int disable_comments = (int) (long) obj.get("disable_comments");
		long abstract_word_count = -1;
		if (obj.get("abstract_word_count") instanceof Integer) {
			Integer words = (Integer) (obj.get("abstract_word_count") == null ? 0 : obj.get("abstract_word_count"));
			abstract_word_count = new Long(words);
		} else if (obj.get("abstract_word_count") instanceof Long) {
			abstract_word_count = (long) (obj.get("abstract_word_count") == null ? 0 : obj.get("abstract_word_count"));
		}

		Section new_section = new Section(id, title, editor_restricted, meta_indexed, meta_reviewed,
				abstracts_not_required, hide_title, hide_author, hide_about, disable_comments, abstract_word_count);
		return new_section;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject SectionToJSON(Section section) {
		JSONObject obj = new JSONObject();
		obj.put("id", section.getId());
		obj.put("journal", String.format("%s/journals/%s/", base_url,
				app_settings.get("journal_id") == null ? 1 : Long.parseLong(app_settings.get("journal_id"))));
		obj.put("seq", section.getSeq());
		obj.put("editor_restricted", section.getEditor_restricted());
		obj.put("meta_indexed", section.getMeta_indexed());
		obj.put("meta_reviewed", section.getMeta_reviewed());
		obj.put("abstracts_not_required", section.getAbstracts_not_required());
		obj.put("hide_title", section.getHide_title());
		obj.put("hide_about", section.getHide_about());
		obj.put("hide_author", section.getHide_author());
		obj.put("disable_comments", section.getDisable_comments());
		obj.put("abstract_word_count", section.getAbstract_word_count());
		return obj;
	}

	public static Author JSONToAuthor_single_request(JSONObject obj, Author author) {

		obj.get("id");
		long article_id = (Long) obj.get("article");

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

	@SuppressWarnings("unchecked")
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

		return obj;
	}

	/**
	 * @throws IOException
	 * @throws java.text.ParseException
	 * @wbp.parser.entryPoint
	 */
	public Main() throws IOException, java.text.ParseException {
		NimbusLookAndFeel nimbus_theme = new NimbusLookAndFeel();
		try {
			UIManager.setLookAndFeel(nimbus_theme);
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		nimbus_theme.getDefaults().put("defaultFont", new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 12));
		/*
		 * try { for (LookAndFeelInfo info :
		 * UIManager.getInstalledLookAndFeels()) { if
		 * ("Nimbus".equals(info.getName())) {
		 * UIManager.setLookAndFeel(info.getClassName()); break; } } } catch
		 * (UnsupportedLookAndFeelException e) { // handle exception } catch
		 * (ClassNotFoundException e) { // handle exception } catch
		 * (InstantiationException e) { // handle exception } catch
		 * (IllegalAccessException e) { // handle exception }
		 */
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

		ThreadSafeClientConnManager cm = new ThreadSafeClientConnManager(schReg);
		cm.setMaxTotal(50);
		cm.setDefaultMaxPerRoute(50);

		httpClient = new DefaultHttpClient(cm, params);
		httpClient.getParams().setBooleanParameter("http.protocol.expect-continue", true);

		httpClient.getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),
				new UsernamePasswordCredentials("ioannis", "root"));

		// HttpHost targetHost = new HttpHost("webserviceIP", webservicePort,
		// "https");
		// cm.setMaxForRoute(new HttpRoute(targetHost, null, true),
		// maxConnections);

		System.out.println("Loading dashboard");
		dashboard();
	}

	public void add_author() {

		api = new JFrame();
		api.setResizable(false);
		api.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		api.getContentPane().setBackground(new Color(170, 170, 170));
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
			int last_index = -1;
			last_index = source.lastIndexOf("/");
			if (last_index == -1) {
				last_index = source.lastIndexOf("\\");
			}
			String filename = source.substring(last_index + 1);
			System.out.print(String.format("%s/files/%d/%s", directory, art_id, filename));
			File dir = new File(String.format("%s/files/%d/", directory, art_id));
			dir.mkdirs();
			file_id++;
			ConcurrentHashMap<Long, ArticleFile> article_files = null;
			if (!file_storage.containsKey((long) art_id)) {
				article_files = new ConcurrentHashMap<Long, ArticleFile>();
			} else {
				article_files = file_storage.get((long) art_id);
			}
			article_files.put(file_id,
					new ArticleFile(file_id, art_id, String.format("%s/files/%d/%s", directory, art_id, filename)));

			file_storage.put((long) art_id, article_files);
			Files.copy(Paths.get(source), Paths.get(String.format("%s/files/%d/%s", directory, art_id, filename)),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {

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

			e2.printStackTrace();
		} catch (IOException e2) {

			e2.printStackTrace();
		}
		new JsonFactory();
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

			e2.printStackTrace();
		} catch (IOException e2) {

			e2.printStackTrace();
		}
		new JsonFactory();
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

			e2.printStackTrace();
		} catch (IOException e2) {

			e2.printStackTrace();
		}
		new JsonFactory();
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

			e2.printStackTrace();
		} catch (IOException e2) {

			e2.printStackTrace();
		}
		new JsonFactory();
		System.out.println(response.getStatusLine().getStatusCode());
		if (response.getStatusLine().getStatusCode() != 204) {
			InputStream result = response.getEntity().getContent();
			System.out.println(IOUtils.toString(result));
		}
	}

	public static void delete_article(long article_id) throws IOException {
		boolean status = status_online();
		System.out.println("DELETING ARTICLE");
		if (!status) {
			return;
		}
		HttpDelete deleteArticle = new HttpDelete(String.format("%s/delete/article/%s/", base_url, article_id));

		deleteArticle.addHeader("Authorization", "Basic " + encoding);

		// : attachment; filename=upload.jpg.

		HttpResponse response = null;
		try {
			response = httpClient.execute(deleteArticle);
		} catch (ClientProtocolException e2) {

			e2.printStackTrace();
		} catch (IOException e2) {

			e2.printStackTrace();
		}
		new JsonFactory();
		System.out.println(response.getStatusLine().getStatusCode());
		if (response.getStatusLine().getStatusCode() != 204) {
			InputStream result = response.getEntity().getContent();
			System.out.println(IOUtils.toString(result));
		}
		if (response.getStatusLine().getStatusCode() == 204) {
			Article current_article = article_storage.get((long) article_id);
			long issue_id = current_article.getIssue_fk().getId();
			Issue current_issue = issue_storage.get((long) issue_id);
			current_issue.remove_article((long) article_id);
			issue_storage.put((long) issue_id, current_issue);
			article_storage.remove((long) article_id);

		}
	}

	public static void delete_issue(long issue_id) throws IOException {
		boolean status = status_online();
		System.out.println("Deleting Issue: " + issue_id);
		if (!status) {
			return;
		}
		HttpDelete deleteArticle = new HttpDelete(String.format("%s/delete/issue/%s/", base_url, issue_id));

		deleteArticle.addHeader("Authorization", "Basic " + encoding);

		// : attachment; filename=upload.jpg.

		HttpResponse response = null;
		try {
			response = httpClient.execute(deleteArticle);
		} catch (ClientProtocolException e2) {

			e2.printStackTrace();
		} catch (IOException e2) {

			e2.printStackTrace();
		}
		new JsonFactory();
		System.out.println(response.getStatusLine().getStatusCode());
		if (response.getStatusLine().getStatusCode() != 204) {
			InputStream result = response.getEntity().getContent();
			System.out.println(IOUtils.toString(result));
		}
		if (response.getStatusLine().getStatusCode() == 204) {
			Issue current_issue = issue_storage.get((long) issue_id);
			ConcurrentHashMap<Long, Article> articles = current_issue.getArticles_list();
			Set<Long> art_keys = articles.keySet();
			for (long key : art_keys) {
				if (article_storage.containsKey((long) key)) {
					article_storage.remove((long) key);
				}
			}
			issue_storage.remove((long) issue_id);
		}
	}

	public static void delete_section(long section_id) throws IOException {
		boolean status = status_online();
		System.out.println("Deleting Section: " + section_id);
		if (!status) {
			return;
		}
		HttpDelete deleteArticle = new HttpDelete(String.format("%s/delete/section/%s/", base_url, section_id));

		deleteArticle.addHeader("Authorization", "Basic " + encoding);

		// : attachment; filename=upload.jpg.

		HttpResponse response = null;
		try {
			response = httpClient.execute(deleteArticle);
		} catch (ClientProtocolException e2) {

			e2.printStackTrace();
		} catch (IOException e2) {

			e2.printStackTrace();
		}
		new JsonFactory();
		System.out.println(response.getStatusLine().getStatusCode());
		if (response.getStatusLine().getStatusCode() != 204) {
			InputStream result = response.getEntity().getContent();
			System.out.println(IOUtils.toString(result));
		}
		if (response.getStatusLine().getStatusCode() == 204) {
			section_storage.remove((long) section_id);
		}
	}

	public static boolean file_download(long article_id, long file_id, boolean missing)
			throws IllegalStateException, IOException {
		boolean status = status_online();
		boolean exists_remotely = false;
		if (!status) {
			return false;
		}
		String filename = "";
		HttpGet fileDetails = new HttpGet(String.format("%s/files/%s/", base_url, file_id));
		File dir = new File(String.format("%s/files/%d/", directory, article_id));
		dir.mkdirs();
		fileDetails.addHeader("Authorization", "Basic " + encoding);
		HttpResponse response = null;
		try {
			response = httpClient.execute(fileDetails);
		} catch (ClientProtocolException e2) {

			e2.printStackTrace();
		} catch (IOException e2) {

			e2.printStackTrace();
		}
		InputStream result = response.getEntity().getContent();
		new JsonFactory();
		org.json.simple.parser.JSONParser jsonParser = new JSONParser();
		new JSONObject();
		JSONObject issue_obj;
		try {
			issue_obj = (JSONObject) jsonParser.parse(IOUtils.toString(result));

			filename = (String) issue_obj.get("original_filename");
			System.out.println(filename);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		HttpGet fileDownload = new HttpGet(String.format("%s/download/file/%s/%s/", base_url, article_id, file_id));

		fileDownload.addHeader("Authorization", "Basic " + encoding);
		response = null;
		try {
			response = httpClient.execute(fileDownload);
		} catch (ClientProtocolException e2) {

			e2.printStackTrace();
		} catch (IOException e2) {

			e2.printStackTrace();
		}
		if (response.getStatusLine().getStatusCode() == 404) {
			if (missing) {
				JOptionPane.showMessageDialog(null, "File does not exist remotely.");
			}
		} else {
			exists_remotely = true;
		}

		if (exists_remotely) {
			InputStream is = response.getEntity().getContent();
			String filePath = String.format("%s/files/%s/%s/", directory, article_id, filename);
			FileOutputStream fos = new FileOutputStream(new File(filePath));
			int inByte;
			while ((inByte = is.read()) != -1)
				fos.write(inByte);
			is.close();
			fos.close();
			ConcurrentHashMap<Long, ArticleFile> article_files = null;
			if (!file_storage.containsKey((long) article_id)) {
				article_files = new ConcurrentHashMap<Long, ArticleFile>();
			} else {
				article_files = file_storage.get((long) article_id);
				if (article_files == null) {
					article_files = new ConcurrentHashMap<Long, ArticleFile>();
				}
			}
			article_files.put(file_id, new ArticleFile(file_id, article_id,
					String.format("%s/files/%d/%s", directory, article_id, filename)));

			file_storage.put((long) article_id, article_files);
			System.out.println("Downloaded: " + article_files.size());
		}

		// System.out.println(file_storage.get((long)125).get((long)5));
		return exists_remotely;
	}

	public static void main(String[] args) throws ParseException, java.text.ParseException, IOException {
		directory = System.getProperty("user.dir");
		database_setup();
		populate_variables();
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		new Main();
		// update_unpublished_articles_local_single_request(encoding);
		// update_sections((long) 1, encoding,false);
		// file_upload_intersect((long)125,"/home/ioannis/code/toru-app/java_ojs/miglayout-src.zip",25);
		// file_download(125,16);
		// System.out.println(file_storage.get((long)125).get((long)5));

	}
}