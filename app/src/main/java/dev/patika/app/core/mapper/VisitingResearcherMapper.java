package dev.patika.app.core.mapper;

import dev.patika.app.entity.concretes.VisitingResearcher;
import dev.patika.app.entity.dto.VisitingResearcherDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface VisitingResearcherMapper {
    @Mapping(target = "hourlySalary", source = "salary")
    VisitingResearcher mapFromVisitingInstructorDTOToVisitingInstructor(VisitingResearcherDto visitingResearcherDto);
    VisitingResearcherDto mapFromVisitingInstructorToVisitingInstructorDTO(VisitingResearcher visitingResearcher);
}
