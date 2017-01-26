package repository;

import entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mr_St on 27.01.17.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
