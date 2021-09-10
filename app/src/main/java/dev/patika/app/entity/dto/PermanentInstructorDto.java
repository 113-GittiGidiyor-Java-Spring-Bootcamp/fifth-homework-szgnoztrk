package dev.patika.app.entity.dto;

import dev.patika.app.entity.concretes.Course;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermanentInstructorDto extends InstructorDto {
    @ApiModelProperty(example = "1000")
    private double fixedSalary;
}
