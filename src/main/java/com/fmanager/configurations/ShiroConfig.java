package main.java.com.fmanager.configurations;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSessionStorageEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import main.java.com.fmanager.filter.JWTFilter;
import main.java.com.fmanager.services.DBRealm;
import main.java.com.fmanager.services.JWTRealm;
import main.java.com.fmanager.utils.ConstantString;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.mgt.SessionStorageEvaluator;

@Configuration
public class ShiroConfig {

	@Autowired
	private DBRealm dbRealm;

	@Autowired
	private JWTRealm jwtRealm;

	@Bean
	protected SessionStorageEvaluator sessionStorageEvaluator() {
		DefaultWebSessionStorageEvaluator sessionStorageEvaluator = new DefaultWebSessionStorageEvaluator();
		sessionStorageEvaluator.setSessionStorageEnabled(false);
		return sessionStorageEvaluator;
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

	@Bean
	public Authorizer authorizer() {
		return new ModularRealmAuthorizer();
	}
	
	
	@Bean
	public Authenticator authenticator() {
		ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
		// set two Realm，one is for login by username； one for jwt token
		authenticator.setRealms(Arrays.asList(dbRealm, jwtRealm));
		authenticator.setAuthenticationStrategy(new FirstSuccessfulStrategy());
		return authenticator;
	}
}
