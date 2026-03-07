package com.example.testjurnal.config;

import com.example.testjurnal.security.JwtCore;
import com.example.testjurnal.security.TokenFilter;
import com.example.testjurnal.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;

@Component
public class SecurityConfig {

    private final JwtCore jwtCore;
    private final UsersService usersService;

    @Autowired
    public SecurityConfig(JwtCore jwtCore, UsersService usersService) {
        this.jwtCore = jwtCore;
        this.usersService = usersService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOriginPatterns(java.util.List.of("*"));
                    config.setAllowedMethods(java.util.List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
                    config.setAllowedHeaders(java.util.List.of("*"));
                    config.setAllowCredentials(true);
                    return config;
                }))
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new TokenFilter(jwtCore, usersService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
