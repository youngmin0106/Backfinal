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

import com.example.finalB.domain.OneToOne;
import com.example.finalB.repository.OnetoOneRepositroy;
import com.example.finalB.service.OnetoOneService;

@RestController
public class OnetoOneController {
	@Autowired
	private OnetoOneService onetoOneService;
	@Autowired
	private OnetoOneRepositroy onetoOneRepositroy;

	@PostMapping("/onetoone") // 게시물추가
	public ResponseEntity<?> insertOnetoone(@RequestBody OneToOne oneToone) {

		onetoOneService.insertOneToone(oneToone);

		return new ResponseEntity<>("1:1문의 작성 완료", HttpStatus.OK);
	}

	@GetMapping("/onetoone") // 게시물리스트
	public ResponseEntity<?> getOnetooneList() {
		List<OneToOne> oneTooneList = onetoOneService.getOneTooneList();

		return new ResponseEntity<>(oneTooneList, HttpStatus.OK);
	}

	@GetMapping("/onetoone/{no}") // 하나게시물만 보기
	public ResponseEntity<?> getOnetoone(@PathVariable Integer no) {
		OneToOne onetoone = onetoOneService.getOnetoOne(no);

		return new ResponseEntity<>(onetoone, HttpStatus.OK);
	}

	@DeleteMapping("/onetoone")
	public ResponseEntity<?> deleteOnetoone(@RequestParam Integer no) {

		onetoOneService.deleteOnetoOne(no);

		return new ResponseEntity<>(no + "번 게시물 삭제 완료", HttpStatus.OK);
	}

	@PutMapping("/onetoone/{no}/update")
	public ResponseEntity<?> updateOnetoone(@RequestBody OneToOne oneToone) {

		onetoOneService.updateOnetoOne(oneToone);

		return new ResponseEntity<>("수정 완료", HttpStatus.OK);
	}

	@PutMapping("/onetoone/{no}/views")
	public ResponseEntity<String> increaseViews(@PathVariable("no") Integer no) {
		Optional<OneToOne> oneTooneOptional = onetoOneRepositroy.findById(no);
		if (oneTooneOptional.isPresent()) {
			OneToOne oneToone = oneTooneOptional.get();
			oneToone.setCnt(oneToone.getCnt() + 1);
			onetoOneRepositroy.save(oneToone);
			return ResponseEntity.ok("조회수가 증가");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
