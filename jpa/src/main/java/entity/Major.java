package entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name="major_tbl")
@ToString(exclude = "students") // 항상 연관관계가 있는 필드는 toString에서 제외를 시켜줘야한다 : toString 무한루프 돈다.
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="major_id")
    private Long majorid;
    private String name;
    private String category;
    @OneToMany(mappedBy = "major")
    private List<Student> students = new ArrayList<>(); // 값확인? 읽기전용?

    public Major(String name, String category) {
        this.name = name;
        this.category = category;
    }
}
