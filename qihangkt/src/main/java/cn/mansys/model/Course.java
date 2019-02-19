package cn.mansys.model;

public class Course {
	
	
	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Course(String cname, User user, String introduction, String picture,String courseFile) {
		super();
		this.cname = cname;
		this.user = user;
		this.introduction = introduction;
		this.picture = picture;
		this.courseFile=courseFile;
	}
	
	private int id;
	private String cname;
	private User user;
	private String introduction;
	private String picture;
	private String courseFile;
	
	public String getCourseFile() {
		return courseFile;
	}
	public void setCourseFile(String courseFile) {
		this.courseFile = courseFile;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	

}
