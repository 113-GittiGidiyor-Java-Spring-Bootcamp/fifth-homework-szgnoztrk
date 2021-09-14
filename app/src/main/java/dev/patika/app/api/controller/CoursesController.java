package dev.patika.app.api.controller;

import dev.patika.app.bussiness.abstracts.CourseService;
import dev.patika.app.entity.concretes.Course;
import dev.patika.app.entity.dto.CourseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/courses")
public class CoursesController {
    private final CourseService courseService;
    @GetMapping
    public ResponseEntity getAllCourses(){
        return new ResponseEntity(this.courseService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getCourseById(@PathVariable Long id){
        return new ResponseEntity(this.courseService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Course> saveCourse(@RequestBody CourseDto courseDto){
        Optional<Course> optionalCourse = this.courseService.save(courseDto);
        if(optionalCourse.isPresent())
            return new ResponseEntity(optionalCourse.get(), HttpStatus.OK);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/add-student-to-course")
    public ResponseEntity addStudentCourse(@RequestParam Long courseId,
                                           @RequestParam Long studentId){
        return new ResponseEntity(this.courseService.addStudentCourse(courseId, studentId), HttpStatus.OK);
    }
}
