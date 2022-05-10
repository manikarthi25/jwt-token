package com.jwt.token.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.token.dto.LoginRequestDTO;
import com.jwt.token.dto.RequestMetaData;
import com.jwt.token.dto.SignUpRequestDTO;
import com.jwt.token.response.APIResponse;
import com.jwt.token.service.LoginService;
import com.jwt.token.utils.JwtUtil;

@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private RequestMetaData requestMetaData;

	@PostMapping("/signup")
	public ResponseEntity<APIResponse> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {

		APIResponse apiResponse = loginService.signUp(signUpRequestDTO);
		return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.OK);

	}

	@PostMapping("/login")
	public ResponseEntity<APIResponse> login(@RequestBody LoginRequestDTO loginRequestDTO) {

		APIResponse apiResponse = loginService.login(loginRequestDTO);
		return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.OK);

	}
/*
	@GetMapping("/privateApi")
	public ResponseEntity<APIResponse> privateApi(
			@RequestHeader(value = "authorization", defaultValue = "") String auth) throws Exception {

		APIResponse apiResponse = new APIResponse();

		System.out.println(auth);
		jwtUtil.verifyToken(auth);

		apiResponse.setData("This is private API");
		return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.OK);
		// return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
*/	
	@GetMapping("/privateApi")
	public ResponseEntity<APIResponse> privateApi2() throws Exception {

		APIResponse apiResponse = new APIResponse();
		System.out.println(requestMetaData.getUserName());
		apiResponse.setData("This is private API");
		return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.OK);
		// return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

}
