package com.task.exception;

public class ResourceNotFoundException extends RuntimeException {
	
	    public ResourceNotFoundException() {
	        super("Resource with this not found in database !!");
	    }

	    public ResourceNotFoundException(String msg) {
	        super(msg);
	    }
	
}
