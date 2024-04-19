package com.example.springmvc;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.springmvc.model.Employee;
import com.example.springmvc.repo.EmpJpaRepo;

@SpringBootApplication
public class SpringmvcApplication implements CommandLineRunner {

	@Autowired
	EmpJpaRepo ejr;
	public static void main(String[] args) {
		SpringApplication.run(SpringmvcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Employee e1=new Employee(1,"Abhi",LocalDate.of(2021,11,12),30000);
		ejr.save(e1);
		Employee e2=new Employee(2,"Ayushi",LocalDate.of(2024, 01, 22),30000);
		ejr.save(e2);
		
	}

}
