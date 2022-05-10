package com.jwt.token.utils;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.jwt.token.entity.User;
import com.jwt.token.exception.AccessDeniedException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	private static String secret_key = "This is my secret key";
	private static long expiryDuration = 60 * 60; // One hour

	public String generateJWTToken(User user) {

		long currentDate = System.currentTimeMillis();
		Date issueAt = new Date(currentDate);

		Date exiryAt = new Date(currentDate + expiryDuration * 1000);// convert into milliseconds

		// Claim - nothing but payload
		Claims claims = Jwts.claims().setIssuer(user.getId().toString()).setIssuedAt(issueAt).setExpiration(exiryAt);

		// add optional claims - don't give any sensitive information
		claims.put("type", user.getUserType());
		claims.put("name", user.getName());

		// compact - user to convert into Sting like toString
		// generate token using claims
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret_key).compact();

	}
/*
	public void verifyToken(String authorization) throws AccessDeniedException {

		try {
			Jwts.parser().setSigningKey(secret_key).parse(authorization);
		} catch (Exception e) {
			throw new AccessDeniedException("Access denied");
		}

	}
*/
	public Claims verifyToken(String authorization) throws AccessDeniedException {

		try {
			Claims claims = Jwts.parser().setSigningKey(secret_key).parseClaimsJws(authorization).getBody();

			// get particular value from payload, payload from token
			System.out.println(claims.get("name"));
			return claims;
		} catch (Exception e) {
			throw new AccessDeniedException("Access denied");
		}

	}

}
