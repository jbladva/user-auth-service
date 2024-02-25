package com.jbpesto.authmicro;

import com.jbpesto.authmicro.model.Role;
import com.jbpesto.authmicro.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAuthServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(RoleRepository roleRepo) {
		return (args) -> {
			Role role=new Role();
			role.setName("ROLE_ADMIN");
			roleRepo.save(role);
		};
	}

}
