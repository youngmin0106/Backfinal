package com.example.finalB.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.finalB.domain.InTrans;
import com.example.finalB.domain.Trans;
import com.example.finalB.service.InTransactionService;

@RestController
public class IntransController { 
	// 거래 시작됬을때 작동
	
	@Autowired
	private InTransactionService inTransactionService;
	
	@PostMapping("/startTrans")
	public ResponseEntity<?> startTrans(@RequestBody InTrans intrans) {
		
		inTransactionService.startTrans(intrans);
		
		return new ResponseEntity<>("구매요청 완료", HttpStatus.OK);
		
	}
	
//	@GetMapping("/testTrans")
//	public ResponseEntity<?> getInTrans(@RequestBody String username) {
//		
//		List<Trans> Intrans = inTransactionService.findInTrans(username);
//		
//		return new ResponseEntity<>(Intrans, HttpStatus.OK);
//		
//	}
	
	

}
