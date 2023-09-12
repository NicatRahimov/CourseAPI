package az.coders.CourseAPI.service;

import az.coders.CourseAPI.dto.GroupDTO;
import az.coders.CourseAPI.dto.TeacherDTO;
import az.coders.CourseAPI.exception.GroupNotFound;
import az.coders.CourseAPI.exception.TeacherNotFound;
import az.coders.CourseAPI.model.Group;
import az.coders.CourseAPI.model.Teacher;
import az.coders.CourseAPI.repository.GroupRepository;
import az.coders.CourseAPI.repository.TeacherRepository;
import az.coders.CourseAPI.util.DTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    GroupRepository groupRepository;
    private final DTOMapper dtoMapper = DTOMapper.INSTANCE;
    public ResponseEntity<List<TeacherDTO>> getAllTeacher() {
        List<Teacher> teachers = teacherRepository.findAll();
        List<TeacherDTO> teacherDTOS = dtoMapper.T_EntitiesToDto(teachers);
        for (int i = 0; i < teacherDTOS.size(); i++) {
            teacherDTOS.get(i).setGroups(dtoMapper.G_EntitiesToDto(teachers.get(i).getGroups()));
        }
        return new ResponseEntity<>(teacherDTOS , HttpStatus.OK);
    }

    public ResponseEntity<String> addTeacher(Teacher teacher,List<Integer> groupIds) {
        List<Group>groups=new ArrayList<>();
        for (Integer groupID :
                groupIds) {
            groups.add(groupRepository.findById(groupID).get());
        }

        teacher.setGroups(groups);
        teacherRepository.save(teacher);
        return new ResponseEntity<>("Added successfully",HttpStatus.CREATED);
    }

    public ResponseEntity<TeacherDTO> getTeacherById(Integer id) {
        TeacherDTO teacherDTO ;
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);

        if (optionalTeacher.isPresent()){
            teacherDTO = dtoMapper.T_EntityToDto(optionalTeacher.get());
            List<GroupDTO> groupDTOS = dtoMapper.G_EntitiesToDto(optionalTeacher.get().getGroups());
            teacherDTO.setGroups(groupDTOS);
            return new ResponseEntity<>(teacherDTO,HttpStatus.OK);
        }else throw new TeacherNotFound("There is no teacher with id: "+id);
    }

    public ResponseEntity<String> editTeacher(Teacher teacher,
                                              List<Integer> groupIds,
                                              Integer teacherId) {

        List<Group>groups =new ArrayList<>();
        //Fetch groups by id
        for (Integer groupId:
             groupIds) {
            Optional<Group> optionalGroup = groupRepository.findById(groupId);
            if (optionalGroup.isPresent()){
                groups.add(optionalGroup.get());
            }else throw new GroupNotFound("There is no group with id: "+groupIds);
        }
        //To prevent creating new object in DB
   teacher.setGroups(groups);
   teacher.setId(teacherId);
teacherRepository.save(teacher);
return new ResponseEntity<>("Edited successfully",HttpStatus.OK);
    }

    public ResponseEntity<String> deleteTeacherById(Integer id) {
        Optional<Teacher> teacherOpt = teacherRepository.findById(id);
        if (teacherOpt.isPresent()){
            teacherRepository.deleteById(id);
            return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
        }else throw new TeacherNotFound("There is no teacher id: "+id);
    }
}
