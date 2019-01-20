package cn.mansys.dao;

import java.util.List;

import cn.mansys.model.Role;

public interface RoleDao {

	public List<Role> getAllRole();
	public Role getRoleById(int id);
	public Role getRoleByName(String name);
}
