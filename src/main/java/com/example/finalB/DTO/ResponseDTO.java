package com.example.finalB.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO<T> {
	
	private int status; // 상태코드
	private T data; // 응답 데이터 (동적 데이터타입) -> 응답데이터가 항상 같지 않아서
}
