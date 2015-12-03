package com.yash.training.tmp.bean;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yash.training.tmp.domain.Course;
import com.yash.training.tmp.domain.SubHeading;
import com.yash.training.tmp.domain.User;
import com.yash.training.tmp.service.CourseServiceEJBRemote;
import com.yash.training.tmp.service.SubHeadingServiceEJBRemote;

@ManagedBean
@SessionScoped
public class TrainerBean {

	private List activeCoursesList;
	private int selectedCourseId;
	private int subHeadingId;
	private String status;
	private Course course;
	private List filesList;
	@EJB
	CourseServiceEJBRemote courseService;
	@EJB
	SubHeadingServiceEJBRemote subHeadingService;

	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
			.getRequest();
	HttpSession session = request.getSession();
	User user = (User) session.getAttribute("currentUser");

	public List getFilesList() {
		filesList = new ArrayList();
		String path = "C:/Users/kushagra.bhargava/Documents/GitHub/Training-Management-System/TrainingManagementProject/WebContent/Uploaded Files";

		String files;
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				files = listOfFiles[i].getName();
				filesList.add(files);
			}
		}
		return filesList;
	}

	public void setFilesList(List filesList) {

		this.filesList = filesList;
	}

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
		return "trainerHome.xhtml?faces-redirect=true";
	}

	public String downloadFile() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String fileName = params.get("fileName");
		System.out.println("File name>>>>>>>>>>>>>>>>>>" + fileName);
		String PDF_URL = "file:///C:/Users/kushagra.bhargava/Documents/GitHub/Training-Management-System/TrainingManagementProject/WebContent/Uploaded Files/"
				+ fileName;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

		response.reset();
		response.setHeader("Content-Type", "application/pdf");
		response.setHeader("Content-Disposition", "attachment;filename="+fileName);

		URL url;
		InputStream pdfInputStream;
		OutputStream responseOutputStream;
		try {
			responseOutputStream = response.getOutputStream();
			url = new URL(PDF_URL);
			pdfInputStream = url.openStream();
			byte[] bytesBuffer = new byte[2048];
			int bytesRead;
			while ((bytesRead = pdfInputStream.read(bytesBuffer)) > 0) {
				responseOutputStream.write(bytesBuffer, 0, bytesRead);
				responseOutputStream.flush();
				pdfInputStream.close();
				responseOutputStream.close();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "trainerHome.xhtml?faces-redirect=true";
	}
}