package cn.mansys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.mansys.model.HostHolder;
import cn.mansys.service.JoinCourseService;

@Controller
public class JoinController {
	
	@Autowired
	JoinCourseService joinCourseService;
	@Autowired
	HostHolder hostHolder;
	
	@RequestMapping(value="/joinCourse",method=RequestMethod.POST)
	public String joinCourse(@RequestParam("courseId")int courseId) {
		if(hostHolder.getUser()==null) {
			return "redirect:/login";
		}
		int userId=hostHolder.getUser().getId();
		joinCourseService.joinCourse(courseId, userId);
		return "redirect:/course/"+courseId;
	}
	@RequestMapping(value="/unJoinCourse",method=RequestMethod.POST)
	public String unJoinCourse(@RequestParam("courseId")int courseId) {
		if(hostHolder.getUser()==null) {
			return "redirect:/login";
		}
		int userId=hostHolder.getUser().getId();
		joinCourseService.unJoinCourse(courseId, userId);
		return "redirect:/course/"+courseId;
	}

}
