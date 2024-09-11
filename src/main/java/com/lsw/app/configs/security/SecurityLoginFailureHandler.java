package com.lsw.app.configs.security;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.lsw.app.members.MemberMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SecurityLoginFailureHandler implements AuthenticationFailureHandler {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		String message = "로그인 실패";
		
		log.error("Exception : {}", exception);
		
		if(exception instanceof BadCredentialsException) {
			//비번이 틀렸음
			message = "비밀번호 확인바랍니다.";
		}else if(exception instanceof InternalAuthenticationServiceException) {
			//아이디가 틀렸음
			message = "존재하지 않는 ID 입니다.";
		}else if(exception instanceof AccountExpiredException) {
			//memberVO의 isAccountNonExpired()의 return 값이 false
			//계정 유효기간 만료
			message = "만료된 계정입니다. 관리자에게 문의 바랍니다.";
		}else if(exception instanceof LockedException) {
			//memberVO의 isAccountNonLocked()의 return 값이 false
			//계정이 잠겨있음
			message = "잠겨있는 계정입니다. 관리자에게 문의 바랍니다.";
		}else if(exception instanceof CredentialsExpiredException) {
			//memberVO의 isCredentialsNonExpired()의 return 값이 false
			//비번의 유효기간이 만료
			message = "비밀번호의 유효기간이 만료되었습니다.";
		}else if(exception instanceof DisabledException) {
			//memberVO의 isEnabled()의 return 값이 false
			//유효하지 않은 사용자입니다
			//휴면계정 처리 같은 경우에 사용
			message = "휴면 계정입니다. 관리자에게 문의 바랍니다.";
		}
		
		message = URLEncoder.encode(message, "UTF-8");
		
		response.sendRedirect("/member/login?message=" + message);
		
	}
	
	

}
