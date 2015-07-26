package DAOS;
import DAOS.DAOConteneur;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import data.Conteneur;

public class DAOMysqlConteneur implements DAOConteneur {

    private static final String sqlSelect = "SELECT * FROM conteneur;";
    private static String sqlInsert = "INSERT INTO conteneur "
            + " (id, volumemax, ilot_id, typedechets_id) "
            + " VALUES( ";
    private static String sqlDelete = "DELETE FROM conteneur WHERE id = '";
    private static String sqlUpdate = "UPDATE conteneur " +
            " (id, volumemax, ilot_id, typedechets_id) " +
            " VALUES (" ;
    
    @Override
    public List<Conteneur> select() throws Exception {
        List<Conteneur> liste = new LinkedList<Conteneur>();
        return liste;
    }

    @Override
    public int insert(Conteneur c) throws Exception {
        //connexion
        Connection cnx = BDManager.getConnexion();
        //executer la requête
        Statement s = cnx.createStatement();
        sqlInsert += "'" + c.get_id() + "',";
        sqlInsert += "'" + c.get_volumemax() + "',";
        sqlInsert += "'" + c.get_Ilot_id() + "',";
        sqlInsert += c.get_TypeDechets_id() + ");";

        int n = s.executeUpdate(sqlInsert);

        s.close();
        cnx.close();
        return n;
    }

    @Override
    public int update(Conteneur c) throws Exception {
        //connexion
        Connection cnx = BDManager.getConnexion();
        //executer la requête
        Statement s = cnx.createStatement();
        sqlUpdate += "'" + c.get_id() + "',";
        sqlUpdate += "'" + c.get_volumemax() + "',";
        sqlUpdate += c.get_Ilot_id() + ",";
        sqlUpdate += c.get_TypeDechets_id() + ") ";
        sqlUpdate += " WHERE reference = ' " + c.get_id() + "' ;";

        int n = s.executeUpdate(sqlUpdate);

        s.close();
        cnx.close();
        return n;
    }
    
    @Override
    public int delete(Conteneur c) throws Exception {
        Connection cnx = BDManager.getConnexion();
        Statement s = cnx.createStatement();
        sqlDelete += c.get_id() + "';";
        int n = s.executeUpdate(sqlDelete);
        return n;
    }
}
