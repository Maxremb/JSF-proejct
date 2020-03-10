package com.fr.adaming;

import java.util.Scanner;

import com.fr.adaming.config.ConnectDb;
import com.fr.adaming.presentation.PagePrincipale;

public class Main {

	public static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		//Préparer la session au démarrage de l'appli
		ConnectDb.getSession();
		
		PagePrincipale.intro();

	}

}
