package kr.study.jpa1.repository;

import jakarta.persistence.EntityManager;
import kr.study.jpa1.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class MemberJpaRepository implements MemberRepository{

    private final EntityManager em;
//    @Autowired
//    public MemberJpaRepository(EntityManager em){
//        this.em = em;
//    }
    @Override
    public Member save(Member member) {
        em.persist(member);
        return null;
    }

    @Override
    public List<Member> findAll() {
                                                    // jpql
        List<Member> list = em.createQuery("select m from Member m",Member.class)
                .getResultList();
        return list;
    }

    @Override
    public Member findById(Long id) {
        Member m = null;
        try {
            m = em.createQuery("select m from Member m where m.id = :id", Member.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }catch(Exception e){
            m = null;
        }
        return m;
    }

    @Override
    public Member findByLoginId(String loginId) {
        Member m = null;
        try {
            m = em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
                    .setParameter("loginId", loginId)
                    .getSingleResult();
        }catch(Exception e){
            m = null;
        }
        return m;
    }

    @Override
    public Member deleteById(Long id) {
        int delcnt = em.createQuery("delete from Member m where m.id=:id")
                .setParameter("id",id)
                .executeUpdate();
        return null;
    }
}
