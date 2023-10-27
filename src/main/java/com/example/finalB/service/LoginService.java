package com.example.finalB.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.finalB.domain.Member;
import com.example.finalB.repository.MemberRepository;

@Service
public class LoginService {
	
	@Autowired
	MemberRepository memberrepository;
	
	public Member loginIdPwChk(String id, String password) {
		
		Member member = memberrepository.findById(id).orElse(null);

		if (member.getPw().equals(password)) {
			return member;
		}
		return null;
	}
		
}



