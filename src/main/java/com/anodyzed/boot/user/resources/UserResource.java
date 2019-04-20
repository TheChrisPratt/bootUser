package com.anodyzed.boot.user.resources;

import com.anodyzed.boot.user.model.User;
import com.anodyzed.boot.user.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * UserResource
 *
 * @author Chris Pratt
 * @since 2019-01-25
 */
@RestController
@RequestMapping(value="/api/user",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
public class UserResource {
  private static final Logger log = LoggerFactory.getLogger(UserResource.class);

  @Autowired
  private UserRepository userRepository;

  private ResponseEntity error (HttpStatus status,String message) {
    return ResponseEntity.status(status).body("{\"errorMessage\": \"" + message + "\"}");
  } //error

  @GetMapping("/")
  public ResponseEntity<List<User>> list () {
    List<User> users = userRepository.findAll();
    log.info("Listing {} Users",users.size());
    return ResponseEntity.ok(users);
  } //list

  @GetMapping("/{id}")
  public ResponseEntity get (@PathVariable("id") final long id) {
    Optional<User> result = userRepository.findById(id);
    if(result.isPresent()) {
      User user = result.get();
      log.info("Getting User: [{}] {}",user.getId(),user.getName());
      return ResponseEntity.ok(user);
    } else {
      log.warn("User with ID={} Not Found",id);
      return error(HttpStatus.NOT_FOUND,"User with id " + id + " not found.");
    }
  } //get

  @PostMapping("/")
  public ResponseEntity create (@Valid @RequestBody final User user,final UriComponentsBuilder builder) {
    if(userRepository.findByName(user.getName()) == null) {
      User saved = userRepository.save(user);
      UriComponents uri = builder.path("/api/user/{id}").buildAndExpand(saved.getId());
      log.info("Created User: [{}] {}",user.getId(),user.getName());
      return ResponseEntity.created(uri.toUri()).build();
    } else {
      return error(HttpStatus.CONFLICT,"Unable to create new user.  A User with name " + user.getName() + " already exists.");
    }
  } //create

  @PutMapping("/{id}")
  public ResponseEntity update (@PathVariable("id") final long id,@Valid @RequestBody final User user) {
      // fetch user based on id and set it to currentUser object of type User
    Optional<User> result = userRepository.findById(id);
    if(result.isPresent()) {
      User currentUser = result.get();
        // update currentUser object data with user object data
      currentUser.setName(user.getName());
      currentUser.setAddress(user.getAddress());
      currentUser.setEmail(user.getEmail());
        // save currentUser object
      userRepository.saveAndFlush(currentUser);
      log.info("Updated User: [{}] {}",id,user.getName());
        // return ResponseEntity
      return ResponseEntity.ok(currentUser);
    } else {
      log.warn("Unable to locate User with ID={} to update",id);
      return error(HttpStatus.NOT_FOUND,"Unable to update user.  User with id " + id + " not found.");
    }
  } //update

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<User> delete (@PathVariable("id") final long id) {
    userRepository.deleteById(id);
    log.info("Deleted User: [{}]",id);
    return ResponseEntity.noContent().build();
  } //delete
  
} //*UserResource
