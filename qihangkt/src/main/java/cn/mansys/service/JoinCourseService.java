package cn.mansys.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mansys.utils.JedisKeyUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

@Service
public class JoinCourseService {
	
	@Autowired
	JedisAdapter jedisAdapter;
	
	/**
	 * 用户参加课程
	 */
	public boolean joinCourse(int courseId,int userId) {
		String joinCourseKey = JedisKeyUtils.getJoinCourseKey(courseId);
		String userJoinKey = JedisKeyUtils.getUserJoinKey(userId);
		
		Jedis jedis = jedisAdapter.getJedis();
		Transaction transaction = jedisAdapter.multi(jedis);
		jedisAdapter.zadd(joinCourseKey, new Date().getTime(), String.valueOf(userId));
		jedisAdapter.zadd(userJoinKey, new Date().getTime(), String.valueOf(courseId));
		List<Object> exec = jedisAdapter.exec(transaction, jedis);
		
		return exec.size()==2?true:false;
		
	}
	/**
	 * 用户取消参加课程
	 */
	public boolean unJoinCourse(int courseId,int userId) {
		String joinCourseKey = JedisKeyUtils.getJoinCourseKey(courseId);
		String userJoinKey = JedisKeyUtils.getUserJoinKey(userId);
		
		Jedis jedis = jedisAdapter.getJedis();
		Transaction transaction = jedisAdapter.multi(jedis);
		jedisAdapter.zrem(joinCourseKey, String.valueOf(userId));
		jedisAdapter.zrem(userJoinKey, String.valueOf(courseId));
		List<Object> exec = jedisAdapter.exec(transaction, jedis);
		
		return exec.size()==2?true:false;
	}
	/**
	 * 判断用户是否参加了课程
	 */
	public boolean isJoin(int courseId,int userId) {
		List<Integer> courseUsers = getCourseUsers(courseId);
		return courseUsers.contains(userId);
	}
	
	/**
	 * 得到当前课程的所有参加用户
	 */
	public List<Integer> getCourseUsers(int courseId){
		String joinCourseKey = JedisKeyUtils.getJoinCourseKey(courseId);
		
		Set<String> zrevrange = jedisAdapter.zrevrange(joinCourseKey, 0, countCourses(courseId));
		if(zrevrange==null) {
			return null;
		}
		List<Integer> listFromSet = getListFromSet(zrevrange);
		return listFromSet;
	}
	
	/**
	 * 得到当前用户参加了哪些课程
	 */
	public List<Integer> getUserCourses(int userId){
		String userJoinKey = JedisKeyUtils.getUserJoinKey(userId);
		
		Set<String> zrevrange = jedisAdapter.zrevrange(userJoinKey, 0, countUsers(userId));
		if(zrevrange==null) {
			return null;
		}
		List<Integer> listFromSet = getListFromSet(zrevrange);
		return listFromSet; 
	}
	
	/**
	 * 得到用户参加了多少课程
	 */
	public Integer countUsers(int userId) {
		String userJoinKey = JedisKeyUtils.getUserJoinKey(userId);
		long count = jedisAdapter.zcard(userJoinKey);
		return (int) count;
	}
	/**
	 * 得到该课程有多少人参加
	 */
	public Integer countCourses(int courseId) {
		String joinCourseKey = JedisKeyUtils.getJoinCourseKey(courseId);
		long count = jedisAdapter.zcard(joinCourseKey);
		return (int) count;
	}
	
	private List<Integer> getListFromSet(Set<String> set){
		List<Integer> list=new ArrayList<>();
		for(String s:set) {
			list.add(Integer.parseInt(s));
		}
		return list;
	}
	
}
