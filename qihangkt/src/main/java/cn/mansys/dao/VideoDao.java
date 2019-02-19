package cn.mansys.dao;

import java.util.List;

import cn.mansys.model.Video;

public interface VideoDao {
	
	Video getVideoById(int id);
	
	List<Video> getVideoByCourse(int courseId);
	
	void addVideo(Video video);
	

}
