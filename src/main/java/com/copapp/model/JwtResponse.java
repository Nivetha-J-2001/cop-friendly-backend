package com.copapp.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class JwtResponse {


    String token;
    String email;
    String roles;
    Long id;

    public JwtResponse(String token, String email, Collection<? extends GrantedAuthority> collection, Long id) {
        this.token = token;
        this.email=email;
        this.id=id;
        this.roles=collection.toString();
        
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
    
    
}
