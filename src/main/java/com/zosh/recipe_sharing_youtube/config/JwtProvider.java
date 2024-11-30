package com.zosh.recipe_sharing_youtube.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

//this class belongs to create JWT token and provider
@Service
public class JwtProvider {

    private SecretKey key= Keys.hmacShaKeyFor(JwtConstant.JWT_SECRET.getBytes());

    public String generateToken(Authentication auth) {
        String jwt= Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+86400000))
                .claim("email", auth.getName()) //set email inside token for register and login.
                .signWith(key).compact();

        return jwt;
    }

    public String getEmailFromJwtToken(String jwt) {
        jwt= jwt.substring(7);
        Claims claims= Jwts
                .parserBuilder()
                .setSigningKey(key).build()
                .parseClaimsJws(jwt)
                .getBody();

        String email= String.valueOf(claims.get("email")); //Jwts claim and this claims "email" should same for reference.

        return email;
    }
}

//2.19.26
