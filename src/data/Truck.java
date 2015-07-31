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
}
