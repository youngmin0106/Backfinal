package com.example.finalB.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.finalB.domain.Trans;
import com.example.finalB.domain.TransType;

@Repository
public interface TransRepository extends JpaRepository<Trans, Integer>{


	public List<Trans> findByMemberUsernameOrderByIdDesc(String username);
	
	// TransType이 ING인 애들만 뽑기
	List<Trans> findByMemberUsernameAndTrans(String username, TransType transType);

	List<Trans> findByTransAndIdIn(TransType transType, List<Integer> id);

	Long countByMemberUsernameAndTrans(String username, TransType transType); 
}
