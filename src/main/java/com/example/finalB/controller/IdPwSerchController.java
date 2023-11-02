package com.example.finalB.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.finalB.domain.Member;
import com.example.finalB.service.MemberService;

@RestController
public class IdPwSerchController {

	@Autowired
	MemberService memberService;

	@PostMapping("/idserch")
	public ResponseEntity<?> getIdPw(@RequestBody Member member) {

		String phone = member.getPhone();

		Member findIdPw = memberService.getIdPassword(phone);

		return new ResponseEntity<>(findIdPw, HttpStatus.OK);
	}

	@PostMapping("/pwchange")
	public ResponseEntity<?> changePassword(@RequestBody Member member) {

		String phone = member.getPhone();
		String newPassword = member.getPassword();

		System.out.println(phone);
		System.out.println(newPassword);

		Member findMember = memberService.getIdPassword(phone);

		findMember.setPassword(newPassword);
		memberService.updateMember(findMember);

		return new ResponseEntity<>("변경된 비밀번호로 로그인하세요", HttpStatus.OK);

	}
}

