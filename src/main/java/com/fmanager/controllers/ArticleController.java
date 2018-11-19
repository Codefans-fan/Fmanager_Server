package main.java.com.fmanager.controllers;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import main.java.com.fmanager.exception.FmanagerRestException;
import main.java.com.fmanager.models.Article;
import main.java.com.fmanager.services.ArticleService;

@RestController
@RequestMapping("/article")
public class ArticleController {
    
    @Resource
    private ArticleService articleService;
	
    @RequestMapping(value="/list/{page}",method = RequestMethod.GET)
    public List<Article> getArticles(@PathVariable int page){
    	return  articleService.getArticles(page);
    }
    
    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public Article getArticle(@PathVariable long id) {
    	return  articleService.getArticleById(id);
    }

    @RequestMapping(value="/",method = RequestMethod.POST)
    public Article saveArticle(HttpServletRequest  request,  @RequestBody Article article) throws FmanagerRestException{
    	return articleService.saveArticle(article);
    }
    
}
