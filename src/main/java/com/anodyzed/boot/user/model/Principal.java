package com.anodyzed.boot.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * Principal
 *
 * @author Chris Pratt
 * @since 2019-04-13
 */
@Entity
@Table(name="Principal")
public class Principal {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="UserID")
  private Long id;

  @NotEmpty
  @Column(name="UserName",nullable=false)
  private String username;

  @NotEmpty
  @Column(name="Password",nullable=false)
  private String password;

  @Column(name="Enabled")
  private boolean isEnabled;

  @Column(name="Role")
  private String role;

  public Long getId () {
    return id;
  } //getId

  public Principal setId (Long id) {
    this.id = id;
    return this;
  } //setId

  public String getUsername () {
    return username;
  } //getUsername

  public Principal setUsername (String username) {
    this.username = username;
    return this;
  } //setUsername

  public String getPassword () {
    return password;
  } //getPassword

  public Principal setPassword (String password) {
    this.password = password;
    return this;
  } //setPassword

  public boolean isEnabled () {
    return isEnabled;
  } //isEnabled

  public Principal setEnabled (boolean enabled) {
    isEnabled = enabled;
    return this;
  } //setEnabled

  public String getRole () {
    return role;
  } //getRole

  public Principal setRole (String role) {
    this.role = role;
    return this;
  } //setRole
  
} //*Principal
