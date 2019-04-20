package com.anodyzed.boot.user.configs;

import com.anodyzed.boot.user.model.Principal;
import com.anodyzed.boot.user.repositories.PrincipalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * PrincipalDetailsService
 *
 * @author Chris Pratt
 * @since 2019-04-13
 */
@Service
public class PrincipalDetailsService implements UserDetailsService {
  @Autowired
  private PrincipalRepository principalRepository;

  @Override
  public UserDetails loadUserByUsername (String username) {
    Principal principal = principalRepository.findByUsername(username);
    if(principal != null) {
      return new User(principal.getUsername(),principal.getPassword(),AuthorityUtils.createAuthorityList(principal.getRole()));
    } else {
      throw new UsernameNotFoundException("Oops! User not found with username: " + username);
    }
  } //loadUserByUsername

} //*PrincipalDetailsService
