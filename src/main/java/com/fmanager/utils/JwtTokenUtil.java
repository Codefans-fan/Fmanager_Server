package main.java.com.fmanager.utils;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import main.java.com.fmanager.models.User;

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
                    .withClaim("email", username)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
    
    /**
     *  get user name from token
     * @return user name 
     */
    public static String getEmail(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("email").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
    
    
    private static Long getUserId(String token) {
    	try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("id").asLong();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
    
    public static String sign(User user) {
    	if(user != null) {
    		Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
    		Algorithm algorithm = Algorithm.HMAC256(user.getPassword());
    		
    		JWTCreator.Builder builder = JWT.create()
    				.withClaim("id",user.getId())
    		        .withClaim("username", user.getUserName()).withClaim("email",user.getEmail());
    		if(user.getRoleNames() != null && user.getRoleNames().size() > 0 ) {
    			builder.withArrayClaim("roles",user.getRoleNames().toArray(new String[0]));
    		}
    		return  builder.withExpiresAt(date).sign(algorithm);
    	}
    	return StringUtils.EMPTY_STRING;
    }
    
    
    public static String sign(String email, String secret) {
        Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
		Algorithm algorithm = Algorithm.HMAC256(secret);
		return JWT.create()
		        .withClaim("email", email)
		        .withExpiresAt(date)
		        .sign(algorithm);
    }
    
    /**
     * 通过登入用户Principal token 解析 用户id
     * @return userid, 0 returned if failed
     */
    public static long getUserIdFromAuthPrincipal() {
    	Subject subject = SecurityUtils.getSubject();
		Object token = subject.getPrincipal();
		if(token instanceof String) {
			return getUserId((String)token);
		}
		return 0;
    	
    }
    
}
