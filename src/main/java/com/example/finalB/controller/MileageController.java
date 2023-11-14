package com.example.finalB.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.finalB.domain.Member;
import com.example.finalB.repository.MemberRepository;
import com.example.finalB.service.InTransactionService;
import com.example.finalB.service.MileageService;

@RestController
public class MileageController {

	@Autowired
	private InTransactionService inTransactionService;
	
	@Autowired
	private MileageService mileageService;
	
	@PostMapping("/payCultureland")
	public ResponseEntity<?> payCultureland(@RequestBody Member member) {
		
		mileageService.chargeMileage(member);
		
		return new ResponseEntity<>("마일리지 충전 완료", HttpStatus.OK);
	}
	
	

}
