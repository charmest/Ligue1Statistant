package com.statistant.ligue1.controller;

public class AlreadyExistingConfrontationException extends Exception {
	private static final long serialVersionUID = 1L;

	public AlreadyExistingConfrontationException (String message) {
		super(message);
	}
}
