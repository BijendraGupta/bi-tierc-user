
/**

*

 */

package com.institute.bi.tierc.user.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.institute.bi.tierc.user.exception.ErrorItem;
import com.institute.bi.tierc.user.exception.ErrorResponse;
import com.institute.bi.tierc.user.exception.ValidationException;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author gupbi001
 *
 * 
 * 
 */

@ControllerAdvice

@Slf4j
public class CoreControllerHandler {
	private MessageSource exceptionErrorMessageSource;

	private MessageSource validationErrorMessageSource;

	@Autowired
	public CoreControllerHandler(
			@Qualifier("validationErrorMessageSource") MessageSource validationErrorMessageSource) {
		this.validationErrorMessageSource = validationErrorMessageSource;
	}

	/**
	 * Handles required field validation from validators
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = { ValidationException.class })
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleValidationException(ValidationException e) {
		log.debug("handleValidationException {}", e.getMessage());
		ArrayList<ErrorItem> errorItemList = new ArrayList<ErrorItem>();
		List<ObjectError> errorList = e.getErrors().getAllErrors();
		for (ObjectError oe : errorList) {
			// get the error message for the provided error code
			String message = e.getErrorType() + e.getErrorType().name()
					+ validationErrorMessageSource.getMessage(oe, Locale.getDefault());
			errorItemList.add(ErrorItem.builder().message(message).build());
		}

		ErrorResponse response = ErrorResponse.builder().erroritems(errorItemList)
				.status(HttpStatus.BAD_REQUEST.value()).build();
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.BAD_REQUEST); // 400

	}

}
