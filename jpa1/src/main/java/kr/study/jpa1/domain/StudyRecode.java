package kr.study.jpa1.domain;

import jakarta.persistence.*;
import kr.study.jpa1.form.StudyForm;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@ToString
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyRecode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="study_id")
    private Long id;
    private LocalDate studyDay;
    private LocalTime startTime;
    private int studyMins;
    @Lob
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="memberId")
    private Member member; // fk => member_id
    public void setId(long id) {
        this.id = id;
    }
    //도메인 영향을 미치는 비지니스 로직, 생성자 로직

    //생성자 메서드
    @Builder
    public StudyRecode(LocalDate studyDay, LocalTime startTime, int studyMins, String contents,Member member) {
        this.studyDay = studyDay;
        this.startTime = startTime;
        this.studyMins = studyMins;
        this.contents = contents;
        this.member = member;
    }

    public static StudyRecode createRecode(StudyForm form, Member member){
        StudyRecode recode = new StudyRecode();
        recode.member = member;
        recode.studyDay = form.getStudyDay();
        recode.startTime = form.getStartTime();
        recode.studyMins = form.getStudyMins();
        recode.contents = form.getContents();
        return recode;
    }

    public String getEndStudyDay(){
        String patter = "yyyy-MM-dd HH:mm:ss";
        LocalDateTime endStudyTime = LocalDateTime.of(this.studyDay,this.startTime);
        endStudyTime = endStudyTime.plusMinutes(this.studyMins);
        String data = endStudyTime.format(DateTimeFormatter.ofPattern(patter));
        return data;
    }
    public void update(StudyForm stu) {
        this.studyDay = stu.getStudyDay();
        this.startTime = stu.getStartTime();
        this.studyMins = stu.getStudyMins();
        this.contents = stu.getContents();
    }


}
