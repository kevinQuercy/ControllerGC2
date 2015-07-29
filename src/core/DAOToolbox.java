package core;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import DAOS.DAOConteneur;
import DAOS.DAOFactory;
import DAOS.DAOIlot;
import DAOS.DAOPlanification;
import data.Conteneur;
import data.Ilotdepassage;
import data.Ilotsavider;
import data.Ilotsglobalsavider;
import data.Itineraire;
import data.Planification;

public class DAOToolbox {
	public static void main(String[] args) {
		while(true) {
			try {
				DAOToolbox.menu();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println ("\nERREUR !!\n");
				
			}
		}
	}
	public static void menu() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println ("\n\n#######################################################");
		System.out.println ("##  Choix de manipulation DAO                        ##");
		System.out.println ("#######################################################\n");
		System.out.println ("1 - Creer une planification du jour et l'enregistrer");
		System.out.println ("2 - Supprimer la planification du jour");
		System.out.println ("3 - Afficher la planification du jour");
		System.out.println ("4 - Creer et afficher ilotsglobalsavider");
		System.out.println ("5 - Mise à jour d'un conteneur");
		System.out.println ("0 - Quitter");
		System.out.print ("\nEntrez votre choix : ");
		String str = sc.nextLine();
		switch (str) {
			case "1" : DAOToolbox.creeretenregistreruneplanification();break;
			case "2" : DAOToolbox.supprimerplanificationdujour(); break;
			case "3" : DAOToolbox.afficherlaplanificationdujour(); break;
			case "4" : DAOToolbox.creerilotsglobalsavider();break;
			case "5" : DAOToolbox.miseajourdunconteneur();break;
			case "0" : System.out.println ("L'application s'arrete");System.exit(0);
		}
		DAOToolbox.menu();
	}
	//////////////////////////////////////////////////////////////////////
	// Mise à jour l'etat d'un conteneur
	//////////////////////////////////////////////////////////////////////
	public static void miseajourdunconteneur() throws Exception {
		System.out.println ("\n\n#######################################################");
		System.out.println ("##  Mise à jour d'un conteneur                       ##");
		System.out.println ("#######################################################\n");
		Scanner sc = new Scanner(System.in);
		System.out.print ("\nEntrez l'id du conteneur : ");
		String str = sc.nextLine();
		int id = Integer.parseInt(str);
		DAOConteneur daoconteneur = DAOFactory.creerDAOConteneur();
		Conteneur c = daoconteneur.selectbyid(id);
		System.out.println ("id           : " + c.get_id());
		System.out.println ("ilot         : " + c.get_Ilot_id());
		System.out.println ("Volume max   : " + c.get_volumemax());
		System.out.println ("Type dechets : " + c.get_TypeDechets_id());
		System.out.println ("last update  : " + c.get_lastupdate());
		System.out.println ("last poids   : " + c.get_lastpoids());
		System.out.println ("last volume  : " + c.get_lastvolume());
		System.out.print("\nEntrez le nouveau poids du conteneur : ");
		str = sc.nextLine();
		int poids = Integer.parseInt(str);
		System.out.print("Entrez le nouveau volume du conteneur : ");
		str = sc.nextLine();
		int volume = Integer.parseInt(str);
		System.out.println ("\nMise à jour du conteneur ...\n");
		c.majetat(volume, poids);
		daoconteneur.majetat(c);
		c = daoconteneur.selectbyid(id);
		System.out.print ("Nouvelles données :\n");
		System.out.println ("id           : " + c.get_id());
		System.out.println ("ilot         : " + c.get_Ilot_id());
		System.out.println ("Volume max   : " + c.get_volumemax());
		System.out.println ("Type dechets : " + c.get_TypeDechets_id());
		System.out.println ("last update  : " + c.get_lastupdate());
		System.out.println ("last poids   : " + c.get_lastpoids());
		System.out.println ("last volume  : " + c.get_lastvolume());
		
		System.out.println ("\nFin de la mise à jour");
	}
	//////////////////////////////////////////////////////////////////////
	// Suppression de la planification du jour
	//////////////////////////////////////////////////////////////////////
	public static void supprimerplanificationdujour() throws Exception {

		System.out.println ("\n\n#######################################################");
		System.out.println ("##  Suppression de la planification du jour          ##");
		System.out.println ("#######################################################\n");
		DAOPlanification daoplanification = DAOFactory.creerDAOPlanification();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		System.out.println ("Suppression de la planification du : " + sdf.format(new Date()));
		daoplanification.deletebydate(new Date());
	}
	public static void creeretenregistreruneplanification() throws Exception {
		//////////////////////////////////////////////////////////////////////
		// Creation d'une planification
		//////////////////////////////////////////////////////////////////////
		
		System.out.println ("\nCreation d'une planification\n");
		DAOIlot daoilot = DAOFactory.creerDAOIlot();
		System.out.println ("Creation de 3 itineraires");
		System.out.println ("  Creation de l'itineraire 1 pour le camion 2");
		Itineraire it1 = new Itineraire(1,2);
		System.out.println ("    Ajout de l'ilot 1 avec l'ordre 1");
		it1.ajouterilot(daoilot.selectbyid(1),1);
		System.out.println ("    Ajout de l'ilot 2 avec l'ordre 2");
		it1.ajouterilot(daoilot.selectbyid(2),2);
		System.out.println ("    Ajout de l'ilot 3 avec l'ordre 3");
		it1.ajouterilot(daoilot.selectbyid(3),3);
		System.out.println ("    Ajout de la longueur à 10 000 m");
		it1.set_longueur(10000);
		System.out.println ("  Creation de l'itineraire 2 pour le camion 4");
		Itineraire it2 = new Itineraire(2,4);
		System.out.println ("    Ajout de l'ilot 4 avec l'ordre 1");
		it2.ajouterilot(daoilot.selectbyid(4),1);
		System.out.println ("    Ajout de l'ilot 5 avec l'ordre 1");
		it2.ajouterilot(daoilot.selectbyid(5),2);
		System.out.println ("    Ajout de l'ilot 6 avec l'ordre 1");
		it2.ajouterilot(daoilot.selectbyid(6),3);
		System.out.println ("    Ajout de la longueur à 14 000 m");
		it2.set_longueur(14000);
		System.out.println ("  Creation de l'itineraire 3 pour le camion 6");
		Itineraire it3 = new Itineraire(3,6);
		System.out.println ("    Ajout de l'ilot 7 avec l'ordre 1");
		it3.ajouterilot(daoilot.selectbyid(7),1);
		System.out.println ("    Ajout de l'ilot 8 avec l'ordre 1");
		it3.ajouterilot(daoilot.selectbyid(8),2);
		System.out.println ("    Ajout de l'ilot 9 avec l'ordre 1");
		it3.ajouterilot(daoilot.selectbyid(9),3);
		System.out.println ("    Ajout de la longueur à 12 000 m");
		it3.set_longueur(12000);
		System.out.println ("\nCreation de 1 Planification");
		Planification planif = new Planification();
		planif.set_date(new Date());
		System.out.println ("  Ajout de l'itineraire 1");
		planif.ajouteritineraire(it1);
		System.out.println ("  Ajout de l'itineraire 2");
		planif.ajouteritineraire(it2);
		System.out.println ("  Ajout de l'itineraire 3");
		planif.ajouteritineraire(it3);
		System.out.println ("  Set du taux");
		planif.set_taux(50);
		System.out.println ("\nFin de la planification");

		//////////////////////////////////////////////////////////////////////
		// Sauvegarde d'une planification
		//////////////////////////////////////////////////////////////////////		

		System.out.println ("\nSauvegarde de la planification\n");
		DAOPlanification daoplanification = DAOFactory.creerDAOPlanification();
		daoplanification.insert(planif);
		System.out.println ("\nFin de la sauvegarde");
	}
	

	//////////////////////////////////////////////////////////////////////
	// Affichage d'une planification
	//////////////////////////////////////////////////////////////////////
	public static void afficherlaplanificationdujour() throws Exception {
		System.out.println ("\nAffichage d'une Planification\n");
		DAOPlanification daoplanification2 = DAOFactory.creerDAOPlanification();
		Planification pla = daoplanification2.selectbydate(new Date());
		System.out.println ("id : " + pla.get_id());
		System.out.println ("taux : " + pla.get_taux());
		System.out.println ("date : " + pla.get_date());
		System.out.println ("\nNombre d'itineraires : " + pla.get_itineraires().size());
		List<Itineraire> lit = pla.get_itineraires();
		for ( int i = 0 ; i < lit.size() ; i++ ) {
			Itineraire it = lit.get(i);
			System.out.println ("  id : " + it.get_id());
			System.out.println ("  Camion_id: " + it.get_Camion_id());
			System.out.println ("  Planification_date : " + it.get_Planification_date());
			System.out.println ("  Longueur : " + it.get_longueur());
			List<Ilotdepassage> ilots = it.get_ilotsdepassage();
			System.out.println ("\n  Nombre d'ilots : " + ilots.size());
			for ( int jj = 0 ; jj < ilots.size(); jj++ ) {
				Ilotdepassage il = ilots.get(jj);
				System.out.println ("    Ilots : " + il.get_Ilot().get_id() + " | Ordre : " + il.get_ordre() + " | Itineraire : " + il.get_Itineraire_id());
			}
		}
		System.out.println ("\nFin de l'affichage");
	}
	//////////////////////////////////////////////////////////////////////
	// Creer Ilots global a vider
	//////////////////////////////////////////////////////////////////////
	public static void creerilotsglobalsavider() throws Exception {
		System.out.println ("\nCreer ilotsglobalavider\n");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -3);
		Date datelimite = cal.getTime();
		Ilotsglobalsavider etatglobal = new Ilotsglobalsavider(60,datelimite);
	
		System.out.println ("\nAfficher l'etat global\n");
		List<Ilotsavider> ilotsavider = etatglobal.get_ilotsavider();
		System.out.println ("Nombre de type de dechets : " + ilotsavider.size()+"\n");
		for (int i = 0 ; i < ilotsavider.size() ; i++ ) {
			Ilotsavider il = ilotsavider.get(i);
			System.out.println ("Type de dechets : " + il.get_typedechets());
			System.out.println ("Nombre de camions disponibles : " + il.get_camions().size());
			System.out.println ("Nombre d'ilots à vider : " + il.get_ilots().size());
			System.out.print ("Ilots : ");
			for (int j = 0 ; j < il.get_ilots().size() ; j++ ) {
				System.out.print(il.get_ilots().get(j).get_id() + " ");
			}
			System.out.print ("\n\n");
			System.out.println ("\nFin de l'affichage");
		}
	}
}
