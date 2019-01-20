package cn.mansys.model;

import java.io.Serializable;

public class Role implements Serializable{
	
	private int id;
	private String rname;
	private String rcode;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getRcode() {
		return rcode;
	}
	public void setRcode(String rcode) {
		this.rcode = rcode;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", rname=" + rname + ", rcode=" + rcode + "]";
	}

}
