package dev.patika.app.dao.abstracts;

import dev.patika.app.entity.concretes.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorDao extends JpaRepository<Instructor, Long> {
    Instructor findById(long id);
    boolean existsByPhoneNumber(String s);
}
