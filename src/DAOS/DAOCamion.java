package DAOS;

import java.util.List;
import data.Camion;

public interface DAOCamion {
    public List<Camion> select() throws Exception ;
    public List<Camion> selectdisponible() throws Exception ;
    //public int insert(Camion c) throws Exception;
    //public int delete(Camion c) throws Exception;
    public int updatedisponible(Camion c) throws Exception;
}
