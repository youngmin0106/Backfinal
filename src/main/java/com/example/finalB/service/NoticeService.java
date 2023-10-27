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
		
		csRepository.save(cs);
	}
	//게시물 리스트
	public List<Cs> getNoticeList() {

		return csRepository.findAllByOrderByNoDesc();

	}
	
	
	//하나게시물
	public Cs getNotice(Integer no) {
		
		return csRepository.findById(no).get();	
	}
	//삭제
	public void deleteNotice(Integer no) {
		
		csRepository.deleteById(no);
	}
	//수정
	public void updateNotice(Cs cs) {
		Cs oriCs = (Cs)csRepository.findById(cs.getNo()).get();
		
		oriCs.setTitle(cs.getTitle());
		oriCs.setContent(cs.getContent());
		
		csRepository.save(oriCs);
		
	}
}
