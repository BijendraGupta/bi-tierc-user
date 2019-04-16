package com.institute.bi.tierc.user.exception;

import org.springframework.http.HttpStatus;

public interface BaseException {
	
	HttpStatus getHttpStatus();

	void setHttpStatus(HttpStatus httpStatus);

	ErrorType getErrorType();

	void setErrorType(ErrorType errorType);

	Object[] getArgs();

	void setArgs(Object[] args);
}
