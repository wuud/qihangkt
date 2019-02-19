package cn.mansys.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.mansys.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String log() {
		return "login";
	}
	
	@RequestMapping(value="/doLogin",method=RequestMethod.POST)
	public String doLogin(String number,String password,@RequestParam(defaultValue = "off")String rememberme,Model model,HttpServletResponse response) {
		Map<String, String> map = loginService.login(number, password,rememberme);
		if(map.containsKey("error")) {
			model.addAttribute("error",map.get("error"));
			return "login";
		}
		if(map.containsKey("ticket")) {
			String ticket=map.get("ticket");
			Cookie cookie=new Cookie("ticket", ticket);
			cookie.setPath("/");
			if(rememberme.equals("on")) {
				cookie.setMaxAge(3600*24*7);
			}
			response.addCookie(cookie);
		}else {
			model.addAttribute("error","登录时发生异常！");
		}
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/reg/",method=RequestMethod.POST)
	public String doReg(@RequestParam("username")String username,
			@RequestParam("password")String password,
			@RequestParam("phone")String phone,
			@RequestParam("email")String email,Model model,
			HttpServletResponse response) {
		Map<String, String> map = loginService.reg(username, password, phone, email);
		if(map.containsKey("error")) {
			model.addAttribute("error",map.get("error"));
			return "login";
		}
		if(map.containsKey("ticket")) {
			String ticket=map.get("ticket");
			Cookie cookie=new Cookie("ticket", ticket);
			cookie.setPath("/");
			response.addCookie(cookie);
		}else {
			model.addAttribute("error","登录时发生异常！");
			return "login";
		}
		return "redirect:/";
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(@CookieValue("ticket")String ticket) {
		loginService.logout(ticket);
		return "redirect:/";
	}
}
