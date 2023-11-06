package com.example.finalB.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "member")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
	// 클래스명 -> 테이블명
	// 멤버변수 -> 칼럼명

	@Id // 기본키 컬럼
	private String username; // 아이디

	@Column(length = 100)
	private String password; // 비번

	@Column(length = 100) // 크기=20
	private String name; // 이름

	@Column(length = 100)
	private String email; // 이메일

	@Column(length = 13)
	private String phone; // 전화번호

	@Column(length = 100)
	private String address; // 주소

	@Column(length = 100)
	private String detailAddress; // 상세주소

	private Integer mileage = 0; // 마일리지 (초기값 0)
	
	private Integer transactionPoints = 0; // 거래점수 (초기값 0)

	@Column(length = 8)
	private String birthdate; // 생년월일

	// 권한 => MEMBER, ADMIN
	@Enumerated(EnumType.STRING)
	private RoleType role;

	// 소셜로그인 권한 설정
	@Enumerated(EnumType.STRING)
	private OAuthType oauth;

	// 레코드가 생성되는 시점에 자동으로 날짜가 넣어짐 ( = SYSDATE)
	@Column(updatable = false)
	@CreationTimestamp
	private Timestamp createDate;


}
