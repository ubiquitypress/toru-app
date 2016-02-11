package models;

public class Metadata {
	private long id;
	private long article_id;
	private String competing_interests="";
	private String funding="";
	
	public Metadata(long id, long article_id, String competing_interests, String funding) {
		super();
		this.id = id;
		this.article_id = article_id;
		this.competing_interests = competing_interests;
		this.funding = funding;
	}
	
	public Metadata(long id, long article_id) {
		super();
		this.id = id;
		this.article_id = article_id;
		this.competing_interests = "";
		this.funding = "";
	}
	public long getId() {
		return id;
	}

	public long getArticle_id() {
		return article_id;
	}
	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}
	public String getCompeting_interests() {
		return competing_interests;
	}
	public void setCompeting_interests(String competing_interests) {
		this.competing_interests = competing_interests;
	}
	public String getFunding() {
		return funding;
	}
	public void setFunding(String funding) {
		this.funding = funding;
	}
	
	
}
