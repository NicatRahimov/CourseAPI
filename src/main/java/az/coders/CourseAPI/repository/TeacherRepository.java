package az.coders.CourseAPI.repository;

import az.coders.CourseAPI.model.Group;
import az.coders.CourseAPI.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
//    List<Teacher>findTeachersByGroups(List<Group> groups);
}
