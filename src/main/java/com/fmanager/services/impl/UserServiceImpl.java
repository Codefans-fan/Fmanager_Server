package main.java.com.fmanager.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import main.java.com.fmanager.dao.PermissionDAO;
import main.java.com.fmanager.dao.UserDAO;
import main.java.com.fmanager.dao.UserRoleDAO;
import main.java.com.fmanager.models.Permission;
import main.java.com.fmanager.models.User;
import main.java.com.fmanager.models.UserRole;
import main.java.com.fmanager.services.UserServcie;

@Service
public class UserServiceImpl implements UserServcie {

	@Resource
	private UserDAO userDAO;

	@Resource
	private UserRoleDAO userRoleDAO;

	@Resource
	private PermissionDAO permissionDAO;

	@Override
	public User findById(long id) {
		User user =  userDAO.findById(id);
		List<UserRole>  userRoles =  userRoleDAO.getUserRoleByUserId(user.getId());
		List<Permission> permissions = new ArrayList<>();
		for(UserRole role :  userRoles) {
			permissions.addAll(permissionDAO.findPermissionsByRole(role.getId()));
		}
		user.setUserRoles(userRoles);
		user.setPermission(permissions);
		return user;
	}

	@Override
	public List<User> getUserList() {
		return userDAO.getUserList();
	}

	@Override
	public void deleteUserById(long id) {
		userDAO.deleteUserById(id);
	}

	@Override
	public User findByEmail(String email) {
		User user = userDAO.findByEmail(email);

		List<UserRole> userRoles = userRoleDAO.getUserRoleByUserId(user.getId());
		List<Permission> permissions = new ArrayList<>();
		for (UserRole role : userRoles) {
			permissions.addAll(permissionDAO.findPermissionsByRole(role.getId()));
		}
		user.setUserRoles(userRoles);
		user.setPermission(permissions);
		return user;
	}

	@Override
	public User findByMobile(String mobile) {
		User user = userDAO.findByMobile(mobile);

		List<UserRole> userRoles = userRoleDAO.getUserRoleByUserId(user.getId());
		List<Permission> permissions = new ArrayList<>();
		for (UserRole role : userRoles) {
			permissions.addAll(permissionDAO.findPermissionsByRole(role.getId()));
		}
		user.setUserRoles(userRoles);
		user.setPermission(permissions);
		return user;
	}

}