package devscheduler.jpa;

import devscheduler.data.DevUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<DevUser, Long> {
    DevUser findById(long id);
}