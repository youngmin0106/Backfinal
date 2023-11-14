package com.example.finalB.domain;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
@SequenceGenerator(
		name = "REPLY_SEQ_GENERATOR", 
		sequenceName = "REPLY_SEQ", 
		initialValue = 1, allocationSize = 1)
@Table(name = "reply")
public class Reply {
	
	   @Id
	   @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "REPLY_SEQ_GENERATOR")
	   private int id;
	   
	   @Column(nullable = false, length = 200)
	   private String content;
	   
	   @CreationTimestamp
	   @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	   private Timestamp createDate;
	   
	   //하나의 작성자는 하나의 댓글만 작성할수 있나		@OneToOne
	   //하나의 작성자는 여러개의 댓글을 작성할 수 있나   @ManyToOne
	   //여러 작성자는 하나의 댓글만 작성할 수 있나		@OneToMany
	   //여러 작성자는 여러 댓글 작성할수있나			@ManyToMany
	   @ManyToOne(fetch = FetchType.EAGER)
	   @JoinColumn(name = "memberid")
	   private Member member;
	   
	   @JsonBackReference
	   @ManyToOne(fetch = FetchType.EAGER)
	   @JoinColumn(name = "no")
	   private OneToOne onetoone;
}
