package dev.patika.app.bussiness.concretes;

import dev.patika.app.core.exceptions.InstructorIsAlreadyExistsException;
import dev.patika.app.core.exceptions.dao.ExceptionsDao;
import dev.patika.app.core.exceptions.entity.Exception;
import dev.patika.app.core.mapper.InstructorMapper;
import dev.patika.app.core.mapper.VisitingResearcherMapper;
import dev.patika.app.dao.abstracts.InstructorDao;
import dev.patika.app.entity.concretes.Instructor;
import dev.patika.app.entity.concretes.VisitingResearcher;
import dev.patika.app.entity.dto.InstructorDto;
import dev.patika.app.entity.dto.VisitingResearcherDto;
import io.swagger.models.auth.In;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InstructorManagerTest {
    @Mock
    InstructorDao instructorDao;

    @Mock
    ExceptionsDao exceptionsDao;

    @Mock
    VisitingResearcherMapper instructorMapper;

    @Mock
    List<Instructor> instructorList;

    @InjectMocks
    InstructorManager instructorManager;

    @Test
    void save() {
        VisitingResearcher instructor = new VisitingResearcher();
        lenient().when(this.instructorMapper.mapFromVisitingInstructorDTOToVisitingInstructor(any())).thenReturn(instructor);
        lenient().when(this.instructorDao.existsByPhoneNumber(anyString())).thenReturn(Boolean.FALSE);
        lenient().when(this.instructorDao.save(any())).thenReturn(instructor);

        InstructorDto instructorDto = new VisitingResearcherDto();
        instructorDto.setInstructorType("visiting");
        Optional<Instructor> actual = this.instructorManager.save(instructorDto);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(instructor, actual.get()),
                () -> assertEquals(instructor.getPhoneNumber(), actual.get().getPhoneNumber())
        );
    }

    @Test
    void save2() {
        Exception exception = new Exception();
        VisitingResearcher instructor = new VisitingResearcher();
        lenient().when(this.instructorMapper.mapFromVisitingInstructorDTOToVisitingInstructor(any())).thenReturn(instructor);
        lenient().when(this.instructorDao.existsByPhoneNumber(any())).thenReturn(Boolean.TRUE);
        lenient().when(this.exceptionsDao.save(any())).thenReturn(exception);

        InstructorDto instructorDto = new VisitingResearcherDto();
        instructorDto.setInstructorType("visiting");
        Executable executable = () -> this.instructorManager.save(instructorDto).get();

        assertThrows(InstructorIsAlreadyExistsException.class, executable);
    }

    @Test
    void getAll() {
        when(this.instructorDao.findAll()).thenReturn(instructorList);

        List<Instructor> actual = this.instructorManager.getAll().get();

        assertNotNull(actual);
    }
}