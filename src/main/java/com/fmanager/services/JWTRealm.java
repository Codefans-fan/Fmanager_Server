package main.java.com.fmanager.services;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.com.fmanager.models.JWTToken;
import main.java.com.fmanager.models.User;
import main.java.com.fmanager.services.impl.UserServiceImpl;
import main.java.com.fmanager.utils.JwtTokenUtil;

@Service
public class JWTRealm extends AuthorizingRealm {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof JWTToken;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		Object o = principals.getPrimaryPrincipal();
		if(o instanceof String) {
			String username = JwtTokenUtil.getUsername((String)o);
			User user = userServiceImpl.findByEmail(username);
			simpleAuthorizationInfo.addRoles(user.getRoleNames());
			simpleAuthorizationInfo.addStringPermissions(user.getUserPermissions());
		}
		return simpleAuthorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
		String token = (String) auth.getCredentials();
        String username = JwtTokenUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token invalid");
        }

        User userBean = userServiceImpl.findByEmail(username);
        if (userBean == null) {
            throw new AuthenticationException("User didn't existed!");
        }

        if (!JwtTokenUtil.verify(token, username, userBean.getPassword())) {
            throw new AuthenticationException("Username or password error");
        }

        return new SimpleAuthenticationInfo(token, token, "jwt_realm");
    }
}
