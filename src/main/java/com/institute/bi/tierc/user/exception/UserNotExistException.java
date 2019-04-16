package com.institute.bi.tierc.user.exception;

import org.springframework.http.HttpStatus;

public class UserNotExistException extends BaseRuntimeException {
	private static final long serialVersionUID = 1L;

	public UserNotExistException() {
		super();
		this.setHttpStatus(HttpStatus.NOT_FOUND);
		this.setErrorType(ErrorType.USER_NOT_EXIST);
	}

	public UserNotExistException(String userId) {
		super(userId);
		this.setHttpStatus(HttpStatus.NOT_FOUND);
		this.setErrorType(ErrorType.USER_NOT_EXIST);
	}
}
