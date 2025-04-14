package com.example.demo.model.enumerations;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    HOST;

    @Override
    public String getAuthority() {
        return name();
    }
}
