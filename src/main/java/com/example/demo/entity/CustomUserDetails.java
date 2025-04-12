package com.example.demo.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private String email;  // 任意のプロパティ
    private String fullName;  // 任意のプロパティ


    public CustomUserDetails(String username, String password,
                              Collection<? extends GrantedAuthority> authorities,
                              String email, String fullName) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.email = email;
        this.fullName = fullName;
    }
    // 必須メソッド
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // 任意の情報のGetter
    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }
}

