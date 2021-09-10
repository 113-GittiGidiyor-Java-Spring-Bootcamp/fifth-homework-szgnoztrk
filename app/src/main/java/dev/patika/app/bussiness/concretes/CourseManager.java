package dev.patika.app.bussiness.concretes;

import dev.patika.app.bussiness.abstracts.CourseService;
import dev.patika.app.bussiness.abstracts.StudentService;
import dev.patika.app.core.exceptions.CourseNotFoundException;
import dev.patika.app.core.exceptions.InstructorIsAlreadyExistsException;
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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseManager implements CourseService {
    private final CourseDao courseDao;
    private final ExceptionsDao exceptionsDao;
    private final CourseMapper courseMapper;
    private final StudentDao studentDao;
    @Override
    public Optional<Course> save(CourseDto courseDto) {
        if(this.courseDao.existsByCode(courseDto.getCode())){
            Exception exception = Exception.builder()
                    .errorClass(InstructorIsAlreadyExistsException.class.getName())
                    .statusCode("400")
                    .message("Course(" + courseDto.getName() + ") with code("+ courseDto.getCode() +") already exists!").build();
            this.exceptionsDao.save(exception);
            throw new InstructorIsAlreadyExistsException(exception.getMessage());
        }
        Course course = this.courseMapper.mapFromCourseDTOToCourse(courseDto);
        return Optional.of(this.courseDao.save(course));
    }

    @Override
    public Optional<List<Course>> getAll() {
        return Optional.of(this.courseDao.findAll());
    }

    @Override
    public Optional<Course> getById(Long id) {
        return this.courseDao.findById(id);
    }

    @Override
    public Optional<Course> addStudentCourse(Long courseId, Long studentId) {
        Course course = Optional.of(this.courseDao.getById(courseId)).orElseThrow(() -> new CourseNotFoundException(String.format("Course with ID: %d could not foud!", courseId)));

        Student student = Optional.of(this.studentDao.getById(studentId)).orElseThrow(() -> new StudentNotFoundException(String.format("Student with ID: %d could not foud!", studentId)));

        if(course.getStudents().size() > 20){
            Exception exception = Exception.builder()
                    .errorClass(InstructorIsAlreadyExistsException.class.getName())
                    .statusCode("400")
                    .message("A maximum of 20 students can take a course at the same time.").build();
            this.exceptionsDao.save(exception);
            throw new StudentNumberForOneExceededException(exception.getMessage());
        }
        List<Student> listOfCourse = course.getStudents();
        listOfCourse.add(student);
        course.setStudents(listOfCourse);
        return Optional.of(this.courseDao.save(course));
    }
}
