package webservice;

import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebService;

import data.Planification;

@WebService
public interface WSPlanification {
    @WebMethod
    Planification creer(Date d) throws Exception;
}
