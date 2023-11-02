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

    @PostMapping("/idpwserch")
    public ResponseEntity<?> getIdPw(@RequestBody Member member) {
    	
        String phone = member.getPhone();
        System.out.println(phone);
        
        Member findIdPw = memberService.getIdPassword(phone);
     
        return new ResponseEntity<>(findIdPw, HttpStatus.OK);
    }
}
	

