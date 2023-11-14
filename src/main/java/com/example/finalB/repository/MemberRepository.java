package com.example.finalB.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.finalB.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

	Optional<Member> findByUsername(String username);
	Optional<Member> findByPhone(String phone);
}
