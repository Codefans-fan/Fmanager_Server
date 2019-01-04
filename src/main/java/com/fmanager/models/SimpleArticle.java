package main.java.com.fmanager.models;

public class SimpleArticle extends BaseEntity {
	
	private String title;
	
	private String contentDesc;
	
	private long articleType;
	
	private String context;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getContentDesc() {
		return contentDesc;
	}

	public void setContentDesc(String contentDesc) {
		this.contentDesc = contentDesc;
	}

	public long getArticleType() {
		return articleType;
	}

	public void setArticleType(long articleType) {
		this.articleType = articleType;
	}
	
	
	
	
}
