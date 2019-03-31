package com.anodyzed.boot.user.providers;

/**
 * FieldValidationError
 *
 * @author Chris Pratt
 * @since 2019-02-14
 */
public class FieldValidationError {
  private String field;
  private String message;
  private MessageType type;

  public FieldValidationError () {
  } //FieldValidationError

  public FieldValidationError (String field,String message,MessageType type) {
    this.field = field;
    this.message = message;
    this.type = type;
  } //FieldValidationError

  public String getField () {
    return field;
  } //getField

  public FieldValidationError setField (String field) {
    this.field = field;
    return this;
  } //setField

  public String getMessage () {
    return message;
  } //getMessage

  public FieldValidationError setMessage (String message) {
    this.message = message;
    return this;
  } //setMessage

  public MessageType getType () {
    return type;
  } //getType

  public FieldValidationError setType (MessageType type) {
    this.type = type;
    return this;
  } //setType
  
} //*FieldValidationError
