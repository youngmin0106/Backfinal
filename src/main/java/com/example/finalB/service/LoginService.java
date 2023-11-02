package com.example.finalB.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.finalB.domain.Member;
import com.example.finalB.repository.MemberRepository;

@Service
public class LoginService {
	
	@Autowired
	MemberRepository memberrepository;
	
	public Member loginIdPwChk(String username, String password) {
		
		Member member = memberrepository.findByUsername(username).orElse(null);

		if (member.getPassword().equals(password)) {
			return member;
		}
		return null;
	}
	
//	public String userInfo() {
//		String username = memberrepository.findByUsername().get();
//	}
//		
}



