package dev.patika.app.core.mapper;

import dev.patika.app.entity.concretes.Student;
import dev.patika.app.entity.dto.StudentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper
public interface StudentMapper {
    @Mapping(target = "birthDate", expression = "java(java.time.LocalDate.parse(studentDto.getBirthDate()))")
    Student mapFromStudentDTOToStudent(StudentDto studentDto);
    StudentDto mapFromStudentToStudentDTO(Student student);
}
