package com.example.survey;

import com.example.survey.authstuff.ERole;
import com.example.survey.authstuff.Role;
import com.example.survey.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	RoleRepository roleReposiory;
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		System.out.println("hello world, I have just started up");
		Role role1 = new Role(ERole.ROLE_USER);
		Role role2 = new Role(ERole.ROLE_MODERATOR);
		Role role3 = new Role(ERole.ROLE_ADMIN);
		this.roleReposiory.save(role1);
		this.roleReposiory.save(role2);
		this.roleReposiory.save(role3);
	}

}
