package io.github.thecodeinnovator.authentication;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.github.thecodeinnovator.authentication.entity.Role;
import io.github.thecodeinnovator.authentication.entity.User;
import io.github.thecodeinnovator.authentication.service.interfaces.RoleServiceInterface;
import io.github.thecodeinnovator.authentication.service.interfaces.UserServiceInterface;

@SpringBootApplication
public class AuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(RoleServiceInterface roleServiceInterface, UserServiceInterface userServiceInterface) {
		return args -> {
			roleServiceInterface.saveRole(new Role(null, "USER"));
			roleServiceInterface.saveRole(new Role(null, "MANAGER"));
			roleServiceInterface.saveRole(new Role(null, "ADMIN"));
			roleServiceInterface.saveRole(new Role(null, "SUPERUSER"));

			userServiceInterface.saveUser(new User(null, "Tamashin Hasume", "tamashin", "1234", new ArrayList<>()));
			userServiceInterface.addRoleToUser("tamashin", "USER");
			userServiceInterface.addRoleToUser("tamashin", "MANAGER");

			userServiceInterface.saveUser(new User(null, "Aravind M", "aravind", "1234", new ArrayList<>()));
			userServiceInterface.addRoleToUser("aravind", "USER");
			userServiceInterface.addRoleToUser("aravind", "ADMIN");

			userServiceInterface.saveUser(new User(null, "Anonymous", "anonymous", "1234", new ArrayList<>()));
			userServiceInterface.addRoleToUser("anonymous", "USER");
			userServiceInterface.addRoleToUser("anonymous", "SUPERUSER");
		};
	}
}
