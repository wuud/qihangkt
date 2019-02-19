package cn.mansys.dao;

import java.util.List;

import cn.mansys.model.Resource;

public interface ResourceDao {
	
	void insertResource(Resource res);
	
	Integer countResourceByPath(String path);
	
	void updateResource(Resource res);
	
	List<Resource> getAllResource();

}
