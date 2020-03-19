package com.api.movietribute.security.jwt;

import org.springframework.stereotype.Component;

import com.api.movietribute.models.LoginUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtValidator {
    private String secret = "Graphql";

    public LoginUser validate(String token) {

        LoginUser jwtUser = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser = new LoginUser();
            
            jwtUser.setUsername(body.getSubject());
            jwtUser.setEmail((String) body.get("email"));
            jwtUser.setRole((String) body.get("role"));
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return jwtUser;
    }
}
