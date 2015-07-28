package DAOS;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import data.Itineraire;
import data.Planification;

public class DAOMysqlPlanification implements DAOPlanification {

	@Override
	public Planification selectbydate(Date d) throws Exception {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
	    String sql = "SELECT * FROM Planification where datecreation = '" + sdf.format(d) + "';";
        //ouvrir la connexion
        Connection cnx = BDManager.getConnexion();
        //faire la requête
        Statement s = cnx.createStatement();
        ResultSet r = s.executeQuery(sql);
        //traiter les réponses
		Planification pl = new Planification();
		r.beforeFirst();
		r.next();
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
	
	@Override
	public int deletebydate(Date d) throws Exception {
		// recherche de l'ID de la planification
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String sql = "SELECT id FROM Planification  WHERE datecreation = '" + sdf.format(d) + "';";
        Connection cnx = BDManager.getConnexion();
        Statement s = cnx.createStatement();
        ResultSet r = s.executeQuery(sql);
        r.beforeFirst();
        r.next();
        int idplanif = r.getInt("id");
		// delete de tous les itineraires
		DAOItineraire daoItineraire = DAOFactory.creerDAOItineraire();
		List<Itineraire> itineraires = daoItineraire.selectbyplanificationid(idplanif);
		for( int i = 0 ; i < itineraires.size() ; i++ ) {
			daoItineraire.delete(itineraires.get(i));
		}
		// delete de la planification
	    sql = "DELETE FROM Planification  WHERE datecreation = '" + sdf.format(d) + "';";
        int n = s.executeUpdate(sql);
        return n;
	}
}
