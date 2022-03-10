package com.example.ec_202201c.exception;

public class UserNotFoundException extends Exception {
	public UserNotFoundException(String msg) {
		super(msg);
	}

	public UserNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
