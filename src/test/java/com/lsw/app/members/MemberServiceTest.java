package com.lsw.app.members;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class MemberServiceTest {
	
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	void passwordUpdateTest() throws Exception {
		//admin, admin => 1234
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername("admin");
		memberVO.setPassword("admin");
		String newPassword = "1234";
		
		MemberVO check = memberMapper.detail(memberVO);
		
		log.info("MemberVO : {}", memberVO);
		log.info("Check : {}", check);
		
		if(passwordEncoder.matches(memberVO.getPassword(), check.getPassword()) ) { // 실제 비번과 DB에서 가져온 인코딩된 비번을 적어 넣는 순서 중요
			log.info("패스워드가 일치");
		}
		
		
		assertEquals(check.getPassword(), memberVO.getPassword());
		
		
		
	}
	
	
	

//	@Test
//	void test() throws Exception {
//		MemberVO memberVO = new MemberVO();
//		memberVO.setUsername("user");
//		memberVO.setPassword(passwordEncoder.encode("user"));
//		int result = memberMapper.pwUpdate(memberVO);
//		
//		assertEquals(1, result);
//	}

}
