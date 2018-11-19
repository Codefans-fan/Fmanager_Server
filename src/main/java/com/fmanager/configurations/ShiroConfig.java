package main.java.com.fmanager.configurations;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import main.java.com.fmanager.filter.JWTFilter;
import main.java.com.fmanager.services.MyRealm;
import main.java.com.fmanager.utils.ConstantString;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;

@Configuration
public class ShiroConfig {

	@Bean("securityManager")
    public DefaultWebSecurityManager getManager(MyRealm realm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(realm);
        /*
         * close shiro  session
         * http://shiro.apache.org/session-management.html#SessionManagement-StatelessApplications%28Sessionless%29
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        manager.setSubjectDAO(subjectDAO);
        return manager;
    }
	
	@Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean factory(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("jwt", new JWTFilter());
        factoryBean.setFilters(filterMap);
        factoryBean.setLoginUrl(ConstantString.NOT_LOGIN);
        factoryBean.setSecurityManager(securityManager);
        factoryBean.setUnauthorizedUrl(ConstantString.UNAUTH_URL);
        Map<String, String> filterRuleMap = new HashMap<>();
        filterRuleMap.put("/**", "jwt");
        filterRuleMap.put(ConstantString.ERROR_URL, "anon");
        filterRuleMap.put(ConstantString.UNAUTH_URL, "anon");
        factoryBean.setFilterChainDefinitionMap(filterRuleMap);
        return factoryBean;
    }
	
}
