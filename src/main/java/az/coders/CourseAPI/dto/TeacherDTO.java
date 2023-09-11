package az.coders.CourseAPI.dto;

import az.coders.CourseAPI.model.Group;
import lombok.Data;

import java.util.List;
@Data
public class TeacherDTO {
    private Integer id;
    private String name;
    private String surname;
    private Integer age;
    List<GroupDTO> groups;
}
