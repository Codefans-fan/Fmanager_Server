package main.java.com.fmanager.controllers;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import main.java.com.fmanager.events.IEventPublisher;
import main.java.com.fmanager.exception.FmanagerRestException;
import main.java.com.fmanager.models.Article;
import main.java.com.fmanager.models.ArticleType;
import main.java.com.fmanager.models.SimpleArticle;
import main.java.com.fmanager.models.User;
import main.java.com.fmanager.services.ArticleService;
import main.java.com.fmanager.services.UserServcie;
import main.java.com.fmanager.utils.JwtTokenUtil;

@RestController
@RequestMapping("/article")
public class ArticleController {
    
    @Resource
    private ArticleService articleService;
	
    @Resource
	private UserServcie userService;

    
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
    
    @RequestMapping(value="/updatetypes",method = RequestMethod.PUT)
    @RequiresRoles("admin")
    public List<ArticleType> updateTypes(@RequestBody List<ArticleType> typesList) {
    	articleService.updateArticleTypes(typesList);
    	return typesList;
    }
    
    @RequestMapping(value="/updatearticle",method = RequestMethod.POST)
    @RequiresRoles("admin")
    public SimpleArticle updateArticle(@RequestBody SimpleArticle simpleArticle) {
    	
    	Subject subject = SecurityUtils.getSubject();
		String tokenString =  (String) subject.getPrincipal();
		
		String username = JwtTokenUtil.getUsername(tokenString);
		if(StringUtils.isEmpty(tokenString)) {
			return null;
		}
    	
		User user = userService.findByEmail(username);
		if(user == null) {
			return null;
		}
    	
		Article article = new Article();
		
		article.setUserId(user.getId());
		article.setArticleTitle(simpleArticle.getTitle());
		article.setContent(simpleArticle.getContext());
		article.setUpdateTime(new Timestamp(System.currentTimeMillis()));
    	articleService.updateArticle(article);
    	
    	simpleArticle.setId(article.getId());
    	return simpleArticle;
    }
    
    
}
