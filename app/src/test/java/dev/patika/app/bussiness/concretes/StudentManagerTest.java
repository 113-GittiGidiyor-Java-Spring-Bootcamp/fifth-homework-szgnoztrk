package dev.patika.app.bussiness.concretes;

import dev.patika.app.core.exceptions.StudentAgeNotValidException;
import dev.patika.app.core.exceptions.dao.ExceptionsDao;
import dev.patika.app.core.exceptions.entity.Exception;
import dev.patika.app.core.mapper.StudentMapper;
import dev.patika.app.dao.abstracts.StudentDao;
import dev.patika.app.entity.concretes.Student;
import dev.patika.app.entity.dto.StudentDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.function.Executable;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentManagerTest {
    @Mock
    List<Student> studentList;

    @Mock
    StudentMapper studentMapper;

    @Mock
    StudentDao studentDao;

    @Mock
    ExceptionsDao exceptionsDao;

    @InjectMocks
    StudentManager studentManager;

    @Test
    void save() {
        Student student = new Student();
        lenient().when(this.studentMapper.mapFromStudentDTOToStudent(any())).thenReturn(student);
        lenient().when(this.studentDao.save(any())).thenReturn(student);

        StudentDto studentDto = new StudentDto();
        studentDto.setBirthDate("1996-10-24");
        Optional<Student> actual = this.studentManager.save(studentDto);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(student, actual.get()),
                () -> assertEquals(student.getId(), actual.get().getId())
        );
    }

    @Test
    void save2() {
        Student student = new Student();
        Exception exception = new Exception();
        lenient().when(this.studentMapper.mapFromStudentDTOToStudent(any())).thenReturn(student);
        lenient().when(this.exceptionsDao.save(any())).thenReturn(exception);

        StudentDto studentDto = new StudentDto();
        studentDto.setBirthDate("1896-10-24");
        Executable executable = () -> this.studentManager.save(studentDto).get();

        assertThrows(StudentAgeNotValidException.class, executable);
    }

    @Test
    void getAll() {
        when(this.studentDao.findAll()).thenReturn(studentList);

        List<Student> actual = this.studentManager.getAll().get();

        assertNotNull(actual);
    }
}