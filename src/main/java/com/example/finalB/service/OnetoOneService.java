package com.example.finalB.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.finalB.domain.OneToOne;
import com.example.finalB.repository.OnetoOneRepositroy;

@Service
public class OnetoOneService {
	@Autowired
	private OnetoOneRepositroy onetoOneRepositroy;

	// 글작성
	public void insertOneToone(OneToOne oneToone) {
		
		onetoOneRepositroy.save(oneToone);
	}
	//게시물 리스트
	public List<OneToOne> getOneTooneList() {

		return onetoOneRepositroy.findAllByOrderByNoDesc();

	}
	
	
	//하나게시물
	public OneToOne getOnetoOne(Integer no) {
		
		return onetoOneRepositroy.findById(no).get();	
	}
	//삭제
	public void deleteOnetoOne(Integer no) {
		
		onetoOneRepositroy.deleteById(no);
	}
	//수정
	public void updateOnetoOne(OneToOne oneToone) {
		OneToOne oriOneToone = (OneToOne)onetoOneRepositroy.findById(oneToone.getNo()).get();
		
		oriOneToone.setTitle(oneToone.getTitle());
		oriOneToone.setContent(oneToone.getContent());
		
		onetoOneRepositroy.save(oriOneToone);
		
	}
}
