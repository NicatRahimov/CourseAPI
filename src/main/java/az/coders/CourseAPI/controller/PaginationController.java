package az.coders.CourseAPI.controller;

import az.coders.CourseAPI.dto.StudentDTO;
import az.coders.CourseAPI.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
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
    @GetMapping("getStudents")
    public Page<StudentDTO> getStudents(@RequestParam Integer pageSize,
                                        @RequestParam Integer pageNumber){
return studentService.getStudentPage(pageSize,pageNumber);
    }

}
