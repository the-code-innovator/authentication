package io.github.thecodeinnovator.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.thecodeinnovator.authentication.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
