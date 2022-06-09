package com.webblog.app.ws.mobileappws.exception;

public class UserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	UserException(){
		super();
	}
	
	UserException(String messages){
		super(messages);
	}
}
