package az.coders.CourseAPI.service;

import az.coders.CourseAPI.dto.GroupDTO;
import az.coders.CourseAPI.exception.GroupNotFound;
import az.coders.CourseAPI.model.Group;
import az.coders.CourseAPI.model.Student;
import az.coders.CourseAPI.repository.GroupRepository;
import az.coders.CourseAPI.repository.StudentRepository;
import az.coders.CourseAPI.util.DTOMapper;
import jakarta.transaction.Transactional;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        List<Group> groupList = groupRepository.findAll();
        List<GroupDTO>groupDTOS=  dtoMapper.G_EntitiesToDto(groupList);
        return new ResponseEntity<>(groupDTOS,HttpStatus.OK);
    }

    public ResponseEntity<GroupDTO> getGroupByGroupName(String groupName) {
        Optional<Group> optionalGroup = groupRepository.findGroupByGroupName(groupName);
        if (optionalGroup.isPresent()){
            GroupDTO groupDTO = dtoMapper.G_EntityToDto(optionalGroup.get());
            return new ResponseEntity<>(groupDTO,HttpStatus.OK);
        }else throw new GroupNotFound("There is no group named with: "+groupName);
    }
@Transactional
    public ResponseEntity<String> addGroup(Group group) {
        groupRepository.save(group);
        return new ResponseEntity<>("Succesful added", HttpStatus.CREATED);
    }

    @Transactional
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
    @Transactional

    public ResponseEntity<String> deleteGroupById(Integer id) {
        groupRepository.deleteById(id);
        return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
    }

    public Page<GroupDTO> getGroupPage(Integer pageSize, Integer pageNumber, String fieldName, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), fieldName);
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        Page<Group> groupPage = groupRepository.findAll(pageable);
        List<GroupDTO> groupDTOS = groupPage.stream().map(group -> dtoMapper.G_EntityToDto(group)).toList();
        return new PageImpl<>(groupDTOS,pageable,groupPage.getTotalElements());
    }
}
