package DAOS;
import DAOS.DAOHistorique;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Date;
import data.Historique;

public class DAOMysqlHistorique implements DAOHistorique {

    private static final String sqlSelect = "SELECT * FROM Historique;";
    private static String sqlSelectByConteneur = "SELECT * FROM Historique WHERE Conteneur_id = ";
    private static String sqlInsert = "INSERT INTO Historique "
            + " (conteneur_id,date,poids,volume) "
            + " VALUES( ";
    private static String sqlDelete = "DELETE FROM Historique WHERE id = '";
    private static String sqlUpdate = "UPDATE Historique "
            + " (conteneur_id,date,poids,volume) "
            + " VALUES (" ;
    
    @Override
    public List<Historique> select() throws Exception {
        List<Historique> liste = new LinkedList<Historique>();
        //ouvrir la connexion
        Connection cnx = BDManager.getConnexion();
        //faire la requête
        Statement s = cnx.createStatement();
        ResultSet r = s.executeQuery(sqlSelect);

        //traiter les réponses
        while (r.next()) {
        	Historique h = new Historique();
            //récupérer les champs
            h.set_id(r.getInt("id"));
            h.set_Conteneur_id(r.getInt("Conteneur_id"));
            h.set_date(r.getTimestamp("date"));
            h.set_poids(r.getInt("poids"));
            h.set_volume(r.getInt("volume"));
            //ajouter à la liste
            liste.add(h);
        }
        
        r.close();
        s.close();
        cnx.close();

        return liste;
    }
    
    @Override
    public List<Historique> selectByConteneur(int c) throws Exception {
        List<Historique> liste = new LinkedList<Historique>();
        //ouvrir la connexion
        Connection cnx = BDManager.getConnexion();
        //faire la requête
        Statement s = cnx.createStatement();
        sqlSelectByConteneur += c + ";";
        ResultSet r = s.executeQuery(sqlSelectByConteneur);

        //traiter les réponses
        while (r.next()) {
        	Historique h = new Historique();
            //récupérer les champs
            h.set_id(r.getInt("id"));
            h.set_Conteneur_id(r.getInt("Conteneur_id"));
            h.set_date(r.getTimestamp("date"));
            h.set_poids(r.getInt("poids"));
            h.set_volume(r.getInt("volume"));
            //ajouter à la liste
            liste.add(h);
        }
        
        r.close();
        s.close();
        cnx.close();

        return liste;
    }

    @Override
    public int insert(Historique h) throws Exception {
        //connexion
        Connection cnx = BDManager.getConnexion();
        //executer la requête
        Statement s = cnx.createStatement();
        sqlInsert += "'" + h.get_Conteneur_id() + "',";
        java.text.SimpleDateFormat sdf =  new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sqlInsert += "'" + sdf.format(h.get_date()) + "',";
        sqlInsert += "'" + h.get_poids() + "',";
        sqlInsert += "'" + h.get_volume() + "')";

        int n = s.executeUpdate(sqlInsert);

        s.close();
        cnx.close();
        return n;
    }

    @Override
    public int update(Historique h) throws Exception {
        //connexion
        Connection cnx = BDManager.getConnexion();
        //executer la requête
        Statement s = cnx.createStatement();
        sqlUpdate += "'" + h.get_Conteneur_id() + "',";
        java.text.SimpleDateFormat sdf =  new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sqlUpdate += "'" + sdf.format(h.get_date()) + "',";
        sqlUpdate += "'" + h.get_poids() + "',";
        sqlUpdate += "'" + h.get_volume() + "')";
        sqlUpdate += " WHERE reference = ' " + h.get_id() + "' ;";

        int n = s.executeUpdate(sqlUpdate);

        s.close();
        cnx.close();
        return n;
    }
    
    @Override
    public int delete(Historique h) throws Exception {
        Connection cnx = BDManager.getConnexion();
        Statement s = cnx.createStatement();
        sqlDelete += h.get_id() + "';";
        int n = s.executeUpdate(sqlDelete);
        return n;

    }
}