package dev.patika.app.dao.abstracts;

import dev.patika.app.entity.concretes.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDao extends JpaRepository<Course, Long> {
    Course findById(long id);
    @Query("SELECT " +
            "  CASE " +
            "   WHEN " +
            "       COUNT(c)>0 " +
            "   THEN " +
            "       TRUE " +
            "   ELSE " +
            "       FALSE " +
            "   END " +
            "FROM Course c " +
            "WHERE c.code = ?1")
    boolean existsByCode(String s);
    @Query("SELECT " +
            "CASE " +
            "WHEN c.students.size > 20" +
            "THEN TRUE " +
            "ELSE FALSE " +
            "END " +
            "FROM Course c " +
            "WHERE c.id= ?1")
    boolean courseStudentsSizeValid(long id);

}
