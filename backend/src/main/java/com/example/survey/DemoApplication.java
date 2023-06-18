package com.example.survey;

import com.example.survey.authstuff.ERole;
import com.example.survey.authstuff.Role;
import com.example.survey.authstuff.User;
import com.example.survey.repository.RoleRepository;
import com.example.survey.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		System.out.println("hello world, I have just started up");
		
		Role role1 = new Role(ERole.ROLE_USER);
		Role role2 = new Role(ERole.ROLE_MODERATOR);
		Role role3 = new Role(ERole.ROLE_ADMIN);
		this.roleRepository.save(role1);
		this.roleRepository.save(role2);
		this.roleRepository.save(role3);

		User admin = new User();

		Set<Role> admin_roles = new HashSet<>();
		Role adminRole = this.roleRepository.findByName(ERole.ROLE_ADMIN)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		admin_roles.add(adminRole);
		admin.setRoles(admin_roles);
		admin.setUsername("jacek");
		admin.setPassword(encoder.encode("adminadmin"));
		admin.setEmail("jacek@jacek.com");
		userRepository.save(admin);

		User normal_user = new User();
		normal_user.setRoles(Set.of(role1));
		normal_user.setUsername("user");
		normal_user.setPassword(encoder.encode("password"));
		normal_user.setEmail("user@jacek.com");
		userRepository.save(normal_user);
	}

}
