package dev.patika.app.entity.concretes;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.patika.app.entity.abstracts.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Course extends BaseEntity {

    private String name;

    private String code;

    private int creditScore;

    @ManyToMany(mappedBy = "courses")
    List<Student> students;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "instructor_id", referencedColumnName = "id")
    Instructor instructor;
}
