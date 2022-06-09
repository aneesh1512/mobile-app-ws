package com.webblog.app.ws.mobileappws.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.webblog.app.ws.mobileappws.ui.response.models.UserError;

@ControllerAdvice
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleAnyException(Exception ex,  WebRequest req) {
		UserError error = new UserError(new Date(), ex.getLocalizedMessage());
		return new ResponseEntity<Object>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = 
		{NullPointerException.class,
				UserException.class})
	public ResponseEntity<Object> handleSpecificException(Exception ex,  WebRequest req) {
		UserError error = new UserError(new Date(), ex.getLocalizedMessage());
		return new ResponseEntity<Object>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
}
