package kr.boot.basic.service;

import jakarta.transaction.Transactional;
import kr.boot.basic.domain.Member;
import kr.boot.basic.repository.SpringJpaMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberService {
//    @Autowired
//    MemberRepository memberRepository; // 필드 속성값 -> interface 필드주입
    // 생성자로 주입 하는걸 권장한다.
    @Autowired
    private SpringJpaMemberRepository memberRepository;

//    회원가입
    public boolean join(Member member){
        if(!vlidateDuplicateMember(member)){
            memberRepository.save(member);
            return true;
        }else{
            System.out.println("이미 존재하는 회원 입니다.");
            return false;
        }
    }

//    이름 중복검사
    private boolean vlidateDuplicateMember(Member member){
//        memberRepository.findById(member.getId())
//                        .ifPresent(m -> {thorw new IllegalArgumentException("이미 존재하는 회원입니다.");});
        return memberRepository.findByName(member.getName()).isPresent();
    }
    // 전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    // 회원 한명 조회
    public Optional<Member> findOneMember(Long id){
        return memberRepository.findById(id);
    }
}
