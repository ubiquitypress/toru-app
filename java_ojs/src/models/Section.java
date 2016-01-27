package models;

public class Section {
	private long id;
	private String title;
	public Section(long section_id, String title) {
		super();
		this.id = section_id;
		this.title = title;
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
	
	
}
