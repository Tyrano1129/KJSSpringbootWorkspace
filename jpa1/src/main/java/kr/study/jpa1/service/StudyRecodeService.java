package kr.study.jpa1.service;

import kr.study.jpa1.domain.Member;
import kr.study.jpa1.domain.StudyRecode;
import kr.study.jpa1.form.StudyForm;
import kr.study.jpa1.repository.StudyRecodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudyRecodeService {
    private final StudyRecodeRepository repository;

    public String join(StudyForm stu, Member member){
        StudyRecode recode = StudyRecode.createRecode(stu,member);
        repository.save(recode);
        log.info("study ={}",recode);
        return null;
    }

    public List<StudyRecode> getAllRecode(){
        return repository.selectAll();
    }

    public StudyRecode getOneRecode(Long id){
        return repository.find(id);
    }

    public void recodeUpdate(StudyRecode stu){
        StudyRecode recodeUpdate = repository.find(stu.getId());
        recodeUpdate.update(stu);
        repository.save(recodeUpdate);
        log.info("stu = {}",stu);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
