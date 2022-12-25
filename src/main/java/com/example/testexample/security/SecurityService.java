package com.example.testexample.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// this is stumb
// TODO: IMPLEMENT IT WITH USERSERVICE AND DATABASE
@Service
public class SecurityService implements UserDetailsService {

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        if (nonNull(username)) {
//            return new User("name", "pass", Arrays.asList(Role.USER));
//        } else {
//        }
    return null;
  }
}
