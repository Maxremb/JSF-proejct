package com.fr.adaming.presentation;

import com.fr.adaming.Main;
import com.fr.adaming.config.ConnectDb;
import com.fr.adaming.entity.RoleEnum;

public class PagePrincipale {

	public static void intro() {

		int choix = -1;

		do {

			System.out.println();

			// Les droits d'accès
			if (PageAuth.getConnectedUser() != null) {
				if (PageAuth.getConnectedUser().getRole() == RoleEnum.ADMIN) {
					// Redirection vers l'Espace Admin
					PageEspaceAdmin.intro();
				} else {
					// Redirection vers l'Espace Client
					PageEspaceClient.intro();
				}
			}

			System.out.println("\n********************\t Page Principale\t ********************");
			System.out.println("1- pour consulter la page Login");
			System.out.println("2- pour consulter la page Register");
			System.out.println("0- pour Fermer l'application");

			try {

				choix = Integer.parseInt(Main.scan.nextLine());
				switch (choix) {
				case 1:
					PageAuth.login();
					break;
				case 2:
					PageAuth.register();
					break;
				case 0:
					preload();
					ConnectDb.closeSession();
					System.out.println("********************\t Au revoir et à bientôt :)\t********************");
					break;
				default:
					System.err.println("Erreur de saisie! Faut saisir un entier (1, 2 ou 3)");
					break;
				}
			} catch (Exception e) {
				//catcher tous type d'exception
				e.printStackTrace();
			}
		} while (choix != 0);

	}

	public static void preload() {
		System.out.print("Chargement en cours : ");
		try {
			for (int i = 0; i < 25; i++) {
				System.out.print("==");
				Thread.sleep(50);
			}
			System.out.println(">");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void chargement() {
		System.out.print("Vérification en cours : ");
		try {
			for (int i = 0; i < 20; i++) {
				System.out.print(".");
				Thread.sleep(50);
			}
			System.out.println(" Validé :)");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
