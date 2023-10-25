package com.example.finalB.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.finalB.domain.Member;
import com.example.finalB.service.SignUpService;

@RestController
public class SignUpController {

	@Autowired
	SignUpService signupservice;

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody Member member) {
		
		Member findMemberId = signupservice.getMember(member.getId());

		if (findMemberId.getId() == null) {
			signupservice.signup(member);
			return new ResponseEntity<>( "회원가입 완료", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("이미 가입된 회원임", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/idoverlap")
	public ResponseEntity<?> overLapIdCheck(@RequestBody Member member) {
		
		Member overLapIdchk = signupservice.getMember(member.getId());
		
		if(overLapIdchk.getId() != member.getId()) {
			System.out.println("사용가능 아이디");
			return new ResponseEntity<>("사용가능 아이디", HttpStatus.OK);
		} else {
			System.out.println("중복 아이디");
			return new ResponseEntity<>("사용불가 아이디", HttpStatus.BAD_REQUEST);
		}
		
	}
	
}





