package com.yash.training.tmp.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.yash.training.tmp.domain.User;
import com.yash.training.tmp.service.UserServiceEJBLocal;

@ManagedBean
@SessionScoped
public class UserBean {

	/* private int user_id; */
	private String name;
	private String contact;
	private String emailid;
	private int designation;
	private String username;
	private String password;
	private List<String> menuList;
	private boolean rememberMe;

	@EJB
	UserServiceEJBLocal userServiceEJB;

	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
			.getRequest();
	HttpSession session = request.getSession();

	public String getName() {
		return name;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public List<String> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<String> menuList) {
		this.menuList = menuList;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public int getDesignation() {
		return designation;
	}

	public void setDesignation(int designation) {
		this.designation = designation;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String authenticateUser() {
		User user = userServiceEJB.checkAuthentication(username, password);
		String returnString = "home";
		if (username.equals(user.getUsername())) {
			if (password.equals(user.getPassword())) {

				setName(user.getName().toUpperCase());
				setContact(user.getContact());
				setDesignation(user.getDesignation());
				setEmailid(user.getEmailid());
				session.setAttribute("currentUser", user);
				if(user.getDesignation()==1){
					returnString = "managerHome";
				}else if(user.getDesignation()==2){
					returnString = "trainerHome";
					}else{
					returnString = "trainerHome";
					
				}
			} else {
				returnString = "index.xhtml?faces-redirect=true&message=Invalid Password";

			}
		} else {
			if (password.equals(user.getPassword())) {
				returnString = "index.xhtml?faces-redirect=true&message=Invalid Username";
			} else {
				returnString = "index.xhtml?faces-redirect=true&message=Invalid Username and Password";

			}
		}

		return returnString;
	}

	public String registerUser() {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setContact(contact);
		user.setDesignation(designation);
		user.setEmailid(emailid);
		user.setName(name);
		user.setRole(3);
		user.setStatus(1);
		int check = userServiceEJB.registerNewUser(user);
		System.out.println("--------------------------------in register-----------------------------------");
		if (check == 0) {
			return "#";
		}
		return "index";
	}

	public String logout() {
		System.out.println("++++++++++++++++++++++++++++++++++in logout+++++++++++++");
		session.invalidate();
		return "index.xhtml?faces-redirect=true&message=Logout Successful";
	}
	
	public String check(){
		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::in check() of user bean");
		return null;
	}
	@Override
	public String toString() {
		return "UserBean [Name=" + name + ", contact=" + contact + ", emailid=" + emailid + ", designation="
				+ designation + ", username=" + username + ", password=" + password + "]";
	}

}
