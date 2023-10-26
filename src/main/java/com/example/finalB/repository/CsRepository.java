package com.example.finalB.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.finalB.domain.Cs;

@Repository
public interface CsRepository extends JpaRepository<Cs, Integer>{

}
