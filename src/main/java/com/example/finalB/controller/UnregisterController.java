package com.example.finalB.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.finalB.domain.Member;
import com.example.finalB.service.UnregisterService;

@RestController
public class UnregisterController {
	
	@Autowired
	private UnregisterService unregisterService;
	
	// 회원탈퇴
	@PostMapping("/unregister")
	public void unregister(@RequestBody(required = false) Member member) {
		
		System.out.println(member.getId());
		unregisterService.unregisterMember(member.getId());
		
//		String id = member.getId();
//		System.out.println(id);
        // id를 사용하여 원하는 작업을 수행합니다
//        unregisterService.unregisterMember(id);
	}
	
}
