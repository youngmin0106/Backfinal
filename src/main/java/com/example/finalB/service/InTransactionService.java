package com.example.finalB.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.finalB.domain.InTrans;
import com.example.finalB.domain.Trans;
import com.example.finalB.domain.TransType;
import com.example.finalB.repository.InTransRepository;
import com.example.finalB.repository.TransRepository;

@Service
public class InTransactionService {

	@Autowired
	private InTransRepository inTransRepository; 
	
	@Autowired
	private TransRepository transRepository;
	
	public void startTrans(InTrans intrans) {
//		trans.setTrans(TransType.READY); // 게시글 등록시 거래대기중으로 설정
		
		intrans.setTrans(TransType.ING);
		inTransRepository.save(intrans);
	}
	
	
//	public List<Trans> findInTrans(String username) {
//		
//	
//	
//	}
	
	
	@Transactional
	public void changeSellerChk(int id) {
		
		InTrans intrans = inTransRepository.findById(id).get();
		
		intrans.setSellerChk(true);
	}
	
	@Transactional
	public void changeBuyerChk(int id) {
		
		InTrans intrans = inTransRepository.findById(id).get();
		
		intrans.setBuyerChk(true);
	}
}
