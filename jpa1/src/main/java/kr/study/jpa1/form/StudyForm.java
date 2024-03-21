package kr.study.jpa1.form;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@ToString
@Builder
public class StudyForm {
    private String memberId;
    private LocalDate studyDay;
    private LocalTime startTime;
    private int studyMins;
    private String contents;
}
