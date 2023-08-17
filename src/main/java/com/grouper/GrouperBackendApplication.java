package com.grouper;


import com.grouper.grouper_model.GrouperRole;
import com.grouper.grouper_repository.GrouperRoleRepository;
import com.grouper.grouper_service_layer.GrouperUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class GrouperBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrouperBackendApplication.class, args);

	}

	@Bean
	public CommandLineRunner run(GrouperRoleRepository roleRepository,
								 GrouperUserService userService){
		return args -> {
			roleRepository.save(new GrouperRole(1, "USER"));
		};
	}
}
