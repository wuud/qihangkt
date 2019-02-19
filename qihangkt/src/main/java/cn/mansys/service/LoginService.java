package cn.mansys.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mansys.dao.RoleDao;
import cn.mansys.dao.TicketDao;
import cn.mansys.dao.UserDao;
import cn.mansys.model.Role;
import cn.mansys.model.Ticket;
import cn.mansys.model.User;

@Service
public class LoginService {

	@Autowired
	UserDao userDao;
	@Autowired
	TicketDao ticketDao;
	@Autowired
	RoleDao roleDao;

	public Map<String, String> login(String number, String password,String rememberme) {
		Map<String, String> map = new HashMap<>();
		User u = null;
		if(StringUtils.isBlank(number) || StringUtils.isBlank(password)) {
			map.put("error", "账号和密码不能为空！");
			return map;
		}else if (number.contains("@")) {
			u = userDao.getUserByEmail(number);
		} else {
			u = userDao.getUserByPhone(number);
		}
		if(u==null) {
			map.put("error","账号不存在！");
			return map;
		}else if (!u.getPassword().equals(password)) {
			map.put("error", "账号密码不一致！");
			return map;
		}
		String ticket=addTicket(u.getId(), rememberme);
		map.put("ticket", ticket);
		return map;
	}
	
	public Map<String,String> reg(String username,String password,
									String phone,String email){
		Map<String,String> map=new HashMap<>();
		if(userDao.getUserByName(username)!=null) {
			map.put("error", "用户名已被注册！");
			return map;
		}else if(userDao.getUserByPhone(phone)!=null) {
			map.put("error", "手机号已被注册！");
			return map;
		}else if(userDao.getUserByEmail(email)!=null) {
			map.put("error", "邮箱已被注册！");
			return map;
		}
		Role role = roleDao.getRoleById(1);
		User user=new User(username, password, email, phone, 0, new Date(), role);
		userDao.insetUser(user);
		String ticket = addTicket(userDao.getUserByName(username).getId(), "off");
		map.put("ticket", ticket);
		return map;
	}
	
	public String addTicket(int userId,String rememberme) {
		Ticket t=new Ticket();
		t.setUserId(userId);
		t.setStaus(0);//0表示ticket正常，非0表示ticket已过期
		t.setTicket(UUID.randomUUID().toString().replaceAll("-", ""));
		Date d=new Date();
		//如果用户登录时选择了remeberme复选框，则ticket的生命周期要长
		if(rememberme.equals("on")) {
			d.setTime(d.getTime()+(long)3600*24*30*1000);
		}else {
			d.setTime(d.getTime()+(long)3600*24*1000);
		}
		t.setExpired(d);
		ticketDao.insertTicket(t);
		return t.getTicket();
		
	}
	public void logout(String ticket) {
		Ticket t = ticketDao.getTicketByTicket(ticket);
		t.setStaus(1);
		ticketDao.updateTicket(t);
		
	}
	

}
