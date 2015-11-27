package com.yash.training.tmp.bean;

import java.util.EventListener;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.yash.training.tmp.domain.Course;
import com.yash.training.tmp.domain.CourseDetails;
import com.yash.training.tmp.domain.User;
import com.yash.training.tmp.service.CourseServiceEJB;
import com.yash.training.tmp.service.CourseServiceEJBRemote;

@ManagedBean
@SessionScoped
public class CourseBean {
	private int courseId;
	private String courseTitle;
	private String description;
	private String refrenceCode;
	private boolean status = true;
	private List courseList;
	private List activeCoursesList;
	private int activeCourseCount = 0;
	private int selectedCourseId;
	private Course course;
	private int totalCourseCount = 0;

	@EJB
	CourseServiceEJBRemote courseService;

	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
			.getRequest();
	HttpSession session = request.getSession();
	User user = (User) session.getAttribute("currentUser");

	public int getTotalCourseCount() {
		courseList = courseService.getAllCourses(user.getUser_id());
		totalCourseCount = courseList.size();
		return totalCourseCount;
	}

	public void setTotalCourseCount(int totalCourseCount) {
		this.totalCourseCount = totalCourseCount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public CourseServiceEJBRemote getCourseService() {
		return courseService;
	}

	public void setCourseService(CourseServiceEJBRemote courseService) {
		this.courseService = courseService;
	}

	public List getActiveCoursesList() {
		activeCoursesList = courseService.getActiveCourses(user.getUser_id());
		return activeCoursesList;
	}

	public void setActiveCoursesList(List activeCoursesList) {
		this.activeCoursesList = activeCoursesList;
	}

	public int getActiveCourseCount() {
		activeCoursesList = courseService.getActiveCourses(user.getUser_id());
		activeCourseCount = activeCoursesList.size();
		System.out.println("+++++++++++++++++++++>>>>>>>>>>>>>>" + activeCourseCount);
		return activeCourseCount;
	}

	public void setActiveCourseCount(int activeCourseCount) {
		this.activeCourseCount = activeCourseCount;
	}

	public List getCourseList() {

		System.out.println("--------------------------------------------user" + user);
		courseList = courseService.getAllCourses(user.getUser_id());
		return courseList;
	}

	public void setCourseList(List courseList) {
		this.courseList = courseList;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getRefrenceCode() {
		return refrenceCode;
	}

	public void setRefrenceCode(String refrenceCode) {
		this.refrenceCode = refrenceCode;
	}

	public String getCourseTitle() {

		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String saveCourse() {
		// System.out.println("-----------------------------------data--------------------------"+courseTitle+description);
		System.out.println(courseService);
		Course course = new Course();

		course.setCourseTitle(courseTitle);
		course.setDescription(description);
		course.setRefrenceCode(refrenceCode);
		course.setUser_id(user.getUser_id());
		if (status == true) {
			course.setStatus(1);
		} else {
			course.setStatus(0);

		}
		System.out.println(course);
		courseService.saveNewCourse(course);
		return "heading";
	}

	public String changeAction() {
		int status = courseService.getStatusById(selectedCourseId);
		courseService.setStatus(status, selectedCourseId);
		return "#";
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
	public int getCourseIdByTitle(String title){
		int courseId=0;
		courseId=courseService.getCourseIDByTitle(title);
		return courseId;
	}
	public String deleteCourse() {
		courseService.deleteCourse(selectedCourseId);
		return "#";
	}

}
