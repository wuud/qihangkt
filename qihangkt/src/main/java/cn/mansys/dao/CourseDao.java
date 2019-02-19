package cn.mansys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.mansys.model.Course;

public interface CourseDao {
	
	void insertCourse(Course course);
	
	Course getCourseById(Integer id);
	Course getCourseByName(String name);
	List<Course> getCourseByUser(Integer userId);
	List<Course> getAllCourse();
	List<Course> getCourseByPage(@Param("pageSize")int pageSize,@Param("offsetNum")int offsetNum);

}
