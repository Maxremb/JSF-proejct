package com.fr.adaming.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.query.Query;

import com.fr.adaming.config.ConnectDb;
import com.fr.adaming.dao.generic.AbstractDao;
import com.fr.adaming.entity.Film;

public class FilmDao extends AbstractDao<Film, Integer>{

	public FilmDao() {
		super(Film.class);
	}
	
	public List<Film> readByNom(String nom){
		String hql = "FROM Film WHERE nom = :nomParam";
		Query<Film> query = ConnectDb.getSession().createQuery(hql, Film.class);
		
		query.setParameter("nomParam", nom);
		
		return query.getResultList();
	}
	
	public List<Film> searchByNom(String nom){
		String hql = "FROM Film WHERE nom LIKE :nomParam";
		Query<Film> query = ConnectDb.getSession().createQuery(hql, Film.class);
		List<Film> liste = null;
		query.setParameter("nomParam", "%" + nom + "%");
		try {
			liste = query.getResultList();
		} catch (NoResultException e) {
			System.out.println("DEBUG searchByNom FilmDAO");
		}
		return liste;
	}
	
	
	public List<Film> readByCategorie(String nom){
		String hql = "SELECT f FROM Film f JOIN f.categories c WHERE c.nom = :nomParam";
		Query<Film> query = ConnectDb.getSession().createQuery(hql, Film.class);
		
		query.setParameter("nomParam", nom);
		
		List<Film> list =  query.getResultList();
		return list;
	}
	
	public List<Film> readByRealisateur(String nom){
		String hql = "SELECT f FROM Film f JOIN f.realisateur r WHERE r.nomComplet = :nomParam";
		Query<Film> query = ConnectDb.getSession().createQuery(hql, Film.class);
		
		query.setParameter("nomParam", nom);
		
		List<Film> list =  query.getResultList();
		return list;
	}
	
	public List<Film> searchByRealisateur(String nom){
		String hql = "FROM Film WHERE nom LIKE :realParam";
		Query<Film> query = ConnectDb.getSession().createQuery(hql, Film.class);
		List<Film> liste = null;
		query.setParameter("realParam", "%" + nom + "%");
		try {
			liste = query.getResultList();
		} catch (NoResultException e) {
			System.out.println("DEBUG searchByReal FilmDAO");
		}
		return liste;

}
}
