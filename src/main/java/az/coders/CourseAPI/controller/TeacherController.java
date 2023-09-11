package az.coders.CourseAPI.controller;

import az.coders.CourseAPI.dto.TeacherDTO;
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
     public ResponseEntity<List<TeacherDTO>>getTeachers(){
       return teacherService.getAllTeacher();
     }
     @GetMapping("id/{id}")
     public ResponseEntity<TeacherDTO>getTeacherById(@PathVariable Integer id){
      return teacherService.getTeacherById(id);
     }

//     @GetMapping("groupName")
//     public ResponseEntity<List<Teacher>> getTeacherByGroupName
//             (@RequestParam List<String> groupName){
//        return teacherService.getTeachersByGroupName(groupName);
//     }

     @PostMapping("register")
    public ResponseEntity<String>addTeacher(@RequestBody Teacher teacher,@RequestParam("groupId") List<Integer> groupIds){
      return teacherService.addTeacher(teacher,groupIds);
     }
     @PutMapping("edit/{id}")
    public ResponseEntity<String>editTeacher(@RequestBody Teacher teacher,
                                             @RequestParam("groupId") List<Integer> groupIds,
                                             @PathVariable("id") Integer teacherId){
       return teacherService.editTeacher(teacher,groupIds,teacherId);
     }
     @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable Integer id){
        return teacherService.deleteTeacherById(id);

     }


}
