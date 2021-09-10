package dev.patika.app.bussiness.concretes;

import dev.patika.app.bussiness.abstracts.InstructorService;
import dev.patika.app.core.exceptions.InstructorIsAlreadyExistsException;
import dev.patika.app.core.exceptions.StudentAgeNotValidException;
import dev.patika.app.core.exceptions.dao.ExceptionsDao;
import dev.patika.app.core.exceptions.entity.Exception;
import dev.patika.app.core.mapper.InstructorMapper;
import dev.patika.app.core.mapper.PermanentInstructorMapper;
import dev.patika.app.core.mapper.VisitingResearcherMapper;
import dev.patika.app.dao.abstracts.InstructorDao;
import dev.patika.app.entity.concretes.Instructor;
import dev.patika.app.entity.concretes.VisitingResearcher;
import dev.patika.app.entity.dto.InstructorDto;
import dev.patika.app.entity.dto.PermanentInstructorDto;
import dev.patika.app.entity.dto.VisitingResearcherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InstructorManager implements InstructorService {
    private final InstructorDao instructorDao;
    private final ExceptionsDao exceptionsDao;
    private final InstructorMapper instructorMapper;
    private final VisitingResearcherMapper visitingResearcherMapper;
    private final PermanentInstructorMapper permanentInstructorMapper;
    @Override
    public Optional<Instructor> save(InstructorDto instructorDto) {
        Instructor instructor;
        if(instructorDto.getInstructorType().equals("visiting")) {
            VisitingResearcherDto visitingResearcherDto = new VisitingResearcherDto();
            visitingResearcherDto.setFullName(instructorDto.getFullName());
            visitingResearcherDto.setAddress(instructorDto.getAddress());
            visitingResearcherDto.setPhoneNumber(instructorDto.getPhoneNumber());
            visitingResearcherDto.setHourlySalary(instructorDto.getSalary());
            visitingResearcherDto.setSalary(instructorDto.getSalary());
            instructor = visitingResearcherMapper.mapFromVisitingInstructorDTOToVisitingInstructor(visitingResearcherDto);
        }
        else{
            PermanentInstructorDto permanentInstructorDto = new PermanentInstructorDto();
            permanentInstructorDto.setFullName(instructorDto.getFullName());
            permanentInstructorDto.setAddress(instructorDto.getAddress());
            permanentInstructorDto.setPhoneNumber(instructorDto.getPhoneNumber());
            permanentInstructorDto.setFixedSalary(instructorDto.getSalary());
            permanentInstructorDto.setSalary(instructorDto.getSalary());
            instructor = permanentInstructorMapper.mapFromPermanentInstructorDTOToPermanentInstructor(permanentInstructorDto);
        }
        if(this.instructorDao.existsByPhoneNumber(instructor.getPhoneNumber())) {
            Exception exception = Exception.builder()
                    .errorClass(InstructorIsAlreadyExistsException.class.getName())
                    .statusCode("400")
                    .message("Instructor(" + instructor.getFullName() + ") with Phone number("+ instructor.getPhoneNumber() +") already exists!").build();
            this.exceptionsDao.save(exception);
            throw new InstructorIsAlreadyExistsException(exception.getMessage());
        }
        return Optional.of(this.instructorDao.save(instructor));
    }

    @Override
    public Optional<List<Instructor>> getAll() {
        return Optional.of(this.instructorDao.findAll());
    }

    @Override
    public Optional<Instructor> getById(Long id) {
        return this.instructorDao.findById(id);
    }
}
