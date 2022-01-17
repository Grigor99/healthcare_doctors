package com.example.healthcare.configs;


import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

public class AuthProvider extends DaoAuthenticationProvider {
    public boolean supports(Class<?> authentication) {
        return (AuthToken.class.equals(authentication));
    }
}
