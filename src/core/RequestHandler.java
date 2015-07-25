package core;

import java.util.logging.Logger;

import org.jdom2.Document;
import org.jdom2.Element;

import data.Container;
import data.ContainerSystem;

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
		Element eltDateState = new Element("date_state");
		eltSupervisionState.addContent(eltDateState);
		//eltDateState.setText(new Date().toString());
		
		//Test Random for Garbage View 
		int total = 0 + (int)(Math.random()*100); 
		eltDateState.setText(Integer.toString(total));
	}
	
	private void handleReqCircuit(Element rootReq, Element rootResp) {
		buildResponseType(rootResp, "RESP_CIRCUIT");
		Element eltCircuit = new Element("circuit");
		rootResp.addContent(eltCircuit);
		for (int i = 0; i < 3; i++)
		{
			Element eltLocation = new Element("location");
			eltCircuit.addContent(eltLocation);
			Element eltLatitude = new Element("latitude");
			eltLocation.addContent(eltLatitude);
			Element eltLongitude = new Element("longitude");
			eltLocation.addContent(eltLongitude);
			eltLatitude.setText("43.6");
			eltLongitude.setText(String.valueOf(1.4+i/100.0));
		}
	}
	
	private void buildResponseType(Element rootResp, String responseType) {
		LOGGER.info("Client #"+clientNumber+" response: "+ responseType);
		Element eltRespType = new Element("response_type");
		eltRespType.setText(responseType);
		rootResp.addContent(eltRespType);
	}
}
