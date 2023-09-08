package az.coders.CourseAPI.service;

import az.coders.CourseAPI.dto.GroupDTO;
import az.coders.CourseAPI.exception.GroupNotFound;
import az.coders.CourseAPI.model.Group;
import az.coders.CourseAPI.repository.GroupRepository;
import az.coders.CourseAPI.repository.StudentRepository;
import az.coders.CourseAPI.util.DTOMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    StudentRepository studentRepository;
private final DTOMapper dtoMapper = DTOMapper.INSTANCE;


    public ResponseEntity<GroupDTO> getGroupById(Integer id) {
        Optional<Group>groupOptional = groupRepository.findById(id);
        if(groupOptional.isPresent()){
            return new ResponseEntity<>(dtoMapper.G_EntityToDto(groupOptional.get()),HttpStatus.OK);
        }else throw new GroupNotFound("No item with id: "+id);
    }

    public ResponseEntity<List<GroupDTO>> getAllGroups() {
        List<GroupDTO>groupDTOS=new ArrayList<>();
        for (Group g :
                groupRepository.findAll()) {
            groupDTOS.add(dtoMapper.G_EntityToDto(g));
        }
        return new ResponseEntity<>(groupDTOS,HttpStatus.OK);
    }

    public ResponseEntity<GroupDTO> getGroupByGroupName(String groupName) {
        GroupDTO groupDTO = dtoMapper.G_EntityToDto(groupRepository.findGroupByGroupName(groupName));
        return new ResponseEntity<>(groupDTO,HttpStatus.OK);
    }

    public ResponseEntity<String> addGroup(Group group) {
        groupRepository.save(group);
        return new ResponseEntity<>("Succesful added", HttpStatus.CREATED);
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
        return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
    }
}
