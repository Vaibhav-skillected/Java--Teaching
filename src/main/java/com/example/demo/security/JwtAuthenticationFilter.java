package com.example.demo.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    
  @Override
protected void doFilterInternal(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull FilterChain filterChain)
        throws ServletException, IOException {

    System.out.println("========== JWT FILTER ==========");
    System.out.println("URL : " + request.getRequestURI());

    String authHeader = request.getHeader("Authorization");

    System.out.println("Header : " + authHeader);

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {

        System.out.println("No Bearer Token Found");

        filterChain.doFilter(request, response);
        return;
    }

    String token = authHeader.substring(7);

    System.out.println("Token : " + token);

    String username = jwtService.extractUsername(token);

    System.out.println("Username : " + username);

    if (username != null &&
        SecurityContextHolder.getContext().getAuthentication() == null) {

        UserDetails userDetails =
                userDetailsService.loadUserByUsername(username);

        System.out.println("Authorities : " + userDetails.getAuthorities());

        if (jwtService.isTokenValid(token, userDetails)) {

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);

            System.out.println("Authentication SUCCESS");
        }
    }

    filterChain.doFilter(request, response);
}
    }