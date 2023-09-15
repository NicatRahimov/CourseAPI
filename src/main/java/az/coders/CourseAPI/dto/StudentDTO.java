package az.coders.CourseAPI.dto;

import az.coders.CourseAPI.model.Group;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class StudentDTO {
    private Integer id;
    private String name;
    private String surname;
    private Integer age;
}
