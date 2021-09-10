package dev.patika.app.bussiness.concretes;

import dev.patika.app.bussiness.abstracts.StudentService;
import dev.patika.app.core.exceptions.StudentAgeNotValidException;
import dev.patika.app.core.exceptions.dao.ExceptionsDao;
import dev.patika.app.core.exceptions.entity.Exception;
import dev.patika.app.core.mapper.StudentMapper;
import dev.patika.app.dao.abstracts.StudentDao;
import dev.patika.app.entity.concretes.Student;
import dev.patika.app.entity.dto.StudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentManager implements StudentService {
    private final StudentDao studentsDao;
    private final ExceptionsDao exceptionsDao;
    private final StudentMapper studentMapper;

    @Override
    public Optional<Student> save(StudentDto studentDto) {
        LocalDate dtoBirthDate = LocalDate.parse(studentDto.getBirthDate());
        int studentAge = LocalDate.now().getYear() - dtoBirthDate.getYear();
        if(studentAge < 18 || studentAge > 40){
            Exception exception = Exception.builder()
                    .errorClass(StudentAgeNotValidException.class.getName())
                    .statusCode("400")
                    .message("Student( " + studentDto.getFullName() + "): Age("+ studentAge +") must be between 18 and 40.").build();
            this.exceptionsDao.save(exception);
            throw new StudentAgeNotValidException(exception.getMessage());
        }
        Student student = this.studentMapper.mapFromStudentDTOToStudent(studentDto);
        return Optional.of(this.studentsDao.save(student));
    }

    @Override
    public Optional<List<Student>> getAll() {
        return Optional.of(this.studentsDao.findAll());
    }

    @Override
    public Optional<Student> getById(Long id) {
        return this.studentsDao.findById(id);
    }
}
