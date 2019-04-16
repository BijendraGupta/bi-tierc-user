package com.institute.bi.tierc.user.exception;

import java.util.Arrays;
import org.springframework.http.HttpStatus;

/**
 * Base custom exception. Extend this to make your own.
 */

public abstract class BaseRuntimeException extends RuntimeException implements BaseException {
	private static final long serialVersionUID = -389795201542223475L;
	private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	private ErrorType errorType = ErrorType.APPLICATION;
	private Object[] args = null;

	/**
	 * No message
	 */
	public BaseRuntimeException() {
		super();
	}

	/**
	 * @param args Arguments for use with the message
	 */
	public BaseRuntimeException(Object... args) {
		super();
		this.args = args;
	}

	/**
	 * @param t    throwable exception
	 * @param args Arguments for use with the message
	 */
	public BaseRuntimeException(Throwable t, Object... args) {
		super(t);
		this.args = args;
	}

	/**
	 * @return Http Status code for the exception
	 */
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public ErrorType getErrorType() {
		return errorType;
	}

	public void setErrorType(ErrorType errorType) {
		this.errorType = errorType;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		if (args != null) {
			this.args = Arrays.copyOf(args, args.length);
		} else {
			this.args = null;
		}
	}

}
