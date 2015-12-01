package models;

import java.util.ArrayList;
import java.util.Date;

public class Article {
	private int id;
	private String title;
	private int section_id;
	private int pages;
	private String abstract_text;
	private Date date_accepted;
	private Date date_published;
	private Issue issue_fk;
	private ArrayList<Author> authors;

	public Article(int id, String title, int section_id, int pages, String abstract_text, Date date_accepted, Date date_published,
			Issue issue_fk) {

		this.title = title;
		this.id = id;
		this.section_id = section_id;
		this.pages = pages;
		this.abstract_text = abstract_text;
		this.date_accepted = date_accepted;
		this.date_published = date_published;
		this.issue_fk = issue_fk;
		this.authors = new ArrayList<Author>();
	}
	public Article(int id, String title, int section_id, int pages, String abstract_text, Date date_accepted, Date date_published) {

		this.title = title;
		this.id = id;
		this.section_id = section_id;
		this.pages = pages;
		this.abstract_text = abstract_text;
		this.date_accepted = date_accepted;
		this.date_published = date_published;
		this.issue_fk = null;
		this.authors = new ArrayList<Author>();
	}

	public int getId() {
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

	public int getSection_id() {
		return section_id;
	}

	public void setSection_id(int section_id) {
		this.section_id = section_id;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
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
	
	

}
