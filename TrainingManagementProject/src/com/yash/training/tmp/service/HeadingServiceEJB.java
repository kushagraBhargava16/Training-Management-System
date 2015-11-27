package com.yash.training.tmp.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import com.yash.training.tmp.domain.Course;
import com.yash.training.tmp.domain.Heading;
import com.yash.training.tmp.util.DBUtil;

/**
 * Session Bean implementation class HeadingServiceEJB
 */
@Stateful
@LocalBean
public class HeadingServiceEJB implements HeadingServiceEJBRemote {

    /**
     * Default constructor. 
     */
    public HeadingServiceEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List getAllCourses() {
	String sql="select courseTitle from courses";
	ResultSet resultSet=DBUtil.select(sql);
	List courses=new ArrayList();
		try {
			while(resultSet.next()){
				courses.add(resultSet.getString("courseTitle"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courses;
	}

	@Override
	public int getCourseIdByTitle(String courseTitle) {
		String sql="select courses_id from courses where courseTitle ='"+courseTitle+"'";
		int courseId=0;
		ResultSet resultSet=DBUtil.select(sql);
		try {
			while(resultSet.next()){
			 courseId=resultSet.getInt("courses_id");
			System.out.println("----------------------------"+courseId);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return courseId;
	}

	@Override
	public void saveHeading(Heading heading) {
		String sql="insert into heading(heading,courses_id) value('"+heading.getHeadingTitle()+"','"+heading.getCourseId()+"')";
		DBUtil.update(sql);
	}

}
