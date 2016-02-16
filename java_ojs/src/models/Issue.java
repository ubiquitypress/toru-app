package models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Set;

public class Issue {
	private long id;
	private String title; 
	private Journal journal; 
	private int volume;  
	private int number;
	private int year;
	private boolean sync=false;
	private int show_title;
	private int show_volume;
	private int show_number;
	private int show_year;
	private ConcurrentHashMap<Long, Article> articles_list;
	private Date date_accepted;
	private Date date_published;

	private int published;
	private int access_status;
	private int current;
	
	public Issue(long id, String title, int volume, int number, int year, int show_title, int show_volume, int show_number,
			int show_year, Date date_accepted, Date date_published) {
		this.title = title;
		this.id = id;
		this.volume = volume;
		this.number = number;
		this.year = year;
		this.show_title = show_title;
		this.show_volume = show_volume;
		this.show_number = show_number;
		this.show_year = show_year;
		this.date_accepted=date_accepted;
		this.date_published = date_published;
		this.articles_list = new  ConcurrentHashMap<Long, Article>();
		this.published = 0;
		this.access_status = 0;
		this.current = 0;
	}
	public Issue(long id) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		
		Date today = new Date();
		this.title = "";
		this.id = id;
		this.volume = 0;
		this.number = 0;
		this.year = 0;
		this.show_title = 1;
		this.show_volume = 1;
		this.show_number = 1;
		this.show_year = 1;
		this.date_accepted=today;
		this.date_published = today;
		this.articles_list = new  ConcurrentHashMap<Long, Article>();
		this.published = 0;
		this.access_status = 0;
		this.current = 0;
	}
	public Issue(long id, String title, int volume, int number, int year, int show_title, int show_volume, int show_number,
			int show_year, Date date_accepted, Date date_published, int published, int access_status, int current, Journal journal) {
		this.title = title;
		this.id = id;
		this.volume = volume;
		this.number = number;
		this.year = year;
		this.show_title = show_title;
		this.show_volume = show_volume;
		this.show_number = show_number;
		this.show_year = show_year;
		this.date_accepted=date_accepted;
		this.date_published = date_published;
		this.articles_list = new  ConcurrentHashMap<Long, Article>();
		this.published = published;
		this.access_status = access_status;
		this.current = current;
		this.journal = journal;
	}
	public Issue(long id, String title, int volume, int number, int year, Date date_accepted, Date date_published) {
		this.title = title;
		this.id = id;
		this.volume = volume;
		this.number = number;
		this.year = year;
		this.show_title = 1;
		this.show_volume = 1;
		this.show_number = 1;
		this.show_year = 1;
		this.date_accepted=date_accepted;
		this.date_published = date_published;
		this.articles_list = new  ConcurrentHashMap<Long, Article>();
	}
	public Issue(long id, String title, int volume, int number, int year, int show_title, int show_volume, int show_number,
			int show_year, Date date_accepted, Date date_published, int current_article_id) {
		this.title = title;
		this.id = id;
		this.volume = volume;
		this.number = number;
		this.year = year;
		this.show_title = show_title;
		this.show_volume = show_volume;
		this.show_number = show_number;
		this.show_year = show_year;
		this.date_accepted=date_accepted;
		this.date_published = date_published;
		this.articles_list = new  ConcurrentHashMap<Long, Article>();
	}

	public long getId() {
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

	public int getShow_title() {
		return show_title;
	}

	public void setShow_title(int show_title) {
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
	public ConcurrentHashMap<Long, Article> getArticles_list() {
		return articles_list;
	}
	
	public ConcurrentHashMap<Long, Article> getSyncArticles_list() {
		ConcurrentHashMap<Long, Article> sync_articles = new ConcurrentHashMap<Long, Article> ();
		Set<Long> art_keys = articles_list.keySet();
		for(long key: art_keys){
			if(articles_list.get((long)key).shouldBeSynced()){
				sync_articles.put((long)key,articles_list.get((long)key));
			}
		}
		return sync_articles;
	}
	public void setArticles_list(ConcurrentHashMap<Long, Article> articles_list) {
		this.articles_list = articles_list;
	}
	@Override
	public String toString() {
		return "Issue [id=" + id + ", title=" + title + ", journal=" + journal + ", volume=" + volume + ", number="
				+ number + ", year=" + year + ", show_title=" + show_title + ", show_volume=" + show_volume
				+ ", show_number=" + show_number + ", show_year=" + show_year + ", articles_list=" + articles_list
				+ ", date_accepted=" + date_accepted + ", date_published=" + date_published + ", published=" + published
				+ ", access_status=" + access_status + ", current=" + current + "]";
	}
	public void add_article(long article_id,Article a){
		articles_list.put(article_id,a);
	}
	public void update_article(long article_id,Article a){
		articles_list.replace(article_id,a);
	}
	public void add_author(long article_id,Author a){
		Article updated=articles_list.get(article_id);
		updated.add_author(a);		
		articles_list.put(article_id,updated);
	}
	public void reset_authors(long article_id){
		Article updated=articles_list.get(article_id);
		updated.reset_authors();	
		articles_list.put(article_id,updated);
	}
	public void remove_article(long id){
		articles_list.remove(id);
	}
	public Date getDate_accepted() {
		return date_accepted;
	}
	public void setDate_accepted(Date date_accepted) {
		this.date_accepted = date_accepted;
	}
	public int getPublished() {
		return published;
	}
	public void setPublished(int published) {
		this.published = published;
	}
	public int getAccess_status() {
		return access_status;
	}
	public void setAccess_status(int access_status) {
		this.access_status = access_status;
	}
	public int getCurrent() {
		return current;
	}
	public void setCurrent(int current) {
		this.current = current;
	}
	public Journal getJournal() {
		return journal;
	}
	public void setJournal(Journal journal) {
		this.journal = journal;
	}
	public ArrayList<Author> getAuthors() {
		ArrayList<Author> all_authors = new ArrayList<Author>();
		Set<Long> article_keys = articles_list.keySet();
		for(Long key: article_keys){
			Article current_article = articles_list.get(key);
			all_authors.addAll(current_article.getAuthors());
		}
		return all_authors;
	}
	public boolean shouldBeSynced() {
		return sync;
	}
	public void setSync(boolean updated) {
		this.sync = updated;
	}
	

}
