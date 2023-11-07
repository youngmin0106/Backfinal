package com.example.finalB.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.finalB.domain.InTrans;
import com.example.finalB.domain.Trans;
import com.example.finalB.service.InTransactionService;
import com.example.finalB.service.TransService;

@RestController
public class ListPagesController {

	@Autowired
	private TransService transService;
	
	@Autowired 
	private InTransactionService inTransactionService;
	
	@GetMapping("/listPages1/{username}")
	public List<Trans> sellList(@PathVariable String username) {
		
		List<Trans> userSellList = transService.userTransList(username);
		
		return userSellList;
	}
	
	@GetMapping("/listPages5/{username}")
	public ResponseEntity<?> BuyList(@PathVariable String username) {
		
		InTrans intrans = inTransactionService.getIntransBuyInfo(username);
		
		Trans trans = transService.getTrans(intrans.getPostId());
		
		System.out.println();
		
		return new ResponseEntity<>(trans, HttpStatus.OK);
		
	}
}
