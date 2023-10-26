package com.example.finalB.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.finalB.domain.Trans;
import com.example.finalB.repository.TransRepository;

@Service
public class TransService {

	@Autowired
	private TransRepository transRepository;
	
	public void insertTrans(Trans trans) {
		transRepository.save(trans);
		
	}
}
