package az.coders.CourseAPI.service;

import az.coders.CourseAPI.exception.StudentNotFound;
import az.coders.CourseAPI.model.Group;
import az.coders.CourseAPI.model.Student;
import az.coders.CourseAPI.repository.GroupRepository;
import az.coders.CourseAPI.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    GroupRepository groupRepository;

    public ResponseEntity<String> addStudent(Student student,String groupName){
        student.setGroup(groupRepository.findGroupByGroupName(groupName));
studentRepository.save(student);
return new ResponseEntity<>("Succesful added", HttpStatus.CREATED);
    }

    public ResponseEntity<List<Student>> getStudents() {
       List<Student>students = studentRepository.findAll();
       return new ResponseEntity<>(students,HttpStatus.OK);
    }

    public ResponseEntity<Student> getStudentById(Integer id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()){
           return new ResponseEntity<>(studentOptional.get(),HttpStatus.OK);
        }else throw new StudentNotFound("There is no student with id: "+id);
    }

    public ResponseEntity<List<Student>> getStudentsByGroupName(String groupName) {
        List<Student>students;
       Group group = groupRepository.findGroupByGroupName(groupName);
        students=group.getStudents();
        return new ResponseEntity<>(students,HttpStatus.OK);
    }

    public ResponseEntity<String> editStudentById(Integer id, Student student) {
       Optional<Student> studentOptional = studentRepository.findById(id);
       Student student1;
       if (studentOptional.isPresent()){
           student1=studentOptional.get();
           student1.setName(student.getName());
           student1.setSurname(student.getSurname());
           student1.setMail(student.getMail());
            student1.setAge(student.getAge());
            student1.setAddress(student.getAddress());
            student1.setContactNumber(student.getContactNumber());
            studentRepository.save(student1);
            return new ResponseEntity<>("Edited succesfully",HttpStatus.OK);
       }else throw new StudentNotFound("There is no student with id: "+id);
    }

    public ResponseEntity<String> deleteStudentById(Integer id) {
        try{
            studentRepository.deleteById(id);
            return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("There is no student with id: "+id,HttpStatus.BAD_REQUEST);
        }
    }
}
