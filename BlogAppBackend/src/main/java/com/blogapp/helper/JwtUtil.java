package com.blogapp.helper;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.blogapp.services.CustomUserDetails;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * This class contains method for generating and validating jwt
 * 
 * @author Varsha
 * @since 15/03/2021
 *
 */
@Component
public class JwtUtil {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

	@Value("${pass.app.jwtSecret}")
	private String jwtSecret;

	@Value("${pass.app.jwtExpirationMs}")
	private int jwtExpirationMs;

	/**
	 * Method responsible for generating a JWT from username, date, expiration,
	 * secret.
	 * 
	 * @param authentication
	 * @return String
	 */
	public String generateJwtToken(Authentication authentication) {

		CustomUserDetails userPrincipal = (CustomUserDetails) authentication.getPrincipal();

		return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	/**
	 * Method responsible for getting username from JWT
	 * 
	 * @param token
	 * @return String
	 */
	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	/**
	 * This method validates a JWT
	 * 
	 * @param authToken
	 * @return boolean
	 */
	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}
		return false;
	}
}