package com.example.finalB.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.example.finalB.domain.TransType;
import com.example.finalB.service.InTransactionService;
import com.example.finalB.service.TransService;

@RestController
public class IntransController { 
	// 거래 시작됬을때 작동
	
	@Autowired
	private InTransactionService inTransactionService;
	
	@Autowired
	private TransService transService;
	
	@PostMapping("/startTrans")
	public ResponseEntity<?> startTrans(@RequestBody InTrans intrans) {
		
		inTransactionService.startTrans(intrans);
		
		Trans trans = transService.getTrans(intrans.getPostId());
		trans.setTrans(TransType.ING);
		
		transService.updateTrans(trans);
		
		return new ResponseEntity<>("구매요청 완료", HttpStatus.OK);
		
	}
	
	@GetMapping("/intransInfo/{id}")
	public ResponseEntity<?> intransInfo(@PathVariable Integer id) {
		
		// 게시물 정보 보내줌
		Trans trans = transService.getTrans(id);
		InTrans intrans = inTransactionService.getIntrans(id);
		
		Map<String, Object> test = new HashMap<>();
		test.put("trans", trans);
		test.put("intrans", intrans);
		
		System.out.println(test);
		
		return new ResponseEntity<>(test, HttpStatus.OK);
		
	}
	
//	@GetMapping("/intransBuyInfo/{id}")
//	public ResponseEntity<?> intransBuyInfo(@PathVariable Integer id) {
//		
//		InTrans intrans = inTransactionService.getIntransBuyInfo(id);
//		
//		System.out.println(intrans);
//		
//		return null;
//		
//	}

	
	

}
