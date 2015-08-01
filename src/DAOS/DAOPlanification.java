package DAOS;

import java.util.Date;
import data.Planification;

public interface DAOPlanification {
    public Planification selectbydate(Date d) throws Exception ;
    public int insert(Planification pl) throws Exception;
    public int deletebydate(Date d) throws Exception;
}
