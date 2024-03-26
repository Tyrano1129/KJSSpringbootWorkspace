package kr.ex.querydsl.repository;

import kr.ex.querydsl.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepositroy extends JpaRepository<Member,Long>, MemberCusRepository{
    List<Member> findByUsername(String name);
}
