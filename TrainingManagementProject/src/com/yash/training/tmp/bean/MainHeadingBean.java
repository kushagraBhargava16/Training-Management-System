package com.yash.training.tmp.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import com.yash.training.tmp.domain.Course;
import com.yash.training.tmp.domain.Heading;
import com.yash.training.tmp.service.HeadingServiceEJBRemote;

@ManagedBean
@SessionScoped
public class MainHeadingBean {
	private String courseTitle;
	private String headingTitle;
	private int courseId;
	private int headingId;
	private List courses;

	@EJB
	HeadingServiceEJBRemote headingService;

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public String getHeadingTitle() {
		return headingTitle;
	}

	public void setHeadingTitle(String headingTitle) {
		this.headingTitle = headingTitle;
	}

	public int getCourseId() {
		courseId = headingService.getCourseIdByTitle(courseTitle);
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getHeadingId() {
		return headingId;
	}

	public void setHeadingId(int headingId) {
		this.headingId = headingId;
	}

	public List getCourses() {
		courses = headingService.getAllCourses();
		return courses;
	}

	public void setCourses(List courses) {
		this.courses = courses;
	}

	public String saveCourseHeading() {
		Heading heading = new Heading();
		heading.setCourseId(getCourseId());
		heading.setHeadingTitle(headingTitle);
		//System.out.println("-----------------------------------------" + heading + "------------------------------");
		headingService.saveHeading(heading);
		return "courseComposition";
	}

	public String saveMoreHeading() {
		Heading heading = new Heading();
		heading.setCourseId(getCourseId());
		heading.setHeadingTitle(headingTitle);
		headingService.saveHeading(heading);
		return "#";
	}

	
}
