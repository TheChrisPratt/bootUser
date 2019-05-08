package com.anodyzed.boot.user.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * DatabaseSecurityConfiguration
 *
 * @author Chris Pratt
 * @since 2019-04-14
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class DatabaseSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private PrincipalDetailsService principalDetailsService;

  @Override
  protected void configure (AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
    authenticationManagerBuilder.userDetailsService(principalDetailsService);
  } //configure

  @Override
  protected void configure (HttpSecurity http) throws Exception {
    http.httpBasic().realmName("User Registration System")
        .and().authorizeRequests()
              .antMatchers("/","/templates/login.html","/templates/home.html","/scripts/*","/styles/*","/favicon.ico").permitAll()
              .anyRequest().authenticated()
        .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
  } //configure

} //*DatabaseSecurityConfiguration
