package com.example.finalB.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.finalB.domain.Trans;
import com.example.finalB.service.TransService;

@RestController
public class TransController {

	@Autowired
	private TransService transService;
	
	@PostMapping("/insertTrans")
	public @ResponseBody ResponseEntity<?> insertTrans(@RequestBody Trans trans) {
		
		transService.insertTrans(trans);
		
		System.out.println(trans);
		return new ResponseEntity<String>("계정 등록 완료되었습니다.", HttpStatus.OK);
	}
	
	// 계정 판매 목록 
	@GetMapping("/transPost")
	public ResponseEntity<?> transPostList() {
		
		List<Trans> transList = transService.getTransList();
		
		//System.out.println(transList);	// 잘나옴 member만 빼고
		
		return new ResponseEntity<>(transList, HttpStatus.OK);
		
		
	}
}
