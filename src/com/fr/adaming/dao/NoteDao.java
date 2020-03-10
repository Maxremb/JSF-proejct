package com.fr.adaming.dao;

import java.util.List;

import org.hibernate.query.Query;

import com.fr.adaming.config.ConnectDb;
import com.fr.adaming.dao.generic.AbstractDao;
import com.fr.adaming.entity.Film;
import com.fr.adaming.entity.Note;
import com.fr.adaming.entity.NoteEmbeddedId;
import com.fr.adaming.entity.Utilisateur;

public class NoteDao extends AbstractDao<Note, NoteEmbeddedId> {

	public NoteDao() {
		super(Note.class);
	}

	public List<Note> readByUtilisateur(Utilisateur user) {
		String hql = "SELECT n FROM Note n JOIN n.id.user u WHERE u.id = :idParam";
		Query<Note> query = ConnectDb.getSession().createQuery(hql, Note.class);

		query.setParameter("idParam", user.getId());

		List<Note> list = query.getResultList();
		return list;
	}

	public List<Note> readByFilmOuSerie(Film film) {
		String hql = "SELECT n FROM Note n JOIN n.id.film f WHERE f.id = :idParam";
		Query<Note> query = ConnectDb.getSession().createQuery(hql, Note.class);

		query.setParameter("idParam", film.getId());

		List<Note> list = query.getResultList();
		return list;
	}

}
