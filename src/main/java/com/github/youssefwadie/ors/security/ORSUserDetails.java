package com.github.youssefwadie.ors.security;

import java.io.Serial;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.github.youssefwadie.ors.entities.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ORSUserDetails implements UserDetails {
	@Serial
	private static final long serialVersionUID = -2936821934711672746L;
	
	private final User user;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
