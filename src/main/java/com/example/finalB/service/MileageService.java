package com.example.finalB.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.finalB.domain.Member;
import com.example.finalB.repository.MemberRepository;

@Service
public class MileageService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Transactional
	public void chargeMileage(Member member) {
	
		Member newMember = memberRepository.findByUsername(member.getUsername()).get();
	
		newMember.setMileage(member.getMileage());
		
	}
	
	
	
}
