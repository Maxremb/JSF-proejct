
package com.fr.adaming.managedBean;

import com.fr.adaming.entity.Utilisateur;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.fr.adaming.dao.UtilisateurDao;

public class LoginMB {

	private String email;
	private String pwd;
	private Utilisateur user;
	private String msg;

	UtilisateurDao userDao = new UtilisateurDao();

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String login() {

		// SUCCESS
		this.user = userDao.login(this.email, this.pwd);
		if (user != null && user.isActivated()== true) {
			return "SUCCESS";
			//FAIL
		} else {
			return "FAIL";
		}

	}

	public String deco() {
		this.email = null;
		this.pwd = null;
		return "loginRegister.xhtml?faces-redirect=true";
	}
}