package com.example.finalB.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.finalB.domain.Member;
import com.example.finalB.domain.OAuthType;
import com.example.finalB.domain.RoleType;
import com.example.finalB.repository.MemberRepository;

@Service
public class SignUpService {

	@Autowired
	MemberRepository memberrepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void signup(Member member) {
		
		// username, password, email는 입력한 데이터 createDate는 자동입력되기 때문에 회원가입시 Role만 User로 따로 설정
		member.setRole(RoleType.MEMBER);
		
		// 일반 로그인인 경우 OauthType BOARD로 설정
		if(member.getOauth() == null) 
			member.setOauth(OAuthType.BOARD);
		// user.getPassword() // 암호화 하기 전 비밀번호 -> 1234
		// passwordEncoder.encode(user.getPassword()) // 암호화 후 비밀번호 -> !@#!@$%!@#
	
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		
		memberrepository.save(member);
		
	}
	
	public Member getMember(String username) {
		
		Member member = memberrepository.findByUsername(username).orElseGet(() -> {
			return new Member();
		});
		
		return member;
	}
	
	@Transactional
	public Member updateUser(Member member) {
		Member updateUser = memberrepository.findByUsername(member.getUsername()).get();
		
		updateUser.setUsername(member.getUsername());
		updateUser.setPassword(passwordEncoder.encode(member.getPassword()));
		updateUser.setEmail(member.getEmail());
		
		return updateUser;
	}
	
}








