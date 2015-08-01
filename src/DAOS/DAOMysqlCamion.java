package DAOS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import data.Camion;

public class DAOMysqlCamion implements DAOCamion{

	@Override
	public List<Camion> select() throws Exception {
        String sql = "SELECT * FROM camion;";
        List<Camion> liste = new LinkedList<Camion>();
        //ouvrir la connexion
        Connection cnx = BDManager.getConnexion();
        //faire la requête
        Statement s = cnx.createStatement();
        ResultSet r = s.executeQuery(sql);
        
        //traiter les réponses
        while (r.next()) {
            liste.add(new Camion(r.getInt("id"),r.getInt("poidsmax"),r.getInt("volumemax"),r.getInt("Typedechets_id"),r.getBoolean("disponible")));
        }

        r.close();
        s.close();
        cnx.close();

        return liste;
	}

	@Override
	public List<Camion> selectdisponible() throws Exception {
        String sql = "SELECT * FROM camion WHERE disponible = '1';";
        List<Camion> liste = new LinkedList<Camion>();
        //ouvrir la connexion
        Connection cnx = BDManager.getConnexion();
        //faire la requête
        Statement s = cnx.createStatement();
        ResultSet r = s.executeQuery(sql);
        
        //traiter les réponses
        while (r.next()) {
            liste.add(new Camion(r.getInt("id"),r.getInt("poidsmax"),r.getInt("volumemax"),r.getInt("Typedechets_id"),r.getBoolean("disponible")));
        }

        r.close();
        s.close();
        cnx.close();

        return liste;
	}
	/*@Override
	public int insert(Camion c) throws Exception {
        //connexion
        Connection cnx = BDManager.getConnexion();
        //executer la requête
        Statement s = cnx.createStatement();
        String sql = "INSERT INTO Camion (poidsmax,disponible) " + " VALUES( ";
        sql += "'" + h.get_id() + "',";
        sql += "'" + c.get_poidsmax() + "',";
        sql += "'" + c.get_disponible() + "')";
        int n = s.executeUpdate(sql);

        s.close();
        cnx.close();
        return n;
	}*/

	/*@Override
	public int delete(Camion c) throws Exception {

	}*/

	@Override
	public int updatedisponible(Camion c) throws Exception {
        String sql = "UPDATE camion SET " ;
        //connexion
        Connection cnx = BDManager.getConnexion();
        //executer la requête
        Statement s = cnx.createStatement();
        int dispo;
        if(c.get_disponible()==true){
        	dispo=1;
        }else{
        	dispo=0;
        }
        sql += "poidsmax='" + c.get_poidsmax() + "',";
        sql += "disponible='" + dispo + "'";
        sql += " WHERE id = '" + c.get_id() + "';";

        int n = s.executeUpdate(sql);

        s.close();
        cnx.close();
        return n;
	}
	
}
