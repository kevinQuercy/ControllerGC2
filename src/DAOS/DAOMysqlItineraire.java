package DAOS;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import DAOS.DAOItineraire;
import data.Itineraire;
import data.Ilotdepassage;

public class DAOMysqlItineraire implements DAOItineraire {

	@Override
	public List<Itineraire> selectbydate(Date d) throws Exception {
		// recuperation des itineraires
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
	    String sql = "SELECT * FROM Itineraire WHERE Planification_date = '" + sdf.format(d) + "';";
        List<Itineraire> liste = new LinkedList<Itineraire>();
        //ouvrir la connexion
        Connection cnx = BDManager.getConnexion();
        //faire la requête
        Statement s = cnx.createStatement();
        ResultSet r = s.executeQuery(sql);
		DAOIlotdepassage daoIlotdepassage = DAOFactory.creerDAOIlotdepassage();
		r.beforeFirst();
        //traiter les réponses
        while (r.next()) {
        	Itineraire h = new Itineraire();
            h.set_id(r.getInt("id"));
            h.set_Camion_id(r.getInt("Camion_id"));
            h.set_longueur(r.getInt("longueur"));
            h.set_volumetotal(r.getInt("volumetotal"));
            h.set_poidstotal(r.getInt("poidstotal"));
            h.set_Typedechets_id(r.getInt("Typedechets_id"));
            h.set_Planification_date(r.getDate("Planification_date"));
			h.set_ilotsdepassage(daoIlotdepassage.selectbyitineraire(h));
            liste.add(h);
        }
        r.close();
        s.close();
        cnx.close();
		
		// Recuperation de la liste des Ilotsdepassage
		
        return liste;
	}
	
	public Itineraire selectbydateetcamion(Date d,int camionid) throws Exception {
		// recuperation des itineraires
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
	    String sql = "SELECT * FROM Itineraire WHERE Planification_date = '" + sdf.format(d) + "' AND Camion_id = '"+ camionid +"';";
        //ouvrir la connexion
        Connection cnx = BDManager.getConnexion();
        //faire la requête
        Statement s = cnx.createStatement();
        ResultSet r = s.executeQuery(sql);
		DAOIlotdepassage daoIlotdepassage = DAOFactory.creerDAOIlotdepassage();
		r.beforeFirst();
        //traiter les réponses
        r.next();
    	Itineraire h = new Itineraire();
        h.set_id(r.getInt("id"));
        h.set_Planification_date(r.getDate("Planification_date"));
        h.set_Camion_id(r.getInt("Camion_id"));
        h.set_longueur(r.getInt("longueur"));
        h.set_volumetotal(r.getInt("volumetotal"));
        h.set_poidstotal(r.getInt("poidstotal"));
        h.set_Typedechets_id(r.getInt("Typedechets_id"));
		h.set_ilotsdepassage(daoIlotdepassage.selectbyitineraire(h));
        r.close();
        s.close();
        cnx.close();
		
		// Recuperation de la liste des Ilotsdepassage
		
        return h;
	}

	@Override
	public int insert(Itineraire it) throws Exception {
	    String sql = "INSERT INTO Itineraire (Planification_date,Camion_id,longueur,volumetotal,poidstotal,Typedechets_id) " + " VALUES( ";
        //connexion
        Connection cnx = BDManager.getConnexion();
        //executer la requête
        Statement s = cnx.createStatement();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        sql += "'" + sdf.format(it.get_Planification_date()) + "',";
        sql += "'" + it.get_Camion_id() + "',";
        sql += "'" + it.get_longueur() + "',";
        sql += "'" + it.get_volumetotal() + "',";
        sql += "'" + it.get_poidstotal() + "',";
        sql += "'" + it.get_Typedechets_id() + "')";
        int n = s.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
        ResultSet id = s.getGeneratedKeys();
        int lastid = 1;
        while (id.next()) {
          lastid = id.getInt(1);
        }
		s.close();
        cnx.close();
		it.set_id(lastid);
		// Insert des ilotsdepassage
		List<Ilotdepassage> lip = it.get_ilotsdepassage();
		DAOIlotdepassage daoIlotdepassage = DAOFactory.creerDAOIlotdepassage();
		for( int i = 0 ; i < lip.size() ; i++ ) {
			Ilotdepassage il = lip.get(i);
			il.set_Itineraire_id(it.get_id());
			daoIlotdepassage.insert(il);
		}
        return n;
	}

	@Override
	public int delete(Itineraire it) throws Exception {
		// destruction de tous les Ilotsdepassage
		//// creation d'un dao Ilotsdepassage
		DAOIlotdepassage daoIlotdepassage = DAOFactory.creerDAOIlotdepassage();
		//// recuperation de la liste des ilotsdepassage
		List<Ilotdepassage> lip = it.get_ilotsdepassage();
		for(int is = 0 ; is < lip.size() ; is++) {
			Ilotdepassage ip = lip.get(is);
			daoIlotdepassage.delete(ip);
		}
		// Destruction de l'itineraire
		String sql = "DELETE FROM Itineraire WHERE id = '" + it.get_id() + "';";
        Connection cnx = BDManager.getConnexion();
        Statement s = cnx.createStatement();

        int n = s.executeUpdate(sql);

		s.close();
        cnx.close();
        return n;
	}
}
