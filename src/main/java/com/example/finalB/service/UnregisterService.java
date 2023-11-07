package com.example.finalB.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.finalB.domain.Member;
import com.example.finalB.repository.MemberRepository;

@Service
public class UnregisterService {

	@Autowired
	private MemberRepository memberRepository;
	
	public void unregisterMember(String id) {
		
		System.out.println(id);
		
		memberRepository.deleteById(id);
	}
}
