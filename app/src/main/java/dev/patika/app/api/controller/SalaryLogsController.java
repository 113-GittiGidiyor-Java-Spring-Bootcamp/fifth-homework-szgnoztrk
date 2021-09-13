package dev.patika.app.api.controller;

import dev.patika.app.bussiness.abstracts.SalaryLoggerService;
import dev.patika.app.entity.concretes.InstructorSalaryUpdatedLogger;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("api/logs")
@RequiredArgsConstructor
public class SalaryLogsController {
    private final SalaryLoggerService service;
    @GetMapping("/get-all-by-instructor-id-or-date")
    public List<InstructorSalaryUpdatedLogger> getAllByInstructorIdOrDate(@ApiParam(example = "13/09/2021")
                                                                              @RequestParam(required = false) String dateTime,
                                                                          @RequestParam(required = false) Long instructorId){
        return this.service.getAllByDateOrId(dateTime, instructorId);
    }
}
