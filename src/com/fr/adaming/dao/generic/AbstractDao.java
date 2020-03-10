package com.fr.adaming.dao.generic;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.query.Query;

import com.fr.adaming.config.ConnectDb;
import com.fr.adaming.entity.Film;

public abstract class AbstractDao<T, ID> {

	private final Class<T> typeOfT;

	protected AbstractDao(Class<T> typeClass) {
		this.typeOfT = typeClass;
	}

	public void create(T object) {
		ConnectDb.getSession().beginTransaction();
		ConnectDb.getSession().save(object);
		ConnectDb.getSession().getTransaction().commit();
	}

	public void update(T object) {
		ConnectDb.getSession().beginTransaction();
		ConnectDb.getSession().update(object);
		ConnectDb.getSession().getTransaction().commit();	}

	public void delete(T object) {
		ConnectDb.getSession().beginTransaction();
		ConnectDb.getSession().delete(object);
		ConnectDb.getSession().getTransaction().commit();
	}

	public T readById(ID id) {
		String hql = "FROM " + this.typeOfT.getSimpleName() + " WHERE id=:idParam";
		Query<T> query = ConnectDb.getSession().createQuery(hql, this.typeOfT);

		query.setParameter("idParam", id);

		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<T> readAll() {
		String hql = "FROM " + this.typeOfT.getSimpleName();
		Query<T> query = ConnectDb.getSession().createQuery(hql, this.typeOfT);

		return query.getResultList();
	}

	public double getRateAverageByFilm(Film media) {
		String hql = "SELECT avg(note) FROM Note WHERE id.film.id = :id GROUP BY id.film.id";

		Query<Double> query = ConnectDb.getSession().createQuery(hql, Double.class);

		query.setParameter("id", media.getId());

		return query.getResultList().get(0);
	}
}
