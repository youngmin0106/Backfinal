package com.example.finalB.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.finalB.DTO.ResponseDTO;
import com.example.finalB.domain.Member;
import com.example.finalB.domain.Reply;
import com.example.finalB.service.ReplyService;

@RestController
public class ReplyController {
	@Autowired
	private ReplyService replyService;

	@PostMapping("/reply/{no}")
	public @ResponseBody ResponseDTO<?> insertReply(@PathVariable int no, Reply reply, HttpSession session) {
		Member member = (Member) session.getAttribute("principal");

		replyService.insertReply(no, reply, member);

		return new ResponseDTO<>(HttpStatus.OK.value(), "댓글 등록 완료");
	}

	@DeleteMapping("/reply/{replyId}")
	public @ResponseBody ResponseDTO<?> deleteReply(@PathVariable int replyId) {

		replyService.deleteReply(replyId);

		return new ResponseDTO<>(HttpStatus.OK.value(), "댓글 삭제 완료");
	}

	@PutMapping("/reply")
	public @ResponseBody ResponseDTO<?> updateReply(@RequestBody Reply reply) {

		replyService.updateReply(reply);

		return new ResponseDTO<>(HttpStatus.OK.value(), "댓글 수정 완료");
	}

}
