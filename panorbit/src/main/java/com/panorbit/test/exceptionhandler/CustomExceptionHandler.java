package com.panorbit.test.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.panorbit.test.model.ErrorResponse;
import com.panorbit.test.model.GenericResponse;
import com.panorbit.test.model.GenericResponseModel;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class CustomExceptionHandler {

	@Autowired
	private ParseError parseError;

	@ExceptionHandler(InvalidRequestException.class)
	public GenericResponse<GenericResponseModel<Object, ErrorResponse>> invalidDate(InvalidRequestException ex) {
		return new GenericResponse<GenericResponseModel<Object, ErrorResponse>>(
				new GenericResponseModel<Object, ErrorResponse>(null, new ErrorResponse(ex.getMessage())),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidDefinitionException.class)
	public GenericResponse<GenericResponseModel<Object, ErrorResponse>> validationFailed(
			InvalidDefinitionException ex) {
		return new GenericResponse<GenericResponseModel<Object, ErrorResponse>>(
				new GenericResponseModel<Object, ErrorResponse>(null, new ErrorResponse(ex.getOriginalMessage())),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public GenericResponse<GenericResponseModel<Object, ErrorResponse>> requestBodyValidation(
			MethodArgumentNotValidException ex) {
		return new GenericResponse<GenericResponseModel<Object, ErrorResponse>>(
				new GenericResponseModel<Object, ErrorResponse>(null, new ErrorResponse(ex.getBody().getDetail())),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public GenericResponse<GenericResponseModel<Object, ErrorResponse>> constraintVoilationException(
			ConstraintViolationException ex) {
		return new GenericResponse<GenericResponseModel<Object, ErrorResponse>>(
				new GenericResponseModel<Object, ErrorResponse>(null,
						new ErrorResponse(parseError.parseConstrainVoilationExceptionMessage(ex).toString())),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoDataFoundException.class)
	public GenericResponse<GenericResponseModel<Object, ErrorResponse>> noDataFoundException(
			NoDataFoundException ex) {
		return new GenericResponse<GenericResponseModel<Object, ErrorResponse>>(
				new GenericResponseModel<Object, ErrorResponse>(null, new ErrorResponse(ex.getMessage())),
				HttpStatus.BAD_REQUEST);
	}
}
