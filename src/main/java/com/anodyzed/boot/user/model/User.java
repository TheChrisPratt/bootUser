package com.anodyzed.boot.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

/**
 * User
 *
 * @author Chris Pratt
 * @since 2019-01-25
 */
@Entity
@Table(name="User")
@SuppressWarnings({"UnusedReturnValue","unused"})
public class User {
  private static final int NAME_MAX = 50;
  private static final int ADDRESS_MAX = 150;
  private static final int EMAIL_MAX = 80;
  
  @Id
  @GeneratedValue
  @Column(name="UserID")
  private Long id;

  @NotEmpty(message="error.name.empty")
  @Length(max=NAME_MAX,message="error.name.length")
  @Column(name="Name",length=NAME_MAX,nullable=false)
  private String name;

  @NotEmpty(message="error.address.empty")
  @Length(max=ADDRESS_MAX,message="error.address.length")
  @Column(name="Address",length=ADDRESS_MAX,nullable=false)
  private String address;

  @Email(message="error.email.email")
  @NotEmpty(message="error.email.empty")
  @Length(max=EMAIL_MAX,message="error.email.length")
  @Column(name="Email",length=EMAIL_MAX,nullable=false)
  private String email;

  public Long getId () {
    return id;
  } //getId

  public User setId (Long id) {
    this.id = id;
    return this;
  } //setId

  public String getName () {
    return name;
  } //getName

  public User setName (String name) {
    this.name = name;
    return this;
  } //setName

  public String getAddress () {
    return address;
  } //getAddress

  public User setAddress (String address) {
    this.address = address;
    return this;
  } //setAddress

  public String getEmail () {
    return email;
  } //getEmail

  public User setEmail (String email) {
    this.email = email;
    return this;
  } //setEmail
  
} //*User
