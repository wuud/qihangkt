package cn.mansys.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import cn.mansys.model.HostHolder;

@Component
public class ManagerInterceptor implements HandlerInterceptor{
	
	@Autowired
	HostHolder hostHolder;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(hostHolder.getUser()!=null) {
			if(hostHolder.getUser().getRoleId().getId()<4) {
				request.getRequestDispatcher("/error").forward(request, response);
				return true;
			}
		}
		
		return true;
	}

}
