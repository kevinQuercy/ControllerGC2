package webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

import data.Planification;

@WebService
public interface WSPlanification {
    @WebMethod
    Planification creer() throws Exception;
}
