package dev.patika.app.dao.abstracts;

import dev.patika.app.entity.concretes.PermanentInstructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermanentInstructorDao extends JpaRepository<PermanentInstructor, Long> {
    PermanentInstructor findById(long id);
}
