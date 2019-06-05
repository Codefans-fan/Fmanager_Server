package main.java.com.fmanager.services.impl;

import javax.annotation.Resource;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import main.java.com.fmanager.dao.UserDAO;
import main.java.com.fmanager.models.User;


@Service
public class InitUserServiceImpl implements ApplicationListener<ContextRefreshedEvent>{
	private static final Logger logger = LoggerFactory.getLogger(InitUserServiceImpl.class);

	@Resource
	private UserDAO userDAO;
	
	@Value("${fmanager.defaultuser.name}")
	private String defaultUserName;

	@Value("${fmanager.defaultuser.password}")
	private String defaultUserPassword;

	@Value("${fmanager.defaultuser.email}")
	private String defaultUserEmail;

	
	
	//when app start success run this method
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getParent() == null){//root application context .  
			if(userDAO.findByEmail(defaultUserEmail) == null) {
				User user = new User();
				user.setUserName(defaultUserName);
				user.setEmail(defaultUserEmail);
				user.setMobile("0");
				String hashedPasswordHex = new SimpleHash(Sha256Hash.ALGORITHM_NAME, defaultUserPassword, ByteSource.Util.bytes(user.getSalt())).toHex();
				user.setPassword(hashedPasswordHex);
				userDAO.registerUser(user);
				logger.debug("Init defualt user" + defaultUserEmail);
			}			
        }  
		
	}

}
