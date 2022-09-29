package com.statistant.ligue1.controller;

public class NullPasswordException extends Exception {
	private static final long serialVersionUID = 1L;

	public NullPasswordException(String message) {
		super(message);
	}
}
