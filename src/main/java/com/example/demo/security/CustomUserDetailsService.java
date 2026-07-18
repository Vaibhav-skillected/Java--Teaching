package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Employee employee = repository.findByEmail(username);

        if (employee == null) {
            throw new UsernameNotFoundException(
                    "Employee not found with email : " + username);
        }

        return new CustomUserDetails(employee);
    }
//employee-> repository -> userdetial service -> user details -> 
    //authorities -> authentication provider -> authentication manager 
}