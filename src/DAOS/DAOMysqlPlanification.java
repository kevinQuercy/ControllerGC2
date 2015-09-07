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
	// return a Planification selected by date
	public Planification selectbydate(Date d) throws Exception {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
	    String sql = "SELECT * FROM Planification where date = '" + sdf.format(d) + "';";
        //ouvrir la connexion
        Connection cnx = BDManager.getConnexion();
        //faire la requête
        Statement s = cnx.createStatement();
        ResultSet r = s.executeQuery(sql);
        //traiter les réponses
		Planification pl = new Planification();
		r.beforeFirst();
		r.next();
		pl.set_date(r.getDate("date"));
		pl.set_taux(r.getInt("taux"));
		DAOItineraire daoItineraire = DAOFactory.creerDAOItineraire();
		pl.set_itineraires(daoItineraire.selectbydate(d));
        r.close();
        s.close();
        cnx.close();
        return pl;
	}

	@Override
	// insert a planification
	public int insert(Planification pl) throws Exception {
	    String sql = "INSERT INTO Planification (date,taux) " + " VALUES( ";
        //connexion
        Connection cnx = BDManager.getConnexion();
        //executer la requête
        Statement s = cnx.createStatement();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		sql += "'" + sdf.format(pl.get_date()) + "',";
        sql += "'" + pl.get_taux() + "')";
        int n = s.executeUpdate(sql);
		DAOItineraire daoItineraire = DAOFactory.creerDAOItineraire();
		List<Itineraire> itineraires = pl.get_itineraires();
		for( int i = 0 ; i < itineraires.size() ; i++ ) {
			Itineraire it = itineraires.get(i);
			it.set_Planification_date(pl.get_date());
			daoItineraire.insert(it);
		}
        s.close();
        cnx.close();
        return n;
	}
	
	@Override
	// Delete a planification by date
	public int deletebydate(Date d) throws Exception {
		// recherche de l'ID de la planification
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        Connection cnx = BDManager.getConnexion();
        Statement s = cnx.createStatement();
		// delete de tous les itineraires
		DAOItineraire daoItineraire = DAOFactory.creerDAOItineraire();
		List<Itineraire> itineraires = daoItineraire.selectbydate(d);
		for( int i = 0 ; i < itineraires.size() ; i++ ) {
			daoItineraire.delete(itineraires.get(i));
		}
		// delete de la planification
	    String sql = "DELETE FROM Planification  WHERE date = '" + sdf.format(d) + "';";
        int n = s.executeUpdate(sql);
        return n;
	}
}
