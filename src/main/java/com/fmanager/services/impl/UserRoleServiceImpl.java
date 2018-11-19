package main.java.com.fmanager.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import main.java.com.fmanager.dao.UserRoleDAO;
import main.java.com.fmanager.models.UserRole;
import main.java.com.fmanager.services.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Resource
	private UserRoleDAO userRoleDAO;
	

	@Override
	public List<UserRole> getRolesByUser(long id) {
		return null;
	}


	@Override
	public List<UserRole> getRoles() {
		return userRoleDAO.getRoleList();
	}

}
