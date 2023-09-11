package az.coders.CourseAPI.service;

import az.coders.CourseAPI.dto.GroupDTO;
import az.coders.CourseAPI.dto.StudentDTO;
import az.coders.CourseAPI.exception.GroupIsFull;
import az.coders.CourseAPI.exception.StudentNotFound;
import az.coders.CourseAPI.model.Group;
import az.coders.CourseAPI.model.Student;
import az.coders.CourseAPI.repository.GroupRepository;
import az.coders.CourseAPI.repository.StudentRepository;
import az.coders.CourseAPI.util.DTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    GroupRepository groupRepository;
   private final DTOMapper dtoMapper = DTOMapper.INSTANCE;

    public ResponseEntity<List<StudentDTO>> getStudents() {
        List<StudentDTO>studentDTOS =  dtoMapper.S_EntitiesToDto(studentRepository.findAll());
        return new ResponseEntity<>(studentDTOS,HttpStatus.OK);
    }
    public ResponseEntity<StudentDTO> getStudentById(Integer id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()){
            StudentDTO studentDTO = dtoMapper.S_EntityToDto(studentOptional.get());
            return new ResponseEntity<>(studentDTO,HttpStatus.OK);
        }else throw new StudentNotFound("There is no student with id: "+id);
    }

    public ResponseEntity<List<StudentDTO>> getStudentsByGroupName(String groupName) {
        List<StudentDTO>studentDTOS = new ArrayList<>();
        Group group = groupRepository.findGroupByGroupName(groupName);
        for (Student s :
                group.getStudents()) {
            studentDTOS.add(dtoMapper.S_EntityToDto(s));
        }
        return new ResponseEntity<>(studentDTOS,HttpStatus.OK);
    }
    public ResponseEntity<String> addStudent(Student student,String groupName){
        Group group = groupRepository.findGroupByGroupName(groupName);
        int studentsCount = studentRepository.findStudentsByGroupId(group.getId()).size();
        if (studentsCount<group.getCapacity()){
            student.setGroup(group);
            studentRepository.save(student);
            return new ResponseEntity<>("Succesful added", HttpStatus.CREATED);
        }else{
            throw new GroupIsFull("Group is full for now.Please try again tomorrow");
        }
    }
    public ResponseEntity<String> editStudentById(Integer id, Student student) {
       Optional<Student> studentOptional = studentRepository.findById(id);
       Student student1;
       if (studentOptional.isPresent()){
           student1=studentOptional.get();
           student1.setName(student.getName());
           student1.setSurname(student.getSurname());
           student1.setMail(student.getMail());
            student1.setAge(student.getAge());
            student1.setAddress(student.getAddress());
            student1.setContactNumber(student.getContactNumber());
            studentRepository.save(student1);
            return new ResponseEntity<>("Edited succesfully",HttpStatus.OK);
       }else throw new StudentNotFound("There is no student with id: "+id);
    }

    public ResponseEntity<String> deleteStudentById(Integer id) {
        try{
            studentRepository.deleteById(id);
            return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("There is no student with id: "+id,HttpStatus.BAD_REQUEST);
        }
    }
}
