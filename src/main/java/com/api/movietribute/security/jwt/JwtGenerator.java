package com.api.movietribute.security.jwt;

import org.springframework.stereotype.Component;

import com.api.movietribute.models.LoginUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator {
	
    public String generate(LoginUser jwtUser) {
        Claims claims = Jwts.claims()
                .setSubject(jwtUser.getUsername());
        claims.put("role", String.valueOf(jwtUser.getRole()));
        claims.put("email", String.valueOf(jwtUser.getEmail()));

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "Graphql")
                .compact();
    }
}
