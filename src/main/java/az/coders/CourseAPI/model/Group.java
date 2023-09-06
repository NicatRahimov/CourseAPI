package az.coders.CourseAPI.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "groups")
@Data
public class Group {
    @ManyToMany(mappedBy = "groups",cascade = CascadeType.ALL)
    List<Teacher>teachers;
    @OneToMany(mappedBy = "group",cascade = CascadeType.ALL)
    List<Student>students;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "group_name")
    private String groupName;
    private Integer capacity;
}
