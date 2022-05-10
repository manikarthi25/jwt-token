package com.jwt.token.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jwt.token.response.APIResponse;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler
	public ResponseEntity<APIResponse> handleAccessDeniedException(AccessDeniedException accessDeniedException) {

		APIResponse apiResponse = new APIResponse();
		apiResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
		return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.UNAUTHORIZED);
	}

}
