package com.panorbit.test.exceptionhandler;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@Component
public class ParseError {
	
	private final Logger logger = Logger.getLogger(getClass());
	
	public JsonArray parseConstrainVoilationExceptionMessage(ConstraintViolationException exception) {
		logger.info("Inside the parseConstrainVoilationExceptionMessage::");
		Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
		final JsonArrayBuilder jsonArray = Json.createArrayBuilder();
		for (var voilation: constraintViolations) {
			jsonArray.add(voilation.getMessage());
		}
		logger.info("Error message is "+jsonArray.build().toString());
		return jsonArray.build();
	}

	public JsonArray parseErrorAsArray(List<ObjectError> allErrors) {
		final JsonArrayBuilder jsonArray = Json.createArrayBuilder();
		for (var arr : allErrors) {
			jsonArray.add(arr.getObjectName());
		}
		return jsonArray.build();
	}
}
