package core;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import DAOS.*;
import data.*;

public class DAOExample {

	public static void main(String[] args) throws Exception {
		/*System.out.println ("Creation du DAO Conteneur ...");
		DAOConteneur daocont = DAOFactory.creerDAOConteneur();
		System.out.println ("ok \n");
		Conteneur cont = new Conteneur();
		cont.set_id(100);
		cont.set_volumemax(27000);
		cont.set_Ilot_id(1);
		cont.set_TypeDechets_id(1);
		System.out.println ("Conteneur : id " + cont.get_id());
		int result = daocont.insert(cont);
		System.out.println ("Creation du DAO Historique ...");
		DAOHistorique daohis = DAOFactory.creerDAOHistorique();
		System.out.println ("ok \n");
		Historique his = new Historique();
		his.set_poids(20);
		his.set_volume(450);
		his.set_Conteneur_id(100);
		int result1 = daohis.insert(his);
		Historique his2 = new Historique();
		his2.set_poids(21);
		his2.set_volume(450);
		his2.set_Conteneur_id(100);
		result1 = daohis.insert(his2);
		System.out.println ("Recuperation de la liste :");
		List<Historique> lh = daohis.selectByConteneur(100);
		for (int i=0; i<lh.size(); i++)
		{
			Historique h = lh.get(i);
			System.out.println(h)  ;
		}*/
		System.out.println ("##############################################");
		System.out.println ("#  Creation d'un itineraire                  #");
		System.out.println ("##############################################\n\n");
		
		System.out.println ("Creation de 3 Ilotsde passage avec 3 Conteneurs a vider\n");
		Ilotdepassage ilot1 = new Ilotdepassage(1,1);
		ilot1.ajouterconteneur(new Conteneuravider(1));
		ilot1.ajouterconteneur(new Conteneuravider(2));
		ilot1.ajouterconteneur(new Conteneuravider(3));
		Ilotdepassage ilot2 = new Ilotdepassage(2,2);
		ilot2.ajouterconteneur(new Conteneuravider(4));
		ilot2.ajouterconteneur(new Conteneuravider(5));
		ilot2.ajouterconteneur(new Conteneuravider(6));
		Ilotdepassage ilot3 = new Ilotdepassage(3,3);
		ilot3.ajouterconteneur(new Conteneuravider(7));
		ilot3.ajouterconteneur(new Conteneuravider(8));
		ilot3.ajouterconteneur(new Conteneuravider(9));
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
		
		System.out.println ("Sauvegarde de la planification\n");
		DAOPlanification daoplanification = DAOFactory.creerDAOPlanification();
		daoplanification.insert(planif);
		System.out.println ("Fin\n");
		//DAOPlanification daoplanification = DAOFactory.creerDAOPlanification();
		//daoplanification.deletebyplanificationid(5);
	}
		
}