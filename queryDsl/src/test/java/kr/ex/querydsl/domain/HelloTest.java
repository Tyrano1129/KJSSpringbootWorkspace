package kr.ex.querydsl.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 통합 테스트 : spring boot 시작처럼 root-ciontext : db 객체 -> em
@Transactional // 트렌지션을 열어야지만 db 에 값 전달 가능 transitiaonal.commit() -> rollback() DB 테스트 때문에 넣어야함 : 테스트만 하고 db값은 저장안된다.
class HelloTest {
    @Autowired
    EntityManager em;

    @Test
    //@Commit // commit 을 하면 rollback 안하고 진짜 db 에 저장된다.
    void firstTest(){
        Hello hello1 = new Hello();
        Hello hello2 = new Hello();
        Hello hello3 = new Hello();
        Hello hello4 = new Hello();
        em.persist(hello1);
        em.persist(hello2);
        em.persist(hello3);
        em.persist(hello4);

        List<Hello> list =  em.createQuery("select h from Hello h").getResultList();

        list.forEach(h -> System.out.println("h = " + h));
    }

    @Test
    void firstQueryDSL(){

        Hello hello1 = new Hello();
        Hello hello2 = new Hello();
        Hello hello3 = new Hello();
        Hello hello4 = new Hello();
        em.persist(hello1);
        em.persist(hello2);
        em.persist(hello3);
        em.persist(hello4);
        System.out.println("--------------------------------");
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QHello h = new QHello("hello");

        List<Hello> result = queryFactory.select(h.hello).from(h.hello).fetch();
        //QHello 클래스 안에 미리 static final hello 객체를 만들어놨다.
        Hello FindHello = queryFactory.selectFrom(QHello.hello).fetchFirst();
        System.out.println("FindHello = " + FindHello);



        result.forEach(h2 -> System.out.println("h2 = " + h2));
    }
}