package main.java.com.fmanager.models;

public enum AppTypes {

	Android(1), IOS(2);
	
	
	private final int type;
	
	private AppTypes(int type){
		
		this.type = type;
	}

	public int getType() {
		return type;
	}
}
