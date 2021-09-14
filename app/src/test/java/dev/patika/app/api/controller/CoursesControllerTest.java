package dev.patika.app.api.controller;

import dev.patika.app.bussiness.abstracts.CourseService;
import dev.patika.app.entity.concretes.Course;
import dev.patika.app.entity.dto.CourseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CoursesControllerTest {
    @Mock
    CourseService mockCourseService;

    @Mock
    List<Course> courseList;

    @InjectMocks
    CoursesController coursesController;

    @Test
    void testSaveCourse(){
        //given
        Course course = new Course();
        course.setCode("M11");
        Optional<Course> expected = Optional.of(course);
        when(this.mockCourseService.save(any())).thenReturn(expected);

        //when
        CourseDto courseDto = new CourseDto();
        Course actual = this.coursesController.saveCourse(courseDto).getBody();

        //then
        assertAll(
                () ->assertNotNull(actual),
                () ->assertEquals(expected.get(), actual)
        );
    }

    @Test
    void testSaveCourse2(){
        //given
        when(this.mockCourseService.save(any())).thenReturn(Optional.empty());

        //when
        CourseDto courseDto = new CourseDto();
        ResponseEntity<Course> actual = this.coursesController.saveCourse(courseDto);

        //then
        assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
    }

    @Test
    void testGetAll(){
        lenient().when(this.mockCourseService.getAll()).thenReturn(Optional.of(courseList));
    }

    @Test
    void testGetCourseById(){
        //given
        Course course = new Course();
        course.setCode("M11");
        Optional<Course> optionalCourse = courseList.stream().filter(o -> o.getId() == course.getId()).findFirst();
        lenient().when(this.mockCourseService.getById(anyLong())).thenReturn(optionalCourse);
    }

    @Test
    void testAddStudentCourse(){
        Course course = new Course();
        course.setCode("M11");
        Optional<Course> optionalCourse = Optional.of(course);
        lenient().when(this.mockCourseService.addStudentCourse(anyLong(), anyLong())).thenReturn(optionalCourse);
    }
}