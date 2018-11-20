package main.java.com.fmanager.exception;


public class FmanagerRestException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int errorCode;
	
	public FmanagerRestException(int errCode, String message) {
		super(message);
		this.errorCode = errCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

}
