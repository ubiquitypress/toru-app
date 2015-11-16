package models;

public class Section {
	private int id;
	private String title;
	public Section(int section_id, String title) {
		super();
		this.id = section_id;
		this.title = title;
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
	
	
}
