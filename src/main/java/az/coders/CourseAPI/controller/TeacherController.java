package az.coders.CourseAPI.controller;

import az.coders.CourseAPI.model.Teacher;
import az.coders.CourseAPI.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    @GetMapping("allTeacher")
     public ResponseEntity<List<Teacher>>getTeachers(){
       return teacherService.getAllTeacher();
     }
     @PostMapping("register")
    public ResponseEntity<String>addTeacher(@RequestBody Teacher teacher,@RequestParam("groupIds") String groupIds){
      return teacherService.addTeacher(teacher,groupIds);
     }

}
