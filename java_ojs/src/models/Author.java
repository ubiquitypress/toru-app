package models;

public class Author {
	private String first_name; 
	private String middle_name; 
	private String last_name; 
	private String email; 
	private String affiliation; 
	private String bio; 
	private String orcid; 
	private String department;
	private String country;
	private int id;
	
	public Author(int id, String first_name, String middle_name, String last_name, String email, String affiliation, String bio,
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
	public int getId() {
		return id;
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
	
	

}
