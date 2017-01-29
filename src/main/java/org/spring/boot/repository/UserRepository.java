package org.spring.boot.repository;

import org.spring.boot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Aleksey Stoyokha on 27.01.17.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
