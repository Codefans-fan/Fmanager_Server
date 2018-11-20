package main.java.com.fmanager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import main.java.com.fmanager.models.UserRole;
import main.java.com.fmanager.services.UserRoleService;

@RestController
@RequestMapping("/userrole")
public class UserRoleController {
	
	@Autowired
	private UserRoleService userRoleService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<UserRole> getRoleList() {
		return userRoleService.getRoles();
	}
}
