package com.anodyzed.boot.user.repositories;

import com.anodyzed.boot.user.model.Principal;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * PrincipalRepository
 *
 * @author Chris Pratt
 * @since 2019-04-13
 */
public interface PrincipalRepository extends JpaRepository<Principal,Long> {
  Principal findByUsername(String username);
} //#PrincipalRepository
