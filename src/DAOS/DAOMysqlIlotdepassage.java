package DAOS;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import DAOS.DAOIlotdepassage;
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

        //traiter les réponses
        while (r.next()) {
        	Ilotdepassage h = new Ilotdepassage();
            //récupérer les champs
        	DAOIlot daoilot = DAOFactory.creerDAOIlot();
            h.set_ilot(daoilot.selectbyid(r.getInt("Ilot_id")));
            h.set_Itineraire_id(r.getInt("Itineraire_id"));
            h.set_ordre(r.getInt("ordre"));
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
        sql += "'" + it.get_Ilot().get_id() + "',";
        sql += "'" + it.get_ordre() + "')";
        
        int n = s.executeUpdate(sql);
        
        s.close();
        cnx.close();
        return n;
	}

	@Override
	public int delete(Ilotdepassage il) throws Exception {
	    String sql = "DELETE FROM Ilotdepassage WHERE Itineraire_id = '" + il.get_Itineraire_id() + "' AND Ilot_id = '" + il.get_Ilot().get_id() + "' ;";
        Connection cnx = BDManager.getConnexion();
        Statement s = cnx.createStatement();

        int n = s.executeUpdate(sql);

		s.close();
        cnx.close();
        return n;
	}
}
