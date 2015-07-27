package DAOS;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import DAOS.DAOIlotdepassage;
import data.Conteneuravider;
import data.Ilotdepassage;
import data.Itineraire;

public class DAOMysqlIlotdepassage implements DAOIlotdepassage {

	@Override
	public List<Ilotdepassage> selectbyitineraire(Itineraire i) throws Exception {
	    String sql = "SELECT * FROM Ilotdepassage WHERE Itineraire_id = '" + i.get_id() + "';";
        List<Ilotdepassage> liste = new LinkedList<Ilotdepassage>();
        //ouvrir la connexion
        Connection cnx = BDManager.getConnexion();
        //faire la requête
        Statement s = cnx.createStatement();
        
        ResultSet r = s.executeQuery(sql);
        
		DAOConteneuravider daoconteneuravider = DAOFactory.creerDAOConteneuravider();
        //traiter les réponses
        while (r.next()) {
        	Ilotdepassage h = new Ilotdepassage();
            //récupérer les champs
            h.set_Ilot_id(r.getInt("Ilot_id"));
            h.set_Itineraire_id(r.getInt("Itineraire_id"));
            h.set_ordre(r.getInt("ordre"));
			h.set_conteneuravider(daoconteneuravider.selectbyilotanditineraireid(h.get_Ilot_id(),h.get_Itineraire_id()));
            //ajouter à la liste
            liste.add(h);
        }

        r.close();
        s.close();
        cnx.close();

        return liste;
	}

	@Override
	public int insert(Ilotdepassage it) throws Exception {
	    String sql = "INSERT INTO Ilotdepassage " + " (Itineraire_id,Ilot_id,ordre) " + " VALUES( ";
        //connexion
        Connection cnx = BDManager.getConnexion();
        //executer la requête
        Statement s = cnx.createStatement();
        sql += "'" + it.get_Itineraire_id() + "',";
        sql += "'" + it.get_Ilot_id() + "',";
        sql += "'" + it.get_ordre() + "')";
        System.out.println(sql);
        int n = s.executeUpdate(sql);
        List<Conteneuravider> lcav = it.get_conteneuravider();
        DAOConteneuravider daoconteneuravider = DAOFactory.creerDAOConteneuravider();
        for (int i = 0 ; i < lcav.size(); i++) {
        	Conteneuravider c = lcav.get(i);
        	c.set_Itineraire_id(it.get_Itineraire_id());
        	c.set_Ilot_id(it.get_Ilot_id());
        	daoconteneuravider.insert(c);
        }
        s.close();
        cnx.close();
        return n;
	}

	@Override
	public int delete(Ilotdepassage il) throws Exception {
		// Delete de tous les conteneurs a vider
		DAOConteneuravider daoconteneuravider = DAOFactory.creerDAOConteneuravider();
		List<Conteneuravider> lav = il.get_conteneuravider();
		for ( int i = 0 ; i < lav.size() ; i++ ) {
			daoconteneuravider.delete(lav.get(i));
		}
		// Delete de l'ilot
	    String sql = "DELETE FROM Ilotdepassage WHERE Itineraire_id = '" + il.get_Itineraire_id() + "' AND Ilot_id = '" + il.get_Ilot_id() + "' ;";
        Connection cnx = BDManager.getConnexion();
        Statement s = cnx.createStatement();

        int n = s.executeUpdate(sql);

		s.close();
        cnx.close();
        return n;
	}
}
