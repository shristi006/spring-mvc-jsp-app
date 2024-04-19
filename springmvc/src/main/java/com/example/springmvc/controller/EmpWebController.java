package com.example.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springmvc.dao.EmpDao;
import com.example.springmvc.exception.EmployeeNotFoundException;
import com.example.springmvc.model.Employee;

import jakarta.validation.Valid;


@RequestMapping("empwebcntrl")
@Controller
public class EmpWebController {

	@Autowired
	EmpDao ed;
	
	@GetMapping("/")
	public String getEmpForm(Model model) {
		Employee e=new Employee();
		model.addAttribute("employee", e);
	
		return "emppost";
	}
		
	
		
	
	
	@GetMapping("emp/{empid}")
	public String getEmployee(@PathVariable int empid,ModelMap map) {
		
		try {
			Employee e=this.ed.fetchEmp(empid);
			map.addAttribute("empid",empid);
			map.addAttribute("emp",e);	
		}
		catch(EmployeeNotFoundException e){
			map.addAttribute("empexception", e.getMessage());
		}
		
		return "disemp";
	}
	
	@GetMapping("emp/annualsal/{empid}")
	public String fetchAnnualSal(@PathVariable int empid,ModelMap map) {
		
		try {
			Employee e=this.ed.fetchEmp(empid);
			double sal=e.computeAnnualSalary();
			map.addAttribute("empid",empid);
			map.addAttribute("empannualsal",sal);	
		}
		catch(EmployeeNotFoundException e){
			map.addAttribute("empexception", e.getMessage()+", Hence salary can't be computed");
		}
		
		return "annualsal";
	}
	
	@GetMapping("emp/annualsal/{empid}/{bonus}")
	public String fetchAnnualSalWithBonus(@PathVariable int empid,@PathVariable double bonus,ModelMap map) {
		
		try {
			Employee e=this.ed.fetchEmp(empid);
			double sal=e.computeAnnualSalary(bonus);
			map.addAttribute("empid",empid);
			map.addAttribute("annualsalwithbonus",sal);	
		}
		catch(EmployeeNotFoundException e){
			map.addAttribute("empexception", e.getMessage()+", Hence salary can't be computed");
		}
		
		return "annualsalwithbonus";
	}
	

	@PostMapping("/empp")
	public String addEmployee( @ModelAttribute("employee") @Valid Employee e,BindingResult bindingResult,ModelMap map)
	{
		if (bindingResult.hasErrors()) {
			//logger.info("employee validation failed:Returning back to empform.jsp page");
			return "emppost";
		}
		else
		{
		//logger.info("postemp:"+e);
		Employee e1=this.ed.addEmp(e);
		map.addAttribute("postedemp",e1);
		
		
		return "postempdet";
		}
	}
	
	@GetMapping("/allemp")
	
	public String getAllEmp(ModelMap map) {
		
		List<Employee> lst=this.ed.getAllEmp();
		map.addAttribute("EmpList",lst);
		return "emplist";
	}
	
}
