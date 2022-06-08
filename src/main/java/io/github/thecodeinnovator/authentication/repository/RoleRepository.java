package io.github.thecodeinnovator.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.thecodeinnovator.authentication.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
