package cl.smart.job.exercise.bci.repository;

import cl.smart.job.exercise.bci.repository.entity.LoginAccessEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginAccessRepository extends JpaRepository<LoginAccessEntity, Integer> {
}
