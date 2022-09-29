package com.statistant.ligue1.controller;

public class SameTeamsException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public SameTeamsException(String message) {
		super(message);
	}
}
