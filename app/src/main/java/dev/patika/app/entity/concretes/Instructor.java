package dev.patika.app.entity.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Builder
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Instructor extends BaseEntity {

    private String fullName;

    private String address;

    private String phoneNumber;

    @JsonManagedReference
    @OneToMany(mappedBy = "instructor")
    private List<Course> courses;
}
