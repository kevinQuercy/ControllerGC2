package webservice;

import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import DAOS.DAOFactory;
import DAOS.DAOIlot;
import DAOS.DAOPlanification;
import data.Ilotdepassage;
import data.Itineraire;
import data.Planification;

@WebService(endpointInterface="webservice.WSPlanification")
public class WSPlanificationImp implements WSPlanification {

	@Override
	public Planification creer() throws Exception {
		DAOIlot daoilot = DAOFactory.creerDAOIlot();
		Itineraire it1 = new Itineraire(1,2);
		it1.ajouterilot(daoilot.selectbyid(1),1);
		it1.ajouterilot(daoilot.selectbyid(2),2);
		it1.ajouterilot(daoilot.selectbyid(3),3);
		it1.set_longueur(10000);
		it1.set_volumetotal(3600);
		it1.set_poidstotal(1500);
		Itineraire it2 = new Itineraire(2,4);
		it2.ajouterilot(daoilot.selectbyid(4),1);
		it2.ajouterilot(daoilot.selectbyid(5),2);
		it2.ajouterilot(daoilot.selectbyid(6),3);
		it2.set_longueur(14000);
		it2.set_volumetotal(3600);
		it2.set_poidstotal(1500);
		Itineraire it3 = new Itineraire(3,6);
		it3.ajouterilot(daoilot.selectbyid(7),1);
		it3.ajouterilot(daoilot.selectbyid(8),2);
		it3.ajouterilot(daoilot.selectbyid(9),3);
		it3.set_longueur(12000);
		it3.set_volumetotal(3600);
		it3.set_poidstotal(1500);
		Planification planif = new Planification();
		planif.set_date(new Date());
		planif.ajouteritineraire(it1);
		planif.ajouteritineraire(it2);
		planif.ajouteritineraire(it3);
		planif.set_taux(50);
		return planif;
	}
}
