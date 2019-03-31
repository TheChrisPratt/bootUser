package com.anodyzed.boot.user.providers;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ValidationHandler
 *
 * @author Chris Pratt
 * @since 2019-02-14
 */
@ControllerAdvice
public class ValidationHandler {
  @Autowired
  private MessageSource messageSource;

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<FieldValidationErrorDetails> handleValidationError (MethodArgumentNotValidException x,HttpServletRequest req) {
    Locale locale = LocaleContextHolder.getLocale();
    FieldValidationErrorDetails details = new FieldValidationErrorDetails();
    details.setStatus(HttpStatus.BAD_REQUEST.value());
    details.setTitle(messageSource.getMessage("error.fieldValidation.title",null,locale));
    details.setDescription(messageSource.getMessage("error.fieldValidation.description",null,locale));
    details.setMessage(x.getClass().getName());
    details.setMethod(req.getMethod());
    details.setPath(req.getRequestURI());

    BindingResult result = x.getBindingResult();
    List<FieldError> fieldErrors = result.getFieldErrors();
    for(FieldError fieldError : fieldErrors) {
      details.addError(processFieldError(fieldError,locale));
    }
    return ResponseEntity.badRequest().body(details);
  } //handleValidationError

  private FieldValidationError processFieldError (final FieldError error,final Locale locale) {
    return (error != null) ? new FieldValidationError(error.getField(),messageSource.getMessage(error.getDefaultMessage(),null,locale),MessageType.ERROR) : new FieldValidationError();
  } //processFieldError

} //*ValidationHandler
