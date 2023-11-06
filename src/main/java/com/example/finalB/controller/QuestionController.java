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

import com.example.finalB.domain.Questions;
import com.example.finalB.repository.QuestionRepository;
import com.example.finalB.service.QuestionService;

@RestController
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	@Autowired
	private QuestionRepository questionRepository;

	@PostMapping("/questions") // 게시물추가
	public ResponseEntity<?> insertNotice(@RequestBody Questions questions) {

		questionService.insertQuestion(questions);

		return new ResponseEntity<>("자주묻는질문 작성 완료", HttpStatus.OK);
	}

	@GetMapping("/questions") // 게시물리스트
	public ResponseEntity<?> getquestionList() {
		List<Questions> questionList = questionService.getQuestionList();

		return new ResponseEntity<>(questionList, HttpStatus.OK);
	}

	@GetMapping("/questions/{no}") // 하나게시물만 보기
	public ResponseEntity<?> getquestion(@PathVariable Integer no) {
		Questions questions = questionService.getQuestion(no);

		return new ResponseEntity<>(questions, HttpStatus.OK);
	}

	@DeleteMapping("/questions")
	public ResponseEntity<?> deleteQuestion(@RequestParam Integer no) {

		questionService.deleteQuestion(no);

		return new ResponseEntity<>(no + "번 게시물 삭제 완료", HttpStatus.OK);
	}

	@PutMapping("/questions/{no}/update")
	public ResponseEntity<?> updateNotice(@RequestBody Questions questions) {

		questionService.updateQuestion(questions);

		return new ResponseEntity<>("수정 완료", HttpStatus.OK);
	}

	@PutMapping("/questions/{no}/views")
	public ResponseEntity<String> increaseViews(@PathVariable("no") Integer no) {
		Optional<Questions> questionOptional = questionRepository.findById(no);
		if (questionOptional.isPresent()) {
			Questions questions = questionOptional.get();
			questions.setCnt(questions.getCnt() + 1);
			questionRepository.save(questions);
			return ResponseEntity.ok("조회수가 증가");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
