package cn.mansys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mansys.dao.VideoDao;
import cn.mansys.model.Video;

@Service
public class VideoService {
	
	@Autowired
	VideoDao videoDao;
	
	public Video getVideoById(int id) {
		return videoDao.getVideoById(id);
	}
	public List<Video> getVideoByCourse(int courseId){
		return videoDao.getVideoByCourse(courseId);
	}
	
	public void addVideo(Video video) {
		
		videoDao.addVideo(video);
	}

}
