package dev.patika.app.bussiness.concretes;

import dev.patika.app.bussiness.abstracts.SalaryLoggerService;
import dev.patika.app.core.exceptions.TransactionDateTimeParseException;
import dev.patika.app.dao.abstracts.SalaryLoggerDao;
import dev.patika.app.entity.concretes.InstructorSalaryUpdatedLogger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SalaryLoggerManager implements SalaryLoggerService {
    private final SalaryLoggerDao dao;
    @Override
    public List<InstructorSalaryUpdatedLogger> getAllByDateOrId(String date, Long instructorId) {
        LocalDate localDateTime = null;
        if (date != null)
            localDateTime = LocalDate.parse(date, DateTimeFormatter.ofPattern("d/MM/yyyy"));
        return this.dao.getAllByInstructorIdOrLogDateIsLike(instructorId, localDateTime);
    }
}
