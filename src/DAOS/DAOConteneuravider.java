package DAOS;
import data.Conteneuravider;
import java.util.List;

public interface DAOConteneuravider {
    public List<Conteneuravider> selectbyilotanditineraireid(int ilid,int itid) throws Exception ;
    public int insert(Conteneuravider c) throws Exception;
    public int delete(Conteneuravider c) throws Exception;
}
