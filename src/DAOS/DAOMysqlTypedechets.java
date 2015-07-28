package DAOS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import data.Typedechets;

public class DAOMysqlTypedechets implements DAOTypedechets {
	@Override
	public List<Typedechets> select() throws Exception {
        String sql = "SELECT * FROM Typedechets;";
        List<Typedechets> liste = new LinkedList<Typedechets>();
        //ouvrir la connexion
        Connection cnx = BDManager.getConnexion();
        //faire la requête
        Statement s = cnx.createStatement();
        ResultSet r = s.executeQuery(sql);
        
        //traiter les réponses
        while (r.next()) {
            liste.add(new Typedechets(r.getInt("id"),r.getString("libelle")));
        }

        r.close();
        s.close();
        cnx.close();

        return liste;
	}
}
