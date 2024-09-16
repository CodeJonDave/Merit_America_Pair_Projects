package com.techelevator.search;

import com.techelevator.util.TELog;

public class SearchDomainException extends Exception {

	public SearchDomainException(String message) {
		super(message);
		TELog.log(message);
	}
}
