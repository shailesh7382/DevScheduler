package devscheduler.jpa;

import devscheduler.data.TimeOff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeOffRepository extends JpaRepository<TimeOff, Long> {
}