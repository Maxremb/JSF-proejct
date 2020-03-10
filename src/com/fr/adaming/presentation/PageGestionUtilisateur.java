package com.fr.adaming.presentation;

import java.util.List;

import com.fr.adaming.Main;
import com.fr.adaming.dao.UtilisateurDao;
import com.fr.adaming.entity.RoleEnum;
import com.fr.adaming.entity.Utilisateur;

public class PageGestionUtilisateur {

	private static UtilisateurDao dao = new UtilisateurDao();

	public static void intro() {

		int choix = -1;

		do {
			System.out.println("\n********************\t Page Gestion Utilisateur \t ********************");
			System.out.println("1- pour voir la liste des utilisateurs");
			System.out.println("2- pour voir la liste des Admins seulement");
			System.out.println("3- pour voir la liste des Users seulement");
			System.out.println("4- pour chercher un utilisateur par ID");
			System.out.println("5- pour chercher un utilisateur par Email");
			System.out.println("6- pour activer des utilisateurs");
			System.out.println("7- pour ajouter un nouveau utilisateur");
			System.out.println("8- pour modifier les coordonnées d'un utilisateur");
			System.out.println("9- pour supprimer un utilisateur");
			System.out.println("10- pour désactiver votre compte");
			System.out.println("0- pour revenir à la Page Principale");

			choix = Integer.parseInt(Main.scan.nextLine());

			switch (choix) {
			case 1:
				afficherList();
				break;
			case 2:
				afficherListAdmins();
				break;
			case 3:
				afficherListUsers();
				break;
			case 4:
				chercherParId();
				break;
			case 5:
				chercherParEmail();
				break;
			case 6:
				activer();
				break;
			case 7:
				ajouter();
				break;
			case 8:
				modifier();
				break;
			case 9:
				supprimer();
				break;
			case 10:
				desactiverCeCompte();
				break;
			case 0:
				System.out.println("Traitement de la requête en cours...");
				break;
			default:
				System.out.println("Erreur de saisie! Réessayer");
				break;
			}
		} while (choix != 0);

	}

	public static void afficherList() {
		System.out.println("\n********************\t List Utilisateurs \t ********************");
		List<Utilisateur> allUser = dao.readAll();
		System.out.println(allUser);

	}

	public static void afficherListAdmins() {
		System.out.println("\n********************\t List Admins seulement \t ********************");
		List<Utilisateur> allAdmin = dao.readAllAdmins();
		System.out.println(allAdmin);

	}

	public static void afficherListUsers() {
		System.out.println("\n********************\t List User seulement \t ********************");
		List<Utilisateur> allUser = dao.readAllUsers();
		System.out.println(allUser);

	}

	public static Utilisateur chercherParId() {
		System.out.println("\n********************\t Chercher Utilisateur par ID \t ********************");

		System.out.println("Saisir l'ID de l'utilisateur : ");
		// récupérer l'ID
		int id = Integer.valueOf(Main.scan.nextLine());
		Utilisateur user = dao.readById(id);
		if(user != null) {
			System.out.println(user);
		}else {
			System.err.println("Aucun Utilisateur trouvé!");
		}
		return user;

	}

	public static void chercherParEmail() {
		System.out.println("\n********************\t Chercher Utilisateur par Email \t ********************");

		System.out.println("Saisir l'email de l'utilisateur : ");
		// récupérer l'ID
		String email = Main.scan.nextLine();
		Utilisateur user = dao.readByEmail(email);
		
		if(user != null) {
			System.out.println(user);
		}else {
			System.err.println("Aucun Utilisateur trouvé!");
		}

	}

	public static void activer() {
		System.out.println("\n********************\t Activer Utilisateur \t ********************");
		List<Utilisateur> nonActiveUsers = dao.readAllNonActive();


		if(nonActiveUsers.isEmpty()) {
			System.out.println("Tous les comptes sont actifs");
		}else {
			System.out.println("Voici la liste des nouveau Utlisateur : ");
			System.out.println(nonActiveUsers);

			Utilisateur user = chercherParId();

			dao.activerUser(user);
		}
		

	}

	public static void ajouter() {
		System.out.println("\n********************\t Ajouter Utilisateur \t ********************");
		System.out.println("Entrez son nom : ");
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

		// vérifier le mot de passe
		String pwd = "";
		do {
			System.out.println("Saisir votre mot de passe : ");
			String pwd1 = Main.scan.nextLine();
			System.out.println("Saisir votre mot de passe à nouveau : ");
			String pwd2 = Main.scan.nextLine();

			if (pwd1.equals(pwd2)) {
				// mot de passe valide
				correctPwd = true;
				pwd = pwd1;
			}else {
				// mot de passe pas valide
				System.err.println("Les deux mots de passe ne sont pas identiques.. Réessayer");
			}
		} while (!correctPwd);

		System.out.println("Précisez son role : [ADMIN, USER]");
		String inputRole = Main.scan.nextLine();

		RoleEnum role = inputRole.equals("ADMIN") ? RoleEnum.ADMIN : RoleEnum.USER;

		dao.create(new Utilisateur(nom, email, pwd, role));
		System.out.println("L'utilisateur a été ajouté avec succès");

	}

	public static void modifier() {
		System.out.println("\n********************\t Modifier Utilisateur \t ********************");
		Utilisateur user = chercherParId();
		System.out.println("Entrez le nouveau nom : [" + user.getNom() + "]");
		String nom = Main.scan.nextLine();
		if (!nom.equals("")) {
			user.setNom(nom);
		}

		boolean correctEmail = false;

		String email = "";

		// vérifier l'email
		do {
			System.out.println("Entrez son email : [" + user.getEmail() + "]");
			email = Main.scan.nextLine();
			if (!email.equals("")) {
				user.setNom(email);
			}

			if (email.equals("") || dao.readByEmail(email) == null) {
				// Email valide
				PagePrincipale.chargement();
				correctEmail = true;
			}else {
				// Email non valide
				System.err.println("L'email n'est pas valide.. Réessayer");
			}
		} while (!correctEmail);

		boolean correctPwd = false;

		// vérifier le mot de passe
		String pwd = "";
		do {
			System.out.println("Saisir votre mot de passe : ");
			String pwd1 = Main.scan.nextLine();
			System.out.println("Saisir votre mot de passe à nouveau : ");
			String pwd2 = Main.scan.nextLine();

			if (pwd1.equals(pwd2)) {
				// mot de passe valide
				correctPwd = true;
				pwd = pwd1;
			}else {
				// mot de passe pas valide
				System.err.println("Les deux mots de passe ne sont pas identiques.. Réessayer");
			}
		} while (!correctPwd);

		System.out.println("Précisez son role : [ADMIN, USER]");
		String inputRole = Main.scan.nextLine();

		RoleEnum role = inputRole.equals("ADMIN") ? RoleEnum.ADMIN : RoleEnum.USER;

		user.setPwd(pwd);
		user.setRole(role);

		dao.update(user);
		System.out.println("L'utilisateur a été modifié avec succès");

	}

	public static void modifierInfoPerso() {
		System.out.println("\n********************\t Modifier Info Perso \t ********************");
		Utilisateur user = PageAuth.getConnectedUser();
		System.out.println("Entrez le nouveau nom : [" + user.getNom() + "]");
		String nom = Main.scan.nextLine();
		if (!nom.equals("")) {
			user.setNom(nom);
		}

		boolean correctEmail = false;

		String email = "";

		// vérifier l'email
		do {
			System.out.println("Entrez son email : [" + user.getEmail() + "]");
			email = Main.scan.nextLine();
			if (!email.equals("")) {
				user.setNom(email);
			}

			if (email.equals("") || dao.readByEmail(email) == null) {
				// Email valide
				PagePrincipale.chargement();
				correctEmail = true;
			}else {
				// Email non valide
				System.err.println("L'email n'est pas valide.. Réessayer");
			}
		} while (!correctEmail);

		boolean correctPwd = false;

		// vérifier le mot de passe
		String pwd = "";
		do {
			System.out.println("Saisir votre mot de passe : ");
			String pwd1 = Main.scan.nextLine();
			System.out.println("Saisir votre mot de passe à nouveau : ");
			String pwd2 = Main.scan.nextLine();

			if (pwd1.equals(pwd2)) {
				// mot de passe valide
				correctPwd = true;
				pwd = pwd1;
			}else {
				// mot de passe pas valide
				System.err.println("Les deux mots de passe ne sont pas identiques.. Réessayer");
			}
		} while (!correctPwd);

		user.setPwd(pwd);

		dao.update(user);
		System.out.println("L'utilisateur a été modifié avec succès");

	}

	public static void supprimer() {
		System.out.println("\n********************\t Supprimer Utilisateur \t ********************");
		Utilisateur user = chercherParId();

		System.err.println("Vous êtes sur le point de supprimer l'Utilisateur (" + user.getEmail()
				+ ")! Vous voulez continuer? [O/N]");
		boolean continuer = Main.scan.nextLine().toUpperCase().charAt(0) == 'O' ? true : false;

		if (continuer) {
			dao.delete(user);
		} else {
			System.out.println("Suppression abandonnée");
		}

	}

	public static void desactiverCeCompte() {
		System.out.println("\n********************\t Désactiver Ce Compte \t ********************");

		System.err.println("Vous voulez vraiment désactiver ce compte? [O/N]");
		boolean continuer = Main.scan.nextLine().toUpperCase().charAt(0) == 'O' ? true : false;

		if (continuer) {
			Utilisateur user = PageAuth.getConnectedUser();

			user.setActivated(false);

			dao.update(user);

			System.err.println("Ce compte est maintenant désactivé.. ");
		} else {
			System.out.println("Suppression abandonnée");
		}

	}

}
