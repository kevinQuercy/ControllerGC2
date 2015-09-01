package core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jdom2.Document;
import org.jdom2.Element;

import DAOS.DAOConteneur;
import DAOS.DAOFactory;
import DAOS.DAOItineraire;
import DAOS.DAOPlanification;
import data.Camion;
import data.CircuitSolution;
import data.CircuitSolver;
import data.Conteneur;
import data.GeoCoordinate;
import data.Ilot;
import data.Ilotdepassage;
import data.Ilotsavider;
import data.Ilotsglobalsavider;
import data.Itineraire;
import data.Planification;
import data.SolveCamion;
import data.SolveCamion.ItineraireValues;
import data.SolveIlot;
import data.Typedechets;

/** @file
 * 
 * Read XML request from client, do appropriate operations and prepare XML response
 * 
 * <h2>Requests format</h2>
 * <pre>
 *   <request>
 *     <type>request_type</type>
 *     <... specific elements for the request>
 *   </request>
 * </pre>
 * 
 * request_type is one of the following keywords:
 * - CONTAINER_REPORT
 *
 * <h2>Responses format</h2>
 * <pre>
 *   <response>
 *     <type>response_type</type>
 *     <... specific elements for the response>
 *   </response>
 * </pre>
 * 
 * response_type is one of the following keywords:
 * - OK
 * - ERROR
 */

public class RequestHandler {
	private static Logger LOGGER = Logger.getLogger(RequestHandler.class.getName());

	private static final GeoCoordinate depot = new GeoCoordinate(43.602704, 1.441745); // Toulouse center
	private static final int tauxRemplissage = 70; // taux remplissage pour declencher la collecte
	private static final int delaiLimiteToutVenant = 3; // jours maxi pour relever le tout venant
	
	private int clientNumber;

	public RequestHandler(int clientNumber) {
		super();
		this.clientNumber = clientNumber;
	}

	// process request and provide response
	public Document handle(Document request) {
		Element rootResp = new Element("response");
		Document response = new Document(rootResp);

		Element rootReq = request.getRootElement();
		String requestType = rootReq.getChild("request_type").getTextNormalize().toUpperCase();
		LOGGER.info("Client #"+clientNumber+" request: "+ requestType);
		switch(requestType)
		{
			case "CONTAINER_REPORT":
				handleContainerReport(rootReq, rootResp);
				break;
				
			case "REQ_SUPERVISION_STATE":
				handleReqSupervisionState(rootReq, rootResp);
				break;
				
			case "REQ_ALL_CONTAINERS":
				handleReqAllContainers(rootReq,rootResp);
				break;
				
			case "TRIG_CIRCUIT_COMPUTATION":
				handleTrigCircuitComputation(rootReq, rootResp);
				break;
				
			case "REQ_CIRCUITS":
				handleReqCircuits(rootReq, rootResp);
				break;
				
			case "REQ_CIRCUIT":
				handleReqCircuit(rootReq, rootResp);
				break;
				
			default:
				LOGGER.warning("Client #"+clientNumber+" unsupported request: "+requestType);
				buildResponseType(rootResp, "ERROR");
				break;
		}
		
		return response;
	}
	
	private void handleReqAllContainers(Element rootReq, Element rootResp) {
		try {
			
			// get container object associated to this ID
			DAOConteneur daoConteneur = DAOFactory.creerDAOConteneur();
			List<Conteneur> conteneurs = daoConteneur.select();
			System.out.println(conteneurs);
			
			Element eltContainers= new Element("containers");
			
			//[Kevin] : envoi de date au client container supprimé et remplacé par un entier aléatoire
			// à terme, il faudra renvoyer les données présentes dans la base en fonction de l'ID du container qui à envoyé la requête 
			//addField(eltContainers, "containersList", conteneurs);
			
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error connecting to DB", e);
			buildResponseType(rootResp, "ERROR");
			return;
		}
		buildResponseType(rootResp, "OK");
		
	}
	
	private void handleContainerReport(Element rootReq, Element rootResp) {
		try {

			Element eltContRep = rootReq.getChild("container_report");
			int containerId = Integer.valueOf(eltContRep.getChild("id").getTextNormalize());
			int containerVolume = Integer.valueOf(eltContRep.getChild("volume").getTextNormalize());
			
			DAOConteneur daoconteneur = DAOFactory.creerDAOConteneur();
			List<Conteneur> conteneurs = daoconteneur.select();
			
					Conteneur c = conteneurs.get(containerId);
					int max = c.get_volumemax();
					int current = c.get_lastvolume();
					int poids = c.get_lastpoids();
					int min = max/2;
					if(c.get_TypeDechets_id()==2) {
						min = (int)max/3;
					}
					if(c.get_TypeDechets_id()==3) {
						min = 0;
					}
					int volume = (int) (max);
					int maxpoids = 350;
					int minpoids = 30;
					int newpoids = (int)(Math.random() * (maxpoids-minpoids)+minpoids);
					c.majetat(containerVolume,volume);
					System.out.println ("Conteneur: " + (c.get_id()-1) + " | Volmax: " + max + " | Oldvol: " + current + " | Newvol: " + containerVolume +" | OldPoids: " + poids +" | Newpoids: " + newpoids);
					daoconteneur.majetat(c);
				
			} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error connecting to DB", e);
			buildResponseType(rootResp, "ERROR");
			return;
		}

		buildResponseType(rootResp, "OK");
	}
	
	private void handleReqSupervisionState(Element rootReq, Element rootResp) {
		Element eltSupervisionState = new Element("supervision_state");
		
		//[Kevin] : envoi de date au client container supprimé et remplacé par un entier aléatoire
		// à terme, il faudra renvoyer les données présentes dans la base en fonction de l'ID du container qui à envoyé la requête 
		addField(eltSupervisionState, "date_state", Integer.toString((int)(Math.random() * (100 - 0))));
		
		Element eltContainerSets = new Element("container_sets");
		eltSupervisionState.addContent(eltContainerSets);
		
		 try {
			List<Typedechets> type_dechets = DAOFactory.creerDAOTypedechets().select();
			for (Ilot ilot: DAOFactory.creerDAOIlot().select()) {
				Element eltContainerSet = new Element("container_set");
				eltContainerSets.addContent(eltContainerSet);
				
				addLocation(eltContainerSet, "location", ilot.getLocation());
				boolean ilot_a_collecter = false;
				for (Typedechets type_dechet: type_dechets) {
					if (ilot.isReadyForCollect(type_dechet)) {
						ilot_a_collecter = true;
						break;
					}
				}
				addFieldBool(eltContainerSet, "to_be_collected", ilot_a_collecter);
				
				Element eltContainers = new Element("containers");
				eltContainerSet.addContent(eltContainers);
				for (Conteneur conteneur: ilot.get_conteneurs()) {
					Element eltContainer = new Element("container");
					eltContainers.addContent(eltContainer);
					
					addFieldInt(eltContainer, "id", conteneur.get_id());
					addFieldInt(eltContainer, "weight", conteneur.get_lastpoids());
					addFieldInt(eltContainer, "volume", conteneur.get_lastvolume());
					addFieldInt(eltContainer, "volumemax", conteneur.get_volumemax());
					addFieldInt(eltContainer, "fillratio", conteneur.get_fillratio());
					addFieldBool(eltContainer, "to_be_collected", conteneur.isReadyForCollect());
				}
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error connecting to DB", e);
			buildResponseType(rootResp, "ERROR");
			return;
		} 

		buildResponseType(rootResp, "RESP_SUPERVISION_STATE");
		rootResp.addContent(eltSupervisionState);
	}
	
	private void handleTrigCircuitComputation(Element rootReq, Element rootResp) {
		try {
			CircuitSolver solver = new CircuitSolver();
			Planification planif = new Planification();
			planif.set_date(new Date());
			planif.set_taux(tauxRemplissage);
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -delaiLimiteToutVenant);
			Date datelimite = cal.getTime();
			Ilotsglobalsavider etatglobal = new Ilotsglobalsavider(tauxRemplissage,datelimite);

			for (Ilotsavider ilotsavider: etatglobal.get_ilotsavider()) {
				if (ilotsavider.get_ilots().size() > 0 && ilotsavider.get_camions().size() > 0) {
					// construction du probleme
					List<SolveIlot> ilots_a_collecter = new ArrayList<>();
					for (Ilot ilot: ilotsavider.get_ilots())
						ilots_a_collecter.add(new SolveIlot(ilot));
					List<SolveCamion> camions = new ArrayList<>();
					for (Camion camion: ilotsavider.get_camions())
						camions.add(new SolveCamion(camion, depot));
					CircuitSolution problem = new CircuitSolution();
					problem.setSolveIlotList(ilots_a_collecter);
					problem.setSolveCamionList(camions);
					
					// resolution du probleme
					CircuitSolution solution = solver.solve(problem);
					
					// sauvegarde de la solution dans la base
					for (SolveCamion solveCamion: solution.getSolveCamionList()) {
						Itineraire iti = new Itineraire(ilotsavider.get_typedechets(), solveCamion.getCamion().get_id());
						
						int ilotIdx = 0;
						SolveIlot solveIlot = solveCamion.getNextSolveIlot();
						while (solveIlot != null) {
							iti.ajouterilot(solveIlot.getIlot(), ilotIdx++);
							solveIlot = solveIlot.getNextSolveIlot();
						}
						ItineraireValues values = solveCamion.getItineraireValues();
						iti.set_longueur(values.longueur);
						iti.set_poidstotal(values.poidsTotal);
						iti.set_volumetotal(values.volumeTotal);
		
						planif.ajouteritineraire(iti);
					}
				} // if ilots et camions pour collecte
			} // for Ilotsavider
			
			DAOPlanification daoPlanif = DAOFactory.creerDAOPlanification();
			// on supprime une Ã©ventuelle planification existante Ã  cette date
			try {
				daoPlanif.deletebydate(planif.get_date());
			} catch(Exception e) {
				// rien Ã  supprimer: OK
			}
			daoPlanif.insert(planif);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error connecting to DB", e);
			buildResponseType(rootResp, "ERROR");
			return;
		}

		buildResponseType(rootResp, "OK");
	}
	
	private static void addLocation(Element eltRoot, String fieldname, GeoCoordinate geoCoord) {
		Element eltLocation = new Element(fieldname);
		eltRoot.addContent(eltLocation);
		addFieldDouble(eltLocation, "latitude", geoCoord.getLatitude());
		addFieldDouble(eltLocation, "longitude", geoCoord.getLongitude());
	}

	private void handleReqCircuits(Element rootReq, Element rootResp) {
		Element eltCircuits = new Element("circuits");
		try {
			DAOPlanification daoplanification = DAOFactory.creerDAOPlanification();
			Planification pla = daoplanification.selectbydate(new Date());

			for (int i = 0; i < pla.get_itineraires().size(); i++) {
				eltCircuits.addContent(getItineraire(i, pla.get_itineraires().get(i)));
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error connecting to DB", e);
			buildResponseType(rootResp, "ERROR");
			return;
		}
		buildResponseType(rootResp, "RESP_CIRCUITS");
		rootResp.addContent(eltCircuits);

		// liste des ilots non collectÃ©s: pas implÃ©mentÃ©
		Element eltNotCollected = new Element("not_collected");
		eltCircuits.addContent(eltNotCollected);
		eltNotCollected.addContent(new Element("container_sets"));
	}
	
	private void handleReqCircuit(Element rootReq, Element rootResp) {
		Element eltItineraire;
		try {
			int circuitIndex = Integer.valueOf(rootReq.getChild("circuit").getChildTextNormalize("index"));
			DAOItineraire daoItineraire = DAOFactory.creerDAOItineraire();
			Itineraire iti = daoItineraire.selectbydateetcamion(new Date(), circuitIndex);

			eltItineraire = getItineraire(circuitIndex, iti);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error connecting to DB", e);
			buildResponseType(rootResp, "ERROR");
			return;
		}
		buildResponseType(rootResp, "RESP_CIRCUIT");
		rootResp.addContent(eltItineraire);
	}
	
	private Element getItineraire(int circuitIndex, Itineraire iti) {
		Element eltCircuit = new Element("circuit");
		addFieldInt(eltCircuit, "index", circuitIndex);
		addLocation(eltCircuit, "depot_location", depot);
		addFieldInt(eltCircuit, "dechet_id", iti.get_Typedechets_id());
		Element eltContainerSets = getIlotsdepassage(iti.get_ilotsdepassage()); 
		eltCircuit.addContent(eltContainerSets);
		return eltCircuit;
	}
	
	private Element getIlotsdepassage(List<Ilotdepassage> ilotsdepassage) {
		Element eltContainerSets = new Element("container_sets");
		
		for (Ilotdepassage ilotdepassage: ilotsdepassage) {
			Element eltContainerSet = new Element("container_set");
			eltContainerSets.addContent(eltContainerSet);
			Ilot ilot = ilotdepassage.get_Ilot();
			
			addLocation(eltContainerSet, "location", ilot.getLocation());
			
			Element eltContainers = new Element("containers");
			eltContainerSet.addContent(eltContainers);
			for (Conteneur conteneur: ilot.get_conteneurs()) {
				Element eltContainer = new Element("container");
				eltContainers.addContent(eltContainer);
				
				addFieldInt(eltContainer, "id", conteneur.get_id());
			}
		}
		return eltContainerSets;
	}
	
	private void buildResponseType(Element rootResp, String responseType) {
		LOGGER.info("Client #"+clientNumber+" response: "+ responseType);
		addField(rootResp, "response_type", responseType);
	}
	
	private static void addField(Element eltRoot, String fieldname, String value) {
		Element elt = new Element(fieldname);
		elt.setText(value);
		eltRoot.addContent(elt);
	}
	
	private static void addFieldInt(Element eltRoot, String fieldname, int value) {
		addField(eltRoot, fieldname, String.valueOf(value));
	}
	
	private static void addFieldBool(Element eltRoot, String fieldname, boolean value) {
		addField(eltRoot, fieldname, String.valueOf(value));
	}
	
	private static void addFieldDouble(Element eltRoot, String fieldname, double value) {
		addField(eltRoot, fieldname, String.valueOf(value));
	}
}
