package dev.patika.app.dao.abstracts;

import dev.patika.app.entity.concretes.InstructorSalaryUpdatedLogger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorSalaryUpdatedLoggerDao extends JpaRepository<InstructorSalaryUpdatedLogger, Long> {
}
