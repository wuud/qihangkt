package cn.mansys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.mansys.model.User;

public interface UserDao {
	
	User getUserById(int id);
	User getUserByPhone(String phone);
	User getUserByEmail(String email);
	
	
	void insetUser(User u);
	void delUser(int id);
	void updateUser(User u);
	
	Integer countAllUser();
	
	
	List<User> searchUser(@Param("userInfo")String userInfo,@Param("pageSize")int pageSize,@Param("offsetNum")int offsetNum);
	List<User> getUsersByPage(@Param("pageSize")int pageSize,@Param("offsetNum")int offsetNum);
	List<User> getUsersByRole(@Param("roleId")int roleId,@Param("pageSize")int pageSize,@Param("offsetNum")int offsetNum);
	
	

}
