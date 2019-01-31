package com.proxair.exception;

<<<<<<< HEAD
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = -1749672979746392283L;
	
	public NotFoundException() {
	}

	public NotFoundException(String msg) {
		super(msg);
	}
}
=======

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1749672979746392283L;
	
	public NotFoundException() {
	}

	public NotFoundException(String msg) {
		super(msg);
	}

}
>>>>>>> refs/remotes/origin/master
