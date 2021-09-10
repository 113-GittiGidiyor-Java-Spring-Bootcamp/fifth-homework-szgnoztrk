package dev.patika.app.core.mapper;

import dev.patika.app.bussiness.abstracts.InstructorService;
import dev.patika.app.entity.concretes.Course;
import dev.patika.app.entity.dto.CourseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public abstract class CourseMapper {
    @Autowired
    protected InstructorService instructorService;
    @Mapping(target = "instructor", expression = "java(instructorService.getById(courseDto.getInstructorId()).get())")
    public abstract Course mapFromCourseDTOToCourse(CourseDto courseDto);
    public abstract CourseDto mapFromCourseToCourseDTO(Course course);
}
