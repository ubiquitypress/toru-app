package models;

public class Section {
	private long id;
	private String title;
	private double seq=0.0;
	private boolean sync=false;
	private boolean deleted=false;
	private int editor_restricted = 0;
	private int meta_indexed = 0;
	private int meta_reviewed = 0;
	private int abstracts_not_required = 0;
	private int hide_title = 0;
	private int hide_author = 0;
	private int hide_about = 0;
	private int disable_comments = 0;
	private long abstract_word_count = 0;

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

	public int getEditor_restricted() {
		return editor_restricted;
	}

	public void setEditor_restricted(int editor_restricted) {
		this.editor_restricted = editor_restricted;
	}

	public int getMeta_indexed() {
		return meta_indexed;
	}

	public void setMeta_indexed(int meta_indexed) {
		this.meta_indexed = meta_indexed;
	}

	public int getMeta_reviewed() {
		return meta_reviewed;
	}

	public void setMeta_reviewed(int meta_reviewed) {
		this.meta_reviewed = meta_reviewed;
	}

	public int getAbstracts_not_required() {
		return abstracts_not_required;
	}

	public void setAbstracts_not_required(int abstracts_not_required) {
		this.abstracts_not_required = abstracts_not_required;
	}

	public int getHide_title() {
		return hide_title;
	}

	public void setHide_title(int hide_title) {
		this.hide_title = hide_title;
	}

	public int getHide_author() {
		return hide_author;
	}

	public void setHide_author(int hide_author) {
		this.hide_author = hide_author;
	}

	public int getHide_about() {
		return hide_about;
	}

	public void setHide_about(int hide_about) {
		this.hide_about = hide_about;
	}

	public int getDisable_comments() {
		return disable_comments;
	}

	public void setDisable_comments(int disable_comments) {
		this.disable_comments = disable_comments;
	}

	public long getAbstract_word_count() {
		return abstract_word_count;
	}

	public void setAbstract_word_count(long abstract_word_count) {
		this.abstract_word_count = abstract_word_count;
	}

	public Section(long id, String title, int editor_restricted, int meta_indexed, int meta_reviewed,
			int abstracts_not_required, int hide_title, int hide_author, int hide_about, int disable_comments,
			long abstract_word_count) {
		super();
		this.id = id;
		this.title = title;
		this.editor_restricted = editor_restricted;
		this.meta_indexed = meta_indexed;
		this.meta_reviewed = meta_reviewed;
		this.abstracts_not_required = abstracts_not_required;
		this.hide_title = hide_title;
		this.hide_author = hide_author;
		this.hide_about = hide_about;
		this.disable_comments = disable_comments;
		this.abstract_word_count = abstract_word_count;
	}

	public double getSeq() {
		return seq;
	}

	public void setSeq(double seq) {
		this.seq = seq;
	}

	public boolean shouldBeSynced() {
		return sync;
	}

	public void setSync(boolean sync) {
		this.sync = sync;
	}

	@Override
	public String toString() {
		return "Section [id=" + id + ", title=" + title + ", seq=" + seq + ", editor_restricted=" + editor_restricted
				+ ", meta_indexed=" + meta_indexed + ", meta_reviewed=" + meta_reviewed + ", abstracts_not_required="
				+ abstracts_not_required + ", hide_title=" + hide_title + ", hide_author=" + hide_author
				+ ", hide_about=" + hide_about + ", disable_comments=" + disable_comments + ", abstract_word_count="
				+ abstract_word_count + "]";
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
