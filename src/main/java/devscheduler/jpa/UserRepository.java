package devscheduler.jpa;

import devscheduler.data.DevUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<DevUser, Long> {
    Optional<DevUser> findByName(String name);
}