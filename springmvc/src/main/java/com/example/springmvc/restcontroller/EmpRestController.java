package com.example.springmvc.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springmvc.dao.EmpDao;
import com.example.springmvc.model.Employee;

import jakarta.validation.Valid;
@RequestMapping("/emprest")

@RestController
public class EmpRestController {

	@Autowired
	EmpDao ed;
	
	@GetMapping("/hello")
	public String greet() {
		return "its working";
	}
	
	@GetMapping("/emp/{empid}")
	public Employee getEmpById(@PathVariable int empid) {
		return  this.ed.fetchEmp(empid);
	}
	
	@PostMapping("/emp")
	public Employee addEmployee( @Valid @RequestBody Employee e) {
		return this.ed.addEmp(e);
	}
	
	@GetMapping("/allemp")
	public List<Employee> getAllEmp(){
		return this.ed.getAllEmp();
	}
	
	@GetMapping("emp/{empid}/annualsal")
	public double empAnnualSal(@PathVariable int empid) {
		double sal=ed.EmpAnnualSal(empid);
		
		return sal;
	}
	
	@GetMapping("emp/{empid}/annualsal/{bonus}")
	public double empAnnualSal(@PathVariable int empid,@PathVariable double bonus) {
		double sal=ed.EmpAnnualSalWithBonus(empid, bonus);
		
		return sal;
	}
	
}
