package com.fr.adaming.presentation;

import com.fr.adaming.Main;

public class PageEspaceAdmin {

	public static void intro() {

		int choix = -1;
		
		do {
			System.out.println("\n********************\t Espace Admin \t ********************");
			System.out.println("\t Bonjour " + PageAuth.getConnectedUser().getNom());
			System.out.println("1- pour g�rer les films et les s�ries");
			System.out.println("2- pour g�rer les notes");
			System.out.println("3- pour g�rer les utilisateur");
			System.out.println("0- pour se d�connecter et revenir � la Page Principale");

			choix = Integer.parseInt(Main.scan.nextLine());

			switch (choix) {
			case 1:
				PageGestionMedia.intro();
				break;
			case 2:
				PageGestionNotes.intro();
				break;
			case 3:
				PageGestionUtilisateur.intro();
				break;
			case 0:
				System.out.println("Traitement de la requ�te en cours...");
				PageAuth.disconnect();
				break;
			default:
				System.out.println("Erreur de saisie! R�essayer");
				break;
			}
		} while (choix != 0);

	}

}
