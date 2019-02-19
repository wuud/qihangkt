package cn.mansys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ExceptionController {
	
	@RequestMapping(value="/error",method=RequestMethod.GET)
	public String error() {
		return "error";
	}
	@RequestMapping(value="/errorAuthority",method=RequestMethod.GET)
	public String notAuthority() {
		return "authority";
	}

}
