package com.lsw.app.members;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Data;

@Data
public class MemberVO implements UserDetails, OAuth2User {

	private String username;
	private String password;
	private String name;
	private String email;
	private Date birth;
	private boolean enabled; //멤버변수의 타입이 boolean인 경우 
							 //setter의 이름은 set + 변수명(첫글자 대문자)인데, 
							 //getter의 이름은 is + 변수명(첫글자 대문자)가 된다. 
	private List<RoleVO> vos;
	
	//Oaut2User
	//token 정보 저장
	private Map<String, Object> attributes;
	
	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return this.attributes;
	}
	
	
	
	
	
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		for(RoleVO roleVO : vos) {
			GrantedAuthority authority = new SimpleGrantedAuthority(roleVO.getRoleName());
			authorities.add(authority);
		}
		
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
//	public boolean isEnabled() {
//		return true;
//	}
	
	

	
	
	
	
	
	
}
