package com.blogapp.dto.request;

public class DtoPasswordChange {
	private String oldpassword;
	private String newpassword;
	public DtoPasswordChange() {}

	public DtoPasswordChange(String newpassword,String oldpassword) {
		this.newpassword = newpassword;
		this.oldpassword=oldpassword;
	}


	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	
	

}
