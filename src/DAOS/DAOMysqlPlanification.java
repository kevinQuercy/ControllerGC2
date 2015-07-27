package DAOS;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import DAOS.DAOIlotdepassage;
import data.Itineraire;
import data.Planification;

public class DAOMysqlPlanification implements DAOPlanification {

	@Override
	public Planification selectbydate(Date d) throws Exception {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
	    String sql = "SELECT * FROM Ilotdepassage where date = '" + sdf.format(d) + "';";
        //ouvrir la connexion
        Connection cnx = BDManager.getConnexion();
        //faire la requête
        Statement s = cnx.createStatement();
        ResultSet r = s.executeQuery(sql);
        //traiter les réponses
		Planification pl = new Planification();
        pl.set_id(r.getInt("id"));
		pl.set_datecreation(r.getDate("datecreation"));
		pl.set_taux(r.getInt("taux"));
		DAOItineraire daoItineraire = DAOFactory.creerDAOItineraire();
		pl.set_itineraires(daoItineraire.selectbyplanificationid(pl.get_id()));
        r.close();
        s.close();
        cnx.close();
        return pl;
	}

	@Override
	public int insert(Planification pl) throws Exception {
	    String sql = "INSERT INTO Planification (datecreation,taux) " + " VALUES( ";
        //connexion
        Connection cnx = BDManager.getConnexion();
        //executer la requête
        Statement s = cnx.createStatement();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		sql += "'" + sdf.format(pl.get_datecreation()) + "',";
        sql += "'" + pl.get_taux() + "')";
        int n = s.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
        ResultSet id = s.getGeneratedKeys();
        int lastid = 1;
        while (id.next()) {
          lastid = id.getInt(1);
        }
		DAOItineraire daoItineraire = DAOFactory.creerDAOItineraire();
		List<Itineraire> itineraires = pl.get_itineraires();
		for( int i = 0 ; i < itineraires.size() ; i++ ) {
			Itineraire it = itineraires.get(i);
			it.set_Planification_id(lastid);
			daoItineraire.insert(it);
		}
        s.close();
        cnx.close();
        return n;
	}

	@Override
	public int deletebyplanificationid(int plid) throws Exception {
		// delete de tous les itineraires
		DAOItineraire daoItineraire = DAOFactory.creerDAOItineraire();
		List<Itineraire> itineraires = daoItineraire.selectbyplanificationid(plid);
		for( int i = 0 ; i < itineraires.size() ; i++ ) {
			daoItineraire.delete(itineraires.get(i));
		}
		// delete de la planification
	    String sql = "DELETE FROM Planification  WHERE id = '" + plid + "';";
        Connection cnx = BDManager.getConnexion();
        Statement s = cnx.createStatement();
        int n = s.executeUpdate(sql);
        return n;
	}
}
