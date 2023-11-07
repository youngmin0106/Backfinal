package com.example.finalB.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.finalB.domain.Member;
import com.example.finalB.domain.Reply;
import com.example.finalB.service.ReplyService;

@RestController
public class ReplyController {
	@Autowired
	private ReplyService replyService;
	
	 @GetMapping("reply/{no}/list")
	    public ResponseEntity<List<Reply>> getReplyByNo(@PathVariable int no) {
	        List<Reply> reply = replyService.getReplyByNo(no);
	        return new ResponseEntity<>(reply, HttpStatus.OK);
	    }
	
	
	@PostMapping("/reply/{no}")
	public  ResponseEntity<?> insertReply(@PathVariable int no , @RequestBody Reply reply , HttpSession session){
		Member member = (Member)session.getAttribute("principal");

		replyService.insertReply(reply,no,member);
		
		return new ResponseEntity<String>("댓글 등록 완료" ,HttpStatus.OK);
	}
	
	@DeleteMapping("/reply/{replyId}")	
	public ResponseEntity<?> deleteReply(@PathVariable int replyId) {
		
		replyService.deleteReply(replyId);
		
		return new ResponseEntity<String>("댓글 삭제 완료" ,HttpStatus.OK);
	}
	
	@PutMapping("/reply")
	public @ResponseBody ResponseEntity<?> updateReply(@RequestBody Reply reply) {
		
		replyService.updateReply(reply);
		
		return new ResponseEntity<String>("댓글 수정 완료", HttpStatus.OK);
	}
	
	}
	
	

