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
public class PermanentInstructor extends Instructor{
    private double fixedSalary;

    @Builder(builderMethodName = "PermanentInstructorBuilder")
    public PermanentInstructor(String fullName, String address, String phoneNumber, List<Course> courses, double fixedSalary) {
        super(fullName, address, phoneNumber, courses);
        this.fixedSalary = fixedSalary;
    }
}
