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
	public Planification creer(Date d) throws Exception {
		DAOPlanification daoplanification = DAOFactory.creerDAOPlanification();
		return(daoplanification.selectbydate(d));
		
	}
}
