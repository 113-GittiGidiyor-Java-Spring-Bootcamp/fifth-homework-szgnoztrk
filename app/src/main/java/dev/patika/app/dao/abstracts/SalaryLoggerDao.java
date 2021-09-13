package dev.patika.app.dao.abstracts;

import dev.patika.app.entity.concretes.InstructorSalaryUpdatedLogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface SalaryLoggerDao extends JpaRepository<InstructorSalaryUpdatedLogger, Long> {
    @Query("SELECT log " +
            "FROM InstructorSalaryUpdatedLogger log " +
            "WHERE log.instructorId = COALESCE(?1, log.instructorId) AND log.logDate = COALESCE(?2, log.logDate) ")
    List<InstructorSalaryUpdatedLogger> getAllByInstructorIdOrLogDateIsLike(Long id, LocalDate date);
}
