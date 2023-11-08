package com.example.finalB.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	// listPage 내가 등록한 물품
	@GetMapping("/listPages1/{username}")
	public List<Trans> sellList(@PathVariable String username) {
		
		List<Trans> userSellList = transService.userTransList(username);
		
		return userSellList;
	}
	
	// listPage 
	
	// listPage 구매중 탭
	@GetMapping("/listPages5/{username}")
	public ResponseEntity<?> BuyList(@PathVariable String username) {
		
		List<InTrans> intrans = inTransactionService.getIntransBuyInfo(username);
	    System.out.println(intrans);
	    
	    List<Integer> postIds = intrans.stream().map(InTrans::getPostId).collect(Collectors.toList());
	    List<Trans> transList = transService.getTransByPostIds(postIds);
	    System.out.println(transList);
	    
	    return new ResponseEntity<>(transList, HttpStatus.OK);
		
	}
}
