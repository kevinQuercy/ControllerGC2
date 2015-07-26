package DAOS;

import java.util.List;

import data.Historique;

public interface DAOHistorique {
    public List<Historique> select() throws Exception;
    public List<Historique> selectByConteneur(int c) throws Exception ;
    public int insert(Historique h) throws Exception;
    public int delete(Historique h) throws Exception;
    public int update(Historique h) throws Exception;
}
