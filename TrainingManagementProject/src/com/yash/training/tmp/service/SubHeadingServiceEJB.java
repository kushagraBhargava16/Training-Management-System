package com.yash.training.tmp.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import com.yash.training.tmp.domain.SubHeading;
import com.yash.training.tmp.util.DBUtil;

/**
 * Session Bean implementation class SubHeadingEJB
 */
@Stateful
@LocalBean
public class SubHeadingServiceEJB implements SubHeadingServiceEJBRemote {

	/**
	 * Default constructor.
	 */
	public SubHeadingServiceEJB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getHeadingIdByTitle(String headingTitle) {
		String sql = "select heading_id from heading where heading ='" + headingTitle + "'";
		ResultSet resultSet = DBUtil.select(sql);
		int headingId = 0;
		try {
			while (resultSet.next()) {
				headingId = resultSet.getInt("heading_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return headingId;
	}

	@Override
	public List getAllHeadingsByCourseId(int courseId) {
		String sql = "select heading from heading where courses_id='" + courseId + "'";
		ResultSet resultSet = DBUtil.select(sql);
		List headings = new ArrayList();
		try {
			while (resultSet.next()) {
				headings.add(resultSet.getString("heading"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return headings;
	}

	@Override
	public void saveSubHeading(SubHeading subHeading) {
		String sql = "insert into subheading(subheading,heading_id,status) values('" + subHeading.getSubHeadingTitle() + "','"
				+ subHeading.getHeadingId() + "','"+subHeading.getStatus()+"')";
		
		System.out.println("----------------------------------------"+sql+"----------------------------");
		DBUtil.update(sql);
		
	}

	@Override
	public List getAllHeadings() {
		String sql = "select heading from heading";
		ResultSet resultSet = DBUtil.select(sql);
		List headings = new ArrayList();
		try {
			while (resultSet.next()) {
				headings.add(resultSet.getString("heading"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return headings;
	}

	@Override
	public void updateSubheadingStatus(String status, int subHeadingId) {
		// TODO Auto-generated method stub
		String sql="update subheading set status='"+status+"' where subheading_id='"+subHeadingId+"'";
		DBUtil.update(sql);
		
	}

	


}
