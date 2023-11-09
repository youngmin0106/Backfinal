package com.example.finalB.domain;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name = "trans")
@SequenceGenerator(
		name = "TRANS_SEQ_GENERATOR", 
		sequenceName = "TRANS_SEQ", 
		initialValue = 1, allocationSize = 1)
@Data
public class Trans {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRANS_SEQ_GENERATOR")
	private Integer id;	// 거래게시물번호
	
	@Column(nullable = false, length = 100)
	private String title;	// 제목
	
	@Column(nullable = false, length = 100)
	private String content;	// 내용
	
	@Column(nullable = false, length = 100)
	private Integer price;	// 가격
	
	@Column(nullable = false, length = 100)
	private String server; // 서버
	
	@Column(nullable = false, length = 100)
	private String game; // 게임명
	
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Timestamp createdate; // 게시글 등록 일자
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "memberid") // 외래키 어노테이션 (외래키이름)
	private Member member; // 작성자
	
	@Enumerated(EnumType.STRING)
	private TransType trans;
	
	
}
