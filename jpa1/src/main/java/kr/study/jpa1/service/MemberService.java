package kr.study.jpa1.service;

import kr.study.jpa1.domain.Member;
import kr.study.jpa1.repository.MemberJpaRepository;
import kr.study.jpa1.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true) // 읽기전용 트렌젝셔널 -> sql 저장소 빠진거
public class MemberService {
    private final MemberJpaRepository memberRepository;

    @Transactional // 읽기, 쓰기(삭제, 수정)
    public Long join(Member member) throws IllegalStateException{
        vaildCheck(member);
        memberRepository.save(member);
        log.trace("save member = {}",member);
        return null;
    }

    private void vaildCheck(Member Member) throws IllegalStateException{
        if(memberRepository.findByLoginId(Member.getLoginId()) != null){
            throw new IllegalStateException("이미 가입한 회원이 있습니다.");
        }
    }

    public List<Member> memberList() {
        List<Member> list = memberRepository.findAll();
        return list;
    }

    @Transactional
    public int memberDelete(Long id){
        Member m = memberRepository.deleteById(id);
        return 0;
    }

    public Member getMember(String loginId){
        return memberRepository.findByLoginId(loginId);
    }
    public Member getDeleteMember(Long id){
        return memberRepository.findById(id);
    }
}
