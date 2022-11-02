package com.copapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceFoundException extends RuntimeException{
	
	public final long id;

	public ResourceFoundException(String message, long userId) {
		super(message);
		this.id=userId;
		
	}
	public ResourceFoundException(String message) {
		super(message);
		this.id = 0;
	}

}
	
