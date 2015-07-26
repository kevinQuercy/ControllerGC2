package core;
import java.util.List;

import DAOS.*;
import data.*;

public class DAOTester {

	public static void main(String[] args) throws Exception {
		/*System.out.println ("Erreur lors de la lecture : ");
		Conteneur cont = new Conteneur();
		cont.set_id(100);
		cont.set_volumemax(27000);
		cont.set_Ilot_id(1);
		cont.set_TypeDechets_id(1);
		System.out.println ("Conteneur : id " + cont.get_id());
		DAOConteneur daocont = DAOFactory.creerDAOConteneur();
		int result = daocont.insert(cont);
		System.out.println ("Erreur lors de la lecture : ");
		Historique his = new Historique();
		his.set_poids(20);
		his.set_volume(45000);
		his.set_Conteneur_id(100);
		DAOHistorique daohis = DAOFactory.creerDAOHistorique();
		int result1 = daohis.insert(his);*/
		Historique his2 = new Historique();
		his2.set_poids(21);
		his2.set_volume(45000);
		his2.set_Conteneur_id(100);
		DAOHistorique daohis = DAOFactory.creerDAOHistorique();
		int result1 = daohis.insert(his2);
		System.out.println ("Recuperation de la liste");
		List<Historique> lh = daohis.selectByConteneur(100);
		for (int i=0; i<lh.size(); i++)
		{
			Historique h = lh.get(i);
			System.out.println(h)  ;
		}
	}
		
}