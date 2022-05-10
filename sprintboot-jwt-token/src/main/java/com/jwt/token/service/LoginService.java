package com.jwt.token.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwt.token.dto.LoginRequestDTO;
import com.jwt.token.dto.SignUpRequestDTO;
import com.jwt.token.entity.User;
import com.jwt.token.repo.UserRepo;
import com.jwt.token.response.APIResponse;
import com.jwt.token.utils.JwtUtil;

@Service
public class LoginService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private JwtUtil jwtUtil;

	public APIResponse signUp(SignUpRequestDTO signUpRequestDTO) {
		APIResponse apiResponse = new APIResponse();
		User userEO = new User();
		userEO.setName(signUpRequestDTO.getName());
		userEO.setGender(signUpRequestDTO.getGender());
		userEO.setEmailId(signUpRequestDTO.getEmailId());
		userEO.setPassword(signUpRequestDTO.getPassword());
		userEO.setPhoneNumber(signUpRequestDTO.getPhoneNumber());
		userEO.setIsActive(Boolean.TRUE);
		userEO.setCreatedAt(LocalDateTime.now());
		userEO.setUpdatedAt(LocalDateTime.now());
		userEO = userRepo.save(userEO);

		// Generate Token
		String token = jwtUtil.generateJWTToken(userEO);

		Map<String, Object> data = new HashMap<>();
		data.put("accessToken", token);

		apiResponse.setData(data);
		return apiResponse;
	}

	public APIResponse login(LoginRequestDTO loginRequestDTO) {
		APIResponse apiResponse = new APIResponse();

		User user = userRepo.findByEmailIdIgnoreCaseAndPassword(loginRequestDTO.getEmailId(),
				loginRequestDTO.getPassword());
		if (user == null) {
			apiResponse.setData("Login Failed");
			return apiResponse;
		}

		// Generate Token
		String token = jwtUtil.generateJWTToken(user);

		Map<String, Object> data = new HashMap<>();
		data.put("accessToken", token);

		apiResponse.setData(data);
		return apiResponse;
	}

}
