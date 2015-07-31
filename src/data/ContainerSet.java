package data;

import java.util.ArrayList;
import java.util.List;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.AnchorShadowVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariableGraphType;

/** @file
 * 
 * Set of containers, in the same building
 *
 */

@PlanningEntity
public class ContainerSet implements Standstill {
	private List<Container> containers;
	private GeoCoordinate location;
	
    // Planning variables: changes during planning, between score calculations.
    protected Standstill previousStandstill;

    // Shadow variables
    protected ContainerSet nextContainerSet;
    protected Truck truck;

    public ContainerSet() {
    	super();
    }

	public ContainerSet(GeoCoordinate location) {
		super();
		this.location = location;
		containers = new ArrayList<>();
	}
	
	public List<Container> getContainers() {
		return containers;
	}

	public GeoCoordinate getLocation() {
		return location;
	}

    @PlanningVariable(valueRangeProviderRefs = {"vehicleRange", "customerRange"},
            graphType = PlanningVariableGraphType.CHAINED)
	public Standstill getPreviousStandstill() {
		return previousStandstill;
	}

	public void setPreviousStandstill(Standstill previousStandstill) {
		this.previousStandstill = previousStandstill;
	}

    @AnchorShadowVariable(sourceVariableName = "previousStandstill")
	public Truck getTruck() {
		return truck;
	}

	public void setTruck(Truck truck) {
		this.truck = truck;
	}

	public void addContainer(Container container) {
		containers.add(container);
	}
	
	public boolean isReadyForCollect() {
		// check how many containers are full
		int containersFull = 0;
		for (Container container: containers) {
			if (container.isReadyForCollect())
				containersFull++;
		}
		return containersFull*100/containers.size() >= 60; // if 60% or more containers are full, need to collect the set
	}

	@Override
	public ContainerSet getNextContainerSet() {
		return nextContainerSet;
	}

	@Override
	public void setNextContainerSet(ContainerSet nextContainerSet) {
		this.nextContainerSet = nextContainerSet;
	}
	
    /**
     * @return a positive number, the distance multiplied by 1000 to avoid floating point arithmetic rounding errors
     */
    public int getDistanceFromPreviousStandstill() {
        if (previousStandstill == null) {
            return 0;
        }
        return getDistanceFrom(previousStandstill);
    }

    /**
     * @param standstill never null
     * @return a positive number, the distance multiplied by 1000 to avoid floating point arithmetic rounding errors
     */
    public int getDistanceFrom(Standstill standstill) {
        return (int)(1000*standstill.getLocation().distanceTo(location));
    }

    /**
     * @param standstill never null
     * @return a positive number, the distance multiplied by 1000 to avoid floating point arithmetic rounding errors
     */
    public int getDistanceTo(Standstill standstill) {
        return (int)(1000*location.distanceTo(standstill.getLocation()));
    }
}
