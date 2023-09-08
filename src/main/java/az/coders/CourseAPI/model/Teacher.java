package az.coders.CourseAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "teacher")
@Data
public class Teacher {
    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "group_teacher",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
            List<Group>groups;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String name;
    private String surname;
    private Integer age;
    @Email
    private String mail;
    private String address;
    @Column(name = "cotact_number")
    private String contactNumber;
}
