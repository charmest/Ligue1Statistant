package com.statistant.ligue1.controller;

public class ExpiredMembershipException extends Exception {
	private static final long serialVersionUID = 1L;

	public ExpiredMembershipException(String message) {
		super(message);
	}
}
