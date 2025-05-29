package com.lucas.back.end.java.graphql.api;

import com.lucas.back.end.java.graphql.api.entity.Movie;
import com.lucas.back.end.java.graphql.api.entity.User;
import com.lucas.back.end.java.graphql.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}



}
