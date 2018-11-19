package main.java.com.fmanager.models;

public class AuthModel {

	private int status;
	
	private String desc;

	public AuthModel() {}
	
	public AuthModel(int status ,String desc) {
		this.status = status;
		this.desc = desc;
	}
	
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	
}
