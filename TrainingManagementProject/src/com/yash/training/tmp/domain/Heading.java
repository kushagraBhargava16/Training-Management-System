package com.yash.training.tmp.domain;

import java.io.Serializable;
import java.util.List;

public class Heading implements Serializable {

	private static final long serialVersionUID = 1L;
	private String headingTitle;
	private int headingId;
	private int courseId;
	private List subHeadingList;
	
	public List getSubHeadingList() {
		return subHeadingList;
	}

	public void setSubHeadingList(List subHeadingList) {
		this.subHeadingList = subHeadingList;
	}

	public String getHeadingTitle() {
		return headingTitle;
	}

	public void setHeadingTitle(String headingTitle) {
		this.headingTitle = headingTitle;
	}

	public int getHeadingId() {
		return headingId;
	}

	public void setHeadingId(int headingId) {
		this.headingId = headingId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	@Override
	public String toString() {
		return "Heading [headingTitle=" + headingTitle + ", headingId=" + headingId + ", courseId=" + courseId
				+ ", subHeadingList=" + subHeadingList + "]\n";
	}

	
	
}
