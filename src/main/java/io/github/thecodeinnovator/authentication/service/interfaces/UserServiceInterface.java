package io.github.thecodeinnovator.authentication.service.interfaces;

import java.util.List;

import io.github.thecodeinnovator.authentication.entity.User;

public interface UserServiceInterface {
    User saveUser(User user);
    void addRoleToUser(String username, String rolename);
    User getUser(String username);
    List<User> getUsers();
}