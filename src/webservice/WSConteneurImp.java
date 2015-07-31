package webservice;

import javax.jws.WebService;

import DAOS.DAOConteneur;
import DAOS.DAOFactory;
import data.Conteneur;

@WebService(endpointInterface="webservice.WSConteneur")
public class WSConteneurImp implements WSConteneur {

	@Override
	public int maj(int id,int volume,int poids) throws Exception {
		Conteneur ctomaj = new Conteneur(id);
		ctomaj.majetat(volume, poids);
		DAOConteneur daoconteneur = DAOFactory.creerDAOConteneur();
		daoconteneur.majetat(ctomaj);
		return 0;
	}
}
