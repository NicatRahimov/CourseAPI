package az.coders.CourseAPI.repository;

import az.coders.CourseAPI.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserEntity,Integer> {

    UserEntity findByUsername(String username);
}
