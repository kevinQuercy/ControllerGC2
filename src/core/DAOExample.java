package core;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import DAOS.*;
import data.*;

public class DAOExample {

		/*DAOHistorique daohistorique = DAOFactory.creerDAOHistorique();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -5);
		Date datelimite = cal.getTime();
		for (int i = 1 ; i < 107 ; i++){
			Historique h = new Historique(i,datelimite,0,0);
			daohistorique.insert(h);
		}*/

		
		//////////////////////////////////////////////////////////////////////
		// Get d'un itineraire par Camion_id et Date
		//////////////////////////////////////////////////////////////////////

		/*System.out.println ("\nGet d'un itineraire par Camion_id et Date\n");
		DAOItineraire daoitineraire = DAOFactory.creerDAOItineraire();
		Itineraire iti = daoitineraire.selectbydateetcamion(new Date(), 2);
		System.out.println ("  id : " + iti.get_id());
		System.out.println ("  Camion_id: " + iti.get_Camion_id());
		System.out.println ("  Planification_date : " + iti.get_Planification_date());
		System.out.println ("  Longueur : " + iti.get_longueur());
		List<Ilotdepassage> ilots = iti.get_ilotsdepassage();
		System.out.println ("\n  Nombre d'ilots : " + ilots.size());
		for ( int jj = 0 ; jj < ilots.size(); jj++ ) {
			Ilotdepassage il = ilots.get(jj);
			System.out.print ("\n    Ilots : " + il.get_Ilot().get_id() + " | Ordre : " + il.get_ordre() + " | Itineraire : " + il.get_Itineraire_id());
		}
		System.out.println ("\nFin\n");*/
		
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