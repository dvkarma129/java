package com.categorymanagement.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.categorymanagement.security.JwtEntryPoint;
import com.categorymanagement.security.JwtFilter;

import jakarta.servlet.Filter;

@Configuration
public class SecurityConfig {

	@Autowired
	private JwtFilter jwtfilter;
	
	@Autowired
	private JwtEntryPoint entrypoint;
	
	   @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	        http.csrf(csrf -> csrf.disable())
	        .cors(cors -> cors.disable())
	        .authorizeHttpRequests(auth->auth.requestMatchers("/categories/**").authenticated()
	        		.requestMatchers("/products/**").authenticated()
	        .requestMatchers("/auth/login").permitAll()
	        )
	                .exceptionHandling(ex -> ex.authenticationEntryPoint(entrypoint))
	                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
	        http.addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);
	        return http.build();
	    }
	

}