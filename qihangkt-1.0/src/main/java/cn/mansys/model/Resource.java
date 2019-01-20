package cn.mansys.model;

public class Resource {
	
	private int id;
	private String path;
	private int grade;
	
	public Resource(String path, int grade) {
		super();
		this.path = path;
		this.grade = grade;
	}
	public Resource() {
		super();
	}
	@Override
	public String toString() {
		return "Resource [id=" + id + ", path=" + path + ", grade=" + grade + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}

}
