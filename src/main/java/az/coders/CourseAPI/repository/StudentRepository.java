package az.coders.CourseAPI.repository;

import az.coders.CourseAPI.model.Group;
import az.coders.CourseAPI.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    List<Student> findStudentsByGroupId(Integer groupId);
}
