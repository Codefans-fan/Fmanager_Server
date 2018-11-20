package main.java.com.fmanager.utils;

import java.security.SecureRandom;

import org.apache.commons.codec.binary.Base64;

public final class Utils {

	public static String getRandomSalt() {
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[15];
		random.nextBytes(bytes);
		return Base64.encodeBase64String(bytes);
	}
	
}
