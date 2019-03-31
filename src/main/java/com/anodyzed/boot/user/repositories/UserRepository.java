package com.anodyzed.boot.user.repositories;

import com.anodyzed.boot.user.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRepository
 *
 * @author Chris Pratt
 * @since 2019-01-25
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

  User findByName(String name);

} //#UserRepository
