package cn.mansys.Interceptor;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.mansys.dao.TicketDao;
import cn.mansys.dao.UserDao;
import cn.mansys.model.HostHolder;
import cn.mansys.model.Ticket;
import cn.mansys.model.User;

@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	TicketDao ticketDao;
	@Autowired
	UserDao userDao;
	@Autowired
	HostHolder hostHolder;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Ticket ticket = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("ticket")) {
					ticket = ticketDao.getTicketByTicket(c.getValue());
				}
			}
		}
		if (ticket == null || ticket.getExpired().before(new Date()) || ticket.getStaus() != 0) {
			
			return true;
		}
		User user = userDao.getUserById(ticket.getUserId());
		hostHolder.setUser(user);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (hostHolder.getUser() != null && modelAndView!=null) {
			modelAndView.addObject("user", hostHolder.getUser());
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		hostHolder.clear();
	}

}
