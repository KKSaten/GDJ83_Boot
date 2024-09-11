package com.lsw.app.members;

import java.util.Enumeration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("check")
	public void check() throws Exception {
		
	}
	
	//add
	@GetMapping("add")
	public void add() throws Exception {
		
	}
	@PostMapping("add")
	public String add(MemberVO memberVO) throws Exception {
		int result = memberService.add(memberVO);
		
		return "redirect:../";
	}
	
	//login
	@GetMapping("login")
	public String login(String message, Model model) throws Exception {
		model.addAttribute("message", message);
		
		SecurityContext context = SecurityContextHolder.getContext();
		log.info("context : {}", context);
		if(context == null) {
			return "member/login";			
		}
		String user = context.getAuthentication().getPrincipal().toString();
		log.info("user : {}", user);
		if(user.equals("anonymousUser")) {
			return "member/login";
		}
				
		return "redirect:/";
		
	}
	//security에서 진행됨
//	@PostMapping("login")
//	public String login(MemberVO memberVO, HttpSession session) throws Exception {
//		memberVO = memberService.detail(memberVO);
//		
//		if(memberVO != null) {
//			session.setAttribute("member", memberVO);
//		}
//		
//		return "redirect:../";
//	}
	
	
	//security에서 진행됨
//	//logout
//	@GetMapping("logout")
//	public String logout(HttpSession session) throws Exception {
//		
//		session.invalidate();
//		
//		return "redirect:../";
//	}
	
	
	
	@GetMapping("mypage")
	public void mypage(HttpSession session)throws Exception{
		
		Enumeration<String> en = session.getAttributeNames();
		while(en.hasMoreElements()) {
			String name = en.nextElement();
			log.info("Name : {}", name); //SPRING_SECURITY_CONTEXT
		}
		
		Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
		log.info("obj : {}", obj.getClass()); // obj : class org.springframework.security.core.context.SecurityContextImpl
		
		SecurityContextImpl sc = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
		log.info("sc : {}", sc);
		
		SecurityContext context = SecurityContextHolder.getContext();
		log.info("context : {}", context); //위 sc와 동일
		
		
		Authentication authentication = context.getAuthentication();
		log.info("authentication : {}", authentication);
		
		MemberVO memberVO = (MemberVO)authentication.getPrincipal();
		log.info("memberVO : {}", memberVO);
		log.info("Name: {}", authentication.getName());
		
		
		
		
		
		
		
	}
	
	
	
}
