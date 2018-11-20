package main.java.com.fmanager.controllers;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import main.java.com.fmanager.models.AccessToken;
import main.java.com.fmanager.models.User;
import main.java.com.fmanager.services.UserServcie;
import main.java.com.fmanager.utils.JwtTokenUtil;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserServcie userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public AccessToken login(@RequestBody User user) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getEmail(), user.getPassword());
		subject.login(token);
		User checkedUser = (User) subject.getPrincipal();
		String newToken = JwtTokenUtil.sign(checkedUser.getEmail(), checkedUser.getPassword());
		return new AccessToken(newToken);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public void logout() {
		Subject subject = SecurityUtils.getSubject();
		if(subject.getPrincipals() != null) {
			subject.logout();
        }
	}
	
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@RequiresRoles("admin")
	public User getUserById(@PathVariable long id) {
		return userService.findById(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@RequiresRoles("admin")
	public void deleteUserById(@PathVariable long id) {
		userService.deleteUserById(id);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@RequiresRoles("admin")
	public List<User> getUserList() {
		return userService.getUserList();
	}

}