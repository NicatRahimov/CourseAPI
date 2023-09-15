package az.coders.CourseAPI.util;

import az.coders.CourseAPI.dto.GroupDTO;
import az.coders.CourseAPI.dto.StudentDTO;
import az.coders.CourseAPI.dto.TeacherDTO;
import az.coders.CourseAPI.model.Group;
import az.coders.CourseAPI.model.Student;
import az.coders.CourseAPI.model.Teacher;
import jdk.jfr.Name;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

@Mapper(componentModel = "spring")
public interface DTOMapper {
    DTOMapper INSTANCE = Mappers.getMapper(DTOMapper.class);
    @Mapping(target = "id",source = "id")
    @Mapping(target = "groupName",source = "groupName")
    @Mapping(target = "capacity",source = "capacity")
    @Mapping(target = "studentNumber", source = "students", qualifiedByName = "listSize")
    GroupDTO G_EntityToDto(Group entity);

    @Mapping(target = "id",source = "id")
    @Mapping(target = "name",source = "name")
    @Mapping(target = "surname",source = "surname")
    @Mapping(target = "age",source = "age")
    StudentDTO S_EntityToDto(Student entity);

    @Mapping(target = "id",source = "id")
    @Mapping(target = "name",source = "name")
    @Mapping(target = "surname",source = "surname")
    @Mapping(target = "age",source = "age")
    @Mapping(target = "groups",source = "groups")
    TeacherDTO T_EntityToDto(Teacher entity);

    List<StudentDTO> S_EntitiesToDto (List<Student> students);
    List<TeacherDTO> T_EntitiesToDto (List<Teacher> students);
    List<GroupDTO> G_EntitiesToDto (List<Group> students);

    @Named("listSize")
    default Integer listSize(List<Student> list) {
        return list != null ? list.size() : 0;
    }
}