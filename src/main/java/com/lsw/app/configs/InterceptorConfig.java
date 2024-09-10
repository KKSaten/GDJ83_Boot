package com.lsw.app.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.lsw.app.interceptors.AdminCheckInterceptor;
import com.lsw.app.interceptors.LoginInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{
	
	@Autowired
	private LoginInterceptor loginInterceptor;
	
	@Autowired
	private AdminCheckInterceptor admincheckInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//어떤 URL이 왔을 때 어떤 Interceptor를 실행할 것인지 설정
		// /qna/list -> LoginInterceptor를 거쳐가게 하자
		
//		registry.addInterceptor(loginInterceptor)
//				.addPathPatterns("/qna/*")
//				.excludePathPatterns("/qna/list");
//		
//		registry.addInterceptor(admincheckInterceptor)
//				.addPathPatterns("/qna/add");
				
		
	}
	
}
