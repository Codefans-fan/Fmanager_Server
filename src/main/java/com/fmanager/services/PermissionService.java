package main.java.com.fmanager.services;

import java.util.List;

import main.java.com.fmanager.models.Permission;


public interface PermissionService {

	public List<Permission> getPermissionByRole(long id);
	
}
