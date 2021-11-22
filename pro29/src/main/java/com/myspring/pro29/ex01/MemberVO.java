package com.myspring.pro29.ex01;

public class MemberVO {
	
	private String id;
	private String pwd;
	private String name;
	private String email;
	
	public MemberVO() {
		
	}
	
	public void setId(String id) {
		this.id=id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pwd=" + pwd + ", name=" + name + ", email=" + email + "]";
	}
	
	
	
	

}
