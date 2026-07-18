package com.example.demo.service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	//jwt service responsible for -> creating token
								// reading token , validating token
	

	//@value -> value comming from applicaton.properties
	
    @Value("${jwt.secret}")
    private String secretKey;

    //how to create jwt secret key // generate jwt secret //alg h256 token -> jwt 
    private SecretKey getSignKey() {
        return Keys.hmacShaKeyFor(
                secretKey.getBytes(StandardCharsets.UTF_8)
        );
    }
    //jwt librart cannot use string directly 
//string -> bytes -> secret key 
    
    
    public String generateToken(String email) {
//builders means build pattern to start creating token
        return Jwts.builder()
                .subject(email)//owner of jwt 
                .issuedAt(new Date()) //current time 
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))//token expiration 1hr 
                .signWith(getSignKey(), Jwts.SIG.HS256)//this is heart of jwt here token is digitally signed
                //401 -> invalid signature 
                .compact();//converts bulder to jwt string (token)
    }
    //builder -> subject -> issue time -> expiry -> signature -> jwt 

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);

        return username.equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    
    //jwt structure -> header , payload , signature 
}