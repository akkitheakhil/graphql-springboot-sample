package com.api.movietribute.security;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.api.movietribute.security.filters.JwtAuthenticationTokenFilter;
import com.api.movietribute.security.jwt.JwtAuthenticationEntryPoint;
import com.api.movietribute.security.jwt.JwtAuthenticationProvider;
import com.api.movietribute.security.jwt.JwtSuccessHandler;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

		@Autowired
	    private JwtAuthenticationProvider authenticationProvider;
	    @Autowired
	    private JwtAuthenticationEntryPoint entryPoint;

	    @Bean
	    public AuthenticationManager authenticationManager() {
	        return new ProviderManager(Collections.singletonList(authenticationProvider));
	    }

	    @Bean
	    public JwtAuthenticationTokenFilter authenticationTokenFilter() {
	        JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter();
	        filter.setAuthenticationManager(authenticationManager());
	        filter.setAuthenticationSuccessHandler(new JwtSuccessHandler());
	        return filter;
	    }


	    @Override
	    protected void configure(HttpSecurity http) throws Exception {

	        http.csrf().disable()
	                .authorizeRequests().antMatchers("**/graphql/**").permitAll()
	                .and()
	                .exceptionHandling().authenticationEntryPoint(entryPoint)
	                .and()
	                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	        http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	        http.headers().cacheControl();
	        System.out.println("/graphql patterns");

	    }
}
