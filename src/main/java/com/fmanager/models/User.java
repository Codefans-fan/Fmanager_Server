package main.java.com.fmanager.models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import main.java.com.fmanager.utils.Utils;


public class User {

	/**
	* 
	*/

	@JsonIgnore
	private long id;

	private String userName;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	private String email;

	private String displayName;

	@JsonIgnore
	private String mobile;

	@JsonIgnore
	private Timestamp createDate;

	@JsonIgnore
	private Timestamp loginDate;

	@JsonIgnore
	private Timestamp lastLoginTime;

	private List<UserRole> userRoles;

	private List<Permission> permission;
	
	@JsonIgnore
	private int count;

	@JsonIgnore
	private String salt;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Timestamp loginDate) {
		this.loginDate = loginDate;
	}

	public Timestamp getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	
	public List<String> getRoleNames(){
		if(this.userRoles != null && this.userRoles.size() > 0) {
			List<String> result = new ArrayList<>();
			for(UserRole role : this.userRoles) {
				result.add(role.getRoleName());
			}
			return result;
		}
		
		return null;
	}
	
	
	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	
	public List<Permission> getPermission() {
		return permission;
	}

	public void setPermission(List<Permission> permission) {
		this.permission = permission;
	}

	
	public Set<String> getUserPermissions(){
		if(this.permission != null && this.permission.size() > 0) {
			Set<String> result = new HashSet<>();
			for(Permission permission : this.permission) {
				result.add(permission.getName());
			}
			return result;
		}
		return null;
		
	}
	
	
	public String getSalt() {
		if(StringUtils.isEmpty(this.salt)) {
			this.salt = Utils.getRandomSalt();
		}
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public String toString() {
		return this.getUserName() + ":" + this.getEmail(); //$NON-NLS-1$
	}

}
