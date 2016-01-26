package models;

import java.util.ArrayList;
import java.util.Date;

public class Journal {
	private int id;
	private String path;
	private float seq;
	private String primary_locale;
	private int enabled;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public float getSeq() {
		return seq;
	}
	public void setSeq(float seq) {
		this.seq = seq;
	}
	public String getPrimary_locale() {
		return primary_locale;
	}
	public void setPrimary_locale(String primary_locale) {
		this.primary_locale = primary_locale;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public int getId() {
		return id;
	}
	public Journal(int id, String path, float seq, String primary_locale, int enabled) {
		this.id = id;
		this.path = path;
		this.seq = seq;
		this.primary_locale = primary_locale;
		this.enabled = enabled;
	}
	
}
