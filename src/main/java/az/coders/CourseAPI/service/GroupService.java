package az.coders.CourseAPI.service;

import az.coders.CourseAPI.model.Group;
import az.coders.CourseAPI.repository.GroupRepository;
import az.coders.CourseAPI.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class GroupService {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    StudentRepository studentRepository;

    public ResponseEntity<String> addGroup(Group group) {
        groupRepository.save(group);
        return new ResponseEntity<>("Succesful added", HttpStatus.CREATED);
    }


    public ResponseEntity<List<Group>> getAllGroups() {
        return new ResponseEntity<>(groupRepository.findAll(),HttpStatus.OK);
    }

    public ResponseEntity<String> editGroupById(Group group,Integer id) {
        if (groupRepository.findById(id).isPresent()){
            Group group1 = groupRepository.findById(id).get();
            if (group.getGroupName()!=null){
                group1.setGroupName(group.getGroupName());
            }
            if (group.getCapacity()!=null){
                group1.setCapacity(group.getCapacity());
            }
            groupRepository.save(group1);
            return new ResponseEntity<>("Edited succesfully",HttpStatus.OK);
        }else return new ResponseEntity<>("Nothing changed because you dont insert new data",HttpStatus.OK);
    }

    public ResponseEntity<String> deleteGroupById(Integer id) {
        groupRepository.deleteById(id);
        return new ResponseEntity<>("Deleted succesfully",HttpStatus.OK);
    }
}
