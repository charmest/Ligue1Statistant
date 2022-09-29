package com.statistant.ligue1.controller;

public class NullResourceSelectedException extends Exception {
	private static final long serialVersionUID = 1L;

	public NullResourceSelectedException(String message) {
		super(message);
	}
}
