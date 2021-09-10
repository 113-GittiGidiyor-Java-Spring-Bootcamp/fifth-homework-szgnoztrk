package dev.patika.app.entity.concretes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class VisitingResearcher extends Instructor {
    private double hourlySalary;

    @Builder(builderMethodName = "VisitingResearcherBuilder")
    public VisitingResearcher(String fullName, String address, String phoneNumber, List<Course> courses, double hourlySalary) {
        super(fullName, address, phoneNumber, courses);
        this.hourlySalary = hourlySalary;
    }
}
