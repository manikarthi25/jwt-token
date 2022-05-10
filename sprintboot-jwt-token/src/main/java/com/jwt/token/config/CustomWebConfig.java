package com.jwt.token.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.jwt.token.dto.RequestMetaData;

@Component
public class CustomWebConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private JwtInterceptorConfig jwtInterceptorConfig;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptorConfig);
	}
	
	@Bean
	@RequestScope
	public RequestMetaData getRequestMetaData() {
		return new RequestMetaData();
	}
	
	@Bean
	public JwtInterceptorConfig jwtInterceptor() {
		return new JwtInterceptorConfig(getRequestMetaData());
	}

}
