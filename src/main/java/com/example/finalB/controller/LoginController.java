package com.example.finalB.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.finalB.domain.Member;
import com.example.finalB.service.LoginService;
import com.example.finalB.service.MemberService;

@RestController
public class LoginController {
	
	@Autowired
	private LoginService loginservice;
	
	@Autowired
	private MemberService memberService; 
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Member member) {
		
		String username = member.getUsername();
		String password = member.getPassword();
		
		System.out.println(memberService.getResponseEntity(username, password));
		
//		Member loginIdPw = memberService.getResponseEntity(username, password);
		return memberService.getResponseEntity(username, password);
	
		}
	
	
	@GetMapping("/userInfo")			// 필터쪽에 인증 객체를 만들어놔서 가능
	public ResponseEntity<?> userInfo(Authentication authentication) {
		
		String username = authentication.getName(); //username
		
		Member member = loginservice.getMember(username); // 멤버객체
		
		
		return new ResponseEntity<>(member, HttpStatus.OK);
	}

}






