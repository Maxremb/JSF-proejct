package com.fr.adaming.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConnectDb {

	private static SessionFactory sessionFactory;
	private static Session session;
	
	private ConnectDb() {
		System.out.println("Nouvelle connexion à la BD");
		sessionFactory = new Configuration().configure("config-bd.xml").buildSessionFactory();
		session = sessionFactory.openSession();
	}
	
	public static Session getSession() {
		if(session == null) {
			new ConnectDb();
		}
		return session;
	}
	
	public static void closeSession() {
		if(session != null && session.isOpen()) {
			System.out.println("Fermeture de connexion avec la BD");
			session.close();
		}
	}
}
