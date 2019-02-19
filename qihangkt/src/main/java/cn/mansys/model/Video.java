package cn.mansys.model;

public class Video {

	public Video() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Video(String vname, Course course, String videoPath,Double videoSize) {
		super();
		this.vname = vname;
		this.course = course;
		this.videoPath = videoPath;
		this.videoSize=videoSize;
	}
	private int id;
	private String vname;
	private Course course;
	private String videoPath;
	private Double videoSize;
	
	public Double getVideoSize() {
		return videoSize;
	}
	public void setVideoSize(Double videoSize) {
		this.videoSize = videoSize;
	}
	public String getVideoPath() {
		return videoPath;
	}
	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVname() {
		return vname;
	}
	public void setVname(String vname) {
		this.vname = vname;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
}
