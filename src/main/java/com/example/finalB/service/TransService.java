package com.example.finalB.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.finalB.domain.Member;
import com.example.finalB.domain.RoleType;
import com.example.finalB.domain.Trans;
import com.example.finalB.domain.TransType;
import com.example.finalB.repository.TransRepository;

@Service
public class TransService {

	@Autowired
	private TransRepository transRepository;
	
	// 게시물 등록
	public void insertTrans(Trans trans) {
		trans.setTrans(TransType.READY); // 게시글 등록시 거래대기중으로 설정
		transRepository.save(trans);
	}
	
	// 전체 게시물 리스트
	public List<Trans> getTransList() {
		
		return transRepository.findAllByOrderByIdDesc();
	}
	
	// 해당 게시물 정보
	public Trans getTrans(Integer id) {
		 
		return transRepository.findById(id).get();
	}
	
	// 해당 게시물 수정
	public void updateTrans(Trans trans) {
		Trans originalTrans = transRepository.findById(trans.getId()).get();
		
		originalTrans.setTitle(trans.getTitle());
		originalTrans.setContent(trans.getContent());
		originalTrans.setPrice(trans.getPrice());
		
		transRepository.save(originalTrans);
	}
	// 해당 게시물 삭제
	public void deleteTrans(Integer id) {
		transRepository.deleteById(id);
	}
	
	// 해당 유저가 등록한 게시물 목록 가져오기
	public List<Trans> userTransList(String username) {
		
		return transRepository.findByMemberUsernameOrderByIdDesc(username);
		
	}
	
	// 해당 유저가 등록한 게시물중 TransType이 ING(거래중)인 애들만 가져오기
	public List<Trans> transINGList(String username) {
		
		return transRepository.findByMemberUsernameAndTrans(username, TransType.ING);
	}
	
	public List<Trans> transDONEList(String username) {
		
		return transRepository.findByMemberUsernameAndTrans(username, TransType.DONE);
	}
	
	
	
	// 게시글 정보들 postId로 List 가져오기
	public List<Trans> getTransByPostIds(List<Integer> postIds) {
	    return transRepository.findAllById(postIds);
	}
	
	// 거래 완료 후 게시물 거래상태 수정
	@Transactional
	public void TransDone(Trans trans) {
		Trans oriTrans = transRepository.findById(trans.getId()).get();
		
		oriTrans.setTrans(TransType.DONE);
	}
	
	public Long countList(String username, TransType transType) {
		
		return transRepository.countByMemberUsernameAndTrans(username, transType);
	
	}
	
}
