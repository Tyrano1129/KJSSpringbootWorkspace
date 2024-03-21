package entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name="student_tbl")
@ToString(exclude = "major")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="student_id")
    private long studentid;
    private String name;
    private String grade;
                                        // fetch = FetchType.LAZY 지연 로딩
    @ManyToOne(fetch = FetchType.LAZY) // 관계 구성 FetchType.EAGER(): 즉시로딩 : 연관되어있는 모든 값 가지고오기
    @JoinColumn(name="major_id")
    private Major major; // Major 의 Sutudent 가 여러개있다
    // 컬럼명 fk
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="locker_id",unique = true) // default 클래스이름_id 로 생성됨
    private Locker locker; // locker_id 1를 누가 참조하고 잇으면 다른 Student 가 못받는다

    public Student(String name, String grade) {
        this.name = name;
        this.grade = grade;
    }
}
