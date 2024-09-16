package com.techelevator.util;

import java.io.IOException;

public class TELogException extends RuntimeException {

	public TELogException(String message, IOException e) {
		super(message,e);
	}
}
