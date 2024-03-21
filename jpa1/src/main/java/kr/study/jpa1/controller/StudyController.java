package kr.study.jpa1.controller;

import kr.study.jpa1.domain.Member;
import kr.study.jpa1.domain.StudyRecode;
import kr.study.jpa1.form.MemberForm;
import kr.study.jpa1.form.StudyForm;
import kr.study.jpa1.repository.StudyRecodeRepository;
import kr.study.jpa1.service.MemberService;
import kr.study.jpa1.service.StudyRecodeService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/study")
@RequiredArgsConstructor
public class StudyController {
    private final MemberService memberService;
    private final StudyRecodeService service;
    @GetMapping
    public String addForm(Model model){
        List<Member> mList = memberService.memberList();
        if(mList == null){
            return "redirect:/member";
        }
        List<MemberForm> list = new ArrayList<>();
        for(Member member : mList){
            MemberForm m = new MemberForm();
            m.setId(member.getLoginId());
            m.setName(member.getName());
            list.add(m);
            log.trace("m = {}",m);
        }
        model.addAttribute("members",list);
        model.addAttribute("curdate", LocalDate.now());
        model.addAttribute("curtime", LocalTime.now());

        return "study/addForm";
    }
    @PostMapping
    public String addStudyRecode(@ModelAttribute StudyForm stu){
        log.trace("study ={}",stu);
        Member m = memberService.getMember(stu.getMemberId());
        if(m == null){
            log.error("{} 아이디가 존재하지않음",m);
            return "redirect:/";
        }
        log.trace("member ={}",m);
        service.join(stu,m);
        return "redirect:/study";
    }

    @GetMapping("/records")
    public String getAllList(Model model){
        List<StudyRecode> list = service.getAllRecode();
        log.trace("list = {}",list);
        model.addAttribute("list",list);
        return  "study/list";
    }

    @GetMapping("/{keyId}")
    public String updateForm(@PathVariable Long keyId,Model model){
        StudyRecode recode = service.getOneRecode(keyId);
        log.trace("recode = {}",recode);
        model.addAttribute("record",recode);
        model.addAttribute("curdate",LocalDate.now());
        return "study/updateForm";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute StudyForm stu,@RequestParam Long id){
        log.trace("stu = {}",stu);
        service.recodeUpdate(stu,id);
        return "redirect:/study/records";
    }

    @DeleteMapping("/{id}")
    public @ResponseBody String delete(@PathVariable Long id){
        System.out.println("id = " + id);
        service.delete(id);
        return "ok";
    }
}
