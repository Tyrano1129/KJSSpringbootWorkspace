package kr.ex.querydsl.controller;

import kr.ex.querydsl.dto.MemberSearchCond;
import kr.ex.querydsl.dto.MemberTeamDto;
import kr.ex.querydsl.entity.Member;
import kr.ex.querydsl.repository.MemberRepositroy;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepositroy memberRepository;


    @GetMapping("/home")
    public String home(){
        return "홈이야";
    }


    @GetMapping("/members")
    public List<Member> allMembers(){
       return null;//memberRepository.findAll_QueryDsl();
    }
    @PostMapping("/member")
    public String addMember(@RequestBody Member member){
        memberRepository.save(member);
        return "회원 생성 완료!";
    }

    @GetMapping("/member")
    public String findByuUsername(@RequestParam(name="username") String username){
        System.out.println("username = " + username);
        Member findMember = null;
        if(memberRepository.findByUsername(username).size() >0){
            findMember = memberRepository.findByUsername(username).get(0);
        }
        return findMember != null? findMember.toString() : "없다";
    }
    @GetMapping("/member/{id}")
    public String find(@PathVariable(name="id") Long id){
        Optional<Member> findMember = memberRepository.findById(id);
        return findMember.isEmpty()? "해당 번호 회원없음" : findMember.toString();
    }

    @GetMapping("/member/search")
    public List<MemberTeamDto> searchMember(MemberSearchCond condition){
        return memberRepository.searchByBuilder(condition);
    }
    @GetMapping("/member/search2")
    public List<MemberTeamDto> searchMember2(MemberSearchCond condition){
        return memberRepository.search(condition);
    }

    @GetMapping("/member/page1")
    public Page<MemberTeamDto> searchMember3(MemberSearchCond conditon, Pageable pageable){
        System.out.println("========================== paging 1 ===================");
        return memberRepository.searchPageSimple(conditon, pageable);
    }

    @GetMapping("/member/page2")
    public Page<MemberTeamDto> searchMember4(MemberSearchCond conditon, Pageable pageable){
        System.out.println("========================== paging 2 ===================");
        return memberRepository.searchPageComplex(conditon, pageable);
    }

}
