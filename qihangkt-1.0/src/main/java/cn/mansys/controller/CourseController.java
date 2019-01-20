package cn.mansys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.mansys.model.Course;
import cn.mansys.service.CourseService;

@Controller
public class CourseController {
	
	@Autowired
	CourseService courseService;
	
	@RequestMapping(value="/course/{courseId}")
	public String courseDetail(Model model,@PathVariable("courseId")int courseId) {
		Course course=courseService.getCourseById(courseId);
		model.addAttribute("course",course);
		
		return "courseDetail";
	}
	@RequestMapping(value="/course/{courseId}/video")
	public String coursevideo(Model model,@PathVariable("courseId")int courseId) {
		Course course=courseService.getCourseById(courseId);
		model.addAttribute("course",course);
		return "video";
	}

}
