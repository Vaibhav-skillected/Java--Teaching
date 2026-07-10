package com.example.demo.corsConfig;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    // Security Rules
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http

                // Disable CSRF (for REST APIs)
                .csrf(csrf -> csrf.disable())

                // Authorization Rules
                .authorizeHttpRequests(auth -> auth

                        // Public APIs
                        .requestMatchers("/employee/save").permitAll()

                        // Protected APIs
                        .requestMatchers("/employee/getall").authenticated()
                        .requestMatchers("/employee/getbyidres/**").authenticated()

                        // Admin Only
                        .requestMatchers("/employee/update/**").hasRole("ADMIN")
                        .requestMatchers("/employee/**").hasRole("ADMIN")

                        // Any other request
                        .anyRequest().authenticated()

                )

                // Default Login Page
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

}