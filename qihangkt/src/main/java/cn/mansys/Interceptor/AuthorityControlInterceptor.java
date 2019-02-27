package cn.mansys.Interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.HandlerInterceptor;

import cn.mansys.model.HostHolder;
import cn.mansys.model.Resource;
import cn.mansys.service.ResourceService;

@Component
public class AuthorityControlInterceptor implements HandlerInterceptor,ServletContextAware {
	@Autowired
	HostHolder hostHolder;
	@Autowired
	ResourceService resourceService;
	
	private ServletContext servletContext;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 如果没有登录，则不做权限控制
		if (hostHolder.getUser() == null)
			return true;
		
		List<Resource> resources=null;
		if(resourceService.getAllResource().size()!=0) {
			resources = resourceService.getAllResource();
		}else {
			resources=(List<Resource>) servletContext.getAttribute("resources");
			servletContext.removeAttribute("resources");
			resourceService.initResource(resources);
		}
		
		String thisPath = request.getRequestURL().toString();
		
		Map<String, Integer> pathes = new HashMap<>();
		for (Resource res : resources) {
			pathes.put(res.getPath(), res.getGrade());
		}
		for (String path : pathes.keySet()) {
			if (thisPath.endsWith(path)) {
				int userId = hostHolder.getUser().getRoleId().getId();
				if (userId < pathes.get(path)) {
					request.getRequestDispatcher("/error").forward(request, response);
					return true;
				}
			}
		}

		return true;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext=servletContext;
	}

}
