package com.webblog.app.ws.mobileappws.ui.response.models;

import java.util.Date;

public class UserError {

	private Date timeStamp;
	private String errorMessage;
	
	public UserError(Date date, String message){
		timeStamp = date;
		errorMessage = message;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
