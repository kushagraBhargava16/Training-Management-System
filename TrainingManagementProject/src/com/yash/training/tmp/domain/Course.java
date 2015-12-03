package com.yash.training.tmp.domain;

import java.io.Serializable;
import java.util.List;

public class Course implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int courseId;
	String courseTitle;
	String description;
	int status;
	String refrenceCode;
	int user_id;
	String courseAction;
	List headingList;
	
	
	public List getHeadingList() {
		return headingList;
	}

	public void setHeadingList(List headingList) {
		this.headingList = headingList;
	}

	public String getCourseAction() {
		return courseAction;
	}

	public void setCourseAction(String courseAction) {
		this.courseAction = courseAction;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRefrenceCode() {
		return refrenceCode;
	}

	public void setRefrenceCode(String refrenceCode) {
		this.refrenceCode = refrenceCode;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseTitle=" + courseTitle + ", description=" + description
				+ ", status=" + status + ", refrenceCode=" + refrenceCode + ", user_id=" + user_id + ", courseAction="
				+ courseAction + ", headingList=" + headingList + "]\n";
	}



	

}
