package models;

import java.util.Date;
import java.util.HashMap;

public class Issue {
	private int id;
	private String title; 
	private int volume;  
	private int number;
	private int year;
	private String show_title;
	private int show_volume;
	private int show_number;
	private int show_year;
	private HashMap<Integer, Article> articles_list;
	private Date date_published;
	
	public Issue(int id, String title, int volume, int number, int year, String show_title, int show_volume, int show_number,
			int show_year, Date date_published) {
		this.title = title;
		this.id = id;
		this.volume = volume;
		this.number = number;
		this.year = year;
		this.show_title = show_title;
		this.show_volume = show_volume;
		this.show_number = show_number;
		this.show_year = show_year;
		this.date_published = date_published;
		this.articles_list = new  HashMap<Integer, Article>();
	}
	public Issue(int id, String title, int volume, int number, int year, Date date_published) {
		this.title = title;
		this.id = id;
		this.volume = volume;
		this.number = number;
		this.year = year;
		this.show_title = title;
		this.show_volume = volume;
		this.show_number = number;
		this.show_year = year;
		this.date_published = date_published;
		this.articles_list = new  HashMap<Integer, Article>();
	}
	public Issue(int id, String title, int volume, int number, int year, String show_title, int show_volume, int show_number,
			int show_year, Date date_published, int current_article_id) {
		this.title = title;
		this.id = id;
		this.volume = volume;
		this.number = number;
		this.year = year;
		this.show_title = show_title;
		this.show_volume = show_volume;
		this.show_number = show_number;
		this.show_year = show_year;
		this.date_published = date_published;
		this.articles_list = new  HashMap<Integer, Article>();
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

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getShow_title() {
		return show_title;
	}

	public void setShow_title(String show_title) {
		this.show_title = show_title;
	}


	public int getShow_volume() {
		return show_volume;
	}

	public void setShow_volume(int show_volume) {
		this.show_volume = show_volume;
	}

	public int getShow_number() {
		return show_number;
	}

	public void setShow_number(int show_number) {
		this.show_number = show_number;
	}

	public int getShow_year() {
		return show_year;
	}

	public void setShow_year(int show_year) {
		this.show_year = show_year;
	}

	public Date getDate_published() {
		return date_published;
	}

	public void setDate_published(Date date_published) {
		this.date_published = date_published;
	}
	public HashMap<Integer, Article> getArticles_list() {
		return articles_list;
	}
	public void setArticles_list(HashMap<Integer, Article> articles_list) {
		this.articles_list = articles_list;
	}
	public void add_article(int article_id,Article a){
		articles_list.put(article_id,a);
	}
	public void update_article(int article_id,Article a){
		articles_list.put(article_id,a);
	}
	public void add_author(int article_id,Author a){
		Article updated=articles_list.get(article_id);
		updated.add_author(a);		
		articles_list.put(article_id,updated);
	}
	public void remove_article(int id){
		articles_list.remove(id);
	}
	
	

}
