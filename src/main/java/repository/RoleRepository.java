package repository;

import entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mr_St on 27.01.17.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}