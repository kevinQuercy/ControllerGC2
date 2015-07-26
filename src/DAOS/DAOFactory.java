package DAOS;

public class DAOFactory {
    private static String typeBD = "Mysql";
    
    public static DAOConteneur creerDAOConteneur(){
        DAOConteneur dao = null;
        if(typeBD.equalsIgnoreCase("Mysql")){
            dao = new DAOMysqlConteneur();
        }
        return dao;
    }

    public static DAOHistorique creerDAOHistorique(){
        DAOHistorique dao = null;
        if(typeBD.equalsIgnoreCase("Mysql")){
            dao = new DAOMysqlHistorique();
        }
        return dao;
    }
}
