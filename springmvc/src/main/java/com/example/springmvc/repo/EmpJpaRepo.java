package com.example.springmvc.repo;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springmvc.model.Employee;

@Repository
public interface EmpJpaRepo extends JpaRepository<Employee,Integer>{

	
}
