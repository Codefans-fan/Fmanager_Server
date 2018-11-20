package main.java.com.fmanager.models;

import java.sql.Timestamp;


public class Article {
    
    private long id;
    private String userName;
    private long userId;
    private String articleTitle;
    private long contentId;
    private String contentDesc;
    private Timestamp publishTime;
    private int viewCount;
    private long articleType;
    private int protectType;
    private boolean isCommentAble;
    private String tags;
    
    //only when get article detail will set this value
    private String content;
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getArticleTitle() {
        return articleTitle;
    }
    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }
    public long getContentId() {
        return contentId;
    }
    public void setContentId(long contentId) {
        this.contentId = contentId;
    }
    public String getContentDesc() {
        return contentDesc;
    }
    public void setContentDesc(String contentDesc) {
        this.contentDesc = contentDesc;
    }
    
    public Timestamp getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}
	public int getViewCount() {
        return viewCount;
    }
    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }
    public long getArticleType() {
        return articleType;
    }
    public void setArticleType(long articleType) {
        this.articleType = articleType;
    }
    public int getProtectType() {
        return protectType;
    }
    public void setProtectType(int protectType) {
        this.protectType = protectType;
    }
    public boolean isCommentAble() {
        return isCommentAble;
    }
    public void setCommentAble(boolean isCommentAble) {
        this.isCommentAble = isCommentAble;
    }
    public String getTags() {
        return tags;
    }
    public void setTags(String tags) {
        this.tags = tags;
    }
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
    
	
}
