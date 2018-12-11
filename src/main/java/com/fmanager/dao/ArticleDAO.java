package main.java.com.fmanager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import main.java.com.fmanager.models.Article;
import main.java.com.fmanager.models.ArticleType;

@Mapper
public interface ArticleDAO {
	
	public List<Article> getArticleByPage(int page);

	public Article getArticleById(long id);

	public long saveArticle(Article article);
	
	public long saveArticleContent(Article article);
	
	public List<ArticleType> getArticleTypes();
}
