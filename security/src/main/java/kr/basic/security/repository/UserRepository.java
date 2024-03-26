package kr.basic.security.repository;

import kr.basic.security.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Long>{
}
