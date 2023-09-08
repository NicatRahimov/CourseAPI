package az.coders.CourseAPI.util;

import az.coders.CourseAPI.dto.GroupDTO;
import az.coders.CourseAPI.dto.StudentDTO;
import az.coders.CourseAPI.dto.TeacherDTO;
import az.coders.CourseAPI.model.Group;
import az.coders.CourseAPI.model.Student;
import az.coders.CourseAPI.model.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DTOMapper {
    DTOMapper INSTANCE = Mappers.getMapper(DTOMapper.class);
    @Mapping(target = "id",source = "id")
    @Mapping(target = "groupName",source = "groupName")
    @Mapping(target = "capacity",source = "capacity")
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
    TeacherDTO T_EntityToDto(Teacher entity);

}