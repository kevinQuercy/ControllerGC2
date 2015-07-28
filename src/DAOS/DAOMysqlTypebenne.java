package DAOS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import data.Typebenne;

public class DAOMysqlTypebenne implements DAOTypebenne {

	@Override
	public List<Typebenne> select() throws Exception {
        String sql = "SELECT * FROM Typebenne;";
        List<Typebenne> liste = new LinkedList<Typebenne>();
        //ouvrir la connexion
        Connection cnx = BDManager.getConnexion();
        //faire la requête
        Statement s = cnx.createStatement();
        ResultSet r = s.executeQuery(sql);
        
        //traiter les réponses
        while (r.next()) {
            liste.add(new Typebenne(r.getInt("id"),r.getInt("volume"),r.getInt("Typedechets_id")));
        }

        r.close();
        s.close();
        cnx.close();

        return liste;
	}

}
