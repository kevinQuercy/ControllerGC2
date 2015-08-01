package core;
import java.net.URL;
import java.util.Date;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import data.Ilotdepassage;
import data.Itineraire;
import data.Planification;
import webservice.WSPlanification; 
public class GC2SOAPClient{
 
	public static void main(String[] args) throws Exception {
 
		URL url = new URL("http://localhost:8081/WS/WSPlanification?wsdl");
 
        //1st argument service URI, refer to wsdl document above
		//2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://webservice/","WSPlanificationImpService");
 
        Service service = Service.create(url, qname);
 
        WSPlanification hello = service.getPort(WSPlanification.class);
        Planification pla = hello.creer();
		System.out.println ("id : " + pla.get_id());
		System.out.println ("taux : " + pla.get_taux());
		System.out.println ("date : " + pla.get_date());
		System.out.println ("\nNombre d'itineraires : " + pla.get_itineraires().size());
		List<Itineraire> lit = pla.get_itineraires();
		for ( int i = 0 ; i < lit.size() ; i++ ) {
			Itineraire it = lit.get(i);
			System.out.println ("  id : " + it.get_id());
			System.out.println ("  Camion_id: " + it.get_Camion_id());
			System.out.println ("  Planification_date : " + it.get_Planification_date());
			System.out.println ("  Longueur : " + it.get_longueur());
			System.out.println ("  Volume total : " + it.get_volumetotal());
			System.out.println ("  Poids total : " + it.get_poidstotal());
			List<Ilotdepassage> ilots = it.get_ilotsdepassage();
			System.out.println ("\n  Nombre d'ilots : " + ilots.size());
		}
		System.out.println ("\nFin de l'affichage");
    }
 
}