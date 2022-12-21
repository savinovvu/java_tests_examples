package com.example.testexample.model;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import static com.example.testexample.model.Authority.EDIT_USER;
import static com.example.testexample.model.Authority.SHOW_DATA;

// TODO: IMPLEMENT IT WITH GROUPS AUTHORITY AND USER AUTHORITY
@AllArgsConstructor
public enum Role implements GrantedAuthority {

    USER(SHOW_DATA), ADMIN(EDIT_USER);


    public final String authority;

    @Override
    public String getAuthority() {
        return authority;
    }
}
