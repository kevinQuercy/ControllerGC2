package DAOS;
import data.Conteneur;
import java.util.List;

public interface DAOConteneur {
    public List<Conteneur> select() throws Exception ;
    //public int insert(Conteneur a) throws Exception;
    public int delete(Conteneur a) throws Exception;
    //public int update(Conteneur a) throws Exception;
    public int majetat(Conteneur a) throws Exception;
}
