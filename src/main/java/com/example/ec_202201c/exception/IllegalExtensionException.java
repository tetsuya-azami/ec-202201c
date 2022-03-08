package com.example.ec_202201c.exception;

public class IllegalExtensionException extends Exception {
	public IllegalExtensionException(String msg) {
		super(msg);
	}

	public IllegalExtensionException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
