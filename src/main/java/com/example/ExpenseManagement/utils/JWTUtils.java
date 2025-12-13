package com.example.ExpenseManagement.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTUtils {
    private final String SECRET = "c6oWcW_JgdIWj7Wk}MV-a^~nu}-}&h[A";
    private final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
    public String generateToken(String username){
        return Jwts.builder()
                .issuedAt(new Date())
                .subject(username)
                .expiration(new Date(System.currentTimeMillis() + 1000 *60*3))   //3mins
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }
}
