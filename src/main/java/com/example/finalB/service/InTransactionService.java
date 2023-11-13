package com.example.finalB.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.finalB.domain.InTrans;
import com.example.finalB.domain.Member;
import com.example.finalB.domain.Trans;
import com.example.finalB.domain.TransType;
import com.example.finalB.repository.InTransRepository;
import com.example.finalB.repository.TransRepository;

@Service
public class InTransactionService {

	@Autowired
	private InTransRepository inTransRepository; 
	
	
	public void startTrans(InTrans intrans) {
//		trans.setTrans(TransType.READY); // 게시글 등록시 거래대기중으로 설정
		
		intrans.setTrans(TransType.ING);
		inTransRepository.save(intrans);
	}
	
	
	// postId로 해당하는 판매게시글 찾기
	public InTrans getIntrans(Integer id) {
		
		return inTransRepository.findByPostId(id).get();
	}
	
	// username이 Intrans BuyerId랑 같은 Intrans 리스트 뽑기
	public List<InTrans> getIntransBuyInfo(String username) {
		
	    return inTransRepository.findByBuyerId(username);
	}
	
	
	
	@Transactional
	public void changeSellerChk(int id) {
		
		InTrans intrans = inTransRepository.findByPostId(id).get();
		
		intrans.setSellerChk(true);
	}
	
	@Transactional
	public void changeBuyerChk(int id) {
		
		InTrans intrans = inTransRepository.findByPostId(id).get();
		
		intrans.setBuyerChk(true);
	}
	
	@Transactional
	public void sellerAfterTrans(Member member, int price) {
		
		member.setMileage(member.getMileage() + price);
		member.setTransactionPoints(member.getTransactionPoints() + 1);
		
	}
	
	@Transactional
	public void buyerAfterTrans(Member member, int price) {
		
		member.setMileage(member.getMileage() - price);
		member.setTransactionPoints(member.getTransactionPoints() + 1);
	}
	
	@Transactional
	public void IntransDone(InTrans intrans) {
		InTrans oriIntrans = inTransRepository.findById(intrans.getTransId()).get();
		
		oriIntrans.setTrans(TransType.DONE);
	}
}
