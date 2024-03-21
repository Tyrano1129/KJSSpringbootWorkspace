package kr.study.jpa1.service;

import com.sun.jdi.LongValue;
import kr.study.jpa1.domain.Member;
import kr.study.jpa1.domain.StudyRecode;
import kr.study.jpa1.form.StudyForm;
import kr.study.jpa1.repository.StudyRecodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudyRecodeService {
    private final StudyRecodeRepository repository;

    @Transactional
    public String join(StudyForm stu, Member member){
        StudyRecode recode = StudyRecode.createRecode(stu,member);
        repository.save(recode);
        log.trace("study ={}",recode);
        return null;
    }

    public List<StudyRecode> getAllRecode(){
        return repository.selectAll();
    }

    public StudyRecode getOneRecode(Long id){
        Optional<StudyRecode> recode = repository.findById(id);
        return recode.isPresent()? recode.get() : null;
    }
    @Transactional
    public void recodeUpdate(StudyForm stu, Long id){
        StudyRecode recodeUpdate = repository.findById(id).get();
        recodeUpdate.update(stu);
        repository.save(recodeUpdate);
        log.trace("stu = {}",stu);
    }
    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }

    @Transactional
    public void memberAllDelete(Member member){
        List<StudyRecode> recodeList = repository.recodeDeleteAll(member);
        log.trace("list = {}",recodeList);
        for(StudyRecode stu : recodeList){
            repository.deleteById(stu.getId());
        }
    }
}
