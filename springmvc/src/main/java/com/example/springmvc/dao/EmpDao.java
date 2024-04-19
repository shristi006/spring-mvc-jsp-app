package com.example.springmvc.dao;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springmvc.exception.EmployeeNotFoundException;
import com.example.springmvc.model.Employee;
import com.example.springmvc.repo.EmpJpaRepo;

@Service
public class EmpDao {

	@Autowired
	EmpJpaRepo er;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	public Employee addEmp(Employee e) {
		 e=er.save(e);
		logger.info("addEmp () method called..");
		return e;
	}
	
	public Employee fetchEmp(int id) {
		Optional<Employee>ope=er.findById(id);
		if(ope.isPresent()) {
			return ope.get();
		}
		else {
			throw new EmployeeNotFoundException("Employee with employee id :"+id+" not found");
		}
		
	}
	
	public List<Employee> getAllEmp(){
		return er.findAll();
	}
	
	public double EmpAnnualSal(int id) {
		Optional<Employee>ope=er.findById(id);
		if(ope.isPresent()) {
			double sal=ope.get().computeAnnualSalary();
			return sal;
		}
		else {
			throw new EmployeeNotFoundException("Employee with employee id:"+id+"not found,Hence salary cannot be computed");
		}
		
	}
	
	public double EmpAnnualSalWithBonus(int id,double bonus) {
		Optional<Employee>ope=er.findById(id);
		if(ope.isPresent()) {
			double sal=ope.get().computeAnnualSalary(bonus);
			return sal;
		}
		else {
			throw new EmployeeNotFoundException("Employee with employee id:"+id+"not found,Hence salary cannot be computed");
		}
		
	}
}
