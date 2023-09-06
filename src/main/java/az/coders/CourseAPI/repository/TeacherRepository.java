package az.coders.CourseAPI.repository;

import az.coders.CourseAPI.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
}
