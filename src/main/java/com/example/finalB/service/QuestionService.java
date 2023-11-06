package com.example.finalB.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.finalB.domain.Questions;
import com.example.finalB.repository.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	// 글작성
	public void insertQuestion(Questions questions) {
		
		questionRepository.save(questions);
	}
	//게시물 리스트
	public List<Questions> getQuestionList() {

		return questionRepository.findAllByOrderByNoDesc();

	}
	
	
	//하나게시물
	public Questions getQuestion(Integer no) {
		
		return questionRepository.findById(no).get();	
	}
	//삭제
	public void deleteQuestion(Integer no) {
		
		questionRepository.deleteById(no);
	}
	//수정
	public void updateQuestion(Questions questions) {
		Questions oriQuestion = (Questions)questionRepository.findById(questions.getNo()).get();
		
		oriQuestion.setTitle(questions.getTitle());
		oriQuestion.setContent(questions.getContent());
		
		questionRepository.save(oriQuestion);
		
	}
}

