package com.example.finalB.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.finalB.domain.InTrans;

@Repository
public interface InTransRepository extends JpaRepository<InTrans, Integer> {

}
