package main.java.com.fmanager.services;

import java.util.List;

import main.java.com.fmanager.models.UserRole;

public interface UserRoleService {
	public List<UserRole> getRolesByUser(long id);

	public List<UserRole> getRoles();
	
}
