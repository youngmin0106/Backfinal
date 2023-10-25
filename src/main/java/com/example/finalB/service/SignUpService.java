package com.example.finalB.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.finalB.domain.Member;
import com.example.finalB.domain.RoleType;
import com.example.finalB.repository.MemberRepository;

@Service
public class SignUpService {

	@Autowired
	MemberRepository memberrepository;
	
	public void signup(Member member) {
		
		member.setRole(RoleType.MEMBER);
		
		memberrepository.save(member);
	}
	
	public Member getMember(String id) {
		
		Member member = memberrepository.findById(id).orElseGet(() -> {
			return new Member();
		});
		
		return member;
	}
	
}








