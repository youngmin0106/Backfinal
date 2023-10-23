package com.example.finalB.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.finalB.domain.Cs;
import com.example.finalB.service.NoticeService;


@RestController
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@PostMapping("/notice")
	public ResponseEntity<?> insertNotice(@RequestBody Cs cs){
		
		noticeService.insertNotice(cs);
			
		return new ResponseEntity<>("공지사항 작성 완료" , HttpStatus.OK);
	}
	
	@GetMapping("/notice")
	public ResponseEntity<?> getNoticeList(){
		List<Cs> noticeList = noticeService.getNoticeList();
		
		return new ResponseEntity<>(noticeList,HttpStatus.OK);
	}
}
