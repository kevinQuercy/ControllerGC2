package DAOS;
import DAOS.DAOConteneur;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import data.Conteneur;
import data.Historique;

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
            h.set_lastvolume(r.getInt("lastvolume"));
            h.set_lastpoids(r.getInt("lastpoids"));
            h.set_lastupdate(r.getDate("lastupdate"));
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

    /*@Override
    public int insert(Conteneur c) throws Exception {
        String sql = "INSERT INTO conteneur " + " (id, volumemax, ilot_id, typedechets_id) " + " VALUES( ";
        //connexion
        Connection cnx = BDManager.getConnexion();
        //executer la requête
        Statement s = cnx.createStatement();
        sql += "'" + c.get_id() + "',";
        sql += "'" + c.get_volumemax() + "',";
        sql += "'" + c.get_Ilot_id() + "',";
        sql += "'" + c.get_TypeDechets_id() + ");";

        int n = s.executeUpdate(sql);

        s.close();
        cnx.close();
        return n;
    }

    @Override
    public int update(Conteneur c) throws Exception {
        String sql = "UPDATE conteneur SET " ;
        //connexion
        Connection cnx = BDManager.getConnexion();
        //executer la requête
        Statement s = cnx.createStatement();
        sql += "volumemax='" + c.get_volumemax() + "',";
        sql += "ilot_id='" + c.get_Ilot_id() + ",";
        sql += "typedechets_id='" + c.get_TypeDechets_id() + "'";
        sql += " WHERE id = '" + c.get_id() + "';";

        int n = s.executeUpdate(sql);

        s.close();
        cnx.close();
        return n;
    }*/
    
    public int majetat(Conteneur c) throws Exception {
        String sql = "UPDATE conteneur SET " ;
        //connexion
        Connection cnx = BDManager.getConnexion();
        //executer la requête
        Statement s = cnx.createStatement();
        sql += "lastvolume='" + c.get_lastvolume() + "',";
        sql += "lastpoids='" + c.get_lastpoids() + "',";
        java.text.SimpleDateFormat sdf =  new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sql += "lastupdate='" + sdf.format(c.get_lastupdate()) + "' ";
        sql += " WHERE id = '" + c.get_id() + "' ;";
        int n = s.executeUpdate(sql);
        Historique h = new Historique(c.get_id(),c.get_lastupdate(),c.get_lastpoids(),c.get_lastvolume());
        DAOHistorique daohistorique = DAOFactory.creerDAOHistorique();
        daohistorique.insert(h);
        s.close();
        cnx.close();
        return n;
    }
    
    /*@Override
    public int delete(Conteneur c) throws Exception {
    	String sql = "DELETE FROM conteneur WHERE id = '";
        Connection cnx = BDManager.getConnexion();
        Statement s = cnx.createStatement();
        sql += c.get_id() + "';";
        int n = s.executeUpdate(sql);
        return n;
    }*/
}
