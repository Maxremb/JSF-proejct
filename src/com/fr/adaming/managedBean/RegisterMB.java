package com.fr.adaming.managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import com.fr.adaming.dao.UtilisateurDao;
import com.fr.adaming.entity.RoleEnum;
import com.fr.adaming.entity.Utilisateur;

@ManagedBean
@RequestScoped
public class RegisterMB {

	private String nom;
	private String email;
	private String pwd;
	private RoleEnum role;
	private Boolean activated;

	UtilisateurDao utilisateurDao = new UtilisateurDao();

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return pwd;
	}

	public void setPassword(String pwd) {
		this.pwd = pwd;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public RoleEnum getRole() {
		return role;
	}

	public void setRole(RoleEnum role) {
		this.role = role;
	}

	public Boolean getActivated() {
		return activated;
	}

	public void setActivated(Boolean activated) {
		this.activated = activated;
	}

	public String register() {
		Utilisateur user = new Utilisateur(nom, email, pwd, RoleEnum.USER, Boolean.TRUE);
		try {
			utilisateurDao.create(user);
		} catch (Exception e) {

			return "loginRegister.xhtml?faces-redirect=true";

		}
		return "loginRegister.xhtml?faces-redirect=true";
	}
}