package dev.patika.app.bussiness.abstracts;

import dev.patika.app.entity.concretes.Course;
import dev.patika.app.entity.dto.CourseDto;
import java.util.List;
import java.util.Optional;

public interface CourseService {
    Optional<Course> save(CourseDto courseDto);
    Optional<List<Course>> getAll();
    Optional<Course> getById(Long id);
    Optional<Course> addStudentCourse(Long courseId,Long studentId);
}
