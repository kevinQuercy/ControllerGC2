package data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.Solution;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

@PlanningSolution
public class CircuitSolution implements Solution<HardSoftScore> {
    protected List<Truck> truckList;
	protected List<ContainerSet> containerSetList;
	protected HardSoftScore score;

    @PlanningEntityCollectionProperty
    @ValueRangeProvider(id = "vehicleRange")
    public List<Truck> getTruckList() {
        return truckList;
    }

    public void setVehicleList(List<Truck> truckList) {
        this.truckList = truckList;
    }

    @PlanningEntityCollectionProperty
    @ValueRangeProvider(id = "customerRange")
    public List<ContainerSet> getCustomerList() {
        return containerSetList;
    }

    public void setContainerSetList(List<ContainerSet> containerSetList) {
        this.containerSetList = containerSetList;
    }

    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }

	@Override
	public Collection<? extends Object> getProblemFacts() {
        List<Object> facts = new ArrayList<Object>();
        facts.addAll(containerSetList);
        // Do not add the planning entities (vehicleList, customerList) because that will be done automatically
        return facts;
	}
}