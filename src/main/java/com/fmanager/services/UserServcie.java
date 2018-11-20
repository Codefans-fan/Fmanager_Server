package main.java.com.fmanager.services;

import java.util.List;

import main.java.com.fmanager.models.User;

public interface UserServcie {
	
	public User findById(long id);

	public User findByEmail(String email);
	
	public User findByMobile(String mobile);
	
	public List<User> getUserList();

	public void deleteUserById(long id);
	
	
	
}
