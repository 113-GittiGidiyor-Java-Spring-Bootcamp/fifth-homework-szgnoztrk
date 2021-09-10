package dev.patika.app.api.controller;

import dev.patika.app.bussiness.abstracts.CourseService;
import dev.patika.app.entity.dto.CourseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity saveCourse(@RequestBody CourseDto courseDto){
        return new ResponseEntity(this.courseService.save(courseDto), HttpStatus.OK);
    }

    @PutMapping("/add-student-to-course")
    public ResponseEntity addStudentCourse(@RequestParam Long courseId,
                                           @RequestParam Long studentId){
        return new ResponseEntity(this.courseService.addStudentCourse(courseId, studentId), HttpStatus.OK);
    }
}
