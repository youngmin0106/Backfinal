package com.example.finalB.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.finalB.domain.Member;
import com.example.finalB.domain.Trans;
import com.example.finalB.service.TransService;

@RestController
public class TransController {

	@Autowired
	private TransService transService;
	
	@PostMapping("/insertTrans")
	public @ResponseBody ResponseEntity<?> insertTrans(@RequestBody Trans trans) {

		transService.insertTrans(trans);
		
		return new ResponseEntity<String>("계정 등록 완료되었습니다.", HttpStatus.OK);
	}
	
	// 계정 판매 목록 
	@GetMapping("/transPost")
	public ResponseEntity<?> transPostList() {
		
		List<Trans> transList = transService.getTransList();
		
		return new ResponseEntity<>(transList, HttpStatus.OK);
		
		
	}
	
	@GetMapping("/transDetail/{id}")
	public ResponseEntity<?> getTrans(@PathVariable Integer id) {
		
		Trans trans = transService.getTrans(id);
		
		return new ResponseEntity<>(trans, HttpStatus.OK);
	}
	
	@PutMapping("/updateTrans")
	public ResponseEntity<?> updateTrans(@RequestBody Trans trans) {
		System.out.println(trans);
		transService.updateTrans(trans);
		
		return new ResponseEntity<String>("수정 완료되었습니다.", HttpStatus.OK);
		
	}
	
	@DeleteMapping("/deleteTrans/{id}")
	public ResponseEntity<?> deleteTrans(@PathVariable Integer id) {
		transService.deleteTrans(id);
		return new ResponseEntity<String>("삭제가 완료되었습니다", HttpStatus.OK);
	}
}
