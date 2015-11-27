package com.yash.training.tmp.service;

import java.util.List;

import javax.ejb.Remote;

import com.yash.training.tmp.domain.SubHeading;

@Remote
public interface SubHeadingServiceEJBRemote {

	int getHeadingIdByTitle(String headingTitle);

	List getAllHeadingsByCourseId(int courseId);

	void saveSubHeading(SubHeading subHeading);

	List getAllHeadings();

	void updateSubheadingStatus(String status, int subHeadingId);

}
