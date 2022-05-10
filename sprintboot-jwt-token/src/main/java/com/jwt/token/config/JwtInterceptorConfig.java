package com.jwt.token.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jwt.token.dto.RequestMetaData;
import com.jwt.token.utils.JwtUtil;

import io.jsonwebtoken.Claims;

@Component
public class JwtInterceptorConfig extends HandlerInterceptorAdapter {

	@Autowired
	private JwtUtil jwtUtil;

	private RequestMetaData requestMetaData;

	public JwtInterceptorConfig(RequestMetaData requestMetaData) {
		this.requestMetaData = requestMetaData;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println(request.getRequestURI());

		String authorization = request.getHeader("authorization");
		System.out.println("--------Verify Token--------");

		if (!(request.getRequestURI().contains("login") || request.getRequestURI().contains("signup"))) {
			Claims claims = jwtUtil.verifyToken(authorization);

			requestMetaData.setUserId(Long.valueOf(claims.getIssuer()));
			requestMetaData.setUserName(claims.get("name").toString());
		}
		return super.preHandle(request, response, handler);
	}

}
