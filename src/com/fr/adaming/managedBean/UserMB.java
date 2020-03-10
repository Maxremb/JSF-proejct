package com.fr.adaming.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import com.fr.adaming.dao.UtilisateurDao;
import com.fr.adaming.entity.RoleEnum;
import com.fr.adaming.entity.Utilisateur;

public class UserMB implements Serializable {

	private UtilisateurDao userDao = new UtilisateurDao();
	private String email;
	private String pwd;
	private String nom;
	private RoleEnum role;
	private Boolean activated;
	private String message;
	private String Style;
	private Utilisateur user;
	private List<Utilisateur> usersList = userDao.readAllUsers();
	private List<Utilisateur> adminsList = userDao.readAllAdmins();
	private String see = null;

	private Utilisateur selectedUser;

	// method add user
	public void addUser() {
		// verif user dans DB
		Utilisateur user = userDao.readByEmail(this.email);
		if (user == null) {
			// enregistrer dans BD
			user = new Utilisateur(this.nom, this.email, this.pwd, this.role, this.activated);
			userDao.createUser(user);
			// affichage message success
			if (this.role == RoleEnum.ADMIN) {
				this.message = "You just added a new ADMIN !";
				this.Style = "border:solid;border-color:green;color:green;font-weight: bold";
				printAdminsList();
			} else {
				this.message = "You just added a new user !";
				this.Style = "border:solid;border-color:green;color:green;font-weight: bold";
				printUsersList();
			}
		}

	}

	// afficher list of user
	public void printUsersList() {
		try {
			usersList = userDao.readAllUsers();
		} catch (IndexOutOfBoundsException e) {
		}

		see = "userList";
	}

	// afficher list of admins
	public void printAdminsList() {
		try {
			adminsList = userDao.readAllAdmins();
		} catch (IndexOutOfBoundsException e) {
		}

		see = "adminList";
	}

	// activate user or admin
	public void activateUserAdmin(Utilisateur u) {
		userDao.activerUser(u);
	}

	// desactivate user or admin
	public void desActivateUserAdmin(Utilisateur u) {
		userDao.desActiverUser(u);
	}

	// search user or admin by email
	public void readUserByEmail() {
		this.selectedUser = userDao.readByEmail(this.email);
		see = "emailList";
	}

	// modif user
	public void updateUser() {
		System.out.println("********************************");
		System.out.println("DEBUG UPDATE : " + selectedUser);
		this.selectedUser.setActivated(this.activated);
		this.selectedUser.setRole(this.role);
		userDao.update(selectedUser);
		adminsList = userDao.readAllAdmins();
		usersList = userDao.readAllUsers();

	}

	public void closeList() {
		see = null;
	}

	// delete user or admin
	public void deleteUser() {
		System.out.println("DEBUG DELETE" + selectedUser);
		adminsList.remove(selectedUser);
		userDao.delete(selectedUser);
		selectedUser = null;
	}



	// GETTER & SETTER
	public String getMessage() {
		return message;
	}

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStyle() {
		return Style;
	}

	public void setStyle(String style) {
		Style = style;
	}

	public List<Utilisateur> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<Utilisateur> usersList) {
		this.usersList = usersList;
	}

	public List<Utilisateur> getAdminsList() {
		return adminsList;
	}

	public void setAdminsList(List<Utilisateur> adminsList) {
		this.adminsList = adminsList;
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

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
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

	public String getSee() {
		return see;
	}

	public void setSee(String see) {
		this.see = see;
	}

	public Utilisateur getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(Utilisateur selectedUser) {
		this.selectedUser = selectedUser;
	}

}
