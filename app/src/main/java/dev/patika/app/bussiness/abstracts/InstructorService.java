package dev.patika.app.bussiness.abstracts;

import dev.patika.app.entity.concretes.Instructor;
import dev.patika.app.entity.dto.InstructorDto;

import java.util.List;
import java.util.Optional;

public interface InstructorService {
    Optional<Instructor> save(InstructorDto instructorDto);
    Optional<List<Instructor>> getAll();
    Optional<Instructor> getById(Long id);
}
