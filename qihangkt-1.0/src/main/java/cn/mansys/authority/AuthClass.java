package cn.mansys.authority;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/**
 * 	用来修饰类，grade表示资源等级，如果在AuthClass注解中声明了grade，
 * 	表明该类中的所有方法对应的资源等级都是grade，如果在这个类里面又使用了AuthMethod注解，
 * 	则AuthMethod注解的grade值会覆盖AuthClass的grade
 * @author wuu
 * 2019年1月19日
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthClass {
	
	public int grade() default 1;

}
