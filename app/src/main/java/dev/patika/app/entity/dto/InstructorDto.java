package dev.patika.app.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstructorDto {
    @ApiModelProperty(example = "Koray Güney")
    private String fullName;
    @ApiModelProperty(example = "Pendik/İstanbul")
    private String address;
    @ApiModelProperty(example = "+905554443322")
    private String phoneNumber;
    @ApiModelProperty(example = "visiting or permanent")
    private String instructorType;
    @ApiModelProperty(example = "250.0")
    private double salary;
}
