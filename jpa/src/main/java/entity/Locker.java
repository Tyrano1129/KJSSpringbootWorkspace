package entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"lockNo"})})
public class Locker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private int lockNo;
    // 관계형 데이터베이스 mysql 에서는 생성이 안된다.
    @OneToOne(mappedBy = "locker")
    private Student student;

    public Locker(int lockNo) {
        this.lockNo = lockNo;
    }
}
