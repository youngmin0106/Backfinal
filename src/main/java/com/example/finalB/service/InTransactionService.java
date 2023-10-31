package com.example.finalB.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.finalB.domain.InTrans;
import com.example.finalB.repository.InTransRepository;

@Service
public class InTransactionService {

	@Autowired
	private InTransRepository inTransRepository; 
	
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
