package core;

import java.util.Date;
import java.util.logging.Logger;

import org.jdom2.Document;
import org.jdom2.Element;

import data.Container;
import data.ContainerSet;
import data.ContainerSystem;
import data.GeoCoordinate;

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
				
			case "TRIG_CIRCUIT_COMPUTATION":
				handleTrigCircuitComputation(rootReq, rootResp);
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
	
	private void handleContainerReport(Element rootReq, Element rootResp) {
		// get container ID
		Element eltContRep = rootReq.getChild("container_report");
		int containerId = Integer.valueOf(eltContRep.getChild("id").getTextNormalize());
		
		// get container object associated to this ID
		Container container = ContainerSystem.getContainerSystem().getContainer(containerId);
		
		// update container state
		container.setState(
				Integer.valueOf(eltContRep.getChild("weight").getTextNormalize()),
				Integer.valueOf(eltContRep.getChild("volume").getTextNormalize()),
				Integer.valueOf(eltContRep.getChild("volumemax").getTextNormalize())
		);

		buildResponseType(rootResp, "OK");
	}
	
	private void handleReqSupervisionState(Element rootReq, Element rootResp) {
		buildResponseType(rootResp, "RESP_SUPERVISION_STATE");
		Element eltSupervisionState = new Element("supervision_state");
		rootResp.addContent(eltSupervisionState);
		//[Kevin] : envoi de date au client container comment� et remplac� par un entier al�atoire
		// � terme, il faudra renvoyer les donn�es pr�sentes dans la base en fonction de l'ID du container qui � envoy� la requ�te 
		//addField(eltSupervisionState, "date_state", new Date().toString());
		addField(eltSupervisionState, "date_state", Integer.toString((int)(Math.random() * (100 - 0))));
		
		Element eltContainerSets = new Element("container_sets");
		eltSupervisionState.addContent(eltContainerSets);
		
		for (ContainerSet cs: ContainerSystem.getContainerSystem().getContainerSets()) {
			Element eltContainerSet = new Element("container_set");
			eltContainerSets.addContent(eltContainerSet);
			
			addLocation(eltContainerSet, cs.getLocation());
			addFieldBool(eltContainerSet, "to_be_collected", cs.isReadyForCollect());
			
			Element eltContainers = new Element("containers");
			eltContainerSet.addContent(eltContainers);
			for (Container container: cs.getContainers()) {
				Element eltContainer = new Element("container");
				eltContainers.addContent(eltContainer);
				
				addFieldInt(eltContainer, "id", container.getContainerId());
				addFieldInt(eltContainer, "weight", container.getWeight());
				addFieldInt(eltContainer, "volume", container.getVolume());
				addFieldInt(eltContainer, "volumemax", container.getVolumeMax());
				addFieldInt(eltContainer, "fillratio", container.getFillRatio());
				addFieldBool(eltContainer, "to_be_collected", container.isReadyForCollect());
			}
		}
	}
	
	private void handleTrigCircuitComputation(Element rootReq, Element rootResp) {
		ContainerSystem.getContainerSystem().trigCircuitComputation();
		buildResponseType(rootResp, "OK");
	}
	
	private static void addLocation(Element eltRoot, GeoCoordinate geoCoord) {
		Element eltLocation = new Element("location");
		eltRoot.addContent(eltLocation);
		addFieldDouble(eltLocation, "latitude", geoCoord.getLatitude());
		addFieldDouble(eltLocation, "longitude", geoCoord.getLongitude());
	}

	private void handleReqCircuit(Element rootReq, Element rootResp) {
		buildResponseType(rootResp, "RESP_CIRCUIT");
		Element eltCircuit = new Element("circuit");
		rootResp.addContent(eltCircuit);
		for (int i = 0; i < 3; i++)
		{
			Element eltLocation = new Element("location");
			eltCircuit.addContent(eltLocation);
			addField(eltLocation, "latitude", "43.6");
			addField(eltLocation, "longitude", String.valueOf(1.4+i/100.0));
		}
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
