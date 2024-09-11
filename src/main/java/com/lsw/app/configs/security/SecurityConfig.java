package com.lsw.app.configs.security;

import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.lsw.app.members.MemberUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private SecurityLoginSuccessHandler successHandler;
	@Autowired
	private SecurityLoginFailureHandler failHandler;
	@Autowired
	private MemberUserService memberUserService;
	
	
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
		
		String message = URLEncoder.encode("로그인 실패", "UTF-8");
		
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
							.requestMatchers("/member/add", "/member/login", "/member/check").permitAll()
							.requestMatchers("/member/*").authenticated()
							.anyRequest().permitAll() 
	
			)//권한 관련 설정 끝
			
			
			//form login 관련 설정
			.formLogin(
					login ->
						login
							//security 자체의 로그인 폼이 아니라 개발자가 만든 로그인 페이지를 사용
							.loginPage("/member/login")
							//.defaultSuccessUrl("/") //로그인에 성공하면 갈 경로 지정
							.successHandler(successHandler)
							//.failureUrl("/member/login?message=" + message)
							.failureHandler(failHandler)
							//아이디를 보내는 파라미터 이름이 'username'이 아니라
							//'id'나 그 외 다른 것으로 사용했을 경우 명시를 해줘야함
							//.usernameParameter("id")
							
							//마찬가지로 비밀번호도 'password'가 아니라
							//'pw'와 같이 해놓았을 경우 명시를 해줘야함 
							//.passwordParameter('pw')
							.permitAll()
					
			)//form login 관련 설정 끝
			
			
			//logout 설정
			.logout(
					logout ->
						logout
							.logoutUrl("/member/logout") 	// 로그아웃 URL 지정 방법(1) 
							//RequestMatcher("url"), 로그아웃 URL 경로 지정 방법(2)
							//.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
							.logoutSuccessUrl("/")			// 로그아웃에 성공하면 갈 경로 지정
							.invalidateHttpSession(true)	// true면 session 만료
							//.deleteCookies("")			// cookie 삭제
							
			)//logout 관련 설정 끝
			
			
			//rememberMe (자동로그인) 설정
			.rememberMe(
					remember ->
						remember
							.rememberMeParameter("rememberMe")				// 파라미터 이름
							.tokenValiditySeconds(60)						// token의 유효 시간
							.key("rememberMe")								// token 생성시 사용되는 값, 필수값, 개발자가 값 설정
							.userDetailsService(memberUserService)			// 인증절차(로그인) 진행할 UserDetailService
							.authenticationSuccessHandler(successHandler)	// 로그인이 성공했을 경우 진행할 Handler
							.useSecureCookie(false)
							
					
			)//rememberMe 설정 끝
			
			
			//동시 세션
			.sessionManagement(
					sessionManager ->
						sessionManager
							.maximumSessions(1)					// 동시 접속 최대 허용 갯수, -1이면 무한대
							.maxSessionsPreventsLogin(false)	// false라면 동시접속시에 기존사용자를 만료시킴, true라면 새로운 접속자 인증 실패
							.expiredUrl("/member/check")		// session이 만료되었을 경우 리다이렉트할 URL
							
							
			)
			
			
			;
		
		
		

		
		
		return security.build();
		
	}
	
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
}
