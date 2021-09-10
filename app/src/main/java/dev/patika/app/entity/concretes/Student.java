package dev.patika.app.entity.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.patika.app.entity.abstracts.BaseEntity;
import dev.patika.app.entity.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Student extends BaseEntity {

    private String fullName;

    private LocalDate birthDate;

    private String address;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @JsonIgnore
    @ManyToMany
    private List<Course> courses;
}
