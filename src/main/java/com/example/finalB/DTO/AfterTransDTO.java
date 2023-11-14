package com.example.finalB.DTO;

import com.example.finalB.domain.InTrans;
import com.example.finalB.domain.Trans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AfterTransDTO {

	private Integer id;
	
	private Trans trans;
	
	private InTrans intrans;
}
