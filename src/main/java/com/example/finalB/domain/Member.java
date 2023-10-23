package com.example.finalB.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

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
	private String id; // 아이디

	@Column(length = 100)
	private String pw; // 비번

	@Column(nullable = false, length = 100, unique = true) // null 값 가질 수 없음, 크기=20, 중복 됨
	private String username; // 이름

	@Column(length = 100, nullable = false)
	private String email; // 이메일

	@Column(length = 13, nullable = false)
	private String phone; // 전화번호

	@Column(length = 100, nullable = false)
	private String address; // 주소

	@Column(length = 100, nullable = false)
	private String detailAddress; // 상세주소

	@Column(length = 6, nullable = false)
	private Integer birthdate; // 생년월일

	// 권한 => USER, ADMIN
	@Enumerated(EnumType.STRING)
	private RoleType role;

	// 소셜로그인 권한 설정
	@Enumerated(EnumType.STRING)
	private OAuthType oauth;

	// 레코드가 생성되는 시점에 자동으로 날짜가 넣어짐 ( = SYSDATE)
	@CreationTimestamp
	private Timestamp createDate;

}
