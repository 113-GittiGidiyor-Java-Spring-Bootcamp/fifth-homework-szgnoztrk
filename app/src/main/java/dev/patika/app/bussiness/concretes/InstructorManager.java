package dev.patika.app.bussiness.concretes;

import dev.patika.app.bussiness.abstracts.InstructorService;
import dev.patika.app.core.config.ClientRequestInfo;
import dev.patika.app.core.exceptions.InstructorIsAlreadyExistsException;
import dev.patika.app.core.exceptions.NotFoundInstructorException;
import dev.patika.app.core.exceptions.dao.ExceptionsDao;
import dev.patika.app.core.exceptions.entity.Exception;
import dev.patika.app.core.mapper.PermanentInstructorMapper;
import dev.patika.app.core.mapper.VisitingResearcherMapper;
import dev.patika.app.dao.abstracts.InstructorDao;
import dev.patika.app.dao.abstracts.InstructorSalaryUpdatedLoggerDao;
import dev.patika.app.dao.abstracts.PermanentInstructorDao;
import dev.patika.app.dao.abstracts.VisitingInstructorDao;
import dev.patika.app.entity.concretes.Instructor;
import dev.patika.app.entity.concretes.InstructorSalaryUpdatedLogger;
import dev.patika.app.entity.concretes.PermanentInstructor;
import dev.patika.app.entity.concretes.VisitingResearcher;
import dev.patika.app.entity.dto.InstructorDto;
import dev.patika.app.entity.dto.PermanentInstructorDto;
import dev.patika.app.entity.dto.VisitingResearcherDto;
import dev.patika.app.entity.enums.PercentType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InstructorManager implements InstructorService {
    private final InstructorDao instructorDao;
    private final VisitingInstructorDao visitinginstructorDao;
    private final PermanentInstructorDao permanentInstructorDao;
    private final ExceptionsDao exceptionsDao;
    private final VisitingResearcherMapper visitingResearcherMapper;
    private final PermanentInstructorMapper permanentInstructorMapper;
    private final ClientRequestInfo clientRequestInfo;
    private final InstructorSalaryUpdatedLoggerDao instructorSalaryUpdatedLoggerDao;
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
        boolean isExists = this.instructorDao.existsByPhoneNumber(instructor.getPhoneNumber());
        if(isExists) {
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

    @Override
    public Optional<InstructorSalaryUpdatedLogger> updateSalary(long id, double percent, PercentType percentType) {
        Optional<PermanentInstructor> permanentInstructor = Optional.ofNullable(this.permanentInstructorDao.findById(id));
        Optional<VisitingResearcher> visitingResearcher = Optional.ofNullable(this.visitinginstructorDao.findById(id));
        InstructorSalaryUpdatedLogger instructorSalaryUpdatedLogger = new InstructorSalaryUpdatedLogger();
        if(!permanentInstructor.isPresent() && !visitingResearcher.isPresent())
            throw new NotFoundInstructorException("Instructor with ID(" + id + ") not found!");
        else{

            instructorSalaryUpdatedLogger.setInstructorId(id);
            instructorSalaryUpdatedLogger.setPercent(percent);
            instructorSalaryUpdatedLogger.setPercentType(percentType);
            if (permanentInstructor.isPresent()){
                instructorSalaryUpdatedLogger.setSalaryBefore(permanentInstructor.get().getFixedSalary());
                if (percentType.equals(PercentType.SUB))
                    permanentInstructor.get().setFixedSalary((permanentInstructor.get().getFixedSalary() * (percent / 100)));
                else
                    permanentInstructor.get().setFixedSalary((permanentInstructor.get().getFixedSalary() * ((100 + percent) / 100)));
                instructorSalaryUpdatedLogger.setSalaryAfter(permanentInstructor.get().getFixedSalary());
            }else if(visitingResearcher.isPresent()){
                instructorSalaryUpdatedLogger.setSalaryBefore(visitingResearcher.get().getHourlySalary());
                if (percentType.equals(PercentType.SUB))
                    visitingResearcher.get().setHourlySalary((visitingResearcher.get().getHourlySalary() * (percent / 100)));
                else
                    visitingResearcher.get().setHourlySalary((visitingResearcher.get().getHourlySalary() * ((100 + percent) / 100)));
                instructorSalaryUpdatedLogger.setSalaryAfter(visitingResearcher.get().getHourlySalary());
            }
            instructorSalaryUpdatedLogger.setClientUrl(clientRequestInfo.getClientUrl());
            instructorSalaryUpdatedLogger.setClientIpAddress(clientRequestInfo.getClientIpAdress());
            instructorSalaryUpdatedLogger.setSessionActivityId(clientRequestInfo.getSessionActivityId());
            instructorSalaryUpdatedLogger.setLogDate(LocalDate.now());
            this.instructorSalaryUpdatedLoggerDao.save(instructorSalaryUpdatedLogger);
            return Optional.of(instructorSalaryUpdatedLogger);
        }
    }
}
