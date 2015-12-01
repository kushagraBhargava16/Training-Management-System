package com.yash.training.tmp.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import com.yash.training.tmp.domain.Course;
import com.yash.training.tmp.domain.CourseDetails;
import com.yash.training.tmp.domain.Heading;
import com.yash.training.tmp.domain.SubHeading;
import com.yash.training.tmp.util.DBUtil;

/**
 * Session Bean implementation class CourseServiceEJB
 */
@Stateful
@LocalBean
public class CourseServiceEJB implements CourseServiceEJBRemote {

	/**
	 * Default constructor.
	 */
	public CourseServiceEJB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void saveNewCourse(Course course) {
		String sql = "insert into courses(courseTitle,description,status,refrenceCode,user_id) values('"
				+ course.getCourseTitle() + "','" + course.getDescription() + "','" + course.getStatus() + "','"
				+ course.getRefrenceCode() + "','"+course.getUser_id()+"')";
		DBUtil.update(sql);
	}

	@Override
	public List getAllCourses(int userId) {
		String sql = "select * from courses where user_id='" + userId + "'";
		List courses = new ArrayList();
		ResultSet resultSet = DBUtil.select(sql);
		try {
			while (resultSet.next()) {
				Course course = new Course();
				course.setCourseTitle(resultSet.getString("courseTitle"));
				course.setRefrenceCode(resultSet.getString("refrenceCode"));
				course.setDescription(resultSet.getString("description"));
				course.setStatus(resultSet.getInt("status"));
				course.setCourseId(resultSet.getInt("courses_id"));
				course.setUser_id(resultSet.getInt("user_id"));
				if (course.getStatus() == 1) {
					course.setCourseAction("DeActivate");
				} else {
					course.setCourseAction("Activate");

				}
				courses.add(course);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return courses;
	}

	@Override
	public List getActiveCourses() {
		String sql = "select * from courses where status=1";
		List courses = new ArrayList();
		ResultSet resultSet = DBUtil.select(sql);
		try {
			while (resultSet.next()) {
				Course course = new Course();
				course.setCourseTitle(resultSet.getString("courseTitle"));
				course.setRefrenceCode(resultSet.getString("refrenceCode"));
				course.setDescription(resultSet.getString("description"));
				course.setStatus(resultSet.getInt("status"));
				course.setCourseId(resultSet.getInt("courses_id"));
				course.setUser_id(resultSet.getInt("user_id"));
				courses.add(course);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courses;
	}

	@Override
	public int getActiveCourseCount(int user_id) {
		String sql = "select count(*) from courses where user_id='" + user_id + "' and status=1";
		int count = 0;
		ResultSet resultSet = DBUtil.select(sql);
		try {
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int getAllActiveCourseCount(int user_id) {
		String sql = "select count(*) from courses where user_id='" + user_id + "'";
		int count = 0;
		ResultSet resultSet = DBUtil.select(sql);
		try {
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int getStatusById(int selectedCourseId) {
		String sql = "select status from courses where COURSES_ID ='" + selectedCourseId + "'";
		int status = 0;
		ResultSet resultSet = DBUtil.select(sql);
		try {
			while (resultSet.next()) {
				if (resultSet.getInt(1) == 1) {

				} else {
					status = 1;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return status;
	}

	@Override
	public void setStatus(int status, int selectedCourseId) {

		String query = "UPDATE courses SET status = " + status + " WHERE COURSES_ID = " + selectedCourseId;
		try {
			DBUtil.update(query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Course getAllCourseDetails(int selectedCourseId) {
		String courseSql = "select * from courses where courses_id='" + selectedCourseId + "'";
		System.out.println(courseSql);
		ResultSet resultSet = DBUtil.select(courseSql);
		List headingList;
		List subHeadingList;
		Course course = new Course();
		try {
			while (resultSet.next()) {

				course.setCourseTitle(resultSet.getString("courseTitle"));
				course.setRefrenceCode(resultSet.getString("refrenceCode"));
				course.setDescription(resultSet.getString("description"));
				course.setStatus(resultSet.getInt("status"));
				course.setCourseId(resultSet.getInt("courses_id"));
				course.setUser_id(resultSet.getInt("user_id"));

				headingList = new ArrayList();
				String headingSql = "select * from heading where courses_id='" + selectedCourseId + "'";

				ResultSet resultSetHeading = DBUtil.select(headingSql);

				/*System.out.println("$$$$$$$$$$$$$$$$$$$$$$$course" + course);
				System.out.println(headingSql);
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>is first" + resultSetHeading.isFirst()
						+ ">>>>>>>>>>>>>>>>>is last" + resultSetHeading.isLast());*/

				while (resultSetHeading.next()) {

					Heading heading = new Heading();

					int headingId = resultSetHeading.getInt("heading_id");

					heading.setHeadingId(headingId);

					heading.setCourseId(resultSetHeading.getInt("courses_Id"));

					heading.setHeadingTitle(resultSetHeading.getString("heading"));

					/*System.out.println("%%%%%%%%%%%%%%%%%heading" + heading);*/

					headingList.add(heading);

					subHeadingList = new ArrayList();
					String subHeadingSql = "select * from subheading where heading_id='" + headingId + "'";

					ResultSet resultSetSubHeading = DBUtil.select(subHeadingSql);

					/*System.out.println(subHeadingSql);
					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>is first" + resultSetSubHeading.isFirst()
							+ ">>>>>>>>>>>>>>>>>is last" + resultSetSubHeading.isLast());*/
					while (resultSetSubHeading.next()) {

						SubHeading subHeading = new SubHeading();

						subHeading.setHeadingId(resultSetSubHeading.getInt("heading_id"));

						subHeading.setStatus(resultSetSubHeading.getString("status"));

						subHeading.setSubHeadingId(resultSetSubHeading.getInt("subheading_id"));

						subHeading.setSubHeadingTitle(resultSetSubHeading.getString("subheading"));

						subHeadingList.add(subHeading);
					/*	System.out.println("@@@@@@@@@@@@@@@@@@@@@Subheading" + subHeading);*/

					}
					heading.setSubHeadingList(subHeadingList);

				}
				course.setHeadingList(headingList);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return course;
	}

	@Override
	public void deleteCourse(int selectedCourseId) {
	String sql="delete from courses where courses_id='"+selectedCourseId+"'";
	/*System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++in ejb delete++++++++++++++++++++++"+sql);*/
	
	DBUtil.update(sql);
		
	}

	@Override
	public int getCourseIDByTitle(String title) {
		String sql="select courses_id from courses where courseTitle='"+title+"'";
		int id=0;
		ResultSet resultSet=DBUtil.select(sql);
		try {
			while(resultSet.next()){
				id=resultSet.getInt("courses_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

}
