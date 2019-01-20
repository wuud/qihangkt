package cn.mansys.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import cn.mansys.model.HostHolder;

@Component
public class AlreadyLoginInterceptor implements HandlerInterceptor{
	
	@Autowired
	HostHolder hostHolder;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(hostHolder.getUser()!=null) {
			response.sendRedirect(request.getContextPath());
		}
		
		return true;
	}

}
