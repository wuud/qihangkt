package cn.mansys.authority;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/**
 * 	�������η�����������������Ӧ·������Դ�ȼ�
 * 	�����AuthClassע�����ʹ��
 * @author wuu
 * 2019��1��19��
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthMethod {
	
	public int grade()default 1;

}
