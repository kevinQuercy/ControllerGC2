package webservice;

import javax.jws.WebService;

import DAOS.DAOFactory;
import DAOS.DAOIlot;
import data.Ilot;

@WebService(endpointInterface="webservice.WSIlot")
public class WSIlotImp implements WSIlot {

	@Override
	public Ilot select(int id) throws Exception {
		DAOIlot daoilot = DAOFactory.creerDAOIlot();
		return(daoilot.selectbyid(id));
		
	}
}