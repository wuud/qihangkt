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
	 * ����˼·��
	 * 1.  �õ����е�Controller���µ�class�ļ�
	 * 2. ���÷����õ����е�Controller���󣬵õ����������AuthClassע��Ķ���
	 * 3. ��������AuthClassע��Ķ�����в������õ�������з���
	 * 4.�õ����з�������AuthMethodע������εķ������õ������ϵ�RequestMappingע�����
	 * 5.���õ�RequestMapping�����valueֵ��˵����ǰ·������Ҫ����Ȩ�޿��Ƶ�·��
	 * 6.�õ�AuthMethod��grade����RequestMapping�����value��װ��һ��Resource����
	 * 7.��Resources����ע��ServletContext����
	 */
	@Override
	public void init() throws ServletException {
		int grade=1;
		// ��ʼ��Spring��IOC����
		ServletContext sc = getServletContext();
		applicationContext = WebApplicationContextUtils.getWebApplicationContext(sc);
		// �õ�Ҫ����Ȩ�޿��Ƶİ���·��
		String packageRealPath = this.getClass().getClassLoader().getResource(packagePath).getPath().substring(1);
		File file = new File(packageRealPath);
		// �����ļ����������ļ����õ����� .class���ļ�
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
				// �õ��������İ�ȫ��
				String classAllpackageName = packageName + "." + classFileName;
				// ͨ�������ȡ����Ӧ����Ķ���
				Class clazz = Class.forName(classAllpackageName);
				//�����ǰ��û��AuthClassע�⣬ֱ��������ǰѭ��
				if (!clazz.isAnnotationPresent(AuthClass.class))
					continue;
				AuthClass authClass=(AuthClass) clazz.getAnnotation(AuthClass.class);
				
				//�õ���ǰ������з���
				Method[] methods = clazz.getDeclaredMethods();
				for(Method mtd:methods) {
					
					grade=authClass.grade();
					//�õ���������ϵ�RequestMappingע�����
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
