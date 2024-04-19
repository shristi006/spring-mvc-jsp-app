package com.example.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@RequestMapping("/webcntrl")
@Controller
public class GreetController {

	@GetMapping("/greet")
	public String greetAll() {
		return "greetall";
	}
	
	@ResponseBody//it will act as a rest controller .it will not not try to resolve it as a view name because @RestController=@Controller+@ResponseBody 
	@GetMapping("/greet1")
	public String greetAll1() {
		return " as it is->greetall";
	}
	
	@GetMapping("/greet/{msg}")
	public String greetAll(@PathVariable String msg,ModelMap map) {
		
		map.addAttribute("greetmsg", msg);
		return "greet";
	}
}
