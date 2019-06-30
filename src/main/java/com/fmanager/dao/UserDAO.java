package main.java.com.fmanager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import main.java.com.fmanager.models.User;


@Mapper
public interface UserDAO {
	public User findById(long id);
	
	public User findByUserName(String name);

	public User findByEmail(String email);

	public User findByMobile(String mobile);
	
	public void registerUser(User user);

	public List<User> getUserList();

	public void deleteUserById(long id);
	
	public Long getUserCount();
	
	public Long getTodaysRegister();
}
