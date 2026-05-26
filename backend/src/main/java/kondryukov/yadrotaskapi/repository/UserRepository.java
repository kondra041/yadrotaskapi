package kondryukov.yadrotaskapi.repository;

import kondryukov.yadrotaskapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    User findRandomUser();
}