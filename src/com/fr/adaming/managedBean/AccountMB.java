package com.fr.adaming.managedBean;

import javax.persistence.EnumType;

import com.fr.adaming.dao.UtilisateurDao;
import com.fr.adaming.entity.RoleEnum;
import com.fr.adaming.entity.Utilisateur;

public class AccountMB {

	private LoginMB loginMB = new LoginMB();
	private UtilisateurDao userDao = new UtilisateurDao();
	private String email;
	private String pwd;
	private String nom;
	private Boolean activated;
	
	 public LoginMB getLoginMB()
	    {
	    return loginMB;
	    }

	    public void setLoginMB(LoginMB loginMB)
	    {
	    this.loginMB = loginMB;
	    }

	// modif user
	public String update() {
		if (this.email != "") {
			loginMB.getUser().setEmail(this.email);
		} 
		if (this.pwd != "") {
			loginMB.getUser().setPwd(this.pwd);
		} 
		if (this.nom != "") {
			loginMB.getUser().setNom(this.nom);
		}
		if (this.activated != null) {
			loginMB.getUser().setActivated(this.activated);
		}
		userDao.update(loginMB.getUser());
		
		return "managedAccount.xhtml?faces-redirect=true";
	}

	// Getter & Setter
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

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Boolean getActivated() {
		return activated;
	}

	public void setActivated(Boolean activated) {
		this.activated = activated;
	}

}
