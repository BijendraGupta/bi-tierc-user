package com.institute.bi.tierc.user.exception;

import org.springframework.validation.Errors;

public class ValidationException extends BaseRuntimeException {

	private static final long serialVersionUID = -2405926773714297927L;

	private ErrorType errorType = ErrorType.APPLICATION;

	private Errors errors;

	public ValidationException(Errors errors) {
		this.errors = errors;
	}

	public Errors getErrors() {
		return errors;
	}
}
