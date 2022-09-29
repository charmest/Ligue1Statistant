package com.statistant.ligue1.controller;

/**
 * Exception thrown the specified statstic 
 * isn't found in the table from database.
 * @author Thomas CHARMES
 */
public class NullStatisticException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public NullStatisticException(String message) {
		super(message);
	}
}
