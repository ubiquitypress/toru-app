package models;

public class ArticleFile {
	private int id;
	private int article_id;
	private String path;
	public ArticleFile(int file_id,int article_id, String path) {
	
		this.id = file_id;
		this.article_id = article_id;
		this.path = path;
	}
	public int getId() {
		return id;
	}
	public int getArticle_id() {
		return article_id;
	}
	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	
	
}
