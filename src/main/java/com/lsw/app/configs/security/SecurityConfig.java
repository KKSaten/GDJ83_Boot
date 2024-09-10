package com.lsw.app.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() throws Exception {
		return web -> web
						.ignoring()
						.requestMatchers("/images/**")
						.requestMatchers("/css/**")
						.requestMatchers("/js/**")
						.requestMatchers("/vender/**")
						.requestMatchers("/favicon/**");
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
		
		security
				.cors()
				.and()
				.csrf()
				.disable();
		
		security
			//권한에 관련된 설정
			.authorizeHttpRequests(
					(authorizeRequest) ->
						authorizeRequest
							.requestMatchers("/").permitAll()
							.requestMatchers("/qna/list").permitAll()
							.requestMatchers("/qna/*").authenticated()
							.requestMatchers("/notice/list", "/notice/detail").permitAll()
							.requestMatchers("/notice/*").hasRole("ADMIN")
							.requestMatchers("/manager/*").hasAnyRole("MANAGER", "ADMIN")
							.requestMatchers("/member/add", "/member/login").permitAll()
							.requestMatchers("/member/*").authenticated()
							.anyRequest().permitAll() 
	
			)//권한 관련 설정 끝
			
			
			//form login 관련 설정
			.formLogin(
					login ->
						login
							.loginPage("/member/login")
							.defaultSuccessUrl("/")
							.failureUrl("/member/login")
							//아이디를 보내는 파라미터 이름이 'username'이 아니라
							//'id'나 그 외 다른 것으로 사용했을 경우 명시를 해줘야함
							//.usernameParameter("id")
							
							//마찬가지로 비밀번호도 'password'가 아니라
							//'pw'와 같이 해놓았을 경우 명시를 해줘야함 
							//.passwordParameter('pw')
							.permitAll()
					
			);
		
		
		

		
		
		return security.build();
		
	}
	
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
}
