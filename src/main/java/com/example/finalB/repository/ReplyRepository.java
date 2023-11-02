package com.example.finalB.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.finalB.domain.Reply;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Integer> {

}
