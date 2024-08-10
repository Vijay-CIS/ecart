package com.demo.ecart;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.demo.ecart.entity.User;
import com.demo.ecart.repository.UserRepository;

@SpringBootApplication
public class EcartApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepo;

	public static void main(String[] args) {
		SpringApplication.run(EcartApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		createUser();

	}

	private void createUser() {
		List<User> user = new ArrayList<>();
		user.add(new User(1l, "vijay", "vijay123", "vijay@gmail.com"));
		user.add(new User(2l, "rohith", "rohith123", "rohith@gmail.com"));
		user.add(new User(3l, "suriya", "suriya123", "suriya@gmail.com"));
		userRepo.saveAll(user);
	}

}
