package com.yash.training.tmp.service;

import java.util.List;

import javax.ejb.Remote;

import com.yash.training.tmp.domain.Course;
import com.yash.training.tmp.domain.Heading;

@Remote
public interface HeadingServiceEJBRemote {

	List getAllCourses();

	int getCourseIdByTitle(String courseTitle);

	void saveHeading(Heading heading);

}
