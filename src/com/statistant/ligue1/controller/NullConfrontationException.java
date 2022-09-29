package com.statistant.ligue1.controller;

/**
 * Exception thrown the specified confrontation 
 * isn't found in the "confrontations" table from database.
 * @author Thomas CHARMES
 */
public class NullConfrontationException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public NullConfrontationException(String message) {
		super(message);
	}

}
