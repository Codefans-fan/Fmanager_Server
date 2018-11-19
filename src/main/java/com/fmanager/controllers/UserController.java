package main.java.com.fmanager.controllers;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import main.java.com.fmanager.models.AuthModel;
import main.java.com.fmanager.models.User;
import main.java.com.fmanager.services.UserServcie;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserServcie userService;

	@RequestMapping(value = "/notlogin", method = RequestMethod.GET)
	public AuthModel notLogin() {
		return new AuthModel(-1, "No user logn");
	}
	
	@RequestMapping(value = "/notrole", method = RequestMethod.GET)
	public AuthModel notRole() {
		return new AuthModel(-1, "No role");
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public User login(@RequestBody User user) {
		return userService.findByEmail(user.getEmail());
	}
	
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User getUserById(@PathVariable long id) {
		return userService.findById(id);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteUserById(@PathVariable long id) {
		userService.deleteUserById(id);
	}
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<User> getUserList(){
		return userService.getUserList();
	}
	
	
	

}
