package dev.patika.app.bussiness.concretes;

import dev.patika.app.core.exceptions.CourseIsAlreadyExistsException;
import dev.patika.app.core.exceptions.CourseNotFoundException;
import dev.patika.app.core.exceptions.StudentNotFoundException;
import dev.patika.app.core.exceptions.StudentNumberForOneExceededException;
import dev.patika.app.core.exceptions.dao.ExceptionsDao;
import dev.patika.app.core.exceptions.entity.Exception;
import dev.patika.app.core.mapper.CourseMapper;
import dev.patika.app.dao.abstracts.CourseDao;
import dev.patika.app.dao.abstracts.StudentDao;
import dev.patika.app.entity.concretes.Course;
import dev.patika.app.entity.concretes.Student;
import dev.patika.app.entity.dto.CourseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opentest4j.TestSkippedException;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseManagerTest {

    @Mock
    CourseDao courseDao;

    @Mock
    StudentDao studentDao;

    @Mock
    CourseMapper courseMapper;

    @Mock
    ExceptionsDao exceptionsDao;

    @InjectMocks
    CourseManager courseService;

    @Test
    void save() {
        Course course = new Course();
        lenient().when(this.courseDao.existsByCode(anyString())).thenReturn(Boolean.FALSE);
        when(this.courseMapper.mapFromCourseDTOToCourse(any())).thenReturn(course);
        when(this.courseDao.save(any())).thenReturn(course);

        CourseDto courseDto = new CourseDto();
        Course actual = this.courseService.save(courseDto).get();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(course, actual),
                () -> assertEquals(course.getCode(), actual.getCode())
        );
    }

    @Test
    void save2() {
        Exception exception = new Exception();
        Course course = new Course();
        lenient().when(this.courseDao.existsByCode(anyString())).thenReturn(Boolean.TRUE);
        lenient().when(this.exceptionsDao.save(any())).thenReturn(exception);
        lenient().when(this.courseMapper.mapFromCourseDTOToCourse(any())).thenReturn(course);
        lenient().when(this.courseDao.save(any())).thenReturn(course);

        CourseDto courseDto = new CourseDto();
        courseDto.setCode("JAVA");
        Executable executable =  () -> { this.courseService.save(courseDto).get(); };

        assertThrows(CourseIsAlreadyExistsException.class, executable);
    }

    @Test
    void getCourseById(){
        Course course = new Course();
        lenient().when(this.courseDao.findById(anyLong())).thenReturn(course);

        Optional<Course> actual = this.courseService.getById(anyLong());

        assertNotNull(actual);
    }

    @Test
    void addStudentCourse1() {
        Exception exception = new Exception();
        lenient().when(this.courseDao.findById(anyLong())).thenReturn(null);
        lenient().when(this.exceptionsDao.save(any())).thenReturn(exception);

        Long aLong = 1L, bLong = 1L;
        Executable executable = () -> this.courseService.addStudentCourse(aLong, bLong);

        assertThrows(CourseNotFoundException.class, executable);
    }

    @Test
    void addStudentCourse2() {
        Exception exception = new Exception();
        Course course = new Course();
        lenient().when(this.courseDao.findById(any())).thenReturn(Optional.of(course));
        lenient().when(this.studentDao.findById(anyLong())).thenReturn(null);
        lenient().when(this.exceptionsDao.save(any())).thenReturn(exception);

        Long aLong=1L, bLong=1L;
        Executable executable = () -> this.courseService.addStudentCourse(aLong, bLong);

        assertThrows(StudentNotFoundException.class, executable);
    }

    @Test
    void addStudentCourse3() {
        Exception exception = new Exception();
        Course course = new Course();
        Student student = new Student();
        lenient().when(this.courseDao.findById(any())).thenReturn(Optional.of(course));
        lenient().when(this.studentDao.findById(any())).thenReturn(Optional.of(student));
        lenient().when(this.courseDao.courseStudentsSizeValid(anyLong())).thenReturn(Boolean.TRUE);
        lenient().when(this.exceptionsDao.save(any())).thenReturn(exception);

        Executable executable = () -> this.courseService.addStudentCourse(null, null);

        assertThrows(StudentNumberForOneExceededException.class, executable);
    }
}