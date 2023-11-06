package com.example.finalB.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.finalB.domain.Member;
import com.example.finalB.service.MemberService;

@RestController
public class GoogleLoginController {
	
	@Autowired
	private MemberService memberService;
	
	@PostMapping("/oauth/google")
	   public ResponseEntity<?> googleLogin(@RequestBody Map<String, String> accessToken) {
	      	
	      // String으로 했기 때문에 accessToken을 뽑아서 보내줌
	      Member member = memberService.googleLogin(accessToken.get("accessToken"));
	      
	      Member findMember = memberService.getMember(member.getUsername());
	      System.out.println(member.getUsername());
	      
	      if(findMember.getUsername() == null) {
	    	  memberService.insertMember(member);
	      }
	      
	      return memberService.getResponseEntity(member.getUsername(), member.getPassword());
	   }
	
}





