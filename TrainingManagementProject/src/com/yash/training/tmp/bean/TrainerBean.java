package com.yash.training.tmp.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.yash.training.tmp.domain.Course;
import com.yash.training.tmp.domain.SubHeading;
import com.yash.training.tmp.domain.User;
import com.yash.training.tmp.service.CourseServiceEJBRemote;
import com.yash.training.tmp.service.SubHeadingServiceEJBRemote;

@ManagedBean
@SessionScoped
public class TrainerBean {

	private Course course;
	@EJB
	CourseServiceEJBRemote courseService;
	@EJB
	SubHeadingServiceEJBRemote subHeadingService;

	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
			.getRequest();
	HttpSession session = request.getSession();
	User user = (User) session.getAttribute("currentUser");

	private List activeCoursesList;
	private int selectedCourseId;
	private int subHeadingId;
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

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public int getSelectedCourseId() {
		return selectedCourseId;
	}

	public void setSelectedCourseId(int selectedCourseId) {
		this.selectedCourseId = selectedCourseId;
	}

	public List getActiveCoursesList() {
		activeCoursesList = courseService.getActiveCourses();
		return activeCoursesList;
	}

	public void setActiveCoursesList(List activeCoursesList) {
		this.activeCoursesList = activeCoursesList;
	}

	public String getAllDetails() {
		course = courseService.getAllCourseDetails(selectedCourseId);
		String returnString = "";
		if (user.getDesignation() == 1) {
			returnString = "detailedCourse";
		} else if (user.getDesignation() == 2) {
			returnString = "trainerDetailedCourse";

		} else {
			returnString = "detailedCourse";

		}
		return returnString;
	}

	public String changeSubHeadingStatus() {
		subHeadingService.updateSubheadingStatus(status, subHeadingId);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + subHeadingId + status);
		course = courseService.getAllCourseDetails(selectedCourseId);
		return "#";
	}

}