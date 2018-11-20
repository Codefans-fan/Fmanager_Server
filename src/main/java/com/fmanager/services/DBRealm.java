package main.java.com.fmanager.services;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.com.fmanager.models.User;
import main.java.com.fmanager.models.UserRole;
import main.java.com.fmanager.services.impl.UserServiceImpl;

@Service
public class DBRealm extends AuthorizingRealm {

	public DBRealm() {
        this.setCredentialsMatcher(new HashedCredentialsMatcher(Sha256Hash.ALGORITHM_NAME));
	}
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private UserRoleService userRoleService;
	
	
	
	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken userpasswordToken = (UsernamePasswordToken)token;
        String username = userpasswordToken.getUsername();
        User user = userServiceImpl.findByEmail(username);
        if(user == null)
            throw new AuthenticationException("user not found.");
        
        return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), "dbRealm");
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		Object o = principals.getPrimaryPrincipal();
		if(o instanceof User) {  
			 User user = (User)o;
		        List<UserRole> roles = user.getUserRoles();
		        if(roles == null) {
		            roles = userRoleService.getRolesByUser(user.getId());
		            user.setUserRoles(roles);
		        }
		        if (roles != null)
		            simpleAuthorizationInfo.addRoles(user.getRoleNames());
		}
        return simpleAuthorizationInfo;
	}

}
