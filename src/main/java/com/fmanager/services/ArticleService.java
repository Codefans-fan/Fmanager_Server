package main.java.com.fmanager.services;

import java.util.List;

import main.java.com.fmanager.exception.FmanagerRestException;
import main.java.com.fmanager.models.Article;

public interface ArticleService {
    
    public List<Article> getArticles(int page);
    
    public Article getArticleById(long id);
    
    
    public Article saveArticle(Article article) throws FmanagerRestException;

}
