package com.example.finalB.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.finalB.domain.Trans;
import com.example.finalB.repository.TransRepository;

@Service
public class TransService {

	@Autowired
	private TransRepository transRepository;
	
	public void insertTrans(Trans trans) {
		transRepository.save(trans);
	}
	
	public List<Trans> getTransList() {
		
		return transRepository.findAll();
		
	}


}
