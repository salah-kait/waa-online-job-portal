package com.MIU.OnlineJob.DbInitializer;

import com.MIU.OnlineJob.DataAccess.UserRepository;
import com.MIU.OnlineJob.Models.User;
import com.MIU.OnlineJob.Models.enums.RoleName;
import com.MIU.OnlineJob.Services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@Configuration
public class DbSeeder {

	@Bean
	public CommandLineRunner commandLineRunner(@Autowired UserRepository userRepository, @Autowired RoleService roleService,
											   @Autowired PasswordEncoder passwordEncoder) {

		return args -> {

		};
	}
}
