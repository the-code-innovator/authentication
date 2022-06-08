package io.github.thecodeinnovator.authentication.service.implementation;

import org.springframework.stereotype.Service;

import io.github.thecodeinnovator.authentication.entity.Role;
import io.github.thecodeinnovator.authentication.repository.RoleRepository;
import io.github.thecodeinnovator.authentication.service.interfaces.RoleServiceInterface;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class RoleServiceImplementation implements RoleServiceInterface {
    private final RoleRepository roleRepository;

    @Override
    public Role saveRole(Role role) {
        log.info("Saving Role[{}] to Database.", role.toString());
        return roleRepository.save(role);
    }
}
