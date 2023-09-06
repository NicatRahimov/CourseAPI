package az.coders.CourseAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "student")
@Data
public class Student {
    @ManyToOne(cascade = {
     CascadeType.PERSIST,
            CascadeType.MERGE
    } )
    @JoinColumn(name = "group_id")
    @JsonIgnore
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
