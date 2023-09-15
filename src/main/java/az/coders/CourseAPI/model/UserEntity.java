package az.coders.CourseAPI.model;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
@Table(name = "user",schema = "public")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY  )
    Integer id;
}
