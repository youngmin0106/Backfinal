package com.example.finalB.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "intrans")
@SequenceGenerator(
	name = "INTRANS_SEQ_GENERATOR",
	sequenceName = "INTRANS_SEQ",
	initialValue = 1, allocationSize = 1)
@Data
public class InTrans {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INTRANS_SEQ_GENERATOR")
	private Integer transId; // 거래중인 게시글 번호
	
	@Column(nullable = false)
	private String transStatus;
		
	private boolean sellerChk;
	
	private boolean buyerChk;
	
	@Column(nullable = false)
	private String sellerId;
	
	@Column(nullable = false)
	private String buyerId;
	
	@Column
	private Integer postId; // 원래 게시글 번호
	
}
