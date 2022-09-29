package com.statistant.ligue1.controller;

public class IncompatibleEmailException extends Exception {
	private static final long serialVersionUID = 1L;

	public IncompatibleEmailException(String message) {
		super(message);
	}
}
