package az.coders.CourseAPI.repository;

import az.coders.CourseAPI.dto.GroupDTO;
import az.coders.CourseAPI.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group,Integer> {
    Optional<Group> findGroupByGroupName(String groupName);

}
