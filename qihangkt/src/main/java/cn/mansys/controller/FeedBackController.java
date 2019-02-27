package cn.mansys.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.mansys.dao.FeedbackDao;
import cn.mansys.model.Feedback;
import cn.mansys.model.HostHolder;
import cn.mansys.model.User;

@Controller
public class FeedBackController {
	
	@Autowired
	HostHolder hostHolder;
	@Autowired
	FeedbackDao feedbackDao;
	
	@RequestMapping(value="/feedback",method=RequestMethod.GET)
	public String getFeedBack() {
		return "feedback";
	}
	@RequestMapping(value="/feedback",method=RequestMethod.POST)
	public String postFeedBack(@RequestParam("message") String message,HttpServletRequest request) throws IOException {
		System.out.println(message);
		User localUser=hostHolder.getUser();
		feedbackDao.addFeedback(new Feedback(message, localUser, new Date()));
		
		return "feedback";
	}
	

}
