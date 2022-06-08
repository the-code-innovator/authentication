package io.github.thecodeinnovator.authentication.service.implementation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.thecodeinnovator.authentication.entity.Role;
import io.github.thecodeinnovator.authentication.entity.User;
import io.github.thecodeinnovator.authentication.repository.RoleRepository;
import io.github.thecodeinnovator.authentication.repository.UserRepository;
import io.github.thecodeinnovator.authentication.service.interfaces.UserServiceInterface;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImplementation implements UserServiceInterface, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        log.info("Saving User[{}] to Database.", user.toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(rolename);
        log.info("Adding Role[{}] to User[{}] in Database.", role.toString(), user.toString());
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        User fetchedUser = userRepository.findByUsername(username);
        log.info("Fetching User[{}] from Database.", fetchedUser.toString());
        return fetchedUser;
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching All Users from Database.");
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.error("User with User[username: {}] not found in the Database.", username);
            throw new UsernameNotFoundException(String.format("User with User[username: {}] not found in the Database.", username));
        } else {
            log.info("User with User[username: {}] found in the Database.", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}