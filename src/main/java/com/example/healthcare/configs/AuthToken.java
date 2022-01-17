package com.example.healthcare.configs;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AuthToken extends UsernamePasswordAuthenticationToken {
    public AuthToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public AuthToken(Object principal, Object credentials,
                     Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
