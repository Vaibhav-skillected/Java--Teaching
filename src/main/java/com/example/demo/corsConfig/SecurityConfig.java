package com.example.demo.corsConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    //calll register time
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //cll login time 
    @Bean
    public AuthenticationProvider authenticationProvider() {

    	//dao -> databse acess object -> this provides authentictes users using database
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(customUserDetailsService);
//bcrypt pass and compare password db and user entered
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }
    
    //authentication manager controls login
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration)
            throws Exception {

        return configuration.getAuthenticationManager();

    }

    //every api  must pass through security before entering 
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

    	http
    	
    	//cross site request forgery
//its protects browser sessions 
    	.csrf(csrf->csrf.disable())

    	.authorizeHttpRequests(auth->auth

    	.requestMatchers("/employee/save","/auth/**").permitAll()

    	.requestMatchers("/employee/getall").hasRole("EMPLOYEE")

    	.requestMatchers("/employee/getbyidres/**").authenticated()

    	.requestMatchers("/employee/update/**").hasRole("ADMIN")
 
    	//.hasAny()

    	.anyRequest().authenticated()

    	)

    	.authenticationProvider(authenticationProvider())

    	.sessionManagement(session->
    	session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    	)

    	.addFilterBefore(
    	jwtAuthenticationFilter,
    	UsernamePasswordAuthenticationFilter.class
    	);

        return http.build();
    }
}