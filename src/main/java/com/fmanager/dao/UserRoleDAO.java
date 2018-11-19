package main.java.com.fmanager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import main.java.com.fmanager.models.UserRole;

@Mapper
public interface UserRoleDAO {
	
	public List<UserRole> getUserRoleByUserId(long id);
	
	public List<UserRole> getRoleList();

	
}
