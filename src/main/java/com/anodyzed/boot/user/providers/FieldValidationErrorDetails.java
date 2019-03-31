package com.anodyzed.boot.user.providers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FieldValidationErrorDetails
 *
 * @author Chris Pratt
 * @since 2019-02-14
 */
public class FieldValidationErrorDetails {
  private String title;
  private int status;
  private String description;
  private long timestamp;
  private String method;
  private String path;
  private String message;
  private Map<String,List<FieldValidationError>> errors;

  public FieldValidationErrorDetails () {
    timestamp = System.currentTimeMillis();
    errors = new HashMap<>();
  } //FieldValidationErrorDetails

  public String getTitle () {
    return title;
  } //getTitle

  public FieldValidationErrorDetails setTitle (String title) {
    this.title = title;
    return this;
  } //setTitle

  public int getStatus () {
    return status;
  } //getStatus

  public FieldValidationErrorDetails setStatus (int status) {
    this.status = status;
    return this;
  } //setStatus

  public String getDescription () {
    return description;
  } //getDescription

  public FieldValidationErrorDetails setDescription (String description) {
    this.description = description;
    return this;
  } //setDescription

  public long getTimestamp () {
    return timestamp;
  } //getTimestamp

  public FieldValidationErrorDetails setTimestamp (long timestamp) {
    this.timestamp = timestamp;
    return this;
  } //setTimestamp

  public String getMethod () {
    return method;
  } //getMethod

  public FieldValidationErrorDetails setMethod (String method) {
    this.method = method;
    return this;
  } //setMethod

  public String getPath () {
    return path;
  } //getPath

  public FieldValidationErrorDetails setPath (String path) {
    this.path = path;
    return this;
  } //setPath

  public String getMessage () {
    return message;
  } //getMessage

  public FieldValidationErrorDetails setMessage (String message) {
    this.message = message;
    return this;
  } //setMessage

  public Map<String,List<FieldValidationError>> getErrors () {
    return errors;
  } //getErrors

  public FieldValidationErrorDetails setErrors (Map<String,List<FieldValidationError>> errors) {
    this.errors = errors;
    return this;
  } //setErrors

  public FieldValidationErrorDetails addError (FieldValidationError error) {
    errors.computeIfAbsent(error.getField(),k -> new ArrayList<>()).add(error);
    return this;
  } //addError
  
} //*FieldValidationErrorDetails
