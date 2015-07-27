package DAOS;
import DAOS.DAOConteneur;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import data.Conteneur;

public class DAOMysqlConteneur implements DAOConteneur {
    
    @Override
    public List<Conteneur> select() throws Exception {
        String sql = "SELECT * FROM conteneur;";
        List<Conteneur> liste = new LinkedList<Conteneur>();
        //ouvrir la connexion
        Connection cnx = BDManager.getConnexion();
        //faire la requête
        Statement s = cnx.createStatement();
        ResultSet r = s.executeQuery(sql);

        //traiter les réponses
        while (r.next()) {
        	Conteneur h = new Conteneur();
            //récupérer les champs
            h.set_id(r.getInt("id"));
            h.set_volumemax(r.getInt("volumemax"));
            h.set_Ilot_id(r.getInt("Ilot_id"));
            h.set_TypeDechets_id(r.getInt("TypeDechets_id"));
            //ajouter à la liste
            liste.add(h);
        }
        
        r.close();
        s.close();
        cnx.close();

        return liste;
    }

    @Override
    public int insert(Conteneur c) throws Exception {
        String sql = "INSERT INTO conteneur " + " (id, volumemax, ilot_id, typedechets_id) " + " VALUES( ";
        //connexion
        Connection cnx = BDManager.getConnexion();
        //executer la requête
        Statement s = cnx.createStatement();
        sql += "'" + c.get_id() + "',";
        sql += "'" + c.get_volumemax() + "',";
        sql += "'" + c.get_Ilot_id() + "',";
        sql += c.get_TypeDechets_id() + ");";

        int n = s.executeUpdate(sql);

        s.close();
        cnx.close();
        return n;
    }

    @Override
    public int update(Conteneur c) throws Exception {
        String sql = "UPDATE conteneur " + " (id, volumemax,ilot_id, typedechets_id) " + " VALUES (" ;
        //connexion
        Connection cnx = BDManager.getConnexion();
        //executer la requête
        Statement s = cnx.createStatement();
        sql += "'" + c.get_id() + "',";
        sql += "'" + c.get_volumemax() + "',";
        sql += c.get_Ilot_id() + ",";
        sql += c.get_TypeDechets_id() + ") ";
        sql += " WHERE reference = ' " + c.get_id() + "' ;";

        int n = s.executeUpdate(sql);

        s.close();
        cnx.close();
        return n;
    }
    
    @Override
    public int delete(Conteneur c) throws Exception {
    	String sql = "DELETE FROM conteneur WHERE id = '";
        Connection cnx = BDManager.getConnexion();
        Statement s = cnx.createStatement();
        sql += c.get_id() + "';";
        int n = s.executeUpdate(sql);
        return n;
    }
}
