package com.example.finalB.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.finalB.domain.Cs;
import com.example.finalB.repository.CsRepository;
import com.example.finalB.service.NoticeService;



@RestController
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	@Autowired
	private CsRepository csRepository;
	
	@PostMapping("/notice") //게시물추가
	public ResponseEntity<?> insertNotice(@RequestBody Cs cs){
		
		noticeService.insertNotice(cs);
			
		return new ResponseEntity<>("공지사항 작성 완료" , HttpStatus.OK);
	}
	
	@GetMapping("/notice") //게시물리스트
	public ResponseEntity<?> getNoticeList(){
		List<Cs> noticeList = noticeService.getNoticeList();
		
		return new ResponseEntity<>(noticeList,HttpStatus.OK);
	}
	@GetMapping("/notice/{no}") //하나게시물만 보기
	public ResponseEntity<?> getNotice(@PathVariable Integer no){
		Cs cs = noticeService.getNotice(no);
		
		return new ResponseEntity<>(cs,HttpStatus.OK);
	}
	@DeleteMapping("/notice")
	public ResponseEntity<?> deleteNotice(@RequestParam Integer no){

		noticeService.deleteNotice(no);
		
		return new ResponseEntity<>(no + "번 게시물 삭제 완료",HttpStatus.OK);
	}
	
	@PutMapping("/notice/{no}/update")
	public ResponseEntity<?> updateNotice(@RequestBody Cs cs){
		
		noticeService.updateNotice(cs);
		
		return new ResponseEntity<>("수정 완료" , HttpStatus.OK);
	}
	
	
	
	@PutMapping("/notice/{no}/views")
    public ResponseEntity<String> increaseViews(@PathVariable("no") Integer no) {
        Optional<Cs> noticeOptional = csRepository.findById(no);
        if (noticeOptional.isPresent()) {
            Cs cs = noticeOptional.get();
            cs.setCnt(cs.getCnt()+1);
            csRepository.save(cs);
            return ResponseEntity.ok("조회수가 증가");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
	
	

	  
	}


