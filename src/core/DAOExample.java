package core;
import java.util.Date;
import java.util.List;

import DAOS.*;
import data.*;

public class DAOExample {

	public static void main(String[] args) throws Exception {

		//////////////////////////////////////////////////////////////////////
		// Creation d'une planification
		//////////////////////////////////////////////////////////////////////
		
		System.out.println ("\nCreation d'une planification\n");
		System.out.println ("Creation de 3 Ilotsde passage avec 3 Conteneurs a vider\n");
		Ilotdepassage ilot1 = new Ilotdepassage(1,1);
		ilot1.ajouterconteneur(1);
		ilot1.ajouterconteneur(2);
		ilot1.ajouterconteneur(3);
		Ilotdepassage ilot2 = new Ilotdepassage(2,2);
		ilot2.ajouterconteneur(4);
		ilot2.ajouterconteneur(5);
		ilot2.ajouterconteneur(6);
		Ilotdepassage ilot3 = new Ilotdepassage(3,3);
		ilot3.ajouterconteneur(7);
		ilot3.ajouterconteneur(8);
		ilot3.ajouterconteneur(9);
		System.out.println ("Creation de 2 Itinenaires\n");
		Itineraire it1 = new Itineraire();
		it1.ajouterilot(ilot1);
		it1.ajouterilot(ilot2);
		it1.ajouterilot(ilot3);
		it1.set_Camion_id(1);
		Itineraire it2 = new Itineraire();
		it2.ajouterilot(ilot1);
		it2.ajouterilot(ilot2);
		it2.ajouterilot(ilot3);
		it2.set_Camion_id(2);
		System.out.println ("Creation de 1 Planification\n");
		Planification planif = new Planification();
		planif.set_datecreation(new Date());
		planif.ajouteritineraire(it1);
		planif.ajouteritineraire(it2);
		planif.set_taux(50);
		System.out.println ("Fin de la planification\n");

		//////////////////////////////////////////////////////////////////////
		// Sauvegarde d'une planification
		//////////////////////////////////////////////////////////////////////		

		System.out.println ("\nSauvegarde de la planification\n");
		DAOPlanification daoplanification = DAOFactory.creerDAOPlanification();
		daoplanification.insert(planif);
		System.out.println ("\nFin de la sauvegarde\n");

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
		
		System.out.println ("\nAffichage d'une Planification\n");
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
		System.out.println ("\nFin de l'affichage\n");
		
		//////////////////////////////////////////////////////////////////////
		// Get d'un itineraire par Camion_id et Date
		//////////////////////////////////////////////////////////////////////

		System.out.println ("\nGet d'un itineraire par Camion_id et Date\n");
		DAOItineraire daoitineraire = DAOFactory.creerDAOItineraire();
		Itineraire iti = daoitineraire.selectbydateetcamion(new Date(), 2);
		System.out.println ("  id : " + iti.get_id());
		System.out.println ("  Camion_id: " + iti.get_Camion_id());
		System.out.println ("  Planification_id : " + iti.get_Planification_id());
		System.out.println ("  Date : " + iti.get_date());
		List<Ilotdepassage> ilots = iti.get_ilotsdepassage();
		System.out.println ("\n  Nombre d'ilots : " + ilots.size());
		for ( int jj = 0 ; jj < ilots.size(); jj++ ) {
			Ilotdepassage il = ilots.get(jj);
			List<Conteneuravider> cav = il.get_conteneuravider();
			System.out.print ("\n    Ilots : " + il.get_Ilot_id() + " | Ordre : " + il.get_ordre() + " | Itineraire : " + il.get_Itineraire_id() + " | Nombre de conteneur à vider : " + cav.size() + "\n");
			for ( int jjj = 0 ; jjj < cav.size(); jjj++ ) {
				Conteneuravider c = cav.get(jjj);
				System.out.println ("      Conteneur : " + c.Conteneur_id() + " | Ilot : " + c.get_Ilot_id() + " | Itineraire : " + c.get_Itineraire_id());
			}
		}
		System.out.println ("\nFin\n");
		
		//////////////////////////////////////////////////////////////////////
		// Mise à jour l'etat d'un conteneur
		//////////////////////////////////////////////////////////////////////
		
		System.out.println ("\nMise à jour l'etat d'un conteneur\n");
		Conteneur c = new Conteneur(1);
		c.majetat(40,40);
		DAOConteneur daoconteneur = DAOFactory.creerDAOConteneur();
		daoconteneur.majetat(c);
		System.out.println ("\nFin\n");
		
		//////////////////////////////////////////////////////////////////////
		// Recuperer tous les conteneurs
		//////////////////////////////////////////////////////////////////////
		
		System.out.println ("\nRecuperer tous les conteneurs\n");
		DAOConteneur daoconteneur3 = DAOFactory.creerDAOConteneur();
		List<Conteneur> lc = daoconteneur3.select();
		for ( int i = 0 ; i < lc.size(); i++ ) {
			Conteneur c2;
			c2 = lc.get(i);
			System.out.println ("id : " + c2.get_id() + " | ilot : " + c2.get_Ilot_id() + " | Volume max : " + c2.get_volumemax() + " | Type dechets : " + c2.get_TypeDechets_id() + " | last update : " + c2.get_lastupdate() + " | last poids : " + c2.get_lastpoids() + " | last volume : " + c2.get_lastvolume());
		}
		System.out.println ("\nFin\n");
		
		//////////////////////////////////////////////////////////////////////
		// Recuperer tous les ilots
		//////////////////////////////////////////////////////////////////////
		
		System.out.println ("\nRecuperer tous les ilots\n");
		DAOIlot daoilot = DAOFactory.creerDAOIlot();
		List<Ilot> li = daoilot.select();
		for ( int j = 0 ; j < li.size(); j++ ) {
			Ilot ilot = li.get(j);
			System.out.println ("id : " + ilot.get_id() + " | Adresse : " + ilot.get_adresse() + " | Code postal : " + ilot.get_codepostal() + " | Ville : " + ilot.get_ville() + " | Longitude : " + ilot.get_longitude() + " | Latitude : " + ilot.get_latitude() + " | Contact id : " + ilot.get_Contact_id());
		}
		System.out.println ("\nFin\n");
		
		//////////////////////////////////////////////////////////////////////
		// Recuperer tous les camions
		//////////////////////////////////////////////////////////////////////
		
		System.out.println ("\nRécuperer tous les camions\n");
		DAOCamion daocamion = DAOFactory.creerDAOCamion();
		List<Camion> lcam = daocamion.select();
		for ( int jrr = 0 ; jrr < lcam.size(); jrr++ ) {
			Camion camion = lcam.get(jrr);
			System.out.println ("id : " + camion.get_id() + " | Poids max : " + camion.get_poidsmax() + " | Disponible : " + camion.get_disponible());
		}
		System.out.println ("\nFin\n");
		
		//////////////////////////////////////////////////////////////////////
		// Changer état d'un camion
		//////////////////////////////////////////////////////////////////////
		
		System.out.println ("\nChanger état d'un camion\n");
		boolean dispo = true;
		Camion camionachanger = new Camion(1,19,dispo);
		System.out.println ("Camion 1 : disponible à "+dispo);
		DAOCamion daocamion3 = DAOFactory.creerDAOCamion();
		daocamion3.update(camionachanger);
		System.out.println ("\nFin\n");
		
		//////////////////////////////////////////////////////////////////////
		// Recuperer tous les camions
		//////////////////////////////////////////////////////////////////////
		
		System.out.println ("\nRécuperer tous les camions\n");
		DAOCamion daocamion2 = DAOFactory.creerDAOCamion();
		List<Camion> lcam2 = daocamion2.select();
		for ( int jrr2 = 0 ; jrr2 < lcam2.size(); jrr2++ ) {
			Camion camion = lcam2.get(jrr2);
			System.out.println ("id : " + camion.get_id() + " | Poids max : " + camion.get_poidsmax() + " | Disponible : " + camion.get_disponible());
		}
		System.out.println ("\nFin\n");
	}
}