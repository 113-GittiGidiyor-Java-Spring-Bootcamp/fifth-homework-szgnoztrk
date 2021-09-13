package dev.patika.app.bussiness.abstracts;

import dev.patika.app.entity.concretes.InstructorSalaryUpdatedLogger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface SalaryLoggerService {
    List<InstructorSalaryUpdatedLogger> getAllByDateOrId(String date, Long instructorId);
}
