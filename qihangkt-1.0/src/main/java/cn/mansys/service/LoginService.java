package cn.mansys.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mansys.dao.TicketDao;
import cn.mansys.dao.UserDao;
import cn.mansys.model.Ticket;
import cn.mansys.model.User;

@Service
public class LoginService {

	@Autowired
	UserDao userDao;
	@Autowired
	TicketDao ticketDao;

	public Map<String, String> login(String number, String password,String rememberme) {
		Map<String, String> map = new HashMap<>();
		User u = null;
		if(StringUtils.isBlank(number) || StringUtils.isBlank(password)) {
			map.put("error", "�˺ź����벻��Ϊ�գ�");
			return map;
		}else if (number.contains("@")) {
			u = userDao.getUserByEmail(number);
		} else {
			u = userDao.getUserByPhone(number);
		}
		if(u==null) {
			map.put("error","�˺Ų����ڣ�");
			return map;
		}else if (!u.getPassword().equals(password)) {
			map.put("error", "�˺����벻һ�£�");
			return map;
		}
		String ticket=addTicket(u.getId(), rememberme);
		map.put("ticket", ticket);
		return map;
	}
	public String addTicket(int userId,String rememberme) {
		Ticket t=new Ticket();
		t.setUserId(userId);
		t.setStaus(0);//0��ʾticket��������0��ʾticket�ѹ���
		t.setTicket(UUID.randomUUID().toString().replaceAll("-", ""));
		Date d=new Date();
		//����û���¼ʱѡ����remeberme��ѡ����ticket����������Ҫ��
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
