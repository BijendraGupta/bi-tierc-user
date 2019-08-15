
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

import com.institute.bi.tierc.user.exception.BaseRuntimeException;
import com.institute.bi.tierc.user.exception.ErrorItem;
import com.institute.bi.tierc.user.exception.ErrorResponse;
import com.institute.bi.tierc.user.exception.ValidationException;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author bijendra
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
	public CoreControllerHandler(@Qualifier("validationErrorMessageSource") MessageSource validationErrorMessageSource,
			@Qualifier("exceptionErrorMessageSource") MessageSource exceptionErrorMessageSource) {
		this.validationErrorMessageSource = validationErrorMessageSource;
		this.exceptionErrorMessageSource = exceptionErrorMessageSource;
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
			String message =  validationErrorMessageSource.getMessage(oe, Locale.getDefault());
			errorItemList.add(ErrorItem.builder().message(message).build());
		}

		ErrorResponse response = ErrorResponse.builder().erroritems(errorItemList).status(HttpStatus.NOT_FOUND.value())
				.build();
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.BAD_REQUEST); // 400

	}

	/**
	 * Handles required field validation from validators
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = { BaseRuntimeException.class })
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleBaseRuntimeException(BaseRuntimeException e) {
		log.debug("Handle BaseRuntime Exception {}", e.getMessage());
		ArrayList<ErrorItem> errorItemList = new ArrayList<ErrorItem>();
		// get the error message for the provided error code
		String message = exceptionErrorMessageSource.getMessage(e.getErrorType().name(),
				new Object[] { e.getArgs()[0] }, Locale.getDefault());

		errorItemList.add(ErrorItem.builder().message(message).build());

		ErrorResponse response = ErrorResponse.builder().erroritems(errorItemList).status(e.getHttpStatus().value())
				.build();
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.BAD_REQUEST); // 400

	}

}
