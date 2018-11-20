package main.java.com.fmanager.models;

public final class ErrorResponse {
	private int code;
	
	private String message;

	
	public ErrorResponse(int code, String msg) {
		this.code = code;
		this.message = msg;
	}
	
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
