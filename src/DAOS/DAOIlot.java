package DAOS;

import java.util.List;

import data.Ilot;


public interface DAOIlot {
    public List<Ilot> select() throws Exception;
    public List<Ilot> selectByContact(int i) throws Exception ;
    //public int insert(Ilot i) throws Exception;
    //public int delete(Ilot i) throws Exception;
    //public int update(Ilot i) throws Exception;
}
