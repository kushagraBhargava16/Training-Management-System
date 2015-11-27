package com.yash.training.tmp.bean;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

import com.yash.training.tmp.domain.SubHeading;
import com.yash.training.tmp.service.CourseServiceEJBRemote;
import com.yash.training.tmp.service.HeadingServiceEJBRemote;
import com.yash.training.tmp.service.SubHeadingServiceEJBRemote;

@ManagedBean
@SessionScoped
public class SubHeadingBean {
	private int subHeadingId;
	private String subHeadingTitle;
	private String headingTitle;
	private int headingId;
	private String courseTitle;
	private String status;
	private List headings;

	@EJB
	SubHeadingServiceEJBRemote subHeadingService;
	@EJB
	HeadingServiceEJBRemote headingService;
	@EJB
	CourseServiceEJBRemote courseService;
	@Inject
	CourseBean courseBean;


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
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

	public String getHeadingTitle() {
		return headingTitle;
	}

	public void setHeadingTitle(String headingTitle) {
		this.headingTitle = headingTitle;
	}

	public int getHeadingId() {
		headingId = subHeadingService.getHeadingIdByTitle(headingTitle);
		// System.out.println("++++++++++++++++++++++++++++++++"+headingId);
		return headingId;
	}

	public void setHeadingId(int headingId) {
		this.headingId = headingId;
	}

	public List getHeadings() {
		int courseId = headingService.getCourseIdByTitle(courseTitle);
		headings = subHeadingService.getAllHeadingsByCourseId(courseId);
		return headings;
	}

	/*
	 * public List getHeadings() { int courseId =
	 * headingService.getCourseIdByTitle(courseTitle); headings =
	 * subHeadingService.getAllHeadings(); return headings; }
	 */
	public void setHeadings(List headings) {
		this.headings = headings;
	}

	public String saveSubHeading() {
		SubHeading subHeading = new SubHeading();
		subHeading.setHeadingId(getHeadingId());
		subHeading.setSubHeadingTitle(subHeadingTitle);
		subHeading.setStatus(status);
		System.out.println("--------------------------------subheadimng" + subHeading + "-----------------------");
		subHeadingService.saveSubHeading(subHeading);
		return "courseComposition";
	}

	public String saveSubHeadingAndReturn() {
		SubHeading subHeading = new SubHeading();
		headingId = subHeadingService.getHeadingIdByTitle(headingTitle);
		subHeading.setHeadingId(headingId);
		subHeading.setSubHeadingTitle(subHeadingTitle);
		subHeading.setStatus(status);
		System.out.println("--------------------------------" + subHeading + "-----------------------");
		subHeadingService.saveSubHeading(subHeading);
		return "#";
	}

	public String save() {
		System.out.println("+++++++++++++++++++++++++++++++++++++++hello");
		return "home";
	}

	public void getHeadingList(ValueChangeEvent e) {

		// System.out.println("------------------------------------------------------------inside
		// getHeading()--------------------------------"+e.getNewValue().toString());
		courseTitle = e.getNewValue().toString();
		int courseId = headingService.getCourseIdByTitle(courseTitle);
		headings = subHeadingService.getAllHeadingsByCourseId(courseId);
		System.out.println("__________________________________________" + headings
				+ "___________________________________________________");
	}

	public String changeSubHeadingStatus() {
		subHeadingService.updateSubheadingStatus(status, subHeadingId);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+subHeadingId);
		
		/*courseBean.setCourseId();
		courseBean.getAllDetails();*/
		/*courseBean.setCourse(null);
		courseBean.setCourse(courseService.getAllCourseDetails(courseBean.getCourseIdByTitle(courseTitle)));
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+courseBean.getCourse());
		*/
		return "#";
	}
}
