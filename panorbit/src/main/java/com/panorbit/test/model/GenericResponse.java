package com.panorbit.test.model;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class GenericResponse<T> extends ResponseEntity<T>{
	
	public GenericResponse(T t, HttpStatusCode status) {
		super(t, status);
	}
}
