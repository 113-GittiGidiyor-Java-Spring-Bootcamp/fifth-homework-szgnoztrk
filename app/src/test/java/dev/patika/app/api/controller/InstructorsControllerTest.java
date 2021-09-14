package dev.patika.app.api.controller;

import dev.patika.app.bussiness.abstracts.InstructorService;
import dev.patika.app.entity.concretes.Instructor;
import dev.patika.app.entity.dto.InstructorDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class InstructorsControllerTest {
    @Mock
    List<Instructor> instructorList;

    @Mock
    InstructorService instructorService;

    @InjectMocks
    InstructorsController instructorsController;

    @Test
    void getAllInstructors() {
        when(this.instructorService.getAll()).thenReturn(Optional.of(instructorList));

        ResponseEntity actual = this.instructorsController.getAllInstructors();

        assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    void getInstructorById() {
        Instructor instructor = new Instructor();
        instructor.setId(1L);
        Optional<Instructor> optionalInstructor = this.instructorList.stream().filter(o -> o.getId() == instructor.getId()).findFirst();
        when(this.instructorService.getById(anyLong())).thenReturn(optionalInstructor);

        ResponseEntity actual = this.instructorsController.getInstructorById(anyLong());

        assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    void saveInstructor() {
        Instructor instructor = new Instructor();
        instructor.setId(1L);
        Optional<Instructor> optionalInstructor = Optional.of(instructor);
        when(this.instructorService.save(any())).thenReturn(optionalInstructor);

        InstructorDto instructorDto = new InstructorDto();
        Instructor actual = (Instructor) this.instructorsController.saveInstructor(instructorDto).getBody();

        assertAll(
                () ->assertNotNull(actual),
                () ->assertEquals(optionalInstructor.get(), actual)
        );
    }

    @Test
    void saveInstructor2() {
        when(this.instructorService.save(any())).thenReturn(Optional.empty());

        InstructorDto instructorDto = new InstructorDto();
        ResponseEntity<Instructor> actual = this.instructorsController.saveInstructor(instructorDto);

        assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
    }
}