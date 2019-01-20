package cn.mansys.dao;

import java.util.List;

import cn.mansys.model.Course;

public interface CourseDao {
	
	void insertCourse(Course course);
	
	Course getCourseById(Integer id);
	Course getCourseByName(String name);
	List<Course> getCourseByUser(Integer userId);
	List<Course> getAllCourse();

}
