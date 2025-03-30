package com.springboot.rest.services;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

//import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import io.jsonwebtoken.Claims;




import java.util.Base64;

import java.util.function.Function;


@Service
public class JWTservice {

     private  String SECRET_KEY = ""; 
	 
	 public JWTservice() throws NoSuchAlgorithmException {
		 KeyGenerator keyGenerator =  KeyGenerator.getInstance("HmacSHA256");
		 SecretKey sk = keyGenerator.generateKey();
		 
		 SECRET_KEY =   Base64.getEncoder().encodeToString(sk.getEncoded());
		 
		 

	 }
	 
	// generates JWT Token
	 	Map<String ,Object> claims = new  HashMap<>();
	    public String generateToken(String username) {
	        return Jwts.builder()
	                .claims()
	                .add(claims)
	                .subject(username)
	                .issuedAt(new Date( System.currentTimeMillis()))
	                .expiration(new Date( System.currentTimeMillis() + 1000 * 60 * 30))
	                .and()
	                .signWith(getKey())
	                .compact();
	    }

	    private SecretKey getKey() {
	    	byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
	    	
	        return Keys.hmacShaKeyFor(keyBytes);
	        		 
	    }
 
	
	    public String extractUserName(String token) {
	        // extract the username from jwt token
	        return extractClaim(token, Claims::getSubject);
	    }

	 // extract any claim from the token

	    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
	        final Claims claims = extractAllClaims(token);
	        return claimResolver.apply(claims);
	    }

	 // extract all claims from the token
	    private Claims extractAllClaims(String token) {
	        return Jwts.parser()
	                .verifyWith((SecretKey) getKey())
	                .build()
	                .parseSignedClaims(token)
	                .getPayload();
	    }
	    // Validate token
	    public boolean validateToken(String token, UserDetails userDetails) {
	        final String userName = extractUserName(token);
	        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
	    }

	    // Checks if token is expired
	    private boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());
	    }
	 // Extracts expiration date
	    private Date extractExpiration(String token) {
	        return extractClaim(token, Claims::getExpiration);
	    }

	
}
