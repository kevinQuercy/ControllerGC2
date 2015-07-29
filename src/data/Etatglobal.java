package data;

import java.util.LinkedList;
import java.util.List;

import DAOS.DAOCamion;
import DAOS.DAOFactory;
import DAOS.DAOIlot;
import DAOS.DAOTypedechets;

public class Etatglobal {
	private List<Ilotsavider> ilotsavider;
	
	public Etatglobal(int taux) throws Exception {
		ilotsavider = new LinkedList<Ilotsavider>();
		DAOIlot daoilot = DAOFactory.creerDAOIlot();
		// recuperation de tous les ilots
		List<Ilot> touslesilots = daoilot.select();
		// recuperation de tous les types de dechets
		DAOTypedechets daotypedechets = DAOFactory.creerDAOTypedechets();
		List<Typedechets> touslesdechets = daotypedechets.select();
		System.out.println ("Nombre de types de dechets : " + touslesdechets.size());
		DAOCamion daocamion = DAOFactory.creerDAOCamion();
		List<Camion> camionsdisponibles = daocamion.selectdisponible();
		System.out.println ("Nombre de camions disponibles : " + camionsdisponibles.size());
		for ( int i = 0 ; i < touslesdechets.size(); i++ ) {
			Ilotsavider il = new Ilotsavider(touslesdechets.get(i).get_id());
			for ( int j = 0 ; j < camionsdisponibles.size(); j++ ) {
				if(camionsdisponibles.get(j).get_Typedechets_id() == touslesdechets.get(i).get_id()) {
					il.ajoutercamion(camionsdisponibles.get(j));
				}
			}
			for (int k = 0 ; k < touslesilots.size() ; k++ ) {
				List<Conteneur> cont = touslesilots.get(k).get_conteneurs();
				int volumemax = 0;
				int volumeencours = 0;
				int poids = 0;
				for (int l = 0 ; l < cont.size(); l++) {
					if(cont.get(l).get_TypeDechets_id() == touslesdechets.get(i).get_id()) {
						volumemax = volumemax + cont.get(l).get_volumemax();
						volumeencours = volumeencours + cont.get(l).get_lastvolume();
						poids = poids + cont.get(l).get_lastpoids();
					}
				}
				if(volumeencours > 0 ) {
					if(volumeencours  * 100 / volumemax >= taux) {
						il.ajouterilot(touslesilots.get(k));
					}
				}
			}
			this.ajouterilotsavider(il);
		}
	}
	
	public void ajouterilotsavider(Ilotsavider i) {
		this.ilotsavider.add(i);
	}
	public List<Ilotsavider> get_ilotsavider() {
		return this.ilotsavider;
	}
}
