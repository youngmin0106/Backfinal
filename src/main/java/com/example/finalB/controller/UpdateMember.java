package com.example.finalB.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.finalB.domain.Member;
import com.example.finalB.service.MemberService;

@RestController
public class UpdateMember {

	@Autowired
	MemberService memberService;

	@PutMapping("/updateMember")
	public ResponseEntity<?> updateMember(@RequestBody Member member) {
		
		System.out.println(member.toString());
		memberService.updateMember(member);

		return new ResponseEntity<String>("변경된 정보로 로그인 하세요.", HttpStatus.OK);
	}
	
}



