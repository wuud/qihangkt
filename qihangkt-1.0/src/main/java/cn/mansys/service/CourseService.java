package cn.mansys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mansys.dao.CourseDao;
import cn.mansys.model.Course;

@Service
public class CourseService {
	
	@Autowired
	CourseDao courseDao;
	
	public void insertCourse(Course c) {
		courseDao.insertCourse(c);
	}
	
	public Course getCourseById(int id) {
		return courseDao.getCourseById(id);
		
	}
	public Course getCourseByName(String name) {
		return courseDao.getCourseByName(name);
	}
	public List<Course> getCourseByUser(int userId) {
		return courseDao.getCourseByUser(userId);
		
	}
	public List<Course> getAllCourse() {
		return courseDao.getAllCourse();
	}

}
