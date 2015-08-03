package core;
import java.net.URL;
import java.util.Date;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import data.Ilot;
import data.Ilotdepassage;
import data.Itineraire;
import data.Planification;
import webservice.WSPlanification;
import webservice.WSIlot; 
public class GC2SOAPClient{
 
	public static void main(String[] args) throws Exception {
 
		URL url = new URL("http://localhost:8081/WS/WSPlanification?wsdl");
		URL urlilot = new URL("http://localhost:8081/WS/WSIlot?wsdl");
        //1st argument service URI, refer to wsdl document above
		//2nd argument is service name, refer to wsdl document above
        QName qnameilot = new QName("http://webservice/","WSIlotImpService");
        QName qname = new QName("http://webservice/","WSPlanificationImpService");
        Service service = Service.create(url, qname);
        Service serviceilot = Service.create(urlilot, qnameilot);
        WSPlanification wsplanification = service.getPort(WSPlanification.class);
        WSIlot wsilot = serviceilot.getPort(WSIlot.class);
        Planification pla = wsplanification.creer(new Date());
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
			for ( int jj = 0 ; jj < ilots.size(); jj++ ) {
				Ilotdepassage il = ilots.get(jj);
				System.out.println ("    Ilot_id: " + il.get_Ilot_id() + " | Ordre: " + il.get_ordre() + " | Itineraire: " + il.get_Itineraire_id());
				Ilot ilot = wsilot.select(il.get_Ilot_id());
				System.out.println("Ilots: " + ilot.get_id() + " Adresse: " + ilot.get_adresse() + ", " + ilot.get_codepostal() + ", " + ilot.get_ville());
			}
		}
		System.out.println ("\nFin de l'affichage");
    }
 
}