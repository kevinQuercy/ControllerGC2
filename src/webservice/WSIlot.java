package webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

import data.Ilot;

@WebService
public interface WSIlot {
    @WebMethod
    public Ilot select(int id) throws Exception;
}
