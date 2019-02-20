package com.kenzan.config;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SecurityConfig {

	private final ApplicationProperties authProperties;

	@Autowired
	public SecurityConfig(ApplicationProperties authProperties) {
		this.authProperties = authProperties;
	}

	private String getCredentials() {
		return Base64.getEncoder()
				.encodeToString((authProperties.getUser() + ":" + authProperties.getPassword()).getBytes()).toString();
	}

	public boolean isAuthorized(String token) {
		String credentials = getCredentials();
		if (token != null)
			return token.contains(credentials);
		return false;
	}

}
