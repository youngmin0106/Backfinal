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
	
		System.out.println(member);
		
		mileageService.chargeMileage(member);
		
		return new ResponseEntity<>("마일리지 충전 완료", HttpStatus.OK);
	}
	
	@PostMapping("/testSellTrans") 
	public ResponseEntity<?> turnOverTest(@RequestBody int id) {
		
		System.out.println(id);
		
		inTransactionService.changeSellerChk(id);
		
		return new ResponseEntity<>("인계 완료", HttpStatus.OK);
	}
	
	@PostMapping("/testBuyTrans")
	public ResponseEntity<?> takeOverTest(@RequestBody int id) {
		
		inTransactionService.changeBuyerChk(id);
		
		
		
		return new ResponseEntity<>("인수 완료", HttpStatus.OK);
	}

}
