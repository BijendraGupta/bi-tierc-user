package com.institute.bi.tierc.user.controller;

import java.util.Map;
import org.slf4j.Logger;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.institute.bi.tierc.user.exception.UserNotExistException;
import com.institute.bi.tierc.user.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author gupbi001
 *
 * 
 * 
 */

@Slf4j
public class BaseController {

	public Map<String, Validator> validatorMap;

	public BaseController(Map<String, Validator> validatorMap) {
		this.validatorMap = validatorMap;
	}

	protected void validate(Validator validator, Object data, Class clazz) {
		Errors errors = new BeanPropertyBindingResult(data, clazz.getName());
		if (validator == null) {
			log.debug("No validator defined for {}", clazz.getName());
			return;
		}
		ValidationUtils.invokeValidator(validator, data, errors);

		if (errors.hasErrors()) {
			throw new ValidationException(errors);
		}

	}
}