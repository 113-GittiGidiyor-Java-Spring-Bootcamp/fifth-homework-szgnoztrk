package dev.patika.app.bussiness.abstracts;

import dev.patika.app.entity.concretes.Student;
import dev.patika.app.entity.dto.StudentDto;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Optional<Student> save(StudentDto studentDto);
    Optional<List<Student>> getAll();
    Optional<Student> getById(Long id);
}
