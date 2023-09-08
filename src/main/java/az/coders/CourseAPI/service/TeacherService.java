package az.coders.CourseAPI.service;

import az.coders.CourseAPI.model.Group;
import az.coders.CourseAPI.model.Teacher;
import az.coders.CourseAPI.repository.GroupRepository;
import az.coders.CourseAPI.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    public ResponseEntity<String> addTeacher(Teacher teacher,String groupIds) {
        List<String>StIds=new ArrayList<>();
        List<Group>groups=new ArrayList<>();
        if (groupIds.contains(",")){
           StIds.addAll(List.of(groupIds.split(",")));
            for (String StId :
                    StIds) {
                Integer id = Integer.valueOf(StId);
                groups.add(groupRepository.findById(id).get());
            }}else{
           groups.add(groupRepository.findById(Integer.valueOf(groupIds)).get());
        }
        teacher.setGroups(groups);
        teacherRepository.save(teacher);
        return new ResponseEntity<>("Added successfully",HttpStatus.CREATED);
    }
}
