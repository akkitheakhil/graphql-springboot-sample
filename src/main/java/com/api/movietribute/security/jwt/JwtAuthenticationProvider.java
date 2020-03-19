package com.api.movietribute.security.jwt;

import java.util.List;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.api.movietribute.models.JwtAuthenticationToken;
import com.api.movietribute.models.JwtUserDetails;
import com.api.movietribute.models.LoginUser;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	 @Autowired
	 private JwtValidator validator;
	 
	 	@Override
	  	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {

	    }
	 	
	 	@Override
	    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {

	        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) usernamePasswordAuthenticationToken;
	        String token = jwtAuthenticationToken.getToken();

	        LoginUser jwtUser = validator.validate(token);

	        if (jwtUser == null) {
	            throw new RuntimeException("JWT Token is incorrect");
	        }

	        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
	        		 .commaSeparatedStringToAuthorityList(jwtUser.getRole());
	        return new JwtUserDetails(jwtUser.getUsername(), jwtUser.getPassword(),
	                token,
	                grantedAuthorities);
	    }

	 	@Override
	    public boolean supports(Class<?> aClass) {
	        return (JwtAuthenticationToken.class.isAssignableFrom(aClass));
	    }
}
