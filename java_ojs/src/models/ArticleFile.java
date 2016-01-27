package models;

public class ArticleFile {
	private long id;
	private long article_id;
	private String path;
	public ArticleFile(long file_id,long article_id, String path) {
	
		this.id = file_id;
		this.article_id = article_id;
		this.path = path;
	}
	public long getId() {
		return id;
	}
	public long getArticle_id() {
		return article_id;
	}
	public void setArticle_id(long article_id) {
		this.article_id = article_id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	
	
}
