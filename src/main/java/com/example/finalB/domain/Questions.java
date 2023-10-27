package com.example.finalB.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@Table(name = "Questions")
@SequenceGenerator(
		name = "QUESTIONS_SEQ_GENERATOR", 
		sequenceName = "QUESTIONS_SEQ", 
		initialValue = 1, allocationSize = 1)
@AllArgsConstructor //자주묻는 질문
public class Questions {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "QUESTIONS_SEQ_GENERATOR")
	private Integer no; // 게시글 번호

	@Column(nullable = false, length = 100)
	private String title; // 게시글 제목

	@Lob
	@Column(nullable = false)
	private String content; // 내용

	@ColumnDefault("SYSDATE")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date creationDate;
	
	private int cnt; // 조회수

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "memberid")
	private Member member;
}
