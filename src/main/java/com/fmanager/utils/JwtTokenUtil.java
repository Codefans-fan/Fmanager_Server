package main.java.com.fmanager.utils;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

public final class JwtTokenUtil {

	// 10 minute
    private static final long EXPIRE_TIME = 60*60*60*1000;


    /**
     * verify token
     * @param token 
     * @param secret  password
     * @return is ok
     */
    public static boolean verify(String token, String username, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
    
    /**
     *  get user name from token
     * @return username 
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
    
    
    public static String sign(String username, String secret) {
        Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
		Algorithm algorithm = Algorithm.HMAC256(secret);
		return JWT.create()
		        .withClaim("username", username)
		        .withExpiresAt(date)
		        .sign(algorithm);
    }
}
