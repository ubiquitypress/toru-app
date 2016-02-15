package models;

import java.util.ArrayList;
import java.util.Date;

public class Article {
	private long id;
	private String title;
	private boolean sync=false;
	private String doi;
	private long section_id;
	private String pages;
	private String abstract_text;
	private Journal journal;
	private long user_id;
	private String locale;
	private String language;
	private int status;
	private int submission_progress;
	private int current_round;
	private int fast_tracked;
	private int hide_author;
	private long published_pk;
	private int comments_status;
	private Date date_submitted;
	private Date date_accepted;
	private Date date_published = null;
	private Issue issue_fk;
	private ArrayList<Author> authors;

	public Article(long id, String title, long section_id, String pages, String abstract_text, Date date_accepted,
			Issue issue_fk, Date date_submitted, Journal journal) {

		this.title = title;
		this.id = id;
		this.section_id = section_id;
		this.pages = pages;
		this.abstract_text = abstract_text;
		this.date_accepted = date_accepted;
		this.issue_fk = issue_fk;
		this.authors = new ArrayList<Author>();
		this.journal = journal;
		this.user_id = 1;
		this.locale = "en_US";
		this.language = "eng";
		this.status = 1;
		this.submission_progress = 0;
		this.current_round = 1;
		this.fast_tracked = 0;
		this.hide_author = 0;
		this.comments_status = 0;
		this.date_submitted = date_submitted;
		this.published_pk=-1;
	}
	public Article(long id, String title, long section_id, String pages, String abstract_text, Date date_accepted, Date date_submitted, Journal journal) {

		this.title = title;
		this.id = id;
		this.section_id = section_id;
		this.pages = pages;
		this.abstract_text = abstract_text;
		this.date_accepted = date_accepted;
		this.issue_fk = null;
		this.authors = new ArrayList<Author>();
		this.journal = journal;
		this.user_id = 1;
		this.locale = "en_US";
		this.language = "eng";
		this.status = 1;
		this.submission_progress = 0;
		this.current_round = 1;
		this.fast_tracked = 0;
		this.hide_author = 0;
		this.comments_status = 0;
		this.date_submitted = date_submitted;
		this.published_pk=-1;
	}

	public long getId() {
		return id;
	}

	public void setAuthors(ArrayList<Author> authors) {
		this.authors = authors;
	}

	public ArrayList<Author> getAuthors() {
		return authors;
	}

	public void add_author(Author a) {
		if (!authors.contains(a)) {
			this.authors.add(a);
		}
	}

	public void reset_authors() {
		this.authors = new ArrayList<Author>();
	}

	public void remove_author(Author a) {
		this.authors.remove(a);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getSection_id() {
		return section_id;
	}

	public void setSection_id(long section_id) {
		this.section_id = section_id;
	}

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public String getAbstract_text() {
		return abstract_text;
	}

	public void setAbstract_text(String abstract_text) {
		this.abstract_text = abstract_text;
	}

	public Date getDate_published() {
		return date_published;
	}

	public void setDate_published(Date date_published) {
		this.date_published = date_published;
	}

	public Issue getIssue_fk() {
		return issue_fk;
	}

	public void setIssue_fk(Issue issue_fk) {
		this.issue_fk = issue_fk;
	}
	public Date getDate_accepted() {
		return date_accepted;
	}
	public void setDate_accepted(Date date_accepted) {
		this.date_accepted = date_accepted;
	}
	public Journal getJournal() {
		return journal;
	}
	public void setJournal(Journal journal) {
		this.journal = journal;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getSubmission_progress() {
		return submission_progress;
	}
	public void setSubmission_progress(int submission_progress) {
		this.submission_progress = submission_progress;
	}
	public int getCurrent_round() {
		return current_round;
	}
	public void setCurrent_round(int current_round) {
		this.current_round = current_round;
	}
	public int getFast_tracked() {
		return fast_tracked;
	}
	public void setFast_tracked(int fast_tracked) {
		this.fast_tracked = fast_tracked;
	}
	public int getHide_author() {
		return hide_author;
	}
	public void setHide_author(int hide_author) {
		this.hide_author = hide_author;
	}
	public int getComments_status() {
		return comments_status;
	}
	public void setComments_status(int comments_status) {
		this.comments_status = comments_status;
	}
	public Date getDate_submitted() {
		return date_submitted;
	}
	public void setDate_submitted(Date date_submitted) {
		this.date_submitted = date_submitted;
	}
	public Article(long id, String title, long section_id, String pages, String abstract_text, Journal journal,
			long user_id, String locale, String language, int status, int submission_progress, int current_round,
			int fast_tracked, int hide_author, int comments_status, Date date_submitted, Date date_accepted,
			Issue issue_fk, ArrayList<Author> authors) {
		super();
		this.id = id;
		this.title = title;
		this.section_id = section_id;
		this.pages = pages;
		this.abstract_text = abstract_text;
		this.journal = journal;
		this.user_id = user_id;
		this.locale = locale;
		this.language = language;
		this.status = status;
		this.submission_progress = submission_progress;
		this.current_round = current_round;
		this.fast_tracked = fast_tracked;
		this.hide_author = hide_author;
		this.comments_status = comments_status;
		this.date_submitted = date_submitted;
		this.date_accepted = date_accepted;
		this.issue_fk = issue_fk;
		this.authors = authors;
		this.published_pk=-1;
	}
	public Article(long id, long section_id, String pages, long user_id, String locale, String language,
			int status, int submission_progress, int current_round, int fast_tracked, int hide_author,
			int comments_status, Issue issue_fk) {
		super();
		this.id = id;
		this.section_id = section_id;
		this.pages = pages;
		this.user_id = user_id;
		this.locale = locale;
		this.language = language;
		this.status = status;
		this.submission_progress = submission_progress;
		this.current_round = current_round;
		this.fast_tracked = fast_tracked;
		this.hide_author = hide_author;
		this.comments_status = comments_status;
		this.issue_fk = issue_fk;
		this.authors = new ArrayList<Author>();
		this.published_pk=-1;
	}
	@Override
	public String toString() {
		return title;
	}
	public String getDoi() {
		return doi;
	}
	public void setDoi(String doi) {
		this.doi = doi;
	}
	public Article(long id, String title, String doi, long section_id, String pages, String abstract_text,
			Journal journal, long user_id, String locale, String language, int status, int submission_progress,
			int current_round, int fast_tracked, int hide_author, int comments_status, Date date_submitted,
			Date date_accepted, Issue issue_fk, ArrayList<Author> authors) {
		super();
		this.id = id;
		this.title = title;
		this.doi = doi;
		this.section_id = section_id;
		this.pages = pages;
		this.abstract_text = abstract_text;
		this.journal = journal;
		this.user_id = user_id;
		this.locale = locale;
		this.language = language;
		this.status = status;
		this.submission_progress = submission_progress;
		this.current_round = current_round;
		this.fast_tracked = fast_tracked;
		this.hide_author = hide_author;
		this.comments_status = comments_status;
		this.date_submitted = date_submitted;
		this.date_accepted = date_accepted;
		this.issue_fk = issue_fk;
		this.authors = authors;
		this.published_pk=-1;
	}
	public long getPublished_pk() {
		return published_pk;
	}
	public void setPublished_pk(long published_pk) {
		this.published_pk = published_pk;
	}
	public boolean shouldBeSynced() {
		return sync;
	}
	public void setSync(boolean updated) {
		this.sync = updated;
	}
	
	

}
