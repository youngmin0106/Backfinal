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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.finalB.DTO.AfterTransDTO;
import com.example.finalB.domain.InTrans;
import com.example.finalB.domain.Member;
import com.example.finalB.domain.Trans;
import com.example.finalB.domain.TransType;
import com.example.finalB.service.InTransactionService;
import com.example.finalB.service.MemberService;
import com.example.finalB.service.TransService;

@RestController
public class IntransController { 
	// 거래 시작됬을때 작동
	
	@Autowired
	private InTransactionService inTransactionService;
	
	@Autowired
	private TransService transService;
	
	@Autowired
	private MemberService memberService;
	
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
	
	@PostMapping("/sellTrans") 
	public ResponseEntity<?> turnOver(@RequestBody InTrans intrans) {
		
		int id = intrans.getPostId();
		System.out.println("id : " + id);
		
		inTransactionService.changeSellerChk(id);
		
		return new ResponseEntity<>("인계 완료", HttpStatus.OK);
	}
	
	@PostMapping("/buyTrans")
	public ResponseEntity<?> takeOver(@RequestBody AfterTransDTO afterTransDTO) {
		
		int id = afterTransDTO.getId();
		Trans trans = afterTransDTO.getTrans();
		InTrans intrans = afterTransDTO.getIntrans();
		
		// 인수 확인 여부 체크 -> true로 변경
		inTransactionService.changeBuyerChk(id);
		
		// 인수가 끝나면 자동으로 프론트에서 거래완료 처리
		// 판매자 마일리지 + , 구매자 마일리지 -, 양쪽 거래점수 +1, 거래상태 DONE 변경
		
		// 판매자, 구매자 정보 찾기
		Member seller = memberService.getMember(intrans.getSellerId());
		Member buyer = memberService.getMember(intrans.getBuyerId());

		System.out.println("판매자 : " + seller);
		System.out.println("구매자 : " + buyer);

		// 판매자 마일리지 +, (service쪽에서 거래점수도 +1 처리해줌)
		inTransactionService.sellerAfterTrans(seller, trans.getPrice());
		
		// 구매자 마일리지 -, (service쪽에서 거래점수도 +1 처리해줌)
		inTransactionService.buyerAfterTrans(buyer, trans.getPrice());
		
		transService.TransDone(trans);
		inTransactionService.IntransDone(intrans);
		
//		trans.setTrans(TransType.DONE);
		
		
		return new ResponseEntity<>("인수 완료", HttpStatus.OK);
	}
	

}
