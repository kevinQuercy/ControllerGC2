package data;

/** @file
 * 
 * Truck object, that will collect Containers from ContainerSets
 *
 */

public class Truck implements Standstill {
	private GeoCoordinate depot;

    // Shadow variables
    protected ContainerSet nextContainerSet;

    public GeoCoordinate getDepot() {
		return depot;
	}

	public void setDepot(GeoCoordinate depot) {
		this.depot = depot;
	}

	@Override
	public GeoCoordinate getLocation() {
		return depot;
	}

	@Override
	public Truck getTruck() {
		return this;
	}

	@Override
	public ContainerSet getNextContainerSet() {
		return nextContainerSet;
	}

	@Override
	public void setNextContainerSet(ContainerSet nextContainerSet) {
		this.nextContainerSet = nextContainerSet;
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
		if (nextContainerSet == null)
			return 0;
		// go to first container set
		double circuitLength = depot.distanceTo(nextContainerSet.getLocation());
		int nbContainers = 1;
		ContainerSet containerSet = nextContainerSet;
		while (containerSet.getNextContainerSet() != null)
		{
			// go to next container set
			circuitLength += containerSet.getLocation().distanceTo(containerSet.getNextContainerSet().getLocation());
			containerSet = containerSet.getNextContainerSet();
			nbContainers++;
		}
		// go back to depot
		circuitLength += containerSet.getLocation().distanceTo(depot);
		
		long scoreTime = ((long)circuitLength + nbContainers*3000) / 10;//reduce scale to avoid overflows
		
		return -scoreTime*scoreTime;
	}
}
