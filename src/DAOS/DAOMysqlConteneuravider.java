package DAOS;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import DAOS.DAOConteneuravider;
import data.Conteneuravider;

public class DAOMysqlConteneuravider implements DAOConteneuravider {

	@Override
	public List<Conteneuravider> selectbyilotanditineraireid(int ilid,int itid) throws Exception {
	    String sql = "SELECT * FROM Conteneuravider WHERE Itineraire_id = '" + itid + "' AND Ilot_id = '"+ ilid +"';";
        List<Conteneuravider> liste = new LinkedList<Conteneuravider>();
        //ouvrir la connexion
        Connection cnx = BDManager.getConnexion();
        //faire la requête
        Statement s = cnx.createStatement();
        ResultSet r = s.executeQuery(sql);

        //traiter les réponses
        while (r.next()) {
        	Conteneuravider c = new Conteneuravider();
            //récupérer les champs
            c.set_Ilot_id(r.getInt("Ilot_id"));
            c.set_Itineraire_id(r.getInt("Itineraire_id"));
            c.set_Conteneur_id(r.getInt("Conteneur_id"));
            //ajouter à la liste
            liste.add(c);
        }
        
        r.close();
        s.close();
        cnx.close();

        return liste;
	}

	@Override
	public int insert(Conteneuravider c) throws Exception {
	    String sql = "INSERT INTO Conteneuravider " + " (Ilot_id,Itineraire_id,Conteneur_id) " + " VALUES( ";
        //connexion
        Connection cnx = BDManager.getConnexion();
        //executer la requête
        Statement s = cnx.createStatement();
        sql += "'" + c.get_Ilot_id() + "',";
        sql += "'" + c.get_Itineraire_id() + "',";
        sql += "'" + c.Conteneur_id() + "')";
        System.out.println (sql);
        int n = s.executeUpdate(sql);
        
        s.close();
        cnx.close();
        return n;
	}

	@Override
	public int delete(Conteneuravider c) throws Exception {
	    String sql = "DELETE FROM Conteneuravider WHERE Itineraire_id = '" + c.get_Itineraire_id() + "' AND Ilot_id = '" + c.get_Ilot_id() + "' AND Conteneur_id='" + c.Conteneur_id() + "';";
        Connection cnx = BDManager.getConnexion();
        Statement s = cnx.createStatement();

        int n = s.executeUpdate(sql);

		s.close();
        cnx.close();
        return n;
	}
}
