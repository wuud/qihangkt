package cn.mansys.dao;

import java.util.List;

import cn.mansys.model.Feedback;

public interface FeedbackDao {
	
	void addFeedback(Feedback feedback);
	
	List<Feedback> getAllFeedback();

}
