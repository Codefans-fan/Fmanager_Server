package main.java.com.fmanager.models;

public class ArticleType extends BaseEntity {

	private String typeName;
	
	private String description;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
