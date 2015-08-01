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
    public static DAOItineraire creerDAOItineraire(){
        DAOItineraire dao = null;
        if(typeBD.equalsIgnoreCase("Mysql")){
            dao = new DAOMysqlItineraire();
        }
        return dao;
    }
	public static DAOIlotdepassage creerDAOIlotdepassage() {
        DAOIlotdepassage dao = null;
        if(typeBD.equalsIgnoreCase("Mysql")){
            dao = new DAOMysqlIlotdepassage();
        }
        return dao;
	}
	public static DAOPlanification creerDAOPlanification() {
        DAOPlanification dao = null;
        if(typeBD.equalsIgnoreCase("Mysql")){
            dao = new DAOMysqlPlanification();
        }
        return dao;
	}
	public static DAOIlot creerDAOIlot() {
        DAOIlot dao = null;
        if(typeBD.equalsIgnoreCase("Mysql")){
            dao = new DAOMysqlIlot();
        }
        return dao;
	}
	public static DAOCamion creerDAOCamion() {
        DAOCamion dao = null;
        if(typeBD.equalsIgnoreCase("Mysql")){
            dao = new DAOMysqlCamion();
        }
        return dao;
	}
	public static DAOTypedechets creerDAOTypedechets() {
		DAOTypedechets dao = null;
        if(typeBD.equalsIgnoreCase("Mysql")){
            dao = new DAOMysqlTypedechets();
        }
        return dao;
	}
}
