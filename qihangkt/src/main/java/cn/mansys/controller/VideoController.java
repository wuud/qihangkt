package cn.mansys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.mansys.model.Course;
import cn.mansys.model.Video;
import cn.mansys.service.CourseService;
import cn.mansys.service.VideoService;

@Controller
public class VideoController {
	
	@Autowired
	CourseService courseService;
	@Autowired
	VideoService videoService;
	

	@RequestMapping(value = "/course/{courseId}/{videoId}")
	public String coursevideo(Model model, @PathVariable("courseId") int courseId,
								@PathVariable("videoId")int videoId) {
		Course course = courseService.getCourseById(courseId);
		Video video = videoService.getVideoById(videoId);
		course.setPicture(course.getPicture().replace("\\", "/"));
		model.addAttribute("course", course);
		model.addAttribute("video", video);
		return "video";
	}
	
	@RequestMapping(value="/course/{courseId}/list")
	public String videoList(Model model,@PathVariable("courseId")int courseId) {
		List<Video> videoByCourse = videoService.getVideoByCourse(courseId);
		model.addAttribute("videoList",videoByCourse);
		model.addAttribute("courseId",courseId);
		return "videoList";
	}


}
