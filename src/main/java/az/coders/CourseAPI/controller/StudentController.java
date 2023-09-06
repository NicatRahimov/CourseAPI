package az.coders.CourseAPI.controller;

import az.coders.CourseAPI.model.Student;
import az.coders.CourseAPI.repository.StudentRepository;
import az.coders.CourseAPI.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/register")
    public ResponseEntity<String> addStudent(@RequestBody Student student){
       return studentService.addStudent(student);
    }

    @GetMapping("allStudent")
    public ResponseEntity<List<Student>> getAllStudent(){
       return studentService.getStudents();
    }



}
