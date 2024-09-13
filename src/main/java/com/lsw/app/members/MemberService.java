package com.lsw.app.members;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public int add(MemberVO memberVO) throws Exception {
		
		memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
		
		int result = memberMapper.add(memberVO);
		
		Map<String, Object> map = new HashMap<>();
		map.put("username", memberVO.getUsername());
		map.put("roleNum", 1);
		
		result = memberMapper.addRole(map);
		
		return result;
	}
	
	
	public MemberVO detail(MemberVO memberVO) throws Exception {
		
		MemberVO result = memberMapper.detail(memberVO);
		if(result != null) {
			if(result.getPassword().equals(memberVO.getPassword())) {
				return result;
			}
		}
		
		return null;
	}
	
	
	
	
	
	

}
