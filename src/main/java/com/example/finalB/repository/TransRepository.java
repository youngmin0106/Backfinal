package com.example.finalB.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.finalB.domain.Trans;

@Repository
public interface TransRepository extends JpaRepository<Trans, Integer>{

}
