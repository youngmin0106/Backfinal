package com.example.finalB.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.finalB.domain.Member;
import com.example.finalB.service.LoginService;

@RestController
public class LoginController {
	
	@Autowired
	LoginService loginservice;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Member member) {
		
		String id = member.getId();
		String password = member.getPw();
		
		Member loginIdPw = loginservice.loginIdPwChk(id, password);
		
		System.out.println(id);
		System.out.println(password);
		
		if (loginIdPw != null) {
			return new ResponseEntity<>("로그인 성공", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("로그인 실패", HttpStatus.BAD_REQUEST);
		}
	}

}



