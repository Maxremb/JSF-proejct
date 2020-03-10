package com.fr.adaming.presentation;

import com.fr.adaming.Main;
import com.fr.adaming.dao.UtilisateurDao;
import com.fr.adaming.entity.RoleEnum;
import com.fr.adaming.entity.Utilisateur;

public class PageAuth {

	private static UtilisateurDao dao = new UtilisateurDao();
	private static Utilisateur connectedUser;

	public static void login() {
		System.out.println("\n********************\t Page Login\t ********************");
		boolean repeat = false;
		do {

			System.out.println("Tapez votre email : ");
			String email = Main.scan.nextLine();
			System.out.println("Tapez votre pwd : ");
			String pwd = Main.scan.nextLine();

			connectedUser = dao.login(email, pwd);

			if (connectedUser != null) {
				if(connectedUser.getRole() == RoleEnum.ADMIN) {
					PageEspaceAdmin.intro();
					repeat = false;
				}else {
					PageEspaceClient.intro();
					repeat = false;
				}
			} else {
				System.err.println("Ce compte n'existe pas ou désactivé! Merci de réessayer");
				repeat = true;
			}
		} while (repeat);

	}

	public static void register() {
		System.out.println("\n********************\t Page Register\t ********************");
		System.out.println("Saisir votre nom : ");
		String nom = Main.scan.nextLine();

		boolean correctEmail = false;

		String email = "";

		// vérifier l'email
		do {
			System.out.println("Entrez son email : ");
			email = Main.scan.nextLine();

			if (dao.readByEmail(email) == null) {
				// Email valide
				PagePrincipale.chargement();
				correctEmail = true;
			}else {
				// Email non valide
				System.err.println("L'email n'est pas valide.. Réessayer");
			}
		} while (!correctEmail);

		boolean correctPwd = false;

		do {

			System.out.println("Saisir votre mot de passe : ");
			String pwd1 = Main.scan.nextLine();
			System.out.println("Saisir votre mot de passe à nouveau : ");
			String pwd2 = Main.scan.nextLine();

			if (pwd1.equals(pwd2)) {
				dao.create(new Utilisateur(nom, email, pwd1, RoleEnum.USER));
				correctPwd = true;
				System.out.println("Vous êtes maintenant inscrit :)");
				System.out.println("Vous devez demander à l'Admin d'activer votre compte");
			}else {
				// mot de passe pas valide
				System.err.println("Les deux mots de passe ne sont pas identiques.. Réessayer");
			}
		} while (!correctPwd);

	}

	public static Utilisateur getConnectedUser() {
		return connectedUser;
	}

	public static void disconnect() {
		connectedUser = null;
		PagePrincipale.preload();
	}
	
	

}
