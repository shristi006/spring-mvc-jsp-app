package com.example.springmvc.model;

import java.time.LocalDate;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

@Entity
public class Employee {

	@Id
	//@GeneratedValue
	@NotNull(message="Emp id can't be empty")
	int empid;
	
	@NotNull
	@NotBlank(message="Employee name is mandatory")
	private String name;
	
	@NotNull(message="joining date can't be null")
	@Past
	LocalDate joiningDate;
	@NotNull(message="salary can't be null")
	private double salary;
	public Employee() 
	{
		
	}
	
	public Employee(int empid,String name, LocalDate joiningDate, double salary) {
		super();
		this.empid=empid;
		this.name = name;
		this.joiningDate = joiningDate;
		this.salary = salary;
	}

	
	
	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}

	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", name=" + name + ", joiningDate=" + joiningDate + ", salary=" + salary
				+ "]";
	}
	
	public double computeAnnualSalary() {
		System.out.println("The salary of the employee is:");
		return this.getSalary()*12;
	}
	
	public double computeAnnualSalary(double bonus) {
		System.out.println("Annual salary with bonus");
		return this.getSalary()*12+bonus;
	}

	
	
	
	
}
