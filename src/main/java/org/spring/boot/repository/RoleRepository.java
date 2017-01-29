package org.spring.boot.repository;

import org.spring.boot.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Aleksey Stoyokha on 27.01.17.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}
