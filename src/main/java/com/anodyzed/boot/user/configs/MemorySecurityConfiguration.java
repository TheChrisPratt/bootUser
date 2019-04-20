package com.anodyzed.boot.user.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * SecurityMemoryConfiguration
 *
 * @author Chris Pratt
 * @since 2019-04-12
 */
//@Configuration
public class MemorySecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  protected void configureGlobal (AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().withUser("user").password("{noop}password").roles("USER")
                           .and().withUser("admin").password("{noop}password").roles("USER","ADMIN");
  } //configureGlobal

  @Override
  protected void configure (HttpSecurity http) throws Exception {
    http.httpBasic()
        .and().authorizeRequests()
              .antMatchers(HttpMethod.GET,   "/api/user/").hasRole("USER")
              .antMatchers(HttpMethod.POST,  "/api/user/").hasRole("USER")
              .antMatchers(HttpMethod.PUT,   "/api/user/**").hasRole("USER")
              .antMatchers(HttpMethod.DELETE,"/api/user/**").hasRole("ADMIN")
        .and().csrf().disable();
  } //configure

} //*MemorySecurityConfiguration
