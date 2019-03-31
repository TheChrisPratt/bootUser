package com.anodyzed.boot.user.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * UserRegistrationConfiguration
 *
 * @author Chris Pratt
 * @since 2019-02-15
 */
@Configuration
public class UserRegistrationConfiguration {

  @Bean(name="messageSource")
  public ReloadableResourceBundleMessageSource messageSource () {
    ReloadableResourceBundleMessageSource messageBundle = new ReloadableResourceBundleMessageSource();
    messageBundle.setBasename("classpath:messages/messages");
    messageBundle.setDefaultEncoding("UTF-8");
    return messageBundle;
  } //messageSource

} //*UserRegistrationConfiguration
