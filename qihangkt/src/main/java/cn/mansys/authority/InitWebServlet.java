package cn.mansys.authority;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.mansys.model.Resource;
import cn.mansys.service.ResourceService;

public class InitWebServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final String packagePath = "cn/mansys/controller";
	private final String packageName = "cn.mansys.controller";

	private static ApplicationContext applicationContext;

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 具体思路：
	 * 1.  拿到所有的Controller包下的class文件
	 * 2. 利用反射拿到所有的Controller对象，得到所有添加了AuthClass注解的对象
	 * 3. 对所有有AuthClass注解的对象进行操作，拿到类的所有方法
	 * 4.拿到所有方法中由AuthMethod注解的修饰的方法，拿到方法上的RequestMapping注解对象
	 * 5.再拿到RequestMapping对象的value值，说明当前路径是需要进行权限控制的路径
	 * 6.拿到AuthMethod的grade，和RequestMapping对象的value封装成一个Resource对象
	 * 7.将Resources集合注入ServletContext集合
	 */
	@Override
	public void init() throws ServletException {
		int grade=1;
		// 初始化Spring的IOC容器
		ServletContext sc = getServletContext();
		applicationContext = WebApplicationContextUtils.getWebApplicationContext(sc);
		// 拿到要进行权限控制的包的路径
		String packageRealPath = this.getClass().getClassLoader().getResource(packagePath).getPath().substring(1);
		File file = new File(packageRealPath);
		// 遍历文件夹下所有文件，得到所有 .class的文件
		String[] classFilesName = file.list(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				if (name.endsWith(".class")) {
					return true;
				}
				return false;
			}
		});
		List<Resource> resources = new ArrayList<>();
		for (String classFileName : classFilesName) {
			try {
				classFileName = classFileName.substring(0, classFileName.indexOf(".class"));
				// 拿到纯粹的类的包全名
				String classAllpackageName = packageName + "." + classFileName;
				// 通过反射获取到对应的类的对象
				Class clazz = Class.forName(classAllpackageName);
				//如果当前类没有AuthClass注解，直接跳出当前循环
				if (!clazz.isAnnotationPresent(AuthClass.class))
					continue;
				AuthClass authClass=(AuthClass) clazz.getAnnotation(AuthClass.class);
				
				//拿到当前类的所有方法
				Method[] methods = clazz.getDeclaredMethods();
				for(Method mtd:methods) {
					
					grade=authClass.grade();
					//拿到这个方法上的RequestMapping注解对象
					RequestMapping requestMapping = mtd.getAnnotation(RequestMapping.class);
					
					if(!mtd.isAnnotationPresent(AuthMethod.class)) {
						Resource res=new Resource(requestMapping.value()[0], grade);
						resources.add(res);
						continue;
					}
					AuthMethod authMethod = mtd.getAnnotation(AuthMethod.class);
					grade=authMethod.grade();
					Resource res=new Resource(requestMapping.value()[0], grade);
					resources.add(res);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}
		sc.setAttribute("resources", resources);
	}
}
