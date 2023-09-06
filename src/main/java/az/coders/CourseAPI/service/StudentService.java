package az.coders.CourseAPI.service;

import az.coders.CourseAPI.model.Group;
import az.coders.CourseAPI.model.Student;
import az.coders.CourseAPI.repository.GroupRepository;
import az.coders.CourseAPI.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.transform.OutputKeys;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    GroupRepository groupRepository;

    public ResponseEntity<String> addStudent(Student student){
       String groupName= student.getGroup().getGroupName();
        student.setGroup(groupRepository.findByGroupName(groupName));
studentRepository.save(student);
return new ResponseEntity<>("Succesful added", HttpStatus.CREATED);
    }

    public ResponseEntity<List<Student>> getStudents() {
       List<Student>students = studentRepository.findAll();
       return new ResponseEntity<>(students,HttpStatus.OK);
    }
}
