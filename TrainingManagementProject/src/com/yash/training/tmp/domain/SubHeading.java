package com.yash.training.tmp.domain;

import java.io.Serializable;

public class SubHeading implements Serializable {

	private static final long serialVersionUID = 1L;
	private int subHeadingId;
	private String subHeadingTitle;
	private int headingId;
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getSubHeadingId() {
		return subHeadingId;
	}

	public void setSubHeadingId(int subHeadingId) {
		this.subHeadingId = subHeadingId;
	}

	public String getSubHeadingTitle() {
		return subHeadingTitle;
	}

	public void setSubHeadingTitle(String subHeadingTitle) {
		this.subHeadingTitle = subHeadingTitle;
	}

	public int getHeadingId() {
		return headingId;
	}

	public void setHeadingId(int headingId) {
		this.headingId = headingId;
	}

	@Override
	public String toString() {
		return "SubHeading [subHeadingId=" + subHeadingId + ", subHeadingTitle=" + subHeadingTitle + ", headingId="
				+ headingId + "]\n";
	}

}
