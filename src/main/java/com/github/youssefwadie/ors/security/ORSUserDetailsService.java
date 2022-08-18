package com.github.youssefwadie.ors.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.github.youssefwadie.ors.entities.User;
import com.github.youssefwadie.ors.repositories.UserRepository;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class ORSUserDetailsService implements UserDetailsService {
	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if(user == null) {
			throw new UsernameNotFoundException("no user with email: %s was found!".formatted(email));
		}
		return new ORSUserDetails(user);
	}

}
