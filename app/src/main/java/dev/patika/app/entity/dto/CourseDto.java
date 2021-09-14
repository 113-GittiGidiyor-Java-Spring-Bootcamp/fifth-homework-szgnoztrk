package dev.patika.app.entity.dto;

import dev.patika.app.entity.concretes.Instructor;
import dev.patika.app.entity.concretes.Student;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDto {
    @ApiModelProperty(example = "Java Programlama")
    private String name;
    @ApiModelProperty(example = "JAVA")
    private String code;
    @ApiModelProperty(example = "5")
    private int creditScore;
    @ApiModelProperty(example = "1")
    Long instructorId;
}
