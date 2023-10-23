package com.example.finalB.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.finalB.domain.Cs;
import com.example.finalB.repository.CsRepository;

@Service
public class NoticeService {

	@Autowired
	private CsRepository csRepository;

	// 글작성
	public void insertNotice(Cs cs) {
		cs.setCnt(0);
		csRepository.save(cs);
	}

	public List<Cs> getNoticeList() {

		return csRepository.findAllByOrderByNoDesc();

	}
}
