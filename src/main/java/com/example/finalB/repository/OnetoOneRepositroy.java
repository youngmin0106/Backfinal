package com.example.finalB.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.finalB.domain.OneToOne;

@Repository
public interface OnetoOneRepositroy extends JpaRepository<OneToOne, Integer>{
	
	public List<OneToOne> findAllByOrderByNoDesc();
	
}
