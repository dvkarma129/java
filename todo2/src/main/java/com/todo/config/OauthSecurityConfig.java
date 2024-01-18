//package com.todo.config;
//
//import org.springframework.boot.autoconfigure.security.SecurityProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//public class OauthSecurityConfig {
//
//	@Bean
//    @Order(SecurityProperties.BASIC_AUTH_ORDER)
//    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(csrf -> csrf.disable()).cors(cors -> cors.disable());
//        http.authorizeHttpRequests(requests -> requests.anyRequest().authenticated());
//        http.oauth2Login(Customizer.withDefaults());
//        return http.build();
//    }
//}
