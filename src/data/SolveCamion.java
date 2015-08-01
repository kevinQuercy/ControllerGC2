package data;

/** @file
 * 
 * Encapsulation de Camion pour le calcul d'itineraire
 *
 */

public class SolveCamion implements Standstill {
	protected Camion camion; // camion encapsule
	
	private GeoCoordinate depot;

    // Shadow variables
    protected SolveIlot nextSolveIlot;
    
    public SolveCamion() {
    }

    public SolveCamion(Camion camion, GeoCoordinate depot) {
    	this.camion = camion;
    	this.depot = depot;
    }

    public GeoCoordinate getDepot() {
		return depot;
	}

	public void setDepot(GeoCoordinate depot) {
		this.depot = depot;
	}

	public Camion getCamion() {
		return camion;
	}
	
	@Override
	public GeoCoordinate getLocation() {
		return depot;
	}

	@Override
	public SolveCamion getSolveCamion() {
		return this;
	}

	@Override
	public SolveIlot getNextSolveIlot() {
		return nextSolveIlot;
	}

	@Override
	public void setNextSolveIlot(SolveIlot nextSolveIlot) {
		this.nextSolveIlot = nextSolveIlot;
	}
	
	/* get score
	 * 
	 * Strategy:
	 * - evaluate *time* of the circuit by using:
	 *   - length of the circuit (in metres)
	 *   - penalty time for each ContainerSet to visit
	 * - unit of the evaluation is metres, and so time to visit a ContainerSet should be expressed in
	 *   'equivalent distance that truck can move during the visit of a ContainerSet'
	 *   (example: 3000 => process a ContainerSet is equivalent to drive 3km)
	 * - then score of the truck is squared to search solution that is balancing circuit time between all trucks.
	 *   (see http://docs.jboss.org/optaplanner/release/6.2.0.Final/optaplanner-docs/html_single/index.html#fairnessScoreConstraints)
	 */
	public long getScore() {
		if (nextSolveIlot == null)
			return 0;
		// go to first container set
		double circuitLength = depot.distanceTo(nextSolveIlot.getLocation());
		int nbContainers = 1;
		SolveIlot ilot = nextSolveIlot;
		while (ilot.getNextSolveIlot() != null)
		{
			// go to next container set
			circuitLength += ilot.getLocation().distanceTo(ilot.getNextSolveIlot().getLocation());
			ilot = ilot.getNextSolveIlot();
			nbContainers++;
		}
		// go back to depot
		circuitLength += ilot.getLocation().distanceTo(depot);
		
		long scoreTime = ((long)circuitLength + nbContainers*3000) / 10;//reduce scale to avoid overflows
		
		return -scoreTime*scoreTime;
	}
}
