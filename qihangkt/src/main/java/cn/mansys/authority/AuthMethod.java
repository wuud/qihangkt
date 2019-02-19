package cn.mansys.authority;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/**
 * 	用来修饰方法，。声明方法对应路径的资源等级
 * 	必须和AuthClass注解配合使用
 * @author wuu
 * 2019年1月19日
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthMethod {
	
	public int grade()default 1;

}
