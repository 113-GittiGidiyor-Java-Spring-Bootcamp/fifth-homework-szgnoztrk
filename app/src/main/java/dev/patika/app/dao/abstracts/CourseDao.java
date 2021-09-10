package dev.patika.app.dao.abstracts;

import dev.patika.app.entity.concretes.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDao extends JpaRepository<Course, Long> {
    Course findById(long id);
    boolean existsByCode(String s);
}
