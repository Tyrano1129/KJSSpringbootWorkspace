package kr.study.jpa1.controller;

import kr.study.jpa1.domain.Member;
import kr.study.jpa1.form.MemberForm;
import kr.study.jpa1.service.MemberService;
import kr.study.jpa1.service.StudyRecodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;
    private final StudyRecodeService recodeService;

    @GetMapping
    public String joinForm(){
        return "member/joinForm";
    }

    @PostMapping
    public String joinMember(@ModelAttribute MemberForm form){
        System.out.println("memberForm = " + form);
        log.debug("memberForm = {}" ,form);
        Member member = Member.builder()
                .loginId(form.getId())
                .password(form.getPw())
                .name(form.getName())
                .build();
        log.trace("member = {}" ,member);
        try {
            service.join(member);
        }catch(Exception e){
            log.error("errMSG={}","이미 존재하는 회원 아이디가 있습니다.");
            return "redirect:/";
        }

        return "redirect:/";
    }

    @GetMapping("/members")
    public String memberListForm(Model model){
        List<Member> members = service.memberList();
        model.addAttribute("members",members);
        return "member/memberList";
    }

    @GetMapping("/delete/{keyId}")
    public String memberDelete(@PathVariable Long keyId){
        recodeService.memberAllDelete(service.getDeleteMember(keyId));
        int cnt = service.memberDelete(keyId);
        System.out.println("keyId = " + cnt);
        return "redirect:/member/members";
    }
    @DeleteMapping("/{id}")
    public @ResponseBody String memberDelete2(@PathVariable Long id){
        System.out.println("id = " + id);
        recodeService.memberAllDelete(service.getDeleteMember(id));
        service.memberDelete(id);
        return 0+"";
    }
}
