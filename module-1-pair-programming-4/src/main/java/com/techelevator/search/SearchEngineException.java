package com.techelevator.search;

import com.techelevator.util.TELog;

public class SearchEngineException extends Exception {

	public SearchEngineException(String message) {
		super(message);
		TELog.log(message);
	}
}
