package com.example.finalB.service;

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

	// 댓글작성
	public void insertReply(int no, Reply reply, Member member) {

		OneToOne oneToone = onetoOneRepositroy.findById(no).get();

		reply.setOnetoone(oneToone);
		reply.setMember(member);

		replyRepository.save(reply);
	}

	public void deleteReply(int replyId) {

		replyRepository.deleteById(replyId);
	}

	@Transactional
	public void updateReply(Reply reply) {
		// 어차피 아이디로 리플리 가져오면 거기에 포스트아이디 있으니까 굳이 set할 필요없음
		// Post post = (Post)postRepository.findById(reply.getPost().getId()).get();
		// reply.setPost(post);

		Reply oriReply = (Reply) replyRepository.findById(reply.getId()).get();
		oriReply.setContent(reply.getContent());

	}

}
