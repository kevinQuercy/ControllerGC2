package webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface WSConteneur {
    @WebMethod
    public int maj(int id,int volume, int poids) throws Exception;
}
