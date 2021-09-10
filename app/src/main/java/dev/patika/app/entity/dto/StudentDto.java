package dev.patika.app.entity.dto;

import dev.patika.app.entity.enums.Gender;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    @ApiModelProperty(example = "Sezgin Öztürk")
    private String fullName;
    @ApiModelProperty(example = "1996-10-24")
    private String birthDate;
    @ApiModelProperty(example = "Avcılar/İstanbul")
    private String address;
    @ApiModelProperty(example = "Erkek")
    private Gender gender;
}
