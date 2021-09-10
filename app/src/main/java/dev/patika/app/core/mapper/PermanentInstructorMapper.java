package dev.patika.app.core.mapper;

import dev.patika.app.entity.concretes.PermanentInstructor;
import dev.patika.app.entity.dto.PermanentInstructorDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PermanentInstructorMapper {
    @Mapping(target = "fixedSalary", source = "salary")
    PermanentInstructor mapFromPermanentInstructorDTOToPermanentInstructor(PermanentInstructorDto permanentInstructorDto);
    PermanentInstructorDto mapFromPermanentInstructorToPermanentInstructorDTO(PermanentInstructor permanentInstructor);
}
