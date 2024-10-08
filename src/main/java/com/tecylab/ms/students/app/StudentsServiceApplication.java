package com.tecylab.ms.students.app;

import com.tecylab.ms.students.app.infrastructure.adapters.output.persistence.models.StudentEntity;
import com.tecylab.ms.students.app.infrastructure.adapters.output.persistence.repository.StudentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
@RequiredArgsConstructor
public class StudentsServiceApplication implements CommandLineRunner {

	private final StudentJpaRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(StudentsServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repository.saveAll(Arrays.asList(
				new StudentEntity(null, "Carlos", "Torres", 39, "carlos.torresmacassi@gmail.com", "Callao"),
				new StudentEntity(null, "Edson", "Torres", 37, "edson.torresmacassi@gmail.com", "Callao"),
				new StudentEntity(null, "Waldir", "Torres", 35, "waldir.torresmacassi@gmail.com", "Callao")
		));
	}
}
