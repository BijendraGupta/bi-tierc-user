
package com.institute.bi.tierc.user.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Loads validation & exception related error message properties file
 * 
 */

@Slf4j
@Configuration
public class ErrorMessageConfig {
//

//            @Bean("exceptionErrorMessageSource")

//            public MessageSource exceptionErrorMessageSource() {

//

//                            log.debug("Loading exception error messages properties file");

//

//                            ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

//                            messageSource.setBasename("classpath:exception_error_messages");

//                            return messageSource;

//            }

	@Bean("validationErrorMessageSource")
	public MessageSource validatoinErrorMessageSource() {
		log.info("Loading validation error messages properties file");
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:validation_error_messages");
		return messageSource;
	}
}
