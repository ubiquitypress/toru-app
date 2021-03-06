package models;

public class Author {
	private String first_name; 
	private String middle_name; 
	private String last_name; 
	private String email; 
	private String affiliation=""; 
	private String bio=""; 
	private String orcid=""; 
	private String twitter=""; 
	private String department="";
	private String country="";
	private String url="";

	private boolean deleted=false;
	private long user_group=1;
	private long id;
	private long article_id;
	private long primary_contact=0;
	private long seq=0;
	
	public Author(long id, String first_name, String middle_name, String last_name, String email, String affiliation, String bio,
			String orcid, String department, String country) {
		this.id = id;
		this.first_name = first_name;
		this.middle_name = middle_name;
		this.last_name = last_name;
		this.email = email;
		this.affiliation = affiliation;
		this.bio = bio;
		this.orcid = orcid;
		this.department = department;
		this.country = country;
	}
	public long getId() {
		return id;
	}
	public String getFull_name() {
		String full_name=this.first_name+" ";
		if(!this.middle_name.isEmpty()){
			full_name=full_name+this.middle_name+" ";
		}
		full_name=full_name+this.last_name;
		return full_name;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getMiddle_name() {
		return middle_name;
	}
	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAffiliation() {
		return affiliation;
	}
	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public String getOrcid() {
		return orcid;
	}
	public void setOrcid(String orcid) {
		this.orcid = orcid;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Author(long id) {
		super();
		this.id = id;
	}
	public long getArticle_id() {
		return article_id;
	}
	public void setArticle_id(long article_id) {
		this.article_id = article_id;
	}
	public Author(String first_name, String middle_name, String last_name, String email, String affiliation, String bio,
			String orcid, String department, String country, long id, long article_id) {
		super();
		this.first_name = first_name;
		this.middle_name = middle_name;
		this.last_name = last_name;
		this.email = email;
		this.affiliation = affiliation;
		this.bio = bio;
		this.orcid = orcid;
		this.department = department;
		this.country = country;
		this.id = id;
		this.article_id = article_id;
	}
	public Author(String email) {
		super();
		this.email = email;
	}
	@Override
	public String toString() {
		return  first_name+" "+ middle_name +" "+  last_name+" ("+email+")";
	}
	public long getPrimary_contact() {
		return primary_contact;
	}
	public void setPrimary_contact(long primary_contact) {
		this.primary_contact = primary_contact;
	}
	public long getSeq() {
		return seq;
	}
	public void setSeq(long seq) {
		this.seq = seq;
	}
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public long getUser_group() {
		return user_group;
	}
	public void setUser_group(long user_group) {
		this.user_group = user_group;
	}
	public String getTwitter() {
		return twitter;
	}
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	public Author(String first_name, String middle_name, String last_name, String email, String affiliation, String bio,
			String orcid, String twitter, String department, String country, String url, long user_group, long id,
			long article_id, long primary_contact, long seq) {
		super();
		this.first_name = first_name;
		this.middle_name = middle_name;
		this.last_name = last_name;
		this.email = email;
		this.affiliation = affiliation;
		this.bio = bio;
		this.orcid = orcid;
		this.twitter = twitter;
		this.department = department;
		this.country = country;
		this.url = url;
		this.user_group = user_group;
		this.id = id;
		this.article_id = article_id;
		this.primary_contact = primary_contact;
		this.seq = seq;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	

}
