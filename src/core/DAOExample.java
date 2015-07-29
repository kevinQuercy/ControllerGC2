package core;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import DAOS.*;
import data.*;

public class DAOExample {

	public static void main(String[] args) throws Exception {
		
		
		/*DAOHistorique daohistorique = DAOFactory.creerDAOHistorique();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -5);
		Date datelimite = cal.getTime();
		for (int i = 1 ; i < 107 ; i++){
			Historique h = new Historique(i,datelimite,0,0);
			daohistorique.insert(h);
		}*/
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
		System.out.println ("\nFin de la planification\n");

		//////////////////////////////////////////////////////////////////////
		// Sauvegarde d'une planification
		//////////////////////////////////////////////////////////////////////		

		/*System.out.println ("\nSauvegarde de la planification\n");
		DAOPlanification daoplanification = DAOFactory.creerDAOPlanification();
		daoplanification.insert(planif);
		System.out.println ("\nFin de la sauvegarde\n");*/

		//////////////////////////////////////////////////////////////////////
		// Suppression d'une planification
		//////////////////////////////////////////////////////////////////////

		/*DAOPlanification daoplanification = DAOFactory.creerDAOPlanification();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		System.out.println ("\nSuppression de la planification du : " + sdf.format(new Date()) + "\n");
		daoplanification.deletebydate(new Date());*/
		//////////////////////////////////////////////////////////////////////
		// Affichage d'une planification
		//////////////////////////////////////////////////////////////////////
		
		/*System.out.println ("\nAffichage d'une Planification\n");
		DAOPlanification daoplanification2 = DAOFactory.creerDAOPlanification();
		Planification pla = daoplanification2.selectbydate(new Date());
		System.out.println ("id : " + pla.get_id());
		System.out.println ("taux : " + pla.get_taux());
		System.out.println ("datecreation : " + pla.get_datecreation());
		System.out.println ("\nNombre d'itineraires : " + pla.get_itineraires().size());
		List<Itineraire> lit = pla.get_itineraires();
		for ( int i = 0 ; i < lit.size() ; i++ ) {
			Itineraire it = lit.get(i);
			System.out.println ("\nItineraire " + i + " :\n");
			System.out.println ("  id : " + it.get_id());
			System.out.println ("  Camion_id: " + it.get_Camion_id());
			System.out.println ("  Planification_id : " + it.get_Planification_id());
			System.out.println ("  Date : " + it.get_date());
			List<Ilotdepassage> ilots = it.get_ilotsdepassage();
			System.out.println ("\n  Nombre d'ilots : " + ilots.size());
			for ( int j = 0 ; j < ilots.size(); j++ ) {
				Ilotdepassage il = ilots.get(j);
				List<Conteneuravider> cav = il.get_conteneuravider();
				System.out.print ("\n    Ilots : " + il.get_Ilot_id() + " | Ordre : " + il.get_ordre() + " | Itineraire : " + il.get_Itineraire_id() + " | Nombre de conteneur à vider : " + cav.size() + "\n");
				for ( int k = 0 ; k < cav.size(); k++ ) {
					Conteneuravider c = cav.get(k);
					System.out.println ("      Conteneur : " + c.Conteneur_id() + " | Ilot : " + c.get_Ilot_id() + " | Itineraire : " + c.get_Itineraire_id());
				}
			}
		}
		System.out.println ("\nFin de l'affichage\n");*/
		
		//////////////////////////////////////////////////////////////////////
		// Get d'un itineraire par Camion_id et Date
		//////////////////////////////////////////////////////////////////////

		System.out.println ("\nGet d'un itineraire par Camion_id et Date\n");
		DAOItineraire daoitineraire = DAOFactory.creerDAOItineraire();
		Itineraire iti = daoitineraire.selectbydateetcamion(new Date(), 2);
		System.out.println ("  id : " + iti.get_id());
		System.out.println ("  Camion_id: " + iti.get_Camion_id());
		System.out.println ("  Planification_id : " + iti.get_Planification_date());
		System.out.println ("  Longueur : " + iti.get_longueur());
		List<Ilotdepassage> ilots = iti.get_ilotsdepassage();
		System.out.println ("\n  Nombre d'ilots : " + ilots.size());
		for ( int jj = 0 ; jj < ilots.size(); jj++ ) {
			Ilotdepassage il = ilots.get(jj);
			System.out.print ("\n    Ilots : " + il.get_Ilot().get_id() + " | Ordre : " + il.get_ordre() + " | Itineraire : " + il.get_Itineraire_id());
		}
		System.out.println ("\nFin\n");
		
		//////////////////////////////////////////////////////////////////////
		// Mise à jour l'etat d'un conteneur
		//////////////////////////////////////////////////////////////////////
		
		System.out.println ("\nMise à jour l'etat d'un conteneur\n");
		Conteneur c = new Conteneur(1);
		c.majetat(0,0);
		DAOConteneur daoconteneur = DAOFactory.creerDAOConteneur();
		daoconteneur.majetat(c);
		System.out.println ("\nFin\n");
		
		//////////////////////////////////////////////////////////////////////
		// Recuperer tous les conteneurs
		//////////////////////////////////////////////////////////////////////
		
		/*System.out.println ("\nRecuperer tous les conteneurs\n");
		DAOConteneur daoconteneur3 = DAOFactory.creerDAOConteneur();
		List<Conteneur> lc = daoconteneur3.select();
		for ( int i = 0 ; i < lc.size(); i++ ) {
			Conteneur c2;
			c2 = lc.get(i);
			System.out.println ("id : " + c2.get_id() + " | ilot : " + c2.get_Ilot_id() + " | Volume max : " + c2.get_volumemax() + " | Type dechets : " + c2.get_TypeDechets_id() + " | last update : " + c2.get_lastupdate() + " | last poids : " + c2.get_lastpoids() + " | last volume : " + c2.get_lastvolume());
		}
		System.out.println ("\nFin\n");*/
		
		//////////////////////////////////////////////////////////////////////
		// Creer l'etat global
		//////////////////////////////////////////////////////////////////////
		
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
		}
		
		// Pour tous les types de dechets creer une liste d'ilots
		/*for ( int j = 0 ; j < li.size(); j++ ) {
			Ilot ilot = li.get(j);
			System.out.println ("id : " + ilot.get_id() + " | Adresse : " + ilot.get_adresse() + " | Code postal : " + ilot.get_codepostal() + " | Ville : " + ilot.get_ville() + " | Longitude : " + ilot.get_longitude() + " | Latitude : " + ilot.get_latitude() + " | Contact id : " + ilot.get_Contact_id());
			List<Conteneur> lcont = ilot.get_conteneurs();
			for (int clcont = 0 ; clcont < lcont.size() ; clcont++) {
				Conteneur cc = lcont.get(clcont);
				System.out.println ("  Conteneur : " + cc.get_id() + " | Ilot_id : " + cc.get_Ilot_id() + " | Volume max : " + cc.get_volumemax() + " | Last volume : " + cc.get_lastvolume() + " | Last poids : " + cc.get_lastpoids() + " | Last update : " + cc.get_lastupdate() + " | Typedechets_id : " + cc.get_TypeDechets_id());
			}
		}
		System.out.println ("\nFin\n");*/
		
		//////////////////////////////////////////////////////////////////////
		// Recuperer tous les camions
		//////////////////////////////////////////////////////////////////////
		
		/*System.out.println ("\nRécuperer tous les camions\n");
		DAOCamion daocamion = DAOFactory.creerDAOCamion();
		List<Camion> lcam = daocamion.select();
		for ( int jrr = 0 ; jrr < lcam.size(); jrr++ ) {
			Camion camion = lcam.get(jrr);
			System.out.println ("id : " + camion.get_id() + " | Poids max : " + camion.get_poidsmax() + " | Disponible : " + camion.get_disponible());
		}
		System.out.println ("\nFin\n");*/
		
		//////////////////////////////////////////////////////////////////////
		// Changer état d'un camion
		//////////////////////////////////////////////////////////////////////
		
		/*System.out.println ("\nChanger état d'un camion\n");
		boolean dispo = true;
		Camion camionachanger = new Camion(1,19,dispo);
		System.out.println ("Camion 1 : disponible à "+dispo);
		DAOCamion daocamion3 = DAOFactory.creerDAOCamion();
		daocamion3.update(camionachanger);
		System.out.println ("\nFin\n");*/
		
		//////////////////////////////////////////////////////////////////////
		// Recuperer tous les camions
		//////////////////////////////////////////////////////////////////////
		
		/*System.out.println ("\nRécuperer tous les camions\n");
		DAOCamion daocamion2 = DAOFactory.creerDAOCamion();
		List<Camion> lcam2 = daocamion2.select();
		for ( int jrr2 = 0 ; jrr2 < lcam2.size(); jrr2++ ) {
			Camion camion = lcam2.get(jrr2);
			System.out.println ("id : " + camion.get_id() + " | Poids max : " + camion.get_poidsmax() + " | Disponible : " + camion.get_disponible());
		}
		System.out.println ("\nFin\n");*/
		
		//////////////////////////////////////////////////////////////////////
		// Récuperer tous les types de dechets
		//////////////////////////////////////////////////////////////////////
		
		/*System.out.println ("\nRécuperer tous les types de dechets\n");
		DAOTypedechets daotypedechets = DAOFactory.creerDAOTypedechets();
		List<Typedechets> ltd = daotypedechets.select();
		for ( int jrr3 = 0 ; jrr3 < ltd.size(); jrr3++ ) {
			Typedechets typedechets = ltd.get(jrr3);
			System.out.println ("id : " + typedechets.get_id() + " | Libelle : " + typedechets.get_libelle());
		}
		System.out.println ("\nFin\n");*/
	}
}