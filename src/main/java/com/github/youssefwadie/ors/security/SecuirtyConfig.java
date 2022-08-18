package com.github.youssefwadie.ors.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecuirtyConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
			.formLogin()
			.loginPage("/login")
			.usernameParameter("email")
			.passwordParameter("password")
			.permitAll();
		
		http
			.rememberMe()
			.key("abCDeFgHiJkLmNOpQRStUVWxYZ")
			.tokenValiditySeconds(3600); 	// one hour

		http.authorizeRequests(request -> {
			request.antMatchers("/js/**", "/webjars/**").permitAll();
			request.anyRequest().authenticated();
		});

		return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
