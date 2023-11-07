package com.example.finalB.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.finalB.domain.Member;
import com.example.finalB.domain.OneToOne;
import com.example.finalB.domain.Reply;
import com.example.finalB.repository.OnetoOneRepositroy;
import com.example.finalB.repository.ReplyRepository;

@Service
public class ReplyService {

	@Autowired
	private ReplyRepository replyRepository;
	@Autowired
	private OnetoOneRepositroy onetoOneRepositroy;
	
	   public List<Reply> getReplyByNo(int no) {
	        return replyRepository.findByOnetooneNo(no);
	    }
	
	// 댓글작성
	public void insertReply( Reply reply ,int no , Member member) {

		OneToOne oneToone = onetoOneRepositroy.findById(no).get();

		reply.setOnetoone(oneToone);
	
		replyRepository.save(reply);
	}

	public void deleteReply(int replyId) {

		replyRepository.deleteById(replyId);
	}

	@Transactional
	public void updateReply(Reply reply) {
	
		Reply oriReply = (Reply) replyRepository.findById(reply.getId()).get();
		oriReply.setContent(reply.getContent());

	}

}
