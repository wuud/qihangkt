package cn.mansys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mansys.dao.RoleDao;
import cn.mansys.model.Role;

@Service
public class RoleService {
	
	@Autowired
	RoleDao roleDao;
	
	public List<Role> getAllRole(){
		return roleDao.getAllRole();
	}
	public Role getRoleById(int id) {
		return roleDao.getRoleById(id);
	}
	public Role getRoleByName(String name) {
		return roleDao.getRoleByName(name);
	}

}
