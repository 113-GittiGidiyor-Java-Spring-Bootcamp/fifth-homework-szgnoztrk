package dev.patika.app.api.controller;

import dev.patika.app.bussiness.abstracts.StudentService;
import dev.patika.app.entity.concretes.Student;
import dev.patika.app.entity.dto.StudentDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StudentsControllerTest {
    @Mock
    List<Student> studentList;

    @Mock
    StudentService studentService;

    @InjectMocks
    StudentsController studentsController;

    @Test
    void getAllStudents() {
        when(this.studentService.getAll()).thenReturn(Optional.of(studentList));

        ResponseEntity actual = this.studentsController.getAllStudents();

        assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    void getStudentById() {
        Student student = new Student();
        student.setId(1L);
        Optional<Student> optionalStudent = this.studentList.stream().filter(o -> o.getId() == student.getId()).findFirst();
        when(this.studentService.getById(anyLong())).thenReturn(optionalStudent);

        ResponseEntity actual = this.studentsController.getStudentById(anyLong());

        assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    void saveStudent() {
        Student student = new Student();
        student.setId(1L);
        Optional<Student> optionalStudent = Optional.of(student);
        when(this.studentService.save(any())).thenReturn(optionalStudent);

        StudentDto studentDto = new StudentDto();
        Student actual = (Student) this.studentsController.saveStudent(studentDto).getBody();

        assertAll(
                () ->assertNotNull(actual),
                () ->assertEquals(optionalStudent.get(), actual)
        );
    }

    @Test
    void saveStudent2() {
        Student student = new Student();
        student.setId(1L);
        Optional<Student> optionalStudent = Optional.of(student);
        when(this.studentService.save(any())).thenReturn(optionalStudent);

        StudentDto studentDto = new StudentDto();
        ResponseEntity<Student> actual = this.studentsController.saveStudent(studentDto);

        assertEquals(HttpStatus.OK, actual.getStatusCode());
    }
}