package cn.mansys.authority;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/**
 * 	���������࣬grade��ʾ��Դ�ȼ��������AuthClassע����������grade��
 * 	���������е����з�����Ӧ����Դ�ȼ�����grade������������������ʹ����AuthMethodע�⣬
 * 	��AuthMethodע���gradeֵ�Ḳ��AuthClass��grade
 * @author wuu
 * 2019��1��19��
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthClass {
	
	public int grade() default 1;

}
