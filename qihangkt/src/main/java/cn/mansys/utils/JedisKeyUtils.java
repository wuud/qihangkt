package cn.mansys.utils;

public class JedisKeyUtils {
	
	private final static String SPLIT=":";
	private final static String JOIN="join";
	
	/**
	 *  课程参加用户
	 */
	public static String getJoinCourseKey(int courseId) {
		return JOIN+SPLIT+courseId;
	}
	/**
	 * 用户参加的课程
	 */
	public static String getUserJoinKey(int userId) {
		return userId+SPLIT+JOIN;
	}
	

}
