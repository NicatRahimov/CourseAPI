package az.coders.CourseAPI.controller;

import az.coders.CourseAPI.dto.GroupDTO;
import az.coders.CourseAPI.dto.StudentDTO;
import az.coders.CourseAPI.dto.TeacherDTO;
import az.coders.CourseAPI.model.Teacher;
import az.coders.CourseAPI.service.GroupService;
import az.coders.CourseAPI.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pagination")
@RequiredArgsConstructor
public class PaginationController  {

private final StudentService studentService;
private final GroupService groupService;
    @GetMapping("getStudents")
    public Page<StudentDTO> getStudents(@RequestParam(defaultValue = "5") Integer pageSize,
                                        @RequestParam(defaultValue = "0") Integer pageNumber){
return studentService.getStudentPage(pageSize,pageNumber);
    }
    @GetMapping("getGroups")
        public Page<GroupDTO>getTeacher(@RequestParam Integer pageSize,
                                        @RequestParam Integer pageNumber,
                                        @RequestParam String fieldName,
                                        @RequestParam(defaultValue = "desc") String sortDirection){

return groupService.getGroupPage(pageSize,pageNumber,fieldName,sortDirection);
        }

}
