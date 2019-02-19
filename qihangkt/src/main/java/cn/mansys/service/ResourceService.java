package cn.mansys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mansys.dao.ResourceDao;
import cn.mansys.dao.UserDao;
import cn.mansys.model.Resource;

@Service
public class ResourceService {

	@Autowired
	ResourceDao resourceDao;
	
	private static boolean flag=true;

	public void initResource(List<Resource> resources) {
		for (Resource res : resources) {
			if (resourceDao.countResourceByPath(res.getPath()) == null
					|| resourceDao.countResourceByPath(res.getPath()) == 0) {
				resourceDao.insertResource(res);
			}else {
				resourceDao.updateResource(res);
			}
		}
		flag=false;
	}
	
	public List<Resource> getAllResource(){
		return resourceDao.getAllResource();
	}
	
	public boolean getFlag() {
		return this.flag;
	}

}
