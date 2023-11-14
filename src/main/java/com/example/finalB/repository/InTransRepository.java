package com.example.finalB.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.finalB.domain.InTrans;
import com.example.finalB.domain.Member;

@Repository
public interface InTransRepository extends JpaRepository<InTrans, Integer> {

	Optional<InTrans> findByPostId(Integer id);
	
	List<InTrans> findByBuyerId(String username);
	
	List<InTrans> findByTransId(Integer id);
	
}
