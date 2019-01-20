package cn.mansys.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import cn.mansys.dao.UserDao;
import cn.mansys.model.Role;
import cn.mansys.model.User;

@Service
public class UserService {
	
	@Autowired
	UserDao userDao;
	@Autowired
	RoleService roleService;
	
	public User getUserById(int id) {
		return userDao.getUserById(id);
	}
	public void insertUser(User u) {
		u.setAddDate(new Date());
		u.setStatus(1);//1��ʾ����״̬����1��ʾ�˻��쳣
		userDao.insetUser(u);
	}
	public Integer countAllUser() {
		return userDao.countAllUser();
	}
	
	public List<User> getUsersByPage(int page,int pageSize){
		int offsetNum=page*pageSize;
		return userDao.getUsersByPage(pageSize,offsetNum);
	}
	public void delUser(int id) {
		userDao.delUser(id);
	}
	public void batchDelUsers(int [] ids) {
		for(int i:ids) {
			userDao.delUser(i);
		}
	}
	
	public List<User> searchUser(String userInfo,int pageSize,int page){
		int offsetNum=pageSize*page;
		return userDao.searchUser("%"+userInfo+"%",pageSize,offsetNum);
	}
	
	public List<User> getUsersByRole(int roleId,int pageSize,int page){
		int offsetNum=pageSize*page;
		return userDao.getUsersByRole(roleId, pageSize, offsetNum);
	}
	
	public void updateUser(User u) {
		userDao.updateUser(u);
	}
	public void pageControl(Model model,int roleId,int page,int pageSize) {
		List<Role> roleList = roleService.getAllRole();
		model.addAttribute("roleList", roleList);
		// ����ҳ��ѯ�ĵ������ݴ���ǰ��
		List<User> userList = getUsersByRole(roleId, pageSize, page);
		model.addAttribute("userList", userList);
		// ����û���������ǰ����ʾ
		Integer total = countAllUser();
		// �����ҳ��
		int totalPages = (int) Math.ceil(((double) total / pageSize));
		// �����һҳ
		int prevPage = 0;
		if (page > 0) {
			prevPage = page - 1;
		}
		// �����һҳ
		int nextPage = totalPages;
		if (page < totalPages) {
			nextPage = page + 1;
		}
		model.addAttribute("total", total);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("page", page);
		model.addAttribute("prevPage", prevPage);
		model.addAttribute("nextPage", nextPage);
	}

}
