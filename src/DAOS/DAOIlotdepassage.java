package DAOS;
import data.Ilotdepassage;
import data.Itineraire;
import java.util.List;

public interface DAOIlotdepassage {
    public List<Ilotdepassage> selectbyitineraire(Itineraire i) throws Exception ;
    public int insert(Ilotdepassage i) throws Exception;
    public int delete(Ilotdepassage i) throws Exception;
}
