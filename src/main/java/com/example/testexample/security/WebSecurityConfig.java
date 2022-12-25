package com.example.testexample.security;

import com.example.testexample.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


  private final SecurityService securityService;
  private final BCryptPasswordEncoder passwordEncoder;


  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .userDetailsService(securityService)
        .passwordEncoder(passwordEncoder);
  }


  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.authorizeRequests()
        .antMatchers(
            "/",
            "/web",
            "/web/",
            "/web/common",
            "/api/checker"
        )
        .permitAll()
        .antMatchers("/web/users/**")
        .hasAuthority(Role.ADMIN.getAuthority())
        .antMatchers("/api/v1/users/**")
        .hasAuthority(Role.ADMIN.getAuthority())
//                .antMatchers("/web/groups")
//                .hasAuthority(Role.ADMIN.getAuthority())
        .antMatchers("/web/**")
        .authenticated()
//                .and()
//                TODO: NOT IMPLEMENTED YET
//                .formLogin()
//                .loginPage("/web/signin")
//                .loginProcessingUrl("/web/signin")
//                .permitAll()
//                .and()

//                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/web/signout", "GET"))
//                .permitAll()
        .and()
        .exceptionHandling();
  }


  @Override
  public void configure(WebSecurity web) {
//        TODO: ADD Static content, fix favicon request
    web.ignoring()
        .antMatchers(
            "/static/**",
            "/webjars/**",
            "/css/**",
            "/js/**",
            "/images/**",
            "/error/**",
            "/web/error/**",
            "/favicon.ico"
        );
  }


  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }


}
