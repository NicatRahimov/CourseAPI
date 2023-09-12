package az.coders.CourseAPI.dto;

import az.coders.CourseAPI.model.Student;
import az.coders.CourseAPI.model.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
public class GroupDTO {
        private Integer id;
        private String groupName;
        private Integer capacity;
}
