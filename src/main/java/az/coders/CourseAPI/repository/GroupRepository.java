package az.coders.CourseAPI.repository;

import az.coders.CourseAPI.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group,Integer> {
    Group findByGroupName(String groupName);
}
