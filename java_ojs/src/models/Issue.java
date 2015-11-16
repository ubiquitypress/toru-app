package models;

import java.util.Date;

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
	
	

}
