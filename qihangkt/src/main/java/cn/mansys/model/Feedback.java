package cn.mansys.model;

import java.util.Date;

public class Feedback {
	
	public Feedback(String message, User user, Date createDate) {
		super();
		this.message = message;
		this.user = user;
		this.createDate = createDate;
	}
	private int id;
	private String message;
	private User user;
	private Date createDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
