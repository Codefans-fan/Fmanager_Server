package main.java.com.fmanager.services;

import java.util.List;

import main.java.com.fmanager.models.RegisterUser;
import main.java.com.fmanager.models.User;
import main.java.com.fmanager.models.UserRole;

public interface UserServcie {
	
	public User findById(long id);

	public User findByEmail(String email);
	
	public User findByMobile(String mobile);
	
	public List<User> getUserList();

	public void deleteUserById(long id);
	
	public void registerUser(RegisterUser registerUser);
	
	
	public List<UserRole> getUserRoles();
	
	public long getUserCount();
	
	public long getTodaysRegister();
	
}
