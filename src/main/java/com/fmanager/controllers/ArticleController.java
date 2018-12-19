package main.java.com.fmanager.controllers;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import main.java.com.fmanager.events.Event;
import main.java.com.fmanager.events.IEventPublisher;
import main.java.com.fmanager.events.TestEventModel;
import main.java.com.fmanager.exception.FmanagerRestException;
import main.java.com.fmanager.models.Article;
import main.java.com.fmanager.models.ArticleType;
import main.java.com.fmanager.services.ArticleService;

@RestController
@RequestMapping("/article")
public class ArticleController {
    
    @Resource
    private ArticleService articleService;
	
    @Resource
    private IEventPublisher eventPublisherServiceImpl;
    
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
    
    @RequestMapping(value="/typelist",method = RequestMethod.GET)
    @RequiresRoles("admin")
    public List<ArticleType> getArticleTypes() {
    	return articleService.getArticleTypes();
    	
    }
    
    @RequestMapping(value="/test",method = RequestMethod.GET)
    @RequiresGuest
    public void test() {
    	
    	
    	System.out.println("stest");
    	TestEventModel model = new TestEventModel();
    	model.setText("Hello world");
    	eventPublisherServiceImpl.publishEvent(new Event<>(this,model));
    }
}
