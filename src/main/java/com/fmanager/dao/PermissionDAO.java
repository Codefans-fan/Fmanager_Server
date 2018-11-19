package main.java.com.fmanager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import main.java.com.fmanager.models.Permission;

@Mapper
public interface PermissionDAO {

	public List<Permission> findPermissionsByRole(long roleid);
	
}
