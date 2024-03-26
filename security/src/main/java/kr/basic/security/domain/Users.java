package kr.basic.security.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Lob
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private RoleUser role;
    //date
//    private LocalDateTime createDate;

    @Builder
    public Users(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = RoleUser.ROLE_USER;
        this.createDate = LocalDateTime.now();
    }
}
