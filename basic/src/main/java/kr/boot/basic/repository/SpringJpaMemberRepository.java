package kr.boot.basic.repository;

import kr.boot.basic.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JpaRepository 는 자동으로 Repository 를 해서 빈이 생성된다.
public interface SpringJpaMemberRepository extends JpaRepository<Member, Long>{
    // JPQL select m from Member m where m.name = ?
    Optional<Member> findByName(String name); // select * from members where name = name;
}