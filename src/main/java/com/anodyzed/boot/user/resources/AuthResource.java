package com.anodyzed.boot.user.resources;

import java.security.Principal;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthResource
 *
 * @author Chris Pratt
 * @since 2019-05-05
 */
@RestController
@RequestMapping("/api/auth")
public class AuthResource {

  @GetMapping(value="/user",produces=MediaType.APPLICATION_JSON_VALUE)
  public Principal getUser (Principal user) {
    return user;
  } //getUser
  
} //*AuthResource
