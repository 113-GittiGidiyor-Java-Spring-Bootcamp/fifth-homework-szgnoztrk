package dev.patika.app.entity.concretes;

import dev.patika.app.entity.enums.PercentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class InstructorSalaryUpdatedLogger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long instructorId;
    private double salaryBefore;
    private double salaryAfter;
    private double percent;
    @Enumerated(EnumType.STRING)
    private PercentType percentType;
    private String clientIpAddress;
    private String clientUrl;
    private String sessionActivityId;
    private LocalDateTime logDate;
}
