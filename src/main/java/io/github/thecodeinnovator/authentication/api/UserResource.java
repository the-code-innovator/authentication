package io.github.thecodeinnovator.authentication.api;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.thecodeinnovator.authentication.api.formdata.UserToRoleDataForm;
import io.github.thecodeinnovator.authentication.entity.Role;
import io.github.thecodeinnovator.authentication.entity.User;
import io.github.thecodeinnovator.authentication.service.interfaces.RoleServiceInterface;
import io.github.thecodeinnovator.authentication.service.interfaces.UserServiceInterface;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserResource {
    private final UserServiceInterface userServiceInterface;
    private final RoleServiceInterface roleServiceInterface;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userServiceInterface.getUsers());
    }

    @PostMapping("/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userServiceInterface.saveUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(roleServiceInterface.saveRole(role));
    }

    @PostMapping("/role/maptouser")
    public ResponseEntity<?> mapRoleToUser(@RequestBody UserToRoleDataForm form) {
        userServiceInterface.addRoleToUser(form.getUsername(), form.getRolename());
        return ResponseEntity.ok().build();
    }
}