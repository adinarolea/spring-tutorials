package com.tutorial.spring.transactional.repository;

import com.tutorial.spring.transactional.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
