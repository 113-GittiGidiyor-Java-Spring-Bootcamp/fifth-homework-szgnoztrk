package dev.patika.app.core.mapper;

import dev.patika.app.entity.concretes.Instructor;
import dev.patika.app.entity.dto.InstructorDto;
import org.mapstruct.Mapper;

@Mapper
public interface InstructorMapper {
    Instructor mapFromInstructorDTOToInstructor(InstructorDto instructorDto);
    InstructorDto mapFromInstructorToInstructorDTO(Instructor instructor);

}
