package com.fr.adaming.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.query.Query;

import com.fr.adaming.config.ConnectDb;
import com.fr.adaming.dao.generic.AbstractDao;
import com.fr.adaming.entity.RoleEnum;
import com.fr.adaming.entity.Utilisateur;

public class UtilisateurDao extends AbstractDao<Utilisateur, Integer> {

	public UtilisateurDao() {
		super(Utilisateur.class);
	}

	public void createUser(Utilisateur user) {
			ConnectDb.getSession().save(user);
	}
	
	
	public List<Utilisateur> readAllUsers() {
		String hql = "FROM Utilisateur WHERE role = :role";
		Query<Utilisateur> query = ConnectDb.getSession().createQuery(hql, Utilisateur.class);
		query.setParameter("role", RoleEnum.USER);
		return query.getResultList();
	}

	public List<Utilisateur> readAllAdmins() {
		String hql = "FROM Utilisateur WHERE role = :role";
		Query<Utilisateur> query = ConnectDb.getSession().createQuery(hql, Utilisateur.class);
		query.setParameter("role", RoleEnum.ADMIN);
		return query.getResultList();
	}

	public Utilisateur login(String email, String pwd) {

		String hql = "FROM Utilisateur WHERE email like :email AND pwd like :pwd AND activated = true";
		Query<Utilisateur> query = ConnectDb.getSession().createQuery(hql, Utilisateur.class);
		query.setParameter("email", email);
		query.setParameter("pwd", pwd);
		Utilisateur user = null;
		try {
			// user can be an Utilisateur instance or NULL
			user = query.getSingleResult();

		} catch (NoResultException e) {
//			System.err.println("DEBUG login fail : Mot de passe ou Email erroné! Réessayer");
		}
		return user;
	}

	public Utilisateur readByEmail(String email) {
		String hql = "FROM Utilisateur WHERE email=:emailParam";
		Query<Utilisateur> query = ConnectDb.getSession().createQuery(hql, Utilisateur.class);
		query.setParameter("emailParam", email);
		try {
			System.out.println(query.getSingleResult());
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Utilisateur> readAllNonActive() {
		String hql = "FROM Utilisateur WHERE activated = false";
		Query<Utilisateur> query = ConnectDb.getSession().createQuery(hql, Utilisateur.class);
		return query.getResultList();
	}

	public void activerUser(Utilisateur user) {
		user.setActivated(true);
		ConnectDb.getSession().beginTransaction();
		try {
			ConnectDb.getSession().update(user);
		} catch (NullPointerException e) {
		}
		
		ConnectDb.getSession().getTransaction().commit();
	}
	
	public void desActiverUser(Utilisateur user) {
		user.setActivated(false);
		ConnectDb.getSession().beginTransaction();
		try {
			ConnectDb.getSession().update(user);
		} catch (NullPointerException e) {
		}
		
		ConnectDb.getSession().getTransaction().commit();
	}
	
	@Override
	public void update(Utilisateur user) {
		ConnectDb.getSession().beginTransaction();
		try {
			ConnectDb.getSession().update(user);
		} catch (NullPointerException e) {
		}
		
		ConnectDb.getSession().getTransaction().commit();
	}
}
