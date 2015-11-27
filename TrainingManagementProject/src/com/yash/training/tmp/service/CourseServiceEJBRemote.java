package com.yash.training.tmp.service;

import java.util.List;

import javax.ejb.Remote;

import com.yash.training.tmp.domain.Course;
import com.yash.training.tmp.domain.CourseDetails;

@Remote
public interface CourseServiceEJBRemote {

	void saveNewCourse(Course course);

	List getAllCourses(int i);

	List getActiveCourses(int user_id);
	
	int getActiveCourseCount(int user_id);
	
	int getAllActiveCourseCount(int user_id);

	int getStatusById(int selectedCourseId);

	void setStatus(int status, int selectedCourseId);

	Course getAllCourseDetails(int selectedCourseId);
	
	void deleteCourse(int selectedCourseId);

	int getCourseIDByTitle(String title);

}
