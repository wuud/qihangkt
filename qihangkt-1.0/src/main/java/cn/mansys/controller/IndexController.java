package cn.mansys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.mansys.model.Course;
import cn.mansys.model.HostHolder;
import cn.mansys.model.User;
import cn.mansys.service.CourseService;
import cn.mansys.service.UserService;

@Controller
public class IndexController {
	
	@Autowired
	UserService userService;
	@Autowired
	HostHolder hostHolder;
	@Autowired
	CourseService courseService;
	
	@RequestMapping(value= {"/index","/"})
	public String index(Model model) {
		User user = hostHolder.getUser();
		model.addAttribute("user",user);
		List<Course> courseList = courseService.getAllCourse();
		model.addAttribute("courseList",courseList);
		return "index";
	}

}
