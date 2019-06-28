package main.java.com.fmanager.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * AppDispatch model
 * 
 * @author fky  2019-06-18
 *
 */
public class AppDispatch extends BaseEntity{

	private String appId;
	
	private String appName;
	
	@JsonIgnore
	private long userId;
	
	//basec64 string
	private String combo;
	
	private int appType;
	
	private String appBundle;
	
	private int downloadCount;
	
	private String appVersion;
	
	private int version;
	
	//file path
	private String appFile;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getCombo() {
		return combo;
	}

	public void setCombo(String combo) {
		this.combo = combo;
	}

	public int getAppType() {
		return appType;
	}

	public void setAppType(int appType) {
		this.appType = appType;
	}

	public String getAppBundle() {
		return appBundle;
	}

	public void setAppBundle(String appBundle) {
		this.appBundle = appBundle;
	}

	public int getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}


	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getAppFile() {
		return appFile;
	}

	public void setAppFile(String appFile) {
		this.appFile = appFile;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	
}
