package az.coders.CourseAPI.service;

import az.coders.CourseAPI.model.Group;
import az.coders.CourseAPI.model.Teacher;
import az.coders.CourseAPI.repository.GroupRepository;
import az.coders.CourseAPI.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    GroupRepository groupRepository;
    public ResponseEntity<List<Teacher>> getAllTeacher() {
return new ResponseEntity<>(teacherRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<String> addTeacherWGroupName(Teacher teacher) {
        List<Group> groups = teacher.getGroups();
        List<Integer>ids= new ArrayList<>();
        for (Group group :
                groups) {
            ids.add(group.getId());
        }
           List<Group> groups1 = groupRepository.findAllById(ids);
teacher.setGroups(groups1);
        teacherRepository.save(teacher);
        return new ResponseEntity<>("Added successfully",HttpStatus.CREATED);
    }
}
