package DAOS;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import DAOS.DAOIlot;
import data.Ilot;

public class DAOMysqlIlot implements DAOIlot {
    private static final String sqlSelect = "SELECT * FROM Ilot;";
    private static String sqlSelectByContact = "SELECT * FROM Ilot WHERE Contact_id = ";
    private static String sqlInsert = "INSERT INTO Ilot "
            + " (adresse,codepostal,ville,contact_id) "
            + " VALUES( ";
    private static String sqlDelete = "DELETE FROM Ilot WHERE id = '";
    private static String sqlUpdate = "UPDATE Ilot "
            + " (adresse,codepostal,ville,contact_id) "
            + " VALUES (" ;

	@Override
	public List<Ilot> select() throws Exception {
        List<Ilot> liste = new LinkedList<Ilot>();
        //ouvrir la connexion
        Connection cnx = BDManager.getConnexion();
        //faire la requête
        Statement s = cnx.createStatement();
        ResultSet r = s.executeQuery(sqlSelect);

        //traiter les réponses
        while (r.next()) {
        	Ilot h = new Ilot();
            //récupérer les champs
            h.set_id(r.getInt("id"));
            h.set_adresse(r.getString("adresse"));
            h.set_codepostal(r.getInt("codepostal"));
            h.set_ville(r.getString("ville"));
            h.set_Contact_id(r.getInt("contact_id"));
            //ajouter à la liste
            liste.add(h);
        }
        
        r.close();
        s.close();
        cnx.close();

        return liste;
	}

	@Override
	public List<Ilot> selectByContact(int i) throws Exception {
        List<Ilot> liste = new LinkedList<Ilot>();
        //ouvrir la connexion
        Connection cnx = BDManager.getConnexion();
        //faire la requête
        Statement s = cnx.createStatement();
        sqlSelectByContact += i + ";";
        ResultSet r = s.executeQuery(sqlSelectByContact);

        //traiter les réponses
        while (r.next()) {
        	Ilot h = new Ilot();
            //récupérer les champs
            h.set_id(r.getInt("id"));
            h.set_adresse(r.getString("adresse"));
            h.set_codepostal(r.getInt("codepostal"));
            h.set_ville(r.getString("ville"));
            h.set_Contact_id(r.getInt("Contact_id"));
            //ajouter à la liste
            liste.add(h);
        }
        
        r.close();
        s.close();
        cnx.close();

        return liste;
	}

	@Override
	public int insert(Ilot i) throws Exception {
        //connexion
        Connection cnx = BDManager.getConnexion();
        //executer la requête
        Statement s = cnx.createStatement();
        sqlInsert += "'" + i.get_adresse() + "',";
        sqlInsert += "'" + i.get_codepostal() + "',";
        sqlInsert += "'" + i.get_ville() + "',";
        sqlInsert += "'" + i.get_Contact_id() + "')";

        int n = s.executeUpdate(sqlInsert);

        s.close();
        cnx.close();
        return n;
	}

	@Override
	public int update(Ilot i) throws Exception {
        //connexion
        Connection cnx = BDManager.getConnexion();
        //executer la requête
        Statement s = cnx.createStatement();
        sqlUpdate += "'" + i.get_adresse() + "',";
        sqlUpdate += "'" + i.get_codepostal() + "',";
        sqlUpdate += "'" + i.get_ville() + "',";
        sqlUpdate += "'" + i.get_Contact_id() +  "')";
        sqlUpdate += " WHERE reference = ' " + i.get_id() + "' ;";

        int n = s.executeUpdate(sqlUpdate);

        s.close();
        cnx.close();
        return n;
	}

	@Override
	public int delete(Ilot i) throws Exception {
        Connection cnx = BDManager.getConnexion();
        Statement s = cnx.createStatement();
        sqlDelete += i.get_id() + "';";
        int n = s.executeUpdate(sqlDelete);
        return n;
	}
}
