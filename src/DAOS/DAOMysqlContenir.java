package DAOS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import data.Contenir;

public class DAOMysqlContenir implements DAOContenir {

	@Override
	public List<Contenir> select() throws Exception {
        String sql = "SELECT * FROM Contenir;";
        List<Contenir> liste = new LinkedList<Contenir>();
        //ouvrir la connexion
        Connection cnx = BDManager.getConnexion();
        //faire la requête
        Statement s = cnx.createStatement();
        ResultSet r = s.executeQuery(sql);
        
        //traiter les réponses
        while (r.next()) {
            liste.add(new Contenir(r.getInt("Camion_id"),r.getInt("Typebenne_id")));
        }

        r.close();
        s.close();
        cnx.close();

        return liste;
	}

}
