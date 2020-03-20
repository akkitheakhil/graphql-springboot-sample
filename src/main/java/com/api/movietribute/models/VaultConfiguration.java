package com.api.movietribute.models;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("mtconf")
public class VaultConfiguration {

	
	private String jwtkey;

	public String getJwtkey() {
		return jwtkey;
	}

	public void setJwtkey(String jwtkey) {
		this.jwtkey = jwtkey;
	}
}
