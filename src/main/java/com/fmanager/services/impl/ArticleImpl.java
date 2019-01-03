package main.java.com.fmanager.services.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import main.java.com.fmanager.dao.ArticleDAO;
import main.java.com.fmanager.exception.FmanagerRestException;
import main.java.com.fmanager.models.Article;
import main.java.com.fmanager.models.ArticleType;
import main.java.com.fmanager.services.ArticleService;
import main.java.com.fmanager.utils.ErrorNumber;

@Service
public class ArticleImpl implements ArticleService {

	@Resource
	private ArticleDAO articleDAO;
	
	
	
	
    @Override
    public List<Article> getArticles(int page) {
    	if(page > 0) {
    		return articleDAO.getArticleByPage(page);
    	}
    	return articleDAO.getArticleByPage(1);
    }

	@Override
	public Article getArticleById(long id) {
		return articleDAO.getArticleById(id);
	}

	@Override
	public Article saveArticle(Article article) throws FmanagerRestException {
		
		if(StringUtils.isEmpty(article.getArticleTitle())) {
			throw new FmanagerRestException(ErrorNumber.ARTICLE_TITLE_EMPTY, "Article Title is Empty");
		}
		
		//todo  need check more article detail
		
		articleDAO.saveArticleContent(article);
		article.setPublishTime(new Timestamp(System.currentTimeMillis()));
		article.setContentDesc(article.getContent().substring(0,article.getContent().length()/3));
		articleDAO.saveArticle(article);
		return article;
	}

	@Override
	public List<ArticleType> getArticleTypes() {
		return articleDAO.getArticleTypes();
	}

	@Override
	public void updateArticleTypes(List<ArticleType> typeList) {
		articleDAO.updateArticleTypes(typeList);
	}

	@Override
	public Article updateArticle(Article article) {
		
		
		if(article.getId() > 0) {
			//update
		}else {
			//new
			articleDAO.saveArticleContent(article);
			articleDAO.saveArticle(article);
		}
		
		return null;
	}

	
}
