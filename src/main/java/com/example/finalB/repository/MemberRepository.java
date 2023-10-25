package com.example.finalB.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.finalB.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

}
