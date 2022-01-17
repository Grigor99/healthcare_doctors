package com.example.healthcare.configs.utils;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class SecureUser implements UserDetails {
    private String id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private Collection<? extends GrantedAuthority> authorities = Collections.EMPTY_LIST;
    private Boolean accountNonExpired = true;
    private Boolean accountNonLocked = true;
    private Boolean credentialsNonExpired = true;
    private Boolean enabled = true;

    public SecureUser() {
        super();
    }

    public SecureUser(String id, String email, String password, String name, String surname,
                      Collection<? extends GrantedAuthority> authorities) {
        this.setId(id);
        this.setUsername(email);
        this.setPassword(password);
        this.setName(name);
        this.setSurname(surname);
        this.setAuthorities(authorities);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @JsonIgnore
    public Boolean getAccountNonExpired() {
        return this.accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.getAccountNonExpired();
    }

    @JsonIgnore
    public Boolean getAccountNonLocked() {
        return this.accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.getAccountNonLocked();
    }

    @JsonIgnore
    public Boolean getCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.getCredentialsNonExpired();
    }

    @JsonIgnore
    public Boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isEnabled() {
        return this.getEnabled();
    }

}
