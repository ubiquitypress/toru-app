package models;

import java.util.ArrayList;
import java.util.Date;

public class Journal {
	private long id;
	private String path;

	private String title;
	private float seq;
	private String primary_locale;
	private long enabled;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public float getSeq() {
		return seq;
	}
	@Override
	public String toString() {
		return "Journal [id=" + id + ", path=" + path + ", seq=" + seq + ", primary_locale=" + primary_locale
				+ ", enabled=" + enabled + "]";
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
	public long getEnabled() {
		return enabled;
	}
	public void setEnabled(long enabled) {
		this.enabled = enabled;
	}
	public long getId() {
		return id;
	}
	public Journal(long id, String path, float seq, String primary_locale, long enabled) {
		this.id = id;
		this.path = path;
		this.seq = seq;
		this.primary_locale = primary_locale;
		this.enabled = enabled;
		this.title="";
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
