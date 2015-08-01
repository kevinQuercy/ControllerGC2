package webservice;
import javax.xml.ws.Endpoint;

public class WSPublisher {
    public static void main(String[] args){
    	Endpoint.publish("http://localhost:8081/WS/WSPlanification", new WSPlanificationImp());
    }
}
