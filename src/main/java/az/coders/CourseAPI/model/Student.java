package az.coders.CourseAPI.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "student")
@Data
public class Student {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    private Group group;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Size(min = 2, max = 50)
    private String name;
    @Size(min = 2, max = 50)
    private String surname;
    private Integer age;
    @Email
    private String mail;
    private String address;
    @Column(name = "cotact_number")
    private String contactNumber;

}
