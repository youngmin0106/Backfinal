package com.example.finalB.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.finalB.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

}
