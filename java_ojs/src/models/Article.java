package models;

import java.util.Date;

public class Article {
	private int id;
	private String title; 
	private int section_id;
	private int pages; 
	private String abstract_text;
	private Date date_published; 
	private Issue issue_fk;
	
	public Article(int id,String title, int section_id, int pages, String abstract_text, Date date_published, Issue issue_fk) {
	
		this.title = title;
		this.id = id;
		this.section_id = section_id;
		this.pages = pages;
		this.abstract_text = abstract_text;
		this.date_published = date_published;
		this.issue_fk = issue_fk;
	}
	public int getId() {
		return id;
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
	

}
