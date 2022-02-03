package com.example.memorybook.repository;

import com.example.memorybook.model.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.nio.file.LinkOption;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
