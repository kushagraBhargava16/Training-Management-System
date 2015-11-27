package com.yash.training.tmp.domain;

import java.io.Serializable;

public class CourseDetails implements Serializable{
Course course;
Heading heading;
SubHeading subHeading;
public Course getCourse() {
	return course;
}
public void setCourse(Course course) {
	this.course = course;
}
public Heading getHeading() {
	return heading;
}
public void setHeading(Heading heading) {
	this.heading = heading;
}
public SubHeading getSubHeading() {
	return subHeading;
}
public void setSubHeading(SubHeading subHeading) {
	this.subHeading = subHeading;
}
@Override
public String toString() {
	return "CourseDetails [course=" + course + ", heading=" + heading + ", subHeading=" + subHeading + "\n]/n";
}


}
